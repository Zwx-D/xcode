package com.xcode.app.service.impl;

import com.xcode.app.domain.CollectionFolder;
import com.xcode.app.domain.CollectionItem;
import com.xcode.app.domain.WechatUser;
import com.xcode.app.repository.CollectionFolderRepository;
import com.xcode.app.repository.CollectionItemRepository;
import com.xcode.app.repository.WechatUserRepository;
import com.xcode.app.service.CollectionFolderService;
import com.xcode.app.service.mapper.CollectionFolderMapper;
import com.xcode.app.web.rest.dto.CreateCollectionFolderDTO;
import com.xcode.app.web.rest.dto.UpdateCollectionItemDTO;
import com.xcode.app.web.rest.vm.CollectionFolderVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CollectionFolderServiceImpl implements CollectionFolderService {

    @Autowired
    private CollectionFolderRepository repository;

    @Autowired
    private CollectionFolderMapper mapper;

    @Autowired
    private WechatUserRepository wechatUserRepository;

    @Autowired
    private CollectionItemRepository collectionItemRepository;

    @Override
    public CollectionFolderVM save(CreateCollectionFolderDTO vm) {
        WechatUser wechatUser = wechatUserRepository.findOneById(vm.getWechatUserId()).orElseThrow(() -> {
            throw new RuntimeException("找不到当前用户");
        });
        Optional<CollectionFolder> oneByNameAndWechatUserId = repository.findOneByNameAndWechatUserId(vm.getName(), vm.getWechatUserId());
        if (oneByNameAndWechatUserId.isPresent()) {
            throw new RuntimeException("已存在收藏夹：" + vm.getName());
        }
        CollectionFolder collectionFolder = new CollectionFolder();
        collectionFolder.setCollectionItemList(new ArrayList<>());
        collectionFolder.setDesc(vm.getDesc());
        collectionFolder.setName(vm.getName());
        collectionFolder.setWechatUser(wechatUser);
        CollectionFolder save = repository.save(collectionFolder);
        return mapper.toVM(save);
    }

    @Override
    public CollectionFolderVM update(CollectionFolderVM vm) {
        CollectionFolder collectionFolder = repository.findOneByUuid(vm.getUuid()).orElseThrow(() -> {
            throw new RuntimeException("找不到对应收藏夹");
        });
        collectionFolder.setName(vm.getName());
        collectionFolder.setDesc(vm.getDesc());
        CollectionFolder save = repository.save(collectionFolder);
        return mapper.toVM(save);
    }

    @Override
    public CollectionFolderVM findOneByUuid(String uuid) {
        return repository.findOneByUuid(uuid).map(mapper::toVM).orElseThrow(() -> {
            throw new RuntimeException("找不到对应收藏夹");
        });
    }

    @Override
    public void delete(String uuid) {
        CollectionFolder collectionFolder = repository.findOneByUuid(uuid).orElseThrow(() -> {
            throw new RuntimeException("找不到对应收藏夹");
        });
        repository.delete(collectionFolder);
    }

    @Override
    public List<CollectionFolderVM> findByWechatUserId(Long wechatUserId) {
        List<CollectionFolder> byWechatUserId = repository.findByWechatUserId(wechatUserId);
        return mapper.toVMList(byWechatUserId);
    }

    @Override
    public void addItemInFolder(UpdateCollectionItemDTO dto) {
        CollectionFolder collectionFolder = repository.findOneByUuid(dto.getFolderUuid()).orElseThrow(() -> {
            throw new RuntimeException("找不到对应收藏夹");
        });
        List<CollectionItem> collectionItemList = collectionFolder.getCollectionItemList();
        List<CollectionItem> collect = collectionItemList.stream().filter(item -> item.getImageUuid().equals(dto.getItemUuid())).collect(Collectors.toList());
        if (collect.size() > 0) {
            throw new RuntimeException("已经收藏过了");
        }
        CollectionItem collectionItem = new CollectionItem();
        collectionItem.setCollectionFolder(collectionFolder);
        collectionItem.setImageUuid(dto.getItemUuid());
        collectionItem.setName(dto.getItemUuid());
        collectionItemRepository.save(collectionItem);
    }

    @Override
    public void delItemInFolder(UpdateCollectionItemDTO dto) {
        CollectionFolder collectionFolder = repository.findOneByUuid(dto.getFolderUuid()).orElseThrow(() -> {
            throw new RuntimeException("找不到对应收藏夹");
        });
        CollectionItem collectionItem = collectionItemRepository.findOneByUuid(dto.getItemUuid()).orElseThrow(() -> {
            throw new RuntimeException("找不到对应项");
        });
        collectionItemRepository.delete(collectionItem);
    }

}

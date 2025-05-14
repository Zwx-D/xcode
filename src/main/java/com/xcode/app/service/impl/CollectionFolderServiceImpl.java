package com.xcode.app.service.impl;

import com.xcode.app.domain.CollectionFolder;
import com.xcode.app.domain.WechatUser;
import com.xcode.app.repository.CollectionFolderRepository;
import com.xcode.app.repository.WechatUserRepository;
import com.xcode.app.service.CollectionFolderService;
import com.xcode.app.service.mapper.CollectionFolderMapper;
import com.xcode.app.web.rest.dto.CreateCollectionFolderDTO;
import com.xcode.app.web.rest.vm.CollectionFolderVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CollectionFolderServiceImpl implements CollectionFolderService {

    @Autowired
    private CollectionFolderRepository repository;

    @Autowired
    private CollectionFolderMapper mapper;

    @Autowired
    private WechatUserRepository wechatUserRepository;

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
        return null;
    }

    @Override
    public CollectionFolderVM findOneByUuid(String uuid) {
        return repository.findOneByUuid(uuid).map(mapper::toVM).orElseThrow(() -> {
            throw new RuntimeException("找不到对应作品集");
        });
    }

}

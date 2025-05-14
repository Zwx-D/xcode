package com.xcode.app.service.impl;

import com.xcode.app.domain.Photographer;
import com.xcode.app.repository.PhotographerRepository;
import com.xcode.app.service.PhotographerService;
import com.xcode.app.service.mapper.PhotographerMapper;
import com.xcode.app.web.rest.vm.PhotographerVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PhotographerServiceImpl implements PhotographerService {

    @Autowired
    private PhotographerRepository repository;

    @Autowired
    private PhotographerMapper mapper;

    @Override
    public PhotographerVM save(PhotographerVM vm) {
        List<Photographer> all = repository.findAll();
        if (all.size() > 0) {
            Photographer photographer = all.get(0);
            photographer.setDesc(vm.getDesc());
            photographer.setImageUuid(vm.getImageUuid());
            photographer.setName(vm.getName());
            return mapper.toVM(repository.save(photographer));
        } else {
            return mapper.toVM(repository.save(mapper.toEntity(vm)));
        }
    }

    @Override
    public PhotographerVM getPhotographer() {
        List<Photographer> all = repository.findAll();
        if (all.size() > 0) {
            return mapper.toVM(all.get(0));
        } else {
            throw new RuntimeException("还没创建摄影者信息~");
        }
    }

}

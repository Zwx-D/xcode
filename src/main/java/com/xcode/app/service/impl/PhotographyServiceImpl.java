package com.xcode.app.service.impl;

import com.xcode.app.domain.Photography;
import com.xcode.app.repository.PhotographyRepository;
import com.xcode.app.service.PhotographyService;
import com.xcode.app.service.mapper.PhotographyMapper;
import com.xcode.app.web.rest.vm.PhotographyVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PhotographyServiceImpl implements PhotographyService {

    @Autowired
    private PhotographyRepository repository;

    @Autowired
    private PhotographyMapper mapper;

    @Override
    public PhotographyVM save(PhotographyVM vm) {
        return mapper.toVM(repository.save(mapper.toEntity(vm)));
    }

    @Override
    public void delete(String uuid) {
        Optional<Photography> oneByUuid = repository.findOneByUuid(uuid);
        if (oneByUuid.isPresent()) {
            Photography photography = oneByUuid.get();
            repository.delete(photography);
        } else {
            throw new RuntimeException("找不到对应摄影展示");
        }

    }

    @Override
    public void updateIsShow(String uuid, Boolean isShow) {
        Optional<Photography> oneByUuid = repository.findOneByUuid(uuid);
        if (oneByUuid.isPresent()) {
            Photography photography = oneByUuid.get();
            photography.setIsShow(isShow);
            repository.save(photography);
        } else {
            throw new RuntimeException("找不到对应摄影展示");
        }

    }
}

package com.xcode.app.service;

import com.xcode.app.web.rest.vm.PhotographyVM;

import java.util.List;

public interface PhotographyService {

    PhotographyVM save(PhotographyVM vm);

    void delete(String uuid);

    void updateIsShow(String uuid, Boolean isShow);

    List<PhotographyVM> findAllByIsShow(Boolean isShow);

}

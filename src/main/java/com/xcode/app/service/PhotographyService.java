package com.xcode.app.service;

import com.xcode.app.web.rest.vm.PhotographyVM;

public interface PhotographyService {

    PhotographyVM save(PhotographyVM vm);

    void delete(String uuid);

    void updateIsShow(String uuid, Boolean isShow);

}

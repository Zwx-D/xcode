package com.xcode.app.service;

import com.xcode.app.web.rest.vm.PhotographerVM;

public interface PhotographerService {

    PhotographerVM save(PhotographerVM vm);

    PhotographerVM getPhotographer();

}

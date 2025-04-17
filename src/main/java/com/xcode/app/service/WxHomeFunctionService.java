package com.xcode.app.service;

import com.xcode.app.web.rest.vm.WxHomeFunctionVM;

import java.util.List;

public interface WxHomeFunctionService {

    WxHomeFunctionVM save(WxHomeFunctionVM vm);

    void delete(String uuid);

    void updateIsShow(String uuid, Boolean isShow);

    List<WxHomeFunctionVM> findAllByIsShow(Boolean isShow);

}

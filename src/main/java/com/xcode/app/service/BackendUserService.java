package com.xcode.app.service;

import com.xcode.app.domain.BackendUser;
import com.xcode.app.web.rest.vm.BackendUserVM;

public interface BackendUserService {

    String registerUser(BackendUser user);

    String loginUser(String username, String password);

}

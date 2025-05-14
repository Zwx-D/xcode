package com.xcode.app.web.rest.api;

import com.xcode.app.web.rest.filter.WechatUserCriteria;
import com.xcode.app.web.rest.vm.WechatUserVM;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface WechatUserApi {

    @GetMapping("/wechatUser")
    ResponseEntity<List<WechatUserVM>> findByCriteria(@SpringQueryMap WechatUserCriteria criteria,
                                                      @PageableDefault(page = 0, size = 10) Pageable pageable);

}

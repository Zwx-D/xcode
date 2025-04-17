package com.xcode.app.web.rest.api;

import com.xcode.app.web.rest.filter.WxHomeFunctionCriteria;
import com.xcode.app.web.rest.vm.WxHomeFunctionVM;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface WxHomeFunctionApi {

    @GetMapping("/wxHomeFunction")
    ResponseEntity<List<WxHomeFunctionVM>> findByCriteria(@SpringQueryMap WxHomeFunctionCriteria criteria,
                                                          @PageableDefault(page = 0, size = 10) Pageable pageable);

    @PostMapping("/wxHomeFunction")
    ResponseEntity<WxHomeFunctionVM> save(@RequestBody WxHomeFunctionVM vm);

    @DeleteMapping("/wxHomeFunction/{uuid}")
    ResponseEntity<Void> delete(@PathVariable String uuid);

    @PutMapping("/wxHomeFunction/{uuid}")
    ResponseEntity<Void> updateIsShow(@PathVariable String uuid, @RequestParam Boolean isShow);

}

package com.xcode.app.web.rest.api;

import com.xcode.app.web.rest.filter.PhotographyCriteria;
import com.xcode.app.web.rest.vm.PhotographyVM;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PhotographyApi {

    @PostMapping("/photography")
    ResponseEntity<PhotographyVM> save(@RequestBody PhotographyVM vm);

    @GetMapping("/photography")
    ResponseEntity<List<PhotographyVM>> findByCriteria(@SpringQueryMap PhotographyCriteria criteria,
                                                         @PageableDefault(page = 0, size = 10) Pageable pageable);

    @DeleteMapping("/photography/{uuid}")
    ResponseEntity<Void> delete(@PathVariable String uuid);

    @PutMapping("/photography/{uuid}")
    ResponseEntity<Void> updateIsShow(@PathVariable String uuid, @RequestParam Boolean isShow);

}

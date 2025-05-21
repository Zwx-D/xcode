package com.xcode.app.web.rest.api;

import com.xcode.app.web.rest.filter.PortfolioTypeTagCriteria;
import com.xcode.app.web.rest.vm.PortfolioTypeTagVM;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PortfolioTypeTagApi {

    @GetMapping("/portfolioTag")
    ResponseEntity<List<PortfolioTypeTagVM>> findByCriteria(@SpringQueryMap PortfolioTypeTagCriteria criteria,
                                                            @PageableDefault(page = 0, size = 10) Pageable pageable);

    @PostMapping("/portfolioTag")
    ResponseEntity<PortfolioTypeTagVM> save(@RequestBody PortfolioTypeTagVM vm);

    @PutMapping("/portfolioTag")
    ResponseEntity<PortfolioTypeTagVM> update(@RequestBody PortfolioTypeTagVM vm);

    @DeleteMapping("/portfolioTag/{uuid}")
    ResponseEntity<Void> delete(@PathVariable String uuid);

    @GetMapping("/portfolioTag/all")
    ResponseEntity<List<PortfolioTypeTagVM>> findAll();

}

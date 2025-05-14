package com.xcode.app.web.rest.api;

import com.xcode.app.web.rest.filter.PortfolioCriteria;
import com.xcode.app.web.rest.vm.PortfolioVM;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PortfolioApi {

    @GetMapping("/portfolio")
    ResponseEntity<List<PortfolioVM>> findByCriteria(@SpringQueryMap PortfolioCriteria criteria,
                                                     @PageableDefault(page = 0, size = 10) Pageable pageable);

    @PostMapping("/portfolio")
    ResponseEntity<PortfolioVM> save(@RequestBody PortfolioVM vm);

    @PutMapping("/portfolio")
    ResponseEntity<PortfolioVM> update(@RequestBody PortfolioVM vm);

    @GetMapping("/portfolio/{uuid}")
    ResponseEntity<PortfolioVM> findOneByUuid(@PathVariable String uuid);

}

package com.xcode.app.web.rest.api;

import com.xcode.app.web.rest.dto.CreatePortfolioItemDTO;
import com.xcode.app.web.rest.filter.PortfolioCriteria;
import com.xcode.app.web.rest.filter.PortfolioItemCriteria;
import com.xcode.app.web.rest.vm.PortfolioItemVM;
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

    @GetMapping("/portfolioItem")
    ResponseEntity<List<PortfolioItemVM>> findPortfolioItemByCriteria(@SpringQueryMap PortfolioItemCriteria criteria,
                                                                      @PageableDefault(page = 0, size = 10) Pageable pageable);

    @PostMapping("/portfolio")
    ResponseEntity<PortfolioVM> save(@RequestBody PortfolioVM vm);

    @PutMapping("/portfolio")
    ResponseEntity<PortfolioVM> update(@RequestBody PortfolioVM vm);

    @GetMapping("/portfolio/changeIsShow/{uuid}")
    ResponseEntity<Void> changIsShow(@PathVariable String uuid, @RequestParam("isShow") Boolean isShow);

    @DeleteMapping("/portfolio/{uuid}")
    ResponseEntity<Void> delete(@PathVariable String uuid);

    @GetMapping("/portfolio/{uuid}")
    ResponseEntity<PortfolioVM> findOneByUuid(@PathVariable String uuid);

    @PostMapping("/portfolioItem")
    ResponseEntity<Void> createPortfolioItem(@RequestBody CreatePortfolioItemDTO dto);

    @GetMapping("/portfolioItem/changeIsShow/{uuid}")
    ResponseEntity<Void> changItemIsShow(@PathVariable String uuid, @RequestParam("isShow") Boolean isShow);

    @DeleteMapping("/portfolioItem/{uuid}")
    ResponseEntity<Void> deletePortfolioItem(@PathVariable String uuid);

}

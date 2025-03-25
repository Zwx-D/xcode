package com.xcode.app.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public class HeaderUtils {

    public static HttpHeaders createHeadersWithTotalCount(Page<?> page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        return headers;
    }

}

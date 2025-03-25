package com.xcode.app.web.rest.vm;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class JwtVM implements Serializable {
    private String token;
}

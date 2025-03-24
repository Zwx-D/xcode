package com.xcode.app.web.rest.dto.deepseek.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class Messages implements Serializable {
    private String content;
    private String role;
}

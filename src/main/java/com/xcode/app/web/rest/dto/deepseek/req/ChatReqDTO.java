package com.xcode.app.web.rest.dto.deepseek.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ChatReqDTO implements Serializable {

    private List<Messages> messages;
    private String model;
    private Integer frequency_penalty;
    private Integer max_tokens;
    private Integer presence_penalty;
    private Map response_format;
    private String stop;
    private Boolean stream;
    private String stream_options;
    private Integer temperature;
    private Integer top_p;
    private String tools;
    private String tool_choice;
    private Boolean logprobs;
    private String top_logprobs;

}

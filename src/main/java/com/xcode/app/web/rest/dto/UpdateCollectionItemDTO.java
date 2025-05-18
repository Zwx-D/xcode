package com.xcode.app.web.rest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateCollectionItemDTO implements Serializable {

    private String folderUuid;

    private String itemUuid;

}

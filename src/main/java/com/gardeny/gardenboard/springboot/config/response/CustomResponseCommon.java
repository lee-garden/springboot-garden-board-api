package com.gardeny.gardenboard.springboot.config.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class CustomResponseCommon {
    private boolean success;

    private int status_code;

    private String msg;
}

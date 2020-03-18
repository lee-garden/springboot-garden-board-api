package com.gardeny.gardenboard.springboot.config.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response extends CustomResponseCommon {
    @Builder
    public Response(boolean success, int status_code, String msg) {
        super(success, status_code, msg);
    }
}

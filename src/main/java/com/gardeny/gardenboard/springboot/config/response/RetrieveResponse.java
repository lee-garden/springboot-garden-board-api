package com.gardeny.gardenboard.springboot.config.response;

import lombok.*;

@Getter
@Setter
public class RetrieveResponse<T> extends CustomResponseCommon {
    private T data;

    @Builder
    public RetrieveResponse(boolean success, int status_code, String msg, T data) {
        super(success, status_code, msg);
        this.data = data;
    }
}

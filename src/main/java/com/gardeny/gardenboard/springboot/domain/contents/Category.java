package com.gardeny.gardenboard.springboot.domain.contents;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    NORMAL("NORMAL", "일반 게시판"),
    INFO("INFO", "정보 게시판"),
    MARKET("MARKET", "장터 게시판");

    private final String key;
    private final String name;

}

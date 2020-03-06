package domain.contents;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    NORMAL("normal", "일반 게시판"),
    INFO("info", "정보 게시판"),
    MARKET("market", "장터");

    private final String key;
    private final String name;

}

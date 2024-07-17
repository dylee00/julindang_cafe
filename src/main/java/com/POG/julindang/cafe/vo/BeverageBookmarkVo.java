package com.POG.julindang.cafe.vo;

import java.time.LocalDateTime;

public interface BeverageBookmarkVo {
    String getBeverageName();

    String getCafeName();

    LocalDateTime getCreatedAt();

    Double getCalorie();

    Double getSugar();

    Long getCafeId();
}

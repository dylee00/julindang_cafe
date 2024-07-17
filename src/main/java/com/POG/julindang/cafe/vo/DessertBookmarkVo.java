package com.POG.julindang.cafe.vo;

import java.time.LocalDateTime;

public interface DessertBookmarkVo {
    String getDessertName();

    String getCafeName();

    LocalDateTime getCreatedAt();

    Double getCalorie();

    Double getSugar();

    Long getDessertId();
}

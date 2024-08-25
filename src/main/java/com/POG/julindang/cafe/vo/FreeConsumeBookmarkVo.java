package com.POG.julindang.cafe.vo;


import java.time.LocalDateTime;

public interface FreeConsumeBookmarkVo {
    Long getProductId();
    String getName();
    Long getSugar();
    Long getCalorie();
    Integer getSize();
    Integer getType();
    LocalDateTime getCreatedAt();
}

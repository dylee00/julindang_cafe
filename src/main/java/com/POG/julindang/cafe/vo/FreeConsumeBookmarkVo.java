package com.POG.julindang.cafe.vo;


import java.time.LocalDateTime;

public interface FreeConsumeBookmarkVo {
    Long getConsumeId();
    Long getProductId();
    String getName();
    Long getSugar();
    Long getCalorie();
    Integer getType();
    LocalDateTime getCreatedAt();
}

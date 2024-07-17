package com.POG.julindang.cafe.vo;


import java.time.LocalDateTime;

public interface FreeConsumeBookmarkVo {
    Long getConsumeId();
    String getName();
    Long getSugar();
    Long getCalorie();
    LocalDateTime getCreatedAt();
}

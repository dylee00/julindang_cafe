package com.POG.julindang.cafe.dto.response.cafe;

import java.time.LocalDateTime;

public interface CafeLikeResponseDto {
    public String getCafeName();
    public String getUrl();
    public Long getIsLiked();
    public LocalDateTime getCreatedAt();
}

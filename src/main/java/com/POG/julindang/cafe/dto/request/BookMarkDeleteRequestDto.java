package com.POG.julindang.cafe.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookMarkDeleteRequestDto {
    private String userEmail;
    private String cafeName;
    private String beverageName;
}

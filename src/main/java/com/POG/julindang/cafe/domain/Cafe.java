package com.POG.julindang.cafe.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAFE_ID")
    private Long id;


    @Column(name = "CAFE_NAME")
    private String cafeName;

    @Column(name = "BEVERAGE_NAME")
    private String beverageName;

    private String size;

    private Double serve;

    private Double sugar;

    private Double calorie;

    private Boolean temperature;

    private Boolean deleted;

   // private String manager;  // 담당자

}

package com.POG.julindang.cafe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="beverage_image")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeverageImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BEVERAGE_IMAGE_ID")
    private Long id;

    @NotNull
    @Column(name = "cafe_name", columnDefinition = "varchar(30)")
    private String cafeName;

    @NotNull
    @Column(name = "beverage_name", columnDefinition = "varchar(50)")
    private String beverageName;

    @Column(name = "url", columnDefinition = "text")
    private String url;
}

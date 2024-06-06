package com.POG.julindang.cafe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="dessert_image")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DessertImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private Long id;

    @NotNull
    @Column(columnDefinition = "varchar(30)")
    private String cafeName;

    @NotNull
    @Column(columnDefinition = "varchar(50)")
    private String dessertName;

    @Column(columnDefinition = "text")
    private String url;


}

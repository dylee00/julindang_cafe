package com.POG.julindang.cafe.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
@Table(name="cafe")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAFE_ID")
    private Long id;
    @NotNull
    @Column(name="CAFE_NAME", columnDefinition = "varchar(30)", length = 30)
    private String cafeName;
    @NotNull
    @Column(name = "BEVERAGE_NAME", columnDefinition = "varchar(50)", length = 50)
    private String beverageName;
    @NotNull
    @Column(name="SIZE", columnDefinition = "varchar(10)", length = 10)
    private String size;
    @NotNull
    private Double serve;
    @NotNull
    private Double sugar;
    @NotNull
    private Double calorie;
    @NotNull
    private Boolean temperature;
    @NotNull
    @Column(name="MANAGER", columnDefinition = "varchar(10)", length = 10)
    private String manager;
    @NotNull
    @ColumnDefault("false")
    private Boolean deleted;




}

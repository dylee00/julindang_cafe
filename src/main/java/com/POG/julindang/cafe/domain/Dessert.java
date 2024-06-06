package com.POG.julindang.cafe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;


@Entity
@Table(name = "dessert")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dessert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DESSERT_ID")
    private Long id;
    @NotNull
    @Column(name="DESSERT_NAME", columnDefinition = "varchar(50)")
    private String dessertName;
    @NotNull
    @Column(name="CAFE_NAME", columnDefinition = "varchar(30)")
    private String cafeName;
    @NotNull
    private Double calorie;
    @NotNull
    private Boolean deleted;
    @NotNull
    private Double serve;
    @NotNull
    private Double sugar;
    @NotNull
    @Column(name="MANAGER", columnDefinition = "varchar(10)")
    private String manager;


    public void toggleDeleted(){
        this.deleted = !this.deleted;
    }
}

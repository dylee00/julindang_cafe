package com.POG.julindang.cafe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;





@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BREAD_ID")
    private Long id;
    @NotNull
    @Column(name="BREAD_NAME")
    private String breadName;
    @NotNull
    @Column(name = "CAFE_NAME")
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
    private String manager;
    public void toggleDeleted(){
        this.deleted = !this.deleted;
    }
}

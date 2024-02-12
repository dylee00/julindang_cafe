package com.POG.julindang.cafe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPPING_ID")
    private Long id;
    
    @NotNull
    private String toppingName;

    @NotNull
    private Double sugar;

    @NotNull
    private Double calorie;

    @NotNull
    @ColumnDefault("false")
    private Boolean deleted;

  //  private String manager; 담당자
}

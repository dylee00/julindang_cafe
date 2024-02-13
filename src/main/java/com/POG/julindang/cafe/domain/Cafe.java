package com.POG.julindang.cafe.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @NotNull
    @Column(name = "CAFE_NAME")
    private String cafeName;

    @NotNull
    @Column(name = "BEVERAGE_NAME")
    private String beverageName;

    @NotNull
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
    @ColumnDefault("false")
    private Boolean deleted;

//    @NotNull
   // private String manager;  // 담당자


    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}

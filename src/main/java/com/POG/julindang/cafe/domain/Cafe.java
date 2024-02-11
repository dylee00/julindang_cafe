package com.POG.julindang.cafe.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"CAFE_NAME", "BEVERAGE_NAME"}
                )
        }
)
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAFE_ID")
    Long id;


    @Column(name = "CAFE_NAME")
    String cafeName;

    @Column(name = "BEVERAGE_NAME")
    String beverageName;

    String size;

    Double serve;

    Double sugar;

    Double calorie;

    Boolean temperature;

    Boolean deleted;


}

package com.POG.julindang.cafe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@Entity
@Table(name = "dessert_bookmark")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE dessert_bookmark SET deleted = true WHERE id = ?")
public class DessertBookmark{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name="user_email", columnDefinition = "varchar(30)")
    private String userEmail;

    @NotNull
    @Column(name="member_id", columnDefinition = "bigint")
    private Long memberId;

    @NotNull
    @Column(name="cafe_name", columnDefinition = "varchar(30)")
    private String cafeName;

    @NotNull
    @Column(name="dessert_name", columnDefinition = "varchar(50)")
    private String dessertName;

    @CreationTimestamp
    @Column(name="created_at", columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @NotNull
    @Column(name="deleted", columnDefinition = "tinyint")
    private Boolean deleted;

    @Column(name = "dessert_id", columnDefinition = "bigint")
    private Long dessertId;

}

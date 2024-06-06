package com.POG.julindang.cafe.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@Entity
@Table(name = "cafe_bookmark")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE cafe_bookmark SET deleted = true WHERE id = ?")
public class CafeBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name="USER_EMAIL", columnDefinition = "varchar(30)")
    private String userEmail;

    @NotNull
    @Column(name="CAFE_NAME", columnDefinition = "varchar(30)")
    private String cafeName;

    @NotNull
    @Column(name="DELETED", columnDefinition = "tinyint")
    private Boolean deleted;

    @NotNull
    @CreationTimestamp
    @Column(name="CREATED_AT", columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;


    public void toggleDeleted(){
        this.deleted = !this.deleted;
    }
}

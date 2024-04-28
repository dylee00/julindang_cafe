package com.POG.julindang.cafe.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "bookmark")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark {

    /**
     * TODO : 이거 domain 수정 방안좀 생각하자
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKMARK_ID")
    private Long id;

    @NotNull
    @Column(name="USER_EMAIL", columnDefinition = "varchar(30)")
    private String userEmail;

    @NotNull
    @Column(name="CAFE_NAME", columnDefinition = "varchar(30)")
    private String cafeName;

    @NotNull
    @Column(name="PRODUCT_NAME", columnDefinition = "varchar(50)")
    private String productName;

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

package com.POG.julindang.cafe.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
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
    private String userEmail;

    @NotNull
    private String cafeName;

    @NotNull
    private String productName;

    @ColumnDefault(value = "false")
    private Boolean deleted;

    @CreationTimestamp
    private Date createdAt;

    @ManyToOne
    @JoinColumn
    private Member member;

    public void toggleDeleted(){
        this.deleted = !this.deleted;
    }
}

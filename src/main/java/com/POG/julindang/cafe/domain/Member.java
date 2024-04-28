package com.POG.julindang.cafe.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Member {
    @Id
    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "nickname", columnDefinition = "varchar(20)")
    private String nickname;

    @Column(name = "email", columnDefinition = "varchar(30)")
    private String email;

    @Column(name = "login_type", columnDefinition = "int")
    private Long loginType;

    @Column(name = "gender", columnDefinition = "tinyint")
    private Boolean gender;

    @Column(name = "age", columnDefinition = "int")
    private Long age;

    @Column(name = "height", columnDefinition = "int")
    private Long height;

    @Column(name = "weight", columnDefinition = "int")
    private Long weight;

    @Column(name = "phone_number", columnDefinition = "int")
    private Long phoneNumber;

    @Column(name = "marketing", columnDefinition = "tinyint")
    private Boolean marketing;

    @Column(name = "access_token", columnDefinition = "text")
    private String accessToken;

    @Column(name = "access_token_expired_at", columnDefinition = "date")
    private LocalDate accessTokenExpiredAt;

    @Column(name = "refresh_token", columnDefinition = "text")
    private String refreshToken;

    @Column(name = "refresh_token_expired_at", columnDefinition = "date")
    private LocalDate refreshTokenExpiredAt;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @Column(name = "deleted", columnDefinition = "tinyint")
    private Boolean deleted;
}

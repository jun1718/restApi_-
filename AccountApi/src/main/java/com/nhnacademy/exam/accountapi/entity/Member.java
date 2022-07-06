package com.nhnacademy.exam.accountapi.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    private String id;
    private String pw;
    private String email;

    @Column(name = "member_status")
    private String memberStatus;
    private String authority;

    public Member(String id, String pw, String email, String memberStatus, String authority) {
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.authority = authority;
        this.memberStatus = memberStatus;
    }
}


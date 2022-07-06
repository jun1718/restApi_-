package com.nhnacademy.exam.accountapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.exam.accountapi.dto.MemberDto;
import com.nhnacademy.exam.accountapi.dto.MemberDtoCreateComposition;
import com.nhnacademy.exam.accountapi.entity.Member;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TestEntityManager em;
    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("id", "pw", "email", "가입", "ROLE_USER");
    }

    @Test
    void findMemberDtoByMemberNo() {
        em.persist(member);
        MemberDto memberDto = memberRepository.findMemberDtoByEmail("email");

        assertThat(memberDto.getId())
            .isEqualTo(member.getId());
        assertThat(memberDto.getMemberNo())
            .isEqualTo(member.getMemberNo());
    }

    @Test
    void existsByMemberNo() {
        MemberDto memberDto = memberRepository.findMemberDtoByMemberNo(-1L);
        assertThat(memberDto)
            .isNull();

        boolean flag = memberRepository.existsByMemberNo(-1L);
        assertThat(flag)
            .isFalse();
    }

    @Test
    void existsById() {
        boolean flag = memberRepository.existsById("id");
        assertThat(flag)
            .isFalse();

        em.persist(member);
        flag = memberRepository.existsById("id");
        assertThat(flag)
            .isTrue();
    }

    @Test
    void existsByEmail() {
        boolean flag = memberRepository.existsByEmail("email");
        assertThat(flag)
            .isFalse();

        em.persist(member);
        flag = memberRepository.existsByEmail("email");
        assertThat(flag)
            .isTrue();
    }

    @Test
    void findMemberDtoById(){
        MemberDto memberDto = memberRepository.findMemberDtoById("id");
        assertThat(memberDto)
            .isNull();

        em.persist(member);
        memberDto = memberRepository.findMemberDtoById("id");
        assertThat(memberDto)
            .isNotNull();
    }

    @Test
    void findMemberDtoByEmail() {
        MemberDto memberDto = memberRepository.findMemberDtoById("id");
        assertThat(memberDto)
            .isNull();

        em.persist(member);
        memberDto = memberRepository.findMemberDtoById("id");
        assertThat(memberDto)
            .isNotNull();
    }

    @Test
    void findAllCreateComposition() {
        List<MemberDtoCreateComposition> allCreateComposition =
            memberRepository.findAllCreateComposition();

        int index = allCreateComposition.size();
        assertThatThrownBy(() -> allCreateComposition.get(index))
            .isInstanceOf(IndexOutOfBoundsException.class);

        em.persist(member);
        List<MemberDtoCreateComposition> allCreateComposition2 =
            memberRepository.findAllCreateComposition();
        assertThatCode(() -> allCreateComposition2.get(index)).doesNotThrowAnyException();
    }
}
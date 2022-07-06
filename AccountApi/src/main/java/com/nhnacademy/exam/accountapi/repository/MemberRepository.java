package com.nhnacademy.exam.accountapi.repository;

import com.nhnacademy.exam.accountapi.dto.MemberDto;
import com.nhnacademy.exam.accountapi.dto.MemberDtoCreateComposition;
import com.nhnacademy.exam.accountapi.entity.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    MemberDto findMemberDtoByMemberNo(Long memberNo);

    boolean existsByMemberNo(Long memberNo);
    boolean existsById(String id);
    boolean existsByEmail(String email);

    MemberDto findMemberDtoById(String id);

    MemberDto findMemberDtoByEmail(String email);

    @Query(value = "select m.memberNo as memberNo, m.id as id from Member m order by m.id asc")
    List<MemberDtoCreateComposition> findAllCreateComposition();
}

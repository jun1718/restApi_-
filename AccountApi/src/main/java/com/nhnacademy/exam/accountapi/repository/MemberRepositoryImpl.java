//package com.nhnacademy.exam.accountapi.repository;
//
//import com.nhnacademy.exam.accountapi.dto.MemberDto;
//import com.nhnacademy.exam.accountapi.entity.Member;
//import com.nhnacademy.exam.accountapi.entity.QMember;
//import com.querydsl.jpa.JPQLQuery;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//public class MemberRepositoryImpl extends QuerydslRepositorySupport
//    implements MemberRepositoryCustom {
//    public MemberRepositoryImpl() {
//        super(Member.class);
//    }
//
//    @Override
//    public Page<MemberDto> getPage(Pageable pageable, Long memberNo) {
//        QMember qMember = QMember.member;
//
////        JPQLQuery query = from(qMember)
//        return from(qMember)
//            .innerJoin(Oject)
//            .where(qMember.eq(hi))
//            .distinct();
//    }
//}

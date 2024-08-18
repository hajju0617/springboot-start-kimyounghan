package org.example.kimyounghanspringbootstart.repository;

import org.example.kimyounghanspringbootstart.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원이 저장소에 저장
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);   // findById, findByName 으로 찾아올 수 있음.

    /*
     findById, findByName 으로 가져오는 데 이 반환 값이 null일 수도 있음
     값이 없으면 null로 반환됨
     요즘에는 null을 처리하는 방법에서 null을 그대로 반환하는 방법 대신
     Optional<T> 으로 감싸서 반환하는 것을 선호함.
    */

    List<Member> findAll();     // 지금까지 저장된 모든 회원 리스트를 반환

}

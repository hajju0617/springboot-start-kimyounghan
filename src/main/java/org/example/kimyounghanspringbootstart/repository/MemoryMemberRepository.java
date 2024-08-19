package org.example.kimyounghanspringbootstart.repository;

import org.example.kimyounghanspringbootstart.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
                     // key, value
    private static long sequence = 0L;
    // sequence는 0, 1, 2 이렇게 키값을 생성해주는 애

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
               // Optional.of 해서 nullable 하면 store.get(id)값이 null이어도 감쌀 수 있음.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    // name이 같은 경우에만 필터링
                .findAny();     // findAny : 하나라도 찾는 것
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store 에 있는 values가 멤버들이다.
    }

    public void clearStore() {
        store.clear();
    }
}

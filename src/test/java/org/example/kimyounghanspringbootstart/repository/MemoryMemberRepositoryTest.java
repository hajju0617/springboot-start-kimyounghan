package org.example.kimyounghanspringbootstart.repository;

import org.assertj.core.api.Assertions;
import org.example.kimyounghanspringbootstart.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestExecutionListeners;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Optional 에서 값을 꺼낼때는 get

//        Assertions.assertEquals(result, member); // 이렇게 하는 방법도 있고
        // org.junit.jupiter.api

        assertThat(member).isEqualTo(result);
        // org.assertj.core.api
        // alt + enter 로 상단 static org.assertj.core.api.Assertions.*; 해놓으면
        // assertThat 으로만 사용가능 ( 기존 Assertions.assertThat)

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2  = new Member();
        member2.setName("spring");
        repository.save(member2);
    }
}

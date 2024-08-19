package org.example.kimyounghanspringbootstart.repository;

import org.assertj.core.api.Assertions;
import org.example.kimyounghanspringbootstart.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestExecutionListeners;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    // 모든 테스트는 순서뢍 상관없이 메서드별로 다 따로 동작하게만 설계 해야 한다.
    // 테스트는 서로 순서와 관계없이 서로 의존관계 없이 설계가 되어야 한다.
    // => 하나의 테스트가 끝날 때마다 저장소나 공용 데이터들을 다시 깔끔하게 지워줘야 함.
    // => afterEach .clearStore

    // 테스트 틀을 먼저 만들고 구현 클래스를 작성 : 테스트 주도 개발(TDD)

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {   // 메서드가 실행이 끝날 때마다 어떤 동작을 하는 것 (-> 콜백 메서드라 생각)
        repository.clearStore();    // 테스트가 끝날 때마다 repository를 깔끔하게 지워줌.
                                    // 테스트가 실행이 되고 끝날 때마다 한 번씩 저장소를 다 지워줌.
    }                               // => 순서와 상관 없어짐.

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
        member1.setName("spring1");
        repository.save(member1);

        Member member2  = new Member();
        member2.setName("spring2");
        repository.save(member2);

//        Optional<Member> result = repository.findByName("spring1");
        Member result = repository.findByName("spring1").get(); // .get() 을 해주면 옵션을 한번 까서 꺼낼 수 있음.

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}

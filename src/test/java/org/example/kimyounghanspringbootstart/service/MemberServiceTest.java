package org.example.kimyounghanspringbootstart.service;

import org.assertj.core.api.Assertions;
import org.example.kimyounghanspringbootstart.domain.Member;
import org.example.kimyounghanspringbootstart.repository.MemberRepository;
import org.example.kimyounghanspringbootstart.repository.MemoryMemberRepository;
import org.example.kimyounghanspringbootstart.repository.MemoryMemberRepositoryTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {
    // 테스트는 정상 플로우도 중요한데 예외 플로우가 훨씬 중요.

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach // 동작하기 전에 넣어줌.
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }   // MemberService를 생성할 때 내가 MemberMemoryRepository를 내가 직접 넣어줘야 함.
        // Test 실행할 때마다 두 객체를 각각 생성함.   =>  Test는 독립적으로 실행이 돼야 하기 때문.

    @AfterEach
    public void afterEach() {                   // 메서드가 실행이 끝날 때마다 어떤 동작을 하는 것 (-> 콜백 메서드라 생각)
        memoryMemberRepository.clearStore();    // 테스트가 끝날 때마다 repository를 깔끔하게 지워줌.
    }                                           // 테스트가 실행이 되고 끝날 때마다 한 번씩 저장소를 다 지워줌.
                                                // => 순서와 상관 없어짐.

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
        // Assertions : assertj(import)
    }

//    @Test
//    public void duplicateMemberException() {
//        // given
//        Member member1 = new Member();
//        member1.setName("spring");
//
//        Member member2 = new Member();
//        member2.setName("spring");
//
//        // when
//        memberService.join(member1);
//        try { // 예외 체크 때문에 try-catch 넣는 게 애매하다.
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
//    }

    @Test
    public void duplicateMemberException() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        // memberService.join(member2) 로직 실행 했을 때 IllegalStateException 해당 예외가 발생해야 됨.

        // 그러면 메세지는 어떻게 검증 해야 하나
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
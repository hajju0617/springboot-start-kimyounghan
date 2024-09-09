package org.example.kimyounghanspringbootstart.service;

import org.example.kimyounghanspringbootstart.domain.Member;
import org.example.kimyounghanspringbootstart.repository.MemberRepository;
import org.example.kimyounghanspringbootstart.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {    // ctrl + shift + T : 테스트 코드 생성
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;    // MemberService 입장에서 내가 직접 new 하지 않음.
                                                        // 외부에서 MemoryMemberRepository를 외부에서 넣어줌.
                                                        // 이것을 의존성주입 (DI) 라고 한다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {     // ifPresent : 여기 만약 값이 있으면 (정확히는 얘가 null이 아니라 어떤 값이 있으면 해당 로직이 동작. [Optional이기 때문에 가능])
//                                    // m : member, 멤버의 값이 있으면 Exception
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });
        // Optional을 바로 반환하는 것은 별로 좋지 않음 또한 코드가 안 예쁨.
        // 아래 처럼 수정.
        validateDuplicateMember(member);        // 중복 회원 검증
                                                // ctrl + alt + m (or) ctrl + alt + shift + T Extract Method
        // 메서드로 만듦.

        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())   // findByName의 결과는 Optional 멤버
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

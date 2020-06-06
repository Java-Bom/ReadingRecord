package Chap8_Method.item55;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

class MemberRepositoryTest {

    @DisplayName("orElse 는 Optional의 값과 관계 없이 실행된다.")
    @Test
    void orElseExample() {
        //given
        MemberRepository memberRepository = new MemberRepository();
        Member member = new Member("박찬인");
        memberRepository.save(member);

        //when
        Member persistMember = memberRepository.findByName(member.getName())
                .orElse(memberRepository.save(member));

        //then
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(2);
    }

    @DisplayName("orElseGet 은 Optional의 값이 비어있어야지 실행된다.")
    @Test
    void orElseGetExample() {
        //given
        MemberRepository memberRepository = new MemberRepository();
        Member member = new Member("박찬인");
        memberRepository.save(member);

        //when
        Member persistMember = memberRepository.findByName(member.getName())
                .orElseGet(() -> memberRepository.save(member));

        //then
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(1);
    }
}
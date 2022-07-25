package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // 스프링 테스트에서는 테스트 한건마다 롤백함
class MemberServiceIntegrationTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Test
  void 회원가입() {
    // given
    Member member = new Member();
    member.setName("spring");

    // when
    Long saveId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  public void 중복_회원_예외() {
    // given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");
    // when

    memberService.join(member1);
    IllegalStateException illegalStateException = assertThrows(IllegalStateException.class,
        () -> memberService.join(member2));
    assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    // memberService.join(member1);
    // try {
    // memberService.join(member2);
    // fail();
    // } catch (IllegalStateException e) {
    // assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    // }

    // then
  }

  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}

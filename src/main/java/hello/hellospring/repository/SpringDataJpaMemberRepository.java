package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

  // 해당 이름에 의존하여 함수명을 정하면 된다.
  // findByNameAndId(String name, Long id)
  // 복잡한 동적쿼리는 Querydsl 라이브러리를 사용
  @Override
  Optional<Member> findByName(String name);
}

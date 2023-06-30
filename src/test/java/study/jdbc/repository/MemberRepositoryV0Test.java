package study.jdbc.repository;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import study.jdbc.domain.Member;

@Slf4j
class MemberRepositoryV0Test {

	MemberRepositoryV0 repository = new MemberRepositoryV0();

	@Test
	void save() throws SQLException {
		Member member = new Member("memberV0", 10000);
		repository.save(member);
	}

	@Test
	void crud() throws SQLException {
		Member member = new Member("memberV0", 10000);
		repository.save(member);

		Member findMember = repository.findById(member.getMemberId());
		log.info("findMember={}", findMember);

		assertThat(findMember).isEqualTo(member);
	}
}
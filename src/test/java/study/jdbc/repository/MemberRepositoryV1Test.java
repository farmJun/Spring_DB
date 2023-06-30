package study.jdbc.repository;

import static org.assertj.core.api.Assertions.*;
import static study.jdbc.connection.ConnectionConst.*;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;
import study.jdbc.connection.ConnectionConst;
import study.jdbc.domain.Member;

@Slf4j
class MemberRepositoryV1Test {

	MemberRepositoryV1 repository;

	@BeforeEach
	void beforeEach() {
		//DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		repository = new MemberRepositoryV1(dataSource);
	}

	@Test
	void save() throws SQLException {
		Member member = new Member("memberV0", 10000);
		repository.save(member);
	}

	@Test
	void findById() throws SQLException {
		Member member = new Member("memberV0", 10000);
		repository.save(member);

		Member findMember = repository.findById(member.getMemberId());
		log.info("findMember={}", findMember);

		assertThat(findMember).isEqualTo(member);
	}

	@Test
	void update() throws SQLException {
		Member member = new Member("memberV4", 10000);
		repository.save(member);

		Member findMember = repository.findById(member.getMemberId());
		log.info("findMember={}", findMember);

		assertThat(findMember).isEqualTo(member);

		repository.update(member.getMemberId(), 20000);
		Member updatedMember = repository.findById(member.getMemberId());
		assertThat(updatedMember.getMoney()).isEqualTo(20000);
	}

	@Test
	void delete() throws SQLException {
		Member member = new Member("memberV1", 10000);
		repository.save(member);

		repository.delete(member.getMemberId());
		assertThatThrownBy(() -> repository.findById(member.getMemberId()))
			.isInstanceOf(NoSuchElementException.class);
	}
}
package study.jdbc.connection;

import static study.jdbc.connection.ConnectionConst.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionTest {

	@Test
	void driverManager() throws SQLException {
		Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		Connection connection2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		log.info("connection={}, class={}", connection1, connection1.getClass());
		log.info("connection={}, class={}", connection2, connection2.getClass());
	}

	@Test
	void dataSourceDriverManager() throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
		useDataSource(dataSource);
	}

	private void useDataSource(DataSource dataSource) throws SQLException {
		Connection connection1 = dataSource.getConnection();
		Connection connection2 = dataSource.getConnection();
		log.info("connection={}, class={}", connection1, connection1.getClass());
		log.info("connection={}, class={}", connection2, connection2.getClass());
	}

}

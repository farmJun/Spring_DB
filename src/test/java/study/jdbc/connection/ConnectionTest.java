package study.jdbc.connection;

import static study.jdbc.connection.ConnectionConst.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

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

}

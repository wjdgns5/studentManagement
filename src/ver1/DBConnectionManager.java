package ver1;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnectionManager {
	
	private static HikariDataSource dataSource;
	
	private static final String URL = "jdbc:mysql://localhost:3306/studentdb?serverTimezone=Asia/Seoul";
	private static final String USER = "root";
	private static final String PASSWORD = "asd123";
	
	static {
		
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PASSWORD);
		
		config.setMaximumPoolSize(5); // 최대 연결 수 5개
		
		dataSource = new HikariDataSource(config);
		
	}
	
	public static Connection getConnection() throws SQLException{
		System.out.println("HIKAriCP 를 사용한 DATA Source 활용");
		return dataSource.getConnection();
	}
	
	public static void main(String[] args) {
		
		try {
			Connection conn = DBConnectionManager.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

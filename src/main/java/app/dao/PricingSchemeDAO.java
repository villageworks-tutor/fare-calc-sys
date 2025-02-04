package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PricingSchemeDAO extends BaseDAO {
	public PricingSchemeDAO() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> findAll() {
		Map<String, String> map = new HashMap<>();
		try (Connection con = DriverManager.getConnection(DB_URL, DB_UESR, DB_PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM pricing_scheme ORDER BY code");
			 ResultSet rs = pstmt.executeQuery();
			) {
			while (rs.next()) {
				map.put(rs.getString("code"), rs.getString("name"));
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
}

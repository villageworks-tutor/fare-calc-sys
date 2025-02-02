package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.bean.PricingScheme;

public class PricingSchemeDAO extends BaseDAO {
	public PricingSchemeDAO() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<PricingScheme> findAll() {
		List<PricingScheme> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(DB_URL, DB_UESR, DB_PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM priving_scheme");
			 ResultSet rs = pstmt.executeQuery();
			) {
			while (rs.next()) {
				PricingScheme bean = new PricingScheme(rs.getString("code"), rs.getString("name"));
				list.add(bean);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}
	
}

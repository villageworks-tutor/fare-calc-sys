package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.bean.Line;

public class LineDAO extends BaseDAO {

	public LineDAO() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Line> findAll() {
		List<Line> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(DB_URL, DB_UESR, DB_PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM line");
			 ResultSet rs = pstmt.executeQuery();
			) {
			while (rs.next()) {
				Line bean = new Line(rs.getString("code"), rs.getString("name"));
				list.add(bean);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}
	
}

package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.bean.Fare;


public class FareDAO extends BaseDAO {
	
	private static final String SQL_FIND_ALL = "SELECT * FROM fare ORDER BY distance";
	private static final String SQL_FIND_BY_SCHEME
		= "SELECT * FROM fare "
				+ "WHERE pricing_scheme = ? "
				+ "ORDER BY distance";
	private static final String SQL_FIND_BY_SCHEME_AND_DISTANCE
		= "SELECT * FROM fare "
				+ "WHERE pricing_scheme = ? "
				+ "AND (distance::double precision) >= ceil((?::double precision)) "
				+ "ORDER BY distance "
				+ "LIMIT 1;";
	
	/**
	 * コンストラクタ
	 */
	public FareDAO() {
		super();
	}
	
	/**
	 * 運賃体系と乗車距離を指定して対応する料金を取得する
	 * @param scheme   運賃体系
	 * @param distance 乗車距離
	 * @return 乗車距離以上の距離の範囲の最小の距離の運賃
	 */
	public Fare findBySchemeAndDistance(String scheme, double distance) {
		// データベース接続オブジェクトとSQL実行オブジェクトを取得
		try (Connection con = DriverManager.getConnection(DB_URL, DB_UESR, DB_PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(SQL_FIND_BY_SCHEME_AND_DISTANCE);
			) {
			// プレースホルダをパラメータで置換
			pstmt.setString(1, scheme);
			pstmt.setDouble(2, distance);
			// SQLの実行と結果セットの取得
			try (ResultSet rs = pstmt.executeQuery();) {
				Fare bean = convertToBean(rs);
				return bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 指定した運賃体系の運賃リストを取得する
	 * @param scheme 取得対象となる運賃体系
	 * @return 運賃リスト
	 */
	public List<Fare> findByScheme(String scheme) {
		// データベース接続オブジェクトとSQL実行オブジェクトを取得
		try (Connection con = DriverManager.getConnection(DB_URL, DB_UESR, DB_PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(SQL_FIND_BY_SCHEME);
			) {
			// プレースホルダをパラメータで置換
			pstmt.setString(1, scheme);
			// SQLの実行と結果セットの取得
			try (ResultSet rs = pstmt.executeQuery();) {
				// 結果セットから運賃リストに変換
				List<Fare> list = convertToList(rs);
				// 戻り値を返却
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 運賃マスタの全レコードを取得する
	 * @return 運賃リスト
	 */
	public List<Fare> findAll() {
		try (Connection con = DriverManager.getConnection(DB_URL, DB_UESR, DB_PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL);
			 ResultSet rs = pstmt.executeQuery();
			) {
			// 結果セットから運賃リストに変換
			List<Fare> list = convertToList(rs);
			// 戻り値を返却
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 結果セットのレコードをBeanリストに変換する
	 * @param rs 結果セット
	 * @return 運賃リスト
	 * @throws SQLException
	 */
	private List<Fare> convertToList(ResultSet rs) throws SQLException {
		// 戻り値の初期化
		List<Fare> list = new ArrayList<Fare>();
		// 結果セットのすべてのレコードを走査
		while (rs.next()) {
			Fare bean = new Fare();
			bean.setScheme(rs.getString("pricing_scheme"));
			bean.setDistance(rs.getInt("distance"));
			bean.setValue(rs.getInt("value"));
			// Beanを運賃リストに追加
			list.add(bean);
		}
		// 戻り値の返却
		return list;
	}
	
	/**
	 * 結果セットのレコードをBeanに変換する
	 * @param rs 結果セット
	 * @return 運賃
	 * @throws SQLException
	 */
	private Fare convertToBean(ResultSet rs) throws SQLException {
		// 戻り値の初期化
		Fare bean = null;
		// 結果セットのすべてのレコードを走査
		if (rs.next()) {
			bean = new Fare();
			bean.setScheme(rs.getString("pricing_scheme"));
			bean.setDistance(rs.getInt("distance"));
			bean.setValue(rs.getInt("value"));
		}
		// 戻り値の返却
		return bean;
	}

}

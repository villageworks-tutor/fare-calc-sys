package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.bean.Station;

public class StationDAO extends BaseDAO {
	
	private static final String SQL_FIND_ALL = "SELECT * FROM station ORDER BY id";
	private static final String SQL_FIND_BY_CODE
		= "SELECT * FROM station "
				+ "WHERE code = ? "
				+ "ORDER BY id";
	private static final String SQL_FIND_BY_NAME 
		= "SELECT * FROM station "
				+ "WHERE name = ?";
	private static final String SQL_FIND_BY_SCHEME_AND_NAME
		= "SELECT * FROM station "
				+ "WHERE pricing_scheme = ? "
				+ "AND name = ?";
	private static final String SQL_FIND_BY_CODE_AND_NAME
		= "SELECT * FROM station "
				+ "WHERE code = ? "
				+ "AND name = ?";
	
	/**
	 * コンストラクタ
	 */
	public StationDAO() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Object findByCodeAndName(String code, String name) {
		// データベース接続オブジェクトとSQL実行オブジェクトを取得
		try (Connection con = DriverManager.getConnection(DB_URL, DB_UESR, DB_PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(SQL_FIND_BY_CODE_AND_NAME);
			) {
			// プレースホルダをパラメータで置換
			pstmt.setString(1, code);
			pstmt.setString(2, name);
			// SQLの実行と結果セットの取得
			try (ResultSet rs = pstmt.executeQuery();) {
				// 戻り値を初期化
				Station bean = convertToBean(rs);
				// 戻り値の返却
				return bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 指定した運賃体系かつ駅名の駅を取得する
	 * @param scheme 運賃体系
	 * @param name   駅名
	 * @return 駅クラスのインスタンス
	 */
	public Station findBySchemeAndName(String scheme, String name) {
		try {
			Station bean = getStation(SQL_FIND_BY_SCHEME_AND_NAME, scheme, name);
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 指定した駅名の駅を取得する
	 * @param name 取得対象の駅名
	 * @return 駅クラスのインスタンス
	 */
	public Station findByName(String name) {
		try {
			Station bean = getStation(SQL_FIND_BY_NAME, name);
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 指定された路線コードの駅を取得する
	 * @param code 路線コード
	 * @return 駅リスト
	 */
	public List<Station> findByCode(String code) {
		// データベース接続オブジェクトとSQL実行オブジェクトを取得
		try (Connection con = DriverManager.getConnection(DB_URL, DB_UESR, DB_PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(SQL_FIND_BY_CODE);
			) {
			// プレースホルダをパラメータで置換
			pstmt.setString(1, code);
			// SQLの実行と結果セットの取得
			try (ResultSet rs = pstmt.executeQuery();) {
				// 戻り値を初期化
				List<Station> list = convertToList(rs);
				// 戻り値の返却
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 駅マスタの全レコードを取得する
	 * @return 駅リスト
	 */
	public List<Station> findAll() {
		// データベース接続オブジェクトとSQL実行オブジェクトを取得
		try (Connection con = DriverManager.getConnection(DB_URL, DB_UESR, DB_PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL);
			 ResultSet rs = pstmt.executeQuery();
			) {
			// 結果セットからBeanリストに変換
			List<Station> list = convertToList(rs);
			// 戻り値を返却
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 駅名検索を実行する
	 * @param sql  実行するSQL文
	 * @param name 検索対象駅名
	 * @return 対象となる駅名の駅が存在した場合は駅クラスのインスタンス、それ以外はnull
	 * @throws SQLException
	 */
	private Station getStation(String sql, String name) throws SQLException {
		return getStation(sql, null, name);
	}
	
	/**
	 * 運賃体系を指定して駅名検索を実行する
	 * @param sql    実行するSQL文
	 * @param scheme 運賃体系
	 * @param name   検索対象駅名
	 * @return 対象となる駅名の駅が存在した場合は駅クラスのインスタンス、それ以外はnull
	 * @throws SQLException
	 */
	private Station getStation(String sql, String scheme,  String name) throws SQLException {
		// データベース接続オブジェクトとSQL実行オブジェクトを取得
		try (Connection con = DriverManager.getConnection(DB_URL, DB_UESR, DB_PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(sql);
			) {
			// 第一引数によってプレースホルダの置換を分岐
			if (scheme == null) {
				pstmt.setString(1, name);
			} else {
				pstmt.setString(1, scheme);
				pstmt.setString(2, name);
			}
			// SQLの実行と結果セットの取得
			try (ResultSet rs = pstmt.executeQuery();) {
				// 結果セットからBeanのインスタンスに変換
				Station bean = convertToBean(rs);
				// 戻り値の返却
				return bean;
			}
		}
	}

	/**
	 * 結果セットのレコードをBeanリストに変換する
	 * @param rs 結果セット
	 * @return 駅リスト
	 * @throws SQLException
	 */
	private List<Station> convertToList(ResultSet rs) throws SQLException {
		// 戻り値の初期化
		List<Station> list = new ArrayList<Station>();
		// 結果セットのすべてのレコードを走査
		while (rs.next()) {
			// 結果セットのレコードをBeanに変換
			Station bean = mapToBean(rs);
			// Beanを駅リストに追加
			list.add(bean);
		}
		// 戻り値の返却
		return list;
	}

	/**
	 * 結果セットのレコードをBeanに変換する
	 * @param rs 結果セット
	 * @return 駅
	 * @throws SQLException
	 */
	private Station convertToBean(ResultSet rs) throws SQLException {
		// 戻り値の初期化
		Station bean = null;
		// 結果セットのすべてのレコードを走査
		if (rs.next()) {
			// 結果セットのレコードをBeanに変換
			bean = mapToBean(rs);
		}
		// 戻り値の返却
		return bean;
	}
	
	/**
	 * 結果セットのレコードからBeanに変換する
	 * @param rs 結果セット
	 * @return 変換されたBean
	 * @throws SQLException
	 */
	private Station mapToBean(ResultSet rs) throws SQLException {
		Station bean = new Station();
		bean.setId(rs.getInt("id"));
		bean.setCode(rs.getString("code"));
		bean.setPricingScheme(rs.getString("pricing_scheme"));
		bean.setName(rs.getString("name"));
		bean.setDistance(rs.getDouble("distance"));
		bean.setTotalDistance(rs.getDouble("total_distance"));
		return bean;
	}

}

package app.dao;

/**
 * すべてのDAOクラスが継承する基底クラス
 */
public class BaseDAO {
	
	/**
	 * クラス定数
	 */
	// データベース接続情報文字列定数
	protected static final String JDBC_DRIVER = "org.postgresql.Driver";
	protected static final String DB_URL = "jdbc:postgresql://localhost:5432/faredb";
	protected static final String DB_UESR = "faredbusr";
	protected static final String DB_PASSWORD = "himitu";
	
	public BaseDAO() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

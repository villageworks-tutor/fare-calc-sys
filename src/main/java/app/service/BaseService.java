package app.service;

/**
 * すべてのServiceクラスが継承する基底クラス
 */
public class BaseService {

	public BaseService() throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
	}
	
}

package app.bean;

public class Fare {
	
	/**
	 * フィールド
	 */
	private String scheme; // 運賃体系
	private int value;     // 運賃
	private int distance;  // 距離
	
	/**
	 * デフォルトコンストラクタ
	 */
	public Fare() {}

	/**
	 * コンストラクタ
	 * @param scheme   運賃体系
	 * @param value    路線コード
	 * @param distance 距離
	 */
	public Fare(String scheme, int distance) {
		this.scheme = scheme;
		this.distance = Integer.valueOf(distance);
	}
	
	/**
	 * コンストラクタ
	 * @param scheme   運賃体系
	 * @param value    路線コード
	 * @param distance 距離
	 */
	public Fare(String scheme, String distance) {
		this.scheme = scheme;
		this.distance = Integer.valueOf(distance);
	}

	/**
	 * コンストラクタ
	 * @param scheme   運賃体系
	 * @param value    路線コード
	 * @param distance 距離
	 */
	public Fare(String scheme, String distance, int value) {
		this(scheme, distance);
		this.value = Integer.valueOf(value);
	}
	
	/**
	 * コンストラクタ
	 * @param scheme   運賃体系
	 * @param value    路線コード
	 * @param distance 距離
	 */
	public Fare(String scheme, int distance, int value) {
		this(scheme, distance);
		this.value = value;
	}
	
	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = Integer.valueOf(distance);
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Fare [scheme=" + scheme + ", value=" + value + ", distance=" + distance + "]";
	}
	
}



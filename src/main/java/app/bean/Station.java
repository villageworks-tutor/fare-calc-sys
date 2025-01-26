package app.bean;

public class Station {
	
	/**
	 * フィールド
	 */
	private int id;               // 駅ID
	private String code;          // 路線コード
	private String pricingScheme; // 運賃体系コード
	private String name;          // 駅名
	private double distance;      // 駅間距離
	private double totalDistance; // 累積距離
	
	/**
	 * デフォルトコンストラクタ
	 */
	public Station() {}

	/**
	 * コンストラクタ
	 * @param code          路線コード
	 * @param pricingScheme　運賃体系コード
	 * @param name          駅名
	 */
	public Station(String code, String pricingScheme, String name) {
		this.code = code;
		this.pricingScheme = pricingScheme;
		this.name = name;
	}
	
	/**
	 * コンストラクタ
	 * @param id            駅ID
	 * @param code          路線コード
	 * @param pricingScheme　運賃体系コード
	 * @param name          駅名
	 */
	public Station(int id, String code, String pricingScheme, String name) {
		this(code, pricingScheme, name);
		this.id = id;
	}
	
	/**
	 * コンストラクタ
	 * @param id            駅ID
	 * @param code          路線コード
	 * @param pricingScheme　運賃体系コード
	 * @param name          駅名
	 * @param distance      駅間距離
	 * @param totalDistance 累積距離
	 */
	public Station(int id, String code, String pricingScheme, String name, double distance, double totalDistance) {
		this(id, code, pricingScheme, name);
		this.distance = distance;
		this.totalDistance = totalDistance;
	}
	
	/**
	 * コンストラクタ
	 * @param id            駅ID
	 * @param code          路線コード
	 * @param pricingScheme　運賃体系コード
	 * @param name          駅名
	 * @param distance      駅間距離
	 * @param totalDistance 累積距離
	 */
	public Station(int id, String code, String pricingScheme, String name, long distance, long totalDistance) {
		this(id, code, pricingScheme, name);
		this.distance = Double.valueOf(distance);
		this.totalDistance = Double.valueOf(totalDistance);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPricingScheme() {
		return pricingScheme;
	}

	public void setPricingScheme(String pricingScheme) {
		this.pricingScheme = pricingScheme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public void setDistance(long distance) {
		this.distance = Double.valueOf(distance);
	}
	
	public void setDistance(String distance) {
		this.distance = Double.valueOf(distance);
	}

	public double getTotalDistance() {
		return totalDistance;
	}
	
	public void setTotalDistance(double totalDistance) {
		this.totalDistance = totalDistance;
	}

	public void setTotalDistance(long totalDistance) {
		this.totalDistance = Double.valueOf(totalDistance);
	}
	
	public void setTotalDistance(String totalDistance) {
		this.totalDistance = Double.valueOf(totalDistance);
	}

	@Override
	public String toString() {
		return "Station [id=" + id + ", code=" + code + ", pricingScheme=" + pricingScheme + ", name=" + name
				+ ", distance=" + distance + ", totalDistance=" + totalDistance + "]";
	}
	
}

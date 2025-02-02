package app.bean;

public class Ride {

	/**
	 * フィールド
	 */
	private int id;             // 乗車情報ID
	private String boarding;    // 乗車駅
	private String distination; // 降車駅
	private double distance;    // 乗車距離
	private int fare;           // 運賃
	
	/**
	 * デフォルトコンストラクタ
	 */
	public Ride() {}
	
	/**
	 * コンストラクタ
	 * @param boarding    乗車駅
	 * @param distination 降車駅
	 */
	public Ride(String boarding, String distination) {
		this.boarding = boarding;
		this.distination = distination;
	}

	/**
	 * コンストラクタ
	 * @param id
	 * @param boarding    乗車駅
	 * @param distination 降車駅
	 * @param distance    乗車距離
	 */
	public Ride(int id, String boarding, String distination, double distance) {
		this(boarding, distination);
		this.id = id;
		this.distance = distance;
	}
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param boarding
	 * @param distination
	 * @param distance
	 */
	public Ride(int id, String boarding, String distination, long distance) {
		this(boarding, distination);
		this.id = id;
		this.distance = Double.valueOf(distance);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBoarding() {
		return boarding;
	}

	public void setBoarding(String boarding) {
		this.boarding = boarding;
	}

	public String getDistination() {
		return distination;
	}

	public void setDistination(String distination) {
		this.distination = distination;
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

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}
	
	
}

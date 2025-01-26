package app.model;

import app.bean.Station;

public class Ticket {
	
	/**
	 * フィールド
	 */
	private Station boarding;    // 乗車駅
	private Station distination; // 降車駅
	private double  distance;    // 乗車距離
	private int fare;            // 運賃
	
	/**
	 * デフォルトコンストラクタ
	 */
	public Ticket() {}

	/**
	 * コンストラクタ
	 * @param boarding    乗車駅
	 * @param distination 降車駅
	 */
	public Ticket(Station boarding, Station distination) {
		this.boarding = boarding;
		this.distination = distination;
	}

	public Station getBoarding() {
		return boarding;
	}

	public void setBoarding(Station boarding) {
		this.boarding = boarding;
	}

	public Station getDistination() {
		return distination;
	}

	public void setDistination(Station distination) {
		this.distination = distination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "Ticket [boarding=" + boarding + ", distination=" + distination + ", distance=" + distance + ", fare="
				+ fare + "]";
	}
	
}

package app.model;

import app.bean.Station;

public class Ticket {
	
	/**
	 * フィールド
	 */
	private Station boarding;    // 乗車駅
	private Station destination; // 降車駅
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
	public Ticket(Station boarding, Station destination) {
		this.boarding = boarding;
		this.destination = destination;
	}

	public Station getBoarding() {
		return boarding;
	}

	public void setBoarding(Station boarding) {
		this.boarding = boarding;
	}

	public Station getDestination() {
		return destination;
	}

	public void setDestination(Station destination) {
		this.destination = destination;
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
		return "Ticket [boarding=" + boarding + ", destination=" + destination + ", distance=" + distance + ", fare="
				+ fare + "]";
	}
	
}

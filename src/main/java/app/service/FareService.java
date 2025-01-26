package app.service;

import java.math.BigDecimal;

import app.bean.Fare;
import app.bean.Station;
import app.dao.FareDAO;
import app.dao.StationDAO;
import app.model.Ticket;

public class FareService {

	/**
	 * 指定されたチケットの乗車区間の運賃を計算する
	 * @param target 計算対象のチケット
	 * @return チケットの乗車区間の運賃
	 */
	public int calcFare(Ticket target) {
		// 戻り値を初期化
		int fareValue = 0;
		
		// チケットから乗車駅と降車駅を取得
		StationDAO stationDao = new StationDAO();
		Station boardingStation = stationDao.findBySchemeAndName(target.getBoarding().getPricingScheme(), target.getBoarding().getName());
		Station distinationStation = stationDao.findBySchemeAndName(target.getDistination().getPricingScheme(), target.getDistination().getName());
		
		// 乗車距離の計算
		double[] distances = {boardingStation.getTotalDistance(), distinationStation.getTotalDistance()};
		double distance = calcDistance(distances);
		
		// 乗車距離をもとにして運賃を取得
		FareDAO fareDao = new FareDAO();
		Fare fare = fareDao.findBySchemeAndDistance(target.getBoarding().getPricingScheme(), distance);
		fareValue = fare.getValue();
		
		// チケットのフィールドを上書き
		target.setBoarding(boardingStation);
		target.setDistination(distinationStation);
		target.setDistance(distance);
		target.setFare(fareValue);
		
		// 戻り値を返却
		return target.getFare();
	}

	/**
	 * 距離を計算する
	 * @param distanceList 計算対象の距離の浮動小数配列
	 * @return 計算された距離
	 * @throws IllegalAccessException 
	 */
	private double calcDistance(double[] distanceList) {
		try {
			// 引数の配列のサイズ検査
			validateArraySize(distanceList, 2);
			
			BigDecimal distance1 = BigDecimal.valueOf(distanceList[0]);
			BigDecimal distance2 = BigDecimal.valueOf(distanceList[1]);
			BigDecimal distanceValue = distance1.subtract(distance2).abs();
			
			return distanceValue.doubleValue();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return -1.00;
		}
	}

	/**
	 * 引数に指定された配列が指定された要素数であるかどうかを検査する
	 * @param distanceList
	 * @param length 配列の規定要素数
	 * @throws IllegalAccessException：配列の要素数が規定の要素数と異なる場合にthrow
	 */
	private void validateArraySize(double[] distanceList, int length) throws IllegalAccessException {
		if (distanceList.length != length) {
			throw new IllegalAccessException("distanceList must contain at least two elements.");
		}
	}

}

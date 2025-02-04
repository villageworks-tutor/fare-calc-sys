package app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.bean.Fare;
import app.bean.Line;
import app.bean.Station;
import app.dao.FareDAO;
import app.dao.LineDAO;
import app.dao.PricingSchemeDAO;
import app.dao.StationDAO;
import app.model.Ticket;

public class FareService {

	/**
	 * 運賃算定サービスを実行する
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 遷移先URL
	 */	
	public List<String> execute(Ticket ticket) {
		// ロジック検査
		List<String> messageList = new ArrayList<String>();
		if (ticket.getBoarding() == null) {
			messageList.add("指定された乗車駅は指定された路線に見つかりませんでした。");
		}
		if (ticket.getDestination() == null) {
			messageList.add("指定された降車駅は指定された路線に見つかりませんでした。");
		}
		
		if (messageList.size() > 0) {
			// エラーリストに要素がある場合
			return messageList;
		}
		
		// 運賃の計算
		calcFare(ticket);
		
		return messageList;
	}

	

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
		Station distinationStation = stationDao.findBySchemeAndName(target.getDestination().getPricingScheme(), target.getDestination().getName());
		
		// 乗車距離の計算
		double[] distances = {boardingStation.getTotalDistance(), distinationStation.getTotalDistance()};
		double distance = calcDistance(distances);
		
		// 乗車距離をもとにして運賃を取得
		FareDAO fareDao = new FareDAO();
		Fare fare = fareDao.findBySchemeAndDistance(target.getBoarding().getPricingScheme(), distance);
		fareValue = fare.getValue();
		
		// チケットのフィールドを上書き
		target.setBoarding(boardingStation);
		target.setDestination(distinationStation);
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

	public List<Line> findAllLine() {
		LineDAO dao = new LineDAO();
		List<Line> list = dao.findAll();
		return list;
	}



	public Map<String, String> findAllScheme() {
		PricingSchemeDAO dao = new PricingSchemeDAO();
		Map<String, String> map = dao.findAll();
		return map;
	}

}

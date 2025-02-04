package app.service;

import app.bean.Station;
import app.dao.StationDAO;

public class StationService {

	/**
	 * 駅を取得する
	 * @param lineCode    取得対象の路線コード
	 * @param stationName 取得対象の駅名
	 * @return 路線コードと駅名が一致した駅が見つかった場合は駅クラスのインスタンス、それ以外はnull
	 */
	public Station getStation(String lineCode, String stationName) {
		StationDAO dao = new StationDAO();
		Station bean = dao.findByCodeAndName(lineCode, stationName);
		return bean;
	}

	
	
}

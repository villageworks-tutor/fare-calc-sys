package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.bean.Line;
import app.bean.Station;
import app.model.Ticket;
import app.service.FareService;
import app.service.StationService;

public class FareController {

	/**
	 * フィールド
	 */
	private List<Line> lines; // 路線コードリスト
	
	/**
	 * デフォルトコンストラクタ
	 */
	public FareController() {}

	/**
	 * コンストラクタ
	 * @param servletContext アプリケーションスコープ
	 */
	@SuppressWarnings("unchecked")
	public FareController(ServletContext servletContext) {
		this.lines = (List<Line>) servletContext.getAttribute("lines");
	}

	/**
	 * 運賃計算を実行する
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 遷移先URLの初期化
		String nextURL = "/pages/calc.jsp";
		
		// リクエストパラメータの取得
		String boardingLine = request.getParameter("boardingLine");
		String boarding = request.getParameter("boarding");
		String destinationLine = request.getParameter("destinationLine");
		String destination = request.getParameter("destination");
		
		// 入力値をスコープに登録
		request.setAttribute("boardingLine", boardingLine);
		request.setAttribute("boarding", boarding);
		request.setAttribute("destinationLine", destination);
		request.setAttribute("destination", destination);
		
		// 入力値妥当性検査
		List<String> messageList = new ArrayList<String>();
		if (boarding == null || boarding.isEmpty()) {
			messageList.add("乗車駅は必須です。");
		}
		if (destination == null || destination.isEmpty()) {
			messageList.add("降車駅は必須です。");
		}
		
		// エラーメッセージの有無によって処理を分岐
		if (messageList.size() > 0) {
			request.setAttribute("errors", messageList);
			return nextURL;
		}
		
		// 乗車駅と降車駅のインスタンスを生成:StationServiceを呼び出して駅名でStationのインスタンスを取得
		StationService stationService = new StationService();
		Station boardintStation = stationService.getStation(boardingLine, boarding);
		Station destinationStation = stationService.getStation(destinationLine, destination);
		
		// 乗車情報インスタンスを生成
		Ticket ticket = new Ticket(boardintStation, destinationStation);
		
		// FareServiceをインスタンス化
		FareService service = new FareService();
		// 乗車運賃計算を実行
		List<String> errors = service.execute(ticket);
		
		// エラーリストの要素によって処理を分岐
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			return nextURL;
		}
		
		// 乗車駅・降車駅の路線名を取得
		String boardingLineName = this.getLineNameByCode(ticket.getBoarding().getCode());
		String destinatonLineName = this.getLineNameByCode(ticket.getDestination().getCode());
		
		//　結果をスコープに登録
		request.setAttribute("ticket", ticket);
		request.setAttribute("boardingLine", errors);
		request.setAttribute("boardingLineName", boardingLineName);
		request.setAttribute("destinationLineName", destinatonLineName);
		
		// 戻り値の返却
		return nextURL;
	}

	/**
	 * 路線コードリストを準備する
	 * @return 路線コードリスト
	 */
	public List<Line> prepareLines() {
		// FareServiceをインスタンス化
		FareService service = new FareService();
		List<Line> list = service.findAllLine();
		return list;
	}

	/**
	 * 運賃体系コードマップを準備する
	 * @return 運賃体系コードマップ
	 */
	public Map<String, String> prepareSchemes() {
		// FareServiceをインスタンス化
		FareService service = new FareService();
		Map<String, String> map = service.findAllScheme();
		return map;
	}

	/**
	 * 路線コードから路線名を取得する
	 * @param code 取得対象となる路線名の路線コード
	 * @return 取得できた場合は路線コードに対応した路線名、それ以外はnull：ただし、設計仕様上この時点でnullが返されることはない
	 */
	private String getLineNameByCode(String code) {
		String lineName = null;
		for (Line line : this.lines) {
			if (line.getCode().equals(code)) {
				lineName = line.getName();
				break;
			}
		}
		return lineName;
	}

}

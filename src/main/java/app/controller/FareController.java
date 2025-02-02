package app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.bean.Station;
import app.model.Ticket;
import app.service.FareService;

public class FareController {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 遷移先URLの初期化
		String nextURL = "index.jsp";
		
		// リクエストパラメータの取得
		String boardingLine = request.getParameter("boardingLine");
		String boarding = request.getParameter("boarding");
		String destinationLine = request.getParameter("destinationLine");
		String destination = request.getParameter("destination");
		
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
		
		// 乗車駅と降車駅のインスタンスを生成
		Station boardintStation = new Station();
		boardintStation.setCode(boardingLine);
		boardintStation.setName(boarding);
		Station destinationStation = new Station();
		destinationStation.setCode(destinationLine);
		destinationStation.setName(destination);
		
		// 乗車情報インスタンスを生成
		Ticket ticket = new Ticket(boardintStation, destinationStation);
		
		// FareServiceをインスタンス化
		FareService service = new FareService();
		// 乗車運賃計算を実行
		List<String> errors = service.execute(ticket);
		
		// エラーリストの要素によって処理を分岐
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
		}
		
		return nextURL;
	}
	
	

}

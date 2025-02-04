package app;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.bean.Line;
import app.controller.FareController;

/**
 * Servlet implementation class FreCalcSysServlet
 */
@WebServlet({"/calc"})
public class FareCalcSysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		// アプリケーションスコープに路線コードリストが登録されている場合
		if (getServletContext().getAttribute("lines") != null) {
			// 処理せず終了
			return;
		}
		// 路線コードリストを取得
		FareController controller = new FareController();
		List<Line> list = controller.prepareLines();
		getServletContext().setAttribute("lines", list);
		// アプリケーションスコープに運賃体系マップが登録されている場合
		if (getServletContext().getAttribute("schemes") != null) {
			// 処理せず終了
			return;
		}
		// 運賃体系マップを取得
		Map<String, String> fareSchemes = controller.prepareSchemes();
		getServletContext().setAttribute("scheme", fareSchemes);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを設定
		request.setCharacterEncoding("utf-8");
		// actionパラメータを取得
		String action = request.getParameter("action");
		
		// 遷移先URLの初期化
		String nextURL = "/pages/calc.jsp";
		
		// actionパラメータによって処理を分岐
		if (action != null && !action.isEmpty()) {
			FareController controller = new FareController(getServletContext());
			nextURL = controller.execute(request, response);
		}
		
		// 画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextURL);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

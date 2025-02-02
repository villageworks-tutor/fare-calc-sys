package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを設定
		request.setCharacterEncoding("utf-8");
		// コンテキストパスとサーブレットパスを取得
		String servletPath = request.getServletPath();
		
		// 遷移先URLの初期化
		String nextURL = "index.jsp";
		
		// サーブレットパスによって処理を分岐
		if (servletPath != null && (!servletPath.isEmpty() && !servletPath.equals("/") )) {
			FareController controller = new FareController();
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

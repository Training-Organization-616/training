package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public ShowServlet() {
		super();
	}

	/**
	 * doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 送信する文字コードの指定
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html><meta charset='UTF-8'><head><title>掲示板</title></head><body>");
		out.println("<form action='/jmaster/BbsServlet2' method='post'>");
		out.println("名前：<br>");
		out.println("<input type='text' name='name'><br>");
		out.println("メッセージ：<br>");
		out.println("<textarea name='message' cols='40' rows='5'></textarea><br>");
		out.println("<button>書き込み</button></form>");
		out.println("<hr>");

		// セッション領域から投稿リストを取得
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<String> articles = (ArrayList<String>)session.getAttribute("articles");
		
		// 繰り返し処理を利用して投稿リストを表示する
		for (String article : articles) {
			out.println(article);
			out.println("<hr>");
		}

		out.println("</body></html>");
	}
	
	/**
	 * doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// GetもPostも同じ処理をさせる
		doGet(request, response);
	}

}

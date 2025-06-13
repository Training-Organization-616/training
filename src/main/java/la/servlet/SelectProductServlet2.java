package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SelectProductServlet2")
public class SelectProductServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// リクエストパラメータの読み込み
		String productNo = request.getParameter("product_no");
		int no = Integer.parseInt(productNo);
		String productName = null;
		// 選択された商品の判定
		switch (no) {
		case 100:
			productName = "パソコン";
			break;
		case 101:
			productName = "プリンタ";
			break;
		case 102:
			productName = "デジタルカメラ";
			break;
		default:
			productName = "???";
		}

		out.println("<html><head><title>SelectProduct</title></head><body>");
		out.println("選択された商品は「" + productName + "」です");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO2;

@WebServlet("/ItemServlet2")
public class ItemServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			// パラメータの解析
			String action = request.getParameter("action");
			// モデルのDAOを生成
			ItemDAO2 dao = new ItemDAO2();
			// パラメータなしの場合は全レコード表示
			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll();
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
				// addは追加
			} else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(name, price);
				// 追加後、全レコード表示
				List<ItemBean> list = dao.findAll();
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
			}
			// sortはソート
			else if (action.equals("sort")) {
				// ソートのパラメータ(key)をURLから取得
				String key = request.getParameter("key");

				// セッションスコープの取得
				HttpSession session = request.getSession();
				
				// セッションスコープからデータを取り出す
				String min = (String) session.getAttribute("minPrice");
				String max = (String) session.getAttribute("maxPrice");
				String itemName = (String) session.getAttribute("itemName");

				List<ItemBean> list;
				// セッションスコープにデータがない場合、minなどはnullになる
				if ((min != null && min.length() != 0)
						|| (max != null && max.length() != 0)
						|| (itemName != null && itemName.length() != 0)) {
					// 商品名、価格が入力されている場合
					list = dao.findByPriceAndName(min, max, itemName, key);
				} else {
					if (key.equals("price_asc")) {
						list = dao.sortPrice(true);
					} else {
						list = dao.sortPrice(false);
					}
				}
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
			}
			// searchは検索
			else if (action.equals("search")) {

				// パラメータ取得
				// 「""」（空文字）
				String min = request.getParameter("minPrice");
				String max = request.getParameter("maxPrice");
				String itemName = request.getParameter("itemName");

				// セッションスコープの取得
				HttpSession session = request.getSession();

				// セッションスコープに入力値を保存
				session.setAttribute("minPrice", min);
				session.setAttribute("maxPrice", max);
				session.setAttribute("itemName", itemName);
				
				// 名前と価格で検索
				// 検索ボタンをクリックした時は並び変えの指定はないためkeyの値は空文字とする
				List<ItemBean> list = dao.findByPriceAndName(min, max, itemName, "");

				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
			}
			// deleteは削除
			else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
				// 削除後、全レコード表示
				List<ItemBean> list = dao.findAll();
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

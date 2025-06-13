package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean;

public class ItemDAO2 {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:sample";
	private String user = "student";
	private String pass = "himitu";
	
	/**
	 * コンストラクタ
	 */
	public ItemDAO2() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました。");
		}
	}

	/**
	 * 全商品検索処理
	 */
	public List<ItemBean> findAll() throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM item";

		try (// データベースへの接続
			 Connection con = DriverManager.getConnection(url, user, pass);
			 // PreparedStatementオブジェクトの取得
			 PreparedStatement st = con.prepareStatement(sql);
			 // SQLの実行
			 ResultSet rs = st.executeQuery();) {
			// 結果の取得
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			// 商品一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/**
	 * ソート（値段）処理
	 */
	public List<ItemBean> sortPrice(boolean isAscending) throws DAOException {
		// SQL文の作成
		String sql;
		// ソートキーの指定
		if (isAscending) {
			sql = "SELECT * FROM item ORDER BY price";
		} else {
			sql = "SELECT * FROM item ORDER BY price desc";
		}
		try (// データベースへの接続
			 Connection con = DriverManager.getConnection(url, user, pass);
			 // PreparedStatementオブジェクトの取得
			 PreparedStatement st = con.prepareStatement(sql);
			 // SQLの実行
			 ResultSet rs = st.executeQuery();) {

			// 結果の取得
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			// 商品一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} 
	}
	
	/**
	 * 価格と商品名で検索、ソート指定で並び変え
	 * minPriceとmaxPriceが価格
	 * itemNameが商品名
	 * keyが並び変えの指定
	 */
	public List<ItemBean> findByPriceAndName(
			String minPrice, String maxPrice, String itemName, String key)
			throws DAOException {
		// SQL文の作成
		// WHERE句に「1 = 1」とすることで
		// それ以降の条件を「AND」(論理演算子)を使用して追加していくことができる
		// 条件が複数追加される時に用いられることが多い手法
		// 「1 = 1」自体には特に意味は無い(常に真偽値の真になる)
		String sql = "SELECT * FROM item WHERE 1 = 1 ";
		
		// ここからはWHERE句の条件
		if(minPrice.length() != 0 && maxPrice.length() != 0) {
			sql += "AND price >= ? AND price <= ? ";
		} else if(minPrice.length() != 0) {
			sql += "AND price >= ? ";
		} else if(maxPrice.length() != 0) {
			sql += "AND price <= ? ";
		}
		if (itemName.length() != 0) {
			// 商品名の指定がある場合は以下の条件をANDで繋げる
			sql += "AND name LIKE ? ";
		}

		// ここからはORDER BY句の並び変え指定
		if (key.equals("price_asc")) {
			sql += "ORDER BY price ";
		} else if (key.equals("price_desc")) {
			sql += "ORDER BY price DESC ";
		}
		
		try (// データベースへの接続
			 Connection con = DriverManager.getConnection(url, user, pass);
			 // PreparedStatementオブジェクトの取得
			 PreparedStatement st = con.prepareStatement(sql); ) {
			// 「""」（空文字）の場合、文字列長さが0になる
			// 「"3"」（1文字）の場合、文字列長さが1になる
			int counter = 1;
			// プレースホルダの設定
			if(minPrice.length() != 0) {
				// 文字列長さが0でなければ
				st.setInt(counter, Integer.parseInt(minPrice));
				counter++;
			}
			if(maxPrice.length() != 0) {
				st.setInt(counter, Integer.parseInt(maxPrice));
				counter++;
			}
			if(itemName.length() != 0) {
				st.setString(counter, "%" + itemName + "%");
			}
			// 実行するSQLの出力
			System.out.println("SQL > " + st.toString());
			try (// SQLの実行
				 ResultSet rs = st.executeQuery(); ) {
				// 結果の取得および表示
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					String name = rs.getString("name");
					int price = rs.getInt("price");
					// ItemBeanオブジェクトを生成してリストに追加する
					list.add(new ItemBean(code, name, price));
				}
				// 商品一覧をListとして返す
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	
	/**
	 * 追加処理
	 */
	public int addItem(String name, int price) throws DAOException {
		// SQL文の作成
		String sql = "INSERT INTO item(name, price) VALUES(?, ?)";
		
		try (// データベースへの接続
			 Connection con = DriverManager.getConnection(url, user, pass);
			 // PreparedStatementオブジェクトの取得
			 PreparedStatement st = con.prepareStatement(sql);) {
			// 商品名と値段の指定
			st.setString(1, name);
			st.setInt(2, price);
			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} 
	}

	/**
	 * 削除処理
	 */
	public int deleteByPrimaryKey(int key) throws DAOException {
		// SQL文の作成
		String sql = "DELETE FROM item WHERE code = ?";
		
		try (// データベースへの接続
			 Connection con = DriverManager.getConnection(url, user, pass);
			 // PreparedStatementオブジェクトの取得
			 PreparedStatement st = con.prepareStatement(sql);) {
			// 主キーの指定
			st.setInt(1, key);
			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} 
	}
}
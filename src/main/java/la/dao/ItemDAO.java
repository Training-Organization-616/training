package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean;

public class ItemDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:sample";
	private String user = "student";
	private String pass = "himitu";
	
	// ItemDAOコンストラクタの呼び出し元にExceptionの例外処理を任せる
	// （呼び出し元でtry-catchを記述する）
	public ItemDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// ドライバの登録に失敗した場合、catchブロックの処理が実行される
			// 例外発生原因をコンソールに出力
			e.printStackTrace();
			// Exceptionを呼び出し元にthrow（投げる）する
			throw new DAOException("ドライバの登録に失敗しました。");
		}
	}

	// 戻り値の型：List<ItemBean>（複数の商品情報）
	// （呼び出し元に複数の商品情報（List<ItemBean>）を返す）
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
			// 複数の商品を追加するListを作成（ArrayListオブジェクトを作成）
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				// 各カラム（項目）のデータを取得
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				// 取得したデータをItemBeanのコンストラクタの引数として設定
				// １つの商品情報がbeanオブジェクトとして生成される
				ItemBean bean = new ItemBean(code, name, price);
				// 生成したオブジェクトをlist（複数の商品情報オブジェクト）に追加する
				list.add(bean);
			}
			// 商品一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
}
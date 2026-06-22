package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*import bean.Book;
import bean.Order;
import bean.Sale;
import bean.User;
import dao.BookDAO;
import dao.OrderDAO;
*/
@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//エラー処理に使用する変数の定義
		String error = null;

		try {
			//セッションからuserを取得
			//	HttpSession session = request.getSession();
			//User user = (User) session.getAttribute("user");

			//セッション切れかの判定
			/*if (user == null) {
				error = "セッション切れの為、購入は出来ません。";
				request.setAttribute("cmd", "logout");
				return;
			}
			
			//セッションから"order_list"を取得
			ArrayList<Order> order_list =(ArrayList<Order>) session.getAttribute("order_list");
			
			//カートの中身があるかの判定
			if (order_list == null || order_list.isEmpty()) {
				error = "カートの中に何も無かったので購入は出来ません。";
				request.setAttribute("cmd", "menu");
				return;
			}
			
			//BookDAOとOrderDAOをインスタンス化
			BookDAO objBookDAO = new BookDAO();
			OrderDAO objOrderDao = new OrderDAO();
			
			//JSPに渡すためのリストの生成
			ArrayList<Sale> list = new ArrayList<Sale>();
			
			// 【購入品の数だけ繰り返すループ】
			// order_list から 1つずつ Orderオブジェクトを取り出して変数orderに代入する
			for (int i = 0; i < order_list.size(); i++) {
			
				Order order = order_list.get(i);
			
				// 取り出した order のISBNを使って、DBから書籍の詳細情報を取得する
				Book book = objBookDAO.selectByIsbn(order.getIsbn());
			
				//購入データを元にDBのorderinfoテーブルに新規登録処理を行うメソッドの呼び出し
				objOrderDao.insert(order);
			
				Sale bookInfo = new Sale(book, order.getQuantity());
			
				// 取得した書籍情報を、JSPに渡すためのリスト（list）に順番に追加する
				list.add(bookInfo);
			}
			
			//リクエストスコープに"book_list"という名前で格納
			request.setAttribute("book_list", list);
			
			// セッションからログイン中のユーザー名を取得（userオブジェクトからgetName()などで取得）
			String userId = user.getUserid();
			
			//メールを送信するメソッドの呼び出し
			util.SendMail.sendPurchaseEmail(userId, list);
			
			//order_list情報をクリア
			session.setAttribute("order_list", null);*/

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、購入は出来ません。";
			request.setAttribute("cmd", "logout");
		} finally {
			if (error != null) {
				//エラー発生時、error.jspにフォワード
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			} else {
				//エラーがない場合、buyConfirm.jspにフォワード
				request.getRequestDispatcher("/view/formfinish.jsp").forward(request, response);
			}
		}
	}
}

package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import bean.Book;
//import dao.BookDAO;

@WebServlet("/insert")
public class InsertFormServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//エラー処理に使用する変数の定義
		String error = null;
		String cmd = "";

		try {
			//文字のエンコーディング
			response.setCharacterEncoding("UTF-8");

			//画面からの入力情報の受け取り
			String name = request.getParameter("name");
			String title = request.getParameter("title");
			String pricestr = request.getParameter("price");

			//DAOオブジェクト宣言
			//BookDAO objDao = new BookDAO();

			//未入力の場合のエラー判定
			/*if (isbn.isEmpty()) {
				error = "ISBNが未入力の為、書籍登録処理は行えませんでした。";
				request.setAttribute("cmd", cmd);
				return;
			} else if (title.isEmpty()) {
				error = "タイトルが未入力の為、書籍登録処理は行えませんでした。";
				request.setAttribute("cmd", cmd);
				return;
			} else if (pricestr.isEmpty()) {
				error = "価格が未入力の為、書籍登録処理は行えませんでした。";
				request.setAttribute("cmd", cmd);
				return;
			}*/

			//データの重複チェック
			/*	Book book = objDao.selectByIsbn(isbn);
				if (book.getIsbn() != null) {
					//データが取得出来た場合重複
					error = "入力ISBNは既に登録済みの為、書籍登録処理は行えませんでした。";
					request.setAttribute("cmd", cmd);
					return;
				}
			
				//Stringで受け取ったpriceをint型に変換
				int price = Integer.parseInt(pricestr);
			
				//価格を負の値で入力した場合のエラー判定
				if (price < 0) {
					error = "価格の値が不正の為、書籍登録処理は行えませんでした。";
					request.setAttribute("cmd", cmd);
					return;
				}
			
				//isbn, title, priceを設定
				book.setIsbn(isbn);
				book.setTitle(title);
				book.setPrice(price);
			
				//insertメソッドを呼び出し
				objDao.insert(book);*/

		} catch (IllegalStateException e) {
			//DB接続エラー
			error = "DB接続エラーの為、書籍登録処理は行えませんでした。";
			request.setAttribute("cmd", "logout");

		} catch (NumberFormatException e) {
			//価格に文字列が入力された場合
			error = "価格の値が不正の為、書籍登録処理は行えませんでした。";
			request.setAttribute("cmd", cmd);
		} finally {
			if (error != null) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			} else {
				//ListServletへフォワード
				request.getRequestDispatcher("/view/formConfirm.jsp").forward(request, response);
			}
		}
	}
}

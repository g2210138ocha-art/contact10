package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import bean.Book;
//import dao.BookDAO;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エラー処理に使用する変数の定義
		String error = null;
		String cmd = "";

		try {
			//パラメータの取得
			String isbn = request.getParameter("isbn");
			cmd = request.getParameter("cmd");

			//オブジェクト宣言
			//BookDAO objDao = new BookDAO();

			//Book book = objDao.selectByIsbn(isbn);

			//エラー処理
			//if (book.getIsbn() == null) {
			//	if (cmd.equals("update")) {
			//	error = "更新対象の書籍が存在しない為、変更画面は表示できませんでした。";
			//	request.setAttribute("cmd", cmd);
			//	return;
			//	} else {
			//		error = "表示対象の書籍が存在しない為、詳細情報は表示できませんでした。";
			//	request.setAttribute("cmd", cmd);
			//	return;
			//	}
			//}

			//リクエストスコープに登録
			//request.setAttribute("book", book);

			//cmdの値の判定
			//if (cmd.equals("update")) {
			//	request.getRequestDispatcher("/view/update.jsp").forward(request, response);
			//	} else {
				request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
			//}

		} catch (IllegalStateException e) {
			if (cmd.equals("update")) {
				error = "DB接続エラーの為、変更画面は表示できませんでした。";
			} else {
				error = "DB接続エラーの為、書籍詳細は表示できませんでした。";
			}
			request.setAttribute("cmd", "logout");

		} finally {
			if (error != null) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}

}
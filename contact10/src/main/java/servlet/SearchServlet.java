package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import bean.Book;
//import dao.BookDAO;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//文字化け・変換エラーを防ぐ設定
			request.setCharacterEncoding("UTF-8");

			//パラメータの取得
			/*String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			//オブジェクト作成
			BookDAO objDao = new BookDAO();

			//selectAllメソッドを呼び出し、戻り値としてBookオブジェクトのbookListを取得する
			ArrayList<Book> bookList = objDao.search(isbn, title, price);

			//取得したListをリクエストスコープに"book_list"という名前で格納する
			request.setAttribute("book_list", bookList);*/

			//list.jspにフォワード
			request.getRequestDispatcher("/view/list.jsp").forward(request, response);

		} catch (IllegalStateException e) {
			//エラーがあった場合
			request.setAttribute("error", "DB接続エラーの為、一覧表示は行えませんでした。");
			request.setAttribute("cmd", "logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}

}

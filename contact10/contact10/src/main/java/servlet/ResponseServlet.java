package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import bean.Book;
//import dao.BookDAO;

@WebServlet("/response")
public class ResponseServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//エラー処理に使用する変数の定義
		String error = null;

		try {

			//オブジェクト作成
			//BookDAO objDao = new BookDAO();

			//selectAllメソッドを呼び出し、戻り値としてBookオブジェクトのlistを取得する
			//ArrayList<Book> list = objDao.selectAll();

			//取得したListをリクエストスコープに"book_list"という名前で格納する
			//request.setAttribute("book_list", list);

			//list.jspにフォワード
			request.getRequestDispatcher("/view/list.jsp").forward(request, response);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		}
	}

}

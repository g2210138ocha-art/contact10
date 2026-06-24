package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Form;
import dao.FormDAO;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";

		//FormDAOクラスのオブジェクトを生成します。
		FormDAO objDao = new FormDAO();

		//表示する問い合わせ情報を格納するFormオブジェクトを生成します。
		Form form = new Form();

		try {
			//画面から送信される情報を受け取るためのエンコードを設定します。
			request.setCharacterEncoding("UTF-8");

			//画面から送信されるNo情報を受け取ります。
			String strno = request.getParameter("no");

			int no = Integer.parseInt(strno);

			//FormDAOクラスに定義したselectByNo（）メソッドを利用して問い合わせ情報を取得します。
			form = objDao.selectByNo(no);

			//取得し問い合わせ情報を「form」という名前でリクエストスコープに登録します。  
			request.setAttribute("form", form);

		} catch (IllegalStateException e) {
			error = "DB接続エラーのため詳細表示を行えませんでした。";
		} finally {
			if (error.equals("")) {
				//「detail.jsp」へフォワードします。
				request.getRequestDispatcher("/view/detail.jsp").forward(request, response);

			} else {
				//リクエストスコープへの登録
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}
}
package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Form;
import dao.FormDAO;
import util.SendMail;

@WebServlet("/response")
public class ResponseServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//エラー処理に使用する変数の定義
		String error = "";

		//FormDAOクラスのオブジェクトを生成します。
		FormDAO objDao = new FormDAO();

		//メール用のオブジェクトを生成
		SendMail sendMail = new SendMail();

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

			//メールアドレスと、返信用の本文を取得
			String mail = form.getMail();
			String content = request.getParameter("reply");

			//メール送信用のメソッドを呼び出す
			sendMail.send("お問い合わせについて", content, mail);

			//対応を返信済みに更新する
			objDao.updateStatus(no);

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

package servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Form;
import bean.User;
import dao.FormDAO;
import util.SendMail;

@WebServlet("/response")
public class ResponseServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//エラー処理に使用する変数の定義
		String error = "";

		//パラメーターからnoを取得
		String strno = request.getParameter("no");
		int no = Integer.parseInt(strno);

		//セッションから"user"のUserオブジェクトを取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		//FormDAOクラスのオブジェクトを生成します。
		FormDAO objDao = new FormDAO();

		//メール用のオブジェクトを生成
		SendMail sendMail = new SendMail();

		//表示する問い合わせ情報を格納するFormオブジェクトを生成します。
		Form form = new Form();

		try {
			//画面から送信される情報を受け取るためのエンコードを設定します。
			request.setCharacterEncoding("UTF-8");

			//FormDAOクラスに定義したselectByNo（）メソッドを利用して問い合わせ情報を取得します。
			form = objDao.selectByNo(no);

			//メールアドレスと、返信用の本文を取得
			String mail = "system.project.team54@kanda-it-school-system.com";
			String content = request.getParameter("response_message");

			//メール送信用のメソッドを呼び出す
			sendMail.send("お問い合わせについて(神田英会話スクール)", content, mail);

			//返信の情報を格納するオブジェクトの生成
			Form form1 = new Form();

			//それぞれの値をform1にセットする
			form1.setNo(no);
			LocalDateTime now = LocalDateTime.now();//今の日時を取得
			String date = LocalDateTime.now().toString(); //日時をString型に変換
			form1.setResponsed_by(user.getUserid());
			form1.setUpdated_at(date);
			form1.setResponse(content);

			//対応を返信済みに更新する
			objDao.updateStatus(form1);

			//ListServletにフォワード
			request.getRequestDispatcher("/list").forward(request, response);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		}
	}

}

package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.User;
import dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		UserDAO userDaoObj = new UserDAO();

		//①userid, password入力パラメータを取得する
		String error = ""; //エラーメッセージ用変数
		String inputUser = request.getParameter("userid"); //入力パラメータの取得
		String inputPass = request.getParameter("password"); //入力パラメータの取得

		//②UserDAOをインスタンス化し、関連メソッドを呼び出す
		User user = userDaoObj.selectByUserid(inputUser, inputPass);

		//・User情報がある場合
		//→取得したUserオブジェクトをセッションスコープに"user"という名前で登録する。

		if (user.getUserid() != null) {

			// ③②で取得したuserをリクエストスコープに"user"という名前で格納する
			session.setAttribute("user", user);

			//ログイン処理
			if (!inputUser.equals(user.getUserid()) || !inputPass.equals(user.getPassword())) {

				//user用クッキーの生成
				//クッキーオブジェクト
				Cookie useridCookie = new Cookie("userid", inputUser);
				//	5日間
				useridCookie.setMaxAge(60 * 60 * 24 * 5);
				//送る
				response.addCookie(useridCookie);

				//パスワード用クッキーの生成
				Cookie passwordCookie = new Cookie("password", inputPass);
				passwordCookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(passwordCookie);

				// ④login.jspにフワード
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);

			} else {
				// ログイン失敗時の処理（ユーザーが見つからない、またはパスワード不一致）
				error = "ログイン失敗！ユーザーIDまたはパスワードが間違っています。";
				request.setAttribute("error", error);

				// ログイン画面（login.jsp）に戻す
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);

			}
		}
	}
}

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
		try {
			//文字のエンコーディング
			request.setCharacterEncoding("UTF-8");

			//オブジェクトの生成
			UserDAO userDaoObj = new UserDAO();

			//userid, password入力パラメータを取得する
			String inputUser = request.getParameter("userid"); //入力パラメータの取得
			String inputPass = request.getParameter("password"); //入力パラメータの取得

			//UserDAOをインスタンス化し、関連メソッドを呼び出す
			User user = userDaoObj.selectByUserid(inputUser, inputPass);

			//ログイン処理
			if (!inputUser.equals(user.getUserid()) || !inputPass.equals(user.getPassword())) {
				//useridかpasswordの入力情報が間違っていた場合
				request.setAttribute("error", "入力データが間違っています。");
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			} else {
				//・User情報がある場合
				//取得したUserオブジェクトをセッションスコープに"user"という名前で登録
				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				//user用クッキーの生成
				//クッキーオブジェクト
				Cookie useridCookie = new Cookie("userid", inputUser);
				//1日間
				useridCookie.setMaxAge(60 * 60 * 24 * 1);
				//送る
				response.addCookie(useridCookie);

				//パスワード用クッキーの生成
				Cookie passwordCookie = new Cookie("password", inputPass);
				passwordCookie.setMaxAge(60 * 60 * 24 * 1);
				response.addCookie(passwordCookie);

				//login.jspにフワード
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
			}
		} catch (Exception e) {
			//エラーがある場合
			request.setAttribute("error", "DB接続エラーの為、ログインは出来ません。");
			request.setAttribute("cmd", "logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}
}

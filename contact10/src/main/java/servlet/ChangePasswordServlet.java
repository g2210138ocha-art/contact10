/*
 * プログラム名：問い合わせシステム パスワード変更機能
 * プログラムの説明：問い合わせシステムにおけるパスワード変更機能に関する処理をおこなうサーブレットクラス
 * 作成者：髙城 樹里杏
 * 作成日：2026年6月22日
 * ページ移動の流れ：changePassword.jsp→ChangePasswordServlet.java→finishPassword.jsp
 */

package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.User;
import dao.UserDAO;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

	//changePassword.jspから受け取った情報でパスワード変更するメソッド
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = ""; //エラーメッセージ格納変数
		String errorCmd = "menu"; //遷移先用変数
		String oldPass = null; //古パスワードを格納する変数
		String newPass = null; //新パスワードを格納する変数
		String newConPass = null; //新確認パスワードを格納する変数

		//画面から送信される情報を受け取るためのエンコードを設定します。
		request.setCharacterEncoding("UTF-8");

		try {
			//セッションから"user"を取得する。
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			//セッション切れかどうか判定
			if (user == null) {
				error = "セッション切れの為、パスワード変更は行えません。";
				errorCmd = "logout";
				return;
			}

			//画面からの入力データ(パスワード変更情報)を取得する。
			oldPass = request.getParameter("oldPass");
			newPass = request.getParameter("newPass");
			newConPass = request.getParameter("newConPass");

			//入力値チェック
			if (oldPass.isEmpty()) {//旧パスワードが未入力
				error = "旧パスワードを入力して下さい！";
				return;
			} else if (newPass.isEmpty()) {//新パスワードが未入力
				error = "新パスワードを入力して下さい！";
				return;
			} else if (newConPass.isEmpty()) {//新パスワード(確認用)が未入力
				error = "新パスワード(確認用)を入力して下さい！";
				return;
			} else if (!oldPass.equals(user.getPassword())) {//旧パスワードが合っていない
				error = "旧パスワードが間違っています！";
				return;
			} else if (!newPass.equals(newConPass)) {//新と確認のパスワードとが一致しない
				error = "新パスワードと確認パスワードが合っていません！";
				return;
			}

			//UserDAOクラスをインスタンス化し、関連メソッド（パスワード変更処理）を呼び出す。
			UserDAO objUserDao = new UserDAO();
			objUserDao.updateForPassword(user.getUserid(), newPass);

			//新パスワードをセッションの"user"に設定し、セッションに再格納する。
			user.setPassword(newPass);
			session.setAttribute("user", user);

		} catch (IllegalStateException e) { //DB接続エラー発生時
			error = "DB接続エラーの為、パスワード変更は出来ません。";
			errorCmd = "logout";
		} catch (Exception e) {
			error = "予期せぬエラー";
		} finally {
			if (error.equals("")) { //正常処理の遷移
				request.getRequestDispatcher("/view/finishPassword.jsp").forward(request, response); //「finishPassword.jsp」へフォワード
			} else {
				request.setAttribute("error", error);
				if (errorCmd.equals("menu")) {//エラー時の遷移先分岐
					request.getRequestDispatcher("/view/changePassword.jsp").forward(request, response); //「changePassword.jsp」へフォワード
				} else {
					request.getRequestDispatcher("/view/error.jsp").forward(request, response); //「error.jsp」へフォワード
				}
			}
		}
	}
}
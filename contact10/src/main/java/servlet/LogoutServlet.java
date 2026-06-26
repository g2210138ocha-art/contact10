/*
 * プログラム名：問い合わせシステム ログアウト機能
 * プログラムの説明：問い合わせシステムにおけるログアウト機能に関する処理をおこなうサーブレットクラス
 * 作成者：吉田春希
 * 作成日：2026年6月22日
 * ページ移動の流れ：menu.jsp→LogoutServlet.java→login.jsp
 */

package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッション情報をクリアする
		HttpSession session = request.getSession();
		session.invalidate();

		//cmd情報をリクエストスコープに登録
		request.setAttribute("cmd", "logout");

		//login.jspにフォワード
		request.getRequestDispatcher("/view/login.jsp").forward(request, response);
	}

}

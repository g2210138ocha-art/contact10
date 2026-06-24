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

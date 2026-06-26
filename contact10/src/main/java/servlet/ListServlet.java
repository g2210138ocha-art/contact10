/*
 * プログラム名：問い合わせシステム 一覧表示機能
 * プログラムの説明：問い合わせシステムにおける一覧表示機能に関する処理をおこなうサーブレットクラス
 * 作成者：中村ほのか
 * 作成日：2026年6月23日
 * ページ移動の流れ：menu.jsp→ListServlet.java→list.jsp
 */

package servlet;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Form;
import dao.FormDAO;

@WebServlet("/list")
public class ListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//エラー処理に使用する変数の定義
		String error = null;

		try {
			//オブジェクト作成
			FormDAO objDao = new FormDAO();

			//selectAllメソッドを呼び出し、戻り値としてBookオブジェクトのlistを取得する
			ArrayList<Form> list = objDao.selectAll();

			//取得したListをリクエストスコープに"form_list"という名前で格納する
			request.setAttribute("form_list", list);

			//cmd情報を登録する
			request.setAttribute("cmd", "");

			//list.jspにフォワード
			request.getRequestDispatcher("/view/list.jsp").forward(request, response);

		} catch (IllegalStateException e) { //DB接続エラー発生時
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "logout");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		}
	}

}

/*
 * プログラム名：問い合わせシステム 詳細表示機能
 * プログラムの説明：問い合わせシステムにおける詳細表示機能に関する処理をおこなうサーブレットクラス
 * 作成者：吉田春希・中村ほのか
 * 作成日：2026年6月24日
 * ページ移動の流れ：list.jsp→DetailServlet.java→detail.jsp
 */

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
			//画面から送信される情報を受け取るためのエンコードを設定
			request.setCharacterEncoding("UTF-8");

			//画面から送信されるNo情報を受け取ります。
			String strno = request.getParameter("no");

			//noをint型に変換
			int no = Integer.parseInt(strno);

			//FormDAOクラスに定義したselectByNoメソッドを利用して問い合わせ情報を取得します。
			form = objDao.selectByNo(no);

			//エラー処理
			if (form.getNo() == 0) {
				error = "表示対象の問い合わせが存在しない為、詳細情報は表示できませんでした。";
				return;
			}

			//取得し問い合わせ情報をリクエストスコープに登録します。  
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
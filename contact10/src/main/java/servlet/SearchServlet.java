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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//検索処理に使うリストを生成
		ArrayList<Form> formList = new ArrayList<Form>();
		ArrayList<Form> formList2 = new ArrayList<Form>();
		ArrayList<Form> formList3 = new ArrayList<Form>();
		ArrayList<Form> formList4 = new ArrayList<Form>();

		String cmd = null;

		try {
			//文字化け・変換エラーを防ぐ設定
			request.setCharacterEncoding("UTF-8");

			//パラメータの取得
			cmd = request.getParameter("cmd");
			String no = request.getParameter("no");
			String keyword = request.getParameter("string");
			String strkind = request.getParameter("kind");
			String strstatus = request.getParameter("status");

			//性別、項目は入力された場合のみint型に変換
			int kind = 0;
			if (strkind != null && !strkind.isEmpty()) {
				kind = Integer.parseInt(strkind);
			}
			int status = 0;
			if (strstatus != null && !strstatus.isEmpty()) {
				status = Integer.parseInt(strstatus);
			}

			//オブジェクト作成
			FormDAO objDao = new FormDAO();

			if (cmd.equals("no")) {
				//noを検索するselectByNoメソッドを呼び出す
				formList = objDao.searchNo(no);
				request.setAttribute("form_list", formList);

			} else if (cmd.equals("name")) {
				//nameとdateを検索するsearchメソッドを呼び出し、戻り値としてformListを取得する
				formList2 = objDao.search(keyword);
				request.setAttribute("form_list", formList2);

			} else if (cmd.equals("kind")) {
				//項目を検索するsearchKindメソッドを呼び出し、戻り値としてformListを取得する
				formList3 = objDao.searchKind(kind);
				request.setAttribute("form_list", formList3);

			} else {
				//返信済み、未返信を検索するsearchメソッドを呼び出し、戻り値としてformListを取得する
				formList4 = objDao.search(status);
				request.setAttribute("form_list", formList4);
			}

			//list.jspにフォワード
			request.getRequestDispatcher("/view/list.jsp").forward(request, response);

		} catch (IllegalStateException e) {
			//エラーがあった場合
			request.setAttribute("error", "DB接続エラーの為、一覧表示は行えませんでした。");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}

}
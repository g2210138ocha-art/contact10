/*
 * プログラム名：問い合わせシステム 問い合わせ検索機能
 * プログラムの説明：問い合わせシステムにおける問い合わせ返信機能に関する処理をおこなうサーブレットクラス
 * 作成者：吉田春希・中村ほのか
 * 作成日：2026年6月24日
 * ページ移動の流れ：list.jsp→SearchServlet.java→list.jsp
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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//検索処理に使うリストを生成
		ArrayList<Form> formList = new ArrayList<Form>();
		ArrayList<Form> formList2 = new ArrayList<Form>();
		ArrayList<Form> formList3 = new ArrayList<Form>();
		ArrayList<Form> formList4 = new ArrayList<Form>();

		//cmd情報を格納する変数
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

			//一覧画面で表示する文言の判定(項目）
			switch (kind) {
			case 1:
				strkind = "料金・お支払いについて";
				break;
			case 2:
				strkind = "講座、コース、教材について";
				break;
			case 3:
				strkind = "学習の進め方について";
				break;
			case 4:
				strkind = "受講期限について";
				break;
			case 5:
				strkind = "受講終了後のサポートについて";
				break;
			default:
				strkind = "未選択";
			}

			//一覧画面で表示する文言の判定(返信状態）
			if (status == 1) {
				strstatus = "未返信";
			} else {
				strstatus = "返信済";
			}

			//cmdのデータによって呼び出すメソッドを変える
			if (cmd.equals("no")) {
				//noを検索するselectByNoメソッドを呼び出す
				formList = objDao.searchNo(no);
				request.setAttribute("form_list", formList);
				request.setAttribute("cmd", cmd);
				request.setAttribute("keyword", no);

			} else if (cmd.equals("name")) {
				//nameとdateを検索するsearchメソッドを呼び出し、戻り値としてformList2を取得する
				formList2 = objDao.search(keyword);
				request.setAttribute("form_list", formList2);
				request.setAttribute("cmd", cmd);
				request.setAttribute("keyword", keyword);

			} else if (cmd.equals("kind")) {
				//項目を検索するsearchKindメソッドを呼び出し、戻り値としてformList3を取得する
				formList3 = objDao.searchKind(kind);
				request.setAttribute("form_list", formList3);
				request.setAttribute("cmd", cmd);
				request.setAttribute("keyword", strkind);

			} else {
				//返信済み、未返信を検索するsearchメソッドを呼び出し、戻り値としてformList4を取得する
				formList4 = objDao.search(status);
				request.setAttribute("form_list", formList4);
				request.setAttribute("cmd", cmd);
				request.setAttribute("keyword", strstatus);
			}

			//list.jspにフォワード
			request.getRequestDispatcher("/view/list.jsp").forward(request, response);

		} catch (IllegalStateException e) {
			//エラーがあった場合
			request.setAttribute("error", "DB接続エラーの為、検索結果を表示できませんでした。");
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}

}
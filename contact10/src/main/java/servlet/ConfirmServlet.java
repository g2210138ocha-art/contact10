/*
 * プログラム名：問い合わせシステム 問い合わせ確認機能
 * プログラムの説明：問い合わせシステムにおける問い合わせ確認機能に関する処理をおこなうサーブレットクラス
 * 作成者：髙城 樹里杏
 * 作成日：2026年6月22日
 * ページ移動の流れ：form.jsp→ConfiemServlet.java→formConfirm.jsp
 */

package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Form;

@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {

	//お問い合わせ内容登録機能を実装するメソッド
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = ""; //エラーメッセージ用変数

		//FormクラスのDTOオブジェクトを生成
		Form form = new Form();

		//画面からの入力情報を受け取るためのエンコードを設定
		request.setCharacterEncoding("UTF-8");

		try {

			//画面からの入力情報を取得
			String name = request.getParameter("name"); //名前
			String age = request.getParameter("age"); //年齢
			String strsex = request.getParameter("sex"); //性別
			String address = request.getParameter("address"); //住所
			String mail = request.getParameter("mail"); //メアド
			String strkind = request.getParameter("kind"); //問い合わせ種別
			String report = request.getParameter("report"); //問い合わせ本文

			//性別、項目は入力された場合のみint型に変換
			int sex = 0;
			if (strsex != null && !strsex.isEmpty()) {
				sex = Integer.parseInt(strsex);
			}
			int kind = 0;
			if (strkind != null && !strkind.isEmpty()) {
				kind = Integer.parseInt(strkind);
			}

			//Formオブジェクトに値を格納し、formオブジェクトをリストに格納
			form.setName(name);
			form.setAge(age);
			form.setSex(sex);
			form.setAddress(address);
			form.setMail(mail);
			form.setKind(kind);
			form.setReport(report);

			// セッションオブジェクトを取得
			HttpSession session = request.getSession();

			// セッションに登録
			session.setAttribute("form", form);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、問い合わせ内容の確認は行えませんでした。";
		} catch (Exception e) {
			error = "予期せぬエラー";
			e.printStackTrace();
		} finally {
			if (error.equals("")) { //正常処理の遷移
				request.getRequestDispatcher("/view/formConfirm.jsp").forward(request, response); //「formConfirm.jsp」へフォワード
			} else { //エラー処理の遷移
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/form.jsp").forward(request, response); //「form.jsp」へフォワード
			}

		}

	}

}
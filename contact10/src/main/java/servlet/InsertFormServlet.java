/*
 * プログラム名：問い合わせシステム フォーム登録機能
 * プログラムの説明：問い合わせシステムにおけるログイン機能に関する処理をおこなうサーブレットクラス
 * 作成者：髙城 樹里杏
 * 作成日：2026年6月23日
 * ページ移動の流れ：formConfirm.jsp→InsertFormServlet.java→finishForm.jsp
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
import dao.FormDAO;
import util.SendMail;

@WebServlet("/insertForm")
public class InsertFormServlet extends HttpServlet {

	//お問い合わせ内容登録機能を実装するメソッド
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = ""; //エラーメッセージ用変数

		//DAOオブジェクト宣言
		FormDAO objDao = new FormDAO();

		//③画面からの入力情報を受け取るためのエンコードを設定
		request.setCharacterEncoding("UTF-8");

		//メール用のオブジェクトを生成
		SendMail sendMail = new SendMail();

		try {
			//セッションから入力情報を取得
			HttpSession session = request.getSession();
			Form form = (Form) session.getAttribute("form");

			//セッション切れかどうか判定
			if (form == null) {
				error = "セッション切れの為、問い合わせ送信は行えません。";
				return;
			}

			//セッションから取得した情報をform_listに格納
			form.setName(form.getName());
			form.setAge(form.getAge());
			form.setSex(form.getSex());
			form.setAddress(form.getAddress());
			form.setMail(form.getMail());
			form.setKind(form.getKind());
			form.setReport(form.getReport());

			//Formオブジェクトに格納された問い合わせデータをデータベースに登録
			objDao.insert(form);

			//性別を数字で識別し出力
			String text = form.getSexText();

			//問い合わせ項目を数字で識別し出力する
			String kindText = "未選択";
			if (!form.getKindText().equals("")) {
				kindText = form.getKindText() + "について";
			}

			//メールアドレスと、返信用の本文を取得
			String mail = form.getMail();
			String content = form.getName() + "様\n\n"
					+ "お問い合わせありがとうございます。\n" + "神田英会話スクールです。\n"
					+ "下記の内容で問い合わせを受け付けました。\n\n"
					+ "名前：\t" + form.getName() +
					"\n年齢：\t" + form.getAge() +
					"\n性別：\t" + text + "\n住所：\t" + form.getAddress() +
					"\n問い合わせ項目：\t" + kindText +
					"\n問い合わせ内容:\t" + form.getReport() +
					"\n\n神田英会話スクール\n"
					+ "長谷川";

			//メール送信用のメソッドを呼び出す
			sendMail.send("お問い合わせありがとうございます。(神田英会話スクール)", content, mail);

			//セッション情報を削除する
			session.invalidate();

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、問い合わせを送信できませんでした。";
		} catch (Exception e) {
			error = "予期せぬエラー";
		} finally {
			if (error.equals("")) { //正常処理の遷移
				request.getRequestDispatcher("/view/formFinish.jsp").forward(request, response);
			} else { //エラー処理の遷移
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/form.jsp").forward(request, response);
			}

		}

	}

}
package servlet;

import java.io.IOException;
import java.util.ArrayList;

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

		//FormクラスのDTOオブジェクトを生成
		Form form = new Form();

		//③画面からの入力情報を受け取るためのエンコードを設定
		request.setCharacterEncoding("UTF-8");

		//メール用のオブジェクトを生成
		SendMail sendMail = new SendMail();

		try {

			//④セッションから入力情報を取得
			HttpSession session = request.getSession();
			ArrayList<Form> form_list = (ArrayList<Form>) session.getAttribute("form_list");

			//セッションから取得した情報をformに格納
			form.setName(form_list.get(0).getName());
			form.setAge(form_list.get(0).getAge());
			form.setSex(form_list.get(0).getSex());
			form.setAddress(form_list.get(0).getAddress());
			form.setMail(form_list.get(0).getMail());
			form.setKind(form_list.get(0).getKind());
			form.setReport(form_list.get(0).getReport());
			form_list.add(form);

			//Formオブジェクトに格納された問い合わせデータをデータベースに登録
			objDao.insert(form);

			//性別を数字で識別し出力
			String strsex = null;
			if (form_list.get(0).getSex() == 1) {
				strsex = "男性";
			} else if (form_list.get(0).getSex() == 2) {
				strsex = "女性";
			} else {
				strsex = "その他";
			}

			//問い合わせ項目を数字で識別し出力する
			String text = "";
			switch (form_list.get(0).getKind()) {
			case 1:
				text = "料金・お支払いについて";
				break;
			case 2:
				text = "講座、コース、教材について";
				break;
			case 3:
				text = "学習の進め方について";
				break;
			case 4:
				text = "受講期限について";
				break;
			default:
				text = "受講終了後のサポートについて";
				break;
			}

			//メールアドレスと、返信用の本文を取得
			String mail = "system.project.team54@kanda-it-school-system.com";
			String content = form_list.get(0).getName() + "様\n\n"
					+ "お問い合わせありがとうございます。\n" + "神田英会話スクールです。\n"
					+ "下記の内容で問い合わせを受け付けました。\n\n"
					+ "名前：\t" + form_list.get(0).getName() +
					"\n年齢：\t" + form_list.get(0).getAge() +
					"\n性別：\t" + strsex + "\n住所：\t" + form_list.get(0).getAddress() +
					"\n問い合わせ項目：\t" + text +
					"\n問い合わせ内容:\t" + form_list.get(0).getReport() +
					"\n\n神田英会話スクール\n"
					+ "長谷川";

			//メール送信用のメソッドを呼び出す
			sendMail.send("お問い合わせありがとうございます。(神田英会話スクール)", content, mail);

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
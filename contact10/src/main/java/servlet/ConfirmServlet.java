package servlet;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.*;

@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {

	//お問い合わせ内容登録機能を実装するメソッド
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String error = ""; //エラーメッセージ用変数

		//FormクラスのDTOオブジェクトを生成
		Form form = new Form();
		
		ArrayList<Form> form_list = new ArrayList<Form>();

		//③画面からの入力情報を受け取るためのエンコードを設定
		request.setCharacterEncoding("UTF-8");

		try {

			//④画面からの入力情報を取得
			String name = request.getParameter("name"); //名前
			String age = request.getParameter("age"); //年齢
			String sex = request.getParameter("sex"); //性別
			String address = request.getParameter("address"); //住所
			String mail = request.getParameter("mail"); //メアド
			String kind = request.getParameter("kind"); //問い合わせ種別
			String report = request.getParameter("report"); //問い合わせ本文

			//④Formオブジェクトに格納
			form.setName(name);
			form.setAge(Integer.parseInt(age));
			form.setSex(Integer.parseInt(sex));
			form.setAddress(address);
			form.setMail(mail);
			form.setKind(Integer.parseInt(kind));
			form.setReport(report);
			form_list.add(form);
			
			// セッションオブジェクトを取得
			HttpSession session = request.getSession();

			// セッションに登録
			session.setAttribute("form_list", "form_list");


		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、書籍登録処理は行えませんでした。";
		} catch (Exception e) {
			error = "予期せぬエラー";
		} finally {
			if(error.equals("")) {  //正常処理の遷移
				request.getRequestDispatcher("/view/formConfirm.jsp").forward(request, response); //「ListServlet」へフォワード
			}else {  //エラー処理の遷移
			request.setAttribute("error", error);
			request.getRequestDispatcher("/view/form.jsp").forward(request, response); //「error.jsp」へフォワード
			}

		}

	}

}
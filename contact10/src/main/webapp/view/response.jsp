<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Form,dao.FormDAO"%>
<%
String strno = request.getParameter("no");
int no = Integer.parseInt(strno);
//オブジェクト宣言
FormDAO objDao = new FormDAO();
Form form = new Form();
try {
	form = objDao.selectByNo(no);
} catch (IllegalStateException e) {
	request.setAttribute("error", "DB接続エラーのため、返信画面を表示できませんでした。");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}
%>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>お問い合わせ返信画面</title>
</head>
<%@include file="/common/headerA.jsp"%>
<body>
	<div class="breadcrumb">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a> ｜ <a
			href="<%=request.getContextPath()%>/list">お問い合わせ一覧</a> | お問い合わせ詳細
	</div>

	<section>
		<div class="title">
			<h2>お問い合わせ返信</h2>
		</div>
		<%
		//項目表示
		String text = "未選択";
		if (!form.getKindText().equals("未選択")) {
			text = form.getKindText() + "について";
		}
		//性別表示
		String sex = form.getSexText();
		//年齢表示
		String age = "未入力";
		if (!form.getAge().equals(null) && !form.getAge().isEmpty()) {
			age = form.getAge() + "歳";
		}
		//住所表示
		String address = "未入力";
		if (!form.getAddress().equals(null) && !form.getAddress().isEmpty()) {
			address = form.getAddress();
		}
		%>

		<div class="forminfo">
			<div class="forminfo_area">
				<h4>【お問い合わせ者情報】</h4>
				<div class="forminfo_inner">
					<p>
						<span>No</span><%=form.getNo()%></p>
					<p>
						<span>名前</span><%=form.getName()%></p>
					<p>
						<span>年齢</span><%=age%>
					</p>
					<p>
						<span>性別</span><%=sex%></p>
					<p>
						<span>問い合わせ日時</span><%=form.getDate()%></p>
				</div>
				<div class="forminfo_inner">
					<p>
						<span>メールアドレス</span><%=form.getMail()%></p>
					<p>
						<span>問い合わせ種類</span><%=text%></p>
				</div>
				<div class="forminfo_inner">
					<p>
						<span>住所</span><%=address%></p>
				</div>
			</div>
		</div>
		<div class="contact-text">
			<h4>【お問い合わせ内容】</h4>
			<p><%=form.getReport()%></p>
		</div>

		<div class="form_text">
			<p>【返信内容入力欄】</p>
		</div>
		<form action="<%=request.getContextPath()%>/response">
			<div class="response_message">
				<textarea id="response_message" name="response_message" rows="10"
					cols="120%" maxlength="200"></textarea>
			</div>
			<div style="text-align: center">
				<input type="submit" value="送信する"><input type="hidden"
					name="no" value="<%=form.getNo()%>">
			</div>
		</form>
	</section>
</body>
<%@include file="/common/footerA.jsp"%>
</html>
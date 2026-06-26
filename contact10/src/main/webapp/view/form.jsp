<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Form"%>

<%
String name = "";
String age = "";
int sex = 0;
String address = "";
String mail = "";
int kind = 0;
String report = "";

Form form = (Form) session.getAttribute("form"); //セッション情報の取得
String error = (String) request.getAttribute("error"); //エラーメッセージの取得

if (form != null) {
	name = form.getName();
	age = form.getAge();
	sex = form.getSex();
	address = form.getAddress();
	mail = form.getMail();
	kind = form.getKind();
	report = form.getReport();
}
%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>お問い合わせフォーム</title>
</head>
<%@include file="/common/headerU.jsp"%>

<body>
	<div class="title">
		<h2>お問い合わせフォーム</h2>
	</div>
	<div class="formtel-area">
		<div class="formtel">
			<p>お電話でのお問い合わせはこちら</p>
			<a href="#">TEL:000-00-0000</a>
		</div>
	</div>
	<!-- 問い合わせ内容確認画面に行けなかったとき(何かしらのエラーが発生) -->
	<%
	if (error != null) {
	%>
	<p class="form_error_message">
		●●<%=error%>●●
	</p>
	<%
	}
	%>

	<form action="<%=request.getContextPath()%>/confirm" method="post"
		class="form_area">
		<table class="form_table">
			<tr>
				<th><span>必須</span>お名前：</th>
				<td><input type="text" name="name" size="20" required
					value=<%=name%>></td>
			</tr>
			<tr>
				<th>年齢：</th>
				<td><input type="number" min="0" max="120" step="1" name="age"
					value=<%=age%>></td>
			</tr>
			<tr>
				<th>性別：</th>
				<td>
					<div style="display: flex; gap: 20px;">
						<label><input type="radio" name="sex" value="1"
							<%=sex == 1 ? "checked" : ""%>>男性 </label> <label><input
							type="radio" name="sex" value="2" <%=sex == 2 ? "checked" : ""%>>女性
						</label> <label><input type="radio" name="sex" value="3"
							<%=sex == 3 ? "checked" : ""%>> その他</label>
					</div>
				</td>
			</tr>
			<tr>
				<th>住所：</th>
				<td><input type="text" name="address" size="30"
					value=<%=address%>></td>
			</tr>
			<tr>
				<th><span>必須</span>メールアドレス：</th>
				<td><input type="email" name="mail" size="30" required
					value=<%=mail%>></td>
			</tr>
			<tr>
				<th>お問い合わせ項目：</th>
				<td><select name="kind" style="width: 100%; padding: 4px;">
						<option value="" disabled selected>選択してください。</option>
						<option value="1" <%=kind == 1 ? "selected" : ""%>>料金・お支払いについて</option>
						<option value="2" <%=kind == 2 ? "selected" : ""%>>講座、コース、教材について</option>
						<option value="3" <%=kind == 3 ? "selected" : ""%>>学習の進め方について</option>
						<option value="4" <%=kind == 4 ? "selected" : ""%>>受講期限について</option>
						<option value="5" <%=kind == 5 ? "selected" : ""%>>受講終了後のサポートについて</option>
				</select></td>
			</tr>
		</table>
		<div class="form_textarea">
			<p>
				<span>必須</span>お問い合わせ内容
			</p>
			<textarea placeholder="本文を入力してください" name="report" rows="7" cols="80"
				required><%=report%></textarea>
		</div>

		<div class="submit">
			<input type="submit" value="確認">
		</div>
	</form>
</body>

<footer>
	<%@include file="/common/footerU.jsp"%>
</footer>
</html>
<%@page contentType="text/html; charset=UTF-8"%>
<%
String user = ""; //ユーザーを管理する変数
String pass = ""; //パスワードを管理する変数

Cookie[] userCookie = request.getCookies(); //クッキーの取得
String error = (String) request.getAttribute("error"); //エラーメッセージの取得
String cmd = (String) request.getAttribute("cmd");

//クッキーがあるか判定
if (userCookie != null) {
	for (int i = 0; i < userCookie.length; i++) {
		//クッキーからユーザー情報の取得
		if (userCookie[i].getName().equals("userid")) {
	user = userCookie[i].getValue();
		}
		//クッキーからパスワード情報の取得
		if (userCookie[i].getName().equals("password")) {
	pass = userCookie[i].getValue();
		}
	}
}
//エラーメッセージがない(null)場合の代入処理
if (error == null) {
	error = "";
}

if (cmd == null) {
	cmd = "";
}
%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css">
<title>お問い合わせシステムログイン</title>
</head>
<%@include file="/common/headerA.jsp"%>
<body>
	<main>
		<div class="title">
			<h2>ログイン画面</h2>
		</div>
		<%
		if (error != null) {
		%>
		<p style="text-align: center;"><%=error%></p>
		<%
}
%>
		<form action="<%=request.getContextPath()%>/login" method="post"
			class="login-form">
			<table class="login-table">
				<tr>
					<th>ログインID</th>
					<td><input type="text" name="userid" value=<%=user%>></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="password" value=<%=pass%>></td>
				</tr>
				<tr>
					<%if (cmd.equals("logout")) {%>
					<td colspan=2 style="text-align: center; color: red">ログアウトしました。</td>
					<%}%>
				</tr>
			</table>
			<div class="submit">
				<input type="submit" value="ログイン">
			</div>
		</form>
	</main>
<%@include file="/common/footerA.jsp"%>
</body>
</html>
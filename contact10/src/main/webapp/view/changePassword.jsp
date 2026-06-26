<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User,dao.UserDAO"%>

<%
String error = (String) request.getAttribute("error");
User user = (User) session.getAttribute("user");
%>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>パスワード変更画面</title>
</head>
	<%@include file="/common/headerA.jsp"%>
<body>
	<div class="title">
		<h2>パスワード変更</h2>
	</div>

	<form action="<%=request.getContextPath()%>/changePassword"
		method="post" class="login-form">
		<%
		if (error != null) {
		%>
		<p style="text-align: center;"><%=error%></p>
		<%
}
%>
		<table class="login-table">
			<tr>
				<th>ID</th>
				<td><%=user.getUserid()%></td>
			</tr>
			<tr>
				<th>旧パスワード</th>
				<td><input type="password" name="oldPass"></td>
			</tr>
			<tr>
				<th>新パスワード</th>
				<td><input type="password" name="newPass"></td>
			</tr>
			<tr>
				<th>新パスワード（確認）</th>
				<td><input type="password" name="newConPass"></td>
			</tr>
		</table>
		<div class="submit">
			<input type="submit" value="パスワード変更">
		</div>
	</form>
</body>
	<%@include file="/common/footerA.jsp"%>
</html>
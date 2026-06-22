<%@page contentType="text/html; charset=UTF-8"%>


<html>
	<head>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/common/style.css">
		<title>パスワード変更画面</title>
	</head>
	
	<header>
		<%@include file= "/common/header.jsp" %>
	</header>
	
	<body>
		<div class="title">
			<h2>パスワード変更</h2>
		</div>
		
		<form action="<%=request.getContextPath() %>/view/finishPassword.jsp" method="post" class="login-form">
			<table class="login-table">
				<tr>
					<th>ID</th>
					<td>kandaEnglish</td> <!--後で変数に変えて-->
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
	
	<footer>
		<%@include file= "/common/footer.jsp" %>
	</footer>
</html>
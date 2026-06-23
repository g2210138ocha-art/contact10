<%@page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css">
		<title>パスワード変更完了画面</title>
	</head>
	
	<header>
		<%@include file="/common/headerA.jsp"%>
	</header>
	
	<body>
		<div class="title">
			<h2>パスワード変更完了</h2>
		</div>
		
		<div class="error-message"> 
			<p>パスワードの変更が完了しました。</p>
		</div>

		<div class="error-message">
			<a href="login.html">［ログイン画面に戻る］</a>
		</div>

	</body>
	
	<footer>
		<%@include file= "/common/footerA.jsp" %>
	</footer>
	
</html>
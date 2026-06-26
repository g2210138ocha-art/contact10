<%@page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css">
		<title>パスワード変更完了画面</title>
	</head>
	

		<%@include file="/common/headerA.jsp"%>
	
	
	<body>
		<div class="title">
			<h2>パスワード変更完了</h2>
		</div>
		
		<div class="error-message"> 
			<p>パスワードの変更が完了しました。</p>
		</div>

		<div class="error-message">
			<a href="<%=request.getContextPath()%>/view/login.jsp">［ログイン画面に戻る］</a>
		</div>

	</body>
		<%@include file= "/common/footerA.jsp" %>
</html>
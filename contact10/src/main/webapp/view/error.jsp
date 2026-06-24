<%@page contentType="text/html; charset=UTF-8"%>
<%
String error = (String) request.getAttribute("error");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>エラー画面</title>
</head>

<header>
	<%@include file="/common/headerA.jsp"%>
</header>

<body>
	<div class="title">
		<h2>エラー画面</h2>
	</div>

	<div class="error-message">
		<p><%=error%>
		</p>
	</div>

	<div class="error-message">

		<!-- if文で画面遷移先をかえる? -->
		<a href="<%=request.getContextPath()%>/view/login.jsp"><input
			type="hidden" name="cmd" value="logout">［ログイン画面に戻る］</a>
	</div>
</body>
<footer>
	<%@include file="/common/footerA.jsp"%>
</footer>
</html>
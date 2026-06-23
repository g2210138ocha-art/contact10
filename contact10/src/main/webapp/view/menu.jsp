<%@page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css">
<title>ホーム</title>
</head>
<%@include file="/common/header.jsp"%>
<body>
	<main>
		<div class="title">
			<h2>ホーム画面</h2>
		</div>

		<div class="menu">
			<p>
				<a href="<%=request.getContextPath()%>/list">お問い合わせ一覧</a>
			</p>
			<p>
				<a href="<%=request.getContextPath()%>/view/changePassword.jsp">パスワード変更</a>
			</p>
			<p>
				<a href="<%=request.getContextPath()%>/logout">ログアウト</a>
			</p>
		</div>
	</main>
</body>
</html>
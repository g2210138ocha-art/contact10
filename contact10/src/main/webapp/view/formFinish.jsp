<%@page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>お問い合わせ完了</title>
</head>
<%@include file="/common/headerU.jsp"%>
<body>
	<main>
		<div class="title">
			<h2>お問い合わせが完了しました。</h2>
		</div>

		<div class="form-message">
			<p>
				お問い合わせいただいた内容につきましては<br> 順番に対応させていただきます。
			</p>
			<p>お問い合わせありがとうございました。</p>
			<p>神田英会話スクール</p>
		</div>
	</main>
</body>
<%@include file="/common/footerU.jsp"%>
</html>
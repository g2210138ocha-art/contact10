<%@page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>問い合わせ返信完了画面</title>
</head>


<%@include file="/common/headerA.jsp"%>


<body>
	<div class="title">
		<h2>問い合わせ返信完了</h2>
	</div>

	<div class="error-message">
		<p>問い合わせへの返信が完了しました。</p>
	</div>

	<div class="error-message">
		<a href="<%=request.getContextPath()%>/list">［問い合わせ一覧画面に戻る］</a>
	</div>

</body>
<%@include file="/common/footerA.jsp"%>
</html>
<%@page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<link rel="stylesheet" href="style.css">
<title>お問い合わせ返信画面</title>
</head>
<body>
	<%@include file="/common/header.jsp"%>

	<div class="breadcrumb">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a> ｜ <a
			href="<%=request.getContextPath()%>/view/list.jsp">お問い合わせ一覧</a> |
		お問い合わせ詳細
	</div>

	<section>
		<div class="title">
			<h2>お問い合わせ返信</h2>
		</div>

		<table class="detail-table">
			<tr class="green">
				<th>6.</th>
				<th>名前名前</th>
				<th>受講終了後のサポートについて</th>
				<th>2026.06.06 12:00</th>
			</tr>
		</table>
		<div class="form_text">
			<p>～問い合わせ本文～</p>
			<p>【返信内容入力欄】</p>
		</div>
			<form action="<%=request.getContextPath()%>/response">←ほんとはこれ-->
			<div class="response_message">
				<textarea name="response_message" rows="10" cols="150"></textarea>
			</div>
		</form>

		<div class="btn">
			<a href="<%=request.getContextPath()%>/view/list.jsp" class="btn-item">送信する</a>
			<!--			↑今はaタグ(デモ用)だけど、本当はform！(メールにデータを送るから)-->

		</div>
	</section>

</body>
</html>
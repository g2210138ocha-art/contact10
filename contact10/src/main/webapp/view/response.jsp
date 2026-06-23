<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Form,dao.FormDAO"%>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
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
		<%
		Form form = new Form();
		%>
		<table class="detail-table">
			<tr class="green">
				<th><%=form.getNo()%></th>
				<th><%=form.getName()%></th>
				<th><%=form.getKind()%></th>
				<th><%=form.getDate()%></th>
			</tr>
		</table>
		<div class="form_text">
			<p>～問い合わせ本文～</p>
			<p>【返信内容入力欄】</p>
		</div>
		<form action="<%=request.getContextPath()%>/response">
			<div class="response_message">
				<textarea name="response_message" rows="10" cols="120%"></textarea>
			</div>
			<div style="text-align: center">
				<input type="submit" value="送信する">
			</div>
		</form>
	</section>

</body>
</html>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Form,dao.FormDAO"%>
<%
Form form = (Form) request.getAttribute("form");
%>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>お問い合わせ詳細</title>
</head>

<body>
	<%@include file="/common/header.jsp"%>

	<div class="breadcrumb">
		<a href="/menu.jsp">メニュー</a> ｜ <a href="/list.jsp">お問い合わせ一覧</a> |
		お問い合わせ詳細
	</div>

	<section>
		<div class="title">
			<h2>お問い合わせ詳細</h2>
		</div>
		<%
		String text = "";
		switch (form.getKind()) {
		case 1:
			text = "料金・お支払いについて";
			break;
		case 2:
			text = "講座、コース、教材について";
			break;
		case 3:
			text = "学習の進め方について";
			break;
		case 4:
			text = "受講期限について";
			break;
		default:
			text = "受講終了後のサポートについて";
			break;
		}
		%>

		<table class="detail-table">
			<tr class="green">
				<th><%=form.getNo()%></th>
				<th><%=form.getName()%></th>
				<th><%=text%></th>
				<th><%=form.getDate()%></th>
			</tr>
		</table>

		<div class="contact-text">
			<tr>
				<td><%=form.getReport()%></td>
			</tr>
		</div>

		<div class="btn">
			<a href="<%=request.getContextPath()%>/list" class="btn-item">お問い合わせ一覧へ戻る</a>
			<a href="<%=request.getContextPath()%>/view/response.jsp" class="btn-item">返信する</a>
		</div>

	</section>

</body>
</html>
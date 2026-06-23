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
	<%@include file="/common/headerA.jsp"%>

	<div class="breadcrumb">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a> ｜ <a href="<%=request.getContextPath()%>/list">お問い合わせ一覧</a> |
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
			<%=form.getReport()%>
		</div>

		<!-- if文で返信済みかどうか振り分け -->
		<!-- if文ここから (返信済み(1じゃない)なら以下の処理)-->
		<%
		if (form.getStatus() == 2) {
		%>

		<div class="response-text">
			<p>【返信内容】</p>
			<p><!-- 返信内容の変数を入れる -->
			</p>
		</div>
		<!--返信済みのものは「お問い合わせ一覧へ戻る」のボタンのみ表示-->
		<div class="btn">
			<a href="<%=request.getContextPath()%>/list" class="btn-item">お問い合わせ一覧へ戻る</a>
		</div>
		<%
		} else {
		%>

		<div class="btn">
			<a href="<%=request.getContextPath()%>/list" class="btn-item">お問い合わせ一覧へ戻る</a>
			<a
				href="<%=request.getContextPath()%>/view/response.jsp?no=<%=form.getNo()%>"
				class="btn-item">返信する</a>
		</div>
		<%
		}
		%>

	</section>

</body>
</html>
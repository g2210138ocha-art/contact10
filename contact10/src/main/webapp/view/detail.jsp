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
		<a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a> ｜ <a
			href="<%=request.getContextPath()%>/list">お問い合わせ一覧</a> | お問い合わせ詳細
	</div>

	<section>
		<div class="title">
			<h2>お問い合わせ詳細</h2>
		</div>
		<%
		//問い合わせ種類
		String text = "未選択";
		if (!form.getKindText().equals("")) {
			text = form.getKindText() + "について";
		}
		//性別表示
		String sex = form.getSexText();
		//年齢表示
		String age = "未入力";
		if (!form.getAge().equals(null) && !form.getAge().isEmpty()) {
			age = form.getAge() + "歳";
		}
		//住所表示
		String address = "未入力";
		if (!form.getAddress().equals(null) && !form.getAddress().isEmpty()) {
			address = form.getAddress();
		}
		%>
		<div class="forminfo">
			<div class="forminfo_area">
				<h4>【お問い合わせ者情報】</h4>
				<div class="forminfo_inner">
					<p>
						<span>No</span><%=form.getNo()%></p>
					<p>
						<span>名前</span><%=form.getName()%></p>
					<p>
						<span>年齢</span><%=age%>
					</p>
					<p>
						<span>性別</span><%=sex%></p>
					<p>
						<span>問い合わせ日時</span><%=form.getDate()%></p>
				</div>
				<div class="forminfo_inner">
					<p>
						<span>メールアドレス</span><%=form.getMail()%></p>
					<p>
						<span>問い合わせ種類</span><%=text%></p>
				</div>
				<div class="forminfo_inner">
					<p>
						<span>住所</span><%=address%></p>
				</div>
			</div>
		</div>

		<div class="contact-text">
			<h4>【お問い合わせ内容】</h4>
			<p><%=form.getReport()%></p>
		</div>

		<!-- if文で返信済みかどうか振り分け -->
		<!-- if文ここから (返信済み(1じゃない)なら以下の処理)-->
		<%
		if (form.getStatus() == 2) {
		%>

		<div class="response-text">
			<h4>【返信内容】</h4>
			<p><%=form.getResponse()%></p>
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
<%@include file="/common/footerA.jsp"%>
</html>
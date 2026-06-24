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
		//問い合わせ種類
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
		case 5:
			text="受講終了後のサポートについて";
			break;
		default:
			text = "未選択";
			break;
		}
		//性別表示
		String sex = "その他";
		if(form.getSex() == 1){
			sex = "男性";
		}else{
			sex = "女性";
		}		%>

		<% 
		
		%>
		<div class="forminfo">
			<div class="forminfo_area">
				<h4>【お問い合わせ者情報】</h4>
				<div class="forminfo_inner">
					<p><span>No</span><%=form.getNo()%></p>
					<p><span>名前</span><%=form.getName()%></p>
					<p><span>年齢</span><%=form.getAge()%>歳</p>
					<p><span>性別</span><%=sex%></p>
					<p><span>問い合わせ日時</span><%=form.getDate()%></p>
				</div>
				<div class="forminfo_inner">
					<p><span>メールアドレス</span><%=form.getMail()%></p>
					<p><span>問い合わせ種類</span><%=text%></p>
				</div>
				<div class="forminfo_inner">
					<p><span>住所</span><%=form.getAddress()%></p>
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
</html>
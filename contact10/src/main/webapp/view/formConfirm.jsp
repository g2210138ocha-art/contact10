<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Form"%>

<!--リクエストスコープに登録したform情報を取得-->
<%
Form form = (Form) session.getAttribute("form");
%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>お問い合わせ内容確認</title>
</head>
<%@include file="/common/headerU.jsp"%>

<body>
	<div class="title">
		<h2>お問い合わせ内容確認</h2>
	</div>

	<form action="<%=request.getContextPath()%>/insertForm" method="post"
		class="form_area">
		<table class="form_table">

			<%
			//性別を数字で識別し出力
			String strsex = form.getSexText();

			//問い合わせ種類
			String text = "未選択";
			if (!form.getSexText().equals("未選択")) {
				text = form.getKindText() + "について";
			}
			%>
			<tr>
				<th>お名前：</th>
				<td><%=form.getName()%></td>
			</tr>
			<tr>
				<th>年齢：</th>
				<td><%=form.getAge()%></td>
			</tr>
			<tr>
				<th>性別：</th>
				<td><%=strsex%></td>
			</tr>
			<tr>
				<th>住所：</th>
				<td><%=form.getAddress()%></td>
			</tr>
			<tr>
				<th>メールアドレス：</th>
				<td><%=form.getMail()%></td>
			</tr>
			<tr>
				<th>お問い合わせ項目：</th>
				<td><%=text%></td>
			</tr>
		</table>
		<div class="form_textarea">
			<p>お問い合わせ内容</p>
			<p><%=form.getReport()%></p>
		</div>

		<div class="submit">
			<a href="<%=request.getContextPath()%>/view/form.jsp">戻る</a> <input
				type="submit" value="送信" style="margin-left: 130px">
		</div>
	</form>
</body>
<%@include file="/common/footerU.jsp"%>
</html>
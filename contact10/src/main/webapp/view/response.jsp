<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Form,dao.FormDAO"%>
<%
String strno = request.getParameter("no");
int no = Integer.parseInt(strno);
//オブジェクト宣言
FormDAO objDao = new FormDAO();
Form form = objDao.selectByNo(no);
%>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>お問い合わせ返信画面</title>
</head>
<%@include file="/common/headerA.jsp"%>
<body>
	<div class="breadcrumb">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a> ｜ <a
			href="<%=request.getContextPath()%>/list">お問い合わせ一覧</a> | お問い合わせ詳細
	</div>

	<section>
		<div class="title">
			<h2>お問い合わせ返信</h2>
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
		<div class="form_text">
			<p><%=form.getReport()%></p>
			<p>【返信内容入力欄】</p>
		</div>
		<form action="<%=request.getContextPath()%>/response">
			<div class="response_message">
				<textarea id="response_message" name="response_message" rows="10"
					cols="120%" maxlength="200"></textarea>
			</div>
			<div style="text-align: center">
				<input type="submit" value="送信する"><input type="hidden"
					name="no" value="<%=form.getNo()%>">
			</div>
		</form>
	</section>
</body>
<%@include file="/common/footerA.jsp"%>
</html>
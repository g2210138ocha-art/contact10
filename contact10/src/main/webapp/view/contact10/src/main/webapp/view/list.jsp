<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Form,dao.FormDAO"%>
<html>
<head>
<link rel="stylesheet" href="style.css">
<title>お問い合わせ一覧</title>
</head>
<body>
	<%@include file="/common/header.jsp"%>
	<div class="breadcrumb">
		<a href="menu.jsp">メニュー</a> ｜ お問い合わせ一覧
	</div>

	<section>
		<div class="title">
			<h2>お問い合わせ一覧</h2>
		</div>

		<div class="list">
			<div class="search">
				<form action="<%=request.getContextPath()%>/search"
					class="list-seach">
					<p>
						<input type="text" name="text">
					</p>
					<p>
						<input type="submit" name="search" value="検索">
					</p>
				</form>
			</div>
			<%
			Form form = new Form();
			%>
			<div>
				<form
					action="<%=request.getContextPath()%>/search?status=<%=form.getStatus()%>">
					<input type="submit" name="noreply" value="未返信一覧">
				</form>
			</div>
			<div>
				<form
					action="<%=request.getContextPath()%>/search?status=<%=form.getStatus()%>">
					<input type="submit" name="reply" value="返信済一覧">
				</form>
			</div>
		</div>

		<table class="list-table">
			<tr class="list-table-white">
				<th>No.</th>
				<th>名前</th>
				<th>種類</th>
				<th>お問い合わせ日時</th>
				<th>お問い合わせ内容</th>
				<th>未返信/返信済</th>
			</tr>

			<%
			ArrayList<Form> list = (ArrayList<Form>) request.getAttribute("form_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Form form1 = (Form) list.get(i);
					if (i % 2 == 1) {
			%>
			<!--ここでif文 (データある分表示)-->
			<tr class="list-table-green">
				<td><%=form1.getNo()%></td>
				<td><%=form1.getName()%></td>
				<td><%=form1.getKind()%></td>
				<td><%=form1.getDate()%></td>
				<td><%=form1.getReport()%></td>
				<td><%=form1.getStatus()%></td>
			</tr>
			<%
			} else {
			%>
			<tr class="list-table-white">
				<td><%=form1.getNo()%></td>
				<td><%=form1.getName()%></td>
				<td><%=form1.getKind()%></td>
				<td><%=form1.getDate()%></td>
				<td><%=form1.getReport()%></td>
				<td><%=form1.getStatus()%></td>
			</tr>
			<%
			}
			}
			}
			%>
		</table>
	</section>
</body>
</html>
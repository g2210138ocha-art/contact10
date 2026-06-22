<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Form,dao.FormDAO"%>
<html>
<head>
<link rel="stylesheet" href="style.css">
<title>お問い合わせ一覧</title>
</head>
<body>
	<!-- ヘッダー jspにするときはここ消してincludeでいれてね-->
	<header>
		<h1>
			神田英会話スクール<br> お問い合わせ管理システム
		</h1>
	</header>


	<div class="breadcrumb">
		<a href="menu.jsp">メニュー</a> ｜ お問い合わせ一覧
	</div>

	<section>
		<div class="title">
			<h2>お問い合わせ一覧</h2>
		</div>

		<div class="list">
			<div class="search">
				<form action="#" class="list-seach">
					<p>
						<input type="text" name="＃">
					</p>
					<p>
						<input type="submit" name="search" value="検索">
					</p>
				</form>
			</div>
			<div>
				<form action="#">
					<input type="submit" name="＃" value="未返信一覧">
				</form>
			</div>
			<div>
				<form action="#">
					<input type="submit" name="＃" value="返信済一覧">
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
				<th>返信済/未返信</th>
			</tr>
			<%
			ArrayList<Form> list = (ArrayList<Form>) request.getAttribute("form_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Form form = (Form) list.get(i);
					if (i % 2 == 1) {
			%>
			<!--ここでif文 (データある分表示)-->
			<tr class="list-table-green">
				<td><%=form.getNo()%></td>
				<td><%=form.getName()%></td>
				<td><%=form.getKind()%></td>
				<td><%=form.getDate()%></td>
				<td><%=form.getReport()%></td>
				<td><%=form.getStatus()%></td>
			</tr>
			<%
			} else {
			%>

			<!-- i++ して次の行は背景色変えたい-->

			<tr class="list-table-white">
				<td><%=form.getNo()%></td>
				<td><%=form.getName()%></td>
				<td><%=form.getKind()%></td>
				<td><%=form.getDate()%></td>
				<td><%=form.getReport()%></td>
				<td><%=form.getStatus()%></td>
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
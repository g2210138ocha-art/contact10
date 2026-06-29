<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Form,dao.FormDAO"%>

<%
String cmd = (String) request.getAttribute("cmd");
String keyword = (String) request.getAttribute("keyword");
%>

<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>お問い合わせ一覧</title>
</head>
<body>
	<%@include file="/common/headerA.jsp"%>
	<div class="breadcrumb">
		<a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a> ｜
		お問い合わせ一覧
	</div>

	<section>
		<div class="title">
			<%
			if (cmd != null && cmd.isEmpty()) {
			%>
			<h2>お問い合わせ一覧</h2>
			<%
			} else {
			%>
			<h2>お問い合わせ一覧</h2>
			<p>
				(検索ワード：<%=keyword%>)
			</p>
			<%
			}
			%>
		</div>

		<div class="list">
			<form action="<%=request.getContextPath()%>/search"
				class="list-seach">
				<p>
					<input type="number" min="0" step="1" name="no"
						placeholder="No.を入力">
				</p>
				<p>
					<input type="hidden" name="cmd" value="no"><input
						type="submit" name="search" value="検索">
				</p>
			</form>
			<form action="<%=request.getContextPath()%>/search"
				class="list-seach">
				<p>
					<input type="text" name="string" placeholder="キーワードを入力">
				</p>
				<p>
					<input type="hidden" name="cmd" value="name"><input
						type="submit" name="search" value="検索">
				</p>
			</form>
			<form action="<%=request.getContextPath()%>/search">
				<select name="kind" style="padding: 4px;">
					<option value="" disabled selected>問い合わせ種類で検索</option>
					<option value="1">①料金・お支払いについて</option>
					<option value="2">②講座、コース、教材について</option>
					<option value="3">③学習の進め方について</option>
					<option value="4">④受講期限について</option>
					<option value="5">⑤受講終了後のサポートについて</option>
				</select> <input type="hidden" name="cmd" value="kind"><input
					type="submit" value="検索">
			</form>
			<form action="<%=request.getContextPath()%>/search">
				<input type="hidden" name="status" value="1"> <input
					type="hidden" name="cmd" value="status"><input
					type="submit" value="未返信一覧">
			</form>
			<form action="<%=request.getContextPath()%>/search">
				<input type="hidden" name="status" value="2"><input
					type="hidden" name="cmd" value="status"> <input
					type="submit" value="返信済一覧">
			</form>
			<form action="<%=request.getContextPath()%>/list">
				<input type="submit" name="alllist" value="全件表示">
			</form>
		</div>
		<table class="list-table">
			<tr class="list-table-white-th">
				<th>No.</th>
				<th>名前</th>
				<th>種類</th>
				<th>お問い合わせ日時</th>
				<th>お問い合わせ内容</th>
				<th>未返信/済</th>
			</tr>

			<%
			ArrayList<Form> list = (ArrayList<Form>) request.getAttribute("form_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Form form = (Form) list.get(i);

					//項目情報の取得、判定
					String text = form.getKindText();
					String lable = "lable" + form.getKind();

					//返信済、未返信の取得、判定
					String color = "";
					String status = form.getStatusText();
					if (status.equals("未返信")) {
				color = "response_finished";
					}
					//偶数の時緑の行、奇数の時白の行
					if (i % 2 != 1) {
			%>
			<tr class="list-table-green" onclick="rowClicked(<%=form.getNo()%>)">
				<td><%=form.getNo()%></td>
				<td><%=form.getName()%></td>
				<td><p class="<%=lable%>">
						<%=text%>
					</p></td>
				<td><%=form.getDate()%></td>
				<td><%=form.getReport()%></td>
				<td><p class="<%=color%>">
						<%=status%>
					</p></td>
			</tr>
			<%
			} else {
			%>
			<tr class="list-table-white" onclick="rowClicked(<%=form.getNo()%>)">
				<td><%=form.getNo()%></td>
				<td><%=form.getName()%></td>
				<td><p class="<%=lable%>">
						<%=text%>
					</p></td>
				<td><%=form.getDate()%></td>
				<td><%=form.getReport()%></td>
				<td><p class="<%=color%>">
						<%=status%>
					</p></td>
			</tr>
			<%
			}
			%>
			<script>
				function rowClicked(no) {
				window.location.href ='<%=request.getContextPath()%>/detail?no='+no;}
			</script>
			<%
			}
			}
			%>
		</table>
	</section>
	<%@include file="/common/footerA.jsp"%>
</body>
</html>
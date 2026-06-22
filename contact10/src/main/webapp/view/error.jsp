<%@page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>エラー画面</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<%@include file="/common/header.jsp"%>
	<div id="wrap">
		<table style="width: 70%">
			<tr>
				<td class="error-msg">■■エラー■■</td>
			</tr>
			<tr>
				<%
				String error = (String) request.getAttribute("error");
				String cmd = (String) request.getAttribute("cmd");
				%>
				<td><%=error%></td>
			</tr>
			<tr>
				<td class="back-link">
					<%
					if (cmd.equals("menu")) {
					%> [<a href="<%=request.getContextPath()%>/view/menu.jsp">メニューへ戻る</a>]
					<%
					} else if (cmd.equals("logout")) {
					%> [<a href="<%=request.getContextPath()%>/logout">ログイン画面へ</a>] <%
					} else if (cmd.equals("listUser")) {
					%> [<a href="<%=request.getContextPath()%>/listUser">ユーザー一覧表示に戻る</a>]<%
					} else {
					%> [<a href="<%=request.getContextPath()%>/list">一覧表示に戻る</a>]<%
					}
					%>
				</td>
			</tr>
		</table>
	</div>
	<%@include file="/common/footer.jsp"%>
</body>
</html>
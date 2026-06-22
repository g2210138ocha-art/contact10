<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Form" %>

<!--リクエストスコープに登録したform情報を取得-->
<%
ArrayList<Form> form_list = (ArrayList<Form>)session.getAttribute("form_list");
%>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/common/style.css">
		<title>お問い合わせ内容確認</title>
	</head>

	<!-- ヘッダー jspにするときはここ消してincludeでいれてね-->
	<header>
		<h1>神田英会話スクール　お問い合わせ</h1>
	</header>

	<body>
		<div class="title">
			<h2>お問い合わせ内容確認</h2>
		</div>
		
		<form action="<%=request.getContextPath() %>/InsertForm" method="post" class="form_area">
			<table class="form_table">
			
				<%
				if(form_list != null || !form_list.equals("")){
					for(int i = 0; i < form_list.size(); i++){
				%>
				<tr>
					<th>お名前：</th>
					<td><%= form_list.get(i).getName() %></td>
				</tr>
				<tr>
					<th>年齢：</th>
					<td><%= form_list.get(i).getAge() %></td>
				</tr>
				<tr>
					<th>性別：</th>
					<td><%= form_list.get(i).getSex() %></td>
				</tr>
				<tr>
					<th>住所：</th>
					<td><%= form_list.get(i).getAddress() %></td>
				</tr>
				<tr>
					<th>メールアドレス：</th>
					<td><%= form_list.get(i).getMail() %></td>
				</tr>
				<tr>
					<th>お問い合わせ項目：</th>
					<td><%= form_list.get(i).getKind() %></td>
				</tr>
			</table>
			<div class="form_textarea">
				<p>お問い合わせ内容</p>
				<p><%= form_list.get(i).getReport() %></p>
			</div>
			
			<%	}
			}%>
			<div class="submit">
				<input type="submit" value="送信">
			</div>
		</form>
	</body>
	<!-- フッター jspにするときはここ消してincludeでいれてね-->
	<footer>
		<h4>&copy; 2026 神田英会話スクール</h4>
	</footer>
</html>
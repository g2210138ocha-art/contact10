<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Form"%>

<!--リクエストスコープに登録したform情報を取得-->
<%
ArrayList<Form> form_list = (ArrayList<Form>) session.getAttribute("form_list");
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
			if (form_list != null || !form_list.equals("")) {
				for (int i = 0; i < form_list.size(); i++) {
					//性別を数字で識別し出力
					String strsex = null;
					if (form_list.get(i).getSex() == 1) {
						strsex = "男性";
					} else if (form_list.get(0).getSex() == 2) {
						strsex = "女性";
					} else {
						strsex = "その他";
					}

					//問い合わせ項目を数字で識別し出力する
					String text = "";
					switch (form_list.get(i).getKind()) {
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
			<tr>
				<th>お名前：</th>
				<td><%=form_list.get(i).getName()%></td>
			</tr>
			<tr>
				<th>年齢：</th>
				<td><%=form_list.get(i).getAge()%></td>
			</tr>
			<tr>
				<th>性別：</th>
				<td><%=strsex%></td>
			</tr>
			<tr>
				<th>住所：</th>
				<td><%=form_list.get(i).getAddress()%></td>
			</tr>
			<tr>
				<th>メールアドレス：</th>
				<td><%=form_list.get(i).getMail()%></td>
			</tr>
			<tr>
				<th>お問い合わせ項目：</th>
				<td><%=text%></td>
			</tr>
		</table>
		<div class="form_textarea">
			<p>お問い合わせ内容</p>
			<p><%=form_list.get(i).getReport()%></p>
		</div>

		<%
		}
		}
		%>
		<div class="submit">
			<input type="submit" value="送信">
		</div>
	</form>
</body>
<%@include file="/common/footerU.jsp"%>
</html>
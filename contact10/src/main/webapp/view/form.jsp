<%@page contentType="text/html; charset=UTF-8"%>

<%
String error = (String)request.getAttribute("error");
%>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css">
		<title>お問い合わせフォーム</title>
	</head>

	<header>
		<%@include file= "/common/headerU.jsp" %>
	</header>

	<body>
		<div class="title">
			<h2>お問い合わせフォーム</h2>
		</div>
		<div class="formtel-area">
			<div class="formtel">
				<p>お電話でのお問い合わせはこちら</p>
				<a href="#">TEL:000-00-0000</a>
			</div>
		</div>
		<!-- 問い合わせ内容確認画面に行けなかったとき(何かしらのエラーが発生) -->
		<%if(error!=null){ %>
			<p class="form_error_message">●●<%=error %>●●</p>
		<%} %>
		
		<form action="<%=request.getContextPath() %>/confirm" method="post" class="form_area">
			<table class="form_table">
				<tr>
					<th><span>必須</span>お名前：</th>
					<td><input type="text" name="name" size="20" required></td>
				</tr>
				<tr>
					<th>年齢：</th>
					<td><input type="number" name="age"></td>
				</tr>
				<tr>
					<th>性別：</th>
					<td>
						<div style="display:flex; gap:20px;">
							<label><input type="radio" name="sex" value="1">男性 </label>
							<label><input type="radio" name="sex" value="2">女性 </label>
							<label><input type="radio" name="sex" value="3"> その他</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>住所：</th>
					<td><input type="text" name="address" size="30"></td>
				</tr>
				<tr>
					<th><span>必須</span>メールアドレス：</th>
					<td><input type="email" name="mail" size="30" required></td>
				</tr>
				<tr>
					<th>お問い合わせ項目：</th>
					<td>
						<select name="kind" style="width: 100%; padding: 4px;">
							<option value="" disabled selected>選択してください。</option>
							<option value="1">料金・お支払いについて</option>
							<option value="2">講座、コース、教材について</option>
							<option value="3">学習の進め方について</option>
							<option value="4">受講期限について</option>
							<option value="5">受講終了後のサポートについて</option>
						</select>
					</td>
				</tr>
			</table>
			<div class="form_textarea">
				<p><span>必須</span>お問い合わせ内容</p>
				<textarea placeholder="本文を入力してください" name="report" rows="7" cols="80" required></textarea>
			</div>
			
			<div class="submit">
				<input type="submit" value="確認">
			</div>
		</form>
	</body>

	<footer>
		<%@include file= "/common/footerU.jsp" %>
	</footer>
</html>
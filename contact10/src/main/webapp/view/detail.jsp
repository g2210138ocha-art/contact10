<%@page contentType="text/html; charset=UTF-8"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="style.css">
	<title>お問い合わせ詳細</title>
</head>

<body>
	<!-- ヘッダー jspにするときはここ消してincludeでいれてね-->
	
	<header>
		
		
	</header>
	
	
	<div class="breadcrumb">
		<a href="/menu.jsp">メニュー</a>　｜　<a href="/list.jsp">お問い合わせ一覧</a>　|　お問い合わせ詳細
	</div>
	
	<section>
		<div class="title">
			<h2>お問い合わせ詳細</h2>
		</div>
		
	
		<table class="detail-table">
			<tr class="green">
				<th>No.の変数</th>
				<th>名前の変数</th>
				<th>種類の変数</th>
				<th>お問い合わせ日時の変数</th>
			</tr>
		</table>
		
		<div class="contact-text">
			お問い合わせフォームの<br>
			お問い合わせ内容に<br>
			入力された文が<br>
			表示されるよ<br>
			（お問い合わせ内容の変数）
		</div>
		
		<div class="btn">
			<a href="/list.jsp" class="btn-item">お問い合わせ一覧へ戻る</a>
			<a href="/response.jsp" class="btn-item">返信する</a>
			
			
		</div>
		
	</section>
	
</body>
</html>
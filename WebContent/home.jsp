<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/home.css">
</head>
<body>
	<div class="flex dark">
		<span class="block"> <a href="SelectFaculty" class="bttn-dark">注文する</a>
			<div class="description">
				あなたが履修している科目を選択し、<br>その科目に応じた教科書を注文します。
			</div>
		</span>
		<span class="block"> <a href="OrderHistory" class="bttn-dark">注文履歴</a>
			<div class="description">
				注文履歴を確認します。<br>変更・キャンセルもこちらで。
			</div>
		</span>
		<span class="block"> <a href="EvaluateTextbook" class="bttn-dark">評価する</a>
			<div class="description">購入した教科書が<br>必要かどうかを評価します。</div>
		</span>
	</div>
	<a class="logout" href="Logout">logout</a>
</body>
</html>

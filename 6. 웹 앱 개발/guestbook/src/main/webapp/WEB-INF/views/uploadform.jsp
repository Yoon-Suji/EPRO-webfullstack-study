<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadform</title>
</head>
<body>
	<h1>Upload Form</h1>
	<br>
	<br>
	<form method="post" action="upload" enctype="multipart/form-data"> <!-- enctype, post 중요 -->

		file : <input type="file" name="file"><br> <input
			type="submit">
	</form>
</body>
</html>
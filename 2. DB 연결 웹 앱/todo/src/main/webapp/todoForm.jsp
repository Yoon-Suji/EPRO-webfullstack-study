<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>To-Do Form</title>
</head>
<body>
    <header class="register-header">
        할일 등록
    </header>
    <section class="register-section">
        <form action="http://localhost:8080/todo/todoadd" accept-charset="utf-8" method="POST" class="form">
            <div>
                <label for="what">어떤일인가요?</label>
                <input type="text" id="what" name="what" placeholder="swift 공부하기(24자까지)" required maxlength="24">
            </div>
            <div>
                <label for="who">누가 할일인가요?</label>
                <input type="text" id="who" name="who" placeholder="홍길동" required>
            </div>
            <div>
                <label for="rank">우선순위를 선택하세요</label>
                <label class="rank"><input type="radio" name="rank" value="1" required>1순위</label>
                <label class="rank"><input type="radio" name="rank" value="2" required>2순위</label>
                <label class="rank"><input type="radio" name="rank" value="3" required>3순위</label>
            </div>
            <div>
                <button class="back" type="button" onclick="location.href='main'">&lt;이전</button>
                <div class="btn">
                    <input class="submit" type="submit" value="제출">
                    <input class="reset" type="reset" value="내용지우기">
                </div>
            </div>
        </form>
    </section>
</body>
</html>
<%@page import="kr.or.connect.todo.dto.TodoDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>To-Do List</title>
</head>
<body>
    <header class="header">
    	<form class="btn-form" action="http://localhost:8080/todo/todoform">
        <input class="register-btn" type="submit" value="새로운 TODO 등록">
    	</form>
    </header>

    <div class="deco">나의 해야할 일들</div>

    <section class="section">
		<div class="category" id="TODO">
            <div class="category-title">TODO</div>
            
            
        </div>
        <div class="category" id="DOING">
            <div class="category-title">DOING</div>
            
            
        </div>
        <div class="category" id="DONE">
            <div class="category-title">DONE</div>
            
            
        </div>
    </section>
    <%
		List<TodoDto> list = (List<TodoDto>)request.getAttribute("dtolist");
		for (TodoDto dto : list){
			Long id = dto.getId();
			String type = dto.getType();
			String title = dto.getTitle();
			String regDate = dto.getRegDate();
			String name = dto.getName();
			int sequence = dto.getSequence();%>
			<script>
				var card = document.createElement('div');
				card.className = 'card';
				card.id = '<%=id%>';
				var title = document.createElement('div');
				title.innerHTML='<%=title%>';
				title.className='todo-title';
				var detail = document.createElement('div');
				detail.innerHTML='등록날짜: <%=regDate%>, <%=name%>, 우선순위 <%=sequence%>';
				detail.className='detail';
				card.appendChild(title);
				card.appendChild(detail);
				<%
				if (type.equals("DONE")){ %>
					var category = document.querySelector('#DONE');
					category.appendChild(card);
			<%	}
				else {%>
					var rightArrow = document.createElement('button');
					rightArrow.innerHTML="&#10132;";
					rightArrow.className='right-arrow';
					card.appendChild(rightArrow);
					<% 
					if (type.equals("TODO")){%>
						var category = document.querySelector('#TODO');
				<%	}
					else if (type.equals("DOING")){%>
						var category = document.querySelector('#DOING');
				<%	}%>
					category.appendChild(card);
					
			<%	}
				%>			
			</script>
	<%	}
	%>
 
</body>
<script src="javascript.js"></script>
</html>
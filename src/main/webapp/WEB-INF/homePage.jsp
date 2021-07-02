<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
	rel="stylesheet" 
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div style="display: flex; justify-content: space-between">
		<h1>Welcome to Reader's Shop <c:out value="${user.userName}"></c:out></h1>
		<span style="margin-left: 5%; margin-right: 5%;">
			<a href="/logout">Logout</a><br/>
		</span>
	</div>
	
	<div>
		<div style="display: flex; justify-content: space-between">
			<a href="books/new">Add a book to Store</a>
			<a href="showCart">Show Shopping Cart</a>
		</div>
		<table style="outline: solid white 1px;" class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Books</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
				 <c:forEach items="${books}" var="book">
				 	<tr>
				 		<td><c:out value="${book.id}"></c:out></td>
				 		<td>
				 			<p><c:out value="${book.title}"></c:out> by: <c:out value="${book.author}"></c:out></p>
				 			<ul>
				 				<li>Genre: <c:out value="${book.genre}"></c:out></li>
				 				<li>Description: <c:out value="${book.description}"></c:out></li>
				 				<li>Language: <c:out value="${book.language}"></c:out></li>
				 			</ul>
				 		</td>
				 		<td><c:out value="${book.price}"></c:out> <br>
				 			<a style="color: skyblue" href="/addToCart/${book.id}">Add to Cart</a>
				 		</td>
				 	</tr>
				 </c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>
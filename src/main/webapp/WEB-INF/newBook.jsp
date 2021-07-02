<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
	rel="stylesheet" 
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
	crossorigin="anonymous"> 
<title>New Book</title>
</head>
<body style="background-color: slategray; color: white; margin: 5%">
	<div>
		<h1>New Book</h1>
		<form:form class="form" action="/books" method="post" modelAttribute="book">
		
			<%-- <form:input type="hidden" path="user" value="${user.id}"/> --%>
		    <p>
		        <form:label path="title">Title: </form:label>
		        <form:errors path="title"/>
		        <form:input path="title"/>
		    </p>
		    <p>
		        <form:label path="author">Author: </form:label>
		        <form:errors path="author"/>
		        <form:input path="author"/>
		    </p> 
		    <p>
		        <form:label path="genre">Genre: </form:label>
		        <form:errors path="genre"/>
		        <form:input path="genre"/>
		    </p>
		    <p>
		        <form:label path="description">Description: </form:label>
		        <form:errors path="description"/>
		        <form:textarea path="description"/>
		    </p>
		    <p>
		        <form:label path="language">Language: </form:label>
		        <form:errors path="language"/>
		        <form:input path="language"/>
		    </p>   
		    <p>
		        <form:label path="price">Price: </form:label>
		        <form:errors path="price"/>
		        <form:input path="price"/>
		    </p>   
		    <input type="submit" value="Submit"/>
		</form:form>
		
		<a style="color: white;" href="/home">Back to Home Page</a>
	</div>
</body>
</html>
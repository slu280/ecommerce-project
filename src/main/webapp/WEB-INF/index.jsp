<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin login&register</title>
</head>
<body>

	<div class="user">
		<form:form action="/register" method="post" modelAttribute="newUser" class="register">
		<h1>Register</h1>
		    <p>
		        <form:label path="userName">Username: </form:label>
		        <form:errors path="userName"/>
		        <form:input path="userName"/>
		    </p>
		     <p>
		        <form:label path="email">Email: </form:label>
		        <form:errors path="email"/>
		        <form:input path="email"/>
		    </p>
		    <p>
		        <form:label path="password">Password: </form:label>
		        <form:errors path="password"/>     
		        <form:input  path="password"/>
		    </p>    
		    <p>
		        <form:label path="confirm">Confirm Password: </form:label>
		        <form:errors path="confirm"/>
		        <form:input path="confirm"/>
		    </p>
		    <input type="submit" value="Register"/>
		</form:form>  
		
		
		<form:form action="/login" method="post" modelAttribute="newLogin" class="login">
		<h1>Login</h1>
	     <p>
	        <form:label path="email">Email: </form:label>
	        <form:errors path="email"/>
	        <form:input path="email"/>
	    </p>
	    <p>
	        <form:label path="password">Password: </form:label>
	        <form:errors path="password"/>     
	        <form:input  path="password"/>
	    </p>    
	    <input type="submit" value="Login"/>
	</form:form>      
	</div>
</body>
</html>
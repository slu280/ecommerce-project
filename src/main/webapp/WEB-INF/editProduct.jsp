<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product</title>
</head>
<body>
	<form:form action="/updateProduct/${old_product.id}" method="post" modelAttribute="editProduct">
		<h1>Edit Product</h1>
		    <p>
		        <form:label path="productName">Product Name: </form:label>
		        <form:errors path="productName"/>
		        <form:input path="productName" value="${old_product.productName}"/>
		    </p>
		     <p>
		        <form:label path="price">Price: </form:label>
		        <form:errors path="price"/>
		        <form:input path="price" value="${old_product.price}"/>
		    </p>
		    <p>
		        <form:label path="description">Description (Optional): </form:label>
		        <form:errors path="description"/>     
		        <form:input  path="description" value="${old_product.description}"/>
		    </p>    
		    <p>
		        <form:label path="image1">image1: </form:label>
		        <form:errors path="image1"/>
		        <form:input path="image1" value="${old_product.image1}"/>
		    </p>
		    <p>
		        <form:label path="image2">image2 (Optional): </form:label>
		        <form:errors path="image2"/>
		        <form:input path="image2" value="${old_product.image2}"/>
		    </p>
		    <p>
		     	<form:label path="bestSeller">Do you want this item to be in the "best sellers" section: </form:label>
		        <form:select path="bestSeller">
		        	<form:option value="true" label="yes"/>
		        	<form:option value="false" label="no"/>
		        </form:select>
		    </p>
		    <input type="submit" value="Confirm"/>
		    <button><a href="/deleteProduct/${old_product.id}">Delete</a></button>
	</form:form>  
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css" href="/css/dashboard.css">
</head>
<body>
	<div class="create-product">
		<form:form action="/createProduct" method="post" modelAttribute="newProduct">
		<h1>Add Product</h1>
		    <p>
		        <form:label path="productName">Product Name: </form:label>
		        <form:errors path="productName"/>
		        <form:input path="productName"/>
		    </p>
		     <p>
		        <form:label path="price">Price: </form:label>
		        <form:errors path="price"/>
		        <form:input path="price"/>
		    </p>
		    <p>
		        <form:label path="description">Description (Optional): </form:label>
		        <form:errors path="description"/>     
		        <form:input  path="description"/>
		    </p>    
		    <p>
		        <form:label path="image1">image1: </form:label>
		        <form:errors path="image1"/>
		        <form:input path="image1"/>
		    </p>
		    <p>
		        <form:label path="image2">image2 (Optional): </form:label>
		        <form:errors path="image2"/>
		        <form:input path="image2"/>
		    </p>
		     <p>
		     	<form:label path="bestSeller">Do you want this item to be in the "best sellers" section: </form:label>
		        <form:select path="bestSeller">
		        	<form:option value="true" label="yes"/>
		        	<form:option value="false" label="no"/>
		        </form:select>
		    </p>
		    <input type="submit" value="Add"/>
		</form:form>  
	</div>
	<div class="all-products">
		<h2>All Products</h2>
		<c:forEach var="oneProduct" items="${products}">
			<label>id:</label>
			<c:out value="${oneProduct.id}"/>
			</br>
			<label>name:</label>
			<c:out value="${oneProduct.productName}"/>
			</br>
			<label>price:</label>
			<c:out value="${oneProduct.price}"/>
			</br>
			<label>description:</label>
			<c:out value="${oneProduct.description}"/>
			</br>
			<label>image1:</label>
			<c:out value="${oneProduct.image1}"/>
			</br>
			<label>image2:</label>
			<c:out value="${oneProduct.image2}"/>
			</br>
			<label>Best Seller(true/false):</label>
			<c:out value="${oneProduct.bestSeller}"/>
			</br>
			<a href="/editProduct/${oneProduct.id}">Edit</a>
			<a href="/deleteProduct/${oneProduct.id}">Delete</a>
			</br>
			<hr>
		</c:forEach> 
	</div>
</body>
</html>
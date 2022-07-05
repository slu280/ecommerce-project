<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order for Pickup</title>
<link rel="stylesheet" type="text/css" href="/css/home.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<style>
        input[type=number]::-webkit-inner-spin-button, 
		input[type=number]::-webkit-outer-spin-button {  
			opacity: 1;
		}
</style>
</head>
<body>
	<div class="nav-bar">
		<h1 class="store-name">OH HONEY</h1>
		<a href="/home/${sessionId}" class="bar-point">home</a>
		<a href="/allProducts/${sessionId}" class="bar-point">order</a>
		<a href="/contactus/${sessionId}" class="bar-point">contact us</a>
		<a href="/cart/${sessionId}" class="bar-point">cart(${cartNum})</a>
	</div>

	<div class="all-product-div">
	<h2 class="second-header">All Products: </h2>
	<div class="all-products">
		
		<c:forEach var="product" items="${allProducts}" >
			<div class="items-div">
			<img src="${product.image1}" class="product-img"/>
			<br></br>
			<p class="product-name">${product.productName}</p>
			
			<p class="product-price"> $${product.price} </p>
				<form:form action="/createCart/${sessionId}" method="post" modelAttribute="newCart">
					<p class="product-qty">
				        <form:label path="qty">Qty: </form:label>
				        <form:errors path="qty"/>
				        <form:input type="number" min="1" value="1" path="qty" class="input-qty"/>
				    </p>
				    <form:input type="hidden" path="product" value="${product.id}"/>
				    <form:input type="hidden" path="sessionId" value="${sessionId}"/>
				    <input type="submit" value="Add to Cart" class="add-cart"/>
				    
				</form:form>
			</div>
		</c:forEach> 
	</div>
	</div>
</body>
</html>
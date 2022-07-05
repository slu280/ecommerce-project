<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Oh Honey Bakery</title>
<link rel="stylesheet" type="text/css" href="/css/home.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body>

	<div class="nav-bar">
		<h1 class="store-name">OH HONEY</h1>
		<a href="/home/${sessionId}" class="bar-point">home</a>
		<a href="/allProducts/${sessionId}" class="bar-point">order</a>
		<a href="/contactus/${sessionId}" class="bar-point">contact us</a>
		<a href="/cart/${sessionId}" class="bar-point">cart(${cartNum})</a>
	</div>
	<div class="middle-part">
		<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img class="img-slides" src="/css/image5.JPG" alt="First slide">
		    </div>
		    <div class="carousel-item">
		      <img class="img-slides" src="/css/image2.JPG" alt="Second slide">
		    </div>
		   
		  </div>
		  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>
	</div>
	<div class="best-sellers">
		<h3 class="best-seller-title">Best Sellers</h3>
		<div class="all-products">
		
		<c:forEach var="product" items="${products}" >
			<div class="items-div">
			<img src="${product.image1}" class="product-img"/>
			<br></br>
			<p class="product-name">${product.productName}</p>
			
			<p class="product-price"> $${product.price} </p>
			
				<form:form action="/createCartInBest/${sessionId}" method="post" modelAttribute="newCart">
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
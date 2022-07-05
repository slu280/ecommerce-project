<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" type="text/css" href="/css/home.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
	<div class="nav-bar">
		<h1 class="store-name">OH HONEY</h1>
		<a href="/home/${sessionId}" class="bar-point">home</a>
		<a href="/allProducts/${sessionId}" class="bar-point">order</a>
		<a href="/contactus/${sessionId}" class="bar-point">contact us</a>
		<a href="/cart/${sessionId}" class="bar-point">cart(${cartNum})</a>
	</div>
	<c:if test="${cartNum != 0}">
	<div class="cart-items">
		<div class="cart-items-div">
		<h2 class="cart-header">Cart</h2>
		<c:forEach var="cart" items="${carts}" >
			<div class="cart-display">
				<img src="${cart.getProduct().image1}" class="cart-img"/>
				<div class="desc-box">	
					<div class="desc-line">
						<c:out value="${cart.getProduct().productName}"/>
					</div>
					<div class="desc-line">
						<button><a href="/editCart/minus/${cart.id}/${sessionId}">-</a></button>
						<input  min="0" value="${cart.qty}" class="qty-box"/>
						<button><a href="/editCart/add/${cart.id}/${sessionId}">+</a></button>
					</div>
					<div class="desc-line">
						$<fmt:formatNumber type = "number" maxFractionDigits="2" value = "${cart.getProduct().price * cart.qty}" />
						
					</div>
					<div class="desc-line">
						<button class="remove-btn"><a href="/deleteFromCart/${cart.id}/${sessionId}">Remove</a></button>
					</div>
				</div>
			</div>
		</c:forEach>
		
		
		<div class="place-order-btn">
		
		<p class="subtotal-div-order">Total: $<fmt:formatNumber type = "number" maxFractionDigits="2" value = "${totalPrice}" /></p>
		<button><a href="/placeOrder/${sessionId}">Place order</a></button>
		</div>
		</div>
		
	</div>
	</c:if>
	<c:if test="${cartNum == 0}">
		<h2 class="cart-header">Cart</h2>
		<p class="cart-empty">Your cart is empty.</p>
	</c:if>
</body>
</html>
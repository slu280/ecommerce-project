<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirm</title>
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
	<div class="order-number">
	Order number: 
	<c:out value="${orders.get(0).orderNumber}"/>
	</div>  
	
	<c:forEach var="order" items="${orders}" >
			<div class="confirm-display">
				<img src="${order.getProduct().image1}" class="confirm-img"/>
				<div class="confirm-desc-box">	
					<div class="desc-line">
						<c:out value="${order.getProduct().productName}"/>
					</div>
					<div class="desc-line">
						
						<c:out value="${order.qty}"/>
						
					</div>
					<div class="desc-line">
						$<fmt:formatNumber type = "number" maxFractionDigits="2" value = "${order.getProduct().price * order.qty}" />
					</div>
					
				</div>
			</div>
		</c:forEach>
		<p class="confirm-subtotal-div">Subtotal: $<fmt:formatNumber type = "number" maxFractionDigits="2" value = "${totalPrice}" /></p>
		
		<p class="pick-up-info">Hi <c:out value="${orders.get(0).name}"/>, You can pick up your order on <fmt:formatDate pattern = "MM-dd-yyyy" value = "${orders.get(0).pickUpDate}" /></p>
</body>
</html>
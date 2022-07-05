<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order</title>
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
	
	<h4 class="pick-up-title">Pick up information: </h4>
	<div class="form-input-names">
		<form:form action="/editOrder/${sessionId}" method="post" modelAttribute="newOrder">
					<p class="order-font">
				        <form:label path="name">Name: </form:label>
				        <form:errors path="name"/>
				        <form:input type="text" path="name"/>
				    </p>
				    <p class="order-font">
				        <form:label path="phoneNumber">Phone: </form:label>
				        <form:errors path="phoneNumber"/>
				        <form:input type="text" path="phoneNumber"/>
				    </p>
				    <p class="order-font">
				    	<label>Payment: </label>
				        <form:select path="payment" >
				        	<option value="venmo">Venmo</option>
				        	<option value="zelle">Zelle</option>
				        	<option value="payInStore">Pay in store</option>
				        </form:select>
				    </p>
				    <p class="order-font">
				        <form:label path="pickUpDate">Pick Up Date: </form:label>
				        <form:errors path="pickUpDate"/>
				        <form:input type="date" path="pickUpDate"/>
				    </p>
				    <form:input type="hidden" path="qty" value="${carts.get(0).getQty()}"/>
				    <form:input type="hidden" path="product" value="${carts.get(0).getProduct().id}"/>
				    <form:input type="hidden" path="orderNumber" value="${carts.get(0).getProduct().productName}"/>
				    <button><a href="/deleteOrder/${sessionId}">Cancel</a></button>
				    <input type="submit" value="Submit"/>
				    
				</form:form>
	</div>
	
	
</body>
</html>
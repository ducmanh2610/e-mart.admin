<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../layouts/meta.jsp" />
<jsp:include page="../layouts/resources.jsp"></jsp:include>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css" />
<title>Register</title>
</head>
<body>
	<div class="wrapper">
		<div class="login">
			<img alt="logo"
				src="${pageContext.request.contextPath}/resources/images/asset-admin.png">
			<img alt="logo"
				src="${pageContext.request.contextPath}/resources/images/wh-manager.png">
			<h1>
				<i class="bi bi-lock-fill"></i>Login
			</h1>
			<f:form method="POST"
				action="${pageContext.request.contextPath}/register" modelAttribute="user">
				<div class="mb-3">
					<f:label path="username" class="form-label"><i
						class="bi bi-person-fill"></i>Username</f:label> <f:input type="text"
						class="form-control" id="username" path="username"/>
						<f:errors path="username" cssClass="text-danger"></f:errors>
				</div>
				<div class="mb-3">
					<f:label path="password" class="form-label"><i
						class="bi bi-key-fill"></i>Password</f:label> <f:input type="password"
						class="form-control" id="password" path="password"/>
						<f:errors path="password" cssClass="text-danger"></f:errors>
				</div>
				<div class="mb-3">
					<f:label path="firstName" class="form-label"><i
						class="bi bi-key-fill"></i>First Name</f:label> <f:input type="text"
						class="form-control" id="firstName" path="firstName"/>
						<f:errors path="firstName" cssClass="text-danger"></f:errors>
				</div>
				<div class="mb-3">
					<f:label path="lastName" class="form-label"><i
						class="bi bi-key-fill"></i>Last Name</f:label> <f:input type="text"
						class="form-control" id="lastName" path="lastName"/>
						<f:errors path="lastName" cssClass="text-danger"></f:errors>
				</div>
				<div class="mb-3">
					<f:label path="email" class="form-label"><i
						class="bi bi-key-fill"></i>Email</f:label> <f:input type="email"
						class="form-control" id="email" path="email"/>
						<f:errors path="email" cssClass="text-danger"></f:errors>
				</div>
				<div style="text-align: center;">
					<button type="submit" class="btn btn-primary">
						<i class="bi bi-shield-lock-fill"></i>Register
					</button>
				</div>
			</f:form>
		</div>
	</div>
</body>
</html>
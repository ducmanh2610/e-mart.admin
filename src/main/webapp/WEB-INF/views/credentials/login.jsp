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
<title>Login</title>
</head>
<body>
	<div class="wrapper">
		<div class="login">
			<a href="${pageContext.request.contextPath}/login"><img alt="logo"
				src="${pageContext.request.contextPath}/resources/images/asset-admin.png"></a>
			<img alt="logo"
				src="${pageContext.request.contextPath}/resources/images/wh-manager.png">
			<h1>
				<i class="bi bi-lock-fill"></i>Login
			</h1>
			<f:form method="POST"
				action="${pageContext.request.contextPath}/login" modelAttribute="user">
				<span class="text-danger">${msgError}</span>
				<span class="text-success">${status}</span>
				<div class="mb-3">
					<f:label path="username" class="form-label"><i
						class="bi bi-person-fill"></i>Username</f:label> <f:input type="text"
						class="form-control" id="username" path="username"
						aria-describedby="emailHelp"/>
					<f:errors path="username" cssClass="text-danger"></f:errors>
				</div>
				<div class="mb-3">
					<f:label path="password" class="form-label"><i
						class="bi bi-key-fill"></i>Password</f:label> <f:input type="password"
						class="form-control" id="password" path="password"/>
						<f:errors path="password" cssClass="text-danger"></f:errors>
				</div>
				<div style="text-align: center;">
					<button type="submit" class="btn btn-primary">
						<i class="bi bi-shield-lock-fill"></i>Sign In
					</button>
				</div>
				<div style="text-align: center;">
					<a href="${pageContext.request.contextPath}/register"><button
							type="button" class="btn btn-outline-primary">
							<i class="bi bi-shield-lock-fill"></i>Register
						</button></a>
				</div>
			</f:form>
		</div>
	</div>
</body>
</html>
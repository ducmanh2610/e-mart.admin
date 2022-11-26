<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sp:url value="/" var="rootpath"></sp:url>

<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>
<jsp:include page="layouts/meta.jsp" />
<jsp:include page="layouts/resources.jsp"></jsp:include>
<link rel="stylesheet" href="${rootpath}/resources/css/style.css"/>
</head>

<body>
	<div class="header">
		<jsp:include page="layouts/header.jsp" />
	</div>
	<div class="section">
		<div class="sidebar">
			<jsp:include page="layouts/sidebar.jsp" />
		</div>
		<div class="main">
			<p>Dashboard</p>
			<h3>Welcome</h3><b>${username}: ${roles}</b>
		</div>
	</div>
	<jsp:include page="layouts/footer.jsp" />
</body>
</html>
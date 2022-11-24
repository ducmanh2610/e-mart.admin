<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Assets Editor</title>
<jsp:include page="../layouts/meta.jsp" />
<jsp:include page="../layouts/resources.jsp"></jsp:include>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/asset-editor.css" />
</head>

<body>
	<div class="header">
		<jsp:include page="../layouts/header.jsp" />
	</div>
	<div class="section">
		<div class="sidebar">
			<jsp:include page="../layouts/sidebar.jsp" />
		</div>
		<div class="main">
			<div class="container-fluid">
				<div class="title">
					<h3>Categories Editor</h3>
				</div>
				<span class="text-danger" id="textError">${msgErr}</span> <span
					class="text-danger" id="textError">${msg}</span>
				<div class="editor">
					<f:form action="${pageContext.request.contextPath}/categories/${category.id == 0 ? 'save' : 'update'}"
						method="post" id="asset_form" modelAttribute="category"
						>
						<div class="mb-3">
							<label for="name" class="form-label">Name</label>
							<f:input type="text" class="form-control" id="name" path="name"
								aria-describedby="emailHelp" />
							<f:errors class="text-danger" path="name"></f:errors>
						</div>

						<div class="mb-3">
							<label for="assetStatus" class="form-label">Status</label>
							<div class="form-check form-switch">
								<c:choose>
									<c:when test="${status == 0}">
										<input class="form-check-input" type="checkbox" role="switch"
											id="assetStatus" name="assetStatus"/>
										<label class="form-check-label" for="assetStatus">Active/Disabled</label>
									</c:when>
									<c:otherwise>
										<input class="form-check-input" type="checkbox" role="switch"
											id="assetStatus" name="assetStatus" checked />
										<label class="form-check-label" for="assetStatus">Active/Disabled</label>
									</c:otherwise>
								</c:choose>
								<f:input type="text" id="statusValue" path="status" />
							</div>
						</div>

						<div class="mb-3">
							<f:label path="description" for="description" class="form-label">Description</f:label>
							<f:textarea class="form-control" id="description"
								aria-describedby="emailHelp" path="description"></f:textarea>
							<f:errors class="text-danger" path="description"></f:errors>
						</div>

						<script type="module"
							src="${pageContext.request.contextPath}/resources/js/firebase.js"></script>
						<script type="text/javascript"
							src="${pageContext.request.contextPath}/resources/js/validation.js"></script>
						<button type="submit" class="btn btn-primary" id="submit_btn">Save</button>
					</f:form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
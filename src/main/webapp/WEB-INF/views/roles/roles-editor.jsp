<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Roles Editor</title>
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
					<h3>Roles Editor</h3>
				</div>
				<span class="text-danger" id="textError">${msgErr}</span> <span
					class="text-danger" id="textError">${msg}</span>
				<div class="editor">
					<f:form
						action="${pageContext.request.contextPath}/roles/${role.id == 0 ? 'save' : 'update'}"
						method="post" id="asset_form" modelAttribute="role">
						<c:if test="${ role.id != 0 }">
							<f:input type="hidden" path="id" value="${role.id}" />
						</c:if>
						<div class="mb-3">
							<label for="name" class="form-label">Name</label>
							<c:choose>
								<c:when test="${role.id == 0}">
									<f:input type="text" class="form-control" id="name" path="name"
										aria-describedby="name" />
									<f:errors cssClass="text-danger" path="name"></f:errors>
								</c:when>
								<c:otherwise>
									<f:input type="hidden" class="form-control" id="name" path="name"
										aria-describedby="name" />
									<input type="text" class="form-control"
										value="${role.name}" readonly />
								</c:otherwise>
							</c:choose>
						</div>

						<div class="mb-3">
							<label for="assetStatus" class="form-label">Status</label>
							<div class="form-check form-switch">
								<c:choose>
									<c:when test="${role.status == 0}">
										<input class="form-check-input" type="checkbox" role="switch"
											id="assetStatus" name="assetStatus" />
										<label class="form-check-label" for="status">Active/Disabled</label>
									</c:when>
									<c:otherwise>
										<input class="form-check-input" type="checkbox" role="switch"
											id="assetStatus" name="assetStatus" checked />
										<label class="form-check-label" for="status">Active/Disabled</label>
									</c:otherwise>
								</c:choose>
								<f:input type="hidden" id="statusValue" path="status"
									value="${role.status}" />
							</div>
						</div>

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
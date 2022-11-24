<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Users Editor</title>
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
					<h3>Users Editor</h3>
				</div>
				<span class="text-danger" id="textError">${msgErr}</span> <span
					class="text-danger" id="textError">${msg}</span>
				<div class="editor">
					<f:form
						action="${pageContext.request.contextPath}/users/${user.id == 0 ? 'save' : 'update'}"
						method="post" id="asset_form" modelAttribute="user">
						<div class="mb-3">
							<c:if test="${user.id != 0}">
								<f:input type="hidden" path="id" />
							</c:if>
							<label for="name" class="form-label">Username</label>
							<c:choose>
								<c:when test="${user.id != 0}">
									<f:input type="hidden" id="name" path="username" />
									<input type="text" class="form-control" id="name"
										value="${user.username}" readonly />
								</c:when>
								<c:otherwise>
									<f:input type="text" class="form-control" id="name"
										path="username" />
								</c:otherwise>
							</c:choose>
							<f:errors class="text-danger" path="username"></f:errors>
						</div>

						<div class="mb-3">
							<f:label path="password" class="form-label">Password</f:label>
							<f:input path="password" type="password" class="form-control"
								id="password" aria-describedby="password" />
							<f:errors class="text-danger" path="password"></f:errors>
						</div>

						<div class="mb-3">
							<f:label path="firstName" class="form-label">First Name</f:label>
							<f:input path="firstName" type="text" class="form-control"
								id="firstName" aria-describedby="firstName" />
							<f:errors class="text-danger" path="firstName"></f:errors>
						</div>

						<div class="mb-3">
							<f:label path="lastName" class="form-label">Last Name</f:label>
							<f:input path="lastName" type="text" class="form-control"
								id="lastName" aria-describedby="lastName" />
							<f:errors class="text-danger" path="lastName"></f:errors>
						</div>

						<div class="mb-3">
							<f:label path="email" class="form-label">Email</f:label>
							<f:input path="email" type="text" class="form-control"
								id="lastName" aria-describedby="email" />
							<f:errors class="text-danger" path="email"></f:errors>
						</div>


						<div class="mb-3">
							<f:label path="roles" class="form-label">Roles</f:label>
							<f:select class="form-select" path="roles">
								<f:options path="roles" items="${rolesList}" itemLabel="name"
									itemValue="id" />
							</f:select>
						</div>

						<div class="mb-3">
							<f:label path="department" class="form-label">Department</f:label>
							<f:select class="form-select" path="department">
								<f:options items="${departmentList}" itemLabel="name"
									itemValue="id" />
							</f:select>
						</div>

						<div class="mb-3">
							<label for="assetStatus" class="form-label">Status</label>
							<div class="form-check form-switch">
								<c:choose>
									<c:when test="${user.status == 0}">
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
									value="${user.status}" />
							</div>
						</div>

						<div class="mb-3">
							<label for="thumbnailURL" class="form-label">Thumbnail</label>
							<f:input type="hidden" id="namebox" name="thumbnail"
								path="imageURL" />
							<div class="image-preview">
								<div class="image-box" id="imageBox">
									<img id="myimg" src="${user.imageURL}" />
								</div>
							</div>
							<span class="text-danger" id="descError"></span>
							<button class="btn btn-light" type="button" id="selbtn">Select
								Image</button>
							<button class="btn btn-danger" type="button" id="upbtn">Upload
								Image</button>
							<f:errors path="imageURL" class="text-danger" id="thumbnailError" />
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
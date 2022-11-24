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
					<h3>Brand Editor</h3>
				</div>
				<span class="text-danger" id="textError">${msgErr}</span> <span
					class="text-danger" id="textError">${msg}</span>
				<div class="editor">
					<f:form action="${pageContext.request.contextPath}/brands/${brand.id == 0 ? 'save' : 'update'}"
						method="post" id="asset_form" modelAttribute="brand" 
						>
						<c:if test = "${ brand.id != 0 }"><f:input type="hidden" path="id" value="${brand.id}"/></c:if>
						<div class="mb-3">
							<label for="name" class="form-label">Name</label>
							<f:input type="text" class="form-control" id="name" path="name"
								aria-describedby="name" />
							<f:errors cssClass="text-danger" path="name"></f:errors>
						</div>

						<div class="mb-3">
							<f:label path="website" class="form-label">Website</f:label>
							<f:input path="website" type="text" class="form-control" id="website"
								aria-describedby="website" />
							<f:errors cssClass="text-danger" path="website"></f:errors>
						</div>

						<div class="mb-3">
							<label for="assetStatus" class="form-label">Status</label>
							<div class="form-check form-switch">
								<c:choose>
									<c:when test="${brand.status == 0}">
										<input class="form-check-input" type="checkbox" role="switch"
											id="assetStatus" name="assetStatus"/>
										<label class="form-check-label" for="status">Active/Disabled</label>
									</c:when>
									<c:otherwise>
										<input class="form-check-input" type="checkbox" role="switch"
											id="assetStatus" name="assetStatus" checked />
										<label class="form-check-label" for="status">Active/Disabled</label>
									</c:otherwise>
								</c:choose>
								<f:input type="hidden" id="statusValue" path="status" value="${brand.status}"/>
							</div>
						</div>
						<div class="mb-3">
							<f:label path="description" for="description" class="form-label">Description</f:label>
							<f:textarea class="form-control" id="description"
								aria-describedby="emailHelp" path="description"></f:textarea>
							<f:errors cssClass="text-danger" path="description"></f:errors>
						</div>

						<div class="mb-3">
							<f:input type="hidden" class="form-control" id="createdBy"
								 value="admin"
								aria-describedby="createdBy" name="createdBy" path="createdBy"/>
						</div>

						<div class="mb-3">
							<label for="imageURL" class="form-label">Thumbnail</label> <f:input
								type="hidden" id="namebox" name="thumbnail" path="imageURL"/>
							<div class="image-preview">
								<div class="image-box" id="imageBox">
									<img id="myimg" src="${brand.imageURL}"/>
								</div>
							</div>
							<span class="text-danger" id="descError"></span>
							<button class="btn btn-light" type="button" id="selbtn">Select
								Image</button>
							<button class="btn btn-danger" type="button" id="upbtn">Upload
								Image</button>
							<span class="text-danger" id="thumbnailError"></span>
							<f:errors cssClass="text-danger" path="imageURL"></f:errors>
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
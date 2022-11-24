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
					<h3>Assets Editor</h3>
				</div>
				<span class="text-danger" id="textError">${msgErr}</span> <span
					class="text-danger" id="textError">${msg}</span>
				<div class="editor">
					<f:form action="${pageContext.request.contextPath}/products/save"
						method="post" id="asset_form" modelAttribute="product">
						<div class="mb-3">
							<label for="name" class="form-label">Name</label>
							<f:input type="text" class="form-control" id="name" path="name"
								aria-describedby="emailHelp" />
							<f:errors class="text-danger" path="name"></f:errors>
						</div>

						<div class="mb-3">
							<f:label path="assetTagId" class="form-label">Asset Tag ID.</f:label>
							<f:input path="assetTagId" type="text" class="form-control"
								id="assetTagId" aria-describedby="assetTagId" />
							<f:errors class="text-danger" path="assetTagId"></f:errors>
						</div>

						<div class="mb-3">
							<f:label path="cost" class="form-label">Price</f:label>
							<f:input path="cost" type="text" class="form-control" id="price"
								aria-describedby="emailHelp" />
							<f:errors class="text-danger" path="cost"></f:errors>
						</div>

						<div class="mb-3">
							<f:label path="brand" class="form-label">Brand</f:label>
							<f:select class="form-select" aria-label="Brand Option"
								id="brand" name="brandId" path="brand">
								<f:options items="${brandList}" itemLabel="name" itemValue="id" />
							</f:select>
						</div>

						<div class="mb-3">
							<f:label path="site" class="form-label">Site</f:label>
							<f:input path="site" type="text" class="form-control" id="site"
								aria-describedby="site" />
							<f:errors class="text-danger" path="site"></f:errors>
						</div>

						<div class="mb-3">
							<f:label path="location" class="form-label">Location</f:label>
							<f:input path="location" type="text" class="form-control"
								id="location" aria-describedby="emailHelp" />
							<f:errors class="text-danger" path="location"></f:errors>
						</div>

						<div class="mb-3">
							<label for="assetStatus" class="form-label">Status</label>
							<div class="form-check form-switch">
								<c:choose>
									<c:when test="${status == 0}">
										<input class="form-check-input" type="checkbox" role="switch"
											id="assetStatus" name="assetStatus" />
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
							<f:label for="category" class="form-label" path="category">Category</f:label>
							<f:select class="form-select" aria-label="Category Option"
								id="category" path="category">
								<f:options items="${categoryList}" itemValue="id"
									itemLabel="name" />
							</f:select>
						</div>

						<div class="mb-3">
							<f:label for="department" class="form-label" path="department">Department</f:label>
							<f:select class="form-select" aria-label="Category Option"
								id="department" path="department">
								<f:options items="${departmentList}" itemValue="id"
									itemLabel="name" />
							</f:select>
						</div>

						<div class="mb-3">
							<f:label path="description" for="description" class="form-label">Description</f:label>
							<f:textarea class="form-control" id="description"
								aria-describedby="emailHelp" path="description"></f:textarea>
							<f:errors class="text-danger" path="description"></f:errors>
						</div>

						<div class="mb-3">
							<f:input type="hidden" class="form-control" id="createdBy"
								value="admin" aria-describedby="createdBy" name="createdBy"
								path="createdBy" />
						</div>

						<div class="mb-3">
							<label for="thumbnailURL" class="form-label">Thumbnail</label>
							<f:input type="hidden" id="namebox" name="thumbnail"
								path="thumbnail" />
							<div class="image-preview">
								<div class="image-box" id="imageBox">
									<img id="myimg" />
								</div>
							</div>
							<span class="text-danger" id="descError"></span>
							<button class="btn btn-light" type="button" id="selbtn">Select
								Image</button>
							<button class="btn btn-danger" type="button" id="upbtn">Upload
								Image</button>
							<span class="text-danger" id="thumbnailError"></span>
						</div>

						<div class="mb-3">
							<label class="form-label">Asset Images</label>
							<div id="clearLabel"></div>
							<div id="imagesLinkValue"></div>
							<div id="imagesDiv">
								<c:if test="${product.imagesList.size() > 0}">
									<c:forEach items="${product.imagesList}" var="i" varStatus="loop">
									<div>
										<img id="imgNo${loop.index + 1}" src="${i.url}" alt="${i.url}"/>
										<input type="hidden" id="imgSrcNo${loop.index + 1}"/>
									</div>
									</c:forEach>
								</c:if>
							</div>
							<f:input type="hidden" id="imagesList" name="imagesList"
								path="imagesList" />
							<div id="controlDiv">
								<label id="loadlab"></label>
								<button type="button" class="btn btn-light" id="selectBtn">Select
									Images</button>
								<button type="button" class="btn btn-danger" id="uploadBtn">Upload
									Images</button>
								<span class="text-danger" id="imageListError"></span>
							</div>
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
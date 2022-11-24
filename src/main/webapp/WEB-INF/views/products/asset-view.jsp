<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Assets Viewer</title>
<jsp:include page="../layouts/meta.jsp" />
<jsp:include page="../layouts/resources.jsp"></jsp:include>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/asset-list.css" />
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
					<h3>Assets View</h3>
					<div class="button">
						<a href="${pageContext.request.contextPath}/products/view/${prev}"><button type="button" class="btn btn-secondary">
							<i class="bi bi-caret-left-fill"></i>Prev
						</button></a>
						<a href="${pageContext.request.contextPath}/products/view/${next}"><button type="button" class="btn btn-primary">
							<i class="bi bi-caret-right-fill"></i>Next
						</button></a>
					</div>
				</div>
				<div class="action">
					<b class="text-danger">${product.category.getName()}</b>
					<div class="action-btn">
						<button type="button" class="btn btn-outline-primary">
							<i class="bi bi-printer"></i>Print
						</button>
						<a href="${pageContext.request.contextPath}/products/asset-edit/${product.id}"><button class="btn btn-primary" type="button">
							<i class="bi bi-pencil-square"></i>Edit Asset
						</button></a>
							<button class="btn btn-primary dropdown-toggle"
								id="dropdown-action" data-bs-toggle="dropdown"
								aria-expanded="false" type="button">
								More Actions
							</button>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">Delete</a></li>
								<li><a class="dropdown-item" href="#">Export .csv</a></li>
								<li><a class="dropdown-item" href="#">Active/Disable</a></li>
							</ul>
						</div>
				</div>
				<div class="details">
					<div class="p-image">
						<img alt="product-image"
							src="${pageContext.request.contextPath}/resources/images/mbp-15.jpg" alt="${product.images}"/>
					</div>
					<div class="p-table1">
						<table class="table table-bordered border-light">
							<tr class="table-light">
								<td>Name</td>
								<td>${product.name}</td>
							</tr>
							<tr class="table-light">
								<td>Asset Tag ID</td>
								<td>${product.assetTagId}</td>
							</tr>
							<tr class="table-light">
								<td>Price</td>
								<td>${product.cost}</td>
							</tr>
							<tr class="table-light">
								<td>Brand</td>
								<td>${product.brand.getName()}</td>
							</tr>
						</table>
					</div>
					<div class="p-table2">
						<table class="table table-bordered border-light">
							<tr class="table-light">
								<td>Site</td>
								<td>${product.site}</td>
							</tr>
							<tr class="table-light">
								<td>Location</td>
								<td>${product.location}</td>
							</tr>
							<tr class="table-light">
								<td>Created By</td>
								<td>${product.person}</td>
							</tr>
							<tr class="table-light">
								<td>Created Date</td>
								<td>${product.createdDate}</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="info">
					<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
						<li class="nav-item" role="presentation">
							<button class="nav-link active" id="asset-details-tab"
								data-bs-toggle="pill" data-bs-target="#asset-details"
								type="button" role="tab" aria-controls="asset-details"
								aria-selected="true">Asset Details</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="photos-tab" data-bs-toggle="pill"
								data-bs-target="#photos" type="button" role="tab"
								aria-controls="photos" aria-selected="false">Photos</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="docs-tab" data-bs-toggle="pill"
								data-bs-target="#docs" type="button" role="tab"
								aria-controls="docs" aria-selected="false">Docs</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="deprecation-tab"
								data-bs-toggle="pill" data-bs-target="#deprecation"
								type="button" role="tab" aria-controls="deprecation"
								aria-selected="false">Deprecation</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="warranty-tab" data-bs-toggle="pill"
								data-bs-target="#warranty" type="button" role="tab"
								aria-controls="warranty" aria-selected="false">Warranty</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="maintainance-tab"
								data-bs-toggle="pill" data-bs-target="#maintainance"
								type="button" role="tab" aria-controls="maintainance"
								aria-selected="false">Maintainance</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="contracts-tab" data-bs-toggle="pill"
								data-bs-target="#contracts" type="button" role="tab"
								aria-controls="contracts" aria-selected="false">Contracts</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="events-tab" data-bs-toggle="pill"
								data-bs-target="#events" type="button" role="tab"
								aria-controls="events" aria-selected="false">Events</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="audit-tab" data-bs-toggle="pill"
								data-bs-target="#audit" type="button" role="tab"
								aria-controls="audit" aria-selected="false">Audit</button>
						</li>

					</ul>
					<div class="tab-content" id="pills-tabContent">
						<div class="tab-pane show active" id="asset-details"
							role="tabpanel" aria-labelledby="asset-details-tab" tabindex="0">${product.description}</div>
						<div class="tab-pane" id="photos" role="tabpanel"
							aria-labelledby="photos-tab" tabindex="0">Photos</div>
						<div class="tab-pane" id="docs" role="tabpanel"
							aria-labelledby="docs-tab" tabindex="0">Docs</div>
						<div class="tab-pane" id="deprecation" role="tabpanel"
							aria-labelledby="deprecation-tab" tabindex="0">deprecation</div>
						<div class="tab-pane" id="warranty" role="tabpanel"
							aria-labelledby="warranty-tab" tabindex="0">Warranty</div>
						<div class="tab-pane" id="maintainance" role="tabpanel"
							aria-labelledby="maintainance-tab" tabindex="0">Maint.</div>
						<div class="tab-pane" id="contracts" role="tabpanel"
							aria-labelledby="contracts-tab" tabindex="0">Contracts</div>
						<div class="tab-pane" id="events" role="tabpanel"
							aria-labelledby="events-tab" tabindex="0">Events</div>
						<div class="tab-pane" id="audit" role="tabpanel"
							aria-labelledby="audit-tab" tabindex="0">Audit</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
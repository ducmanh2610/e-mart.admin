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
					<h3>Roles View</h3>
					<div class="button">
						<c:choose>
							<c:when test="${role.id > prev}">
								<a href="${pageContext.request.contextPath}/roles/view/${prev}">
									<button type="button" class="btn btn-secondary">
										<i class="bi bi-caret-left-fill"></i>Prev
									</button>
								</a>
							</c:when>
							<c:otherwise>
								<button type="button" disabled class="btn btn-secondary">
									<i class="bi bi-caret-left-fill"></i>Prev
								</button>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${role.id < next}">
								<a href="${pageContext.request.contextPath}/roles/view/${next}">
									<button type="button" class="btn btn-primary">
										<i class="bi bi-caret-right-fill"></i>Next
									</button>
								</a>
							</c:when>
							<c:otherwise>
								<button type="button" disabled class="btn btn-primary">
									<i class="bi bi-caret-right-fill"></i>Next
								</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="action">
					<b class="text-danger">${role.name}</b>
					<div class="action-btn">
						<button type="button" class="btn btn-outline-primary">
							<i class="bi bi-printer"></i>Print
						</button>
						<a href="${pageContext.request.contextPath}/roles/edit/${role.id}"><button
								class="btn btn-primary" type="button">
								<i class="bi bi-pencil-square"></i>Edit Roles
							</button></a>
						<button class="btn btn-primary dropdown-toggle"
							id="dropdown-action" data-bs-toggle="dropdown"
							aria-expanded="false" type="button">More Actions</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">Delete</a></li>
							<li><a class="dropdown-item" href="#">Export .csv</a></li>
							<li><a class="dropdown-item" href="#">Active/Disable</a></li>
						</ul>
					</div>
				</div>
				<div class="details">
					<div class="p-table1">
						<table class="table table-bordered border-light">
							<tr class="table-light">
								<td>Name</td>
								<td>${role.name}</td>
							</tr>
							<tr class="table-light">
								<td>Created Date</td>
								<td>${role.createdDate}</td>
							</tr>
							<tr class="table-light">
								<td>Status</td>
								<td><c:choose>
										<c:when test="${role.status==1}">
											<span class="badge rounded-pill bg-success">Active</span>
										</c:when>
										<c:otherwise>
											<span class="badge rounded-pill bg-danger">Disabled</span>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
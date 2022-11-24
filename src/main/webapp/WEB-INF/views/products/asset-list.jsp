<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Assets List</title>
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
			<div class="title">
				<h3>Assets List</h3>
				<p class="text-success">${msg}</p>
				<a href="${pageContext.request.contextPath}/products/new-product"><button
						class="btn btn-primary">
						<i class="bi bi-plus-circle"></i><b>Add an Asset</b>
					</button></a>
			</div>
			<table class="table table-success table-striped">
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">Name</th>
						<th scope="col" style="width: 15%;">Images</th>
						<th scope="col">Price</th>
						<th scope="col" style="width: 25%;">Description</th>
						<th scope="col">Created Date</th>
						<th scope="col">Modified Date</th>
						<th scope="col">Status</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listProduct}" var="p">
						<tr>
							<td class="table-light">${p.id}</td>
							<td class="table-light">${p.name}</td>
							<td class="table-light"><img
								src="${pageContext.request.contextPath}/resources/images/mbp-15.jpg"
								alt="${p.images}" /></td>
							<td class="table-light">${p.cost}</td>
							<td class="table-light">${p.description}</td>
							<td class="table-light">${p.createdDate}</td>
							<td class="table-light">${p.modifiedDate}</td>
							<td class="table-light"><c:choose>
									<c:when test="${p.id=='1'}">
										<span class="badge rounded-pill bg-success">Active</span>
									</c:when>
									<c:otherwise>
										<span class="badge rounded-pill bg-danger">Disabled</span>
									</c:otherwise>
								</c:choose></td>
							<td class="table-light"><a
								href="${pageContext.request.contextPath}/products/edit/${p.id}"><i
									class="bi bi-pencil-square"></i></a> <a
								href="${pageContext.request.contextPath}/products/view/${p.id}"><i
									class="bi bi-eye"></i></a><a style="cursor: pointer;"
								onclick="confirm('Are you sure want to delete it?');"><i
									class="bi bi-trash"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
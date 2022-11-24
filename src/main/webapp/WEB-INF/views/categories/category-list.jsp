<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Categories List</title>
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
				<h3>Categories List</h3>
				<p class="text-success">${msg}</p>
				<a href="${pageContext.request.contextPath}/categories/new-category"><button
						class="btn btn-primary">
						<i class="bi bi-plus-circle"></i><b>Add new Category</b>
					</button></a>
			</div>
			<table class="table table-success table-striped">
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">Name</th>
						<th scope="col">Description</th>
						<th scope="col">Created Date</th>
						<th scope="col">Modified Date</th>
						<th scope="col">Status</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categoriesList}" var="c" varStatus="loop">
						<tr>
							<td class="table-light">${loop.index + 1}</td>
							<td class="table-light">${c.name}</td>
							<td class="table-light">${c.description}</td>
							<td class="table-light">${p.createdDate}</td>
							<td class="table-light">${p.modifiedDate}</td>
							<td class="table-light"><c:choose>
									<c:when test="${c.id == 1}">
										<span class="badge rounded-pill bg-success">Active</span>
									</c:when>
									<c:otherwise>
										<span class="badge rounded-pill bg-danger">Disabled</span>
									</c:otherwise>
								</c:choose></td>
							<td class="table-light"><a
								href="${pageContext.request.contextPath}/categories/edit/${c.id}"><i
									class="bi bi-pencil-square"></i></a> <a
								href="${pageContext.request.contextPath}/categories/view/${c.id}"><i
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
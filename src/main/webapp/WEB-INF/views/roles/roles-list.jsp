<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<title>Roles List</title>
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
				<h3>Roles List</h3>
				<p class="text-success">${msg}</p>
				<a href="${pageContext.request.contextPath}/roles/new-role"><button
						class="btn btn-primary">
						<i class="bi bi-plus-circle"></i><b>Add a Role</b>
					</button></a>
			</div>
			<table class="table table-success table-striped">
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">Name</th>
						<th scope="col">Created Date</th>
						<th scope="col">Status</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="id" value="0" />
					<c:forEach items="${rolesList}" var="r" varStatus="loop">
						<tr>
							<td class="table-light">${loop.index + 1}</td>
							<td class="table-light">${r.name}</td>
							<td class="table-light">${r.createdDate}</td>
							<td class="table-light">
							<c:choose>
									<c:when test="${r.status==1}">
										<span class="badge rounded-pill bg-success">Active</span>
									</c:when>
									<c:otherwise>
										<span class="badge rounded-pill bg-danger">Disabled</span>
									</c:otherwise>
								</c:choose></td>
							<td class="table-light">
							<c:if test="${!r.name.equals('admin')}">
							<a
								href="${pageContext.request.contextPath}/roles/edit/${r.id}"><i
									class="bi bi-pencil-square"></i></a> 
							</c:if>
							<a
								href="${pageContext.request.contextPath}/roles/view/${r.id}"><i
									class="bi bi-eye"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
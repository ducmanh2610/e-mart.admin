<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Departments List</title>
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
				<a
					href="${pageContext.request.contextPath}/departments/new-department"><button
						class="btn btn-primary">
						<i class="bi bi-plus-circle"></i><b>Add a Department</b>
					</button></a>
			</div>
			<table class="table table-success table-striped">
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">Name</th>
						<th scope="col" style="width: 15%;">Images</th>
						<th scope="col" style="width: 25%;">Description</th>
						<th scope="col">Created Date</th>
						<th scope="col">Modified Date</th>
						<th scope="col">Members</th>
						<th scope="col">Status</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${departmentList}" var="d" varStatus="loop">
						<tr>
							<td class="table-light">${loop.index + 1}</td>
							<td class="table-light">${d.name}</td>
							<td class="table-light"><img src="${d.imageURL}"
								alt="${d.imageURL}" /></td>
							<td class="table-light">${d.description}</td>
							<td class="table-light">${d.createdDate}</td>
							<td class="table-light">${d.modifiedDate}</td>
							<td class="table-light">${d.userList.size()}</td>
							<td class="table-light"><c:choose>
									<c:when test="${d.status == 1}">
										<span class="badge rounded-pill bg-success">Active</span>
									</c:when>
									<c:otherwise>
										<span class="badge rounded-pill bg-danger">Disabled</span>
									</c:otherwise>
								</c:choose></td>
							<td class="table-light"><a
								href="${pageContext.request.contextPath}/departments/edit/${d.id}"><i
									class="bi bi-pencil-square"></i></a> <a
								href="${pageContext.request.contextPath}/departments/view/${d.id}"><i
									class="bi bi-eye"></i></a><a style="cursor: pointer;"
								onclick="confirmDelete(${d.id})"><i
									class="bi bi-trash"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		function confirmDelete(id) {
			if (confirm('Delete ' + id + ' ?')) {
				window.location.href = window.location.href + '/delete/' + id;
			}
		}
	</script>
	<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
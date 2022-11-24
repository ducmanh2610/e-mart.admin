<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<title>Brands List</title>
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
				<h3>Brands List</h3>
				<p class="text-success">${msg}</p>
				<a href="${pageContext.request.contextPath}/brands/new-brand"><button
						class="btn btn-primary">
						<i class="bi bi-plus-circle"></i><b>Add a Brand</b>
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
						<th scope="col">Created By</th>
						<th scope="col">Status</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="id" value="0" />
					<c:forEach items="${brandList}" var="b" varStatus="loop">
						<tr>
							<td class="table-light">${loop.index + 1}</td>
							<td class="table-light">${b.name}</td>
							<td class="table-light"><img class="img-thumbnail"
								src="${b.imageURL}" alt="${b.imageURL}" /></td>
							<c:set var="descLongString" value="${b.description}" />
							<c:set var="descLength" value="${fn:length(descLongString)}" />
							<c:choose>
								<c:when test="${descLength != 0}">
									<td class="table-light">${fn:substring(descLongString, 0, 15)}...
										<span> <a
											href="${pageContext.request.contextPath}/brands/view/${b.id}">
												Show more </a>
									</span>
									</td>
								</c:when>
								<c:otherwise>
									<td class="table-light">${b.description}</td>
								</c:otherwise>
							</c:choose>
							<td class="table-light">${b.createdDate}</td>
							<td class="table-light">${b.modifiedDate}</td>
							<td class="table-light">${b.createdBy}</td>
							<td class="table-light"><c:choose>
									<c:when test="${b.status==1}">
										<span class="badge rounded-pill bg-success">Active</span>
									</c:when>
									<c:otherwise>
										<span class="badge rounded-pill bg-danger">Disabled</span>
									</c:otherwise>
								</c:choose></td>
							<td class="table-light"><a
								href="${pageContext.request.contextPath}/brands/edit/${b.id}"><i
									class="bi bi-pencil-square"></i></a> <a
								href="${pageContext.request.contextPath}/brands/view/${b.id}"><i
									class="bi bi-eye"></i></a><a style="cursor: pointer;"
								onclick="confirmDelete(${b.id})"><i class="bi bi-trash"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<script type="text/javascript">
				function confirmDelete(id) {
					if(confirm('Delete Brand ' + id + ' ?')) {
						window.location.href = window.location.href + '/delete/' + id;
					}
				}
			</script>
		</div>
	</div>
	<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
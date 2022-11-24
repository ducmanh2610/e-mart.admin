<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sp:url value="/" var="rootpath"></sp:url>

<!DOCTYPE html>
<html>
<head>
<title>${mainSlug}Search:Found ${found} result(s)</title>
<jsp:include page="../layouts/meta.jsp" />
<jsp:include page="../layouts/resources.jsp"></jsp:include>
<link rel="stylesheet" href="${rootpath}/resources/css/style.css" />
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
			<h3>Search: <b class="text-danger">${found}</b></h3>
			<table class="table table-success table-striped">
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">Name</th>
						<th scope="col" style="width: 15%;">Images</th>
						<th scope="col">Created Date</th>
						<th scope="col">Department</th>
						<th scope="col">Role</th>
						<th scope="col">Locked</th>
						<th scope="col">Status</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="id" value="0" />
					<c:forEach items="${userListKeywords}" var="u" varStatus="loop">
						<tr>
							<td class="table-light">${loop.index + 1}</td>
							<td class="table-light">${u.firstName}${empty u.lastName ? '' : ' '.concat(u.lastName)}</td>
							<td class="table-light"><img class="img-thumbnail"
								src="${u.imageURL}" alt="${u.imageURL}" /></td>
							<td class="table-light">${u.createdDate}</td>
							<td class="table-light">${u.department.getName()}</td>
							<td class="table-light">${u.roles.getName()}</td>
							<td class="table-light"><c:choose>
									<c:when test="${u.isAccountNonLocked()}">
										<span style="cursor: pointer;"
											class="badge rounded-pill bg-primary">Non-locked</span>
									</c:when>
									<c:otherwise>
										<span onclick="changeStatus(${u.id})" style="cursor: pointer;"
											class="badge rounded-pill bg-danger">Locked</span>
									</c:otherwise>
								</c:choose></td>
							<td class="table-light"><c:choose>
									<c:when test="${u.status == 1}">
										<span class="badge rounded-pill bg-success">Active</span>
									</c:when>
									<c:otherwise>
										<span class="badge rounded-pill bg-danger">Disabled</span>
									</c:otherwise>
								</c:choose></td>
							<td class="table-light"><c:if
									test="${!u.roles.getName().equals('admin')}">
									<a href="${pageContext.request.contextPath}/users/edit/${u.id}"><i
										class="bi bi-pencil-square"></i></a>
									<a href="${pageContext.request.contextPath}/users/view/${u.id}"><i
										class="bi bi-eye"></i></a>
									<a style="cursor: pointer;" onclick="confirmDelete(${u.id})"><i
										class="bi bi-trash"></i> </a>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
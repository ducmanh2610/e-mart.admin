<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Check Out</title>
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
			<h3>Check Out</h3>
			<table class="table table-success table-striped">
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">Name</th>
						<th scope="col" style="width: 15%;">Images</th>
						<th scope="col">Price</th>
						<th scope="col">Purchase Date</th>
						<th scope="col">Status</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="table-light">1</td>
						<td class="table-light">Macbook Pro 15"</td>
						<td class="table-light"><img
							src="${pageContext.request.contextPath}/resources/images/mbp-15.jpg" /></td>
						<td class="table-light">$1.500</td>
						<td class="table-light">25/12/2022</td>
						<td class="table-light">
							<a onclick="confirm('Import this product ?');"><span class="badge bg-success">Export</span></a>
							<a onclick="confirm('Reject this product ?');"><span class="badge bg-danger">Reject</span></a>
							<a href="${pageContext.request.contextPath}/products/asset-view"><span class="badge  bg-info">View</span></a>
						</td>
					</tr>
					<tr>
						<td class="table-light">1</td>
						<td class="table-light">Macbook Pro 15"</td>
						<td class="table-light"><img
							src="${pageContext.request.contextPath}/resources/images/mbp-15.jpg" /></td>
						<td class="table-light">$1.500</td>
						<td class="table-light">25/12/2022</td>
						<td class="table-light">
							<a onclick="confirm('Import this product ?');"><span class="badge bg-success">Import</span></a>
							<a onclick="confirm('Reject this product ?');"><span class="badge bg-danger">Reject</span></a>
							<a href="${pageContext.request.contextPath}/products/asset-view"><span class="badge  bg-info">View</span></a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
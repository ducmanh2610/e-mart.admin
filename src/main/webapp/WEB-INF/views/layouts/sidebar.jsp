<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<a href="${pageContext.request.contextPath}/home"><img alt="logo"
	src="${pageContext.request.contextPath}/resources/images/wh-manager.png"></a>
<div class="sidebar-wrap">

	<div class="selection">
		<a href="${pageContext.request.contextPath}/home"> <span><i
				class="bi bi-activity"></i></span> <b>Dashboard (Everyone)</b>
		</a>
	</div>

	<div class="selection">
		<a href="${pageContext.request.contextPath}/alerts"><span><i
				class="bi bi-activity"></i></span> <b>Alert (Everyone)</b></a>
	</div>
	<div class="selection">
		<a href="${pageContext.request.contextPath}/alerts"><span><i
				class="bi bi-activity"></i></span> <b>Alert Manager (Manager/Admin)</b></a>
	</div>
	<div class="selection">
		<a href="${pageContext.request.contextPath}/products"><span><i
				class="bi bi-activity"></i></span> <b>Products (User/Manager/Admin)</b></a>
	</div>
	<div class="selection">
		<a href="${pageContext.request.contextPath}/products/check-out"><span><i
				class="bi bi-activity"></i></span> <b>Check Out (Manager/Admin)</b></a>
	</div>
	<div class="selection">
		<a href="${pageContext.request.contextPath}/products/check-in"><span><i
				class="bi bi-activity"></i></span> <b>Check In (Manager/Admin)</b></a>
	</div>
	<div class="selection" style="border-bottom: 2px solid #00000036;">
		<a href="${pageContext.request.contextPath}/products/dispose"><span><i
				class="bi bi-activity"></i></span> <b>Dispose (Admin)</b></a>
	</div>
	<div class="selection" style="border-bottom: 2px solid #00000036;">
		<a href="${pageContext.request.contextPath}/users"><span><i
				class="bi bi-activity"></i></span> <b>User Manager (Admin)</b></a>
	</div>
	<div class="selection" style="border-bottom: 2px solid #00000036;">
		<a href="${pageContext.request.contextPath}/roles"><span><i
				class="bi bi-activity"></i></span> <b>Roles Manager (Admin)</b></a>
	</div>
	<div class="selection" style="border-bottom: 2px solid #00000036;">
		<a href="${pageContext.request.contextPath}/departments"><span><i
				class="bi bi-activity"></i></span> <b>Department Manager (Admin)</b></a>
	</div>
	<div class="selection" style="border-bottom: 2px solid #00000036;">
		<a href="${pageContext.request.contextPath}/brands"><span><i
				class="bi bi-activity"></i></span> <b>Brand Manager (Admin/Manager)</b></a>
	</div>
	<div class="selection" style="border-bottom: 2px solid #00000036;">
		<a href="${pageContext.request.contextPath}/reports"><span><i
				class="bi bi-activity"></i></span> <b>Report (Admin/Accountant/Manager)</b></a>
	</div>
	<div class="selection" style="border-bottom: 2px solid #00000036;">
		<a href="${pageContext.request.contextPath}/categories"><span><i
				class="bi bi-activity"></i></span> <b>Category Manager (Admin/Manager)</b></a>
	</div>
	<div class="selection" style="border-bottom: 2px solid #00000036;">
		<a href="${pageContext.request.contextPath}/persons"><span><i
				class="bi bi-activity"></i></span> <b>Category Manager (Admin/Manager)</b></a>
	</div>
	<div class="selection" style="border-bottom: 2px solid #00000036;">
		<a href="${pageContext.request.contextPath}/activities"><span><i
				class="bi bi-activity"></i></span> <b>System Activities (Admin)</b></a>
	</div>
</div>
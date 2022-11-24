<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="navbar navbar-expand-lg bg-nav">
	<div class="container-fluid">
		<div class="navbar-main">
			<div class="left">
				<div class="logo">
					<a class="navbar-brand home-img"
						href="${pageContext.request.contextPath}/"> <img
						src="${pageContext.request.contextPath}/resources/images/asset-admin.png" />
					</a>
				</div>
				<div class="navbar-link">
					<ul class="navbar-nav mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page"
							href="${pageContext.request.contextPath}/${mainSlug}s">List
								Of ${mainSlug}s</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/${mainSlug}s/new-${mainSlug}">Add
								a ${mainSlug}</a></li>
					</ul>
					<form class="d-flex" role="search" method="GET"
						action="${pageContext.request.contextPath}/${mainSlug}s/search">
						<input class="form-control me-2" type="search"
							placeholder="Enter ${mainSlug} name" aria-label="Search"
							name="keywords">
						<button class="btn btn-outline-light text-light" type="submit">Search</button>
					</form>
				</div>
			</div>
			<div class="right">
				<div class="notify">
					<div class="dropdown">
						<button data-bs-toggle="dropdown" aria-expanded="false"
							type="button" class="btn position-relative dropdown-toggle">
							<i class="bi bi-bell"></i> <span
								class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
								99+ <span class="visually-hidden">unread messages</span>
							</span>
						</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">Action</a></li>
							<li><a class="dropdown-item" href="#">Another action</a></li>
							<li><a class="dropdown-item" href="#">Something else
									here</a></li>
						</ul>
					</div>
				</div>
				<div class="navbar-user">
					<div class="dropdown nav-dropdown dropstart">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							data-bs-toggle="dropdown" aria-expanded="false">
							<span><i class="bi bi-person-circle"></i></span> <span>Welcome</span>
							<b>${username}</b>
						</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">User Profile</a></li>
							<li><a class="dropdown-item" href="#">Edit Profile</a></li>
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/logout">Logout </a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
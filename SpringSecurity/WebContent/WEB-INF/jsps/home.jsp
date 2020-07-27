<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Home</h1>
	<sec:authentication property="principal" var="user" />
	<sec:authorize access="isAuthenticated()">
		<div align="right">
			<pre>Welcome <c:out value="${ user.username }" /> </pre>
			<hr>
			<a href="${pageContext.request.contextPath }/logout">Logout</a>
		</div>
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
		<div align="right">
			<a href="${pageContext.request.contextPath }/sign-up">Sign-Up</a>
		</div>
	</sec:authorize>


	<a href="${pageContext.request.contextPath }/show-report">Show
		Report</a>
	<BR>
	<BR>
	<a href="${pageContext.request.contextPath }/show-users">Show Users</a>
	<BR>
	<BR>
	<hr>
	<a href="${pageContext.request.contextPath }/sign-up">Sign-up</a>
	<BR>
	<BR>


</body>
</html>
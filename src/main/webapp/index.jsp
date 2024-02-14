<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Spring MVC Hibernate - CRUD</title>
<style type="text/css">
body {
	font-family: Arial;
	font-size: 10px;
	margin: 30px;
}

.userTable, .userTable td {
	border-collapse: collapse;
	border: 1px solid #0000FF;
	margin: 2px;
	padding: 2px 2px 2px 10px;
	font-size: 14px;
}

.userTable th {
	font-weight: bold;
	font-size: 14px;
	background-color: #5C82FF;
	color: white;
}

.userLabel {
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
}

a, a:AFTER {
	color: blue;
}

 .error {
        color: red; font-weight: bold;
    }
</style>
</head>
<body>
	<%
    	String redirectURL = "http://localhost:8080/MyWeb/sale";
    	response.sendRedirect(redirectURL);
	%>
	<h2>SpringMVC- Point Of Sale</h2>
	<p style="color: green; font-weight: bold;">
		<a href="<c:url value='/sale' />"> <button>Sale</button></a>
	</p>
</body>
</html>
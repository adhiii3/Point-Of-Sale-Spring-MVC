<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Spring MVC Hibernate - CRUD</title>
<style type="text/css">
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
<script type="text/javascript">
    function deleteUser(itemCode){
        if(confirm('Do you want to delete this Item ?')){
            var url = 'delete/'+itemCode;
            window.location.href = url;
        }
    }
    </script>
</head>
<body>
	
	<h2>EDIT ITEM</h2>
	
	<c:url var="action" value="/items/edit/${item.itemCode}"></c:url>
	<form:form method="post" action="${action}" modelAttribute="item">
		<table>
			<c:if test="${not empty item.id}">
				<tr>
					<td><form:hidden path="id" /></td>
				</tr>
			</c:if>
			<tr>
				<td><form:label path="itemCode" cssClass="userLabel">
						<spring:message code="label.itemCode" />
					</form:label></td>
				<td><form:input path="itemCode" readonly="true"/></td>
				<td><form:errors path="itemCode" cssClass="error"/></td>
			</tr>
			<tr>
				<td><form:label path="price" cssClass="userLabel">
						<spring:message code="label.price" />
					</form:label></td>

				<td><form:input type="number" path="price" /></td>
				<td><form:errors path="price" cssClass="error"/></td>
			</tr>
			<tr>
				<td><form:label path="description" cssClass="userLabel">
						<spring:message code="label.description" />
					</form:label></td>

				<td><form:input path="description" /></td>
				<td><form:errors path="description" cssClass="error"/></td>
			</tr>
			<tr>
				<td><form:label path="type" cssClass="userLabel">
						<spring:message code="label.type" />
					</form:label></td>

				<td><form:input path="type" /></td>
				<td><form:errors path="type" cssClass="error"/></td>
			</tr>
			<tr>
				<td><form:label path="taxable" cssClass="userLabel">
						<spring:message code="label.isTaxable" />
					</form:label></td>

				<td>
				 yes
				 <form:radiobutton path="taxable" value="true"/>  
        		 no
        		 <form:radiobutton path="taxable" value="false"/>  
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message code="label.EditItem"/>" /></td>
			</tr>

		</table>
	</form:form>
</body>
</html>
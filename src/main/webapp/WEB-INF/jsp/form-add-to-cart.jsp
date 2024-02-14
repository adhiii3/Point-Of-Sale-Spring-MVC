<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
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

 .error{
        color: red; font-weight: bold;
    }
</style>
</head>
<body>
	
	<h2>Form Add Item To Cart</h2>
	<c:url var="action" value="/sale/add-cart-proses"></c:url>
	<form:form method="post" action="${action}" modelAttribute="item">
		<table>

			<c:if test="${not empty item.description}">
				<tr>
					<td><form:hidden path="id" readonly="true" size="10"
							disabled="true" /></td>
				</tr>
			</c:if>
			<tr>
				<td><form:label path="itemCode" cssClass="userLabel">
						<spring:message code="label.itemCode" />
					</form:label></td>
				<td><form:input path="itemCode" readonly="true"/></td>
			</tr>

			<tr>
				<td><form:label path="price" cssClass="userLabel">
						<spring:message code="label.price" />
					</form:label></td>

				<td><form:input type="number" path="price" readonly="true"/></td>
			</tr>
			<tr>
				<td><form:label path="description" cssClass="userLabel">
						<spring:message code="label.description" />
					</form:label></td>

				<td><form:input path="description" readonly="true"/></td>
			</tr>

			<tr>
				<td><form:label path="type" cssClass="userLabel">
						<spring:message code="label.type" />
					</form:label></td>

				<td><form:input path="type" readonly="true"/></td>
			</tr>
			<tr>
				<td><form:label path="taxable" cssClass="userLabel">
						<spring:message code="label.isTaxable" />
					</form:label></td>

				<td>
				 yes
				 <form:radiobutton path="taxable" value="true" id="myRadio1" class="radio-taxable"/>  
        		 no
        		 <form:radiobutton path="taxable" value="false" id="myRadio2" class="radio-taxable"/>  
				</td>
			</tr>
			<tr>
				<td><form:label path="quantity" cssClass="userLabel">
						<spring:message code="label.quantity" />
					</form:label></td>

				<td><form:input path="quantity"/></td>
				<td><form:errors path="quantity" cssClass="error"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message code="label.AddItem"/>" /></td>
			</tr>

		</table>
	</form:form>
<script type="text/javascript">
    function deleteItem(itemCode){
    	  if(confirm('Do you want to delete this Item ?')){
              var url = 'item/delete/'+itemCode;
              window.location.href = url;
          }
    }
    document.getElementById("myRadio1").disabled = true;
    document.getElementById("myRadio2").disabled = true;
    </script>
</body>
</html>
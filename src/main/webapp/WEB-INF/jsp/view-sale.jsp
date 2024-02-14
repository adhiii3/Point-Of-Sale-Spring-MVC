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

.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<h2>List of Items</h2>
	<c:if test="${not empty items}">
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<tr>
				<th width="160"><spring:message code="label.itemCode" /></th>
				<th width="60"><spring:message code="label.price" /></th>
				<th width="80"><spring:message code="label.description" /></th>
				<th width="60"><spring:message code="label.type" /></th>
				<th width="60"><spring:message code="label.isTaxable" /></th>
				<th width="100">Action</th>
			</tr>
			<c:forEach items="${items}" var="item">
				<tr>
					<td>${item.itemCode}</a>
					</td>
					<td>${item.price}</td>
					<td>${item.description}</td>
					<td>${item.type}</td>
					<td>${item.taxable}</td>
					<td><a
						href="<c:url value='/sale/form-add-to-cart/${item.itemCode}' />">
							<spring:message code="label.AddToCart" />
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<br>
	<br>
	<c:if test="${not empty cart.getSale().getSaleItem()}">
		<h2>List Cart SaleItems</h2>
		<div class="table-responsive">
		<table class="table table-striped table-sm">
			<tr>
				<th width="160"><spring:message code="label.itemCode" /></th>
				<th width="80"><spring:message code="label.description" /></th>
				<th width="60"><spring:message code="label.type" /></th>
				<th width="60"><spring:message code="label.isTaxable" /></th>
				<th width="60"><spring:message code="label.price" /></th>
				<th width="60"><spring:message code="label.quantity" /></th>
				<th width="60"><spring:message code="label.totalPrice" /></th>
				<th width="100">Action</th>
			</tr>
			<c:forEach items="${cart.getSale().getSaleItem()}" var="saleitem">
				<c:set var="item" value="${saleitem.getItem()}" />
				<tr>
					<td>${item.getItemCode()}</td>
					<td>${item.getDescription()}</td>
					<td>${item.getType()}</td>
					<td>${item.taxable}</td>
					<td>${item.getPrice()}</td>
					<td>${saleitem.getQuantity()}</td>
					<td>${saleitem.getTotalPrice()}</td>
					<td><a href="#"
						onclick="javascript:deleteSaleItem('${item.itemCode}')"> <spring:message
								code="label.Delete" />
					</a></td>
				</tr>
			</c:forEach>
			<c:if test="${not empty cart.getSale().getSaleItem()}">
				<tfoot>

					<tr>
						<td colspan="6" style="text-align: center; border: none;">Total
							Harga</td>
						<td style="text-align: center; border: none;"><c:out
								value="${cart.getSale().getTotalPrice()}"></c:out></td>
					</tr>
					<tr>
						<td colspan="8"><a
							href="<c:url value='/pembayaran' />"> 
							<button type="button" class="btn btn-primary btn-sm" style="width: 100%;"><spring:message code="label.bayarButton" /></button> 
						</a></td>
					</tr>

				</tfoot>
			</c:if>
		</table>
	</c:if>
	</div>

	<script type="text/javascript">
		function deleteSaleItem(itemCode) {
			if (confirm('Do you want to delete this Item ?')) {
				var url = 'sale/delete-saleitem/' + itemCode;
				window.location.href = url;
			}
		}
	</script>
</body>
</html>
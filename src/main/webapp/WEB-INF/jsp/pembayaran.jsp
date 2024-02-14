<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Pembayaran</h1>
	<c:if test="${!empty cart.getSale().getSaleItem()}">
		<div class="table-responsive">
				<p> Sale Number : ${cart.getSale().getSalesNumber()}</p>
				<p> Nama Kasir : ${cart.getSale().getCashier().getName()}</p>
				<p> Sale Date : ${cart.getSale().getTransDate()}</p>
			<table id="table-pembayaran" class="table table-striped table-sm">
				
				<thead>
					<tr>
						<th>Item Code</th>
						<th>Description</th>
						<th>Type</th>
						<th>Taxable</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Total Price</th>
					</tr>
				</thead>
				<c:forEach items="${cart.getSale().getSaleItem()}" var="sale">
					<c:set var="item" value="${sale.getItem()}" />
					<tr>
						<td>${item.getItemCode()}</td>
						<td>${item.getDescription()}</td>
						<td>${item.getType()}</td>
						<td>${item.taxable}</td>
						<td>${item.getPrice()}</td>
						<td>${sale.getQuantity()}</td>
						<td>${sale.getTotalPrice()}</td>
					</tr>
				</c:forEach>
				<tfoot>
					<tr>
						<td colspan="6" style="text-align: center;">Total Harga</td>
						<td><c:out
								value="${cart.getSale().getTotalPrice()}"></c:out></td>
					</tr>
					<tr>
						<td colspan="6" style="text-align: center;">Tax</td>
						<td><c:out
								value="${cart.getSale().getTotalTax()}"></c:out></td>
					</tr>
					<tr>
						<td colspan="6" style="text-align: center;">Total Biaya</td>
						<td><c:out
								value="${cart.getSale().getTotalPricePlusTotalTax()}"></c:out></td>
						<input type="hidden" name="totalPriceHidden"
							value="${cart.getSale().getTotalPricePlusTotalTax()}">
					</tr>
					<c:url var="action" value="/pembayaran"></c:url>
					<form:form method="post" action="${action}" modelAttribute="pembayaran">
					<tr>
						<td colspan="4" style="text-align: center;">Jenis Pembayaran</td>
						<td style="border: none;">
							<form:radiobutton path="type" value="qris"/>
							<form:label path="type">
								<spring:message code="label.QRIS" />
							</form:label>
						</td>
						<td style="border: none;">
							<form:radiobutton path="type" value="cash"/>
							<form:label path="type">
								<spring:message code="label.CASH" />
							</form:label>
						<td style="border: none;">
							<form:input type="number" path="cashInHand" />
						</td>
					</tr>
					<tr>
						<td colspan="8">
							<button type="submit" style="width: 100%;" class="btn btn-primary btn-sm">Bayar</button>
					</tr>
					</form:form>
				</tfoot>
			</table>
	</c:if>

	<c:if test="${empty cart.getSale().getSaleItem()}">
		<h3>Anda Belum Menambahkan Sale Item</h3>
	</c:if>
</body>
</html>
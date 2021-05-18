<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Order Add</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
</head>
<div class="container">
		<h2>Add Order</h2>
		<c:if test="${not empty error}">
                    <div class="alert alert-danger">Error: ${error}</div>
                </c:if>

	<form class="form-horizontal" role="form" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="number">Number:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="number" value="<c:out value="${order.number}"/>">
				</div>
		<label class="control-label col-sm-2" for="placed">Placed:</label>
                  <div class="col-sm-10">
                  <input type="datetime-local" class="form-control" id="placed" name="placed" value="<c:out value="${order.placed}"/>">
                  </div>
            </div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button class="btn btn-primary">Save</button>
					<a href="<c:url value="/customers"/>" class="btn btn-warning">Cancel</a>
				</div>
			</div>
		</form>
	</div>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Users</title>
    <a href="/displayUsers/addUser" class="btn btn-primary">AddUser</a>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
  </head>
            <c:if test="${not empty error}">
				<div class="alert alert-danger">Error: ${error}</div>
			</c:if>
  <body>
  	<div class="page-header">
  		<div class="pull-left">
  			Users of this application!!!

       </div>
  		<div class="clearfix"></div>
  	</div>

        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Password</th>

            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td>
					   <a href="/displayUsers/${user.name}/deleteUser" class="btn btn-primary">DeleteUser</a>

                    </td>
            </tr>
            </c:forEach>
    </table>
    <a href="<c:url value="/customers"/>" class="btn btn-primary">Back to Customers List</a></h2>
  </body>
</html>

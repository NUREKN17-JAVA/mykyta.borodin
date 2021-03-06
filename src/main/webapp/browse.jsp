<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head><title>User managment</title></head>
</html>
<body>
	<form action="<%=request.getContextPath() %>/browse>" method="post">
	<table>
		<tr>
			<th>Id</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Date of birth</th>
		</tr>
		<c:forEach var="user" items="${sessionScope.users })">
		<tr>
			<td><input type="radio" name="id" id="id" value="${user.id}"></td>
			<td>${user.firstName }</td>
			<td>${user.lastName }</td>
			<td>${user.dateOfBirth}</td>
		</tr>
		</c:forEach>
	</table>
	<input type="submit" name="addButton" value="Add"/>
	<input type="submit" name="editButton" value="Edit"/>
	<input type="submit" name="deleteButton" value="Delete"/>
	<input type="submit" name="detailsButton" value="Details"/>
	</form>
</body>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Book vehicle</title>
<style>
    .error {
        color: red; font-weight: bold;
    }
</style>
</head>
<body>
	<div align="center">
		<h1>New/Edit Book Vehicle</h1>
		<form:form action="saveContact" method="post" modelAttribute="contact">
			<table>
				<form:hidden path="id" />
				<tr>
					<td>Name:</td>
					<td><form:input path="name" /></td>
					<td><form:errors path="name" cssClass="error"/></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="email" /></td>
					<td><form:errors path="email" cssClass="error"/></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><form:input path="address" /></td>
					<td><form:errors path="address" cssClass="error"/></td>
				</tr>
				<tr>
					<td>Telephone:</td>
					<td><form:input path="telephone" /></td>
					<td><form:errors path="telephone" cssClass="error"/></td>
				</tr>
				<tr>
					<td>Vehicle:</td>
					<td><form:select path="vehicle">
							<form:options items="${listVehicle}" itemValue="vehicleName"
								itemLabel="vehicleName" />
						</form:select>
				</tr>
				<tr>
					<td>No of days:</td>
					<td><form:input path="days" /></td>
					<td><form:errors path="days" cssClass="error"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
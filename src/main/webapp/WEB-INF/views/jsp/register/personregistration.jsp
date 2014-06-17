<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>BizBuzz-Registration</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta content="utf-8" http-equiv="encoding">

	</head>
	<body>
		<div class="container">
			<div class="header">
				<h1 class="header">BizBuzz-User Registration</h1>
			</div>
			

	 			<div class="body">
					<form:form method="POST" action="personregistration" id="person_registration_form" class="registration_form" modelAttribute="personRegistration">
						<table>
							<tr>
								<td>UserId</td>
								<td><form:input path="userLogin.id" type="text"/></td>
								<td><form:errors path="userLogin.id" /></td>
							</tr>
							<tr>
								<td>Password</td>
								<td><form:input path="userLogin.passwordHash" type="password"/></td>
								<td><form:errors path="userLogin.passwordHash" /></td>
							</tr>
							<tr>
								<td>First Name</td>
								<td><form:input path="person.firstName" type="text"/></td>
								<td><form:errors path="person.firstName" /></td>
							</tr>
							<tr>
								<td>Last Name</td>
								<td><form:input path="person.lastName" type="text"/></td>
								<td><form:errors path="person.lastName" /></td>
							</tr>
							<tr>
								<td>Middle Name</td>
								<td><form:input path="person.middleName" type="text"/></td>
								<td><form:errors path="person.middleName" /></td>
							</tr>
							<tr>
								<td>Phone Number</td>
								<td><form:input path="phoneNumber.contactNumber" type="text"/></td>
								<td><form:errors path="phoneNumber.contactNumber" /></td>
							</tr>
							<tr>
								<td>Email</td>
								<td><form:input path="person.email" type="text"/></td>
								<td><form:errors path="person.email" /></td>
							</tr>
							<tr>
								<td>Company Name</td>
								<td><form:input path="company.companyName" type="text"/></td>
								<td><form:errors path="company.companyName" /></td>
							</tr>
							<tr>
								<td>User Type</td>
								<td>
								<form:select path="person.personRole">
									<option value="None">None</option>
									<c:forEach var="item" items="${personRoleList}">
										<option value="${item}">${item}</option>
									</c:forEach>
								</form:select>
								</td>
							</tr>
						</table>
						<input type="submit" value="Submit" />
					</form:form>
				</div>		
			<div class="footer">
			</div>
		</div>
	</body>
</html>

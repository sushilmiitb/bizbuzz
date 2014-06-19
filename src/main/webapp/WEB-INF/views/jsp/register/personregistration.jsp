<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="register">
	<tiles:putAttribute name="title">
		BizBuzz-Registration
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<form:form method="POST" action="personregistration" id="person_registration_form" class="registration_form" modelAttribute="personRegistration">
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Login Details</h3>
				</div>
				<div class="ui-body ui-body-a">
					<form:label path="userLogin.id" for="register.personregistration.username">Username</form:label>
					<form:input path="userLogin.id" id="register.personregistration.username" type="text" />
					<form:errors path="userLogin.id" class="error"/>
					<form:label path="userLogin.passwordHash" for="register.personregistration.password">Password</form:label>
					<form:input path="userLogin.passwordHash" id="register.personregistration.password" autocomplete="off" type="password" />
					<form:errors path="userLogin.passwordHash" class="error" />
				</div>
			</div>
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Person Details</h3>
				</div>
				<div class="ui-body ui-body-a">
					<form:label path="person.firstName" for="register.personregistration.firstname">First Name</form:label>
					<form:input path="person.firstName" id="register.personregistration.firstname" type="text" />
					<form:errors path="person.firstName" class="error" />
					
					<form:label path="person.lastName" for="register.personregistration.lastname">Last Name</form:label>
					<form:input path="person.lastName" id="register.personregistration.lastname" type="text" />
					<form:errors path="person.lastName" class="error" />
					
					<form:label path="person.middleName" for="register.personregistration.middlename">Middle Name</form:label>
					<form:input path="person.middleName" id="register.personregistration.middlename" type="text" />
					<form:errors path="person.middleName" class="error" />
					
					<form:label path="phoneNumber.contactNumber" for="register.personregistration.contactnumber">Contact Number</form:label>
					<form:input path="phoneNumber.contactNumber" id="register.personregistration.contactnumber" type="tel" />
					<form:errors path="phoneNumber.contactNumber" class="error" />
					
					<form:label path="person.email" for="register.personregistration.email">Username</form:label>
					<form:input path="person.email" id="register.personregistration.email" type="text" />
					<form:errors path="person.email" class="error" />
					
					<form:label path="company.companyName" for="register.personregistration.companyname">Username</form:label>
					<form:input path="company.companyName" id="register.personregistration.companyname" type="text" />
					<form:errors path="company.companyName" class="error" />
					
<%-- 					<form:label path="person.personRole" for="register.personregistration.personrole" class="select">User Type</form:label> --%>
<%-- 					<form:select path="person.personRole" id="register.personregistration.personrole" > --%>
<%-- 						<form:option value="None">None</form:option> --%>
<%-- 						<c:forEach var="item" items="${personRoleList}"> --%>
<%-- 							<form:option value="${item}">${item}</form:option> --%>
<%-- 						</c:forEach> --%>
<%-- 					</form:select> --%>
					
					<form:label path="company.companyRole" for="register.personregistration.companyrole" class="select">User Type</form:label>
					<form:select path="company.companyRole" id="register.personregistration.companyrole" >
						
						<c:forEach var="item" items="${companyRoleList}">
							<form:option value="${item}">${item}</form:option>
						</c:forEach>
					</form:select>
					<input type="submit" value="Register" />
				</div>
			</div>
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>

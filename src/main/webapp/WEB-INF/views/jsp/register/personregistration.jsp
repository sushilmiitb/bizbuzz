<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="register">
	<tiles:putAttribute name="include">
	</tiles:putAttribute>
	<tiles:putAttribute name="header">
		<h1>Bizbuzz- Register</h1>
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		BizBuzz-Registration
	</tiles:putAttribute>
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			$(document).ready(function(){
				var debug = true;
				function emptyErrorDisplay(elem, errorMsg){
					if(debug){
						console.log ("Element Value", elem.val());
					}
					if(!elem.val()){
						$("<span>"+errorMsg+"</span>").insertAfter(elem.parent()).toggleClass("error");
						elem.toggleClass("error");
					}
				}
				
				function noneSelectErrorDisplay(elem, errorMsg){
					if(debug){
						console.log ("Element Value", elem.val());
					}
					if(elem.val()=="None"){
						$("<span>"+errorMsg+"</span>").insertAfter(elem.parent().parent()).toggleClass("error");
					}
				}
				
				function isValidEmailAddress(emailAddress) {
				    var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
				    return pattern.test(emailAddress);
				}
				
				function isValidPhoneNumber(phoneNumber){
					var pattern = /^[0-9-+]+$/;
					return pattern.test(phoneNumber);
				}
				
				function invalidEmailErrorDisplay(elem, errorMsg){
					if(debug){
						console.log ("Element Value", elem.val());
					}
					if(!elem.val()){
						return;
					}
					if(!isValidEmailAddress(elem.val())){
						$("<span>"+errorMsg+"</span>").insertAfter(elem.parent()).toggleClass("error");
						elem.toggleClass("error");
					}
				}
				
				function invalidPhoneErrorDisplay(elem, errorMsg){
					if(debug){
						console.log ("Element Value", elem.val());
					}
					if(!elem.val()){
						return;
					}
					if(!isValidPhoneNumber(elem.val())){
						$("<span>"+errorMsg+"</span>").insertAfter(elem.parent()).toggleClass("error");
						elem.toggleClass("error");
					}
				}
				
				function passwordMisMatchErrorDisplay(repass, pass, errorMsg){
					if(pass.val()!=repass.val()){
						$("<span>"+errorMsg+"</span>").insertAfter(repass.parent()).toggleClass("error");
						repass.toggleClass("error");
						pass.toggleClass("error");
					}
				}
				
				$("#register_personregistration_submit").click(function(event){
					event.preventDefault();
					$("span.error").remove();
					$(".error").toggleClass("error");
					emptyErrorDisplay($('#register_personregistration_username'), "Username cannot be empty");
					emptyErrorDisplay($('#register_personregistration_password'), "Password cannot be empty");
					emptyErrorDisplay($('#register_personregistration_firstname'), "First name cannot be empty");
					emptyErrorDisplay($('#register_personregistration_contactnumber'), "Contact number cannot be empty");
					noneSelectErrorDisplay($('#register_personregistration_companyrole'), "Please select a user type");
					invalidEmailErrorDisplay($('#register_personregistration_email'), "Please enter a valid email address");
					passwordMisMatchErrorDisplay($('#register_personregistration_repassword'), $('#register_personregistration_password'), "Password mismatch");
					invalidPhoneErrorDisplay($('#register_personregistration_contactnumber'), "Please enter a valid phone number");
					if($(".error").length == 0){
						$("#register_personregistration_form").submit();
					}
				});
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<c:url var="post_url" value="/register/personregistration"/>
		<form:form method="POST" action="${post_url}" id="register_personregistration_form" class="form" modelAttribute="personRegistration">
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Login Details</h3>
				</div>
				<div class="ui-body ui-body-a">
					<form:label path="userLogin.id" for="register_personregistration_username">Username</form:label>
					<form:input path="userLogin.id" id="register_personregistration_username" type="text" />
					<form:errors path="userLogin.id" class="error"/>
					<form:label path="userLogin.passwordHash" for="register_personregistration_password">Password</form:label>
					<form:input path="userLogin.passwordHash" id="register_personregistration_password" autocomplete="off" type="password" />
					<form:errors path="userLogin.passwordHash" class="error" />
					<label for="register_personregistration_repassword">Re-enter password</label>
					<input id="register_personregistration_repassword" type="password"/>
				</div>
			</div>
			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-a">
					<h3>Person Details</h3>
				</div>
				<div class="ui-body ui-body-a">
					<form:label path="person.firstName" for="register_personregistration_firstname">First Name</form:label>
					<form:input path="person.firstName" id="register_personregistration_firstname" type="text" />
					<form:errors path="person.firstName" class="error" />
					
					<form:label path="person.lastName" for="register_personregistration_lastname">Last Name</form:label>
					<form:input path="person.lastName" id="register_personregistration_lastname" type="text" />
					<form:errors path="person.lastName" class="error" />
					
					<form:label path="person.middleName" for="register_personregistration_middlename">Middle Name</form:label>
					<form:input path="person.middleName" id="register_personregistration_middlename" type="text" />
					<form:errors path="person.middleName" class="error" />
					
					<form:label path="phoneNumber.contactNumber" for="register_personregistration_contactnumber">Contact Number</form:label>
					<form:input path="phoneNumber.contactNumber" id="register_personregistration_contactnumber" type="tel" />
					<form:errors path="phoneNumber.contactNumber" class="error" />
					
					<form:label path="person.email" for="register_personregistration_email">Email</form:label>
					<form:input path="person.email" id="register_personregistration_email" type="text" />
					<form:errors path="person.email" class="error" />
					
					<form:label path="company.companyName" for="register_personregistration_companyname">Company</form:label>
					<form:input path="company.companyName" id="register_personregistration_companyname" type="text" />
					<form:errors path="company.companyName" class="error" />
					
<%-- 					<form:label path="person.personRole" for="register.personregistration.personrole" class="select">User Type</form:label> --%>
<%-- 					<form:select path="person.personRole" id="register.personregistration.personrole" > --%>
<%-- 						<form:option value="None">None</form:option> --%>
<%-- 						<c:forEach var="item" items="${personRoleList}"> --%>
<%-- 							<form:option value="${item}">${item}</form:option> --%>
<%-- 						</c:forEach> --%>
<%-- 					</form:select> --%>
					
					<form:label path="company.companyRole" for="register_personregistration_companyrole" class="select">User Type</form:label>
					<form:select path="company.companyRole" id="register_personregistration_companyrole" >
						<form:option value="None">None</form:option>
						<c:forEach var="item" items="${companyRoleList}">
							<form:option value="${item}">${item}</form:option>
						</c:forEach>
					</form:select>
					<input type="submit" id="register_personregistration_submit"value="Register" />
				</div>
			</div>
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>

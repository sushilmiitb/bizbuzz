<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="register">
	<tiles:putAttribute name="includejs">
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		BizBuzz-Registration
	</tiles:putAttribute>
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			$(document).ready(function(){
				var debug = true;
				var androiddebug = true;
				function customizeForDevice(){
				    var ua = navigator.userAgent;
				    var checker = {
						iphone: ua.match(/(iPhone|iPod|iPad)/),
						blackberry: ua.match(/BlackBerry/),
						android: ua.match(/Android/)
				    };
				    if (checker.android){
				    	/*if(androiddebug){
				       		$("#debuglist").append("<li>Device is android</li>");
				       	}
				       	var cordovajsurl = "<c:url value='/static/js/cordova/cordova.js' />";
				       	$.getScript(cordovajsurl, function(){
				       		if(androiddebug){
				       			$("#debuglist").append("<li>Loaded cordova.js</li>");
				       		}
				       		var telephoneurl = "<c:url value='/static/js/cordova/plugins/telephonenumber.js' />";
				       		$.getScript(telephoneurl, function(){
					       		if(androiddebug){
					       			$("#debuglist").append("<li>Loaded telephonenumber.js</li>");
					       		}
					       	});	
				       	});*/
				    }
				    else if (checker.iphone){
				       	// $('.idevice-only').show();
				    }
				    else if (checker.blackberry){
				       	// $('.berry-only').show();
				    }
				    else {
				       	// $('.unknown-device').show();
				       	/*console.log("Device is unknown.");
				       	if(androiddebug){
				       		$("#debuglist").append("<li>Device is unknown</li>");
				       	}*/
				    }
				}
				function emptyErrorDisplay(elem, errorMsg){
					if(debug){
						console.log ("Element Value", elem.val());
					}
					if(!elem.val()){
						$("<span>"+errorMsg+"</span>").insertAfter(elem).toggleClass("error");
						elem.toggleClass("error");
					}
				}
				
				function noneSelectErrorDisplay(elem, errorMsg){
					if(debug){
						console.log ("Element Value", elem.val());
					}
					if(elem.val()=="None"){
						$("<span>"+errorMsg+"</span>").insertAfter(elem).toggleClass("error");
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
						$("<span>"+errorMsg+"</span>").insertAfter(elem).toggleClass("error");
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
						$("<span>"+errorMsg+"</span>").insertAfter(elem).toggleClass("error");
						elem.toggleClass("error");
					}
				}
				
				function nonTenDigitPhoneErrorDisplay(elem, errorMsg){
					if(debug){
						console.log ("Element Value", elem.val());
					}
					if(!elem.val()){
						return;
					}
					var n = elem.val().length;
					if(n!=10){
						$("<span>"+errorMsg+"</span>").insertAfter(elem).toggleClass("error");
						elem.toggleClass("error");
					}
				}
				
				function passwordMisMatchErrorDisplay(repass, pass, errorMsg){
					if(pass.val()!=repass.val()){
						$("<span>"+errorMsg+"</span>").insertAfter(repass).toggleClass("error");
						repass.toggleClass("error");
						pass.toggleClass("error");
					}
				}
				
				$("#register_personregistration_submit").click(function(event){
					event.preventDefault();
					$("span.error").remove();
					$(".error").toggleClass("error");
					emptyErrorDisplay($('#register_personregistration_username'), "Phone number cannot be empty");
					invalidPhoneErrorDisplay($('#register_personregistration_username'), "Please enter a valid phone number");
					nonTenDigitPhoneErrorDisplay($('#register_personregistration_username'), "Phone number should be of 10 digits");
					emptyErrorDisplay($('#register_personregistration_password'), "Password cannot be empty");
					emptyErrorDisplay($('#register_personregistration_firstname'), "Name cannot be empty");
					emptyErrorDisplay($('#register_personregistration_companyname'), "Company name cannot be empty");
					//emptyErrorDisplay($('#register_personregistration_contactnumber'), "Contact number cannot be empty");
					noneSelectErrorDisplay($('#register_personregistration_companyrole'), "Please select a user type");
					//invalidEmailErrorDisplay($('#register_personregistration_email'), "Please enter a valid email address");
					passwordMisMatchErrorDisplay($('#register_personregistration_repassword'), $('#register_personregistration_password'), "Password mismatch");
					//invalidPhoneErrorDisplay($('#register_personregistration_contactnumber'), "Please enter a valid phone number");
					if($(".error").length == 0){
						$("#register_personregistration_form").submit();
					}
				});
				customizeForDevice();
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<c:url var="post_url" value="/register/personregistration"/>
	<div class="debug">
		<ol id="debuglist">
		</ol>
	</div>
	<div class="container" role="main">
		<div class="row" id="maincontent">
			<div class="col-xs-12 col-md-12 col">
				<div class="panel panel-primary">
					<div class="panel-heading">Register</div>
					<div class="panel-body">
						<form:form class="form-signin form" role="form" method="POST" action="${post_url}" id="register_personregistration_form" modelAttribute="personRegistration">
							<form:label path="userLogin.id" for="register_personregistration_username">Phone Number</form:label>
							<form:input path="userLogin.id" class="form-control" id="register_personregistration_username" placeholder="10 digit number" type="text" />
							<form:errors path="userLogin.id" class="error"/>
							<form:label path="userLogin.passwordHash" for="register_personregistration_password">Password</form:label>
							<form:input path="userLogin.passwordHash" class="form-control" placeholder="Type Password" id="register_personregistration_password" autocomplete="off" type="password" />
							<form:errors path="userLogin.passwordHash" class="error" />
							<label for="register_personregistration_repassword">Re-enter password</label>
							<input id="register_personregistration_repassword" class="form-control"	placeholder="Retype Password" type="password" />
							
							<h3>Details</h3>
							
							<form:label path="person.firstName" for="register_personregistration_firstname">Name</form:label>
							<form:input path="person.firstName" class="form-control" placeholder="Enter your name" id="register_personregistration_firstname" type="text" />
							<form:errors path="person.firstName" class="error" />
							
<%-- 							<form:label path="person.lastName" for="register_personregistration_lastname">Last Name</form:label> --%>
<%-- 							<form:input path="person.lastName" class="form-control"	placeholder="Last Name" id="register_personregistration_lastname" type="text" /> --%>
<%-- 							<form:errors path="person.lastName" class="error" /> --%>
							
<%-- 							<form:label path="person.middleName" for="register_personregistration_middlename">Middle Name</form:label> --%>
<%-- 							<form:input path="person.middleName" class="form-control" placeholder="Middle name" id="register_personregistration_middlename" type="text" /> --%>
<%-- 							<form:errors path="person.middleName" class="error" /> --%>
							
<%-- 							<form:label path="phoneNumber.contactNumber" for="register_personregistration_contactnumber">Contact Number</form:label> --%>
<%-- 							<form:input path="phoneNumber.contactNumber" class="form-control" placeholder="Phone number" id="register_personregistration_contactnumber" type="tel" /> --%>
<%-- 							<form:errors path="phoneNumber.contactNumber" class="error" /> --%>
							
<%-- 							<form:label path="person.email" for="register_personregistration_email">Email</form:label> --%>
<%-- 							<form:input path="person.email" class="form-control" placeholder="Email" id="register_personregistration_email" type="text" /> --%>
<%-- 							<form:errors path="person.email" class="error" /> --%>
							
							<form:label path="company.companyName" for="register_personregistration_companyname">Company/Shop Name</form:label>
							<form:input path="company.companyName" class="form-control"	placeholder="Company name" id="register_personregistration_companyname" type="text" />
							<form:errors path="company.companyName" class="error" />
							
		<%-- 					<form:label path="person.personRole" for="register.personregistration.personrole" class="select">User Type</form:label> --%>
		<%-- 					<form:select path="person.personRole" id="register.personregistration.personrole" > --%>
		<%-- 						<form:option value="None">None</form:option> --%>
		<%-- 						<c:forEach var="item" items="${personRoleList}"> --%>
		<%-- 							<form:option value="${item}">${item}</form:option> --%>
		<%-- 						</c:forEach> --%>
		<%-- 					</form:select> --%>
							
							<form:label path="company.companyRole" for="register_personregistration_companyrole" class="select">User Type</form:label>
							<form:select path="company.companyRole" class="form-control" id="register_personregistration_companyrole" >
								<form:option value="None">None</form:option>
								<c:forEach var="item" items="${companyRoleList}">
									<form:option value="${item}">${item}</form:option>
								</c:forEach>
							</form:select>
							<br/>
							<input type="submit" class="btn btn-md btn-primary btn-block" id="register_personregistration_submit"value="Register" />
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

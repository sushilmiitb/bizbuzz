<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="register">
	<tiles:putAttribute name="include">
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		BizBuzz-Login
	</tiles:putAttribute>
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			$(document).ready(function(){
				var debug = true;			
				
				initialize();
				function initialize(){
					$("#user_remember_me").attr("checked", true);
				}
		/*		
				$("#register_login_submit").click(function(event){
					event.preventDefault();
					$("span.error").remove();
					$(".error").toggleClass("error");
					if($(".error").length == 0){
						$("#register_login_form").submit();
					}
				});
			*/
//  get ten digits from the user input of the phone number field               =====================================
/*				$('#login_form').submit(function(){
					var phonenumber = getTenDigitPhoneNumber($('#j_username').val());
					if(phonenumber==false) alert("Require ten digit number..");
					else alert("Ten Digit Number:" +phonenumber);
					
				//$('input[type=submit]', this).attr('disabled', 'disabled');	
			    //$('#j_username').val($('select[id=register_login_countrycode]').val()+phonenumber);
				});
*/			
//   Uncomment below code if you want to add country code in the phone number textField on select PH No. from the dropdown
/* 			
			    $('#register_login_countrycode').change(function(){
					   $('#j_username').val($(this).val()); 
				});
				$('#j_username').val($('select[id=register_login_countrycode]').val());
			
		*/		
				   $("#j_username").intlTelInput();		   
				   
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
					<div class="panel panel-primary">
						<div class="panel-heading">Sign In</div>
						<div class="panel-body">
							<form class="form-signin" role="form" id="login_form" action="<c:url value='/j_spring_security_check' />" method="post" accept-charset="UTF-8">
						  <label for="phonenumber">Phone number</label>	
                                <br/>
                                   <input class="form-control" id="j_username" name="j_username" placeholder="Phone number" type="tel" required autofocus />     
                          <!--       <select name="numericCode" id="register_login_countrycode" class="form-control" > 
								     <c:forEach var="item" items="${countryCodeList}">
									   <c:choose>
						 			 	<c:when test="${item.numericCode == '+91'}">
									 		<option value="${item.numericCode}"  selected="selected">${item.countryName} ${item.numericCode}</option>
										 </c:when>
    									 <c:otherwise>
    									 	<option value="${item.numericCode}" >${item.countryName} ${item.numericCode}</option>
    									 </c:otherwise>
    								   </c:choose>
								     </c:forEach>
                                   </select>
                                 
                                  	<input class="form-control" id="j_username" type="text" name="j_username" placeholder="Phone number" required autofocus />
                               -->
                               <br/>
                               	
								 <label for="password">Password</label> 
								<input class="form-control" id="j_password" type="password" name="j_password" placeholder="Password" required />
								<c:if test="${not empty error}">
									<span class="error">Username or password doesn't match the records</span>
								</c:if>
								<!--- <label class="checkbox"> -->
								<input id="user_remember_me" type="checkbox" name="_spring_security_remember_me" value="1" /> Remember Me <br/>
								<button class="btn btn-md btn-primary btn-block" id="sign_in" type="submit">Sign	in</button>
							</form>
							<form action="<c:url value='/register/personregistration' /> ">
								<br/>
								<span>New User?</span>
								<br/>
								<button class="btn btn-md btn-success btn-block" id="newuser" >Register</button>
							</form>			
							<a href="#" id="forgotpass">Forgot Password?</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

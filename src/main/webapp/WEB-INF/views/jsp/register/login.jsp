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
			
				$("#register_login_submit").click(function(event){
					event.preventDefault();
					$("span.error").remove();
					$(".error").toggleClass("error");
					if($(".error").length == 0){
						$("#register_login_form").submit();
					}
				});
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="col-xs-12 col-md-12 col">
					<div class="panel panel-primary">
						<div class="panel-heading">Sign In</div>
						<div class="panel-body">
							<form class="form-signin" role="form" action="<c:url value='/j_spring_security_check' />" method="post" accept-charset="UTF-8">
								<input class="form-control" id="j_username" type="text" name="j_username" placeholder="Phone number" required autofocus /> 
								<input class="form-control" id="j_password" type="password" name="j_password" placeholder="Password" required />
								<c:if test="${not empty error}">
									<span class="error">Username or password doesn't match the records</span>
								</c:if>
								<!--- <label class="checkbox"> -->
								<input id="user_remember_me" type="checkbox" name="_spring_security_remember_me" value="1" /> Remember Me <br/>
								<button class="btn btn-md btn-primary btn-block" type="submit">Sign	in</button>
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

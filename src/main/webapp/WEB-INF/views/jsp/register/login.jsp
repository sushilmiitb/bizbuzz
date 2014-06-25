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
		<form action="<c:url value='/j_spring_security_check' />" method="post" accept-charset="UTF-8">
			<input id="j_username" type="text" name="j_username" placeholder="Username" />
			<input id="j_password" type="password" name="j_password" placeholder="Password" />
			<c:if test="${not empty error}">
				<span class="error">Username or password doesn't match the records</span>
			</c:if>
			<input id="user_remember_me" type="checkbox" name="j_spring_security_check" value="1" />
			<label class="string optional" for="user_remember_me">Remember me</label> 
			<br/>
			<a href="#" > Forgot Password? </a><br />
			<input class="btn btn-default" type="submit" name="commit" value="Sign In" />
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>

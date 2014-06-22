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
		<form method="POST" action="login" id="register_login_form" class="form">
			<div class="ui-corner-all custom-corners">
				<div class="ui-body ui-body-a">
					<label for="register_login_username">Username</label>
					<input id="register_login_username" type="text" />
					<label for="register_login_password">Password</label>
					<input id="register_login_password" autocomplete="off" type="password" />
					<input type="submit" id="register_login_submit" value="Login" />
				</div>
			</div>
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>

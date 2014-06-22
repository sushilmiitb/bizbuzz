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
    <%--
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
     --%>
     <form action="j_spring_security_check" method="post"
                  accept-charset="UTF-8">
                  <input id="j_username" style="margin-bottom: 15px;" type="text"
                    name="j_username" size="30" placeholder="Username" /> <input
                    id="j_password" style="margin-bottom: 15px;" type="password"
                    name="j_password" size="30" placeholder="Password" /> <input
                    id="user_remember_me" style="float: left; margin-right: 10px;"
                    type="checkbox" name="j_spring_security_check" value="1" /> <label
                    class="string optional" for="user_remember_me">Remember
                    me</label> <br/><a href="#" > Forgot Password? </a><br /><input class="btn btn-default"
                    style="clear: left; width: 100%; height: 32px; font-size: 13px;"
                    type="submit" name="commit" value="Sign In" />
                </form>
	</tiles:putAttribute>
</tiles:insertDefinition>

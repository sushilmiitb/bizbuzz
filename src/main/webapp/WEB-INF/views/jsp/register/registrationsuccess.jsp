<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="register">
	<tiles:putAttribute name="include">
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		BizBuzz-Registration
	</tiles:putAttribute>
	<tiles:putAttribute name="customJsCode">
		<script>
			$(document).ready(function(){
				/***************************************************************************************
				* code for mobile devices
				***************************************************************************************/
				var debug = false;
				var ua = navigator.userAgent.toLowerCase();   
				var isAndroid = ua.indexOf("android") > -1; //&& ua.indexOf("mobile");
				var baseStaticUrl = "/bizbuzz/static";
				if(isAndroid) {
					if(debug)
						alert("This is android platform.");
					function fail(error){
					}
					function onDataRead(args){
						var data = args.data;
						if(data == ""){
							navigator.splashscreen.hide();
						}
						else{
							var configObj = JSON.parse(data);
							if(configObj.username !== undefined && configObj.password !== undefined){
								var form = document.createElement("form");
								var inputUser = document.createElement("input");
								inputUser.name = "j_username";
								inputUser.value = configObj.username;
								var inputPass = document.createElement("input");
								inputPass.name = "j_password";
								inputPass.value = configObj.password;
								form.action = configObj.url+"/j_spring_security_check";
								form.method = "POST";
								form.appendChild(inputUser);
								form.appendChild(inputPass);
								form.submit();
							}
							else{
								navigator.splashscreen.hide();
							}
						}
					}
					
					function autoLogin(){	
						MobileFileSystem.readAFileAsTextShortcut({path: ["config"], fileName: "properties.txt", success: onDataRead, fail:fail});
						
					}
					
					function onCordovaLoad(){
						if(debug)
							alert("cordova Loaded");
						document.addEventListener("deviceready", onDeviceReady, false);
					    // device APIs are available
					    //
					    function onDeviceReady() {
					        autoLogin();
					    }	
					}
					loadjsfile(baseStaticUrl+"/js/cordova/cordova-combined-<spring:message code="git_hash" />.min.js", onCordovaLoad);
				}
				/*** otherwise ***/
				else{
				}
				
				function loadjsfile(filename, onload){	
					if(debug)
						alert("mobileadaptation.js/loadjsfile:filename->"+filename);
					var script=document.createElement('script');
					script.setAttribute("type","text/javascript");
				
					script.setAttribute("src", filename);
					if (typeof script!="undefined")
						document.getElementsByTagName("head")[0].appendChild(script);
				
					if (script.readyState){  //IE
						script.onreadystatechange = function(){
							if (script.readyState == "loaded" || script.readyState == "complete"){
								script.onreadystatechange = null;
								//do your stuff
							}
						};
					} else {  //Others
						script.onload = onload;
					}
				}			
	
				/***************************************************************************************
				* code for mobile devices ends
				***************************************************************************************/
	
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="col-xs-12 col-md-12 col">
					<div class="panel panel-primary">
						<div class="panel-body">
							<p style="text-align: center;">Registration Successful</p>
							<br />
							<p style="text-align: center;">
								<a href="<c:url value='/login' />">Login</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
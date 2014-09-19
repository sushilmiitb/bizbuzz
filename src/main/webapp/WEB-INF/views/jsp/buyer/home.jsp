<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="buyer">
	<tiles:putAttribute name="includejs">
	</tiles:putAttribute>

	<tiles:putAttribute name="title">
		BizBuzz-Home
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		$(document).ready(function(){
			/***************************************************************************************
			* code for mobile devices
			***************************************************************************************/
			var ua = navigator.userAgent.toLowerCase();   
			var isAndroid = ua.indexOf("android") > -1; //&& ua.indexOf("mobile");
			if(isAndroid) {
				if(debug)
					alert("This is android platform.");
				
				function onCordovaLoad(){
					if(debug)
						alert("cordova Loaded");
						navigator.splashscreen.hide();
				}
				loadjsfile(baseStaticUrl+"/js/cordova/cordova.js", onCordovaLoad);
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
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">What do you want to do?</div>
						<div class="panel-body">
							<ul class="nav nav-pills nav-stacked n">
								<li><a href="<c:url value="/buyer/viewcategory/category/-1"/>"><span
										class="glyphicon glyphicon-search"></span> View Products </a></li>
								<li><a href="<c:url value="/buyer/viewcontacts"/>"><span class="glyphicon glyphicon-cog"></span>
										Contacts </a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
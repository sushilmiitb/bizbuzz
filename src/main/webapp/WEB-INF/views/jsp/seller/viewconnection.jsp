<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		BizBuzz-Groups
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
		$(document).ready(function() {
			alert("Document.ready");

			//	----------------------------------------------------------------
		
			//	Dynamically loading cordova.js              -------------------------------------------------------------
		
			function loadjsfile(filename, onload){	
				var script=document.createElement('script');
				script.setAttribute("type","text/javascript");
		
				script.setAttribute("src", filename);
				if (typeof script!="undefined")
					document.getElementsByTagName("head")[0].appendChild(script);
		
				if (script.readyState){  //IE
					script.onreadystatechange = function(){
						if (script.readyState == "loaded" || script.readyState == "complete"){
							script.onreadystatechange = null;
							alert("successfully loaded.");
						}
					};
				} else {  //Others
					script.onload = onload;
				}
		
			}
		
			function allContactJs(){
		
		
				loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/Contact.js'/>",function(){});
				loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactAddress.js'/>",function(){});
				loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactError.js'/>",function(){});
				loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactField.js'/>",function(){});
				loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactFieldType.js'/>",function(){});
				loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactFindOptions.js'/>",function(){});
				loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactName.js'/>",function(){});
				loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactOrganization.js'/>",function(){});
				loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/contacts.js'/>",function(){});
				loadjsfile("<c:url value='/static/js/cordova/cordova_plugins.js'/>",function(){
						loadjsfile("<c:url value='/static/js/cordova/cordova.js'/>", function(){});});
			}
		
			var ua = navigator.userAgent.toLowerCase();   
			var isAndroid = ua.indexOf("android") > -1; //&& ua.indexOf("mobile");
			if(isAndroid) {
				alert("This is android platform.");
							//As Per Native Code----------------------------------------------------------------
				var contact = {
					// Application Constructor
					initialize: function() {
						alert("Inside contact initialization");
						this.bindEvents();
					},
					// Bind Event Listeners
					//
					// Bind any events that are required on startup. Common events are:
					// 'load', 'deviceready', 'offline', and 'online'.
					bindEvents: function() {
						document.addEventListener('deviceready', this.onDeviceReady(), false);
					},
					onDeviceReady: function() {
				
						var options = new ContactFindOptions();
						options.filter = "";
						options.multiple = true;
						var filter = ["displayName", "phoneNumber"];
						navigator.contacts.find(filter, this.onSuccess , this.onError , options);
				
					},
					onSuccess: function(contacts){
						alert("contact length: " + contacts.length);	
						var total ="";
						for (var i = 0; i < contacts.length; i++) {
							alert("contact:"+contacts[i].displayName);
							/*for (var j = 0; j < contacts[i].phoneNumbers.length; j++) {
								total =  total + "Contact Name: "         + contacts[i].displayName + "\n" 
								+ "Value: " + contacts[i].phoneNumbers[j].value + "\n";
							} */                                          
						}
				
						alert("total: "+total);
					},
					onError: function(error){
						alert("Contacts Error : "+error);
					}
				};
				
				function onCordovaLoad(){
					alert("cordova Loaded");
					$('#select_phonebook_button').click(function(event) {
						event.preventDefault();
						contact.initialize();
					});
				}
				
				loadjsfile("<c:url value='/static/js/cordova/cordova.js'/>", onCordovaLoad);
			}
			//----------------------------------------------------------------------------------
		
		
			$('#seller_viewconnection_form').submit(function(event) {
				var json = { "userId" : $('#seller_viewconnection_phonenumber').val(),
						"groupId" : $('#seller_viewconnection_privategroupoption').val()
				};
				console.log("test", JSON.stringify(json));
				$.ajax({
					url: $("#seller_viewconnection_form").attr( "action"),
					data: JSON.stringify(json),
					type: "POST",
		
					beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(data) {
					var baseUrl = "/seller/viewconnection/";
					var baseDeleteUrl = "/seller/deleteconnection/";
					var baseGroupUrl = "/seller/viewgroup/";
					var respContent="";
		
					var linkElement = document.createElement('a');
					$(linkElement).addClass("list-group-item");
					$(linkElement).attr("href", "<c:url value='/seller/viewconnection/'/>"+data.id);
					var divOut = document.createElement('div');
					$(divOut).addClass('row');
					var divInner = document.createElement('div');
					$(divInner).addClass('col-xs-4 col-sm-4 col-md-4 col-lg-4');
					$(divInner).html(data.firstName + " " + data.middleName + " " + data.lastName);
					$(divOut).append(divInner);
		
					var divInner = document.createElement('div');
					$(divInner).addClass('col-xs-4 col-sm-4 col-md-4 col-lg-4');
					$(divInner).html(data.phoneNumber);
					$(divOut).append(divInner);
		
					var divInner = document.createElement('div');
					$(divInner).addClass('col-xs-4 col-sm-4 col-md-4 col-lg-4');
					$(divInner).html(data.groupName);
					$(divOut).append(divInner);
		
					$(linkElement).append(divOut);
		
					$(linkElement).insertAfter($(".list-group-item").filter(".heading").first());
					$(".loader").remove();
				},
				error: function(){
					$(".loader").remove();
				}
				}); 
				event.preventDefault();
			});
		});
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="post_url" value="/seller/addconnection" />
		<c:url var="base_delete_url" value="/seller/deleteconnection/" />
		<c:url var="base_url" value="/seller/viewconnection/" />
		<c:url var="base_group_url" value="/seller/viewgroup/" />

		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="hidden-xs hidden-sm col-md-1 col-lg-2"></div>
				<div class="col-xs-12 col-sm-12 col-md-10 col-lg-8">
					<div class="panel panel-primary">
						<div class="panel-heading center-align-text">Connections</div>
						<div class="panel-body">
							<legend>Add new connection</legend>
							<form method="POST" role="form" class="form-signin"
								action="${post_url}" id="seller_viewconnection_form"
								class="form">
								<label for="seller_viewconnection_phonenumber">Phone
									number of the person</label> <input class="form-control"
									id="seller_viewconnection_phonenumber" type="text"
									placeholder="10 digit phone number" /> <label
									for="seller_viewconnection_groupname">Group Name</label> <select
									class="form-control"
									id="seller_viewconnection_privategroupoption">
									<c:forEach var="item" items="${privateGroupList}">
										<option value="${item.id}"
											<c:if test="${item.privateGroupName == 'General' }">
											selected="true" 
										</c:if>>${item.privateGroupName}</option>
									</c:forEach>
								</select> <input id="seller_viewconnection_connect" type="submit"
									class="btn btn-primary btn-block" value="Connect" />
									<br/>
									<input id="select_phonebook_button" type="submit"
									class="btn btn-primary btn-block" value="Phonebook" />
							</form>
							<br />
							<div class="list-group">
								<a href="#" class="list-group-item active">
							      <h4 class="list-group-item-heading">
							         Existing Connections
							      </h4>
							   </a>
							   <a href="#" class="list-group-item heading">
										<div class="row">
											<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
												<h4 class="list-group-item-heading">Name</h4>
											</div>
											<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
												<h4 class="list-group-item-heading">Phone Number</h4>
											</div>
											<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
												<h4 class="list-group-item-heading">Group</h4>
											</div>
										</div>
								</a>
								<c:forEach items="${connectionList}" var="item" >
									<a href="${base_url}${item.toParty.id}" class="list-group-item">
										<div class="row">
											<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
												${item.toParty.firstName} ${item.toParty.middleName} ${item.toParty.lastName}
											</div>
											<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
												${item.toParty.userId.id}
											</div>
											<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
												${item.fromParty.privateGroupName }
											</div>
										</div>
									</a>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="hidden-xs hidden-sm col-md-1 col-lg-2"></div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
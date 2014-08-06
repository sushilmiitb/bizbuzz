<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		BizBuzz-Groups
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			$(document).ready(function() {
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
			        	
			        	respContent+="<tr>";
							respContent+="<td>";
								respContent+="<a href="+baseUrl+data.id+">"+data.firstName+" "+data.middleName+" "+data.lastName+"</a>";
							respContent+="</td>";
							respContent+="<td>";
								respContent+="<a href="+baseUrl+data.id+">"+data.id+"</a>";
							respContent+="</td>";
							respContent+="<td>";
								respContent+="<a href="+baseGroupUrl+data.groupId+">"+data.groupName+"</a>";
							respContent+="</td>";

			            $(respContent).insertBefore($(".displayrow").first());
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
							</form>
							<br />
							<div class="list-group">
								<a href="#" class="list-group-item active">
							      <h4 class="list-group-item-heading">
							         Existing Connections
							      </h4>
							   </a>
							   <a href="#" class="list-group-item">
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
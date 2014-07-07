<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		BizBuzz-Groups
	</tiles:putAttribute>
	
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			$(document).ready(function() {
			  $('#seller_viewgroup_form').submit(function(event) {
			  	var json = { "privateGroupName" : $('#seller_viewgroup_groupname').val(),
			  				 "errors" : []	
			  				};
			    console.log("test", JSON.stringify(json));
			    $.ajax({
			        url: $("#seller_viewgroup_form").attr( "action"),
			        data: JSON.stringify(json),
			        type: "POST",
			         
			        beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
			        },
			        success: function(data) {
			        	var respContent = "";
			            if(data.errors.length){
			            	for(var i=0; i<data.errors.length;i++){
			            		respContent += "<span class='error'>" + data.errors[i] +"</span>";
			            		$(respContent).insertBefore($(".group").first());
			            	}
			            	return;
			            }
			            respContent += "<span class='group'>" + data.privateGroupName +"</span>";
			            $(respContent).insertBefore($(".group").first());  
			        }
			    }); 
			    event.preventDefault();
			  });
			});
		</script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<c:url var="post_url" value="/seller/editgroup/${privateGroup.privateGroupName}" />
		<form:form method="POST" action="${post_url}" id="seller_viewsinglegroup_form" class="form" modelAttribute="privateGroup">
			<div class="ui-corner-all custom-corners">
				<div class="ui-body ui-body-a">
					<form:label path="privateGroupName" for="seller_viewsinglegroup_groupname">Group Name</form:label>
					<form:input path="privateGroupName" id="seller_viewsinglegroup_groupname" type="text"/>
					<input id="seller_viewsinglegroup_edit" type="submit" value="Change Group Name" />
				</div>
			</div>
		</form:form>
		<c:url var="delete_url" value="/seller/deletegroup/${privateGroup.privateGroupName}" />
		<form:form method="GET" action="${delete_url}" id="seller_viewsinglegroup_deleteform">
			<div class="ui-corner-all custom-corners">
				<div class="ui-body ui-body-a">
					<input id="seller_viewsinglegroup_delete" type="submit" value="Delete Group" />
				</div>
			</div>
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
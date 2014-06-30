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
			  	var json = { "privateGroupName" : $('#seller_viewgroup_groupname').val()};
			       
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
			            console.log("test", data);
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
		<c:url var="post_url" value="/seller/addgroup"/>
		<form:form method="POST" action="${post_url}" id="seller_viewgroup_form" class="form" modelAttribute="privateGroupForm">
			<div class="ui-corner-all custom-corners">
				<div class="ui-body ui-body-a">
					<form:label path="privateGroupName" for="seller_viewgroup_groupname">Group Name</form:label>
					<form:input path="privateGroupName" id="seller_viewgroup_groupname" type="text"/>
					<input id="seller_viewgroup_create" type="submit" value="Create" />
				</div>
			</div>
		</form:form>
		<div class="ui-corner-all custom-corners">
			<div class="ui-body ui-body-a">
				<c:forEach items="${privateGroups}" var="item" >
					<span class="group">${item.privateGroupName}</span>
				</c:forEach>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
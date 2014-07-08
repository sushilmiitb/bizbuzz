<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		BizBuzz-Groups
	</tiles:putAttribute>
	
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
		</script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<c:url var="post_url" value="/seller/editgroup/${privateGroup.id}" />
		<form:form method="POST" action="${post_url}" id="seller_viewsinglegroup_form" class="form" modelAttribute="privateGroup">
			<div class="ui-corner-all custom-corners">
				<div class="ui-body ui-body-a">
					<form:label path="privateGroupName" for="seller_viewsinglegroup_groupname">Group Name</form:label>
					<form:input path="privateGroupName" id="seller_viewsinglegroup_groupname" type="text"/>
					<input id="seller_viewsinglegroup_edit" type="submit" value="Change Group Name" />
				</div>
			</div>
		</form:form>
		<c:url var="delete_url" value="/seller/deletegroup/${privateGroup.id}" />
		<form:form method="GET" action="${delete_url}" id="seller_viewsinglegroup_deleteform">
			<div class="ui-corner-all custom-corners">
				<div class="ui-body ui-body-a">
					<input id="seller_viewsinglegroup_delete" type="submit" value="Delete Group" />
				</div>
			</div>
		</form:form>
		<c:url var="base_url" value="/seller/viewconnection/"/>
		<table id="seller_viewsinglegroup_displaytable">
			<c:forEach items="${groupMembers}" var="item" >
				<tr class="displayrow">
					<td>
						<a href="${base_url}${item.id}">Link</a>
					</td>
					<td>
						${item.firstName} ${item.middleName} ${item.lastName}
					</td>
				</tr>
			</c:forEach>
		</table>
	</tiles:putAttribute>
</tiles:insertDefinition>
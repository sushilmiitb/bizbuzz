<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		BizBuzz-Groups
	</tiles:putAttribute>
	
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			$(document).ready(function() {
			  $('#seller_viewsingleconnection_editgroupform').submit(function(event) {
			  	var json = { "personId" : ${buyer.id},
			  				 "groupId" : $('#seller_viewsingleconnection_changegroupoption').val()
			  				};
			    console.log("test", JSON.stringify(json));
			    $.ajax({
			        url: $("#seller_viewsingleconnection_editgroupform").attr( "action"),
			        data: JSON.stringify(json),
			        type: "POST",
			         
			        beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
			        },
			        success: function(data) {
			        }
			    }); 
			    event.preventDefault();
			  });
			});
		</script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		
		<h1>Person Details</h1>
		
		<table id="seller_viewsingleconnection_displaytable">
			<tr class="displayrow">
				<td>${buyer.firstName}</td>
				<td>${buyer.middleName}</td>
				<td>${buyer.lastName}</td>
			</tr>
		</table>
		
		<c:url var="base_delete_url" value="/seller/deleteconnection/"/>
		<a href="${base_delete_url}${buyer.id}">Delete Connection</a>
		
		<c:url var="base_group_url" value="/seller/viewgroup/" />
		<h1>Group</h1>
		<a href="${base_group_url}${privateGroup.id}">${privateGroup.privateGroupName}</a>
		
		<c:url var="edit_group_url" value="/seller/editconnection/changegroup" />
		<form action="${edit_group_url}" id="seller_viewsingleconnection_editgroupform" class="form" >
			<select id="seller_viewsingleconnection_changegroupoption">
				<option value="-1">None</option>
				<c:forEach var="item" items="${privateGroupList}">
					<option value="${item.id}">${item.privateGroupName}</option>
				</c:forEach>
			</select>
			<input type="submit" id="seller_viewsingleconnection_submiteditgroup" value="Change Group" />
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>
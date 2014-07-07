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
			  	var json = { "phoneNumber" : $('#seller_viewconnection_phonenumber').val(),
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
			        	var respContent = "";
			        	var baseUrl = "/seller/deleteconnection/";
			        	respContent+="<tr>";
							respContent+="<td>";
								respContent+="<a href="+baseUrl+data.id+">Delete</a>";
							respContent+="</td>";
							respContent+="<td>";
								respContent+=data.firstName + data.middleName + data.lastName;
							respContent+="</td>";
						respContent+="</tr>";
						
			            $(respContent).insertBefore($(".displayrow").first());  
			        }
			    }); 
			    event.preventDefault();
			  });
			});
		</script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<c:url var="post_url" value="/seller/addconnection"/>
		<form method="POST" action="${post_url}" id="seller_viewconnection_form" class="form">
			<div class="ui-corner-all custom-corners">
				<div class="ui-body ui-body-a">
					<label for="seller_viewconnection_phonenumber">Phone Number</label>
					<input id="seller_viewconnection_phonenumber" type="text"/>
					<label for="seller_viewconnection_groupname">Group Name</label>
					<select id="seller_viewconnection_privategroupoption">
						<option value="-1">None</option>
						<c:forEach var="item" items="${privateGroupList}">
							<option value="${item.id}">${item.privateGroupName}</option>
						</c:forEach>
					</select>
					<input id="seller_viewconnection_connect" type="submit" value="Connect" />
				</div>
			</div>
		</form>
		<c:url var="base_url" value="/seller/deleteconnection/"/>
		<table id="seller_viewconnection_displaytable">
			<c:forEach items="${connectionList}" var="item" >
				<tr class="displayrow">
					<td>
						<a href="${base_url}${item.id}">Delete</a>
					</td>
					<td>
						${item.firstName} ${item.middleName} ${item.lastName}
					</td>
				</tr>
			</c:forEach>
		</table>
	</tiles:putAttribute>
</tiles:insertDefinition>
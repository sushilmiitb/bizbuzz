<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		InstaTrade-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<style type="text/css">
			span{
				background-color: gray;
				border: medium;
				margin: 5px;
			}
			.groupdiv{
				border: thick solid; 
				padding: 10px;
				margin: 20px;
			}
			.subgroupdiv{
				border: thin solid;
				padding: 20px;
				margin: 10px;
			}
		</style>
		<script>
			function imageClick(event){
				event.preventDefault();
				var n = $('.imagerow').length;
				var html = "<td><input class='imagetag' name='imageModels["+ n +"].tag' type='text' value="+n+" readonly='true'/></td>";
				html = html+"<td><input class='imagename' name='imageModels["+ n +"].name' type='text'/></td>";
				html = "<tr class='imagerow'>"+html+"</tr>";
				$("#imagetable").append(html);
			}
			function groupClick(event){
				event.preventDefault();
				var i = $('.groupdiv').length;
				var html = "<h2>Group <input class='grouptag' name='propertyGroups["+i+"].tag' type='text' value='"+i+"' readonly='true' /></h2>"
				html = html+"<h2><input name='propertyGroups["+i+"].name' type='text' /></h2>";
				html = html + "<span class='addSubgroup' onclick='subgroupClick(this)' id='addSubgroup-"+i+"'>Add Subgroup</span>";
				html = "<div class='groupdiv' id='groupdiv-"+i+"'>"+html+"</div>";
				$(html).insertBefore("#addGroup");
			}
			function subgroupClick(event){
				//event.preventDefault();
				var clickId = $(event).attr("id");
				var clickIdArray = clickId.split('-');
				var i = parseInt(clickIdArray[1]);
				var subgroups = $("#groupdiv-"+i).find(".subgroupdiv");
				var j = subgroups.length;
				var html = "<h5>Subgroup <input name='propertyGroups["+i+"].propertySubGroups["+j+"].tag' type='text' value='"+j+"' readonly='true' /></h5>";
				html = html + "<h5><input name='propertyGroups["+i+"].propertySubGroups["+j+"].name' type='text' /></h5>";
				html = html + "<table id='table-"+i+"-"+j+"'></table>";
				html = html + "<span class='addField' onclick='fieldClick(this)' id='addField-"+i+"-"+j+"'>Add Field</span>";
				html = "<div class='subgroupdiv' id='subgroupdiv-"+i+"-"+j+"'>"+html+"</div>";
				$(html).insertBefore("#addSubgroup-"+i);
			}
			function fieldClick(event){
				//event.preventDefault();
				var clickId = $(event).attr("id");
				var clickIdArray = clickId.split('-');
				var i = parseInt(clickIdArray[1]);
				var j = parseInt(clickIdArray[2]);
				var rows = $(".fieldrow-"+i+"-"+j);
				var k = rows.length;
				var html = "<td>Field <input class='fieldtag' name='propertyGroups["+i+"].propertySubGroups["+j+"].propertyFields["+k+"].tag' type='text' value='"+k+"' readonly='true' /></td>";
				html = html+"<td><input class='fieldname' name='propertyGroups["+i+"].propertySubGroups["+j+"].propertyFields["+k+"].value' type='text' /></td>";
				html = "<tr class='fieldrow-"+i+"-"+j+"'>"+html+"</tr>";
				//html = html + "<button class='addField' id='addField-"+i+"-"+j+"'>Add Field</button>";
				$("#table-"+i+"-"+j).append(html);
			}
			
			$(document).ready(function(){
				$("#addImage").click(imageClick);
				
				$("#addGroup").click(groupClick);
				
				//$(".addSubgroup").click(subgroupClick);
				
				//$(".addField").click(fieldClick);
			});
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
			<c:url var="save_url" value="/admin/property/save/category/${categoryId }?propertyMetadataId=${propertyMetadata.id}" />
			<c:url var="delete_url" value="/admin/property/delete/category/${categoryId}" />
			<form:form modelAttribute="propertyMetadata" action="${save_url }" method="POST">
				<input name="id" type="hidden" value="${propertyMetadata.id}"/>
				<h1>Images</h1>
				<table id="imagetable">
				<c:forEach var="item" items="${propertyMetadata.imageModels}" varStatus="i" >
					<tr class="imagerow">
						<td style="display:none"><input class="imagetag" name="imageModels[${i.index}].id" value="${item.id}" type="hidden" /></td>
						<td><input class="imagetag" name="imageModels[${i.index}].tag" value="${item.tag}" type="text" readonly="true" /></td>
						<td><input class="imagename" name="imageModels[${i.index}].name" value="${item.name}" type="text" /></td>
						<td><a href="${delete_url}/image/${item.id}">Delete Image</a></td>
					</tr>
				</c:forEach>
				</table>
				
				<button id="addImage">Add Image</button>
				
				<h1>Properties</h1>
				<c:forEach var="group" items="${propertyMetadata.propertyGroups}" varStatus="i" >
					<div class="groupdiv" id="groupdiv-${i.index}">
						<input class="grouptag" name="propertyGroups[${i.index}].id" value="${group.id}" type="hidden" />
						<h2>Group <input class="grouptag" name="propertyGroups[${i.index}].tag" value="${group.tag}" type="text" readonly="true" /></h2>
						<h2><input name="propertyGroups[${i.index}].name" value="${group.name}" type="text" /></h2>
						<p><a href="${delete_url}/group/${group.id}">Delete Group</a></p>
						<c:forEach var="subgroup" items="${group.propertySubGroups}" varStatus="j" >
							<div class="subgroupdiv" id="subgroupdiv-${i.index}-${j.index}">
								<input name="propertyGroups[${i.index}].propertySubGroups[${j.index}].id" value="${subgroup.id}" type="hidden" />
								<h5>Subgroup <input name="propertyGroups[${i.index}].propertySubGroups[${j.index}].tag" value="${subgroup.tag}" type="text" readonly="true" /></h5>
								<h5><input name="propertyGroups[${i.index}].propertySubGroups[${j.index}].name" value="${subgroup.name}" type="text" /></h5>
								<p><a href="${delete_url}/subgroup/${subgroup.id}">Delete Subgroup</a></p>
								<table id="table-${i.index}-${j.index}">
								<c:forEach var="field" items="${subgroup.propertyFields}" varStatus="k" >
									<tr class="fieldrow">
										<td style="display:none;"><input class="grouptag" name="propertyGroups[${i.index}].propertySubGroups[${j.index}].propertyFields[${k.index}].id" value="${field.id}" type="hidden" /></td>
										<td>Field <input class="grouptag" name="propertyGroups[${i.index}].propertySubGroups[${j.index}].propertyFields[${k.index}].tag" value="${field.tag}" type="text" readonly="true" /> </td>
										<td><input name="propertyGroups[${i.index}].propertySubGroups[${j.index}].propertyFields[${k.index}].value" value="${field.value}" type="text" /></td>
										<td><a href="${delete_url}/field/${field.id}">Delete Field</a></td>
									</tr>
								</c:forEach>
								</table>
								<span class="addField" onclick="fieldClick(this)" id="addField-${i.index}-${j.index}">Add Field</span>
							</div>
						</c:forEach>
						<span class="addSubgroup" onclick='subgroupClick(this)' id="addSubgroup-${i.index}">Add Subgroup</span>
					</div>
				</c:forEach>
				<button id="addGroup">Add group</button>
				<br/><br/>
				<input type="submit" value="submit" />
			</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
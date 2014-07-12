<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		BizBuzz-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
		$(document).ready(function() {
			$('#admin_viewproperty_form').submit(function(event) {
				var newProperty = $('.newInputRow');
				var newPropertyJsonObj= [];
				for(i=0;i<newProperty.length;i++){
					var item = new Object();
					item["id"] = null;
					item["propertyName"] = $(newProperty[i]).find(".propertyInputPropertyName").val();
					item["possibleUnits"] = $(newProperty[i]).find(".propertyInputPossibleUnits").val();
					item["possibleValues"] = $(newProperty[i]).find(".propertyInputPossibleValues").val();
					item["groupingName1"] = $(newProperty[i]).find(".propertyInputGroupingname1").val();
					item["groupingName2"] = $(newProperty[i]).find(".propertyInputGroupingname2").val();
					item["groupingName3"] = $(newProperty[i]).find(".propertyInputGroupingname3").val();
					newPropertyJsonObj.push(item);
				}
				var property = $('.propertyInputRow');
				var propertyJsonObj= [];
				for(i=0;i<property.length;i++){
					var item = new Object();
					item["id"] = null;
					item["propertyName"] = $(property[i]).find(".propertyInputPropertyName").val();
					item["possibleUnits"] = $(property[i]).find(".propertyInputPossibleUnits").val();
					item["possibleValues"] = $(property[i]).find(".propertyInputPossibleValues").val();
					item["groupingName1"] = $(property[i]).find(".propertyInputGroupingname1").val();
					item["groupingName2"] = $(property[i]).find(".propertyInputGroupingname2").val();
					item["groupingName3"] = $(property[i]).find(".propertyInputGroupingname3").val();
					propertyJsonObj.push(item);
				}
				var json = new Object();
				json["categoryId"]="${categoryId}";
				json["updatePropertyMetadata"] = propertyJsonObj;
				json["newPropertyMetadata"] = newPropertyJsonObj;
				
				console.log("test", JSON.stringify(json));
				$.ajax({
					url: $("#admin_viewproperty_form").attr( "action"),
					data: JSON.stringify(json),
					type: "POST",
		
					beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(data) {
					var respContent = "";
					/*if(data.errors.length){
				          	for(var i=0; i<data.errors.length;i++){
				          		respContent += "<span class='error'>" + data.errors[i] +"</span>";
				          		$(respContent).insertBefore($(".group").first());
				          	}
				          	return;
				          }*/
					respContent += "<span class='group'>" + data.groupName +"</span>";
					$(respContent).insertBefore($(".group").first());  
				}
				}); 
				event.preventDefault();
			});
		
			$('#admin_viewproperty_addpropertybtn').click(function(event) {
				var html='<tr class="newInputRow">'+
					'<td><input '+ 
					'type="text" class="propertyInputPropertyName"  /> '+
					'</td> '+
					'<td><input '+
					'type="text" class="propertyInputPossibleUnits" /> '+
					'</td> '+
					'<td><input '+ 
					'type="text" class="propertyInputPossibleValues" /> '+
					'</td> '+
					'<td><input '+ 
					'type="text" class="propertyInputGroupingname1" /> '+
					'</td> '+
					'<td><input '+ 
					'type="text" class="propertyInputGroupingname2" /> '+
					'</td> '+
					'<td><input '+ 
					'type="text" class="propertyInputGroupingname3" /> '+
					'</td> '+
					'</tr> ';
					$('.headerRow').after(html);
			});
		
		});
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="base_url" value="/admin/property/save" />
		<button id="admin_viewproperty_addpropertybtn">Add Property</button>
		<form id="admin_viewproperty_form" action="${base_url}">
			<table>

				<tr class="headerRow">
					<th>Property Name</th>
					<th>Unit</th>
					<th>Possible Values</th>
					<th>Property Group1</th>
					<th>Property Group2</th>
					<th>Property Group3</th>
				</tr>
				<c:forEach items="${propertyMetadatas}" var="item" >
					<tr id="admin_viewproperty_row_${item.id}" class="propertyInputRow">
						<td><input id="admin_viewproperty_propertyname_${item.id}"
							type="text" class="propertyInputPropertyName"
							value="${item.propertyName }" /></td>
						<td><input id="admin_viewproperty_possibleunits_${item.id}"
							type="text" class="propertyInputPossibleUnits"
							value="${item.possibleUnits }" /></td>
						<td><input id="admin_viewproperty_possiblevalues_${item.id}"
							type="text" class="propertyInputPossibleValues"
							value="${item.possibleValues }" /></td>
						<td><input id="admin_viewproperty_groupingname1_${item.id}"
							type="text" class="propertyInputGroupingname1"
							value="${item.groupingName1 }" /></td>
						<td><input id="admin_viewproperty_groupingname2_${item.id}"
							type="text" class="propertyInputGroupingname2"
							value="${item.groupingName2 }" /></td>
						<td><input id="admin_viewproperty_groupingname3_${item.id}"
							type="text" class="propertyInputGroupingname3"
							value="${item.groupingName3 }" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td><input type="submit" id="admin_viewproperty_submit_${item.id}" value="Save"/></td>
				</tr>
			</table>
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>
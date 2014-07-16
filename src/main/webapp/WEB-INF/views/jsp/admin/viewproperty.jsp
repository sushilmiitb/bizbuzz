<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		BizBuzz-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
	<!-- 
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
		-->
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
<%-- 		<c:url var="base_url" value="/admin/property/save" /> --%>
<!-- 		<button id="admin_viewproperty_addpropertybtn">Add Property</button> -->
<%-- 		<form id="admin_viewproperty_form" action="${base_url}"> --%>
<!-- 			<table> -->

<!-- 				<tr class="headerRow"> -->
<!-- 					<th>Property Name</th> -->
<!-- 					<th>Unit</th> -->
<!-- 					<th>Possible Values</th> -->
<!-- 					<th>Property Group1</th> -->
<!-- 					<th>Property Group2</th> -->
<!-- 					<th>Property Group3</th> -->
<!-- 				</tr> -->
<%-- 				<c:forEach items="${propertyMetadatas}" var="item" > --%>
<%-- 					<tr id="admin_viewproperty_row_${item.id}" class="propertyInputRow"> --%>
<%-- 						<td><input id="admin_viewproperty_propertyname_${item.id}" --%>
<!-- 							type="text" class="propertyInputPropertyName" -->
<%-- 							value="${item.propertyName }" /></td> --%>
<%-- 						<td><input id="admin_viewproperty_propertycode_${item.id}" --%>
<!-- 							type="text" class="propertyInputPropertyCode" -->
<%-- 							value="${item.propertyCode }" /></td> --%>
<%-- 						<td><input id="admin_viewproperty_possibleunits_${item.id}" --%>
<!-- 							type="text" class="propertyInputPossibleUnits" -->
<%-- 							value="${item.possibleUnits }" /></td> --%>
<%-- 						<td><input id="admin_viewproperty_possiblevalues_${item.id}" --%>
<!-- 							type="text" class="propertyInputPossibleValues" -->
<%-- 							value="${item.possibleValues }" /></td> --%>
<%-- 						<td><input id="admin_viewproperty_groupingname1_${item.id}" --%>
<!-- 							type="text" class="propertyInputGroupingname1" -->
<%-- 							value="${item.groupingName1 }" /></td> --%>
<%-- 						<td><input id="admin_viewproperty_groupingcode1_${item.id}" --%>
<!-- 							type="text" class="propertyInputGroupingcode1" -->
<%-- 							value="${item.groupingCode1 }" /></td> --%>
<%-- 						<td><input id="admin_viewproperty_groupingname2_${item.id}" --%>
<!-- 							type="text" class="propertyInputGroupingname2" -->
<%-- 							value="${item.groupingName2 }" /></td> --%>
<%-- 						<td><input id="admin_viewproperty_groupingcode2_${item.id}" --%>
<!-- 							type="text" class="propertyInputGroupingcode2" -->
<%-- 							value="${item.groupingCode2 }" /></td> --%>
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<!-- 				<tr> -->
<%-- 					<td><input type="submit" id="admin_viewproperty_submit_${item.id}" value="Save"/></td> --%>
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</form> -->
			
			
<%-- 			<c:url var="save_existing_property_url" value="/admin/property/link/category/${categoryId }" /> --%>
<%-- 			<form action="${save_existing_property_url }" method="POST"> --%>
<!-- 				<h4>Link Existing Property Metadata</h4> -->
<!-- 				<h6>PropertyMetadata Id</h6> -->
<!-- 				<input type="text" value="propertyMetadataId"/> -->
<!-- 				<input type="submit" value="submit"/> -->
<!-- 			</form> -->
			
			<c:url var="save_url" value="/admin/property/save/category/${categoryId }?propertyMetadataId=${propertyMetadata.id}" />
			<form:form modelAttribute="propertyMetadata" action="${save_url }" method="POST">
			<table>
				
				<tr style="visibility: hidden;">
					<td><form:label path="id">id</form:label></td>
					<td><form:input path="id" type="text" value="${propertyMetadata.id}"/></td>
				</tr>
				<tr>
					<td><form:label path="isImagePresent">isImagePresent</form:label></td>
					<td><form:input path="isImagePresent" type="text"/></td>
				</tr>
				<tr>
					<td><form:label path="primaryImage">primaryImage</form:label></td>
					<td><form:input path="primaryImage" type="text"/></td>
				</tr>
				<tr>
					<td><form:label path="image1">image1</form:label></td>
					<td><form:input path="image1" type="text"/></td>
				</tr>
				<tr>
					<td><form:label path="image2">image2</form:label></td>
					<td><form:input path="image2" type="text"/></td>
				</tr>
				<tr>
					<td><form:label path="image3">image3</form:label></td>
					<td><form:input path="image3" type="text"/></td>
				</tr>
				<tr>
					<td><form:label path="group1">group1</form:label></td>
					<td><form:input path="group1" type="text"/></td>
				</tr>
				<tr>
					<td><form:label path="group1Subgroup1">group1Subgroup1</form:label></td>
					<td><form:input path="group1Subgroup1" type="text"/></td>
				</tr>
				<tr>
					<td><form:label path="group1Subgroup1Property1">group1Subgroup1Property1</form:label></td>
					<td><form:input path="group1Subgroup1Property1" type="text"/></td>
				</tr>
				<tr>
					<td><form:label path="group1Subgroup1Property2">group1Subgroup1Property2</form:label></td>
					<td><form:input path="group1Subgroup1Property2" type="text"/></td>
				</tr>
			</table>
			<input type="submit" value="submit" />
			</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
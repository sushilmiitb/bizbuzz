<script src="../../../../static/js/jquery/jquery-1.11.1.min.js"></script>
<script src="../../../../static/js/mobile/jquery.mobile-1.4.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#admin_viewcategory_form').submit(function(event) {
		var newProperties = $('.newInputRow');
		var newPropertyJsonObj= new Object();
		for(i=0;i<newProperties.length;i++){
			var item = new Object();
			item["id"] = null;
			item["propertyName"] = newProperty[i].find(".propertyInputPropertyName");
			item["possibleUnits"] = newProperty[i].find(".propertyInputPossibleUnits");
			item["possibleValues"] = newProperty[i].find(".propertyInputPossibleValues");
			item["groupingName1"] = newProperty[i].find(".propertyInputGroupingname1");
			item["groupingName2"] = newProperty[i].find(".propertyInputGroupingname2");
			item["groupingName3"] = newProperty[i].find(".propertyInputGroupingname3");
			newPropertyJsonObj.push(item);
		}
		var properties = $('.propertyInputRow');
		var propertyJsonObj= new Object();
		for(i=0;i<properties.length;i++){
			var item = new Object();
			item["id"] = null;
			item["propertyName"] = property[i].find(".propertyInputPropertyName");
			item["possibleUnits"] = property[i].find(".propertyInputPossibleUnits");
			item["possibleValues"] = property[i].find(".propertyInputPossibleValues");
			item["groupingName1"] = property[i].find(".propertyInputGroupingname1");
			item["groupingName2"] = property[i].find(".propertyInputGroupingname2");
			item["groupingName3"] = property[i].find(".propertyInputGroupingname3");
			propertyJsonObj.push(item);
		}
		var json = new Object();
		json["categoryId"]=asdf;
		json["updatePropertyMetadata"] = propertyJsonObj;
		json["newPropertyMetadata"] = newPropertyJsonObj;
		
		console.log("test", JSON.stringify(json));
		$.ajax({
			url: $("#admin_viewcategory_form").attr( "action"),
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
		var html='<tr class="newInputRow">
			<td><input 
			type="text" class="propertyInputPropertyName"  />
			</td>
			<td><input
			type="text" class="propertyInputPossibleUnits" />
			</td>
			<td><input 
			type="text" class="propertyInputPossibleValues" />
			</td>
			<td><input 
			type="text" class="propertyInputGroupingname1" />
			</td>
			<td><input 
			type="text" class="propertyInputGroupingname2" />
			</td>
			<td><input 
			type="text" class="propertyInputGroupingname3" />
			</td>
			</tr>';
			('.addButtonRow').after(html);
	});

});
</script>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		BizBuzz-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			$(document).ready(function() {
			  $('#admin_viewcategory_form').submit(function(event) {
			  	var json = { "categoryName" : $('#admin_viewcategory_inputcategory').val(),
			  				 "parentId": "${categoryId}",
			  				 "isLeaf": $('#admin_viewcategory_inputisleaf').val()
			  				};
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
			  
			  $('.admin_viewcategory_deletebutton').click(function(event) {
			    
			    $.ajax({
			        url: $(this).attr("id"),
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
			            /*respContent += "<span class='group'>" + data.groupName +"</span>";
			            $(respContent).insertBefore($(".group").first());
			            */  
			        }
			    }); 
			    event.preventDefault();
			  });
			  
			  $('.admin_viewcategory_modifycategoryform').submit(function(event) {
			  	var test = $(this).find(".modified_isleaf_value");
			  	console.log("modifiedisleaf", test);
			  	var json = { "categoryName" : $(this).find(".modified_category_name").val(),
			  				 "parentId": "${categoryId}",
			  				 "isLeaf": $(this).find("select").val()
			  				};
			    console.log("test", JSON.stringify(json));
			    $.ajax({
			        url: $(this).attr("action"),
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
			            /*respContent += "<span class='group'>" + data.groupName +"</span>";
			            $(respContent).insertBefore($(".group").first());*/  
			        }
			    }); 
			    event.preventDefault();
			  });
			  
			});
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="post_url" value="/admin/addcategory" />
		<h1>${parentCategoryName}</h1>
		<form action="${post_url}" id="admin_viewcategory_form">
			<label for="admin_viewcategory_inputcategory">category name</label> 
			<input type="text" id="admin_viewcategory_inputcategory" /> 
			<label for="admin_viewcategory_inputcategory">Is Final Category</label> 
			<select id="admin_viewcategory_inputisleaf">
				<option value="false">No</option>
				<option value="true">Yes</option>
			</select> 
			<input type="submit" value="Create Category" id="admin_viewcategory_submit" />
		</form>

		<c:url var="base_category_url" value="/admin/viewcategory/" />
		<c:url var="base_modifycategory_url" value="/admin/category/edit/" />
		<c:url var="base_delete_url" value="/admin/category/delete/" />
		<c:url var="base_property_url" value="/admin/viewproperty/" />

		<table>
			<c:forEach items="${categoryList}" var="item">
				<tr>
					<td><span class="group"> 
						<c:choose>
							<c:when test="${item.isLeaf}">
								<a href="${base_property_url}${item.id}">${item.categoryName}</a>
							</c:when>
							<c:otherwise>
								<a href="${base_category_url}${item.id}">${item.categoryName}</a>
							</c:otherwise>
						</c:choose>
					</span></td>
					<td>
						<button id="${base_delete_url}${item.id}"
							class="admin_viewcategory_deletebutton">Delete</button>
					</td>
					<td>
						<form class="admin_viewcategory_modifycategoryform" action="${base_modifycategory_url}${item.id}">
							<input class="modified_category_name" type="text" /> 
							<select class="modified_isleaf_value">
								<option value="false">Non-leaf category</option>
								<option value="true">Leaf category</option>
							</select> 
							<input type="submit" value="Change Name" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>

	</tiles:putAttribute>
</tiles:insertDefinition>
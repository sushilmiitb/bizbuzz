<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		InstaTrade-Product
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
	<script type="text/javascript">
		
		$(document).ready(function() {
			
			$('#seller_editcategory_form').submit(function(event) {
				$('#editCategoryModal').modal('toggle');
				var json = { "categoryName" : $('#seller_categoryname').val()};
				console.log("test", JSON.stringify(json));
				$.ajax({
					url: $("#seller_editcategory_form").attr( "action"),
					data: JSON.stringify(json),
					type: "POST",
		
					beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(data) {
					$(".loader").remove();
					var response = data.response;
					if(response=="error"){
						if(data.errors.duplicate_name !== undefined){
							displayQuickNotification(data.errors.duplicate_name, 3000);
						}
						return;
					}
					$('#category_name').text(data.categoryName);
				},
				error: function(){
					$(".loader").remove();
				}
				}); 
				event.preventDefault();
			});
			 
		});
		
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="edit_url" value="/seller/editcategory/category/${categoryTree.id}" />
		<c:url var="base_image_url" value="/${rootDir}" />
		<c:url var="base_product_url" value="/seller/viewproduct/item/" />
		<c:url var="base_uploadproduct_url" value="/seller/uploadproduct/category/" />
		<c:url var="emptyImageUrl"
			value="/${rootDir}/${sizeDir}/noimage.${imageExtn}" />
		<c:url var="uploadImageUrl"
			value="/${rootDir}/${sizeDir}/uploadimage.${imageExtn}" />
		<c:set var="totalItems" value="${fn:length(items)}"/>
		
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
				<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 no-padding">
					<div class="panel panel-primary">
						<div class="panel-heading center-align-text" id="category_name">${categoryTree.categoryName}</div>
						 <div class="panel-body">
						 <c:forEach var="counter" begin="0" end="${totalItems}">
						     <c:set var="displayImage" scope="page" />
						       <c:if test="${counter % 4 == 0}">
									<div class="row">
								</c:if>
								<div class="col-xs-6 col-sm-6 col-md-4 col-lg-3">
								 <c:choose>
								  <c:when test="${counter==0}">
									<a href="${base_uploadproduct_url}${categoryTree.id}" class="thumbnail" align="center" >
									  <img src="${uploadImageUrl}" class="image-responsive" alt="Uploaded Poduct" />
									</a>
								  </c:when>
								  <c:otherwise>
								     
								     <a href="${base_product_url}${items[counter-1].id}" class="thumbnail" align="center" >
									    <c:forEach var="imageModel" items="${items[counter-1].imageModels}">
										    <c:if test="${imageModel.imageModelMetadata.tag=='0'}">
												<c:set var="displayImage" value="${imageModel}" scope="page" />
											</c:if>	
									    </c:forEach>
										<c:choose>
									      <c:when test="${not empty displayImage}">
											<img class="image-responsive"
												src="${base_image_url}/${sizeDir}/${displayImage.id}.${imageExtn}"
											    alt="Image Not Uploaded" />
										  </c:when>
										  <c:otherwise>
											 <img src="${emptyImageUrl}" class="image-responsive" alt="Image Not Uploaded" />
										  </c:otherwise>
										</c:choose>
									   </a>
								  </c:otherwise>
								 </c:choose> 
								</div> 
								<c:if test="${counter % 4 == 3 || counter==totalItems}">
									</div>
								</c:if>
						 </c:forEach>
	            		</div>
	            		 <c:if test="${categoryTree.isCustom}">
	            			 <div class="panel-footer">
	 							<div class="row">
	 								<div class="col-xs-3 col-xm-3 col-md-3 col-lg-3"></div>
								<div class="col-xs-6 col-xm-6 col-md-6 col-lg-6">
									<button data-target="#editCategoryModal" data-toggle="modal" class="btn btn-primary btn-block">Edit Category Details</button>
								</div>
							</div>
						 </div>
						</c:if>
					</div>
				</div>
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
			</div>
			<c:if test="${categoryTree.isCustom}">
				<div class="modal fade" id="editCategoryModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Edit Category Details</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"></div>
								<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"></div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<legend>Edit Category</legend>
									<form:form method="POST" action="${edit_url}"
										id="seller_editcategory_form" class="form form-signin"
										modelAttribute="categoryTree">
										<form:label path="categoryName"
											for="seller_categoryname_label">Category Name</form:label>
										<form:input path="categoryName" class="form-control"
											id="seller_categoryname" type="text" />
										<input id="seller_viewcategory_edit"
											class="btn btn-primary btn-block" type="submit"
											value="Change Category Details" />
									</form:form>
									<br />
								</div>
							</div>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</c:if>
		</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
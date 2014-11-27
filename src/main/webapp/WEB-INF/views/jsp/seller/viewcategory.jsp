<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		InstaTrade-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
		$(document).ready(function() {

		 $('#seller_addcategory_form').submit(function(event) {
		 	$('#addCategoryModal').modal('toggle');
		 	
				var json = { "categoryName" : $('#seller_addcategory_catname').val()};
				console.log("test", JSON.stringify(json));
				$.ajax({
					url: $("#seller_addcategory_form").attr( "action"),
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
					//displayQuickNotification(data.categoryName, 3000);
					var url = "/seller/viewcategory/category/"+data.categoryId;
					window.location.assign(url);
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
		<c:url var="base_category_url" value="/seller/viewcategory/category/" />
		<c:url var="emptyImageUrl"
			value="/${rootDir}/${sizeDir}/noimage.${imageExtn}" />
		<c:url var="add_category_url" value="/seller/addcategory/category"/>
		<c:url var="addCategoryUrl"
			value="/${rootDir}/${sizeDir}/uploadimage.${imageExtn}" />
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
				<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 no-padding">
					<div class="panel panel-primary">
						<div class="panel-heading center-align-text">${parentCategory.categoryName}</div>
						<div class="panel-body">
							<div class="row">
<!-- 							admin added categories listing -->
								<c:forEach items="${categoryList}" var="item">
									<div class="col-xs-6 col-sm-6 col-md-4 col-lg-3">
										<a href="${base_category_url}${item.id}?" class="thumbnail">
											<img src="${emptyImageUrl}" class="image-responsive" alt="Category">
											<h4 class="center-align-text">${item.categoryName}</h4>
										</a>
									</div>
								</c:forEach>
<!-- 								Add a new custom category  -->
								<div class="col-xs-6 col-sm-6 col-md-4 col-lg-3">
									<a href="#" class="thumbnail" align="center" data-toggle="modal" data-target="#addCategoryModal">
									  <img src="${addCategoryUrl}" class="image-responsive" alt="add Category" />
									</a>
								</div>
							</div>
						</div>
						 <c:if test="${parentCategory.isCustom}">
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

<!--------------------------------	Modal for adding category ------------------------------>
			<div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Add category</h4>
						</div>
						<div class="modal-body">
							<form method="POST" action="${add_category_url}/${parentCategory.id}"
								id="seller_addcategory_form" class="form">
								<label for="seller_addcategory_catname_lable">Category Name</label>
								<input class="form-control" name="categoryName"
									id="seller_addcategory_catname" type="text" />
								<input id="seller_addcategory_create"
									class="btn btn-md btn-primary btn-block" type="submit"
									value="Create Category" />
							</form>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
<!--------------------------------	Modal for editing current category ------------------------------>
				<c:if test="${parentCategory.isCustom}">
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
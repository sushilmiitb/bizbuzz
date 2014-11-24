<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		InstaTrade-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
		$(document).ready(function() {
/*
		 $('#seller_addcategory_form').submit(function(event) {
		 	$('#addCategoryModal').modal('toggle');
		 	
			$.ajax({
				url: $("#seller_addcategory_form").attr( "action"),
				data: {categoryName:$('#seller_addcategory_catname').val()},
				type: "POST",
		
			    success: function(data) {
				 alert("data: " +data);
				 $(".loader").remove();
				 var response = data.response;
				 if(response=="error"){
				 	alert("error..");
					return;
				 }

			 },
			 error: function(){
			 	$(".loader").remove();
			 }
			}); 
			  event.preventDefault();
		   });*/
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
					</div>
				</div>
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>	
			</div>
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
								<label for="seller_addcategory_catname">Category Name</label>
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
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>
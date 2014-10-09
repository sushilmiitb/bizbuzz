<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		InstaTrade-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">

		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="base_category_url" value="/seller/viewcategory/category/" />
		<c:url var="emptyImageUrl"
			value="/${rootDir}/${sizeDir}/noimage.${imageExtn}" />
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="panel panel-primary">
					<div class="panel-heading center-align-text">${parentCategoryName}</div>
					<div class="panel-body">
						<div class="row">
							<c:forEach items="${categoryList}" var="item">
								<div class="col-xs-6 col-sm-6 col-md-4 col-lg-3">
									<a href="${base_category_url}${item.id}?" class="thumbnail">
										<img src="${emptyImageUrl}" class="image-responsive" alt="Category">
										<h4 class="center-align-text">${item.categoryName}</h4>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>
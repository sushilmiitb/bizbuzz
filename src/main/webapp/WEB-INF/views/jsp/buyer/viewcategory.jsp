<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="buyer">
	<tiles:putAttribute name="includejs">
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		InstaTrade-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">

		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="base_category_url" value="/buyer/viewcategory/seller/${sellerid}/category/" />
		<c:url var="base_product_url" value="/buyer/viewproduct/seller/${sellerid}/category/" />
		<c:url var="emptyImageUrl"
			value="/${rootDir}/${sizeDir}/noimage.${imageExtn}" />
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
				<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 no-padding">
					<div class="panel panel-primary">
						<div class="panel-heading center-align-text">${parentCategoryName}</div>
						<div class="panel-body">
							<div class="row">
								<c:forEach items="${categoryList}" var="item">
									<div class="col-xs-6 col-sm-6 col-md-4 col-lg-3">
										<c:choose>
											<c:when test="${item.hasProduct==true}">
												<a href="${base_product_url}${item.id}?" class="thumbnail">
													<img src="${emptyImageUrl}" class="image-responsive" alt="Category">
													<h4 class="center-align-text">${item.categoryName}</h4>
												</a>
											</c:when>
											<c:otherwise>
												<a href="${base_category_url}${item.id}?" class="thumbnail">
													<img src="${emptyImageUrl}" class="image-responsive" alt="Category">
													<h4 class="center-align-text">${item.categoryName}</h4>
												</a>
											</c:otherwise>
										</c:choose>
									</div>
										
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>	
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>
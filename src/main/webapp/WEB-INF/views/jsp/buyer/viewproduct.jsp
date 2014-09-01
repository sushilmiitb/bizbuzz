<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="buyer">
	<tiles:putAttribute name="title">
		BizBuzz-Product
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="base_image_url" value="/${rootDir}" />
		<c:url var="base_product_url" value="/buyer/viewproduct/item/" />
		<c:url var="emptyImageUrl"
			value="/${rootDir}/${sizeDir}/noimage.${imageExtn}" />
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="col-xs-12 col-sm-12 col-md-12  col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading center-align-text">${parentCategory.categoryName}</div>
						<div class="panel-body">
							<c:forEach var="item" items="${items}" varStatus="i">
								<c:if test="${i.index % 4 == 0}">
									<div class="row">
								</c:if>
								<div class="col-xs-6 col-sm-6 col-md-4 col-lg-3">
									<a href="${base_product_url}${item.id}/seller/${seller.id}"
										class="thumbnail" align="center"> <c:forEach
											var="imageModel" items="${item.imageModels}">
											<c:if test="${imageModel.imageModelMetadata.tag=='0'}">
												<c:set var="displayImage" value="${imageModel}" scope="page" />
											</c:if>
										</c:forEach> <c:choose>
											<c:when test="${not empty displayImage}">
												<img class="image-responsive"
													src="${base_image_url}/${sizeDir}/${displayImage.id}.${imageExtn}"
													alt="Image Not Uploaded" />
											</c:when>
											<c:otherwise>
												<img src="${emptyImageUrl}" class="image-responsive"
													alt="Image Not Uploaded" />
											</c:otherwise>
										</c:choose>
									</a>
								</div>
								<c:if test="${i.index % 4 == 3 || i.last}">
									</div>
								</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		BizBuzz-Product
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="base_image_url" value="/${rootDir}" />
		<c:url var="base_product_url" value="/seller/viewproduct/item/" />
		<c:url var="emptyImageUrl"
			value="/${rootDir}/${sizeDir}/noimage.${imageExtn}" />
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="col-xs-12 col-sm-12 col-md-12  col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading center-align-text">${parentCategoryName}</div>
						<div class="panel-body">
							<div class="row">
								<c:forEach var="item" items="${items}">
									<div class="col-xs-6 col-sm-6 col-md-4 col-lg-3">
										<a href="${base_product_url}${item.id}" class="thumbnail" align="center" >
											<c:choose>
												<c:when
													test="${not empty item.propertyValue.primaryImageModel}">
													<img class="image-responsive"
														src="${base_image_url}/${sizeDir}/${item.propertyValue.primaryImageModel.id}.${imageExtn}"
														alt="Image Not Uploaded" />
												</c:when>
												<c:otherwise>
													<img src="${emptyImageUrl}" class="image-responsive" alt="Image Not Uploaded" />
												</c:otherwise>
											</c:choose>
										</a>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

<%-- 		<c:forEach var="item" items="${items}"> --%>
<%-- 			<a href="${base_product_url}${item.id}"> <c:choose> --%>
<%-- 					<c:when test="${not empty item.propertyValue.primaryImageModel}"> --%>
<!-- 						<img -->
<%-- 							src="${base_image_url}/${sizeDir}/${item.propertyValue.primaryImageModel.id}.${imageExtn}" --%>
<!-- 							alt="Image Not Uploaded" /> -->
<%-- 					</c:when> --%>
<%-- 					<c:otherwise> --%>
<!-- 						<img src="" alt="Image Not Uploaded" /> -->
<%-- 					</c:otherwise> --%>
<%-- 				</c:choose> --%>
<!-- 			</a> -->
<!-- 			<br /> -->
<%-- 		</c:forEach> --%>
	</tiles:putAttribute>
</tiles:insertDefinition>
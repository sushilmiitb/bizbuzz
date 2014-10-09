<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		InstaTrade-Product
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
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
						<div class="panel-heading center-align-text">${parentCategoryName}</div>
						 <div class="panel-body">
						 <c:forEach var="counter" begin="0" end="${totalItems}">
						       <c:if test="${counter % 4 == 0}">
									<div class="row">
								</c:if>
								<div class="col-xs-6 col-sm-6 col-md-4 col-lg-3">
								 <c:choose>
								  <c:when test="${counter==0}" >
									<a href="${base_uploadproduct_url}${categoryId}" class="thumbnail" align="center" >
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
					</div>
				</div>
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		BizBuzz-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="base_image_url" value="/${rootDir}" />
		<c:url var="emptyImageUrl"
			value="/${rootDir}/${sizeDir}/noimage.${imageExtn}" />
		<c:url var="view_full_size_image_url"
			value="/seller/viewfullimage" />
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading center-align-text">Product name</div>
						<div class="panel-body">
							<div class="row image-area">
								<div class="panel panel-default">
									<div class="panel-heading">Images</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<c:if test="${not empty propertyMetadata.primaryImage }">
													<c:choose>
														<c:when
															test="${not empty item.propertyValue.primaryImageModel}">
															<a class="thumbnail"
																href="${view_full_size_image_url}/${item.propertyValue.primaryImageModel.id}/extn/${imageExtn}">
																<img class="image-responsive upload-preview"
																id="thumbnail_primaryImage" alt="..."
																src="${base_image_url}/${sizeDir}/${item.propertyValue.primaryImageModel.id}.${imageExtn}" />
																<h4 class="center-align-text">${propertyMetadata.primaryImage }</h4>
															</a>
														</c:when>
														<c:otherwise>
															<a href="#" class="thumbnail"> <img
																class="image-responsive no-image upload-preview"
																id="thumbnail_primaryImage" alt=""
																src="${emptyImageUrl} ">
																<h4 class="center-align-text">${propertyMetadata.primaryImage }</h4>
															</a>
														</c:otherwise>
													</c:choose>
												</c:if>
											</div>

											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<c:if test="${not empty propertyMetadata.image1 }">
													<c:choose>
														<c:when test="${not empty item.propertyValue.image1Model}">
															<a class="thumbnail"
																href="${base_image_url}/${baseSizeDir}/${item.propertyValue.image1Model.id}.${imageExtn}">
																<img class="image-responsive upload-preview"
																id="thumbnail_primaryImage" alt="..."
																src="${base_image_url}/${sizeDir}/${item.propertyValue.image1Model.id}.${imageExtn}" />
																<h4 class="center-align-text">${propertyMetadata.image1 }</h4>
															</a>
														</c:when>
														<c:otherwise>
															<a href="#" class="thumbnail"> <img
																class="image-responsive no-image upload-preview"
																id="thumbnail_primaryImage" alt=""
																src="${emptyImageUrl} ">
																<h4 class="center-align-text">${propertyMetadata.image1 }</h4>
															</a>
														</c:otherwise>
													</c:choose>
												</c:if>
											</div>

											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<c:if test="${not empty propertyMetadata.image2 }">
													<c:choose>
														<c:when test="${not empty item.propertyValue.image2Model}">
															<a class="thumbnail"
																href="${base_image_url}/${baseSizeDir}/${item.propertyValue.image2Model.id}.${imageExtn}">
																<img class="image-responsive upload-preview"
																id="thumbnail_primaryImage" alt="..."
																src="${base_image_url}/${sizeDir}/${item.propertyValue.image2Model.id}.${imageExtn}" />
																<h4 class="center-align-text">${propertyMetadata.image2 }</h4>
															</a>
														</c:when>
														<c:otherwise>
															<a href="#" class="thumbnail"> <img
																class="image-responsive no-image upload-preview"
																id="thumbnail_primaryImage" alt=""
																src="${emptyImageUrl} ">
																<h4 class="center-align-text">${propertyMetadata.image2 }</h4>
															</a>
														</c:otherwise>
													</c:choose>
												</c:if>
											</div>

											<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
												<c:if test="${not empty propertyMetadata.image3 }">
													<c:choose>
														<c:when test="${not empty item.propertyValue.image3Model}">
															<a class="thumbnail"
																href="${base_image_url}/${baseSizeDir}/${item.propertyValue.image3Model.id}.${imageExtn}">
																<img class="image-responsive upload-preview"
																id="thumbnail_primaryImage" alt="..."
																src="${base_image_url}/${sizeDir}/${item.propertyValue.image3Model.id}.${imageExtn}" />
																<h4 class="center-align-text">${propertyMetadata.image3 }</h4>
															</a>
														</c:when>
														<c:otherwise>
															<a href="#" class="thumbnail"> <img
																class="image-responsive no-image upload-preview"
																id="thumbnail_primaryImage" alt=""
																src="${emptyImageUrl} ">
																<h4 class="center-align-text">${propertyMetadata.image3 }</h4>
															</a>
														</c:otherwise>
													</c:choose>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="row" id="propertyContent">
								<div class="col-xs-12 col-md-6 col-sm-6 col-lg-4">
									<c:if test="${not empty propertyMetadata.group1 }">
										<div class="panel panel-default">
											<div class="panel-heading">${propertyMetadata.group1 }</div>
											<div class="panel-body">
												<c:if test="${not empty propertyMetadata.group1Subgroup1 }">
													<div class="table-responsive">
														<table class="table">
															<caption>${propertyMetadata.group1Subgroup1}</caption>
															<c:if
																test="${not empty propertyMetadata.group1Subgroup1Property1 }">
																<tr>
																	<td>${propertyMetadata.group1Subgroup1Property1}</td>
																	<td>${item.propertyValue.group1Subgroup1Property1 }</td>
																</tr>
															</c:if>

															<c:if
																test="${not empty propertyMetadata.group1Subgroup1Property2 }">
																<tr>
																	<td>${propertyMetadata.group1Subgroup1Property2}</td>
																	<td>${item.propertyValue.group1Subgroup1Property2 }</td>
																</tr>
															</c:if>
														</table>
													</div>
												</c:if>
											</div>
										</div>
									</c:if>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>
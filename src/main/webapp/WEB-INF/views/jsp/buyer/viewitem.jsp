<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="buyer">
	<tiles:putAttribute name="title">
		InstaTrade-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="base_image_url" value="/${rootDir}" />
		<c:url var="emptyImageUrl"
			value="/${rootDir}/${sizeDir}/noimage.${imageExtn}" />
		<c:url var="view_full_size_image_url" value="/buyer/viewfullimage" />
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
				<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 no-padding">
					<div class="panel panel-primary">
						<div class="panel-heading center-align-text">Product name</div>
						<div class="panel-body">
							<div class="row image-area">
								<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12">
									<div class="panel panel-default">
										<div class="panel-heading">Images</div>
										<div class="panel-body">
											<c:forEach var="item" items="${propertyMetadata.imageModels}" varStatus="i" >
											       <c:if test="${i.index % 3 == 0}">
														<div class="row">
													</c:if>
														<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
														<c:choose>
															<c:when test="${not empty valueImageModelMap[item.id]}">
																<a class="thumbnail"
																	href="${view_full_size_image_url}/${valueImageModelMap[item.id].id}/extn/${imageExtn}">
																	<img class="image-responsive upload-preview"
																	id="thumbnail_images_${i.index}" alt="..."
																	src="${base_image_url}/${sizeDir}/${valueImageModelMap[item.id].id}.${imageExtn}" />
																	<h4 class="center-align-text">${item.name}</h4>
																</a>
															</c:when>
															<c:otherwise>
																<a href="#" class="thumbnail"> <img
																	class="image-responsive no-image upload-preview"
																	id="thumbnail_images_${i.index}" alt=""
																	src="${emptyImageUrl}">
																	<h4 class="center-align-text">${item.name}</h4>
																</a>
															</c:otherwise>
														</c:choose>
														</div>
													<c:if test="${i.index % 3 == 2 || i.index== fn:length(propertyMetadata.imageModels)}">
														</div>
													</c:if>
										 	</c:forEach>
										</div>
									</div>
								</div>
							</div>

						</div>

							<div class="row" id="propertyContent">
								<c:forEach var="group"
									items="${propertyMetadata.propertyGroups}" varStatus="i">
									<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12 no-padding">
										<div class="panel panel-default">
											<div class="panel-heading">${group.name}</div>
											<div class="panel-body">
												<div class="row">
													<c:forEach var="subgroup"
														items="${group.propertySubGroups}" varStatus="j">
														<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
															<strong>${subgroup.name}</strong>
															<c:forEach var="field"
																items="${subgroup.propertyFields}" varStatus="k">
																<div class="row">
																	<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">${field.value}</div>
																	<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">:</div>
																	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">${propertyValueMap[field.id].value}</div>
																</div>
															</c:forEach>
														</div>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
					</div>
				</div>
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

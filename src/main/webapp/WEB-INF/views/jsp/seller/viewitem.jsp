<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		BizBuzz-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="base_image_url" value="/${rootDir}"/>
		<table>
			<c:if test="${not empty propertyMetadata.primaryImage }">
				<tr>
					<td colspan="2">${propertyMetadata.primaryImage}</td>
				</tr>
				<c:if test="${not empty item.propertyValue.primaryImageModel}">
					<tr>
						<td colspan="2"><img
							src="${base_image_url}/${sizeDir}/${item.propertyValue.primaryImageModel.id}.${imageExtn}" />
						</td>
					</tr>
				</c:if>
			</c:if>

			<c:if test="${not empty propertyMetadata.image1 }">
				<tr>
					<td colspan="2">${propertyMetadata.image1}</td>
				</tr>
				<c:if test="${not empty item.propertyValue.image1Model}">
					<tr>
						<td colspan="2"><img
							src="${base_image_url}/${sizeDir}/${item.propertyValue.image1Model.id}.${imageExtn}" />
						</td>
					</tr>
				</c:if>
			</c:if>
			
			<c:if test="${not empty propertyMetadata.image2 }">
				<tr>
					<td colspan="2">${propertyMetadata.image2}</td>
				</tr>
				<c:if test="${not empty item.propertyValue.image2Model}">
					<tr>
						<td colspan="2"><img
							src="${base_image_url}/${sizeDir}/${item.propertyValue.image2Model.id}.${imageExtn}" />
						</td>
					</tr>
				</c:if>
			</c:if>
			
			<c:if test="${not empty propertyMetadata.image3 }">
				<tr>
					<td colspan="2">${propertyMetadata.image3}</td>
				</tr>
				<c:if test="${not empty item.propertyValue.image3Model}">
					<tr>
						<td colspan="2"><img
							src="${base_image_url}/${sizeDir}/${item.propertyValue.image3Model.id}.${imageExtn}" />
						</td>
					</tr>
				</c:if>
			</c:if>
		</table>
		<c:if test="${not empty propertyMetadata.group1 }">
			<h2>${propertyMetadata.group1 }</h2>
			<c:if test="${not empty propertyMetadata.group1Subgroup1 }">
				<h3>${propertyMetadata.group1Subgroup1}</h3>
				<table>
					<c:if
						test="${not empty propertyMetadata.group1Subgroup1Property1 }">
						<tr>
							<td>${propertyMetadata.group1Subgroup1Property1}</td>
							<td>
								<c:if test="${not empty item.propertyValue.group1Subgroup1Property1}">
									${item.propertyValue.group1Subgroup1Property1}
								</c:if>
							</td>
						</tr>
					</c:if>

					<c:if
						test="${not empty propertyMetadata.group1Subgroup1Property2 }">
						<tr>
							<td>${propertyMetadata.group1Subgroup1Property2}</td>
							<td>
								<c:if test="${not empty item.propertyValue.group1Subgroup1Property2}">
									${item.propertyValue.group1Subgroup1Property2}
								</c:if>
							</td>
						</tr>
					</c:if>
				</table>
			</c:if>
		</c:if>

	</tiles:putAttribute>
</tiles:insertDefinition>
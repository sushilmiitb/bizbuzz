<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		BizBuzz-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">		
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="base_image_url" value="/${rootDir}" />
		<c:url var="base_product_url" value="/seller/viewproduct/item/" />
		<c:forEach var="item" items="items">
			<a href="${base_product_url}${item.id}">
				<img src="${base_image_url}/${sizeDir}/${item.propertyValue.primaryImageModel.id}.${imageExtn}" alt="Image Not Uploaded" />
			</a>
		</c:forEach>
	</tiles:putAttribute>
</tiles:insertDefinition>
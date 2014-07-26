<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		BizBuzz-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">

		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<h1>${parentCategoryName}</h1>
		<c:url var="base_category_url" value="/seller/viewcategory/category/" />

		<table>
			<c:forEach items="${categoryList}" var="item">
				<tr>
					<td><span class="group">
						<a href="${base_category_url}${item.id}?leaf=${item.isLeaf}&parentCategoryName=${parentCategoryName}">${item.categoryName}</a>
					</span></td>
				</tr>
			</c:forEach>
		</table>

	</tiles:putAttribute>
</tiles:insertDefinition>
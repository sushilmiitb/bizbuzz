
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="error">
	<tiles:putAttribute name="include">
	</tiles:putAttribute>
	<tiles:putAttribute name="header">
		<h1>InstaTrade</h1>
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		InstaTrade - Error
	</tiles:putAttribute>
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<div class="ui-corner-all custom-corners">
			<div class="ui-bar ui-bar-a">
				<h3>	No User matched your search. Please check your search content.</h3>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


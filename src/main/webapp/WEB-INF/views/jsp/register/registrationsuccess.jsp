<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="register">
	<tiles:putAttribute name="include">
	</tiles:putAttribute>
	<tiles:putAttribute name="header">
		<h1>Bizbuzz- Register</h1>
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		BizBuzz-Registration
	</tiles:putAttribute>
	<tiles:putAttribute name="customJsCode">
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<p style="text-align: center;">Registration Successful</p>
		<br />
		<p style="text-align: center;">
			<a href="<c:url value='/bizbuzz/login' />">Login</a>
		</p>
	</tiles:putAttribute>
</tiles:insertDefinition>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<h1>header</h1> 
<span>
	<form action='<c:url value="/j_spring_security_logout"/>' method="post">
		<input type="submit" value="Logout" />
	</form>
</span>
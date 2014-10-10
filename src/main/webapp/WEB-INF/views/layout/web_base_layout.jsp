<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="<c:url value='/static/js/jquery/jquery-1.11.1.js'/>"></script>
<tiles:insertAttribute name="include" ignore="true"/>
<tiles:insertAttribute name="customJsCode" ignore="true" />
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<div id="page">
		<div id="header">
			<tiles:insertAttribute name="header" ignore="true"/>
		</div>
		<div id="main">
			<tiles:insertAttribute name="body" />
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" ignore="true"/>
		</div>
	</div>
</body>
</html>
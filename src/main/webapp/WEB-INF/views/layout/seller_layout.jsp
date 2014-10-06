<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<c:url var="static_base_url" value="/static" />

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon"
	href="${static_base_url}/assets/ico/favicon.ico">

<!-- Bootstrap core CSS -->
<link href="${static_base_url}/css/seller-buyer-combined-<spring:message code="git_hash" />.min.css"
	rel="stylesheet">

<tiles:insertAttribute name="includecss" ignore="true" />

<title><tiles:insertAttribute name="title" /></title>
</head>

<body role="document">
	<tiles:insertAttribute name="header" ignore="true" />
	<div id="page-content">
		<tiles:insertAttribute name="body" />
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${static_base_url}/js/seller-buyer-combined-<spring:message code="git_hash" />.min.js"></script>
	
	<tiles:insertAttribute name="customJsCode" ignore="true" />
	<tiles:insertAttribute name="includejs" ignore="true" />

	<tiles:insertAttribute name="footer" ignore="true" />
</body>
</html>
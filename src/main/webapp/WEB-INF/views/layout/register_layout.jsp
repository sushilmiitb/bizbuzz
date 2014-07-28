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
<link href="${static_base_url}/css/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap theme -->
<link href="${static_base_url}/css/bootstrap/bootstrap-theme.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${static_base_url}/css/theme.css" rel="stylesheet">


<tiles:insertAttribute name="include" ignore="true" />

<title><tiles:insertAttribute name="title" /></title>
</head>

<body role="document">
	<tiles:insertAttribute name="header" ignore="true" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" ignore="true" />

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${static_base_url}/js/jquery/jquery-1.11.1.min.js"></script>
	<script src="${static_base_url}/js/bootstrap/bootstrap.min.js"></script>
	<script src="${static_base_url}/assets/js/docs.min.js"></script>
	<tiles:insertAttribute name="customJsCode" ignore="true" />
</body>
</html>
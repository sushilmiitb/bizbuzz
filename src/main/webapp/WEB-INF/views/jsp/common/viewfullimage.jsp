<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>InstaTrade - FullImage</title>

<style media="screen" type="text/css">
body {
	background-repeat: no-repeat;
	background-position: top;
	width:${width}px;
	height:${height}px;
}
</style>
</head>
<body background=<c:url value = '/${rootDir}/${sizeDir}/${id}.${extn}' /> >

</body>
</html>
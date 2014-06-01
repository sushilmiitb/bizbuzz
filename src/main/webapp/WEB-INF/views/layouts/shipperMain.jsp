<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title><tiles:getAsString name="title" /></title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link href="/cargo/static/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/cargo/static/bootstrap/css/bootstrap-datetimepicker.min.css" />
<link href="/cargo/static/bootstrap/css/responsiveslides.css" rel="stylesheet" />
<link href="/cargo/static/bootstrap/css/font-awesome.min.css" rel="stylesheet" />
<link href="/cargo/static/bootstrap/css/main.css" rel="stylesheet" />
<tiles:insertAttribute name="head" defaultValue="" />
</head>
<body>
  <div>
  	<tiles:insertAttribute name="header" />
  			<tiles:insertAttribute name="shipperMenu" />
  			<tiles:insertAttribute name="body" />
  	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="/cargo/static/bootstrap/js/jquery-1.11.0.min.js" type="text/javascript"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="/cargo/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="/cargo/static/bootstrap/js/moment-with-langs.min.js" type="text/javascript"></script>
  <script src="/cargo/static/bootstrap/js/responsiveslides.min.js" type="text/javascript"></script>
  <script src="/cargo/static/bootstrap/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
  <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?sensor=false">
    </script> 
  <script src="/cargo/static/bootstrap/js/main.js" type="text/javascript"></script>
  	
  	<tiles:insertAttribute name="footer" />
  </div>
</body>
</html>

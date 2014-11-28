<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!-- Fixed navbar -->
<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top header" role="navigation">
   <div class="navbar-header">
      <a class="navbar-brand" href="#"> <img alt="IT" src="/<spring:message code='app_home_folder'/>/static/css/images/icons/logo.png" class="logo" /> </a>
      
   </div>
   <div>
   		<div>
   			<a class="header-nav-item" href="<c:url value='/buyer/viewcategory/viewsellers'/>" >Product</a>
   			<a class="header-nav-item" href="<c:url value='/buyer/viewcontacts'/>" >Contact</a>
<%--    			<a class="header-nav-item" href="<c:url value='/j_spring_security_logout'/>" >Logout</a> --%>
   		</div>
   </div>
</nav>
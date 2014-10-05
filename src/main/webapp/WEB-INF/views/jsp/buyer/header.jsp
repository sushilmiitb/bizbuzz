<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!-- Fixed navbar -->
<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation" id="slide-nav">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span>
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Bizbuzz</a>
		</div>
		
		<div id="slidemenu">
			
				<ul class="nav navbar-nav">		
					<li><a href="<c:url value="/buyer/home"/>">Home</a></li>
					<li><a href="<c:url value="/buyer/viewcategory/category/-1"/>"> Products </a></li>
					<li><a href="<c:url value="/buyer/viewcontacts"/>">Contacts</a></li>
					<li><a href='<c:url value="/j_spring_security_logout"/>'>Logout</a></li>
				</ul>
		
		</div>
		<!--/.nav-collapse -->
	</div>
</div>
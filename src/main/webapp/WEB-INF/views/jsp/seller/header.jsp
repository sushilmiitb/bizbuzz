<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!-- Fixed navbar -->
<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header pull-right">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Bizbuzz</a>
		</div>
		<div class="navbar-collapse collapse pull-left">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/seller/home"/>">Home</a></li>
				<li><a href="<c:url value="/seller/viewcategory/category/-1"/>">View Products</a></li>
<!--			<li><a href="<c:url value="/seller/uploadproduct/category/-1"/>">Upload</a></li>                -->
				<li><a href="<c:url value="/seller/viewcontacts"/>">Contacts</a></li>
				<li><a href='<c:url value="/j_spring_security_logout"/>'>Logout</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>
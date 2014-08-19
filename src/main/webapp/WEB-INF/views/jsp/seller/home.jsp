<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="includejs">
	</tiles:putAttribute>

	<tiles:putAttribute name="title">
		BizBuzz-Home
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">What do you want to do?</div>
						<div class="panel-body">
							<ul class="nav nav-pills nav-stacked n">
								<li><a href="<c:url value="/seller/viewcategory/category/-1"/>"><span
										class="glyphicon glyphicon-search"></span> View Products </a></li>
								<li><a href="<c:url value="/seller/uploadproduct/category/-1"/>"><span
										class="glyphicon glyphicon-upload"></span> Upload Products </a></li>
								<li><a href="/chat/showchatrooms"><span class="glyphicon glyphicon-user"></span>
										Chat </a></li>
								<li><a href="<c:url value="/seller/viewgroup"/>"><span class="glyphicon glyphicon-cog"></span>
										Group Management </a></li>
								<li><a href="<c:url value="/seller/viewconnection"/>"><span class="glyphicon glyphicon-edit"></span>
										Connection Management </a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="buyer">
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
								<li><a href="<c:url value="/buyer/viewcategory/category/-1"/>"><span
										class="glyphicon glyphicon-search"></span> View Products </a></li>
								<li><a href="<c:url value="/buyer/viewconnection"/>"><span
										class="glyphicon glyphicon-search"></span> View connections </a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
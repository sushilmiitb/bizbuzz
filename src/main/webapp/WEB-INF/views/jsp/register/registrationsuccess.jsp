<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="register">
	<tiles:putAttribute name="include">
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		BizBuzz-Registration
	</tiles:putAttribute>
	<tiles:putAttribute name="customJsCode">
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="col-xs-12 col-md-12 col">
					<div class="panel panel-primary">
						<div class="panel-body">
							<p style="text-align: center;">Registration Successful</p>
							<br />
							<p style="text-align: center;">
								<a href="<c:url value='/login' />">Login</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
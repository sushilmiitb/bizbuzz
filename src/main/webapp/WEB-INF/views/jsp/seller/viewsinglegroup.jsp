<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		BizBuzz-Groups
	</tiles:putAttribute>
	
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
		</script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<c:url var="post_url" value="/seller/editgroup/${privateGroup.id}" />
		<c:url var="delete_url" value="/seller/deletegroup/${privateGroup.id}" />
		<c:url var="base_url" value="/seller/viewconnection/"/>
		
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="hidden-xs hidden-sm col-md-1 col-lg-2"></div>
				<div class="col-xs-12 col-sm-12 col-md-10 col-lg-8">
					<div class="panel panel-primary">
						<div class="panel-heading center-align-text">${privateGroup.privateGroupName}</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-12 col-xm-12 col-md-12 col-lg-12">
									<button class="btn btn-primary btn-block" data-toggle="modal"
										data-target="#editModal">Edit Group Details</button>
								</div>
							</div>
							<br/>
							<div class="list-group">
								<a href="#" class="list-group-item active">
							      <h4 class="list-group-item-heading">
							         Group Members
							      </h4>
							   </a>
							   <a href="#" class="list-group-item">
										<div class="row">
											<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
												<h4 class="list-group-item-heading">Name</h4>
											</div>
											<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
												<h4 class="list-group-item-heading">Phone Number</h4>
											</div>
										</div>
								</a>
								<c:forEach items="${groupMembers}" var="item" >
									<a href="${base_url}${item.id}" class="list-group-item">
										<div class="row">
											<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
												${item.firstName} ${item.middleName} ${item.lastName}
											</div>
											<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
												${item.userId.id}
											</div>
										</div>
									</a>
								</c:forEach>
							</div>							
						</div>
					</div>
				</div>
				<div class="hidden-xs hidden-sm col-md-1 col-lg-2"></div>
			</div>
		</div>
		
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Edit Group Details</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<legend>Edit Group</legend>
								<form:form method="POST" action="${post_url}" id="seller_viewsinglegroup_form" class="form form-signin" modelAttribute="privateGroup">
									<form:label path="privateGroupName" for="seller_viewsinglegroup_groupname">Group Name</form:label>
									<form:input path="privateGroupName" class="form-control" id="seller_viewsinglegroup_groupname" type="text"/>
									<input id="seller_viewsinglegroup_edit" class="btn btn-primary btn-block" type="submit" value="Change Group Name" />
								</form:form>
								<br/>
								<hr>
								<br/>
								<form:form method="GET" action="${delete_url}" id="seller_viewsinglegroup_deleteform">
									<input id="seller_viewsinglegroup_delete" class="btn btn-danger btn-block" type="submit" value="Delete Group" />
								</form:form>								
							</div>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>
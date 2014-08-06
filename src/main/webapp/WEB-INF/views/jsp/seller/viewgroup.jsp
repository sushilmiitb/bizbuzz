<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		BizBuzz-Groups
	</tiles:putAttribute>
	
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			$(document).ready(function() {
				$(".alert").fadeOut(4000);
				$('#seller_viewgroup_form').submit(function(event) {
					var json = { "privateGroupName" : $('#seller_viewgroup_groupname').val(),
								 "errors" : {}	
								};
				  console.log("test", JSON.stringify(json));
				  $.ajax({
				      url: $("#seller_viewgroup_form").attr( "action"),
				      data: JSON.stringify(json),
				      type: "POST",
				       
				      beforeSend: function(xhr) {
				          xhr.setRequestHeader("Accept", "application/json");
				          xhr.setRequestHeader("Content-Type", "application/json");
				      },
				      success: function(data) {
				          if(typeof data.errors.duplicate_name != 'undefined'){
				          	$("span.error").remove();
			          		var spanElement = document.createElement('span');
			          		$(spanElement).addClass('error');
			          		$(spanElement).html(data.errors.duplicate_name);
				          	$(spanElement).insertAfter($("#seller_viewgroup_groupname"));
				          	$(spanElement).before("<br/><br/>");
				          	return;
				          }
				          var listElement = document.createElement('li');
				          var linkElement = document.createElement('a');
				          var html = "<span class='glyphicon glyphicon-user'></span>"+data.privateGroupName;
				          $(linkElement).html(html);
				          $(linkElement).attr("href", "<c:url value='/seller/viewgroup/'/>"+data.id);
				          $(listElement).append(linkElement);
				          
				          /*respContent += "<span class='group'>" + data.privateGroupName +"</span>";
				          respContent += <li>
									<a href="${base_group_url}${item.id}">
										<span class="glyphicon glyphicon-user"></span>
										${item.privateGroupName}
									</a>
								</li>*/
					
				          $(listElement).insertBefore($(".group").first());
				          $(".loader").remove();  
				      },
				      error: function(){
			        	$(".loader").remove();
			          }
				  }); 
				  event.preventDefault();
				});
			});
		</script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<c:url var="post_url" value="/seller/addgroup"/>
		<c:url var="base_group_url" value="/seller/viewgroup/"/>
		<div class="container" role="main">
			<div class="row" id="maincontent">
			<div class="hidden-xs hidden-sm col-md-1 col-lg-2"></div>
				<div class="col-xs-12 col-sm-12 col-md-10 col-lg-8">
					<div class="panel panel-primary">
					<div class="panel-heading"> Create new group</div>
					<div class="panel-body">
						<form:form method="POST" action="${post_url}" id="seller_viewgroup_form" class="form" modelAttribute="privateGroupForm">
							<form:label path="privateGroupName" for="seller_viewgroup_groupname">Group Name</form:label>
							<form:input path="privateGroupName" class="form-control" id="seller_viewgroup_groupname" type="text"/>
							<input id="seller_viewgroup_create" class="btn btn-md btn-primary btn-block" type="submit" value="Create Group" />
						</form:form>
					</div>
					</div>
            		<hr>
            		
            		<div class="list-group">
						<a href="#" class="list-group-item active">
					      <h4 class="list-group-item-heading">
					         Your Groups
					      </h4>
					   </a>
						<c:forEach items="${privateGroups}" var="item" >
							<a href="${base_group_url}${item.id}" class="list-group-item">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<span class="glyphicon glyphicon-user"></span>
											${item.privateGroupName}
									</div>
								</div>
							</a>
						</c:forEach>
					</div>
          		</div>
          		<div class="hidden-xs hidden-sm col-md-1 col-lg-2"></div>
      		</div>
	    </div>
	</tiles:putAttribute>
</tiles:insertDefinition>
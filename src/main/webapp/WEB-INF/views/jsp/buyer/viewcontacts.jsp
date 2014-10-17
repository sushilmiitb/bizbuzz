<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="buyer">
	<tiles:putAttribute name="title">
		InstaTrade-Groups
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
<!------------------------------------------ 	Javascript code for Contacts -------------------------------------------->
		<script type="text/javascript">
		var debug=false;
		var baseStaticUrl = "/static";
		var contactAndroidObj;
		$(document).ready(function() {
			//call the specific function on document ready
		});
		</script>
		
<!------------------------------------------ 	Javascript code for groups -------------------------------------------->
		<script>
			$(document).ready(function() {
				
			});
		</script>
		
<!------------------------------------------ 	Javascript code for page -------------------------------------------->
		<script>
			$("#navContacts").click(function(){
				$(this).addClass("active");
				$("#connectionContainer").show();
			});
			
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="post_url_contacts" value="#" />   <!-- To add connection in future use this url :-  /buyer/addconnection  -->
		<c:url var="base_delete_url" value="/seller/deleteconnection/" />
		<c:url var="base_url" value="/buyer/viewconnection/" />

		
		<div class="container" role="main">
<!------------------------------------------ 	 Contacts Module -------------------------------------------->
			<div class="row" id="maincontent" class="contacts-page">
				<div class="hidden-xs hidden-sm col-md-1 col-lg-2"></div>
				<div class="col-xs-12 col-sm-12 col-md-10 col-lg-8 no-padding">
					<div class="panel panel-primary">
<!-- 						<div class="panel-heading center-align-text">Connections</div> -->
		               <ul class="nav nav-pills subnav-top-horizontal">
							<li class="active" id="navContacts">
		                        <a href="#">Contacts</a>
							</li>
						</ul>
						<div class="panel-body no-padding" id="connectionContainer">

							
							<div class="list-group">
								<a href="#" class="list-group-item active">
									<h4 class="list-group-item-heading">Existing Connections</h4>
								</a> 
						<a href="#" class="list-group-item heading">   
									<div class="row">
										<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
											<h4 class="list-group-item-heading">Name</h4>
										</div>
										<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
											<h4 class="list-group-item-heading">Phone Number</h4>
										</div>
										<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
											<h4 class="list-group-item-heading">Group</h4>
										</div>
									</div>
								</a>
  		  	  				<c:forEach items="${connectionList}" var="item">
									<a href="#" class="list-group-item">
										<div class="row">
											<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
												${item.fromParty.firstName} ${item.fromParty.middleName}
												${item.fromParty.lastName}</div>
											<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
												${item.fromParty.userId.id}</div>
											<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
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
		
	</tiles:putAttribute>
</tiles:insertDefinition>

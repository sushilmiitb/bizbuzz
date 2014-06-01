<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> <%@
taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="register">
	<tiles:putAttribute name="body">
		<div class="container-fluid" style="padding-top: 70px">
			<c:choose>
				<c:when test="${empty successmessage }">
					<div class="row" style="background: #E3E3E3">
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<div class="panel panel-default">
								<div class="panel-body col-xs-offset-1">
									<div class="title">Sign In</div>
									<form class="form-horizontal" role="form"
										action="j_spring_security_check" method="post">
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-4 control-label">Email</label>
											<div class="col-sm-5">
												<input type="text" name="j_username" class="form-control"
													id="inputEmail3" placeholder="Email">
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">Password</label>
											<div class="col-sm-5">
												<input type="password" name="j_password"
													class="form-control" id="inputPassword3"
													placeholder="Password">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-4 col-sm-5">
												<div class="checkbox">
													<label> <input type="checkbox"
														name="_spring_security_remember_me"> Remember me
													</label>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-4 col-sm-5">
												<a href="#"> Forgot Password? </a>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-4 col-sm-5">
												<button type="submit" class="btn btn-default">Sign
													in</button>
											</div>
										</div>
									</form>
									<div class="rule"></div>
									<div class="title">Don't Have an Account? Register Here</div>
									<form action="register_success" method="POST"
										id="userregistrationform" modelAttribute="userregistration"
										class="form-horizontal" role="form">
										<div class="form-group">
											<label for="inputfirstname" class="col-sm-4 control-label">First
												Name</label>
											<div class="col-sm-5">
												<form:input path="userregistration.firstname" type="text"
													class="form-control" id="inputfirstname"
													placeholder="First Name" aria-describedby="name-format"
													required="true" />
											</div>
										</div>
										<div class="form-group">
											<label for="inputfirstname" class="col-sm-4 control-label">Last
												Name</label>
											<div class="col-sm-5">
												<form:input path="userregistration.lastname" type="text"
													class="form-control" id="inputlastname"
													placeholder="Last Name" />
											</div>
										</div>
										<div class="form-group">
											<label for="inputcompanyname" class="col-sm-4 control-label">Company
												Name</label>
											<div class="col-sm-5">
												<form:input path="userregistration.companyname" type="text"
													class="form-control" id="inputcompanyname"
													placeholder="Company Name" />
											</div>
										</div>
										<div class="form-group">
											<label for="inputdesignation" class="col-sm-4 control-label">Designation
											</label>
											<div class="col-sm-5">
												<form:input path="userregistration.designation" type="text"
													class="form-control" id="inputdesignation"
													placeholder="Designation" />
											</div>
										</div>
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-4 control-label">Email</label>
											<div class="col-sm-5">
												<form:input path="userregistration.emailid" type="email"
													class="form-control" id="inputEmail3" placeholder="Email" />
											</div>
										</div>
										<div class="form-group">
											<label for="inputpassword" class="col-sm-4 control-label">Password</label>
											<div class="col-sm-5">
												<form:input path="userregistration.password" type="password"
													class="form-control" id="inputpassword"
													placeholder="Password" required="true" />
											</div>
										</div>
										<div class="form-group">
											<label for="inputphone" class="col-sm-4 control-label">Phone
												Number</label>
											<div class="col-sm-5">
												<form:input path="userregistration.phonenumber" type="text"
													class="form-control" id="inputphone"
													placeholder="Phone Number" aria-describedby="name-format"
													min="5" max="10" pattern="[0-9]{5,10}" required="true"
													title="Please enter correct phone number" />
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-4 col-sm-5">
												<div class="checkbox">
													<label> <form:checkbox
															path="userregistration.isrememberme" value="remember" />
														Remember me
													</label>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-4 col-sm-5">
												<button type="submit" class="btn btn-default">Sign
													Up</button>
											</div>
										</div>
									</form>
								</div>
							</div>

						</div>
						<div class="col-md-2"></div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="row" >
						<div class="col-md-2"></div>
						<div class="col-md-8" style="text-align:center;">
							<div style="color: #008000">${successmessage}</div>
						</div>
						<div class="col-md-2"></div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>
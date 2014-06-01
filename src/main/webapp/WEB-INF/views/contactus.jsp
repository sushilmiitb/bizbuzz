<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insertDefinition name="contactus">
	<tiles:putAttribute name="body">
		<div class="container-fluid" style="padding-top: 70px">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-info">
							<div class="panel panel-heading text-center">Write to Us</div>
							<form name="contactusform" action="contactus_success" method="POST" id="contactusform" modelAttribute="contactus" class="form-horizontal panel-body text-center" role="form"
								style="">
								<div class="form-group">
									<label for="firstname" class="col-sm-4 control-label">First
										Name</label>
									<div class="col-sm-6">
										<form:input name="firstname" path="contactus.firstname"  type="text" class="form-control" id="firstname"
											placeholder="First Name" aria-describedby="name-format" required="true" />
									</div>
								</div>
								<div class="form-group">
									<label for="lastname" class="col-sm-4 control-label">Last
										Name</label>
									<div class="col-sm-6">
										<form:input path="contactus.lastname"  type="text" class="form-control" id="lastname"
											placeholder="Last Name"/>
									</div>
								</div>
								<div class="form-group">
									<label for="help" class="col-sm-4 control-label">How
										Can we Help?</label>
									<div class="col-sm-4">
										<div class="radio text-left">
											<label> <input type="radio" name=role
												id="shipper" value="shipper" checked> I&apos;m a
												Shipper
											</label>
										</div>
										<div class="radio text-left">
											<label> <input type="radio" name="role"
												id="trucker" value="trucker"> I&apos;m a Trucker
											</label>
										</div>
										<div class="radio text-left">
											<label> <input type="radio" name="role"
												id="jobseeker" value="jobseeker"> I&apos;m a Job
												Seeker
											</label>
										</div>
										<div class="radio text-left">
											<label> <input type="radio" name="optionsRadios"
												id="others" value="others"> Others
											</label>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label for="companyname" class="col-sm-4 control-label">Company
										Name</label>
									<div class="col-sm-6">
										<form:input path="contactus.companyname"  type="text" class="form-control" id="companyname"
											placeholder="Company Name"/>
									</div>
								</div>

								<div class="form-group">
									<label for="phonenumber" class="col-sm-4 control-label">Phone
										Number</label>
									<div class="col-sm-6">
										<form:input name="phonenumber" path="contactus.phonenumber"  type="text" class="form-control" id="phonenumber"
											placeholder="Phone Number" aria-describedby="name-format" min="5" max="10" pattern="[0-9]{5,10}" required="true" title="Please enter the phonnumber"/>
									</div>
								</div>



								<div class="form-group">
									<label for="email" class="col-sm-4 control-label">E-mail</label>
									<div class="col-sm-6">
										<form:input path="contactus.emailid"  type="email" class="form-control" id="email"
											placeholder="E-Mail"/>
									</div>
								</div>

								<div class="form-group">
									<label for="Destination" class="col-sm-4 control-label">Message</label>
									<div class="col-sm-6">
										<textarea name ="message" class="form-control" rows="3" style="resize: none"
											id="message"></textarea>
											
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-info">Submit</button>
									</div>
								</div>

							</form>
							<c:if test="${not empty '${successmessage}' }">
										<div style="color:#008000" > ${successmessage}</div>
							</c:if>
						</div>
					</div>
					<div class="col-md-6">
						<div class="text-left">
							<address>
								<strong>CargoBolt Logistics Pvt. Ltd.</strong><br> Ideapad,
								CIIE Building,<br> IIM Ahmedabad, <br> Ahmedabad,<br>
								Gujarat<br> <abbr title="Phone">P:</abbr> +91-9825125088<br>
								<abbr title="email">E:</abbr> <a href="mailto:cargobolt@gmail.com">cargobolt@gmail.com</a>
							</address>
						</div>

						<div class="panel panel-default">
							<div class="panel-body">
								<div id="map-canvas" style="height: 300px"></div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

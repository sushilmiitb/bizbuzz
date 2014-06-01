<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tiles:insertDefinition name="homepage">
	<tiles:putAttribute name="body">
		<div class="container-fluid" style="padding-top: 70px">
			<div class="container">
				<div class="row">

					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-body col-xs-offset-1">
								<div class="title">Sign In</div>
								<form class="form-horizontal" role="form"
									action="j_spring_security_check" method="post">
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-4 control-label">Email</label>
										<div class="col-sm-6">
											<input type="text" name="j_username" class="form-control"
												id="inputEmail3" placeholder="Email">
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label">Password</label>
										<div class="col-sm-6">
											<input type="password" name="j_password" class="form-control"
												id="inputPassword3" placeholder="Password">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-6">
											<div class="checkbox">
												<label> <input type="checkbox"
													name="_spring_security_remember_me"> Remember me
												</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-6">
											<a href="#"> Forgot Password? </a>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-6">
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
										<div class="col-sm-6">
											<form:input path="userregistration.firstname" type="text"
												class="form-control" id="inputfirstname"
												placeholder="First Name" aria-describedby="name-format"
												required="true" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputfirstname" class="col-sm-4 control-label">Last
											Name</label>
										<div class="col-sm-6">
											<form:input path="userregistration.lastname" type="text"
												class="form-control" id="inputlastname"
												placeholder="Last Name" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputcompanyname" class="col-sm-4 control-label">Company
											Name</label>
										<div class="col-sm-6">
											<form:input path="userregistration.companyname" type="text"
												class="form-control" id="inputcompanyname"
												placeholder="Company Name" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputdesignation" class="col-sm-4 control-label">Designation
										</label>
										<div class="col-sm-6">
											<form:input path="userregistration.designation" type="text"
												class="form-control" id="inputdesignation"
												placeholder="Designation" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-4 control-label">Email</label>
										<div class="col-sm-6">
											<form:input path="userregistration.emailid" type="email"
												class="form-control" id="inputEmail3" placeholder="Email" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputpassword" class="col-sm-4 control-label">Password</label>
										<div class="col-sm-6">
											<form:input path="userregistration.password" type="password"
												class="form-control" id="inputpassword"
												placeholder="Password" required="true" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputphone" class="col-sm-4 control-label">Phone
											Number</label>
										<div class="col-sm-6">
											<form:input path="userregistration.phonenumber" type="text"
												class="form-control" id="inputphone"
												placeholder="Phone Number" aria-describedby="name-format"
												min="5" max="10" pattern="[0-9]{5,10}" required="true"
												title="Please enter correct phone number" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-6">
											<div class="checkbox">
												<label> <form:checkbox
														path="userregistration.isrememberme" value="remember" />
													Remember me
												</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-6">
											<button type="submit" class="btn btn-default">Sign
												Up</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-info">
							<div class="text-center panel-heading">Order details</div>
							<form class="form-horizontal panel-body text-center" role="form"
								modelAttribute="bookingdata" style="" action="home_success"
								method="POST">
								<div class="form-group">
									<label for="Source" class="col-sm-4 control-label">Source</label>
									<div class="col-sm-6">
										<form:select path="bookingdata.fromAddress.city"
											value="${order.fromAddress.city}" class="form-control"
											id="source" disabled="true">
											<form:option value="--">--Source--</form:option>
											<form:option value="Ahmedabad">Ahmedabad</form:option>
											<form:option value="Ajmer">Ajmer</form:option>
											<form:option value="Anand">Anand</form:option>
											<form:option value="Banswara">Banswara</form:option>
											<form:option value="Bharuch">Bharuch</form:option>
											<form:option value="Bhilwara">Bhilwara</form:option>
											<form:option value="Bundi">Bundi</form:option>
											<form:option value="Chittaurgarh">Chittaurgarh</form:option>
											<form:option value="Dungarpur">Dungarpur</form:option>
											<form:option value="Gandhinagar">Gandhinagar</form:option>
											<form:option value="Jaipur">Jaipur</form:option>
											<form:option value="Jhalawar">Jhalawar</form:option>
											<form:option value="Kandla Port">Kandla Port</form:option>
											<form:option value="Kota">Kota</form:option>
											<form:option value="Mundra">Mundra Port</form:option>
											<form:option value="Nagaur">Nagaur</form:option>
											<form:option value="Sikar">Sikar</form:option>
											<form:option value="Tong">Tong</form:option>
											<form:option value="Udaipur">Udaipur</form:option>
											<form:option value="Vadodara">Vadodara</form:option>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<label for="Destination" class="col-sm-4 control-label">Destination</label>
									<div class="col-sm-6">
										<form:select path="bookingdata.toAddress.city"
											class="form-control" id="destination"
											value="${order.toAddress.city}" disabled="true">
											<form:option value="--">--Destination--</form:option>
											<form:option value="Ahmedabad">Ahmedabad</form:option>
											<form:option value="Ajmer" hidden="true">Ajmer</form:option>
											<form:option value="Anand">Anand</form:option>
											<form:option value="Banswara">Banswara</form:option>
											<form:option value="Bharuch">Bharuch</form:option>
											<form:option value="Bhilwara">Bhilwara</form:option>
											<form:option value="Bundi">Bundi</form:option>
											<form:option value="Chittaurgarh">Chittaurgarh</form:option>
											<form:option value="Dungarpur">Dungarpur</form:option>
											<form:option value="Gandhinagar">Gandhinagar</form:option>
											<form:option value="Jaipur">Jaipur</form:option>
											<form:option value="Jhalawar">Jhalawar</form:option>
											<form:option value="Kandla Port">Kandla Port</form:option>
											<form:option value="Kota">Kota</form:option>
											<form:option value="Mundra">Mundra Port</form:option>
											<form:option value="Nagaur">Nagaur</form:option>
											<form:option value="Sikar">Sikar</form:option>
											<form:option value="Tong">Tong</form:option>
											<form:option value="Udaipur">Udaipur</form:option>
											<form:option value="Vadodara">Vadodara</form:option>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<label for="weight" class="col-sm-4 control-label">Weight</label>
									<div class="col-sm-6">
										<form:select path="bookingdata.gwt" class="form-control"
											id="weight" value="${order.gwt}" disabled="true">
											<form:option value="0">--Weight(Tonnage)--</form:option>
											<form:option value="9">9 Ton</form:option>
											<form:option value="10">10 Ton</form:option>
											<form:option value="15">15 Ton</form:option>
											<form:option value="20">20 Ton</form:option>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<label for="Date" class="col-sm-4 control-label">Pick-Up
										Date</label>
									<div class="col-sm-6">
										<div class='input-group date' id='datepicker'>
											<form:input path="bookingdata.pickupDate" id='pickUpDate'
												type='text' class="form-control" value="${order.pickupDate}"
												disabled="true" />
											<span class="input-group-addon"><span
												class="glyphicon glyphicon-calendar"></span> </span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="Date" class="col-sm-4 control-label">Pick-Up
										Time</label>
									<div class="col-sm-6">
										<div class='input-group date' id='timepicker'>
											<form:input path="bookingdata.pickUpTime" type='text'
												class="form-control" value="${order.pickUpTime}"
												disabled="true" />
											<span class="input-group-addon"><span
												class="glyphicon glyphicon-time"></span> </span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10 ">
										<button type="submit" class="btn btn-info">
											Book &nbsp;&nbsp;<i class="fa fa-truck"></i>
										</button>
									</div>
								</div>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

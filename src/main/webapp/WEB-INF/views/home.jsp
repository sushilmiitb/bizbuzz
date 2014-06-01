<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insertDefinition name="homepage">
	<tiles:putAttribute name="body">
		<div class="container-fluid" style="padding-top: 70px">
			<div class="container">
				<div class="row">
					<div class="col-md-3">
						<form class="form-inline" role="form"
							style="margin-bottom: 15px; margin-top: 25px;" action="track"
							method="GET" modelAttribute="trackid">
							<div class="form-group">
								<input type="text" class="form-control control-label"
									id="trackid" name="trackid" placeholder="Tracking ID">
							</div>
							<button type="submit" class="btn btn-info">Track</button>
							<c:if test="${not empty '${errormessage}' }">
								<div style="color: #FF0000">${errormessage}</div>
							</c:if>
						</form>
					</div>
					<div class="col-md-6">
						<h2 class="text-center">A hi-tech, reliable and easy way to
							move cargo in trucks</h2>
					</div>
					<div class="col-md-3">
						<h3>
							<span class="glyphicon glyphicon-earphone"
								style="color: #7FBA00;"></span> <span class="text-right">
								<strong>+91-98251-25088 </strong>
							</span>
						</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div style="margin-top: 6%">
							<div class="panel panel-info">
								<div class="text-center panel-heading">Full Truck Load</div>
								<form class="form-horizontal panel-body text-center" role="form"
									modelAttribute="bookingdata" style="" action="home_success"
									method="POST">
									<div class="form-group">
										<label for="Source" class="col-sm-4 control-label">Source</label>
										<div class="col-sm-6">
											<form:select path="bookingdata.fromAddress.city"
												class="form-control" id="source">
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
												class="form-control" id="destination">
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
												id="weight">
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
											<div class="input-group date" id="datepicker1">
												<form:input path="bookingdata.pickupDate" type="text"
													class="form-control" id="pickupDate" readonly="true"/>
												<span class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span> </span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="Date" class="col-sm-4 control-label">Pick-Up
											Time</label>
										<div class="col-sm-6">
											<div class="input-group date" id="timepicker1">
												<form:input path="bookingdata.pickUpTime" type="text"
													class="form-control" id="pickUpTime" readonly="true"/>
												<span class="input-group-addon"><span
													class="glyphicon glyphicon-time"></span> </span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-info">Book &nbsp;&nbsp;<i
													class="fa fa-truck"></i></button>
										</div>
									</div>
								</form>
								<c:if test="${not empty '${successmessage}' }">
									<div style="color: #008000">${successmessage}</div>
								</c:if>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div style="margin-top: 6%">
							<div class="panel panel-default">
								<div class="panel-body" style="background-color: #DDD;">
									<div class="callbacks_container">
										<ul class="rslides" id="slider1"
											style="border: 2px solid #DDD; background-color: #FFFFFF;">
											<li><img class="img-responsive"
												src="/cargo/static/img/1. next generation trucking provider_text.jpg"
												alt="Slide 2" /></li>
											<li><img class="img-responsive"
												src="/cargo/static/img/2. execution at fast speed_text.jpg"
												alt="Slide 3" /></li>
											<li><img class="img-responsive"
												src="/cargo/static/img/3. seamless technology_text.jpg"
												alt="Slide 4" /></li>
											<li><img class="img-responsive"
												src="/cargo/static/img/4. No more sleepless nights (cut the extreme 2 men).jpg"
												alt="Slide 4" /></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="text-center">
							<h2>
								<span class="label label-info">Who are we?</span>
							</h2>
						</div>
						<div class="panel panel-info panel-body">
							<ul class="list-unstyled">
								<li><p>
										<img src="/cargo/static/img/accept.png"> <span>We
											are a group of IIM and IIT graduates building the next
											generation solution for transporting cargo. We envision to
											bring the same level of efficiency in cargo transportation
											that people expect for their own movement in flights. We use
											state of the art technologies and are changing the way people
											think and operate in the trucking industry. When we promise
											something, we deliver it. No excuses.</span>
									</p></li>
								<li><p>
										<img src="/cargo/static/img/accept.png"> <span>We
											would be happy to understand your trucking needs and look
											forward to serving you. Please <a
											href="http://www.cargobolt.com/cargo/contactus">Contact
												Us </a> and we&apos;ll get back to you.
										</span>
									</p></li>
							</ul>
						</div>
					</div>
					<div class="col-md-4">
						<div class="text-center">
							<h2>
								<span class="label label-info">Why choose us?</span>
							</h2>
						</div>
						<div class="panel panel-info panel-body">
							<ul class="list-unstyled">
								<li><h5>
										<img src="/cargo/static/img/accept.png"> <span>Guaranteed
											delivery times</span>
									</h5></li>
								<li><h5>
										<img src="/cargo/static/img/accept.png"> <span>Book
											a truck in 60 seconds</span>
									</h5></li>
								<li><h5>
										<img src="/cargo/static/img/accept.png"> <span>Competitive
											pricing</span>
									</h5></li>
								<li><h5>
										<img src="/cargo/static/img/accept.png"> <span>Online
											and offline booking</span>
									</h5></li>
								<li><h5>
										<img src="/cargo/static/img/accept.png"> <span>Online
											real-time tracking</span>
									</h5></li>
								<li><h5>
										<img src="/cargo/static/img/accept.png"> <span>Customer
											loyalty rewards</span>
									</h5></li>
								<li><h5>
										<img src="/cargo/static/img/accept.png"> <span>Hassle-free
											billing</span>
									</h5></li>
							</ul>
						</div>
					</div>
					<div class="col-md-4">
						<div class="text-center">
							<h2>
								<span class="label label-info">Services</span>
							</h2>
						</div>
						<div class="panel panel-info panel-body">
							<p>For a manufacturer or a wholesaler, CargoBolt is a
								one-stop solution for all trucking needs. Currently, we service
								full-truck loads of all sizes in Gujarat, Rajasthan, Haryana and
								Delhi. We offer customized pick-up and delivery services to best
								serve the needs of our various customers.</p>
							<p>
								Once you are registered with us, you can book trucks both online
								and offline. We provide 100% reliable cargo movement and
								unbeatable execution. <a
									href="http://www.cargobolt.com/cargo/register">Register</a>
								here to get started.
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<!--   <div class="col-md-4">
						<div class="text-center">
							<h2>
								<span class="label label-default">review 1</span>
							</h2>
						</div>
						<div class="well well-lg"></div>
					</div> 
					<div class="col-md-4">
						<div class="text-center">
							<h2>
								<span class="label label-default">review 2</span>
							</h2>
						</div>
						<div class="well well-lg"></div>
					</div> 
					<div class="col-md-4">
						<div class="text-center">
							<h2>
								<span class="label label-default">review 3</span>
							</h2>
						</div>
						<div class="well well-lg"></div>
					</div> -->
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

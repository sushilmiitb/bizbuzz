<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insertDefinition name="shipperHome">
	<tiles:putAttribute name="body">
		<div class="container-fluid" >
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<form class="form-horizontal" role="form" modelAttribute="order"
									method="POST" id="addNewTruckOrder">
									<div class="form-group">
										<label for="Name" class="col-sm-7 control-label">Pick Up Details</label>
									</div>
									<div class="form-group">
										<label for="Name" class="col-sm-4 control-label">Name</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="Name"
												placeholder="Name">
										</div>
									</div>
									<div class="form-group">
										<label for="line1" class="col-sm-4 control-label">Address
											Line 1</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="line1"
												placeholder="Line 1">
										</div>
									</div>
									<div class="form-group">
										<label for="line2" class="col-sm-4 control-label">Address
											Line 2</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="line2"
												placeholder="Line 2">
										</div>
									</div>
									<div class="form-group">
										<label for="landmark" class="col-sm-4 control-label">Landmark</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="landmark"
												placeholder="Landmark">
										</div>
									</div>
									<div class="form-group">
										<label for="city" class="col-sm-4 control-label">City</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="city"
												placeholder="City">
										</div>
									</div>
									<div class="form-group">
										<label for="postalcode" class="col-sm-4 control-label">Postal
											Code</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="postalcode"
												placeholder="Postal Code">
										</div>
									</div>
									<div class="form-group">
										<label for="phonenumber" class="col-sm-4 control-label">Phone
											Number</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="phonenumber"
												placeholder="Phone Number">
										</div>
									</div>
									<div class="rule" style = "margin:3%"></div>
									<div class="form-group">
										<label for="Name" class="col-sm-7 control-label">Drop Details</label>
									</div>
									<div class="form-group">
										<label for="Name" class="col-sm-4 control-label">Name</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="Name"
												placeholder="Name">
										</div>
									</div>
									<div class="form-group">
										<label for="line1" class="col-sm-4 control-label">Address
											Line 1</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="line1"
												placeholder="Line 1">
										</div>
									</div>
									<div class="form-group">
										<label for="line2" class="col-sm-4 control-label">Address
											Line 2</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="line2"
												placeholder="Line 2">
										</div>
									</div>
									<div class="form-group">
										<label for="landmark" class="col-sm-4 control-label">Landmark</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="landmark"
												placeholder="Landmark">
										</div>
									</div>
									<div class="form-group">
										<label for="city" class="col-sm-4 control-label">City</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="city"
												placeholder="City">
										</div>
									</div>
									<div class="form-group">
										<label for="postalcode" class="col-sm-4 control-label">Postal
											Code</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="postalcode"
												placeholder="Postal Code">
										</div>
									</div>
									<div class="form-group">
										<label for="phonenumber" class="col-sm-4 control-label">Phone
											Number</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="phonenumber"
												placeholder="Phone Number">
										</div>
									</div>
									<div class="rule"></div>
									<div class="form-group">
										<label for="Date" class="col-sm-4 control-label">Pick-Up
											Date</label>
										<div class='col-sm-5'>
											<div class='input-group date' id='datepicker1'>
												<input type='text' class="form-control" id="pickupDate" readonly="true" /> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span> </span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="Date" class="col-sm-4 control-label">Pick-Up
											Time</label>
										<div class="col-sm-5">
											<div class='input-group date' id="timepicker1">
												<input  type='text' class="form-control" id="pickUpTime" readonly="true"/> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-time"></span> </span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="trucktype" class="col-sm-4 control-label">Truck
											Type</label>
										<div class="col-sm-5">
											<select class="form-control" id="trucktype">
												<option value="--">--Type of Truck--</option>
												<c:forEach var="item" items="${genericTruckTypeList}">
													<option value="${item}">${item}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="goodstype" class="col-sm-4 control-label">Goods
											Type</label>
										<div class="col-sm-5">
											<select class="form-control" id="goodstype">
												<option value="--">--Type of Good--</option>
												<c:forEach var="item" items="${goodsTypeSpecificNameList}">
													<option value="${item}">${item}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="insurancetype" class="col-sm-4 control-label">Insurance
											Type</label>
										<div class="col-sm-5">
											<select class="form-control" id="insurancetype">
												<option value="--">--Type of Insurance--</option>
												<c:forEach var="item" items="${insuranceTypeList}">
													<option value="${item}">${item}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-5">
											<button type="submit" class="btn btn-info">Book &nbsp;&nbsp;<i
													class="fa fa-truck"></i></button>
										</div>
									</div>
								</form>
							</div>
							<div class="col-md-2"></div>
						</div>
					</div>
				</div>
			</div>
		</div>



	</tiles:putAttribute>
</tiles:insertDefinition>
// JavaScript Document

var divSelected = null;

function changeStyle(active, deactive1, deactive2, deactive3, deactive4) {
	if (divSelected != null)
		divSelected.className = '';
	divSelected = document.getElementById(active);
	document.getElementById(active).className = 'active';
	document.getElementById(deactive1).className = '';
	document.getElementById(deactive2).className = '';
	document.getElementById(deactive3).className = '';
	document.getElementById(deactive4).className = '';

}

function activeDropdown(active) {
	document.getElementById(active).getElementsByClassName("dropdown-menu")
			.item(0).style.display = "block";
	document.getElementById(active).className = 'active';
}

function closeDropdown(deactive) {
	document.getElementById(deactive).getElementsByClassName("dropdown-menu")
			.item(0).style.display = "none";
	document.getElementById(deactive).className = '';
}

$(window).load(function() {
	$("#slider1").responsiveSlides({
		auto : true,
		pager : false,
		nav : true,
		speed : 500,
		namespace : "callbacks",
		before : function() {
			$('.events').append("<li>before event fired.</li>");
		},
		after : function() {
			$('.events').append("<li>after event fired.</li>");
		}
	})
});

$(function() {
	$('#datepicker1').datetimepicker({
		pickTime : false,
		showToday : true,
		minDate : moment().local().subtract('days', 1),
		defaultDate : moment().local()
	});
	$('#timepicker1').datetimepicker({
		pickDate : false,
		defaultDate : moment().local()
	});
	$('#pickupDate').datetimepicker({
		pickTime : false,
		showToday : true,
		minDate : moment().local().subtract('days', 1),
		defaultDate : moment().local()
	});
	$('#pickUpTime').datetimepicker({
		pickDate : false,
		defaultDate : moment().local()
	});
});

function initialize() {
	var myLatlng = new google.maps.LatLng(23.034619, 72.532652);

	var mapOptions = {
		center : myLatlng,
		zoom : 15,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	var map = new google.maps.Map(document.getElementById("map-canvas"),
			mapOptions);

	var marker = new google.maps.Marker({
		position : myLatlng,
		map : map,
		title : "CargoBolt"
	});
}

google.maps.event.addDomListener(window, 'load', initialize);

$(function() {
	// Setup drop down menu
	$('.dropdown-toggle').dropdown();

	// Fix input element click problem
	$('.dropdown input, .dropdown label').click(function(e) {
		e.stopPropagation();
	});
});
<script type="text/javascript">
$(document).ready(function() {
	alert("Document.ready");
	//As Per Native Code----------------------------------------------------------------
	var contact = {
		// Application Constructor
		initialize: function() {
			this.bindEvents();
		},
		// Bind Event Listeners
		//
		// Bind any events that are required on startup. Common events are:
		// 'load', 'deviceready', 'offline', and 'online'.
		bindEvents: function() {
			document.addEventListener('deviceready', this.onDeviceReady(), false);
		},
		onDeviceReady: function() {
	
			var options = new ContactFindOptions();
			options.filter = "";
			options.multiple = true;
			var filter = ["displayName", "phoneNumber"];
			navigator.contacts.find(filter, this.onSuccess() , this.onError() , options);
	
		},
		onSuccess: function(contacts){
			alert(contacts.length);	
			var total ="";
			for (var i = 0; i < contacts.length; i++) {
				for (var j = 0; j < contacts[i].phoneNumbers.length; j++) {
					total =  total + "Contact Name: "         + contacts[i].displayName + "\n" 
					+ "Value: " + contacts[i].phoneNumbers[j].value + "\n";
				}                                           
			}
	
			alert(total);
		},
		onError: function(error){
			console.log("Contacts Error : "+error);
		}
	};
	//	----------------------------------------------------------------

	//	Dynamically loading cordova.js              -------------------------------------------------------------
	/*
	var XMLHttpArray = [
	    function() {return new XMLHttpRequest()},
	    function() {return new ActiveXObject("Msxml2.XMLHTTP")},
	    function() {return new ActiveXObject("Msxml2.XMLHTTP")},
	    function() {return new ActiveXObject("Microsoft.XMLHTTP")}
	];
	 function createXMLHTTPObject(){
		    var xmlhttp = false;
		    for(var i=0; i<XMLHttpArray.length; i++){
		            try{
		                    xmlhttp = XMLHttpArray[i]();
		            }catch(e){
		                    continue;
		            }
		            break;
		    }
		    return xmlhttp;
		}                      
	 */
	function loadjsfile(filename){
		/*	
		var xhrObj = createXMLHTTPObject();
		// open and send a synchronous request
		xhrObj.open('GET', filename, false);
		xhrObj.send('');
		// add the returned content to a newly created script tag
		var fileref = document.createElement('script');
		fileref.type = "text/javascript";
		fileref.text = xhrObj.responseText;
		document.getElementsByTagName('head')[0].appendChild(fileref);
		 */		
		var script=document.createElement('script');
		script.setAttribute("type","text/javascript");

		script.setAttribute("src", filename);
		if (typeof script!="undefined")
			document.getElementsByTagName("head")[0].appendChild(script);

		if (script.readyState){  //IE
			script.onreadystatechange = function(){
				if (script.readyState == "loaded" || script.readyState == "complete"){
					script.onreadystatechange = null;
					alert("successfully loaded.");
				}
			};
		} else {  //Others
			script.onload = function(){
				loadjsfile("<c:url value='/static/js/cordova/cordova_plugins.js'/>");
				//      	alert("successfully loaded.");
			};
		}

	}

	function allContactJs(){


		loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/Contact.js'/>");
		loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactAddress.js'/>");
		loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactError.js'/>");
		loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactField.js'/>");
		loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactFieldType.js'/>");
		loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactFindOptions.js'/>");
		loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactName.js'/>");
		loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/ContactOrganization.js'/>");
		loadjsfile("<c:url value='/static/js/cordova/plugins/org.apache.cordova.contacts/www/contacts.js'/>");
	}

	var ua = navigator.userAgent.toLowerCase();   
	var isAndroid = ua.indexOf("android") > -1; //&& ua.indexOf("mobile");
	if(isAndroid) {
		alert("This is android platform.");
		loadjsfile("<c:url value='/static/js/cordova/cordova.js'/>");

		//		allContactJs();

	}
	//----------------------------------------------------------------------------------

//	find all contacts			            -----------------------------------------------------------------

//	document.addEventListener("deviceready", function(){alert("In the ondevice ready function.");}, false);

	var options = new ContactFindOptions();
	options.filter = "";
	options.multiple = true;
	var filter = ["displayName", "phoneNumber"];
	alert("filter :" +filter);
	navigator.contacts.find(filter, this.onSuccess, this.onError, options);

	function onSuccess(contacts) {
		alert(contacts.length);
//		for (var i = 0; i < contacts.length; i++) {
//		for (var j = 0; j < contacts[i].phoneNumbers.length; j++) {
//		alert("Contact Name: "         + contacts[i].displayName ? contacts[i].displayName : contacts[i].nickName    + "\n" +
//		"Type: "      + contacts[i].phoneNumbers[j].type  + "\n" +
//		"Value: "     + contacts[i].phoneNumbers[j].value + "\n" +
//		"Preferred: " + contacts[i].phoneNumbers[j].pref);
//		}
//		}
	}
	function onError(contactError) {
		alert('onError!');
	}

//	---------------------------------------------------------------------------------

	$('#select_phonebook_button').click(function(event) {
		event.preventDefault();

		var ua = navigator.userAgent.toLowerCase();   
		var isAndroid = ua.indexOf("android") > -1; //&& ua.indexOf("mobile");
		if(isAndroid) {
			contact.initialize();
		}
		else{
			alert("This is not android platform.");
		}

	});		


	$('#seller_viewconnection_form').submit(function(event) {
		var json = { "userId" : $('#seller_viewconnection_phonenumber').val(),
				"groupId" : $('#seller_viewconnection_privategroupoption').val()
		};
		console.log("test", JSON.stringify(json));
		$.ajax({
			url: $("#seller_viewconnection_form").attr( "action"),
			data: JSON.stringify(json),
			type: "POST",

			beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data) {
			var baseUrl = "/seller/viewconnection/";
			var baseDeleteUrl = "/seller/deleteconnection/";
			var baseGroupUrl = "/seller/viewgroup/";
			var respContent="";

			var linkElement = document.createElement('a');
			$(linkElement).addClass("list-group-item");
			$(linkElement).attr("href", "<c:url value='/seller/viewconnection/'/>"+data.id);
			var divOut = document.createElement('div');
			$(divOut).addClass('row');
			var divInner = document.createElement('div');
			$(divInner).addClass('col-xs-4 col-sm-4 col-md-4 col-lg-4');
			$(divInner).html(data.firstName + " " + data.middleName + " " + data.lastName);
			$(divOut).append(divInner);

			var divInner = document.createElement('div');
			$(divInner).addClass('col-xs-4 col-sm-4 col-md-4 col-lg-4');
			$(divInner).html(data.phoneNumber);
			$(divOut).append(divInner);

			var divInner = document.createElement('div');
			$(divInner).addClass('col-xs-4 col-sm-4 col-md-4 col-lg-4');
			$(divInner).html(data.groupName);
			$(divOut).append(divInner);

			$(linkElement).append(divOut);

			$(linkElement).insertAfter($(".list-group-item").filter(".heading").first());
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
//call the specific function on document ready
var debug=false;
var baseStaticUrl = "/bizbuzz/static";

var ua = navigator.userAgent.toLowerCase();   
var isAndroid = ua.indexOf("android") > -1; //&& ua.indexOf("mobile");
if(isAndroid) {
	if(debug)
		alert("This is android platform.");

	$("#phonebookbox").show();
	//As Per Native Code----------------------------------------------------------------
	var contact = {
			// Application Constructor
			initialize: function() {
		if(debug)
			alert("Inside contact initialization");
		this.bindEvents();
	},
	bindEvents: function() {
		document.addEventListener('deviceready', this.onDeviceReady(), false);
	},
	onDeviceReady: function() {
		if(debug)
			alert("OnDeviceReady: ");
		var options = new ContactFindOptions();
		if(debug)
			alert("OnDeviceReady: Sending contact request");
		options.filter = "";
		options.multiple = true;
		var filter = ["displayName", "phoneNumber"];
		navigator.contacts.find(filter, this.onSuccess , this.onError , options);
	},
	onSuccess: function(contacts){
		if(debug)
			alert("contact length: " + contacts.length);
		//for (var i = 0; i < contacts.length; i++) {
		//alert("contact:"+contacts[i].displayName);
		var validContacts = [];
		$(contacts).each(function (i) { //populate contact_selection options 
			if(contacts[i].phoneNumbers!=null){
				validContacts.push(contacts[i]);
			}
		});
		validContacts.sort(function(a, b) {
			var aID = a.displayName;
			var bID = b.displayName;
			return (aID == bID) ? 0 : (aID > bID) ? 1 : -1;
		});

		$("#contact_selection").html(""); //reset contact_selection options
		var strVar="";
		$(validContacts).each(function (i) { //populate contact_selection options
			strVar += "<div class=\"row list-group-item\" onclick='contact.processContact(this);' >";
			strVar += "	<div class=\"col-xs-8 col-sm-8 col-md-8 col-lg-8\">";
			strVar += 		validContacts[i].displayName+"<\/div>";
			strVar += "	<div class=\"col-xs-4 col-sm-4 col-md-4 col-lg-4\">";
			strVar += 		validContacts[i].phoneNumbers[0].value+"<\/div>";
			strVar += "<\/div>";
		});
		$("#contact_selection").append(strVar);
		function processContact(event){
			var children = $(event).children();
			if(typeof children !== 'undefined' && children.length==2){
				var number = children[1].html();
				$("#seller_viewconnection_phonenumber").attr("value",number);
			}
		}
	},
	onError: function(error){
		if(debug)
			alert("mobileadaptation.js.loadmobileconnectionmodule.Contacts Error : "+error);
		console.log("Contacts Error : "+error);
	}
	};

	function onCordovaLoad(){
		if(debug)
			alert("cordova Loaded");
		onContactsObjectAvailable();
		/*$("#phonebook_button").click(function(){
				contact.initialize();
			});*/
//		var delay = 1000;
//		setTimeout(contact.initialize, delay);
	}

	function onContactsObjectAvailable() {
		if (typeof(ContactFindOptions) !== 'undefined') {
			contact.initialize();
		} else {
			setTimeout(function () {
				onContactsObjectAvailable();
			}, 500);
		}
	}
	loadjsfile(baseStaticUrl+"/js/cordova/cordova.js", onCordovaLoad);
}
else{
	$("#phonebookbox").hide();
}

function loadjsfile(filename, onload){	
	if(debug)
		alert("mobileadaptation.js/loadjsfile:filename->"+filename);
	var script=document.createElement('script');
	script.setAttribute("type","text/javascript");

	script.setAttribute("src", filename);
	if (typeof script!="undefined")
		document.getElementsByTagName("head")[0].appendChild(script);

	if (script.readyState){  //IE
		script.onreadystatechange = function(){
			if (script.readyState == "loaded" || script.readyState == "complete"){
				script.onreadystatechange = null;
				//do your stuff
			}
		};
	} else {  //Others
		script.onload = onload;
	}
}


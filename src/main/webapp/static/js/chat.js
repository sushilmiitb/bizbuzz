function initializeChatPanel(){
	$(".chat-content").height(Math.floor($(window).height()*0.8));
	$(".chat-panel").height(Math.floor($(".chat-content").height()));
	var maxHeight = Math.floor($(".chat-panel").height())-70;
	$(".chat-body").css({"max-height": maxHeight+"px"});
}

function initializeChatPanel(bodyBottomOffset){
	$(".chat-content").height(Math.floor($(window).height()*0.8));
	$(".chat-panel").height(Math.floor($(".chat-content").height()));
	var maxHeight = Math.floor($(".chat-panel").height())-bodyBottomOffset;
	$(".chat-body").css({"max-height": maxHeight+"px"});
}

function setChatPanelToggleCallback(){
	$( ".glyphicon-comment" ).click(function() {
		$( ".chat-content" ).animate({
			height: "toggle"
		}, 200, function() {
			// Animation complete.
			// Send info of chat visibility state
			var url = "/bizbuzz/chat/controller";
			var parentObj = $(".chat-panel");
			var isChatPanelVisible = "false";
			if($(".chat-content").length && $(".chat-content").is(":visible")){
				isChatPanelVisible = "true";
			}
			$.ajax({
				url: url,
				type: "GET",
				data: {chatpage: "savevisibilitystate", ischatpanelvisible: isChatPanelVisible},
				success: function(data) {
				},
				error: function(){
				}
			});
		});
	});
}

function setChatBackButtonCallback(){
	$(".chat-back").click(function(){
		var url = "/bizbuzz/chat/controller";
		var parentObj = $(".chat-panel");
		$(".chat-body").addClass("div-loader");
		$.ajax({
			url: url,
			type: "GET",
			data: {chatpage: "back"},
			success: function(data) {
			removeChatPanelToggleCallback();
			removeChatBackButtonCallback();
			$(parentObj).children().remove();
			$(parentObj).append(data);
		},
		error: function(){
		}
		});
	});
}

function removeChatPanelToggleCallback(){
	$( ".glyphicon-comment").unbind("click");
}

function removeChatBackButtonCallback(){
	$(".chat-back").unbind("click");
}

function setChatPanelResizeCallback(){
	$(window).resize(function(){
		$(".chat-content").height(Math.floor($(window).height()*0.8));
		$(".chat-panel").height(Math.floor($(".chat-content").height()));
		var maxHeight = Math.floor($(".chat-panel").height())-70;
		$(".chat-body").css({"max-height": maxHeight+"px"});
	});
}

function setChatPanelResizeCallback(bodyBottomOffset){
	$(window).resize(function(){
		$(".chat-content").height(Math.floor($(window).height()*0.8));
		$(".chat-panel").height(Math.floor($(".chat-content").height()));
		var maxHeight = Math.floor($(".chat-panel").height())-bodyBottomOffset;
		$(".chat-body").css({"max-height": maxHeight+"px"});
	});
}

function removeChatPanelResizeCallback(){
	$(window).unbind("resize");
}

function loadCurrentChatRoomState(){
	var url = "/bizbuzz/chat/controller";
	var parentObj = $(".chat-panel");
	$.ajax({
		url: url,
		type: "GET",
		data: {chatpage: "determine"},
		success: function(data) {
			$(parentObj).append(data);
		},
		error: function(){
		}
	});
}

function loadChatRoomList(){
	var url = "/bizbuzz/chat/controller";
	var parentObj = $(".chat-panel");
	var params = 'chatpage=listofchatrooms';
	$.ajax({
		url: url,
		type: "GET",
		data: {chatpage: "listofchatrooms"},
		success: function(data) {
			$(parentObj).append(data);
		},
		error: function(){
		}
	});
}

function loadNormalChatRoom(url){
	var parentObj = $(".chat-panel");
	$(".chat-body").addClass("div-loader");
	$.ajax({
		url: url,
		type: "GET",
		success: function(data) {
		removeChatPanelToggleCallback();
		removeChatBackButtonCallback();
		$(parentObj).children().remove();
		$(parentObj).append(data);
	},
	error: function(){
	}
	});
}

function insertChatMessage(msg, isSelf){
	var lines = msg.split("\n");
	var str = "";
	if(isSelf==true){
		str += "<div class=\"ind-chat-panel ind-chat-panel-right\">";
	}
	else{
		str += "<div class=\"ind-chat-panel ind-chat-panel-left\">";
	}
	str += "<div class=\"arrow\"><\/div>";
	str += "<div class=\"ind-chat-body\">";
	str += "<p>";
	for (var i=0; i<lines.length; i++){
		str += "<span>"+lines[i]+"<\/span>";
	}
	str += "<\/p>";
	str += "<\/div>";
	str += "<\/div>";
	$(".chat-body").append(str);
	$(".chat-body").scrollTop(1000000);
}

function initializeNormalChatRoom(socketUrl, userId, senderId, chatroomId){
	removeChatPanelResizeCallback();
	initializeChatPanel(128);
	setChatPanelResizeCallback(128);
	setChatPanelToggleCallback();
	setChatBackButtonCallback();
	$(".chat-body").scrollTop(1000000);
	/****websocket code starts***/
	if (!window.console) {
		console = {log: function() {}};
	}
	function refresh() {
		console.log("Refreshing data tables...");
	}

	var socket = $.atmosphere;
	var request = new $.atmosphere.AtmosphereRequest();        
	request.url = socketUrl;
	request.contentType = "application/json";
	request.transport = 'websocket';
	request.fallbackTransport = 'long-polling';

	request.onOpen = function(response){
		console.log('onOpen: connection opened using transport:' + response.transport);
		subSocket.push(JSON.stringify({"message":"0Open0","userId": userId,"chatroomId":chatroomId,"itemId":0}));
	}

	request.onReconnect = function(request, response){
		console.log('onReconnect:');
		socket.info("Reconnecting");
	}

	request.onMessage = function(response){
		var message = response.responseBody;
		var result;
		try {
			result =  $.parseJSON(message);
		} catch (e) {
			console.log("An error ocurred while parsing the JSON Data: " + message.data + "; Error: " + e);
			return;
		}
		if(result.senderId==senderId){
		}
		else{
			insertChatMessage(result.message, false);
		}
	}

	request.onError = function(response){
		content.html($('<p>', { text: 'Sorry, but '
			+ 'there some problem with your '
			+ 'socket or the server is down' }));
	}

	var subSocket = socket.subscribe(request);               //subSocket is used to push messages to the server.

	$('#message-button').click(function() {
		var message = $('#message-field').val();
		var strippedStr = message.trim();
		if(strippedStr == ""){
			console.log('SentMessage: '+'Empty String');
			$('#message-field').val("");
			return;
		}
		console.log('SentMessage:'+message);
		$('#message-field').val("");
		insertChatMessage(message, true);
		subSocket.push(JSON.stringify({"message":message,"userId": userId,"chatroomId":chatroomId,"itemId":0}));
	});

	/****web socket code ends***/
}
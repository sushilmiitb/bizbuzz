var socket = $.atmosphere;

var pageStat;

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
	$( ".chat-toggle-btn" ).click(function() {
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
		if(pageStat=="listofchatrooms")
		{
			socket.unsubscribe();
		}
		else{
			alert(pageStat);
		}
			
		//$(".chat-panel").addClass("div-loader");
		loadDivLoader(parentObj);
		$.ajax({
			url: url,
			type: "GET",
			data: {chatpage: "back"},
			success: function(data) {
			$(".chat-panel").removeClass("div-loader");
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
	$( ".chat-toggle-btn").unbind("click");
}

function removeChatBackButtonCallback(){
	$(".chat-back").unbind("click");
}

/**
 * This function sets ui changes on chat-panel when window is resized
 * @return
 */
function setChatPanelResizeCallback(){
	$(window).resize(function(){
		$(".chat-content").height(Math.floor($(window).height()*0.8));
		$(".chat-panel").height(Math.floor($(".chat-content").height()));
		var maxHeight = Math.floor($(".chat-panel").height())-70;
		$(".chat-body").css({"max-height": maxHeight+"px"});
	});
}

/**
 * This function sets ui changes on chat-panel when window is resized when there is a bottom margin for 
 * chat body. This function is used when there is chat-input-area.
 * @param bodyBottomOffset: How much margin from chat-panel bottom does chat-body ends
 * @return
 */
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

/**
 * 
 * @param isShow: Do we have to make chat-panel visible after loading the current state.
 * @param itemId: If it is item-chat then this parameter contains itemid else undefined.
 * @param secondPersonId: In relevant cases this parameter contains id of the other person in chatroom.
 * @return
 */
function loadCurrentChatRoomState(isShow, itemId, secondPersonId){
	var url = "/bizbuzz/chat/controller";
	var data;
	if(itemId===undefined){//that means it is in normal chat mode and chatcontroller has to determine the state using session state variables
		data = {chatpage:"determine"};
	}else if(secondPersonId===undefined){//we have to load all chat rooms of that particular item
		data = {chatpage:"itemchatroomlist", itemId:itemId};
	}
	else{//we have to load individual item chat room
		data = {chatpage:"itemchatroom", itemId:itemId, secondPersonId:secondPersonId};
	}
	var parentObj = $(".chat-panel");
	$.ajax({
		url: url,
		type: "GET",
		data: data,
		success: function(data) {
			$(parentObj).append(data);
			if(isShow){
				$(".chat-content").show();
			}
		},
		error: function(){
		}
	});
}

/**
 * Load all chat rooms of the person.
 * @return
 */
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

/**
 * Load particular chatroom
 * @param url
 * @return
 */
function loadNormalChatRoom(url){
	var parentObj = $(".chat-panel");
	//$(".chat-panel").addClass("div-loader");
	loadDivLoader(parentObj);
	$.ajax({
		url: url,
		type: "GET",
		success: function(data) {
		$(".chat-panel").removeClass("div-loader");
		removeChatPanelToggleCallback();
		removeChatBackButtonCallback();
		removeChatPanelResizeCallback();
		$(parentObj).children().remove();
		$(parentObj).append(data);
		$(".chat-body").scrollTop(1000000);
	},
	error: function(){
	}
	});
}

/**
 * Load particular item chat room
 * @param chatroomId
 * @param itemId
 * @param fromPage
 * @return
 */
function loadItemChatRoom(chatroomId, itemId, fromPage){
	var parentObj = $(".chat-panel");
	//$(".chat-panel").addClass("div-loader");
	loadDivLoader(parentObj);
	var url = "/bizbuzz/chat/showitemchatroom/chatroomid/"+chatroomId+"/itemid/"+itemId+"/frompage/"+fromPage;
	$.ajax({
		url: url,
		type: "GET",
		success: function(data) {
		$(".chat-panel").removeClass("div-loader");
		removeChatPanelToggleCallback();
		removeChatBackButtonCallback();
		removeChatPanelResizeCallback();
		$(parentObj).children().remove();
		$(parentObj).append(data);
		$(".chat-body").scrollTop(1000000);
	},
	error: function(){
	}
	});
}

/**
 * Insert chat message after person enters chat or when chat is received from other person.
 * @param msg
 * @param isSelf
 * @return
 */
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

function changeState(stateOfPage){

	pageStat=stateOfPage;
}

/**
 * Initialize the chat room. It can be used for both normal chat room and item chatroom. It initializes the socket.
 * @param socketUrl
 * @param userId
 * @param senderId
 * @param chatroomId
 * @param itemId
 * @return
 */
function initializeNormalChatRoom(socketUrl, userId, senderId, chatroomId, itemId){
	var bottomOffset = 100;
	var baseInputHeight = 50;
	initializeChatPanel(bottomOffset);
	setChatPanelResizeCallback(bottomOffset);
	setChatPanelToggleCallback();
	setChatBackButtonCallback();
	$(".chat-body").scrollTop(1000000);
	
	/****Code for handling variable height for text area***/
	var previouslines = 1;
	var baseMessageFieldHeight;
	var baseMessageButtonHeight;
	//var baseChatInputAreaHeight = baseInputHeight;
	var baseBottomOffset = bottomOffset;
	$("textarea").on("keyup", function(e) {
	    /*if ($("textarea").attr("cols")) {
	        var cols = parseInt($("textarea").attr("cols")),
	            curPos = $('textarea').prop("selectionStart"),
	            result = Math.floor(curPos/cols);
	        var msg = (result < 1) ? "Cursor is on the First line!" : "Cursor is on the line #"+(result+1);
	        console.log($("p").text(msg).text());
	    }*/
		if(e.keyCode == '13') {
			sendChatCallback();
			return;
		}
		var lht = parseInt($('textarea').css('lineHeight'),10);
		var lines = Math.round($('textarea').prop('scrollHeight') / lht);
		if(!!window.chrome){
			lines = Math.round(($('textarea').prop('scrollHeight') - 26) / lht);
		}
		if(lines>previouslines){
			if(lines==2){
				baseMessageFieldHeight = $("#message-field").height();
				baseMessageButtonHeight = $("#message-button").height();
			}
			previouslines = lines;
			bottomOffset = bottomOffset+lht;
			initializeChatPanel(bottomOffset);
			removeChatPanelResizeCallback();
			setChatPanelResizeCallback(bottomOffset);
			$("#message-field").height($("#message-field").height()+lht);
			$("#message-button").height($("#message-button").height()+lht);
			//$(".chat-input-area").height($(".chat-input-area").height()+lht);
			$(".chat-body").scrollTop(1000000);
		}
		console.log(lines);
	});
	
	/****websocket code starts***/
	if (!window.console) {
		console = {log: function() {}};
	}
	function refresh() {
		console.log("Refreshing data tables...");
	}

	
	var request = new $.atmosphere.AtmosphereRequest();        
	request.url = socketUrl;
	request.contentType = "application/json";
	request.transport = 'websocket';
	request.fallbackTransport = 'long-polling';

	/**
	 * When the connection opens for the first time.
	 */
	request.onOpen = function(response){
		console.log('onOpen: connection opened using transport:' + response.transport);
		subSocket.push(JSON.stringify({"message":"0Open0","userId": userId,"chatroomId":chatroomId,"itemId":itemId}));
	}

	request.onReconnect = function(request, response){
		console.log('onReconnect:');
		socket.info("Reconnecting");
	}

	/**
	 * On receiving message from other person
	 */
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
		console.log("An error ocurred in web socket: " + response.responseBody);
		return;
	}

  var subSocket = socket.subscribe(request);               //subSocket is used to push messages to the server.

	/**
	 * Send the chat.
	 */
	function sendChatCallback(){
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
		
		if(previouslines>1){
			previouslines =1;
			bottomOffset = baseBottomOffset;
			initializeChatPanel(bottomOffset);
			removeChatPanelResizeCallback();
			setChatPanelResizeCallback(bottomOffset);
			$("#message-field").height(baseMessageFieldHeight);
			$("#message-button").height(baseMessageButtonHeight);
			//$(".chat-input-area").height(baseChatInputAreaHeight);
			
		}
		$("#message-field").focus();
		subSocket.push(JSON.stringify({"message":message,"userId": userId,"chatroomId":chatroomId,"itemId":itemId}));
	}
	$('#message-button').click(function(){
		sendChatCallback();
	});
	//sendChatCallback on pressing enterkey is handled in onkeyup
	/****web socket code ends***/
}

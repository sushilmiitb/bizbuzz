<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="chat">
	
	<tiles:putAttribute name="title">
		BizBuzz-Chatroom
	</tiles:putAttribute>
	
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			
		</script>
	</tiles:putAttribute>
<tiles:putAttribute name="header">
<link rel="stylesheet" href="<c:url value='/static/css/main.css'/>" type="text/css"> 
	<script src="<c:url value='/static/js/jquery/jquery.js'/>"></script>
    <script src="<c:url value='/static/js/jquery/jquery.atmosphere.js'/>"></script>
    <script src="<c:url value='/static/js/jquery/jquery.tmpl.min.js'/>"></script>
  
</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<%-- This is used when new chats are saved in database 
		 <c:url var="post_url" value="/chat/showonetoonechatmessages/${chatroomid}" />
		<form:form method="POST" action="${post_url}"
			id="onetoone_messages_form" class="form" modelAttribute="chat"> --%>
			<div class="ui-corner-all custom-corners">
	 			<c:if test="${!empty secondperson}">
					<div class="ui-bar ui-bar-a">
						<h3> <c:out value="${secondperson.firstName} ${secondperson.lastName}"></c:out> </h3>
					</div>
				</c:if>
				
				<div class="ui-bar ui-bar-a">
					<h3>Messages</h3>
				</div>
				 <table id="twitterMessages">
 			  		<c:if test="${!empty allChatsOfChatroomDTOList}">
 			  			  
 			  			<c:forEach var="dto" items="${allChatsOfChatroomDTOList}">
			    			<c:choose>
			    				
      								<c:when test="${!empty dto.chat}">
         						
										<tr>
											<td> ${dto.chat.sender.userId.id} : ${dto.chat.item.id} :</td>
											<td> ${dto.chat.message}   </td>
											<td align="right">${dto.chat.createdAt} </td>
										 </tr>
								
        						</c:when>
        							
      								<c:otherwise>
       				    			  <c:forEach var="itemchat" items="${dto.listOfChats}">
										<tr  style="outline: thin solid">
											<td> ${itemchat.sender.userId.id} : ${itemchat.item.id} : </td>
											<td> ${itemchat.message}   </td>
											<td align="right">${itemchat.createdAt} </td>
								 		</tr>
									  </c:forEach>
     								</c:otherwise>
 
    						</c:choose>
						</c:forEach>
					  
					</c:if>
				</table>
					<div class="ui-bar ui-bar-a">
						<h3>Type Your Message Bellow</h3>
					</div>
				 <input id="message-field" type="text" size="40" value="Send a tweet to connected clients" />
				</div>
				
			
			<div class="ui-corner-all custom-corners">
				<div class="ui-body ui-body-a">
					<input type="button" id="message-button" value="Send" />
				</div>
			</div>
			
   <script type="text/javascript">

      $(function() {

        if (!window.console) {
          console = {log: function() {}};
        }

        function refresh() {
          console.log("Refreshing data tables...");
        }

        var socket = $.atmosphere;
        var request = new $.atmosphere.AtmosphereRequest();
        
        request.url = document.location.toString() + '/../../../../websockets';
        request.contentType = "application/json";
        request.transport = 'websocket';
        request.fallbackTransport = 'long-polling';
        
        
        request.onOpen = function(response){
          
          console.log('onOpen: connection opened using transport:' + response.transport);
          subSocket.push(JSON.stringify({"message":"0Open0","userId": ${userId},"chatroomId":${chatroomId},"itemId":0}));
         
        }
        
        request.onReconnect = function(request, response){
          console.log('onReconnect:');
          socket.info("Reconnecting");
        }
        
        request.onMessage = function(response){
          var message = response.responseBody;
          console.log('onMessage: Message is:'+message);
          try {
            $("#twitterMessages").append(message);
          } catch (e) {
            console.log('Error: ', message.data);
            return;
          }
        }
        
        request.onError = function(response){
          content.html($('<p>', { text: 'Sorry, but '
                  + 'there some problem with your '
                  + 'socket or the server is down' }));
        }
        
        var subSocket = socket.subscribe(request);               //subSocket is used to push messages to the server.
        
        $('#message-button').click(function() {
          console.log('Clicked MessageButton'+$('#message-field').val());
          subSocket.push(JSON.stringify({"message":$('#message-field').val(),"userId": ${userId},"chatroomId":${chatroomId},"itemId":0}));
        });
      });
  </script>
		
	</tiles:putAttribute>
</tiles:insertDefinition>
								
									
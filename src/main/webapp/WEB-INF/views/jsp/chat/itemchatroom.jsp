<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="chat">
	
	<tiles:putAttribute name="title">
		BizBuzz-Item Chatroom
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
			<div class="ui-corner-all custom-corners">
				<c:if test="${!empty itemid}">
					<div class="ui-bar ui-bar-a">
						<h3> <c:out value="Conversation about Item : ${itemid}"></c:out> </h3>
					</div>
				</c:if>
				
	 			<c:if test="${!empty secondperson}">
					<div class="ui-bar ui-bar-a">
						<h3> <c:out value="${secondperson.firstName} ${secondperson.lastName}"></c:out> </h3>
					</div>
				</c:if>
			  	
				<div class="ui-bar ui-bar-a">
					<h3>Messages</h3>
				</div>
			  	<div class="ui-body ui-body-a">
			  	<c:choose>
      				<c:when test="${!empty chatsOfItem}">
         			    <table id="twitterMessages">
							<c:forEach var="chat" items="${chatsOfItem}">
								<tr>
										<td> ${chat.sender.userId.id} : </td>
										<td> ${chat.message}   </td>
										<td align="right">${chat.createdAt} </td>
								 </tr>
								</c:forEach>
						</table>
        			</c:when>
      				<c:otherwise>
       				    <table id="twitterMessages">
       				    </table>
     				</c:otherwise>
    			</c:choose>
		 
					<div class="ui-bar ui-bar-a">
						<h3>Type Your Message Bellow</h3>
					</div>
				 <input id="message-field" type="text" size="40" value="Send a tweet to connected clients" />
				</div>
				
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
        
        request.url = document.location.toString() + '/../../../../../../websockets';
        request.contentType = "application/json";
        request.transport = 'websocket';
        request.fallbackTransport = 'long-polling';
        
        
        request.onOpen = function(response){
          
          console.log('onOpen: connection opened using transport:' + response.transport);
          subSocket.push(JSON.stringify({"message":"0Open0","userId": ${userId},"chatroomId":${chatroomId},"itemId":${itemId}}));
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
          subSocket.push(JSON.stringify({"message":$('#message-field').val(),"userId": ${userId},"chatroomId":${chatroomId},"itemId":${itemId}}));
        });
      });
  </script>
		
	</tiles:putAttribute>
</tiles:insertDefinition>
								
									
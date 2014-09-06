<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="chat">
	
	<tiles:putAttribute name="title">
		BizBuzz-Chatroom
	</tiles:putAttribute>
	
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			$(document).ready(function() {
				$("#onetoone_messages_form").click(function(event) {
				});
			});
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="post_url" value="/chat/showonetoonechatmessages/${chatroomid}" />
		<form:form method="POST" action="${post_url}"
			id="onetoone_messages_form" class="form" modelAttribute="chat">
			<div class="ui-corner-all custom-corners">
				<c:if test="${!empty person}">
					<div class="ui-bar ui-bar-a">
						<h3> <c:out value="${person.firstName} ${person.lastName}"></c:out> </h3>
					</div>
					
				</c:if>
			  	
				<div class="ui-bar ui-bar-a">
					<h3>Messages</h3>
				</div>
			  	<div class="ui-body ui-body-a">
					<c:if test="${!empty chats}">
						<table cellspacing="5">
							<c:forEach var="chat" items="${chats}">
								<tr>
										<td> ${chat.sender.id} : </td>
										<td> ${chat.message}   </td>
										<td align="right">${chat.createdAt} </td>
								 </tr>
								</c:forEach>
						</table>
					</c:if>
				
					<div class="ui-bar ui-bar-a">
						<h3>Type Your Message Bellow</h3>
					</div>

					<form:input path="message" type="text" id="messages"
						required="true" placeholder="Message" />
						
				</div>
				
			</div>
			<div class="ui-corner-all custom-corners">
				<div class="ui-body ui-body-a">
					<input type="submit" id="message_send" value="Send" />
				</div>
			</div>
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
								
									
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="chat">
	
	<tiles:putAttribute name="title">
		BizBuzz- List of ChatRooms
	</tiles:putAttribute>
	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
		
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
	
			<div class="ui-corner-all custom-corners">		
				<div class="ui-bar ui-bar-a">
					<h3>List of ChatRooms</h3>
				</div>
				<div class="ui-body ui-body-a">
					<table style="table-layout:fixed;">
						<c:if test="${!empty sortedchats}">
						
							<c:forEach  var="chat" items="${sortedchats}">
					  		<c:url var="post_url" value="/chat/showchatroom/chatroomid/${chat.chatRoom.id}" />
 					  			<c:forEach var="member" items="${chat.chatRoom.members}">    
					  				<c:if test="${member.id ne userid}">
										 <c:set var="showperson" value="${member}"  />
									</c:if>
					  			</c:forEach> 
	 							
								<tr>
										<td>
										<a href="${post_url}" style="text-decoration: none;">
												<input type="button" value="${showperson.firstName} ${showperson.lastName}       ${chat.message}        -${chat.createdAt}  "/>
										</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${!empty sortedChatrooms}">
						
							<c:forEach  var="chatroom" items="${sortedChatrooms}">
					  		<c:url var="post_url" value="/chat/showchatroom/chatroomid/${chatroom.id}" />
 					  			<c:forEach var="member" items="${chatroom.members}">    
					  				<c:if test="${member.id ne userid}">
										 <c:set var="showperson" value="${member}"  />
									</c:if>
					  			</c:forEach> 
	 							
								<tr>
										<td>
										<a href="${post_url}" style="text-decoration: none;">
												<input type="button" value="${showperson.firstName} ${showperson.lastName}       -Connected At ${chatroom.createdAt}  "/>
										</a>
									</td>
								</tr>
							</c:forEach>
							
						</c:if>
						
					</table>
					
				</div>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

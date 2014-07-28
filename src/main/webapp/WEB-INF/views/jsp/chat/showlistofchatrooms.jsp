<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="chat">
	
	<tiles:putAttribute name="title">
		BizBuzz- List of ChatRooms
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
	
			<div class="ui-corner-all custom-corners">		
				<div class="ui-bar ui-bar-a">
					<h3>List of ChatRooms</h3>
				</div>
				<div class="ui-body ui-body-a">
					<c:if test="${!empty chatrooms}">
						<table style="table-layout:fixed;">
							<c:forEach var="chatroom" items="${chatrooms}">
					  		<c:url var="post_url" value="/chat/showchatroom/chatroomid/${chatroom.id}" /> 
								<tr>
						
									<td>
										<a href="${post_url}" style="text-decoration: none;">
												<input type="button" value="ChatRoomId : ${chatroom.id}  -  Go To This ChatRoom"/>
										</a>
									</td>
								</tr>
							</c:forEach>
							
						</table>
					</c:if>
				</div>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

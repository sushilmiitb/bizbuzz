<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<div class="chat-content">
<!--------------------------------------------------------------------------------------------------------------- 
Please read the top comment in chat controller first
This module in footer.jsp loads whenever a new page is loaded. It contains the bare minimum structure of chat 
module. Subsequent requests and responses are handled through ajax modules which is written in chat.js. On new
page load, It determines whether current state of chat is item chat or normal chat and stores corresponding value
in the session variable chatmode. This value is then used in chat controller to return correct chat module when
ajax request is made from chat.js. It then determines whether current state of chat is visible or hidden and 
accordingly makes the chat module hidden or visible.
---------------------------------------------------------------------------------------------------------------->

	<div class="panel panel-default chat-panel"></div>
	<script type="text/javascript">
		$(document).ready(function(){
			initializeChatPanel();
			$(".chat-content").hide();
			var itemId = undefined;
			<c:if test="${not empty item}">
				itemId = ${item.id};
			</c:if>
			var sellerId = undefined;
			<c:if test="${not empty seller}">
				sellerId = ${seller.id};
			</c:if>
			
			<%@ page import = "com.bizbuzz.model.Item" %>
			<%
			//If item variable is present that means this is itemchat and accordingly update session variable
				Item item = (Item) pageContext.findAttribute("item");
				if(item == null){
					session.setAttribute("chatmode", "normal");
				}else{
					session.setAttribute("chatmode", "item");
				}
			%>
			
			<c:choose>
				<c:when test="${not empty ischatpanelvisible}">
					loadCurrentChatRoomState(true, itemId, sellerId);
				</c:when>
				<c:otherwise>
					loadCurrentChatRoomState(false, itemId, sellerId);
				</c:otherwise>
			</c:choose>
			
		});
	</script>
</div>
<div class="panel-footer footer">
	<div class="row" id="footer">
		<div class="col-md-4 col-sm-4 col-xs-4" align="left">
		</div>
		<div class="col-md-4 col-sm-4 col-xs-4" align="center">
			<button class="btn btn-primary btn-lg chat-toggle-btn">Chat</button>
		</div>
		<div class="col-md-4 col-sm-4 col-xs-4" align="right">
		</div>
	</div>
</div>

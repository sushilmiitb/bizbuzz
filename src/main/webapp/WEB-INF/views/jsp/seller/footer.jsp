<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<div class="chat-content">
	<div class="panel panel-default chat-panel"></div>
	<script type="text/javascript">
		$(document).ready(function(){
			initializeChatPanel();
			$(".chat-content").hide();
			<c:choose>
				<c:when test="${not empty ischatpanelvisible}">
					loadCurrentChatRoomState(true);
				</c:when>
				<c:otherwise>
					loadCurrentChatRoomState(false);
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
			<span class="glyphicon glyphicon-comment"></span>
		</div>
		<div class="col-md-4 col-sm-4 col-xs-4" align="right">
		</div>
	</div>
</div>

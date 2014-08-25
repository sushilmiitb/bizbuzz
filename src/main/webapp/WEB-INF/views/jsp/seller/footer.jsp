<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<div class="chat-content">
	<div class="panel panel-primary chat-panel"></div>
	<script type="text/javascript">
		$(document).ready(function(){
			initializeChatPanel();
			$(".chat-content").hide();
			loadCurrentChatRoomState();
			<c:if test="${not empty ischatpanelvisible}">
				$(".chat-content").show();
			</c:if>
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

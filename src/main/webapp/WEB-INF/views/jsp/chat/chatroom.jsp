<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<c:url var="static_base_url" value="/static" />
<c:url var="base_image_url" value="/${rootDir}" />
<c:url var="emptyImageUrl" value="/${rootDir}/${sizeDir}/noimage.${imageExtn}" />

<div class="panel-heading chat-header">
	<c:choose>
		<c:when test="${!empty secondperson}">
			<span>
				<c:out value="${secondperson.firstName} ${secondperson.middleName} ${secondperson.lastName}"></c:out>
			</span>
		</c:when>
		<c:otherwise>
			<span>
				Chat Room
			</span>
		</c:otherwise>
	</c:choose>
	<span class="chat-back glyphicon glyphicon-arrow-left pull-right"></span>
</div>

<div class="panel-body chat-body">
	<c:if test="${!empty allChatsOfChatroomDTOList}">
		<c:forEach var="dto" items="${allChatsOfChatroomDTOList}">
			<c:choose>
				<c:when test="${!empty dto.chat}">
					<c:choose>
						<c:when test="${person.id == dto.chat.sender.id}">
							<div class="ind-chat-panel ind-chat-panel-right">
								<div class="arrow"></div>
								<div class="ind-chat-body">
									<c:set var="temp" value="one
									two"/>
									<c:set var="newline" value="${fn:substring(temp,3,4)}"/>
									<c:set var="lines" value="${fn:split(dto.chat.message, newline)}" />
									<p>
										<c:forEach var="item" items="${lines}">
											<span>${item}</span>
										</c:forEach>
									</p>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="ind-chat-panel ind-chat-panel-left">
								<div class="arrow"></div>
								<div class="ind-chat-body">
									<c:set var="temp" value="one
									two"/>
									<c:set var="newline" value="${fn:substring(temp,3,4)}"/>
									<c:set var="lines" value="${fn:split(dto.chat.message, newline)}" />
									<p>
										<c:forEach var="item" items="${lines}">
											<span>${item}</span>
										</c:forEach>
									</p>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<div class="item-chat" id="${dto.listOfChats[0].item.id}">

						<c:forEach var="imageModel" items="${dto.listOfChats[0].item.imageModels}">
							<c:if test="${imageModel.imageModelMetadata.tag=='0'}">
								<c:set var="displayImage" value="${imageModel}" scope="page" />
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${not empty displayImage}">
								<img class="image-responsive chat-item-image"
									src="${base_image_url}/${sizeDir}/${displayImage.id}.${imageExtn}"
									alt="Image Not Uploaded" />
							</c:when>
							<c:otherwise>
								<img src="${emptyImageUrl}" class="image-responsive chat-item-image"
									alt="Image Not Uploaded" />
							</c:otherwise>
						</c:choose>

						<c:forEach var="itemchat" items="${dto.listOfChats}">
							<c:choose>
								<c:when test="${person.id == itemchat.sender.id}">
									<div class="ind-chat-panel ind-chat-panel-right">
										<div class="arrow"></div>
										<div class="ind-chat-body">
											<c:set var="temp" value="one
											two"/>
											<c:set var="newline" value="${fn:substring(temp,3,4)}"/>
											<c:set var="lines" value="${fn:split(itemchat.message, newline)}" />
											<p>
												<c:forEach var="item" items="${lines}">
													<span>${item}</span>
												</c:forEach>
											</p>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="ind-chat-panel ind-chat-panel-left">
										<div class="arrow"></div>
										<div class="ind-chat-body">
											<c:set var="temp" value="one
											two"/>
											<c:set var="newline" value="${fn:substring(temp,3,4)}"/>
											<c:set var="lines" value="${fn:split(itemchat.message, newline)}" />
											<p>
												<c:forEach var="item" items="${lines}">
													<span>${item}</span>
												</c:forEach>
											</p>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</c:if>
</div>
<div class="chat-input-area">
	<div class="row">
		<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
			<textarea class="form-control" id="message-field"></textarea>
		</div>
		<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
			<button class="btn btn-info btn-block" id="message-button">Send</button>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		changeStateOfPage('<%= session.getAttribute("chatpage") %>',${chatroomId},0);	
		
		initializeNormalChatRoom(${person.id}, ${chatroomId}, 0);
		$(".item-chat").click(function(){
			loadItemChatRoom(${chatroomId}, $(this).attr("id"), "normalchatroom");
		});
		changeTotalNoOfUnreadChats(${totalNoOfUnreadChats});
	});
</script>
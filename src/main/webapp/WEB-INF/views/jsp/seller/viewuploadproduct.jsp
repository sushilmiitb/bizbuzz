<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>



<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		InstaTrade-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
		var debug=false;
		var baseStaticUrl = "/static";
		var max_height = 800;
		var max_width = 600;
		var compressionRatio = 0.7;
		var imageIndex;
		var CameraAndroidObj;
		var imageUploadFlag=false;
		$(document).ready(function(){
			$("#selectall").click(function(){
				$(".second").prop("checked",$("#selectall").prop("checked"));
			});
			
			$('.productuploadform').submit(function onsubmit(event){	
				var submitFlag=false;
				$('.imageuploadinput').val('');
				$('.properties').find('input[type=text]').each(function() {
					var lengthOfInputField =  $(this).val();
					if(lengthOfInputField.length>0){
						submitFlag=true;
					}		
				});
				
				if(!submitFlag || !imageUploadFlag){
					event.preventDefault();
					$(".loader").remove();
					displayQuickNotification("Product information and image should be filled.", 3000);     
				}
				else{
					return true;
				}
			});
			
			var ua = navigator.userAgent.toLowerCase();   
			var isAndroid = ua.indexOf("android") > -1; //&& ua.indexOf("mobile");
			if(isAndroid) {
//----------------------------------------android specific code -------------------------------------------//
				if(debug)
				alert("This is android platform.");
				
				CameraAndroidObj = function(){
				this.initialize = function(index){
					this.bindEvents();
					imageIndex = index;
				};
				
				this.bindEvents = function() {
					document.addEventListener('deviceready', this.onDeviceReady, false);
				};
				
				this.onDeviceReady = function() {
					pictureSource=navigator.camera.PictureSourceType;
        			destinationType=navigator.camera.DestinationType;
				};
				
				// Called when a photo is successfully retrieved
				this.onPhotoDataSuccess = function(imageData){
					var imageData = "data:image/jpeg;base64," + imageData;
					
					var input = $("#images"+"Hidden_"+imageIndex);
					var newinput;
					if(input.length > 0){
						newinput = input[0];
						newinput.value = imageData;
					}
					else{    
						newinput = document.createElement("input");
						newinput.type = 'hidden';
						newinput.name = "imagesHidden["+imageIndex+"]";
						newinput.id = "images"+"Hidden_"+imageIndex;
						newinput.value = imageData; // put result from canvas into new hidden input
						$('.productuploadform').append(newinput);
						imageUploadFlag=true;
					}
					$("#thumbnail_images"+"_"+imageIndex).attr("src", imageData);
				};
				
			    this.capturePhoto = function(source, width, height) {
					// Take picture using device camera and retrieve image as base64-encoded string
					navigator.camera.getPicture(this.onPhotoDataSuccess, this.onFail, { quality: compressionRatio*100, allowEdit: true,
						destinationType: destinationType.DATA_URL, sourceType: source, correctOrientation: true, targetWidth: width,
						targetHeight: height});
				};

				// Called if something bad happens.
				//
				this.onFail = function(message) {
					alert('Failed because: ' + message);
				}		
				};
			
				function onCordovaLoad(){
					if(debug)
						alert("cordova Loaded");
				//-----------------------------------making UI changes for android specific-----------------------------------//
					$('.imageuploadinput').click(function(event){
						event.preventDefault();
						var id = $(this).attr("id");
						var index = id.split('[')[1].split(']')[0];
						$('#uploadModal').modal('toggle');
						
						$('.btn-upload-camera').click(function(event){
							$( '.btn-upload-camera, .btn-upload-library, .btn-upload-album').unbind("click");
							$('#uploadModal').modal('toggle');
							var cameraAndroidObj = new CameraAndroidObj();
							cameraAndroidObj.initialize(index);
							cameraAndroidObj.capturePhoto(pictureSource.CAMERA, max_width*4/3, max_height*4/3);
						});
						
						$('.btn-upload-library').click(function(event){
							$( '.btn-upload-camera, .btn-upload-library, .btn-upload-album').unbind("click");
							$('#uploadModal').modal('toggle');
							var cameraAndroidObj = new CameraAndroidObj();
							cameraAndroidObj.initialize(index);
							cameraAndroidObj.capturePhoto(pictureSource.PHOTOLIBRARY, max_width, max_height);
						});
						
						//$('.btn-upload-album').click(function(event){
						//	$( '.btn-upload-camera, .btn-upload-library, .btn-upload-album').unbind("click");
						//	$('#uploadModal').modal('toggle');
						//	var cameraAndroidObj = new CameraAndroidObj();
						//	cameraAndroidObj.initialize(index);
						//	cameraAndroidObj.capturePhoto(pictureSource.SAVEDPHOTOALBUM);
						//});
					});
				}
				loadjsfile(baseStaticUrl+"/js/cordova/cordova-combined-<spring:message code="git_hash" />.min.js", onCordovaLoad);
			}
			else{
//--------------------------------------------web specific code -------------------------------------------//		
				$('.imageuploadinput').change(function onchange(){
					var imageInput = this;
					var form = $('.productuploadform');
			
					function processfile(file) {
			
						if( !( /image/i ).test( file.type ) )
						{
							alert( "File "+ file.name +" is not an image." );
							return false;
						}
			
						// read the files
						var reader = new FileReader();
						//reader.readAsArrayBuffer(file);
						reader.readAsDataURL(file);
			
						reader.onload = function (event) {
							// blob stuff
							/*var blob = new Blob([event.target.result]); // create blob...	
							window.URL = window.URL || window.webkitURL;
							var blobURL = window.URL.createObjectURL(blob); // and get it's URL
							*/
			
							// helper Image object
							//var image = new Image();
							var image = document.createElement("img");
							//image.src = blobURL;
							image.src = event.target.result;
							//preview.appendChild(image); // preview commented out, I am using the canvas instead
							image.onload = function() {
								// have to wait till it's loaded
								var resized = resizeMe(image); // send it to canvas
								var newinput = document.createElement("input");
								newinput.type = 'hidden';
								var name = $(imageInput).attr("name");
								var nameArray = name.split('[');
								newinput.name = nameArray[0]+"Hidden["+nameArray[1];
								var index = nameArray[1].split(']');
								newinput.id = nameArray[0]+"Hidden_"+index[0];
								newinput.value = resized; // put result from canvas into new hidden input
								$(form).append(newinput);
								//putting image into thumbnail
								$("#thumbnail_"+nameArray[0]+"_"+index[0]).attr("src", resized);
								imageUploadFlag=true;
							}
						};
					}
			
					function readfile() {
						var name = $(imageInput).attr("name");
						var nameArray = name.split('[');
						var index = nameArray[1].split(']');
						var prevInput = $("#"+nameArray[0]+"Hidden_"+index[0]);
						if(prevInput !== undefined){
							$(prevInput).remove();
						}
						
						// remove the existing canvases and hidden inputs if user re-selects new pics
						//var existinginputs = document.getElementsByName('images[]');
						//var existinginput = $($(imageInput).attr("name") + "Hidden");
						//var existingcanvases = document.getElementsByTagName('canvas');
						//while (existinginput.length > 0) { // it's a live list so removing the first element each time
							// DOMNode.prototype.remove = function() {this.parentNode.removeChild(this);}
						//	form.removeChild(existinginput);
							//preview.removeChild(existingcanvases[0]);
						//}
			
						processfile(imageInput.files[0]);
			
						//imageInput.value = ""; //remove the original files from fileinput
						// TODO remove the previous hidden inputs if user selects other files
					}
			
			
					function resizeMe(img) {
			
						var canvas = document.createElement('canvas');
			
						var width = img.width;
						var height = img.height;
			
						// calculate the width and height, constraining the proportions
						if (width > height) {
							if (width > max_width) {
								//height *= max_width / width;
								height = Math.round(height *= max_width / width);
								width = max_width;
							}
						} else {
							if (height > max_height) {
								//width *= max_height / height;
								width = Math.round(width *= max_height / height);
								height = max_height;
							}
						}
			
						// resize the canvas and draw the image data into it
						canvas.width = width;
						canvas.height = height;
						var ctx = canvas.getContext("2d");
						ctx.drawImage(img, 0, 0, width, height);
			
						//preview.appendChild(canvas); // do the actual resized preview
			
						return canvas.toDataURL("image/jpeg", compressionRatio); // get the data from canvas as 70% JPG (can be also PNG, etc.)
			
					}
					readfile();
			
				});
			}
//-------------------------------------------- load js file dynamically -------------------------------------------//				
			function loadjsfile(filename, onload){	
				if(debug)
					alert("mobileadaptation.js/loadjsfile:filename->"+filename);
				var script=document.createElement('script');
				script.setAttribute("type","text/javascript");
			
				script.setAttribute("src", filename);
				if (typeof script!="undefined")
					document.getElementsByTagName("head")[0].appendChild(script);
			
				if (script.readyState){  //IE
					script.onreadystatechange = function(){
						if (script.readyState == "loaded" || script.readyState == "complete"){
							script.onreadystatechange = null;
							//do your stuff
						}
					};
				} else {  //Others
					script.onload = onload;
				}
			}
			
		});
		</script>

	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="form_upload_url"
			value="/seller/uploadproduct/category/${categoryId}" />
		<c:if test="${not empty itemId }">
			<c:url var="form_upload_url"
				value="/seller/uploadproduct/category/${categoryId}/item/${itemId}" />
		</c:if>
		<c:url var="newProductUpload"
			value="/seller/uploadproduct/category/${categoryId}" />
		<c:url var="base_image_url" value="/${rootDir}" />
		<c:url var="emptyImageUrl"
			value="/${rootDir}/${sizeDir}/noimage.${imageExtn}" />
		<c:set var="value_count" value="0" scope="page" />
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
				<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 no-padding">
					<div class="panel panel-primary">
						<div class="panel-heading center-align-text">${parentCategoryName}</div>
						<div class="panel-body">
<!---------------------------------------------------- Edit Button ------------------------------------------------------->						
<%-- 							<c:if test="${not empty itemId }"> --%>
<!-- 								<div class="row"> -->
<!-- 									<div class="hidden-xs hidden-sm col-md-2 col-lg-3"></div> -->
<!-- 									<div class="col-xs-12 col-xs-12 col-md-8 col-lg-6"> -->
<%-- 										<a href="${newProductUpload}" --%>
<!-- 											class="btn btn-success btn-block">Upload another -->
<%-- 											${parentCategoryName} product</a> --%>
<!-- 									</div> -->
<!-- 									<div class="hidden-xs hidden-sm col-md-2 col-lg-3"></div> -->
<!-- 								</div> -->
<!-- 								<br /> -->
<%-- 							</c:if> --%>
<!---------------------------------------------------- Share Button ------------------------------------------------------->
<!-- 							<div class="row"> -->
<!-- 								<div class="hidden-xs hidden-sm col-md-2 col-lg-3"></div> -->
<!-- 								<div class="col-xs-12 col-xs-12 col-md-8 col-lg-6"> -->
<!-- 									<button class="btn btn-success btn-block" data-toggle="modal" -->
<!-- 										data-target="#shareModal">Share the product</button> -->
<!-- 								</div> -->
<!-- 								<div class="hidden-xs hidden-sm col-md-2 col-lg-3"></div> -->
<!-- 							</div> -->
<!-- 							<br /> -->
							<form role="form" id="uploadForm" action="${form_upload_url}"
								class="productuploadform" method="POST"
								enctype="multipart/form-data">
<!---------------------------------------------------- Image Module ------------------------------------------------------->
								<div class="row" id="imagecontent">
									<c:forEach var="item" items="${propertyMetadata.imageModels}"
										varStatus="i">
										<div class="col-xs-12 col-md-6 col-sm-6 col-lg-4">
											<div class="panel panel-default">
												<div class="panel-heading">
													<label>${item.name}</label>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
															<c:choose>
																<c:when test="${not empty valueImageModelMap[item.id]}">
																	<input type="hidden" name="imagesValueId[${i.index}]"
																		value="${valueImageModelMap[item.id].id}" />
																	<a class="thumbnail"
																		href="${base_image_url}/${baseSizeDir}/${valueImageModelMap[item.id].id}.${imageExtn}">
																		<img class="image-responsive upload-preview"
																		id="thumbnail_images_${i.index}" alt="..."
																		src="${base_image_url}/${sizeDir}/${valueImageModelMap[item.id].id}.${imageExtn}" />
																	</a>
																</c:when>
																<c:otherwise>
																	<a href="#" class="thumbnail"> <img
																		class="image-responsive no-image upload-preview"
																		id="thumbnail_images_${i.index}" alt=""
																		src="${emptyImageUrl}">
																	</a>
																</c:otherwise>
															</c:choose>
														</div>
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
															<div>
																<button class="btn btn-default btn-block pull-right upload-btn">
																	Upload</button>
																<input type="hidden" name="imagesMetaId[${i.index}]"
																	value="${item.id}" /> <input name="images[${i.index}]"
																	class="imageuploadinput btn btn-default btn-block pull-right"
																	type="file" id="image_input[${i.index}]"
																	style="opacity: 0; margin-top: -34px; height: 35px;" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
<!---------------------------------------------------- Property Module ------------------------------------------------------->								
								<div class="row" id="propertyContent">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<c:forEach var="group"
											items="${propertyMetadata.propertyGroups}" varStatus="i">
											<div class="panel panel-default">
												<div class="panel-heading">${group.name}</div>
												<div class="panel-body">
													<div class="row properties" >
														<c:forEach var="subgroup"
															items="${group.propertySubGroups}" varStatus="j">
															<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4 no-padding">
																<h3>${subgroup.name}</h3>
																<table>
																	<c:forEach var="field"
																		items="${subgroup.propertyFields}" varStatus="k">
																		<c:choose>
																			<c:when test="${not empty newItem}">
																				<tr>
																					<td class="col-xs-6 col-sm-6 col-md-6 col-lg-6 no-padding"><label>${field.value}</label></td>
																					<input type="hidden"
																						name="fieldIds[${value_count}]"
																						value="${field.id}" />
																					<td class="col-xs-6 col-sm-6 col-md-6 col-lg-6 no-padding"><input
																						style="width:100%;" name="values[${value_count}]" value="" type="text" /></td>
																					<c:set var="value_count" value="${value_count + 1}"
																						scope="page" />
																				</tr>
																			</c:when>
																			<c:otherwise>
																				<tr>
																					<td class="col-xs-6 col-sm-6 col-md-6 col-lg-6 no-padding"><label>${field.value}</label></td>
																					<input type="hidden"
																						name="valueIds[${value_count}]"
																						value="${propertyValueMap[field.id].id}" />
																					<td class="col-xs-6 col-sm-6 col-md-6 col-lg-6 no-padding"><input
																						style="width:100%;" name="values[${value_count}]"
																						value="${propertyValueMap[field.id].value}"
																						type="text" /></td>
																					<c:set var="value_count" value="${value_count + 1}"
																						scope="page" />
																				</tr>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</table>
															</div>
														</c:forEach>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
								<br />
<!---------------------------------------------------- Modal for edit ------------------------------------------------------->
								<div class="modal fade" id="shareModal" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title" id="myModalLabel">Share Product</h4>
											</div>
											<div class="modal-body">
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<h4 class="modal-title" id="myModalLabel">Share with
															groups</h4>
														<div class="checkbox">
															<label><input type="checkbox" name="check_all"
																id="selectall" class="second" />Check/Uncheck All</label>
														</div>
														<c:forEach var="item" items="${privateGroups}">
															<c:choose>
																<c:when
																	test="${not empty sharedPrivateGroupMap[item.id]}">
																	<div class="checkbox">
																		<label><input type="checkbox" id="selectall"
																			class="second" name="share" value="${item.id}" checked="true">${item.privateGroupName}</label>
																	</div>
																</c:when>
																<c:otherwise>
																	<div class="checkbox">
																		<label><input type="checkbox" id="selectall"
																			class="second" name="share" value="${item.id}" checked="true">${item.privateGroupName}</label>
																	</div>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Ok</button>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal -->
								</div>
																
								<div class="row" id="submit">
									<div class="hidden-xs hidden-sm col-md-2 col-lg-3"></div>
									<div class="col-xs-12 col-xs-12 col-md-8 col-lg-6">
										<button type="submit" class="btn btn-primary btn-block">Save
											Product Details</button>
									</div>
									<div class="hidden-xs hidden-sm col-md-2 col-lg-3"></div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
			</div>
		</div>
		<!---------------------------------------------------- Modal for upload ------------------------------------------------------->
		<div class="modal fade" id="uploadModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Upload Using</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<button class="btn btn-default btn-block btn-upload-camera">Camera</button>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<button class="btn btn-default btn-block btn-upload-library">Photo Library</button>
							</div>
<!-- 							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
<!-- 								<button class="btn btn-default btn-block btn-upload-album">Saved Album</button> -->
<!-- 							</div> -->
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>
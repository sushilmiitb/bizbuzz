<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>



<tiles:insertDefinition name="seller">
	<tiles:putAttribute name="title">
		BizBuzz-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
		$(document).ready(function(){
			function androidLogger(arg){
				$("#maincontent").append("<div>"+arg+"</div>");
			}
			$('.productuploadform').submit(function onsubmit(event){
				$('.imageuploadinput').val('');
			});
			$('.imageuploadinput').change(function onchange(){
				var imageInput = this;
				var max_height = $(this).attr('data-maxheight');
				var max_width = $(this).attr('data-maxwidth');
				if(max_height === undefined){
					max_height = 800;
				}
				if(max_width === undefined){
					max_width = 600;
				}
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
							newinput.id = $(imageInput).attr("id")+"hidden";
							newinput.name = $(imageInput).attr("name")+"Hidden";
							newinput.value = resized; // put result from canvas into new hidden input
							$(form).append(newinput);
							//putting image into thumbnail
							$("#thumbnail_"+$(imageInput).attr("name")).attr("src", resized);
						}
					};
				}
		
				function readfile() {
					var prevInput = $("#"+$(imageInput).attr("id")+"hidden");
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
		
					return canvas.toDataURL("image/jpeg", 0.7); // get the data from canvas as 70% JPG (can be also PNG, etc.)
		
				}
				readfile();
		
			});
		});
		
		/*$(window).load(function(){
			$(window).resize(function(){
				var width = $(".image-responsive").filter(".upload-preview").width();
				var height = Math.floor(width*4/3);
				$(".no-image-preview").css("width", width);
				$(".no-image-preview").css("height", height);
				$(".no-image-preview").css("margin-top", -height);
				$(".no-image-preview").css("margin-left", 0);
				$(".no-image-preview").css("line-height", height+"px");
			})
		
			function initialize(){
				var width = $(".image-responsive").filter(".upload-preview").width();
				var height = Math.floor(width*4/3);
				$(".image-responsive").filter(".upload-preview").height(height);
				$(".no-image-preview").css("width", width);
				$(".no-image-preview").css("height", height);
				$(".no-image-preview").css("margin-top", -height);
				$(".no-image-preview").css("margin-left", 0);
				$(".no-image-preview").css("line-height", height+"px");
			}
			
			initialize();
		});*/
		</script>

	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="form_upload_url"
			value="/seller/uploadproduct/category/${categoryId}" />
		<c:if test="${not empty itemId }">
			<c:url var="form_upload_url"
				value="/seller/uploadproduct/category/${categoryId}/item/${itemId }" />
		</c:if>
		<c:url var="base_image_url" value="/${rootDir}" />
		<c:url var="emptyImageUrl"
			value="/${rootDir}/${sizeDir}/noimage.${imageExtn}" />
		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading center-align-text">${parentCategoryName}</div>
						<div class="panel-body">
							<form:form role="form" action="${form_upload_url}"
								class="productuploadform" modelAttribute="uploadForm"
								method="POST" enctype="multipart/form-data">
								<div class="row" id="imagecontent">
									<c:if test="${not empty propertyMetadata.primaryImage }">
										<div class="col-xs-12 col-md-6 col-sm-6 col-lg-4">
											<div class="panel panel-default">
												<div class="panel-heading">
													<form:label path="primaryImage">${propertyMetadata.primaryImage }</form:label>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
															<c:choose>
																<c:when
																	test="${not empty uploadForm.propertyValue.primaryImageModel}">
																	<a class="thumbnail"
																		href="${base_image_url}/${baseSizeDir}/${uploadForm.propertyValue.primaryImageModel.id}.${imageExtn}">
																		<img class="image-responsive upload-preview"
																		id="thumbnail_primaryImage" alt="..."
																		src="${base_image_url}/${sizeDir}/${uploadForm.propertyValue.primaryImageModel.id}.${imageExtn}" />
																	</a>
																</c:when>
																<c:otherwise>
																	<a href="#" class="thumbnail"> <img
																		class="image-responsive no-image upload-preview"
																		id="thumbnail_primaryImage" alt=""
																		src="${emptyImageUrl} ">
																	</a>
																</c:otherwise>
															</c:choose>
														</div>
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
															<div>
																<button class="btn btn-default btn-block pull-right">
																	Upload File</button>
																<form:input path="primaryImage"
																	class="imageuploadinput btn btn-default btn-block pull-right"
																	type="file" id="upload_input"
																	style="opacity: 0; margin-top: -34px; height: 35px;" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${not empty propertyMetadata.image1 }">
										<div class="col-xs-12 col-md-6 col-sm-6 col-lg-4">
											<div class="panel panel-default">
												<div class="panel-heading">
													<form:label path="image1">${propertyMetadata.image1 }</form:label>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
															<c:choose>
																<c:when
																	test="${not empty uploadForm.propertyValue.image1Model}">
																	<a class="thumbnail"
																		href="${base_image_url}/${baseSizeDir}/${uploadForm.propertyValue.image1Model.id}.${imageExtn}">
																		<img class="image-responsive upload-preview"
																		id="thumbnail_image1" alt="..."
																		src="${base_image_url}/${sizeDir}/${uploadForm.propertyValue.image1Model.id}.${imageExtn}" />
																	</a>
																</c:when>
																<c:otherwise>
																	<a href="#" class="thumbnail"> <img
																		class="image-responsive no-image upload-preview"
																		id="thumbnail_image1" alt="" src="${emptyImageUrl} ">
																	</a>
																</c:otherwise>
															</c:choose>
														</div>
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
															<div>
																<button class="btn btn-default btn-block pull-right">
																	Upload File</button>
																<form:input path="image1"
																	class="imageuploadinput btn btn-default btn-block pull-right"
																	type="file" id="upload_input" name="upload"
																	style="opacity: 0; margin-top: -34px; height: 35px;" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:if>

									<c:if test="${not empty propertyMetadata.image2 }">
										<div class="col-xs-12 col-md-6 col-sm-6 col-lg-4">
											<div class="panel panel-default">
												<div class="panel-heading">
													<form:label path="image2">${propertyMetadata.image2 }</form:label>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
															<c:choose>
																<c:when
																	test="${not empty uploadForm.propertyValue.image2Model}">
																	<a class="thumbnail"
																		href="${base_image_url}/${baseSizeDir}/${uploadForm.propertyValue.image2Model.id}.${imageExtn}">
																		<img class="image-responsive upload-preview"
																		id="thumbnail_image2" alt="..."
																		src="${base_image_url}/${sizeDir}/${uploadForm.propertyValue.image2Model.id}.${imageExtn}" />
																	</a>
																</c:when>
																<c:otherwise>
																	<a href="#" class="thumbnail"> <img
																		class="image-responsive no-image upload-preview"
																		id="thumbnail_image2" alt="" src="${emptyImageUrl} ">
																	</a>
																</c:otherwise>
															</c:choose>
														</div>
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
															<div>
																<button class="btn btn-default btn-block pull-right">
																	Upload File</button>
																<form:input path="image2"
																	class="imageuploadinput btn btn-default btn-block pull-right"
																	type="file" id="upload_input" name="upload"
																	style="opacity: 0; margin-top: -34px; height: 35px;" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:if>

									<c:if test="${not empty propertyMetadata.image3 }">
										<div class="col-xs-12 col-md-6 col-sm-6 col-lg-4">
											<div class="panel panel-default">
												<div class="panel-heading">
													<form:label path="image2">${propertyMetadata.image3 }</form:label>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
															<c:choose>
																<c:when
																	test="${not empty uploadForm.propertyValue.image3Model}">
																	<a class="thumbnail"
																		href="${base_image_url}/${baseSizeDir}/${uploadForm.propertyValue.image3Model.id}.${imageExtn}">
																		<img class="image-responsive upload-preview"
																		id="thumbnail_image3" alt="..."
																		src="${base_image_url}/${sizeDir}/${uploadForm.propertyValue.image3Model.id}.${imageExtn}" />
																	</a>
																</c:when>
																<c:otherwise>
																	<a href="#" class="thumbnail"> <img
																		class="image-responsive no-image upload-preview"
																		id="thumbnail_image3" alt="" src="${emptyImageUrl} ">
																	</a>
																</c:otherwise>
															</c:choose>
														</div>
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
															<div>
																<button class="btn btn-default btn-block pull-right">
																	Upload File</button>
																<form:input path="image3"
																	class="imageuploadinput btn btn-default btn-block pull-right"
																	type="file" id="upload_input" name="upload"
																	style="opacity: 0; margin-top: -34px; height: 35px;" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:if>
								</div>
								<div class="row" id="propertyContent">
									<div class="col-xs-12 col-md-6 col-sm-6 col-lg-4">
										<c:if test="${not empty propertyMetadata.group1 }">
											<div class="panel panel-default">
												<div class="panel-heading">${propertyMetadata.group1 }</div>
												<div class="panel-body">
													<c:if test="${not empty propertyMetadata.group1Subgroup1 }">
														<h3>${propertyMetadata.group1Subgroup1}</h3>
														<table>
															<c:if
																test="${not empty propertyMetadata.group1Subgroup1Property1 }">
																<tr>
																	<td><form:label
																			path="propertyValue.group1Subgroup1Property1">${propertyMetadata.group1Subgroup1Property1 }</form:label>
																	</td>
																	<td><form:input
																			path="propertyValue.group1Subgroup1Property1"
																			type="text" /></td>
																</tr>
															</c:if>

															<c:if
																test="${not empty propertyMetadata.group1Subgroup1Property2 }">
																<tr>
																	<td><form:label
																			path="propertyValue.group1Subgroup1Property2">${propertyMetadata.group1Subgroup1Property2 }</form:label>
																	</td>
																	<td><form:input
																			path="propertyValue.group1Subgroup1Property2"
																			type="text" /></td>
																</tr>
															</c:if>
														</table>
													</c:if>
												</div>
											</div>
										</c:if>
									</div>
								</div>
								<br />
								<div class="row" id="submit">
									<div class="hidden-xs hidden-sm col-md-2 col-lg-3"></div>
									<div class="col-xs-12 col-xs-12 col-md-8 col-lg-6">
										<button type="submit" class="btn btn-primary btn-block">Upload
											Product</button>
									</div>
									<div class="hidden-xs hidden-sm col-md-2 col-lg-3"></div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
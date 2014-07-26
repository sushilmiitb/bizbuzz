<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		BizBuzz-Category
	</tiles:putAttribute>

	<tiles:putAttribute name="customJsCode">
		<script type="text/javascript">
			/*$(document).ready(function(){
				// (C) WebReflection Mit Style License
				var global = this;
				
				// simple FileReader detection
				if (!global.FileReader){
					// no way to do what we are trying to do ...
					//return $message.innerHTML = "FileReader API not supported";
				}
				
				// async callback, received the
				// base 64 encoded resampled image
				function resampled(data) {
					//$message.innerHTML = "done";
					$('#blah').attr('src', data);
				}
			
				// async callback, fired when the image
				// file has been loaded
				function load(e) {
					//$message.innerHTML = "resampling ...";
					// see resample.js
					Resample(
							this.result,
							this._width || null,
							this._height || null,
							resampled
					);
			
				}
			
				// async callback, fired if the operation
				// is aborted ( for whatever reason )
				function abort(e) {
					//$message.innerHTML = "operation aborted";
				}
			
				// async callback, fired
				// if an error occur (i.e. security)
				function error(e) {
					//$message.innerHTML = "Error: " + (this.result || e);
				}
			
				// listener for the input@file onchange
				$('.fileuploadinput').change(function change(){
					var
					// retrieve the width in pixel
					width = 180,
					// retrieve the height in pixels
					height = null,
					// temporary variable, different purposes
					file
					;
					// no width and height specified
					// or both are NaN
					if (!width && !height) {
						// reset the input simply swapping it
						this.parentNode.replaceChild(
								file = this.cloneNode(false),
								this
						);
						// remove the listener to avoid leaks, if any
						this.removeEventListener("change", change, false);
						// reassign the this DOM pointer
						// with the new input text and
						// add the change listener
						this.addEventListener("change", change, false);
					} else if(
							// there is a files property
							// and this has a length greater than 0
							(this.files || []).length &&
							// the first file in this list 
							// has an image type, hopefully
							// compatible with canvas and drawImage
							// not strictly filtered in this example
							/^image\//.test((file = this.files[0]).type)
					) {
						// reading action notification
						//$message.innerHTML = "reading ...";
						// create a new object
						file = new FileReader;
						// assign directly events
						// as example, Chrome does not
						// inherit EventTarget yet
						// so addEventListener won't
						// work as expected
						file.onload = load;
						file.onabort = abort;
						file.onerror = error;
						// cheap and easy place to store
						// desired width and/or height
						file._width = width;
						file._height = height;
						// time to read as base 64 encoded
						// data te selected image
						file.readAsDataURL(this.files[0]);
						// it will notify onload when finished
						// An onprogress listener could be added
						// as well, not in this demo tho (I am lazy)
					} else if (file) {
						// if file variable has been created
						// during precedent checks, there is a file
						// but the type is not the expected one
						// wrong file type notification
						//$message.innerHTML = "please chose an image";
					} else {
						// no file selected ... or no files at all
						// there is really nothing to do here ...
						//$message.innerHTML = "nothing to do";
					}
				});
			});*/
		</script>
		
		
		<script type="text/javascript">
		$(document).ready(function(){
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
					reader.readAsArrayBuffer(file);
		
					reader.onload = function (event) {
						// blob stuff
						var blob = new Blob([event.target.result]); // create blob...
						window.URL = window.URL || window.webkitURL;
						var blobURL = window.URL.createObjectURL(blob); // and get it's URL
		
						// helper Image object
						var image = new Image();
						image.src = blobURL;
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
		
					return canvas.toDataURL("image/jpeg",0.7); // get the data from canvas as 70% JPG (can be also PNG, etc.)
		
				}
				readfile();
		
			});
		});
		</script>
				
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="form_upload_url" value="/seller/uploadproduct/category/${categoryId}"/>
		<c:if test="${not empty itemId }">
			<c:url var="form_upload_url" value="/seller/uploadproduct/category/${categoryId}/item/${itemId }"/>
		</c:if>
		<c:url var="base_image_url" value="/${rootDir}" />
		<h1>${parentCategoryName}</h1>
		<form:form action="${form_upload_url}" class="productuploadform" modelAttribute="uploadForm" method="POST" enctype="multipart/form-data">
			<table>
				<c:if test="${not empty propertyMetadata.primaryImage }">
					<tr>
						<td>
							<form:label path="primaryImage">${propertyMetadata.primaryImage }</form:label>
						</td>
						<td>
							<form:input class="imageuploadinput" path="primaryImage" type="file"/>
						</td>
					</tr>
					<c:if test="${not empty uploadForm.propertyValue.primaryImageModel}">
					<tr>
						<td colspan="2">
							<img src="${base_image_url}/${sizeDir}/${uploadForm.propertyValue.primaryImageModel.id}.${imageExtn}" />
						</td>
					</tr>
					</c:if>
				</c:if>
				
				<c:if test="${not empty propertyMetadata.image1 }">
					<tr>
						<td>
							<form:label path="image1">${propertyMetadata.image1 }</form:label>
						</td>
						<td>
							<form:input class="imageuploadinput" path="image1" type="file"/>
						</td>
					</tr>
					<c:if test="${not empty uploadForm.propertyValue.image1Model}">
					<tr>
						<td colspan="2">
							<img src="${base_image_url}/${sizeDir}/${uploadForm.propertyValue.image1Model.id}.${imageExtn}" />
						</td>
					</tr>
					</c:if>
				</c:if>
				
				<c:if test="${not empty propertyMetadata.image2 }">
					<tr>
						<td>
							<form:label path="image2">${propertyMetadata.image2 }</form:label>
						</td>
						<td>
							<form:input class="imageuploadinput" path="image2" type="file"/>
						</td>
					</tr>
					<c:if test="${not empty uploadForm.propertyValue.image2Model}">
					<tr>
						<td colspan="2">
							<img src="${base_image_url}/${sizeDir}/${uploadForm.propertyValue.image2Model.id}.${imageExtn}" />
						</td>
					</tr>
					</c:if>
				</c:if>
				
				<c:if test="${not empty propertyMetadata.image3 }">
					<tr>
						<td>
							<form:label path="image3">${propertyMetadata.image3 }</form:label>
						</td>
						<td>
							<form:input class="imageuploadinput" path="image3" type="file"/>
						</td>
					</tr>
					<c:if test="${not empty uploadForm.propertyValue.image3Model}">
					<tr>
						<td colspan="2">
							<img src="${base_image_url}/${sizeDir}/${uploadForm.propertyValue.image3Model.id}.${imageExtn}" />
						</td>
					</tr>
					</c:if>
				</c:if>
			</table>
			<c:if test="${not empty propertyMetadata.group1 }">
				<h2>${propertyMetadata.group1 }</h2>
				<c:if test="${not empty propertyMetadata.group1Subgroup1 }">
					<h3>${propertyMetadata.group1Subgroup1}</h3>
					<table>
						<c:if test="${not empty propertyMetadata.group1Subgroup1Property1 }">
							<tr>
								<td>
									<form:label path="propertyValue.group1Subgroup1Property1">${propertyMetadata.group1Subgroup1Property1 }</form:label>
								</td>
								<td>
									<form:input path="propertyValue.group1Subgroup1Property1" type="text"/>
								</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty propertyMetadata.group1Subgroup1Property2 }">
							<tr>
								<td>
									<form:label path="propertyValue.group1Subgroup1Property2">${propertyMetadata.group1Subgroup1Property2 }</form:label>
								</td>
								<td>
									<form:input path="propertyValue.group1Subgroup1Property2" type="text"/>
								</td>
							</tr>
						</c:if>
					</table>
				</c:if>
			</c:if>
			<input type="submit" value="Upload Product" />
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
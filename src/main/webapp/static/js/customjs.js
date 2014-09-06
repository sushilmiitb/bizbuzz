function loaderAnimation(){
	var element = document.createElement("div");
	$(element).addClass("loader");
	$("body").append(element);
	$(element).show();
}

function loadDivLoader(parentObj){
	var divElem = document.createElement("div");
	$(divElem).addClass("div-loader");
	$(parentObj).append(divElem);
}

$(window).load(function(){
	$(".loader").remove();
});

$(document).ready(function (){
	$(".loader").remove();
	$("a").click(function(){
		var href = $(this).attr("href");
		if(href=="" || href=="#"){
			return;
		}
		loaderAnimation();
	});
	
	$("form").submit(function(){
		loaderAnimation();
	});
});
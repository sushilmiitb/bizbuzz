function loaderAnimation(){
	var element = document.createElement("div");
	$(element).addClass("loader");
	$("body").append(element);
	$(element).show();
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
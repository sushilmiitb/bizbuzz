<script src="../../../../static/js/jquery/jquery-1.11.1.min.js"></script>
<script src="../../../../static/js/mobile/jquery.mobile-1.4.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var debug = true;
		$("#form-submit").click(function(event){
			event.preventDefault();
			var elem = $('#register_personregistration_firstname');
			if(debug){
				console.log ("firstname", elem.val());
			}
			if(!firstname.val()){
				$("<span>First name cannot be empty</span>").insertAfter(elem).addClass("error");
			}
		});
	});
</script>
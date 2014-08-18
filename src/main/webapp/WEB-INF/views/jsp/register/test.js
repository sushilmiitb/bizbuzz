<script src="../../../../static/js/jquery/jquery-1.11.1.min.js"></script>

<script type="text/javascript">
var linkElement = document.createElement('a');
$(linkElement).addClass("list-group-item");
$(linkElement).attr("href", "<c:url value='/seller/viewgroup/'/>"+data.id);
var divOut = document.createElement('div');
$(divOut).addClass('row');
var divInner = document.createElement('div');
$(divInner).addClass('col-xs-12 col-sm-12 col-md-12 col-lg-12');

var innerhtml = '<span class="glyphicon glyphicon-user"></span>'+${item.privateGroupName};
$(divInner).append(innerhtml);
$(divOut).append(divInner);
$(linkElement).append(divOut);

$(linkElement).insertBefore($(".list-group-item").first());
$(".loader").remove();  
</script>

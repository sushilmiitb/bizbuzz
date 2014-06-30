<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<tiles:insertDefinition name="base.definition">
<tiles:putAttribute name="header">
<link rel="stylesheet" href="<c:url value='/static/css/main.css'/>" type="text/css">

    <script src="<c:url value='/static/js/jquery/jquery.js'/>"></script>
    <script src="<c:url value='/static/js/jquery/jquery.atmosphere.js'/>"></script>
    <script src="<c:url value='/static/js/jquery/jquery.tmpl.min.js'/>"></script>
  
</tiles:putAttribute>
<tiles:putAttribute name="body">
<div class="container">
      <div id="header" class="prepend-1 span-22 append-1 last">
        <h1 class="loud">Welcome to Spring Web MVC - Atmosphere Sample</h1>
      </div>
      <div id="content" class="prepend-1 span-17 prepend-top last">
        <input id="message-field" type="text" size="40" value="Send a tweet to connected clients" /> <input id="message-button" type="button" value="Send" />
        <ul id="twitterMessages">
          <li id="placeHolder">Searching...</li>
        </ul>
      </div>
    </div>

    <script type="text/javascript">

      $(function() {

        if (!window.console) {
          console = {log: function() {}};
        }

        function refresh() {

          console.log("Refreshing data tables...");

        }

        var socket = $.atmosphere;
        var request = new $.atmosphere.AtmosphereRequest();
        request.url = document.location.toString() + '/../websockets';
        request.contentType = "application/json";
        request.transport = 'websocket';
        request.fallbackTransport = 'long-polling';
        
        request.onOpen = function(response){
          
          console.log('onOpen: connection opened using transport:' + response.transport);
        }
        
        request.onReconnect = function(request, response){
          console.log('onReconnect:');
          socket.info("Reconnecting");
        }
        
        request.onMessage = function(response){
          var message = response.responseBody;
          console.log('onMessage: Message is:'+message);
          try {
            $("#twitterMessages").append("<li>"+message+"</li>");
          } catch (e) {
            console.log('Error: ', message.data);
            return;
          }
        }
        
        request.onError = function(response){
          content.html($('<p>', { text: 'Sorry, but '
                  + 'there some problem with your '
                  + 'socket or the server is down' }));
        }
        
        var subSocket = socket.subscribe(request);
        
        $('#message-button').click(function() {
          console.log('Clicked MessageButton'+$('#message-field').val());
          subSocket.push($('#message-field').val());
        });
      });
    </script>
</tiles:putAttribute>
</tiles:insertDefinition>
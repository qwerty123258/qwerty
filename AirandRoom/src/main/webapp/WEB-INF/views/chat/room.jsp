<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="ko">
  <head>
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.19.2/axios.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>웹소켓 채팅방</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <style>
      [v-cloak] {
          display: none;
      }
      .profile{
  border-radius: 50px;
      }
    </style>
  </head>
  <body>
  
  

        
    <div class="container" id="app" v-cloak>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="input-group">
            <input type="hidden" class="form-control" v-model="room_name">
        </div>
        <table class="table" id="chatRoomList">
		
        </table>
    </div>
    <!-- JavaScript -->


    <script>
    var id='${id}';
    function enterRoom(name){
        localStorage.setItem('wschat.sender','${id}');
        localStorage.setItem('wschat.roomId',name);
    	location.href="room/enter/"+name;
    }
    
    function findAllRoom(){
    	var html="";
    	$.ajax({
    		type:"POST",
    		url:"chatRoomSelect",
    		dataType:"json",
    		success:function(result){
    			for(var i=0; i<result.length; i++){
    				html +="<tr onclick='enterRoom(\""+result[i].name+"\")''>";
    				html +="<td>";
    				html+="<img class='profile' style='width:100px;height:100px;' src='${pageContext.request.contextPath}/resources/fileUpload/"+result[i].imgname+"'>";
    				html +="</td>";
    				html +="<td style='width:75%;'>";
    				html+=result[i].name +"와의 대화";
    				html +="<br><br>";
    				html+=result[i].contents;
    				html +="</td>";
    				html +="<td>";
    				html+=result[i].cdate;
    				html +="</td>";    
                	html += "</tr>";
    			}
    			$("#chatRoomList").html(html);
    		},error:function(){
    			
    		}
    		
    	});
    }
        var vm = new Vue({
            el: '#app',
            data: {
                room_name : '${id}',
                chatrooms: [
                ]
            },
            created() {
                if(id=="admin"){
                	findAllRoom();
                    var sock = new SockJS("http://localhost:8090/airandroom/ws-stomp");
                    var ws = Stomp.over(sock);
                    ws.connect(
                    		{}, 
                    		function(frame) {
                        ws.subscribe("/sub/chat/room/admin",
                        function(message) {
                        	findAllRoom();
                            
                        });
                    }, 
                    function(error) {
                        alert("error "+error);
                    });
                    
                    
                }
                if(id!="admin"){
                    this.createRoom();
                    this.enterRoom(id);
                }
            },
            methods: {
                createRoom: function() {
                    if("" === this.room_name) {
                        alert("로그인 하셔야합니다.");
                        return;
                    } else {
                        var params = new URLSearchParams();
                        params.append("name",this.room_name);
                        axios.post('http://localhost:8090/airandroom/chat/room', params)
                        .then(
                            response => {
                                this.room_name = '';
                            }
                        )
                        .catch( response => { alert("채팅방 개설에 실패하였습니다."); } );
                    }
                },
                enterRoom: function(roomId) {
                    localStorage.setItem('wschat.sender','${id}');
                    localStorage.setItem('wschat.roomId',roomId);
                    location.href="room/enter/"+roomId;
                }
            }
        });
    </script>
  </body>
</html>
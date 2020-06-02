<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ko">
  <head>
    <title>채팅방</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
$(document).ready(function(){
    $("#scroll").scrollTop($("#scroll")[0].scrollHeight);

});
	</script>
    <style>
      [v-cloak] {
          display: none;
      }
      .input-group{
    position: absolute;

    left: 0;

    bottom: 0;

    width: 100%;
	padding-bottom:20px;
      }
      #scroll{
      height: 580px;
      overflow-y: scroll;
      }
  body::-webkit-scrollbar {
    display: none;
    }
.balloon_03 {
 position:relative;
 margin: 15px;
  margin-right: 200px;
 width:200px;
 height:auto;
  background:skyblue;
  border-radius: 10px;
    padding:10px;
}
.balloon_03:after {
 border-top:15px solid skyblue;
 border-left: 15px solid transparent;
 border-right: 0px solid transparent;
 border-bottom: 0px solid transparent;
 content:"";
 position:absolute;
 top:10px;
 left:-15px;
}
.balloon_04 {
 position:relative;
 margin: 15px;
  margin-left: 200px;
 width:200px;
 height:auto;
  background:yellow;
  border-radius: 10px;
  padding:10px;
}
.balloon_04:after {
 border-top:15px solid yellow;
 border-right: 15px solid transparent;
 border-left: 0px solid transparent;
 border-bottom: 0px solid transparent;
 content:"";
 position:absolute;
 top:10px;
 right:-15px;
}
    </style>
  </head>
  <body>
    <div id="app" v-cloak>
        <div>
        <c:if test="${sessionScope.id eq 'admin'}">
                	<a href="../../../chat/room">뒤로가기</a>
        </c:if>
            <h2>관리자와 채팅</h2>
        </div>
        <div id="scroll" onscroll="chat_on_scroll()">
        <c:forEach var="message" items="${list}">
        <c:if test="${message.name==sessionScope.id}">
                   <div style="text-align:right;">
   			 <div class="balloon_04">${message.contents}</div>
   			 </div>
        </c:if>
                <c:if test="${message.name!=sessionScope.id}">
                   <div style="text-align:left;">
   			 ${message.name}
   			 <div class="balloon_03">${message.contents}</div>
   			 </div>
        </c:if>
        </c:forEach>
   			 <div v-for="(message,i) in messages" >
   			 <div v-if="message.sender.includes(receiver)" style="text-align:right;">
   			 <div class="balloon_04">{{message.message}}</div>
   			 </div>
   			 <div v-else style="text-align:left;">
   			 {{message.sender}}
   			 <div class="balloon_03">{{message.message}}</div>
   			 </div>
            </div>
            </div>
         <div class="input-group">
            <input type="text" id="chatCheck"  class="form-control" style="width:85%"  v-model="message" @keyup.enter="sendMessage">
             <button class="btn btn-primary" id="chatbtn" type="button" @click="sendMessage">보내기</button>
        </div>
    </div>
    <!-- JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.19.2/axios.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script>
    var pre_diffHeight = 0;
    var bottom_flag = true;
    var chat_on_scroll = function(){
            var objDiv = document.getElementById("scroll");
     
            if((objDiv.scrollTop + objDiv.clientHeight) == objDiv.scrollHeight){
                    // 채팅창 전체높이 + 스크롤높이가 스크롤 전체높이와 같다면
                    // 이는 스크롤이 바닥을 향해있다는것이므로
                    // 스크롤 바닥을 유지하도록 플래그 설정
                    bottom_flag = true;
            }

     if(pre_diffHeight > objDiv.scrollTop + objDiv.clientHeight ){
                    // 스크롤이 한번이라도 바닥이 아닌 위로 상승하는 액션이 발생할 경우
                    // 스크롤 바닥유지 플래그 해제
                    bottom_flag = false;  
     }
            //
            pre_diffHeight = objDiv.scrollTop + objDiv.clientHeight
    };
    var check = "";
    
        // websocket & stomp initialize
        var sock = new SockJS("http://localhost:8090/airandroom/ws-stomp");
        var ws = Stomp.over(sock);
        // vue.js
        var vm = new Vue({
            el: '#app',
            data: {
                roomId: '',
                room: {},
                sender: '',
                message: '',
                messages: [],
                receiver:'${sessionScope.id}',
            },
            created() {
                this.roomId = localStorage.getItem('wschat.roomId');
                this.sender = localStorage.getItem('wschat.sender');
                this.findRoom();
            },
            updated : function() {
                // app_chat_list 의 변화가 발생할때마다 수행되는 영역

                var objDiv = document.getElementById("scroll");
                if(bottom_flag){
                    // 채팅창 스크롤 바닥 유지
                    objDiv.scrollTop = objDiv.scrollHeight;
                }
            },
            methods: {
                findRoom: function() {
                    axios.get('http://localhost:8090/airandroom/chat/room/'+this.roomId).then(response => {
                    	this.room = response.data;
                    });
                },
                sendMessage: function() {
         check=$("#chatCheck").val();
         if(check!=""){
        	 ws.send("/pub/chat/message", {}, 
        	JSON.stringify({ roomId:this.roomId, sender:this.sender, message:this.message}));
             this.message = '';	

         }
                   
                },
                recvMessage: function(recv) {
                    this.messages.push({
                    	"sender":recv.sender,
                    	"message":recv.message
                    });
                    
                   
                },
              	scrollToEnd: function() {
                    var container = this.$el.querySelector("#scroll");
                    container.scrollTop = container.scrollHeight;
                  }
            }
        });
        // pub/sub event
        ws.connect(
        		{}, 
        function(frame) {
            ws.subscribe("/sub/chat/room/"+vm.$data.roomId,
            function(message) {
                var recv = JSON.parse(message.body);
                vm.recvMessage(recv);
            }
            );
        }, 
        function(error) {
            alert("error "+error);
        }
        );
        

    </script>
  </body>
</html>
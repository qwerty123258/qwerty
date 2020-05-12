<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="ko">
  <head>
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.19.2/axios.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <title>Websocket Chat</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <style>
      [v-cloak] {
          display: none;
      }
    </style>
  </head>
  <body>
    <div class="container" id="app" v-cloak>
        <div class="row">
            <div class="col-md-12">
                <h3>채팅방 리스트</h3>
            </div>
        </div>
        <div class="input-group">
            <div class="input-group-prepend">
                <label class="input-group-text">방제목</label>
            </div>
            <input type="text" class="form-control" v-model="room_name" @keyup.enter="createRoom">
            <div class="input-group-append">
                <button class="btn btn-primary" type="button" @click="createRoom">채팅방 개설</button>
            </div>
        </div>
        <ul class="list-group">
            <li class="list-group-item list-group-item-action" v-for="item in chatrooms" v-bind:key="item.roomId" v-on:click="enterRoom(item.roomId)">
                {{item.name}}
            </li>
        </ul>
    </div>
    <!-- JavaScript -->
    <script>
        var vm = new Vue({
            el: '#app',
            data: {
                room_name : '',
                chatrooms: [
                ]
            },
            created() {
                this.findAllRoom();
            },
            methods: {
                findAllRoom: function() {
                    axios.get('http://localhost:8090/chat/chat/rooms').then(response => { this.chatrooms = response.data; });
                },
                createRoom: function() {
                    if("" === this.room_name) {
                        alert("방 제목을 입력해 주십시요.");
                        return;
                    } else {
                        var params = new URLSearchParams();
                        params.append("name",this.room_name);
                        axios.post('http://localhost:8090/chat/chat/room', params)
                        .then(
                            response => {
                                alert(response.data.name+"방 개설에 성공하였습니다.")
                                this.room_name = '';
                                this.findAllRoom();
                            }
                        )
                        .catch( response => { alert("채팅방 개설에 실패하였습니다."); } );
                    }
                },
                enterRoom: function(roomId) {
                    var sender = prompt('대화명을 입력해 주세요.');
                    localStorage.setItem('wschat.sender',sender);
                    localStorage.setItem('wschat.roomId',roomId);
                    location.href="room/enter/"+roomId;
                }
            }
        });
    </script>
  </body>
</html>
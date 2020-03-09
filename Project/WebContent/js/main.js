/**
 * 
 */
	function getLikeTopList(){
        $.ajax({
            type:'GET',
            url : "LikeTopList",
            dataType : "json",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
            success : function(data){
                var html = "";
                var length=data.like.length;
                if(length > 0){
                	if(length<=5){
                    	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>순위</th><th>좋아요 수</th><th>글 번호</th><th>카테고리</th><th>글 제목</th><th>작성자</th><th>작성일</th></tr></thead>";
                    	for(var i=0; i<length; i++){
                            	html +="<tr onclick='goBoard("+data.like[i].bno+",\""+data.like[i].category+"\")'><td>"+(i+1)+"</td><td>"+data.like[i].likes+"</td><td>"+data.like[i].bno+"</td><td>"+data.like[i].category+"</td><td>"+data.like[i].title+"</td><td>"+data.like[i].id+"</td><td>"+data.like[i].writedate+"</td></a></tr>";
                    	}
                    	html +="</table>";
                	}
                	else{
                    	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>순위</th><th>좋아요 수</th><th>글 번호</th><th>카테고리</th><th>글 제목</th><th>작성자</th><th>작성일</th></tr></thead>";
                    	for(var i=0; i<5; i++){
                            	html +="<tr onclick='goBoard("+data.like[i].bno+",\""+data.like[i].category+"\")'><td>"+(i+1)+"</td><td>"+data.like[i].likes+"</td><td>"+data.like[i].bno+"</td><td>"+data.like[i].category+"</td><td>"+data.like[i].title+"</td><td>"+data.like[i].id+"</td><td>"+data.like[i].writedate+"</td></a></tr>";
                    	}
                    	html +="</table>";
                	}
                }
                $("#likeTop").html(html);
        		getViewTopList();
            },
            error:function(request,status,error){
                
           }
            
        });
		
	}
	
	function getViewTopList(){
        $.ajax({
            type:'GET',
            url : "ViewTopList",
            dataType : "json",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
            success : function(data){
                var html = "";
                var length=data.view.length;
                if(length > 0){
                	if(length<=5){
                    	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>순위</th><th>조회 수</th><th>글 번호</th><th>카테고리</th><th>글 제목</th><th>작성자</th><th>작성일</th></tr></thead>";
                    	for(var i=0; i<length; i++){
                            	html +="<tr onclick='goBoard("+data.view[i].bno+",\""+data.view[i].category+"\")'><td>"+(i+1)+"</td><td>"+data.view[i].bview+"</td><td>"+data.view[i].bno+"</td><td>"+data.view[i].category+"</td><td>"+data.view[i].title+"</td><td>"+data.view[i].id+"</td><td>"+data.view[i].writedate+"</td></tr>";

                    	}
                    	html +="</table>";
                	}
                	else{
                    	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>순위</th><th>조회 수</th><th>글 번호</th><th>카테고리</th><th>글 제목</th><th>작성자</th><th>작성일</th></tr></thead>";
                    	for(var i=0; i<5; i++){
                            	html +="<tr onclick='goBoard("+data.view[i].bno+",\""+data.view[i].category+"\")'><td>"+(i+1)+"</td><td>"+data.view[i].bview+"</td><td>"+data.view[i].bno+"</td><td>"+data.view[i].category+"</td><td>"+data.view[i].title+"</td><td>"+data.view[i].id+"</td><td>"+data.view[i].writedate+"</td></tr>";

                    	}
                    	html +="</table>";
                	}
                    	
                    }
                $("#viewTop").html(html);
        		getLatestTopList();
                
            },
            error:function(request,status,error){
                
           }
            
        });
		
	}
	function getLatestTopList(){
        $.ajax({
            type:'GET',
            url : "LatestTopList",
            dataType : "json",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
            success : function(data){
                var html = "";
                if(data.latest.length > 0){
                	var length=data.latest.length;
                	if(length<=10){
                    	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>글 번호</th><th>카테고리</th><th>글 제목</th><th>작성자</th><th>조회 수</th><th>작성일</th></tr></thead>";
                    	for(var i=0; i<data.latest.length; i++){
                        	
                            	html +="<tr onclick='goBoard("+data.latest[i].bno+",\""+data.latest[i].category+"\")'><td>"+data.latest[i].bno+"</td><td>"+data.latest[i].category+"</td><td>"+data.latest[i].title+"</td><td>"+data.latest[i].id+"</td><td>"+data.latest[i].bview+"</td><td>"+data.latest[i].writedate+"</td></tr>";
                           
                    	}
                    	html +="</table>";
                	}
                	else{
                    	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>글 번호</th><th>카테고리</th><th>글 제목</th><th>작성자</th><th>조회 수</th><th>작성일</th></tr></thead>";
                    	for(var i=0; i<10; i++){
                        	
                            	html +="<tr onclick='goBoard("+data.latest[i].bno+",\""+data.latest[i].category+"\")'><td>"+data.latest[i].bno+"</td><td>"+data.latest[i].category+"</td><td>"+data.latest[i].title+"</td><td>"+data.latest[i].id+"</td><td>"+data.latest[i].bview+"</td><td>"+data.latest[i].writedate+"</td></tr>";
                           
                    	}
                    	html +="</table>";
                	}
                $("#latestTop").html(html);
                
            }
            },
            error:function(request,status,error){
                
           }
            
        });
		
	}
	function goBoard(bno,category){
		var bno=bno;
		var category=category;
		location.href="BoardDetail?bno="+bno+"&category="+category;
	}
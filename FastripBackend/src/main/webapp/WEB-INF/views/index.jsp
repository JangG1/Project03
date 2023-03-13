<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp" %>

	<div class="container">
	
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">제목 적는 부분</h4>
				<a href="#" class="btn btn-primary">상세보기</a>
			</div>
		</div>
		
			<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">제목 적는 부분</h4>
				<a href="#" class="btn btn-primary">상세보기</a>
			</div>
		</div>
		
			<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">제목 적는 부분</h4>
				<a href="#" class="btn btn-primary">상세보기</a>
			</div>
			
		</div>
		
		<div>
		   <input type="text"  name="voice">
        <button type="button" onclick="sendData()">전송</button>
		</div>
	</div>

<script>
function sendData() {
    axios({
            url: 'http://localhost:7777/tts',
            method: 'post',
            data: {
                voice : "123"
              }
        }).then((response) => {
            console.log(response)
        });
    }
</script>

<%@ include file="layout/footer.jsp" %>



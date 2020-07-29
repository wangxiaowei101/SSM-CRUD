<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<%
 pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH }/static/js/jquery-1.12.4.js"></script>
 <script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<style type="text/css">

      
body {
    background: url("static/bg.png");


}



     </style>

<script type="text/javascript">
	$(function(){
	
		
		//登录
		$("#submitBtn").click(function(){
			
			
			var data = $("#form").serialize();
			
			$.ajax({
				type: "post",
				url: "${APP_PATH }/login",
				data: data, 
				dataType: "json", //返回数据类型
				success: function(data){
					if("success" == data.type){
						window.location.href = "index";
					} else{
						alert(data.msg);
						$("#vcodeImg").click();//切换验证码
						$("input[name='vcode']").val("");//清空验证码输入框
					}	
				}
			});
		});
	
	})
</script>
<body >
 
 
    <div class="modal-dialog" style="margin-top: 10%;">
        <div class="modal-content">
        <form id="form"  method="post">
            <div class="modal-header">
 
                <h4 class="modal-title text-center" id="myModalLabel">管理员登录</h4>
            </div>
            <div class="modal-body" id = "model-body">
                <div class="form-group">
                 
                    <input type="text" name="username" class="form-control "placeholder="用户名" autocomplete="off">
                </div>
                <div class="form-group">
 
                    <input type="password" name="password" class="form-control" placeholder="密码" autocomplete="off">
                </div>
                
            </div>
            <div class="modal-footer">
                <div class="form-group">
                    <button id="submitBtn" type="button" class="btn btn-primary form-control">登录</button>
                </div>
 
            </div>
          
        </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
 
</body>


</html>
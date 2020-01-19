<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工列表</title>
<%
 pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH }/static/js/jquery-1.12.4.js"></script>
 <script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
  <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head"><span style="color:red; font-weight:bold;">${user}&nbsp;</span>您好&nbsp;&nbsp;&nbsp;<a href="${APP_PATH}/loginOut" id="loginOut">安全退出</a></span>

    </div>

<!-- 员工修改模态框 -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" >员工修改</h4>
      </div>
      <div class="modal-body">
      
        <form class="form-horizontal">
  <div class="form-group">
    <label class="col-sm-2 control-label">empName</label>
    <div class="col-sm-10">
      <p class="form-control-static" id="empName_update_static"></p>
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">email</label>
    <div class="col-sm-10">
      <input type="text" name="email" class="form-control" id="email_update_input" placeholder="email@qq.com">
      <span class="help-block"></span>
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">gender</label>
    <div class="col-sm-10">
     <label class="radio-inline">                                        
     
  <input type="radio" name="gender" id="gender1_update_input" value="M" checked="checked"> 男
</label>
<label class="radio-inline">
  <input type="radio" name="gender" id="gender2_update_input" value="F"> 女
</label>
    </div>
  </div>
   <div class="form-group">
    <label  class="col-sm-2 control-label">deptName</label>
    <div class="col-sm-4">
    <!-- 部门提交部门id -->
   <select class="form-control" name="dId" >
		  
</select>
   
    </div>
  </div>
  
</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_update_btn" >更新</button>
      </div>
    </div>
  </div>
</div>





<!-- 员工增加模态框 -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
      </div>
      <div class="modal-body">
      
        <form class="form-horizontal">
  <div class="form-group">
    <label class="col-sm-2 control-label">empName</label>
    <div class="col-sm-10">
      <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="empName">
      <span class="help-block"></span>
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">email</label>
    <div class="col-sm-10">
      <input type="text" name="email" class="form-control" id="email_add_input" placeholder="email@qq.com">
      <span class="help-block"></span>
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">gender</label>
    <div class="col-sm-10">
     <label class="radio-inline">                                        
     
  <input type="radio" name="gender" id="gender1_add_input" value="M" checked="checked"> 男
</label>
<label class="radio-inline">
  <input type="radio" name="gender" id="gender2_add_input" value="F"> 女
</label>
    </div>
  </div>
   <div class="form-group">
    <label  class="col-sm-2 control-label">deptName</label>
    <div class="col-sm-4">
    <!-- 部门提交部门id -->
   <select class="form-control" name="dId" >
		  
</select>
   
    </div>
  </div>
  
</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_save_btn" >保存</button>
      </div>
    </div>
  </div>
</div>

<!-- col-md-?==显示所占格数 -->
<!-- 搭建显示页面 -->
<div class="container">
                <!-- 标题 -->
         <div class="row">
         <div class="col-md-12">
             <h1>SSM-CRUD</h1>
         </div>
         </div>
          <!-- 按钮 -->
          <div class="row">
          <div class="col-md=4 col-md-offset-8">
          
          <button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
          <button class="btn btn-danger"id="emp_delete_all_btn">删除</button>
          </div>
          </div>
           <!-- 显示数据-->
           <div class="row">
           <div class="col-md-12">
           <table class="table table-hover" id="emps_table">
           <thead>
               <tr>
               <th>
                <input type="checkbox" id="checkbox_all"/>
               </th>
               <th>#</th>
               <th>empName</th>
               <th>gender</th>
               <th>email</th>
               <th>deptName</th>
               <th>操作</th>
             </tr>
           </thead>
           
           <tbody>
           
           </tbody>
             
           </table>
           
           </div>
           
           </div>
            <!-- 显示分页信息 -->
            <div class="row">
            <!-- 分页文字信息 -->
            <div class="col-md-6" id="page_info_area">

            </div>
            
            <!-- 分页条信息 -->
            <div class="col-md-6" id="page_nav_area">
            
          
  </div>
            
            </div>

</div>

<script type="text/javascript">
			//拿总记录数
          var totalRecord,currentPage;
        //1.页面加载后，直接发送ajax请求，要到分页数据
        $(function(){
        	//去首页
           to_page(1);
        });
        
        function to_page(pn){
        	$.ajax({
           		url:"${APP_PATH}/emps",
           		data:"pn="+pn,
           		type:"get",
           		success:function(result){
           			
           			//console.log(result);
           			//1.解析员工数据
           			//2，解析显示分页
           			build_emps_table(result);
           			//解析显示分页数据
           			build_page_info(result);
           		//解析显示分页条
           			build_page_nav(result)
           			
           		}
           	});
        }
        //每次解析显示之前必须清空之前数据$("#emps_table tbody").empty();
      //1.解析员工数据
			
        function build_emps_table(result){
        	
        	//清空table
        	$("#emps_table tbody").empty();
        	var emps=result.extend.PageInfo.list;
        	$.each(emps,function(index,item){
        		var checkBoxTd=$("<td><input type='checkbox' class='check_item'</td>")
        		var empIdTd =$("<td></td>").append(item.empId);
        		var empNameTd =$("<td></td>").append(item.empName);
        		var genderTd=$("<td></td>").append(item.gender=='M'?"男":"女");
        		var emailTd=$("<td></td>").append(item.email);
        		var daptNameTd =$("<td></td>").append(item.departmet.deptName);
        		
        		// <button class="btn btn-primary btn-sm">
//                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>           编辑
//                </button>
        		var editBtn=$("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
        		.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
        		//为编辑按钮添加自定义属性，表示当前员工id
        		editBtn.attr("edit-id",item.empId);
        		var delBtn=$("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
        		.append($("<span></span>").addClass("glyphicon glyphicon-trash ")).append("删除");
        		//为删除按钮添加自定义属性，表示当前员工id
        		delBtn.attr("del-id",item.empId);
        		var btnTd=$("<td></td>").append(editBtn).append(" ").append(delBtn);
        		//append方法执行完成以后还是返回原来的元素
	        	$("<tr></tr>").append(checkBoxTd)
	        	.append(empIdTd)
	        	.append(empNameTd)
	        	.append(genderTd)
	        	.append(emailTd)
	        	.append(daptNameTd)
	        	.append(btnTd)
	        	
	        	.appendTo("#emps_table tbody");
	        		
	        	});
        		
        	
        	
        }
        //解析显示分页信息
        function build_page_info(result){
        	$("#page_info_area").empty();
        	
        	$("#page_info_area").append("当前"+result.extend.PageInfo.pageNum+"页，总"+
        			result.extend.PageInfo.pages+"页，总"+	result.extend.PageInfo.total+"条记录");
        	 totalRecord=result.extend.PageInfo.total;
        	 currentPage=result.extend.PageInfo.pageNum;
        	
        
        }
        //解析显示分页条
 function build_page_nav(result){
        	
		$("#page_nav_area").empty();
        	var ul=$("<ul></ul>").addClass("pagination");
        	//构建元素
        	var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
        	var prePageLi=$("<li></li>").append($("<a></a>").append("&laquo;"));
        	if(result.extend.PageInfo.hasPreviousPage==false){
        		firstPageLi.addClass("disabled");
        		prePageLi.addClass("disabled");
        	}else{
        		firstPageLi.click(function(){
            		to_page(1);
            	});
            	prePageLi.click(function(){
            		to_page(result.extend.PageInfo.pageNum-1);
            	});
            	
        	}
        	//为元素添加点击翻译单击事件
        
        	var nextPageLi=$("<li></li>").append($("<a></a>").append("&raquo;"));
        	var lastPageLi=$("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
        	if(result.extend.PageInfo.hasNextPage==false){
        		nextPageLi.addClass("disabled");
        		lastPageLi.addClass("disabled");
        	}else{
        		lastPageLi.click(function(){
            		to_page(result.extend.PageInfo.pages);
            	});
            	nextPageLi.click(function(){
            		to_page(result.extend.PageInfo.pageNum+1);
            		
            	});
        	}
        	
        	
        	
        	ul.append( firstPageLi).append(prePageLi);
        	
        	$.each(result.extend.PageInfo.navigatepageNums,function(index,item){
        		
        		var numLi=$("<li></li>").append($("<a></a>").append(item));
        		if(result.extend.PageInfo.pageNum==item){
        			numLi.addClass("active");
        		}
        		numLi.click(function(){
        			to_page(item);
        		});
        		
        		ul.append(numLi);
        	});
        		ul.append(nextPageLi).append(lastPageLi);
        	
        		var navEle=$("<nav></nav>").append(ul);
        		
        		navEle.appendTo("#page_nav_area");
        		
	
        
        }
        function reset_form(ele){
        	$(ele)[0].reset();
        	//清空表单样式及内容
        	$(ele).find("*").removeClass("has-error has-success");
        	$(ele).find(".help-block").text("");
        };
        
        //点击新增按钮弹出框
        $("#emp_add_modal_btn").click(function(){
        	//清空表单数据（包括样式）
        	
        	
        	reset_form("#empAddModal form");
        	$("#empAddModal select").empty();
        	
        	
        	getDepts("#empAddModal select");
        	//发送ajax请求，查出部门信息，显示在下拉列表
        	
        	$("#empAddModal").modal({
        		backdrop:"static"
        	});      	
        });
        
        //查询部门信息
        function getDepts(ele){
        	$.ajax({
        		url:"${APP_PATH}/depts",
        		type:"get",
        		
        		success:function(result){
        		
        			//$("#dept_add_select").append("")
        			$.each(result.extend.depts,function(){
        				var optionEle=$("<option></option>").append(this.deptName).attr("value",this.deptId);
        				optionEle.appendTo(ele);
        				
        			});
        		}
        	});
        }
        //校验表单数据
		        function validate_add_form(){
		        	//1.拿到校验数据,使用正则表达式
		        	
		      var empName = $("#empName_add_input").val();
		      var regName= /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		       
		      
		      if(!regName.test(empName)){
// 		    	    alert("用户名可以是2-5位中文或6-16英文数字组合");
					show_validate_msg("#empName_add_input","error","用户名可以是2-5位中文或6-16英文数字组合");
 					
 					return false;
		       }else{
                     
                 	show_validate_msg("#empName_add_input","success",""); 
 					
		       };
		      
		       var email = $("#email_add_input").val();
		        var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		        if(!regEmail.test(email)){
// 		        	 alert("邮箱格式不正确");
                       // 应该清空元素之前的样式
                       show_validate_msg("#email_add_input","error","邮箱格式不正确"); 
       
		        	 return false;
		        	 
		        }else{
		        	
                     
                 	show_validate_msg("#email_add_input","success","");
		        };
		        return true;
		      
		        }
        //显示校验结果提示信息
        function show_validate_msg(ele,status,msg){
        	$(ele).parent().removeClass("has-success has-error");
        	$(ele).next("span").text("");
        	
        	if("success"==status){
        		$(ele).parent().addClass("has-success");
             	$(ele).next("span").text(msg);
        	}else if("error"==status){
        		$(ele).parent().addClass("has-error");
					$(ele).next("span").text(msg);
					
        		
        	}
        }
        
        $("#empName_add_input").change(function(){
        	//发送ajax校验用户名是否可用
        	//获取输入的值
        var empName = this.value;
        	$.ajax({
        		url:"${APP_PATH}/checkuser",
        		data:"empName="+empName,
        		type:"POST",
        		success:function(result){
        			if(result.code==100){
        				show_validate_msg("#empName_add_input","success","用户名可用");
        				 $("#emp_save_btn").attr("ajax-va","success");
        			}else{
        				show_validate_msg("#empName_add_input","error",result.extend.va_msg); 
        				 $("#emp_save_btn").attr("ajax-va","error");
        			}
        		}
        	});
        });
        
        //点击保存增加数据
        $("#emp_save_btn").click(function(){
        	//1.模态框中的数据提交
        	//先对提交数据进行校验
        	if(!validate_add_form()){
        		return false;
        		
        		
        	};
        	//1判断ajax校验是否成功
        	if($(this).attr("ajax-va")=="error"){
        		return false;
        	}
        	
        	//2.发送Aajx请求
        	//serialize提取提交数据
        	
        	$.ajax({
        		url:"${APP_PATH}/emp",
        		type:"POST",
        		data:$("#empAddModal form").serialize(),
        		success:function(result){
        			if(result.code==100){
        				//1.关闭模态框
        				$("#empAddModal").modal('hide');
            			//2.来到最后一页，显示新增数据
            			//发送ajax请求显示最后一页
            			to_page(totalRecord);
        			}else{
        				//显示失败信息
        				//有哪个字段的错误信息就显示哪个字段
        				if(undefined!=result.extend.errorFields.email){
        				//显示邮箱错误信息
        					show_validate_msg("#email_add_input","error",result.extend.errorFields.email);
        				}
        				if(undefined!=result.extend.errorFields.empName){
        					//显示用户名错误信息
        					show_validate_msg("#empName_add_input","error",result.extend.errorFields.empName);
        				}
        				
        			}

        		}
        	});
        });
        //因为编辑按钮是绑定单击事件之后请求完才有的故不能使用单击事件来处理
        $(document).on("click",".edit_btn",function(){
        	 //alert("ssssssss");
        	  $("#empUpdateModal select").empty();
        	 //1,查出部门信息显示部门列表
        	 
        	 getDepts("#empUpdateModal select");
        	//2，查询员工信息显示员工信息
        	getEmp($(this).attr("edit-id"));
        	 //3，把员工id传递给模态框更新按钮
        	 $("#emp_update_btn").attr("edit-id",$(this).attr("edit-id"));
        	$("#empUpdateModal").modal({
        		backdrop:"static"
        	}); 
        });
        function getEmp(id){
        	$.ajax({
        		url:"${APP_PATH}/emp/"+id,
        		type:"GET",
        		success:function(result){
        			var empData=result.extend.emp;
        			$("#empName_update_static").text(empData.empName);
        			$("#email_update_input").val(empData.email);
        			$("#empUpdateModal input[name=gender]").val([empData.gender]);
        			$("#empUpdateModal select").val([empData.dId]);
        		}
        	});
        }
        
        //点击更新，更新员工信息
       $("#emp_update_btn").click(function(){
        	//验证邮箱是否合法
        	
                
		       var email = $("#email_update_input").val();
		        var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		        if(!regEmail.test(email)){
//		        	 alert("邮箱格式不正确");
                    // 应该清空元素之前的样式
                    show_validate_msg("#email_update_input","error","邮箱格式不正确"); 
    
		        	 return false;
		        	 
		        }else{
		        	
                  
              	show_validate_msg("#email_update_input","success","");
		        }
		        
		        //2.发送ajax请求保存更新的数据
		        $.ajax({
		        	url:"${APP_PATH}/emp/"+$(this).attr("edit-id"),
		        	type:"PUT",
		        	//serialize序列化
		        	data:$("#empUpdateModal form").serialize(),
		        	success:function(result){
		        		//alert(result.msg);
		        		//1.关闭模态框
        				$("#empUpdateModal").modal('hide');
		        		//2.回到本面
		        		to_page(currentPage);
		        		
		        	}
		        	
		        });
        	
        });
        
       //点击删除，删除员工信息
        $(document).on("click",".delete_btn",function(){
        	//1,弹出是否删除对话框
        	var empName=$(this).parents("tr").find("td:eq(2)").text();
        	var empId=$(this).attr("del-id");
        	//alert($(this).parents("tr").find("td:eq(1)").text());
        	if(confirm("确认删除【"+empName+"】吗？")){
        		//确认，发送ajax删除请求
        		$.ajax({
        			url:"${APP_PATH}/emp/"+empId,
        			type:"DELETE",
        			success:function(result){
        				//2.回到本面
        				to_page(currentPage);
		        		
        			}
        			
        		});
        	}
        });
       //完成全选/全不选
       $("#checkbox_all").click(function(){
    	   //attr获取checked是undefined;
    	   //我们这些dom原生属性：attr获取自定义数据的值
    	   //prop修改dom原生属性的值
    	   //alert($(this).prop("checked"));
    	   $(".check_item").prop("checked",$(this).prop("checked"));
    	   //alert($(this).attr("checked"))
       });
     $(document).on("click",".check_item",function(){
    	 //判断当前选择中的元素是否5个
    	var flag= $(".check_item:checked").length==$(".check_item").length;
    		 $("#checkbox_all").prop("checked",flag);
    	 
     });
     
     //点击全部删除，就批量删除
     $("#emp_delete_all_btn").click(function(){
    	// $(".check_item:checked")
    	var empNames="";
    	var del_idstr="";
    	 $.each($(".check_item:checked"),function(){
    		 //相当于拿到员工姓名
    		empNames += $(this).parents("tr").find("td:eq(2)").text()+",";
    		 //组装员工id字符串
    		 del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";
    	 });
    	 //去除empNames多余的逗号，
       empNames = empNames.substring(0,empNames.length-1);
    	 //去除多余-
       del_idstr = del_idstr.substring(0,del_idstr.length-1);
    	if(confirm("确认删除【"+empNames+"】吗？")){
    		//发送ajax请求删除
    		$.ajax({
    			url:"${APP_PATH}/emp/"+del_idstr,
    			
    			type:"DELETE",
    			success:function(result){
    				//删除复选框的勾
    				$("#checkbox_all").prop("checked",false);
    				//2.回到本面
    				to_page(currentPage);
    				
    			}
    		});
    	}
     });
</script>
</body>
</html>
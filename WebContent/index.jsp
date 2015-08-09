<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    
    <%
    String root = request.getContextPath(); 
    %>
    <!-- IE8兼容性设置 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
    <meta name="description" content="可信实验室信息管理系统">
    <meta name="author" content="skw">

    <title>Web01邮箱</title>

    <link href= <%=root+"/dist/css/bootstrap.min.css"%> rel="stylesheet">
    <!-- Documentation extras -->
    <link href= <%=root+"/docs-assets/css/docs.css"%> rel="stylesheet">   

    <style>
      body{font-family:"ff-tisa-web-pro-1","ff-tisa-web-pro-2","Lucida Grande","Helvetica Neue",Helvetica,Arial,"Hiragino Sans GB","Hiragino Sans GB W3","WenQuanYi Micro Hei",sans-serif;}
      h1, .h1, h2, .h2, h3, .h3, h4, .h4, .lead {font-family:"ff-tisa-web-pro-1","ff-tisa-web-pro-2","Lucida Grande","Helvetica Neue",Helvetica,Arial,"Hiragino Sans GB","Hiragino Sans GB W3","Microsoft YaHei UI","Microsoft YaHei","WenQuanYi Micro Hei",sans-serif;}
      pre code { background: transparent; }
      td{width:80px;height:40px}
    </style>

    <link rel="shortcut icon" href= <%=root+"/docs-assets/ico/icon.ico"%> >
    
</head>

<body class="bs-docs-home">
    
  <div class="bs-masthead" id="content">
    
    <div class="container">
      
      <h1 style="font-size:64px">Web01邮箱</h1>
      <br/>
      <p class="col-md-4" id="reminder" style='color:red'>
      ${message}
      <% 
      String info = (String) request.getAttribute("info");        
      if(info != null){
      	out.print(info);
      }
      %>
      </p>
      <br/>
      <br/>
      
      <form class="col-md-4" action="login" method="post" onsubmit="return verify(this)">
      	<table>
      		<tr>
      			<td>邮箱地址</td>
      			<td><input type="text" name="user.address" placeholder="Email"></td>
      			<td>@Web01.mail</td>
      		</tr>
      		<tr>
      			<td>密<span style="margin:14px"></span>码</td>
      			<td><input type="password" name="user.password" placeholder="Password"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td>验证码</td>	
      			<td><input type="text" name="securityCode" placeholder="SecurityCode"></td>
      			<td><img src="securityCode" id="image"  style="cursor:pointer" alt="看不清，换一张"/></td>
      		</tr>
      		<tr>
      			<td></td>
      			<td><button type="submit" class="btn btn-default">登陆</button><span style="margin-left:50px"><s:a action="regist">注册</s:a></span></td>
      			<td></td>      			
      		</tr>
      	</table>
      </form>
      <br>
                    
    </div> 
       
  </div> 

<script type="text/javascript">
    function verify(field){
    	
    	var x = field.childNodes[1];

    	if( x.rows[0].cells[1].firstChild.value == null || x.rows[0].cells[1].firstChild.value == ""){
    		document.getElementById("reminder").innerHTML = "邮箱地址不能为空!";
    		return false;
    	}    	
    	else if( x.rows[1].cells[1].firstChild.value == null || x.rows[1].cells[1].firstChild.value == ""){
    		document.getElementById("reminder").innerHTML = "密码不能为空!";
    		return false;
    	}
    	else{    		
    		field.submit();
    	}
    	
    }    
    window.onload=function(){
        document.getElementById("image").onclick=function(){
            this.src="securityCode?timestamp="+new Date().getTime();
        };
    };
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.springframework.web.context.WebApplicationContext, models.User" %>

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

    <title>Web01邮箱-写邮件</title>
    
    <link href= <%=root+"/dist/css/bootstrap.min.css"%> rel="stylesheet">
    <!-- Documentation extras -->
    <link href= <%=root+"/docs-assets/css/docs.css"%> rel="stylesheet">
    <script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
    
    <style>
      body{font-family:"ff-tisa-web-pro-1","ff-tisa-web-pro-2","Lucida Grande","Helvetica Neue",Helvetica,Arial,"Hiragino Sans GB","Hiragino Sans GB W3","WenQuanYi Micro Hei",sans-serif;}
      h1, .h1, h2, .h2, h3, .h3, h4, .h4, .lead {font-family:"ff-tisa-web-pro-1","ff-tisa-web-pro-2","Lucida Grande","Helvetica Neue",Helvetica,Arial,"Hiragino Sans GB","Hiragino Sans GB W3","Microsoft YaHei UI","Microsoft YaHei","WenQuanYi Micro Hei",sans-serif;}
      pre code { background: transparent; }      
      @media (min-width: 768px) {
          .bs-docs-home .bs-social,
          .bs-docs-home .bs-masthead-links {
                margin-left: 0;
          }
      }
      .bs-docs-section p {
          line-height: 2;
      }
      .bs-docs-section p.lead {
          line-height: 1.4;
      }
    </style>

    <link rel="shortcut icon" href= <%=root+"/docs-assets/ico/icon.ico"%> >

</head>

<body>

  <header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav">
    
    <div class="container">
      
      <div class="navbar-header">
        <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        </button>
        <p class="navbar-brand">Web01邮箱</p>
      </div>
        
      <nav class="collapse navbar-collapse bs-navbar-collapse">
        <ul class="nav navbar-nav">
          <li>
            <s:a action="inbox">收件箱</s:a>
          </li>
          <li>
            <s:a action="outbox">发件箱</s:a>
          </li>
          <li>
            <s:a action="unloading">转储</s:a>
          </li>
          <li>
            <s:a action="newMail">写邮件</s:a>
          </li>
        </ul>
         
        <ul class="nav navbar-nav navbar-right">
          <li>
            <s:a action="logout">注销</s:a>
          </li>
        </ul>
         
      </nav>
      
    </div>
     
  </header>
  
  <div class="bs-header" id="content">
    <div class="container">
      <h1>写邮件</h1>
      <p id="reminder" style="color:red"></p>
    </div>
  </div>
  

  <!-- 分栏 -->
  <div class="container bs-docs-container">

    <div class="row">
      
      <!-- 导航栏 -->
      <div class="col-md-3">
        <div class="bs-sidebar hidden-print" data-spy="affix" data-offset-top="200" >
          <ul class="nav bs-sidenav">
            <li>
              <a href="javascript:void(0)" onclick="submit()">发送</a>
            </li>
          </ul>
        </div>
      </div>
      <%
      	WebApplicationContext context = (WebApplicationContext)this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
      	User user = (User)context.getBean("user");
      %>
      
      <!-- 内容  -->
      <div class="col-md-9">
        <div class="bs-customizer" style="margin-bottom:100px;margin-top:30px">
        	<form action="send" name="form" method="post" enctype="multipart/form-data">
        		<div class="form-group">
        			<label>收件人</label>
        			<input type="email" name="mail.receiver" class="form-control" placeholder="收件人" id="receiver">
        		</div>
        		<div class="form-group">
        			<label>发件人</label>
        			<p><%="&nbsp&nbsp&nbsp"+user.getAddress()%></p>
        		</div>
        		<div class="form-group">
        			<label>主题</label>
        			<input type="text" name="mail.topic" class="form-control" placeholder="主题" id="topic">
        		</div> 
        		<div class="form-group" id="attach">
        			<label>附件</label>
        			<input type="file"  name="file" style="margin-left:10px;margin-bottom:10px" onchange="add(this)"/>        			
        		</div>       		
        		
        		<div class="form-group">        			
        			<textarea name="mail.content" class="form-control" rows="10" placeholder="邮件正文"></textarea>
        		</div>         		       		      		
        	</form>		
      	</div>
      </div>
      
    </div>
    
  </div>

<script type="text/javascript" src=<%=root+"/dist/js/prototype.js"%> ></script>
<script type="text/javascript">

	var int = self.setInterval("refresh()", 10000);
	
    function submit(){
    	
    	form = document.forms[0];
    	
    	value = document.getElementById("receiver").value;
    	if(value == null || value == ""){
    		alert("收件人不能为空！");
    		return;
    	}
    	value = document.getElementById("topic").value;
    	if(value == null || value == ""){
    		alert("主题不能为空！");
    		return;
    	} 
    	
    	form.submit(); 
    	//document.forms[1].submit();
    }
    function refresh(){
		new Ajax.Request("check?time="+new Date(), {
    		onComplete : function(request){
    			if(request.responseText == "true"){
    				document.getElementById("reminder").innerHTML = "您有新的邮件！";
    				window.clearInterval(int);
    			}
    		}
		});
	}
    function add(field){
    	var c = field.cloneNode(false);
    	document.getElementById("attach").appendChild(c);
    }
</script>  
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.springframework.web.context.WebApplicationContext, models.*, java.util.Set" %>

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

    <title>Web01邮箱-发件箱</title>
    
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
      td{width:160px;height:30px}
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
  
  <div class="bs-header">
    <div class="container">
      <h1>发件箱</h1>
      <p id="reminder" style="color:red"></p>
    </div>
  </div>
  
  <%
     WebApplicationContext context = (WebApplicationContext)this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
  	 User user = (User)context.getBean("user");
 	 Set<Mail> outbox = user.getOutbox();
 	 Mail eg = null;
  %>
  
  <!-- 分栏 -->
  <div class="container bs-docs-container">

    <div class="row">
      
      <!-- 导航栏 -->
      <div class="col-md-3">
        <div class="bs-sidebar hidden-print" data-offset-top="200" >
        	<h4>共有邮件<%=outbox.size() %>封</h4>
       		<ul class="nav bs-sidenav">       		
       		<%	for(Mail mail : outbox){
       				if(eg == null){
   						eg = mail;
   					}
           			%>
           				<li>
           					<a onclick="ajax(<%=mail.getId() %>)">
           						<span style='color:black'><%=mail.getReceiver() %></span>
           						<br/>
           						<span><%=mail.getDate() %></span>
           						<br/>
           						<span><%=mail.getTopic() %></span>
           					</a>
           				</li>
           			<%
           		}
           	%>
          	</ul>	
        </div>
      </div>
        
      <!-- 内容  -->
      <div class="col-md-9">
        <div id="display" class="bs-customizer" style="margin-bottom:100px;margin-top:30px">        
           <%
           		if(outbox.size() == 0){
           			out.print("<h1>无邮件</h1>");
           		}%>     				
           		<h1 id="topic"><%=eg==null?"":eg.getTopic() %></h1>
           		<br/>
           		<table>
           			<tr>
           				<td class="text-muted">发送时间</td>
           				<td id="time"><%=eg==null?"":eg.getDate() %></td>
           			</tr>
           			<tr>
           				<td class="text-muted">发件人</td>
           				<td id="addresser"><%=eg==null?"":eg.getAddresser() %></td>
           			</tr>
           			<tr>
           				<td class="text-muted">收件人</td>
           				<td id="receiver"><%=eg==null?"":eg.getReceiver() %></td>
           			</tr>
           			<tr>
           				<td class="text-muted">附件</td>
           				<td id="attach" style="width:350px;height:30px">       			
           				<%
           					if(eg != null){
           						Set<Attach> attachs = eg.getAttach();
           						if(attachs != null){
           							for(Attach a : attachs){
           						%>
           							
           							<a href="download?mailid=<%= eg.getId() %>&attachid=<%= a.getId()%>&box=outbox" ><%=a.getName()%></a><br/>
           							
           						<%
           							}
           						}
           					}
           				%> 
           				</td>
           			</tr>
           		</table>
           		<br/>
           		<textarea id="content" class="form-control" rows="10"><%=eg==null?"":eg.getContent() %></textarea>
           		  	
          <form action="delete" style="margin-top:30px">
          	<input name="box" value="outbox" type="hidden"/>
          	<input id="id" name="id" value="<%=eg==null?-1:eg.getId() %>" type="hidden"/>
          	<button type="submit" class="btn btn-default">删除</button>
          </form>
        </div>        
      </div>
      
    </div>
    
  </div>
  
<script type="text/javascript" src=<%=root+"/dist/js/prototype.js"%> ></script>
<script type="text/javascript">

	var int = self.setInterval("refresh()", 10000);

	function ajax(No){		
    	new Ajax.Request("getMail", {
    		method : 'get',
    		parameters : {id: No, box: 'outbox'},
    		onComplete : function(request){
    			params = request.responseText.split("$");
    			document.getElementById("topic").innerHTML = params[0];
    			document.getElementById("addresser").innerHTML = params[1];
    			document.getElementById("receiver").innerHTML = params[2];
    			document.getElementById("content").innerHTML = params[3];
    			document.getElementById("attach").innerHTML = params[4];
    			document.getElementById("time").innerHTML = params[5];
    			document.getElementById("id").value = params[6];
    			}
    	});
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
</script>
</body>  
</html>
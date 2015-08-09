<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page
	import="org.springframework.web.context.WebApplicationContext, models.*, java.util.Set"%>

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

<title>Web01邮箱-转储</title>

<link href=<%=root + "/dist/css/bootstrap.min.css"%> rel="stylesheet">
<!-- Documentation extras -->
<link href=<%=root + "/docs-assets/css/docs.css"%> rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script
	src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>

<style>
body {
	font-family: "ff-tisa-web-pro-1", "ff-tisa-web-pro-2", "Lucida Grande",
		"Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB",
		"Hiragino Sans GB W3", "WenQuanYi Micro Hei", sans-serif;
}

h1,.h1,h2,.h2,h3,.h3,h4,.h4,.lead {
	font-family: "ff-tisa-web-pro-1", "ff-tisa-web-pro-2", "Lucida Grande",
		"Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB",
		"Hiragino Sans GB W3", "Microsoft YaHei UI", "Microsoft YaHei",
		"WenQuanYi Micro Hei", sans-serif;
}

pre code {
	background: transparent;
}

@media ( min-width : 768px) {
	.bs-docs-home .bs-social,.bs-docs-home .bs-masthead-links {
		margin-left: 0;
	}
}

.bs-docs-section p {
	line-height: 2;
}

.bs-docs-section p.lead {
	line-height: 1.4;
}

td {
	width: 160px;
	height: 30px
}
</style>

<link rel="shortcut icon" href=<%=root + "/docs-assets/ico/icon.ico"%>>

</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav">

		<div class="container">

			<div class="navbar-header">
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target=".bs-navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<p class="navbar-brand">Web01邮箱</p>
			</div>

			<nav class="collapse navbar-collapse bs-navbar-collapse">
				<ul class="nav navbar-nav">
					<li><s:a action="inbox">收件箱</s:a></li>
					<li><s:a action="outbox">发件箱</s:a></li>
					<li><s:a action="unloading">转储</s:a></li>
					<li><s:a action="newMail">写邮件</s:a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><s:a action="logout">注销</s:a></li>
				</ul>

			</nav>

		</div>

	</header>
	<div class="bs-header">
		<div class="container">
			<h1>转储邮件</h1>			
		</div>
	</div>	

	<!-- 分栏 -->
	<div class="container bs-docs-container">

		<div class="row">

			<!-- 导航栏 -->
			<div class="col-md-3">
				<div class="bs-sidebar hidden-print" data-offset-top="200">
					<ul class="nav bs-sidenav">

						<li>
							<a href="#download">转储邮件</a>
						</li>
						<li>
							<a href="#upload">导入邮件</a>
						</li>
					</ul>
				</div>
			</div>

			<!-- 内容  -->
			<div class="col-md-9">
				<div id="display" class="bs-customizer"	style="margin-bottom: 100px; margin-top: 30px">
						<div class="bs-docs-section">
							<h1 id="download" class="page-header">转储邮件</h1>							
							<form action="storeall" method="post" style="margin-top: 30px" onsubmit="return verify()">
								<p id="reminder" style="color: red"></p>								
								<table>
									<tr>
										<td>
											<p id="reminder" style='color:red'></p>
										</td>										
									</tr>
									<tr>
										<td>
											<input type="text" name="starttime"
											class="form-control" style="width: 200px; height: 30px"
											placeholder="起始时间" ondblclick='ChkChange(this)'>
										</td>
										<td>
											<input type="text" name="endtime"
											class="form-control" style="width: 200px; height: 30px"
											placeholder="截止时间" ondblclick='ChkChange(this)'>
										</td>
										<td></td>
										<td>
											邮件范围：
											<select name="obj" >
												<option value="inbox" selected="selected">收件箱</option>
												<option value="outbox">发件箱</option>
												<option value="all">全部</option>
											</select>
										</td>
									</tr>									
								</table>								
								<br>
								<button type="submit" class="btn btn-default">转储</button>
							</form>
						</div>
							
						<div class="bs-docs-section">
							<h1 id="upload" class="page-header">导入邮件</h1>
							<form action="importmail" method="post"	enctype="multipart/form-data">
								<input class="btn btn-defualt" type="file" name="file"
									style="margin-bottom: 10px" />
								<button type="submit" style="margin-top: 5px;" class="btn btn-default">导入</button>
							</form>
						</div>

				</div>
			</div>

		</div>

	</div>

	<script type="text/javascript">
		function ChkChange(obj) {
			var date = new Date();
			var seperator1 = '-';
			var seperator2 = ':';
			var year = date.getYear() + 1900;
			var month = date.getMonth() + 1;
			var strDate = date.getDate();
			var hour = date.getHours();
			var min = date.getMinutes();
			var sec = date.getSeconds();
			if (month >= 1 && month <= 9) {
				month = '0' + month;
			}
			if (strDate >= 0 && strDate <= 9) {
				strDate = '0' + strDate;
			}
			if (hour >= 0 && hour <= 9) {
				hour = '0' + hour;
			}
			if (min >= 0 && min <= 9) {
				min = '0' + min;
			}
			if (sec >= 0 && sec <= 9) {
				sec = '0' + sec;
			}
			var currentdate = year + seperator1 + month + seperator1 + strDate
					+ ' ' + hour + seperator2 + min
					+ seperator2 + sec;
			obj.value = currentdate;
		};
		function verify(field){
	    	
	    	var s = document.getElementsByName("starttime")[0].value;
	    	var e = document.getElementsByName("endtime")[0].value;

	    	if( s == null || s == ""){
	    		document.getElementById("reminder").innerHTML = "起始时间不能为空!";
	    		alert(document.getElementsByName("starttime"));
	    		return false;
	    	}    	
	    	else if( !isTime(s)){
	    		document.getElementById("reminder").innerHTML = "起始时间格式应为 yyyy-MM-dd HH:CC:SS!";
	    		return false;
	    	}
	    	else if( e == null || e == ""){
	    		document.getElementById("reminder").innerHTML = "截止时间不能为空!";
	    		return false;
	    	}    	
	    	else if( !isTime(e)){
	    		document.getElementById("reminder").innerHTML = "截止时间格式应为 yyyy-MM-dd HH:CC:SS!";
	    		return false;
	    	}
	    	
	    	return true;
	    	
	    };
		function isTime(s){
	    	//时间格式
			var regexp = /^([1][7-9][0-9][0-9]|[2][0-9][0-9][0-9])(\-)([0][1-9]|[1][0-2])(\-)([0][1-9]|[1-2][0-9]|[3][0-1])(\s)([0-1][0-9]|[2][0-3])(:)([0-5][0-9])(:)([0-5][0-9])$/g;
	    	return regexp.test(s);
		};
	    window.onload=function(){
	        document.getElementById("image").onclick=function(){
	            this.src="securityCode?timestamp="+new Date().getTime();
	        };
	    };
	</script>

</body>
</html>
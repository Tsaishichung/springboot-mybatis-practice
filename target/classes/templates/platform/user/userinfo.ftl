<html>
<head>
  <title>个人中心</title>
<script type="text/javascript" src="/js/jquery-1.12.2.js"></script>
<link rel="stylesheet" href="/css/user.css">
</head>
<script type="text/javascript">
	function login(){
	var account = $("#account").val();
	var username = $("#username").val();
	var password = $("#password").val();
	var password1 = $("#password1").val();
	
		if(account.length==0){
			alert("账号未必填项，请确认后提交！");
			$("#account").focus();
			return false;
		}
		if(username.length==0){
			alert("用户名未必填项，请确认后提交！");
			$("#username").focus();
			return false;
		}
		if(password.length==0&&password1.length==0){
			alert("请输入您的密码");
			$("#password").focus();
			return false;
		}
		if(password!=password1){
			alert("两次输入的密码不一致，请确认后提交！");
			$("#password").focus();
			return false;
		}
		if(!isNaN($("#phone").val())){
			alert("电话号码为数字组成");
			return false;
		}
			return true;
	}
</script>
<body style="margin:0px;">
			<!-- 容器开始 -->
		<div class="container">
			<!-- 头部开始 -->
			<div class="header">
				<div class="logo"><img alt="会议论文平台" src="/img/logo.png"></div>
				<div class="header_operate_success">
					欢迎您！<#if Session.userSession?exists>${Session.userSession.username}</#if>,
					<a style="color: white" href="/">首页</a>&nbsp;
					<a style="color: white" href="/sys/platform/user/userCenter">个人中心</a>&nbsp;
					<a style="color: white" href="/sys/platform/logout">退出</a>
				</div>
			<hr>
			</div>
			<!-- 头部结束 -->
			<!-- 正文开始 -->
			<div class="content">
				<!-- 正文左部开始 -->
				<div class="content-left" style="color:white;text-align:center;">
					<br><br>
					 <a style="color: white" href="/sys/platform/user/userCenter">用户信息</a><br><br>
					 <a style="color: white" href="/sys/platform/user/getArticleListByUser/<#if Session.userSession?exists>${Session.userSession.id}</#if>">论文管理</a><br><br>
					 <a style="color: white" href="/sys/platform/user/getArticleListByUser/toUploadPage">论文上传</a><br><br>
					 <a style="color: white" href="">联系客服</a><br><br>
				</div>
				<!-- 正文左部结束 -->
				<!-- 正文右部开始 -->
				<div class="content-right">
				<#if flag?exists>
					<#if flag=="user">
					<div style="text-align:left;"><h2>用户信息中心：</h2></div>
					<hr>
						<form action="/sys/platform/user/updateUserInfo" method="post">
							<input type="hidden" name="id" value="<#if Session.userSession?exists>${Session.userSession.id}</#if>"/>
							账号：<#if Session.userSession?exists>${Session.userSession.account}</#if></br>
							用户名：<input type="text" name="username" id="username" value="<#if Session.userSession?exists>${Session.userSession.username}</#if>"></br>
							男：<input type="radio" name="sex" value="1" <#if Session.userSession?exists><#if Session.userSession.sex==1>checked="checked"</#if></#if> >&nbsp;女：<input type="radio" name="sex" value="2" <#if Session.userSession?exists><#if Session.userSession.sex==2>checked="checked"</#if></#if>></br>
							联系电话：<input type="text" name="phone" id="phone" value="<#if Session.userSession?exists>${Session.userSession.phone}</#if>"></br>
							账号创建时间：<#if Session.userSession?exists>${Session.userSession.createTime}</#if><br>
							<font color="red"><#if updateFlag?exists>${updateFlag}</#if></font></br>
							<input type="submit" value="更改信息">
						</form>
					</#if>
					<#if flag=="article">
					<div style="text-align:left;"><h2>论文管理：</h2>
					<hr>
					<!-- form表单提交条件查询结果，分页信息一并传送 -->
					 <form action="/sys/platform/user/getArticleListByUser/select" id="form" method="post">
					   <input type="hidden" name="pageNow" id="pageNow" value="${page.pageNow}"/>
					   <input type="hidden" name="query['id']" id="id" value="${Session.userSession.id}"/>
						论文状态：<select id="selectId" name="query['status']" onchange="toPage('6')">
						 <option value="null">---请选择---</option>
						 <option value="1">已发布</option>
						 <option value="2">审核未通过</option>
						 <option value="3">待审核</option>
						</select>
				      	<!--按关键字查询:<input type="text" name="query['article_name']" placeholder="按关键字查询" onchange="toPage('6')" value="<#if page.query['artitle_name']?exists>${page.query['artitle_name']}</#if>">-->
			      	 </from>
			      	 
				   </div>
					    <!-- 条件查询结束 -->
					    	
					    	<table class="content-table">
						    	<thead style="color:#800000;font-weight:bold;font-size: 20px;">
							    	<tr>
							    		<td style="width:45%;">文章名</td>
							    		<td>论文类型</td>
							    		<td>提交时间</td>
							    		<td>下载量</td>
							    		<td>阅读量</td>
							    		<td>状态</td>
							    	</tr>
						    	</thead>
						    	<tbody>
							    	<#list articleList as Article>
							    	<tr>
							    		<td class="content-td">${Article.article_name}</td>
							    		<td>${Article.type}</td>
							    		<td>${Article.create_time}</td>
							    		<td>${Article.download_count}</td>
							    		<td>${Article.view_count}</td>
							    		<td>
							    			<#if Article.is_used==1>
												<font color="green">已发布</font>
											<#elseif Article.is_used==2>
												<font color="red">审核未通过</font>
											<#elseif Article.is_used==3>
												<font color="orange">待审核</font>
											</#if>
										</td>
							    	</tr>
							    	</#list>
								</tbody>
					    	</table>
					    
					    
					        <div>共<i>${selectArticleCountByUser}</i>条记录，当前显示第&nbsp;<i>${page.pageNow}&nbsp;/&nbsp;${articlePageCount}</i>页</div>
					        <a href="javascript:void(0)" onclick="toPage('1')">首页</a>
					        <a href="javascript:void(0)" onclick="toPage('2')">上一页</a>
					        <a href="javascript:void(0)" onclick="toPage('3')">${page.pageNow}</a>
					        <a href="javascript:void(0)" onclick="toPage('4')">下一页</a>
					        <a href="javascript:void(0)" onclick="toPage('5')">尾页</a>
					    <input type="hidden" id="totalCount" value="${selectArticleCountByUser}">
					    <input type="hidden" name="total_count" id="total_count" value="${articlePageCount}"/>
					    <input type="hidden" name="arType" id="arType" value="<#if page.query['arType']?exists>${page.query['arType']}</#if>"/>
					</#if>
					<#if flag=="upload">
					<div style="text-align:left;"><h2>论文上传：</h2>
					<hr>
						 <form id="uploadFile_" action="/sys/platform/user/fileUpLoad" enctype="multipart/form-data" method="post" onsubmit="return upload()">
						 	论文标题：<input type="text" name="query['article_name']" id="article_name"><br>
	                   		<input type="hidden" name="query['id']" id="id" value="${Session.userSession.id}"/>
						 	选择上传文件：<input type="file" name="file" id="file">
				 	  论文类型：<select id="sb" name="query['type']">
							 <option>---请选择---</option>
							 <option>科研论文</option>
							 <option>测试论文</option>
							 <option>毕业论文</option>
							</select>
						 	<input type="submit" value="提交">
					 	</form>
					</#if>
					<#if flag=="cotactToManage">
					<div style="text-align:left;"><h2>联系客服：</h2></div>					
					<hr>
					邮箱：tshichung@gmail.com
					电话：157-XXXX-XXXX
					</#if>
				</#if>
				</div>
				<!-- 正文右部结束 -->
			</div>
			<!-- 正文结束 -->
			<!-- 底部开始 -->
			<div class="footer">
			<i>
			Copyright (c) 2016 <a style="color:white" href="http://tsaishichung.github.io">Tsaishichung</a>,
			<br>
			All Rights Reserved.
			</i>|友情链接：<a style="color:white" href="/tocust">长春理工大学官网</a>
			</div>
			<!-- 底部结束 -->
		</div>
		<!-- 容器结束 -->
</body>
<script type="text/javascript">
		//分页Jquery脚本
		function toPage(type){
			var totalCount = parseInt($("#totalCount").val());//记录数
			var total_count = parseInt($("#total_count").val());//分页数
			var pageNow = parseInt($("#pageNow").val());
			if(type=='6'){
		          $("#pageNow").val(1);
		          $("#form").submit();
			}else{
				switch(type){
				case '1':
					if(pageNow==1){
						alert("当前已经是第一页了！");
						return false;
					}else{
						$("#pageNow").val(1);
					}
					break;
				case '2':
					if(pageNow==1){
		                 alert("当前页面已是首页！");
		                 return false;
		             }else{
		                 $("#pageNow").val(pageNow-1);
		             }
		             break;
		         case '4':
		              if(pageNow==total_count){
		                  alert("当前页面已是尾页！");
		                  return false;
		              }else{
		                  $("#pageNow").val(pageNow+1);
		              }
		              break;
		          case '5':
		              if(pageNow==total_count){
		                  alert("当前页面已经是最后一页");
		                  return false;
		              }
		              else{
		                  $("#pageNow").val(total_count);
		              }
		              break;
		            default:
		                break;
				}
				$("#form").submit();
			}
		}
		function upload(){
			var file_type = $("#file").val().split(".")[$("#file").val().split(".").length-1];
			var article_type = $("#sb").val();
			var article_name = $("#article_name").val();
			if(article_name==""){
				alert("请确认您的论文标题！");
				return false;
			}
			if(file_type!='doc'){
				alert("当前只提供*.doc文件上传！");
				return false;
			}
			console.log(article_type);
			if(article_type=='---请选择---'){
				alert("请选择上传论文类型");
				return false;
			}
			return true;
		}
</script>
</html>
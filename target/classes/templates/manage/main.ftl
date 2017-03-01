<html>
<head>
  <title>后台管理登录</title>
<script type="text/javascript" src="/js/jquery-1.12.2.js"></script>
<link rel="stylesheet" href="/css/manage.css">
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
		function forbid(type){
		console.log(type);
			$.ajax({ 
		        type: "post", 
		        url: "/sys/manage/user/forbid", 
		        data:{userid:type},
		        cache:false, 
		        async:false, 
		        dataType: "text", 
		        success: function(data){ 
					location.reload();
					alert("禁用成功");
		        } 
			});
		}
		function resetPwd(type){
			$.ajax({ 
		        type: "post", 
		        url: "/sys/manage/user/resetpwd", 
		        data:{userid:type},
		        cache:false, 
		        async:false, 
		        dataType: "text", 
		        success: function(data){ 
					console.log(data);
					location.reload();
					alert("重置密码成功");
		        } 
			});
		}
		function setArticle(type,status){
			$.ajax({ 
		        type: "post", 
		        url: "/sys/manage/article/status", 
		        data:{id:type,status:status},
		        cache:false, 
		        async:false, 
		        dataType: "text", 
		        success: function(data){ 
					location.reload();
					alert("操作成功");
		        } 
			});
		}
</script>

</head>
<body style="margin:0px;">
	<!-- 容器样式开始 -->
	<div class="container">
		<!-- 头部开始 -->
			<div class="header">
			<div class="logo"><img alt="会议论文平台" src="/img/manageLogo.png"></div>
			<div class="header_operate_success">
					欢迎您！<#if Session.manageSession?exists>${Session.manageSession.username}</#if>,
					<a style="color: white" href="/sys/manage/logout">退出</a>
				</div>
			<hr>
			</div>
			<!-- 头部结束 -->
			<!-- 正文开始 -->
			<div class="content">
				<!-- 正文左部开始 -->
				<div class="content-left" style="color:#808080;text-align:center;">
					<br>
					<br>
					<br>
					<a style="color:#808080;" href="/sys/manage/toLogin">用户信息管理</a><br><br>
					<a style="color:#808080;" href="/sys/manage/article/manage">论文审批</a><br><br>
					<!--<a style="color:#808080;" href="/sys/manage/logout">论文类型管理</a><br><br>-->
				</div>
				<!-- 正文左部结束 -->
				<!-- 正文右部开始 -->
				<div class="content-right">
				<#if flag?exists>
					<#if flag=="user">
					<div style="text-align:left;"><h2>用户管理：</h2>
					<hr>
					<!-- form表单提交条件查询结果，分页信息一并传送 -->
					 <form action="/sys/manage/user/userinfo" id="form" method="post">
					   <input type="hidden" name="pageNow" id="pageNow" value="${page.pageNow}"/>
			      	 </from>
				   </div>
					    <!-- 条件查询结束 -->
					    	<table class="content-table">
						    	<thead style="color:#800000;font-weight:bold;font-size: 20px;">
							    	<tr>
							    		<td>账号</td>
							    		<td>用户名</td>
							    		<td>性别</td>
							    		<td>电话</td>
							    		<td>创建时间</td>
							    		<td>状态</td>
							    		<td>操作</td>
							    	</tr>
						    	</thead>
						    	<tbody>
							    	<#list userList as User>
							    	<tr>
							    		<td>${User.account}</td>
							    		<td>${User.username}</td>
							    		<td><#if User.sex==1>男<#else>女</#if></td>
							    		<td>${User.phone}</td>
							    		<td>${User.createTime}</td>
							    		<td><#if User.isUsed==1><font color="green">正常</font><#else><font color="red">禁用</font></#if></td>
							    		<td>
							    			<input type="button" onclick="forbid('${User.id}')" value="<#if User.isUsed==1>禁用<#else>启用</#if>" />
							    			<input type="button" onclick="resetPwd('${User.id}')" value="重置密码" />
							    		</td>
							    	</tr>
							    	</#list>
								</tbody>
					    	</table>
					        <div>共<i>${userCount}</i>条记录，当前显示第&nbsp;<i>${page.pageNow}&nbsp;/&nbsp;${page_count}</i>页</div>
					        <a href="javascript:void(0)" onclick="toPage('1')">首页</a>
					        <a href="javascript:void(0)" onclick="toPage('2')">上一页</a>
					        <a href="javascript:void(0)" onclick="toPage('3')">${page.pageNow}</a>
					        <a href="javascript:void(0)" onclick="toPage('4')">下一页</a>
					        <a href="javascript:void(0)" onclick="toPage('5')">尾页</a>
					    <input type="hidden" id="totalCount" value="${userCount}">
					    <input type="hidden" name="total_count" id="total_count" value="${page_count}"/>
					</#if>
					<#if flag=="article">
						<div style="text-align:left;"><h2>论文管理：</h2>
					<hr>
					<!-- form表单提交条件查询结果，分页信息一并传送 -->
					 <form action="/sys/manage/article/manage/do" id="form" method="post">
					   <input type="hidden" name="pageNow" id="pageNow" value="${page.pageNow}"/>
					论文状态：<select id="selectId" name="query['status']" onchange="toPage('6')">
						 <option value="null">---请选择---</option>
						 <option value="1">已发布</option>
						 <option value="2">审核未通过</option>
						 <option value="3">待审核</option>
						</select>
			      	 </from>
				   </div>
					    <!-- 条件查询结束 -->
					    	<table class="content-table">
						    	<thead style="color:#800000;font-weight:bold;font-size: 20px;">
							    	<tr>
							    		<td>文章名</td>
							    		<td>提交时间</td>
							    		<td>论文类型</td>
							    		<td>论文状态</td>
							    		<td>操作</td>
							    	</tr>
						    	</thead>
						    	<tbody>
							    	<#list articleList as Article>
							    	<tr>
							    		<td class="content-td">${Article.article_name}</td>
							    		<td>${Article.create_time}</td>
							    		<td>${Article.type}</td>
							    		<td><#if Article.is_used==1><font color="green">已发布</font><#elseif Article.is_used==2><font color="red">审核不通过</font><#else><font color="orange">待审核</font></td></#if>
							    		<td>
							    		<#if Article.is_used==1>
							    			<input type="button" onclick="setArticle('${Article.id}','2')" value="下架" />
							    		<#elseif Article.is_used==2>
							    			<input type="button" onclick="setArticle('${Article.id}','1')" value="通过" />
						    			<#else>
							    			<input type="button" onclick="setArticle('${Article.id}','1')" value="通过" />
							    			<input type="button" onclick="setArticle('${Article.id}','2')" value="不通过" />
										</#if>
							    		</td>
							    	</tr>
							    	</#list>
								</tbody>
					    	</table>
					        <div>共<i>${articleCount}</i>条记录，当前显示第&nbsp;<i>${page.pageNow}&nbsp;/&nbsp;${page_count}</i>页</div>
					        <a href="javascript:void(0)" onclick="toPage('1')">首页</a>
					        <a href="javascript:void(0)" onclick="toPage('2')">上一页</a>
					        <a href="javascript:void(0)" onclick="toPage('3')">${page.pageNow}</a>
					        <a href="javascript:void(0)" onclick="toPage('4')">下一页</a>
					        <a href="javascript:void(0)" onclick="toPage('5')">尾页</a>
					    <input type="hidden" id="totalCount" value="${articleCount}">
					    <input type="hidden" name="total_count" id="total_count" value="${page_count}"/>
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
	<!-- 容器样式结束 -->
</body>
</html>
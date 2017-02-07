<html>
<head>
  <title>会议论文平台</title>
</head>
<script type="text/javascript" src="/js/jquery-1.12.2.js"></script>
<link rel="stylesheet" href="/css/myCss.css">
<script type="text/javascript">
  //获取会议通知列表	
  $(function(){
	  $.ajax({ 
	        type: "post", 
	        url: "/sys/platform/getMeettingList", 
	        data:{limit:"yes"},
	        cache:false, 
	        async:false, 
	        dataType: "json", 
	        success: function(data){ 
				console.log(data);
				$.each(data,function(index, term){
				var title = data[index].title;
					$("#adviceTable").append("<tr><td class='td'><a style='color:blue' href='/sys/platform/viewing/meetting/${'"+data[index].id+"'}'>"+data[index].title+"</td><td style='color:blue'>"+data[index].meettingTime+"</td></tr>");
				});
	        } 

	});
  });
  //获取热门论文	
  $(function(){
	  $.ajax({ 
	        type: "post", 
	        url: "/sys/platform/getHotArticleList", 
	        data:{limit:"yes"},
	        cache:false, 
	        async:false, 
	        dataType: "json", 
	        success: function(data){ 
				console.log(data);
				$.each(data,function(index, term){
					$("#hotArticleTable").append("<tr><td class='td1'><a style='color:blue' href='/sys/platform/viewing/article/${'"+data[index].id+"'}'>"+data[index].article_name+"</td><td style='color:blue'>"+data[index].view_count+"</td><td style='color:blue'>"+data[index].download_count+"</td><td style='color:blue'><a href='/sys/platform/download/article/${'"+data[index].article_name+"'}/${'"+data[index].article_uuid_name+"'}/${'"+data[index].article_extend_name+"'}/${'"+data[index].id+"'}'>下载</a></td></tr>");
				});
	        } 

	});
  });
</script>
<body style="margin:0px;">
		<!-- 容器开始 -->
		<div class="container">
			<!-- 头部开始 -->
			<div class="header">
				<div class="logo"><img alt="会议论文平台" src="/img/logo.png"></div>
				<#if Session.userSession?exists>
				<div class="header_operate_success">
					欢迎您！${Session.userSession.username},
					<a style="color: white" href="/">首页</a>&nbsp;
					<a style="color: white" href="/sys/platform/user/userCenter">个人中心</a>&nbsp;
					<a style="color: white" href="/sys/platform/logout">退出</a>
				</div>
				<#else>
				<div class="header_operate_failed">
					<a style="color: white" href="/">首页</a>&nbsp;
					<a style="color: white" href="/sys/platform/login">登录</a>&nbsp;
					<a style="color: white" href="/sys/platform/register">注册</a>
				</div>
				</#if>
			<hr>
			</div>
			<!-- 头部结束 -->
			<!-- 正文开始 -->
			<div class="content">
				<!-- 正文左部开始 -->
				<div class="content-left">
					<!-- 正文左部上开始 -->
					<div class="content-left-round-first">
					<div style="text-align: left;">
						<h3>论文会议通知：</h3>
						会议概览&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始时间
						<hr>
					</div>
					  <table class="table" id="adviceTable">
					  </table>
					   <hr>
					   <div style="text-align:right;margin-right:20px;">
					   	<a style="color: white" href="#">查看更多</a>
					   </div>
					</div>
					<!-- 正文左部上结束 -->
					<!-- 正文左部下开始 -->
					<div class="content-left-round-second">
					  <div style="text-align: left;">
						<h4><a>最热文章列表</a></h4>
						点击查看&nbsp;&nbsp;&nbsp;&nbsp;阅读数&nbsp;下载数&nbsp;下载
						<hr>
						<table class="table" id="hotArticleTable">
						</table>
					</div>
						<hr>
					</div>
					<!-- 正文左部下结束 -->
				</div>
				<!-- 正文左部结束 -->
				<!-- 正文右部开始 -->
				<div class="content-right">
					<!-- 条件查询开始 -->
				   <div style="text-align:left;"><h2>论文列表：</h2>
					<hr>
					<!-- form表单提交条件查询结果，分页信息一并传送 -->
					 <form action="/sys/platform/article/getArticleListBySelect" id="form" method="post">
					   <input type="hidden" name="pageNow" id="pageNow" value="${page.pageNow}"/>
						论文类型：<select id="selectId" name="query['arType']" onchange="toPage('6')">
						 <option>---请选择---</option>
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
							    		<td>作者</td>
							    		<td>提交时间</td>
							    		<td>下载量</td>
							    		<td>阅读量</td>
							    		<td>下载</td>
							    	</tr>
						    	</thead>
						    	<tbody>
							    	<#list articleListBySelect as Article>
							    	<tr>
							    		<td class="content-td"><a href="/sys/platform/viewing/article/${Article.id}">${Article.article_name}</a></td>
							    		<td>${Article.type}</td>
							    		<td>${Article.author}</td>
							    		<td>${Article.create_time}</td>
							    		<td>${Article.download_count}</td>
							    		<td>${Article.view_count}</td>
							    		<td><a href="/sys/platform/download/article/${Article.article_name}/${Article.article_uuid_name}/${Article.article_extend_name}/${Article.id}">下载</a></td>
							    	</tr>
							    	</#list>
								</tbody>
					    	</table>
					    
					    
					        <div>共<i>${articleCountBySelect}</i>条记录，当前显示第&nbsp;<i>${page.pageNow}&nbsp;/&nbsp;${articlePageCountBySelect}</i>页</div>
					        <a href="javascript:void(0)" onclick="toPage('1')">首页</a>
					        <a href="javascript:void(0)" onclick="toPage('2')">上一页</a>
					        <a href="javascript:void(0)" onclick="toPage('3')">${page.pageNow}</a>
					        <a href="javascript:void(0)" onclick="toPage('4')">下一页</a>
					        <a href="javascript:void(0)" onclick="toPage('5')">尾页</a>
					    <input type="hidden" id="totalCount" value="${articleCountBySelect}">
					    <input type="hidden" name="total_count" id="total_count" value="${articlePageCountBySelect}"/>
					    <input type="hidden" name="arType" id="arType" value="<#if page.query['arType']?exists>${page.query['arType']}</#if>"/>
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
			<!-- 底部开始 -->
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
	//获取文章类型列表
	$(function(){
		$.ajax({ 
	        type: "post", 
	        url: "/sys/platform/getArticleTypeList", 
	        data:{limit:"yes"},
	        cache:false, 
	        async:false, 
	        dataType: "json", 
	        success: function(data){ 
				console.log(data);
				$.each(data,function(index, term){
				var select = $("#arType").val(); 
					if(select==data[index].arType){
						$("#selectId").append("<option value='"+data[index].arType+"' selected='selected'>"+data[index].arType+"</option>");
					}else{
						$("#selectId").append("<option value='"+data[index].arType+"'>"+data[index].arType+"</option>");
					}
				});
	        } 

	});
	})
</script>
</html>
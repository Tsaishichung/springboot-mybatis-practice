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
  //获取单个会议通告时间
  function showSingleMeetting(data){console.log(data)}
	
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
					$("#hotArticleTable").append("<tr><td class='td1'><a style='color:blue' href='/sys/platform/viewing/article/${'"+data[index].id+"'}'>"+data[index].article_name+"</td><td style='color:blue'>"+data[index].download_count+"</td><td style='color:blue'><a href='/sys/platform/download/article/${'"+data[index].article_name+"'}/${'"+data[index].article_uuid_name+"'}/${'"+data[index].article_extend_name+"'}/${'"+data[index].id+"'}'>下载</a></td></tr>");
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
					   	<a style="color: white" href="">查看更多</a>
					   </div>
					</div>
					<!-- 正文左部上结束 -->
					<!-- 正文左部下开始 -->
					<div class="content-left-round-second">
					  <div style="text-align: left;">
						<h4><a>按下载数排名</a>|<a>按阅读数排名</a></h4>
						点击查看&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;热度&nbsp;下载
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
				<div class="content-right-show">
					<#if meettinginfo?exists>
					<div style="text-align:center;">
					<h2>${meettinginfo.title}</h2>
					</div>
					<div style="text-align:right;">
					会议日期：${meettinginfo.meettingTime}
					</div>
					<hr>
					<div style="text-align:left;">
					${meettinginfo.content}
					</div>
					<#elseif articleinfo?exists>
					<div style="text-align:center;">
					<h2>${articleinfo.article.article_name}</h2>
					</div>
					<div style="text-align:right;">
					作者：${articleinfo.author} &nbsp;${articleinfo.article.create_time}
					</div>
					<hr>
					<div style="text-align:left;">
					${articleinfo.context}
					</div>
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
			<!-- 底部开始 -->
		</div>
		<!-- 容器结束 -->
</body>
</html>
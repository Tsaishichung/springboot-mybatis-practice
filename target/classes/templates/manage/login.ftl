<html>
<head>
  <title>后台管理登录</title>
<script type="text/javascript" src="/js/jquery-1.12.2.js"></script>
<script type="text/javascript">
	function login(){
	var account = $("#account").val();
	var password = $("#password").val();
	console.log(account+"=="+password);
		if(account.length==0){
			alert("请输入您的账号");
			$("#account").focus();
			return false;
		}if(password.length==0){
			alert("请输入您的密码");
			$("#password").focus();
			return false;
		}
			return true;
	}
</script>

</head>
<body style="margin:0px;background:black;color:orange;">
		</br>
		</br>
		</br>
		</br>
		<center>
					<h2>会议论文管理系统后台</h2>
			<form action="/sys/manage/toLogin" method="post" onsubmit="return login()"></br>
				账号：<input type="text" name="account" id="account" placeholder="请输入账号"></br>
				密码：<input type="password" name="password" id="password" placeholder="请输入密码"></br>	
			<input type="submit" value="登录">
			</form>
			<font color="red"><#if manageFail?exists>${manageFail}</#if></font><br>
			<font color="white">无关人士请速速离开，您的访问ip已被记录！</font>
			<br>
			<br>
			<i>
			Copyright (c) 1958 <a href="http://www.cust.edu.cn">长春理工大学官网</a>,
			<br>
			All Rights Reserved.
			</i>
		</center>
</body>
</html>
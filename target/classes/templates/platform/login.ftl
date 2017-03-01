<html>
<head>
  <title>用户登录</title>
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
<body>
		</br>
		</br>
		</br>
		</br>
		<center>
			<form action="/sys/platform/user/login" method="post" onsubmit="return login()"></br>
				账号：<input type="text" name="account" id="account" placeholder="请输入账号"></br>
				密码：<input type="password" name="password" id="password" placeholder="请输入密码"></br>	
			<input type="submit" value="登录">
			</form>
			<p><font color="red"><#if message?exists>${message}</#if></font></p>
			<p><font color="red"><#if registerinfo?exists>${registerinfo}</#if></font></p>
			<p><font color="red"><#if handler?exists>${handler}</#if></font></p>
			<a href="/"><——回首页</a>&nbsp;&nbsp;&nbsp;<a href="/sys/platform/register" target="showFrame">注册——></a>
		</center>
</body>
</html>
<html>
<head>
  <title>用户注册</title>
<script type="text/javascript" src="/js/jquery-1.12.2.js"></script>
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
		if(!isNaN($("#phone").val())&&$("#phone").val().length==11){
			alert("电话号码为11位数字组成");
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
		&nbsp;&nbsp;<h3>用户注册</h3>
			<form action="/sys/platform/user/register" method="post" onsubmit="return login()"></br>
				&nbsp;&nbsp;登录账号<font color="red">*</font>：<input type="text" name="account" id="account" placeholder="请设置您的登录账号" value=<#if account?exists>${account}</#if>><font ></font></br>
				&nbsp;&nbsp;&nbsp;用户名<font color="red">*</font>：<input type="text" name="username" id="username" placeholder="请设置您的用户名" <#if username?exists>${username}</#if>></br>
				&nbsp;&nbsp;登录密码<font color="red">*</font>：<input type="password" name="password" id="password" placeholder="请设置密码"></br>	
				再次输入密码<font color="red">*</font>：<input type="password" name="password1" id="password1" placeholder="请再次确认您的密码"></br>	
				男：<input type="radio" name="sex" value="1" checked="checked">&nbsp;女：<input type="radio" name="sex" value="2"></br>
				&nbsp;&nbsp;联系电话：<input type="text" name="phone" id="phone"></br>
				<font color="red">(*)为必填！</font></br>
				<a href="/"><——回首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/sys/platform/login">去登录——></a>
			</form>
			<font color="red"><#if message?exists>${message}</#if></font><br>
			<font color="red"><#if downloadNotAllow?exists>${downloadNotAllow}</#if></font><br>
		</center>
</body>
</html>
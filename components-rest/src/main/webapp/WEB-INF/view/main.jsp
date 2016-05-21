<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML >
<html>
	<head>

		<title>主页</title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="this is my page">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <style type="text/css">
          .demo{
           float: left;
          }
          
        </style>
	</head>
	<body>
	    <div style="text-align: right">
                <h2>${userinfo.username}&nbsp;<a href="#">修改密码</a>&nbsp;<a href="logout.do">退出</a></h2>
        </div>

        <div class="demo" style="width: 10%">
           <ul>
			<li>
				<h2>
					<a target="content3" href="userlist.do">用户管理</a>
				</h2>
			</li>
			<li>
				<h2>
					<a target="content3" href="rolelist.do">角色管理</a>
				</h2>
			</li>
			<li>
				<h2>
					<a target="content3" href="permissionlist.do">权限管理</a>
				</h2>
			</li>
			<li>
				<h2>
					<a target="content3" href="loglist.do">日志管理</a>
				</h2>
			</li>
			<li>
				<h2>
					<a target="content3" href="toUpload.do">上传文件</a>
				</h2>
			</li>
		</ul>
        </div>
    

        <div class="demo" style="margin-left: 2px;margin-top: 10px;width: 89%">
		   <iframe name="content3" id="content3" frameborder="0"  width="100%" height="900px" style="padding:0px;margin:0px"  ></iframe>
		</div>
</html>

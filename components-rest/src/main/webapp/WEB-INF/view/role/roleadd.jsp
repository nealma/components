<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>添加角色</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  
  <body>
     <form action="roleadd.do" method="post">
     角色ID:<input type="text" name="roleid"><br>
     角色名:<input type="checkbox"  name="rolename" value="1"/>角色名1<input type="checkbox"  name="rolename" value="1"/>角色名2<input type="checkbox"  name="rolename" value="1"/>角色名3<br>
     <input type="submit" value="submit"><br>
     </form>
  
   
  </body>
  
</html>

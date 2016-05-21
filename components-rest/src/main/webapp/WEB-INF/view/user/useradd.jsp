<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>添加用户</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  
  <body>
     <form action="useradd.do" method="post">
     用户ID:<input type="text" name="id"><br>
     用户名:<input type="text" name="username"><br>
     密码:<input type="password" name="password"><br>
     <input type="submit" value="submit"><br>
     </form>
  
   
  </body>
  
</html>

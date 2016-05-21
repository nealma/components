<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>用户列表</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  
  <body>
  
  <a href="toadd.do">添加用户</a>
  <table border="1">
     <tr>
       <td width="10%">ID</td>
       <td width="30%">用户名</td>
       <td width="30%">操作</td>
     </tr> 
    <c:forEach items="${userlist}" var="user"> 
     <tr>
       <td>${user.id} </td>
       <td>${user.username}</td>
       <td><a href="userdelete.do?id=${user.id}">删除</a></td>
     </tr>
    </c:forEach>
  </table>    
  </body>
  <script>
    function adduser(){
       window.location="/shirodemo/view/user/useradd.jsp";
    }
  </script>
  
</html>

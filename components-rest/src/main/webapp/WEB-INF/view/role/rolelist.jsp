<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>角色列表</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  
  <body>
  
  <a  href="toroleadd.do" >添加角色 </a>
  <table border="1">
     <tr>
       <td width="10%">ID</td>
       <td width="30%">角色名</td>
       <td width="30%">操作</td>
     </tr> 
    <c:forEach items="${rolelist}" var="role"> 
     <tr>
       <td>${role.roleid} </td>
       <td>${role.rolename}</td>
       <td><a href="">删除</a></td>
     </tr>
    </c:forEach>
  </table>    
  </body>
  
</html>

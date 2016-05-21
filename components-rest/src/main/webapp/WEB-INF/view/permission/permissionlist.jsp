<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>权限列表</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  
  <body>
  <table border="1">
     <tr>
       <td >ID</td>
       <td >权限名</td>
       <td >权限描述</td>
       <td >操作</td>
     </tr> 
    <c:forEach items="${perlist}" var="permission"> 
     <tr>
       <td>${permission.perid} </td>
       <td>${permission.pername}</td>
       <td>${permission.description}</td>
       <td><a href="#">删除</a></td>
     </tr>
    </c:forEach>
  </table>    
 </body>
</html>

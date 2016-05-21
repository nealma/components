<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>日志列表</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  
  <body>
  <table border="1">
     <tr>
       <td width="10%">ID</td>
       <td width="20%">操作</td>
       <td width="10%">用户名</td>
       <td width="10%">时间</td>
       <td width="25%">异常</td>
       <td width="25%">异常详细信息</td>
     </tr> 
    <c:forEach items="${loglist}" var="log"> 
     <tr>
       <td>${log.id} </td>
       <td>${log.description}</td>
       <td>${log.username}</td>
       <td>${log.createDate}</td>
       <td>${log.exceptionCode}</td>
       <td>${log.exceptionDetail}</td>
     </tr>
    </c:forEach>
  </table>    
 </body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page language="java"  import="java.util.ArrayList"%>
    <%@ page language="java"  import="model.bean.Lesson"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
ArrayList<Lesson> LessonArray = (ArrayList<Lesson>)request.getAttribute("lessons");
for(int i = 0;i< LessonArray.size();i++){
%>
<tr>
<td><%= LessonArray.get(i).getLessonID() %></td>
<td><%= LessonArray.get(i).getTopic() %></td>
<td><%= LessonArray.get(i).getLevel()%></td>
<td><%= %></td>
</tr>
<% } %>
</body>
</html>
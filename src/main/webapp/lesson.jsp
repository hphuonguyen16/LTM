<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.ArrayList" %>
<%@ page language="java" import="model.bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/lesson.css" />
</head>
<body>
	<jsp:include page="header.jsp" />
  <div class="container" style="padding-top: 100px">
<%
	ArrayList<Lesson> lessons = (ArrayList<Lesson>)request.getAttribute("lessons");
	for (int i = 0; i < lessons.size(); i++){
%>
	<a href="QuizServlet?action=getQuestion&lesson_id=<%=lessons.get(i).getLessonID()%>" class="card">
  <div class="card__img-container">
    <img class="card__img" src="https://images.pexels.com/photos/4144923/pexels-photo-4144923.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2" alt="unsplash random image" />
    <div class="card__tags">
      <span class="card__tag">Level <%=lessons.get(i).getLevel() %></span>
    </div>
  </div>
  <div class="card__body">
    <h3 class="card__title"><%= lessons.get(i).getTopic() %></h3>
    <p class="card__date">Updated on
      <time datetime="2022-10-24">Oct 24, 2022</time>
    </p>
    <p class="card__cta">Practice now!</p>
  </div>
</a>
 <%}%>
</div>
</body>
</html>
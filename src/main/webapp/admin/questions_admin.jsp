<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page language="java" import="java.util.ArrayList" %>
<%@ page language="java" import="model.bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/questions.css" />
    <script
      src="https://kit.fontawesome.com/120c850448.js"
      crossorigin="anonymous"
    ></script>
</head>
<body>
	<a class="button-add" href="<%=request.getContextPath()%>/QuizServlet?action=showAddForm&lesson_id=1">Add<i class="fa-solid fa-circle-plus"></i></a>
	<%
  	HashMap<Question,List<Choices>> allQuizes = (HashMap<Question, List<Choices>>)request.getAttribute("allQuizes");
	 Set<Question> setOfKeySet = allQuizes.keySet();
	 for(Question q : setOfKeySet){
	%>
	<div class="whole-container">
      <div class="question-container">
        <div class="question-element">
          <h3><%=q.getQuestion()%></h3>
          <a href="<%=request.getContextPath()%>/QuizServlet?action=deleteQuestion_admin&question_id=<%=q.getQuizID()%>&lesson_id=<%=q.getLessonID()%>"><i class="fa-regular fa-trash-can fa-lg" ></i></a>
        </div>
        <ul class="choice-container">
        <%for(Choices c : allQuizes.get(q)){ %>
          <li><%=c.getAnswer()%></li>
          <%} %>
        </ul>
      </div>
      <%}%>

     
    </div>

</body>
</html>
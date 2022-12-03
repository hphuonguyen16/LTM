<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.ArrayList" %>
<%@ page language="java" import="model.bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
      <form action="<%=request.getContextPath()%>/QuizServlet?action=addQuestion" method="post">
        
      <div class="topic-element">
      <label for="lesson">Lesson</label> 
      <select name="lesson" id="lessons">
      		<% ArrayList<Lesson> lessons = (ArrayList<Lesson>)request.getAttribute("lessons");
      		for (Lesson ls : lessons)  {
      		%>
          <option value=<%=ls.getLessonID() %>> <%=ls.getTopic() %> </option>
			<%} %>
</select>
      </div>
      
      <div class="question-element">
      <label for="question">Question</label> 
      <input type="text" name="question" id="question" />
      </div>
     
      <div class="choice-element">
         <label for="answer1">Choice 1</label>
      <input type="radio" id="answer1" name="answer" value="1">
      <input type="text" id="answer1" name="answer1" placeholder="Answer">
      <div>
  
    
      <div class="choice-element">
          <label for="answer2">Choice 2</label>
      <input type="radio" id="answer2" name="answer" value="2">
      <input type="text" id="answer2" name="answer2" placeholder="Answer">
      <div>
       <div class="choice-element">
      <label for="answer3">Choice 3</label>
      <input type="radio" id="answer3" name="answer" value="3">
      <input type="text" id="answer3" name="answer3" placeholder="Answer">
      </div>
      <div class="choice-element">
      <label for="answer4">Choice 4</label>
      <input type="radio" id="answer4" name="answer" value="4">
      <input type="text" id="answer4" name="answer4" placeholder="Answer">
      </div>
      <input type="submit" value="Submit">
      </form>
    </div>

</body>
</html>
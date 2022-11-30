<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.ArrayList" %>
<%@ page language="java" import="model.bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/quiz.css" />
<script src="https://kit.fontawesome.com/120c850448.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/jsfiles/quiz.js"></script>
<script type="text/javascript">
	function checkAnswer(){
		var correct = document.getElementById("correct"); // using VAR
		  correct.style.background ='#ccffcc';
		  var elms = document.querySelectorAll("[id='wrong']");
		  for(var i = 0; i < elms.length; i++) 
			 elms[i].style.background ='#ffb9b9'; // <-- whatever you need to do here.
		
	}
</script>
<style>
	ul li{
		padding-top:10px;
		padding-bottom:15px;
	}
</style>
</head>
<body>
<%
	Question question = (Question)request.getAttribute("question");
	Choices correctChoice = (Choices)request.getAttribute("correctChoice");
	int next_ques = (Integer)request.getAttribute("next_ques");
	int num_ques = (Integer)request.getAttribute("num_ques");
%>
<div class="quize">
  <div class="question-bar">
    <h2 class="qestion">Question <%=num_ques%>: <%= question.getQuestion() %></h2>
    <ul>
    <%
	ArrayList<Choices> choices = (ArrayList<Choices>)request.getAttribute("choices");
    String classname;
	for (int i = 0; i < choices.size(); i++){
		if(choices.get(i).isCorrect()){
			classname = "correct";
		}
		else{
			classname = "wrong";
		}
	%>
      <li class="ansCorrect" id=<%= classname%>>
        <input type="radio" name=<%=choices.get(i).getQuestionID()%> id=<%= choices.get(i).getChoiceID() %>  class="ansList">
		<label for=<%= choices.get(i).getChoiceID() %> class="ansa"><%= choices.get(i).getAnswer() %></label>
      </li>
      <%}%>
    </ul>
    <div class="footer-button">
      <button id="submit" class="submitBtn" onclick="checkAnswer()">Check</button>
    <div class="next_previous_btn">
    	<a href="QuizServlet?action=getQuestion&next_ques=<%=next_ques-2%>&lesson_id=<%=question.getLessonID()%>"><i class="fa-solid fa-chevron-left" id="icon-1"></i></a>
        <a href="QuizServlet?action=getQuestion&next_ques=<%=next_ques%>&lesson_id=<%=question.getLessonID()%>"><i class="fa-solid fa-chevron-right" id="icon-2"></i></a>
    </div>
    </div>
  </div>
		<div id="showscore" class="scoreArea"></div>
</div>

</body>
</html>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
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
		  correct.style.color ='#1cc61c';
		  var elms = document.querySelectorAll("[id='wrong']");
		  for(var i = 0; i < elms.length; i++) 
			 elms[i].style.color ='red'; // <-- whatever you need to do here.
	}
</script>
<style>

</style>
</head>
<body>
<div id="quize">
  <div class="question-bar">
  	<%
  	ArrayList<Question> questions = (ArrayList<Question>)request.getAttribute("questions");
  	 ArrayList<Choices> choices = (ArrayList<Choices>)request.getAttribute("choices");
	%>
	<script>
		let temp =0;
		let questions =[];
		let choices = [];
		let question_temp=[];
		let choice=[];
		<%
			for(int i=0; i<questions.size(); i++){	
		%>
			 question_temp = {
					"quizID": <%=questions.get(i).getQuizID() %>,
					"question": "<%=questions.get(i).getQuestion() %>",
					"lessonID": <%=questions.get(i).getLessonID() %>
			}
			questions.push(question_temp);
		
		<%}%>
		<%
		for(int i=0; i<choices.size(); i++){	
		%>
			 choice = {
				"choiceID": <%=choices.get(i).getChoiceID() %>,
				"answer": "<%=choices.get(i).getAnswer() %>",
				"correct": <%=choices.get(i).isCorrect() %>,
				"questionID": <%=choices.get(i).getQuestionID() %>
				
		}
		choices.push(choice);
	
		<%}%>
		function clickRight(){
			if (temp<questions.length-1){
			var list = document.getElementById('container');
			    while (list.firstChild) {
			    	list.removeChild(list.firstChild);
			    }
			temp++;
			console.log(temp);
			var a = temp + 1;
			document.getElementById("qestion").innerHTML = "Question "+a+": " +questions[temp].question;
			const choices_temp = choices.filter(choice => choice.questionID == questions[temp].quizID );
			for (i=0; i<choices_temp.length;i++){
			let classname ="";
			if (choices_temp[i].correct){
				classname = "correct";
			}
			else {
				classname="wrong";
			}

			 var radiobox = document.createElement('input');

			    radiobox.type = 'radio';
			    radiobox.id = choices_temp[i].choiceID;
			    radiobox.name = choices_temp[i].questionID;
			
			    var label = document.createElement('label')
			    label.htmlFor = choices_temp[i].choiceID;
			    label.className = "ansa";
			    label.innerText = choices_temp[i].answer;
			 
			    var li_container = document.createElement('li');
			   	
			    li_container.className = "ansCorrect";
			    li_container.id = classname;
			    
			    var container = document.getElementById('container');
			    li_container.appendChild(radiobox);
			    li_container.appendChild(label);
			    container.appendChild(li_container);  
			}
		}
		}
		function clickLeft(){
			temp--;
			console.log(temp);
			if (temp>=0){
			var list = document.getElementById('container');
			    while (list.firstChild) {
			    	list.removeChild(list.firstChild);
			    }
			var a = temp+1;
			document.getElementById("qestion").innerHTML = "Question "+a+": " +questions[temp].question;
			const choices_temp = choices.filter(choice => choice.questionID == questions[temp].quizID );
			for (i=0; i<choices_temp.length;i++){
			let classname ="";
			if (choices_temp[i].correct){
				classname = "correct";
			}
			else {
				classname="wrong";
			}

			 var radiobox = document.createElement('input');

			    radiobox.type = 'radio';
			    radiobox.id = choices_temp[i].choiceID;
			    radiobox.value = 'email';
			    radiobox.name = choices_temp[i].questionID
			
			    var label = document.createElement('label')
			    label.htmlFor = choices_temp[i].choiceID;
			    label.className = "ansa";
			    label.innerText = choices_temp[i].answer;
			 
			    var li_container = document.createElement('li');
			   	
			    li_container.className = "ansCorrect";
			    li_container.id = classname;
			    
			    var container = document.getElementById('container');

			    li_container.appendChild(radiobox);
			    li_container.appendChild(label);
			    container.appendChild(li_container);
			}
			
		}
		}
	</script>
    <h2 id="qestion">Question 1:<%=questions.get(0).getQuestion() %> </h2>
    <ul id="container" class="backOutRight">
      	<%
  	 	String classname ="";
  	 	for (int i=0; i<choices.size();i++){
  	 		if (choices.get(i).getQuestionID()==questions.get(0).getQuizID()){
  	 		if (choices.get(i).isCorrect()){
  	 			classname = "correct";
  	 		}
  	 		else{
  	 			classname="wrong";
  	 		}
	%>
      <li class="ansCorrect" id=<%=classname%>>
        <input type="radio" name=<%=choices.get(i).getQuestionID()%> id=<%= choices.get(i).getChoiceID() %>  class="ansList">
		<label for=<%= choices.get(i).getChoiceID() %> class="ansa"><%= choices.get(i).getAnswer() %></label>
      </li>
      <%} }%>
    </ul>
    <div class="footer-button">
      <button id="submit" class="submitBtn" onclick="checkAnswer()">Check</button>
    <div class="next_previous_btn">
    	<i class="fa-solid fa-chevron-left" id="icon-1" onclick="clickLeft()"></i>
        <i class="fa-solid fa-chevron-right" id="icon-2" onclick="clickRight()"></i>
    </div>
  </div>
		<div id="showscore" class="scoreArea"></div>
</div>

</body>
</html>
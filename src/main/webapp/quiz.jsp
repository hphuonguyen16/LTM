<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/quiz.css" />
<script src="https://kit.fontawesome.com/120c850448.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="quize">
  <div class="question-bar">
    <h2 class="qestion">1) Which type of JavaScript language is ___</h2>
    <ul>
      <li>
        <input type="radio" name="option" id="a" class="ansList">
        <label for="a" class="ansa">Object-Oriented</label>
      </li>
      <li>
        <input type="radio" name="option" id="b" class="ansList">
        <label for="b" class="ansb">Object-Based</label>
      </li>
      <li>
        <input type="radio" name="option" id="c" class="ansList">
        <label for="c" class="ansc">Assembly-language</label>
      </li>
      <li>
        <input type="radio" name="option" id="d" class="ansList">
        <label for="d" class="ansd">High-level</label>
      </li>

    </ul>
    <div class="footer-button">
      <button id="submit" class="submitBtn">Check</button>
    <div class="next_previous_btn">
    	<i class="fa-solid fa-chevron-left" id="icon-1"></i>
        <i class="fa-solid fa-chevron-right" id="icon-2"></i>
    </div>
    </div>
  </div>
  <div id="showscore" class="scoreArea"></div>
</div>
</body>
</html>
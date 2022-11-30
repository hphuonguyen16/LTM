<!DOCTYPE html>
<%@page import="model.bean.Flashcard"%>
<%@page import="java.util.ArrayList"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flashcard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flashcard.css">
</head>

<body>
    <!-- Flashcard contains a front (w a word in English & an image associated w/ it) & a back (w the meaning of the word) -->
    <div class="container">
        <img class="swipe-left" src="${pageContext.request.contextPath}/image/swipe-left.png"
            onclick="clickLeft()" />
            <div style="width:500px;"></div>
        <% ArrayList<Flashcard> flashcards = (ArrayList<Flashcard>)request.getAttribute("listFlashcard");
        	for(int i = 0; i < flashcards.size(); i++) {
        %>
        <script>let i = 0;
    		function clickRight(){
    			console.log(i);
    			if(i < <%= flashcards.size()-1 %>){
    			document.getElementsByClassName('flashcard')[i].classList.remove('appear-left');
    			document.getElementsByClassName('flashcard')[i].classList.remove('hide-card');
    			document.getElementsByClassName('flashcard')[i++].classList.add('disappear-left');
    			document.getElementsByClassName('flashcard')[i].classList.add('appear-left');}
    		}
    		function clickLeft(){
    			console.log(i);
    			document.getElementsByClassName('flashcard')[i + 1].classList.remove('appear-left');
    			document.getElementsByClassName('flashcard')[i + 1].classList.add('hide-card');
    			document.getElementsByClassName('flashcard')[i--].classList.remove('disappear-left');
    		}
    	</script>
	        <div class="flashcard <%= i==0?"":"hide-card" %>" style="z-index: -<%= i %>">
	            <div class="front">
	                <div class="card-container">
	                    <div class="img-container">
	                        <img src="data:image/png;base64, <%= flashcards.get(i).getImage() %>" alt="">
	                    </div>
	                </div>
	                <div class="word"><%= flashcards.get(i).getWord()%></div>
	            </div>
	            <div class="back">
	                <div class="card-container">
	                    <div class="word-type"><%= flashcards.get(i).getWord_type()%></div>
	                    <div class="meaning"><%= flashcards.get(i).getMeaning()%></div>
	                </div>
	            </div>
	        </div>
	    <% } %>
        <img class="swipe-right" src="${pageContext.request.contextPath}/image/swipe-left.png"
            onclick="clickRight()"/>
    </div>
</body>

</html>
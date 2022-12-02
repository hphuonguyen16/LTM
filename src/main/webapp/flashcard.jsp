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
	    <div class="cards-slider">
	        <img class="swipe-left" style="opacity: 0" src="${pageContext.request.contextPath}/image/swipe-left.png"
	            onclick="clickLeft()" />
	            <div style="width:500px;"></div>
	        <% ArrayList<Flashcard> flashcards = (ArrayList<Flashcard>)request.getAttribute("listFlashcard");
	        	for(int i = 0; i < flashcards.size(); i++) {
	        %>
	        <script>
	        	let i = 0, n = <%= flashcards.size()-1 %>;
	    		function clickRight(){
	    			if(i < n){
	    			document.getElementsByClassName('flashcard')[i].classList.remove('appear-left');
	    			document.getElementsByClassName('flashcard')[i].classList.remove('hide-card');
	    			document.getElementsByClassName('flashcard')[i++].classList.add('disappear-left');
	    			document.getElementsByClassName('flashcard')[i].classList.add('appear-left');}
	    			if(i == n)
		        		document.getElementsByClassName('swipe-right')[0].style.opacity = 0;
	    			if(i != 0)
	    				document.getElementsByClassName('swipe-left')[0].style.opacity = 1;
	    		}
	    		function clickLeft(){
	    			if(i > 0){
	    			document.getElementsByClassName('flashcard')[i].classList.remove('appear-left');
	    			document.getElementsByClassName('flashcard')[i--].classList.add('hide-card');
	    			document.getElementsByClassName('flashcard')[i].classList.remove('disappear-left');}
	    			if(i == 0)
	            		document.getElementsByClassName('swipe-left')[0].style.opacity = 0;
	    			else 
	    				document.getElementsByClassName('swipe-left')[0].style.opacity = 1;
	    			if(i != n)
	    				document.getElementsByClassName('swipe-right')[0].style.opacity = 1;
	    		}
	    		function openInventory(){
	    			document.getElementsByClassName('blur-background')[0].classList.toggle('showInventory');
	    			document.getElementsByClassName('my-cards')[0].classList.toggle('showInventory');
	    		}
	    	</script>
		        <div class="flashcard <%= i==0?"":"hide-card" %>" style="z-index: <%= 99 - i %>">
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
		                    <textarea class="meaning"><%= flashcards.get(i).getMeaning()%></textarea>
		                </div>
		            </div>
		        </div>
		    <% } %>
	        <img class="swipe-right" src="${pageContext.request.contextPath}/image/swipe-right.png"
	            onclick="clickRight()"/>
	    </div>
		<div class="add-section">
			<div style="z-index: -999; position: absolute; right: 31%; bottom: 120px; font-size: 23pt; text-align: center; font-family: 'Racing Sans One', cursive; transform: rotateZ(7deg)">Create your <br> own
			<span style="color: #b3572d; font-size: 28pt; margin: 0 5px; text-shadow: 2px 2px 0 #fbd37b, 5px 6px 0 #284b59;">flashcard</span> !</div>
			<img class="dash-arrow" alt="" src="${pageContext.request.contextPath}/image/dash-arrow.png">
			<a class="add-button" href="customFlashcard.jsp?action=add">+</a>
		</div>
	    <div class="inventory">
	    	<div class="image" onclick="openInventory()"></div>
	    	<img class="bubble inventory-pop" alt="" src="${pageContext.request.contextPath}/image/speech-bubble-2.png">
	    	<div class="text inventory-pop">your <br> cards </div>
	    </div>
	    <div class="blur-background"></div>
	    <div class="my-cards">
	    	<div class="close" onclick="openInventory()">&#10060;&#xfe0e;</div>
	    	<div class="scroll-pane">
	    	<% ArrayList<Flashcard> mycards = (ArrayList<Flashcard>)request.getAttribute("listFlashcard");
	        	for(int i = 0; i < mycards.size(); i++) {
	        %>
	        	<div class="my-card">
	        		<img class="pic" src="data:image/png;base64, <%= mycards.get(i).getImage() %>" alt="">
	        		<div class="content">
	        			<div class="word"><%= mycards.get(i).getWord()%></div>
	        			<div class="word-type"><%= mycards.get(i).getWord_type()%></div>
	        			<div class="meaning"><%= mycards.get(i).getMeaning()%></div>
	        		</div>
	        		<div class="options">
	        			<a href="<%=request.getContextPath()%>/flashcard?action=showCard&flashcardID=<%= mycards.get(i).getFlashcardID() %>" class="edit"></a>
	        			<a href="<%=request.getContextPath()%>/flashcard?action=deleteFlashcard&flashcardID=<%= mycards.get(i).getFlashcardID() %>" class="delete"></a>
<%-- 	        			<img class="edit" alt="" src="${pageContext.request.contextPath}/image/edit-icon.png"> --%>
<%-- 	        			<img class="delete" alt="" src="${pageContext.request.contextPath}/image/delete-icon.png"> --%>
	        			
	        		</div>
	        	</div>
	        <% } %>
	    	</div>
	    </div>
	</div>
</body>

</html>
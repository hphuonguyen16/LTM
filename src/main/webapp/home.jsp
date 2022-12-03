<!DOCTYPE html>
<%@page import="model.bean.*"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ELW</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <script type="text/javascript">
    </script>
</head>

<% 
	String fullname = (String)session.getAttribute("fullname");
	int userID = session.getAttribute("userID") != null ? Integer.valueOf(session.getAttribute("userID").toString()) : 0;
	if(session.getAttribute("role") != null && ((String)session.getAttribute("role")).equals("admin")){
		fullname = null;
		userID = 0;
	}
%>

<body id="wrapper">
    <div class="header">
        <div class="menu">
            <div class="left-menu">
                <a href="home.jsp" class="item">Home</a>
                <a href="<%=request.getContextPath()%>/flashcard?action=getRandomizedFlashcards&userID=<%= userID %>" class="item">Flashcard</a>
                <a href="LessonServlet" class="item">Quiz</a>
                <a href="<%= fullname != null ? request.getContextPath() + "/point?action=getAllPointsByUserID&userID=" + userID  : "login.jsp" %>" class="item">Points</a>
            </div>
            <div class="right-menu others">
                <%
                if(fullname != null)
            	{
            		%>
            		<h3 style="font-family: 'Poppins', sans-serif; position: absolute; top: 15px; right: 30px;">Welcome <%= fullname %></h3>
            		<a href="<%=request.getContextPath()%>/UserController?action=LogOut"  class="item" style="position: absolute; top: 40px; right: 30px; padding: 0" >Logout</a>
            		<%
            		}
            	else {
            		%>
                		<a href="login.jsp" class="item">Login</a>
                	<% 
            		}%>
            </div>
        </div>
        <div class="name others"><span style="color: #b3572d; margin-left: 60px;">This</span> is an <br> <span style="text-shadow: 3px 4px 0 #f8c365,
        6px 8px 0 #284b59;"> <span style="font-size: 70pt;">English</span>
                <span style="font-size: 54pt;">Learning</span></span> <br> <span style="margin-left: 280px">website
                !</span> </div>
        <!-- <img class="paperplane" src="../image/paperplane.png" alt=""> -->
        <div class="quote others"> <span class="quotation">"</span> Lorem ipsum dolor sit amet consectetur adipisicing
            elit. Quia a eius laborum illum, voluptatum porro necessitatibus aliquid saepe error, officiis commodi
            fugiat iste at quod! <span class="quotation">"</span>
        </div>
        <button class="right-part"></button>
        <img class="image" src="${pageContext.request.contextPath}/image/home-illustration.png" alt="">
        <a href="<%= fullname != null ? request.getContextPath() + "/flashcard?action=getRandomizedFlashcards&userID=" + userID : "login.jsp" %>" class="home-button others">Get started!</a>
        <!-- <div class="decorations orange">
            <img src="../image/zigzag.jpg" alt="">
        </div> -->
        <!-- <div class="right-part"></div> -->
    </div>
    <div class="section-1">
        <div class="img-container">
            <img src="" alt="">
        </div>
        <div class="brief">
            <div class="title"></div>
            <div class="content"></div>
        </div>
    </div>
    <div class="section-2"></div>
    <script>
        var elems = document.getElementsByClassName('others');
        var items = document.getElementsByClassName('item');
        for (let i = 0; i < items.length; i++)
            if (window.location.href.indexOf(items[i].innerHTML.toLowerCase()) > -1) {
                items[i].setAttribute('style', 'text-decoration: underline');
                break;
            }
        window.addEventListener('scroll', () => {
            if (window.pageYOffset > 840) {
                document.getElementsByClassName('header')[0].classList.add('header-shrink');
                document.getElementsByClassName('right-part')[0].classList.add('login-shrink');
                document.getElementsByClassName('image')[0].classList.add('image-shrink');
                document.getElementsByClassName('right-part')[0].innerHTML = "<%= fullname != null ? "Logout" : "Login" %>";
                for (let i = 0; i < elems.length; i++)
                    elems[i].classList.add('shrink');
                document.getElementsByClassName('section-1')[0].classList.add('section-shrink');
            }
            else {
                document.getElementsByClassName('header')[0].classList.remove('header-shrink');
                document.getElementsByClassName('right-part')[0].classList.remove('login-shrink');
                document.getElementsByClassName('image')[0].classList.remove('image-shrink');
                document.getElementsByClassName('right-part')[0].innerHTML = "";
                for (let i = 0; i < elems.length; i++)
                    elems[i].classList.remove('shrink');
                document.getElementsByClassName('section-1')[0].classList.remove('section-shrink');
            }
        })
    </script>
</body>
</html>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
</head>

<body id="wrapper">
    <div class="header">
        <div class="menu">
            <div class="left-menu">
                <a href="home.jsp" class="item">Home</a>
                <a href="<%=request.getContextPath()%>/flashcard?action=getAllFlashcards" class="item">Flashcard</a>
                <a href="quiz.jsp" class="item">Quiz</a>
                <a class="item">Points</a>
            </div>
            <div class="right-menu others">
                <div class="item">Login</div>
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
        <button class="home-button others">Get started!</button>
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
                document.getElementsByClassName('right-part')[0].innerHTML = "Login";
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
<!DOCTYPE html>
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
        <div class="flashcard">
            <div class="front">
                <div class="card-container">
                    <div class="img-container">
                        <img src="../image/ADHD.jpg" alt="">
                    </div>

                </div>
                <div class="word">ADHD</div>
            </div>
            <div class="back">
                <div class="card-container">
                    <div class="word-type">danh tu</div>
                    <div class="meaning">Chung roi loan tang dong giam chu y</div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
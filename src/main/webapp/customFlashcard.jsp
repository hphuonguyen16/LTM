<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Custom Flashcard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newFlashcard.css">
</head>

<body>
    <div class="custom-flashcard">
        <div class="title">Create your own flashcard!
        </div>
        <form class="flashcard-form" action="<%=request.getContextPath()%>/flashcard?action=addNewFlashcard" method="post" enctype="multipart/form-data">
        	<div class="front">
	            <input type="file" class="image-upload" name="image" />
	            <input type="text" name="word" class="word" placeholder="Word"  required="required" />
            </div>
            <div class="back">
	            <input type="text" name="word_type" class="word-type" placeholder="Word type"  required="required" />
	            <textarea type="text" name="meaning" class="meaning" placeholder="Word's meaning"  required="required"></textarea>
	            <button class="submit-button" type="submit">
	                <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-check"
	                    viewBox="0 0 16 16">
	                    <path
	                        d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z" />
	                </svg>
	            </button>
            </div>
        </form>
    </div>
</body>

</html>
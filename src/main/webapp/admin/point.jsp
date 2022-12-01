<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Point - admin</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <b>Upload | <a href="view.jsp">View</a></b><br/><br/>
        <form action="<%=request.getContextPath()%>/flashcard?action=addNewPoint" method="post">
        	userID:  <input type="text" name="userID" required="required"/><br/><br/>
        	lessonID: <input type="text" name="lessonID" required="required"/><br/><br/>
        	points: <input type="text" name="points" required="required"/><br/><br/>
            <input type="submit"/>
        </form>
    </body>
</html>
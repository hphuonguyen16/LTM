<!DOCTYPE html>
<%@page import="model.bean.Point"%>
<%@page import="java.util.ArrayList"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/point.css">
</head>

<body>
    <div class="container">
        <div class="table">
        <% ArrayList<Point> points = (ArrayList<Point>) request.getAttribute("listPoint");
	    %>
            <div class="main-column">
                <div class="option-title" style="height: 42px;">
                     
                </div>
                <div class="options topic-options">
                <% for(int i = 0; i < points.size(); i++) { %>
                    <div class="option">
                        <%= points.get(i).getLessonID() %>
                    </div>
                <% } %>
                </div>
                
            </div>
            <div class="filled">
            <div class="column">
                <div class="option-title">
                    points
                </div>
                <div class="options">
                <% for(int i = 0; i < points.size(); i++) { %>
                    <div class="option">
                        <%= points.get(i).getPoints() %>
                    </div>
                <% } %>
                </div>
            </div>
            <div class="column">
                <div class="option-title">
                    ratio
                </div>
                <div class="options">
                <% for(int i = 0; i < points.size(); i++) { %>
                    <div class="option">
                        <%= points.get(i).getPoints() %>%
                    </div>
                <% } %>
                </div>
            </div></div>
        </div>
    </div>
</body>

</html>
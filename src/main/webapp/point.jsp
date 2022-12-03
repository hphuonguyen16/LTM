<!DOCTYPE html>
<%@page import="model.bean.Point_Lesson"%>
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
	<jsp:include page="header.jsp" />
    <div class="container">
    	<div class="table-header"></div>
        <div class="table">
        
        <% ArrayList<Point_Lesson> points = (ArrayList<Point_Lesson>) request.getAttribute("listPoint");
	    %>
            <div class="main-column column">
                <div class="option-title" style="height: 42px;">
                     
                </div>
                <div class="options topic-options">
                <% for(int i = 0; i < points.size(); i++) { %>
                    <div class="option">
                        <%= points.get(i).getTopic() + " lvl. " + points.get(i).getLevel() %>
                    </div>
                <% } %>
                </div>
                
            </div>
            <div class="filled"></div>
	            <div class="column1">
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
	            <div class="column2">
	                <div class="option-title">
	                    correct rate
	                </div>
	                <div class="options">
	                <% for(int i = 0; i < points.size(); i++) { %>
	                    <div class="option">
	                        <%= points.get(i).getPoints() / points.get(i).getNum_question() * 100 %>%
	                    </div>
	                <% } %>
	                </div>
	            </div>
            
        </div>
    </div>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page language="java"  import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/C_Account?action=ChangePassword" method="post">
	<h1>Welcome	</h1>
	<% String username = (String)session.getAttribute("name");
	if(session != null)
	{
		out.print("Chao ban "+username);}
		else {
			out.print("Chao khach");
		}
	%>
	<br>
	Username: <input type="text" name="username" value= <%=username %> readonly/>
	<br>
	Current Password: <input type="password" name ="current_password"/>
	<br>
	Confirm Password: <input type="password" name ="confirm_password"/>
	<br>
	<input type = "submit" value="Login"/>
</form>
</body>
</html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checklist Questions</title>
<link rel="stylesheet" href="resources/static/checklist.css">

</head>
<body>

	<div class="topnav">
          <a href="/home">Home</a>
          <a href="/logout">Logout</a>
      </div>
	
	<div id="checklist">
	
		<h1><center>Audit Checklist Questions</center></h1><br>
        <form method="post" action="/auditQuestionsRes" >
        
        
        
        
        <c:forEach var="temp" items="${message }">
            
            
            
            <label>${temp.value}</label>
            <br><br>
            <input  type="radio" name="radio_${temp.key}" value="yes" required>Yes
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input  type="radio" name="radio_${temp.key}" value="no" required>No
           	<br><br>
            
           
           
            
         </c:forEach>   
            
        <input type="submit" value="Check Status" />
       
        
        
        
        </form>
    </div>
	
</body>
</html>






<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Audit Response</title>
<link rel="stylesheet" href="resources/static/response.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
    crossorigin="anonymous">
</head>
<body >

<div class="topnav">
          <a href="/home">Home</a>
          <a href="/logout">Logout</a>
      </div>

<div id="status">
	<table  class="table table-light table-hover table-striped table-bordered w-75 h-75" id="table">
  <tbody>
  
   <tr>
            
          <th scope="row">Project Manager Name</th>
            <td><strong>${response2.getProjectManagerName()  }</strong></td>
  </tr>
  
   <tr>
           <th scope="row">Audit Date</th>
          <td><strong>${response2.getAuditDetail().getAuditDate()}</strong></td>
  </tr>
  
  <tr>
           <th scope="row">Project Execution Status</th>
           <c:choose>
           <c:when test="${response.getProjectExecutionStatus()=='GREEN' }">
           <td class="bg-success"><strong>GREEN</strong></td>
          </c:when>
          <c:otherwise>
            <td class="bg-danger"><strong>RED</strong></td>
           </c:otherwise>
           </c:choose> 
        
  </tr>
  
  <tr>
           <th scope="row">Remedial Action Duration</th>
           <c:choose>
           <c:when test="${response.getRemedialActionDuration()==0 }">
           <td class="text-success"><strong>No Action Needed.</strong></td>
          </c:when>
          <c:otherwise>
            <td class="text-danger"><strong>Action needed to be taken in ${response.getRemedialActionDuration()} weeks.</strong></td>
           </c:otherwise>
           </c:choose> 
  </tr>
  
   
  </tbody>
</table>
        	
	
	</div>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>


<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
	
</body>
</html>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Audit Request Form</title>
<link rel="stylesheet" href="resources/static/auditrequeststyle.css" />

 
</head>
<body>


		<div class="topnav">
          <a href="/home">Home</a>
          <a href="/logout">Logout</a>
      </div>
      
     
		
		<div id="request">
      		<h1><strong><center>Audit Request Form</center></strong></h1>
      		<br><br>

      		<form action="auditQues" method="POST">
      		
      		<div>
        	<label for="pname">Project Name</label>
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;
        	<input
          		type="text"
          		id="pname"
          		name="pname"
          		placeholder="Enter project name"
          		pattern="[A-Za-z].{4,}"
          		title="Please Enter Valid Project Name"
          		required
        	/>
        	<br /><br />
        	<label for="pmname">Project Manager Name</label>
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<input
          		type="text"
          		id="pmname"
          		name="pmname"
          		pattern="[A-Za-z\s]{4,}"
          		title="Please Enter Valid Name (Minimum characters 4)"
          		placeholder="Enter Project Manager Name"
          		required
        	/>
        	<br /><br />
        	<label for="aoname">Application Owner Name</label>
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<input
          		type="text"
          		id="aoname"
          		name="aoname"
          		pattern="[A-Za-z\s]{4,}"
          		title="Please Enter Valid Name (Minimum characters 4)"
          		placeholder="Enter Application Owner Name"
          		required
        	/>
        	<br /><br />
        	<label for="auditType">Audit Type</label>
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;
        	<select id="auditType" name="auditType" required>
          		<option value="" selected>--Select--</option>
          		<option value="internal" >Internal</option>
          		<option value="sox">Sox</option>
        	</select>
        	<br /><br /><br>
        	<input type="submit" value="Submit"/>
        	
        	</div>
      		</form>
     
    </div>
		
</body>
</html>
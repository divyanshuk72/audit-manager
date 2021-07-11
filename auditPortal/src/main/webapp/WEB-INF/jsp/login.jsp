<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Audit Management | Login</title>
<link rel="stylesheet" href="resources/static/loginstyle.css">
</head>
<body>

        
	<div id="mydiv">

  		<h1>Audit Management System</h1>
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Login to your account</button>
     
        <h3 style="color:red"><center>${ message}</center></h3>
        <h3 style="color:green"><center>${logout}</center></h3>
	</div>

                  
                   
	<div id="id01" class="modal">
  
  		<form class="modal-content animate" action="/validateCredentials" method="post">

    	<div class="imgcontainer">

      	<span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
      	<img src="resources/static/Login.jpg" alt="Avatar" class="avatar">

    </div>

    <div class="container">

      <label for="username"><b>Username</b></label>&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="text" placeholder="Enter Username" name="username" required>
      <br>
      <label for="password"><b>Password</b></label>&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="password" placeholder="Enter Password" name="password" required>
      <br>
      <button type="submit">Login</button>
      <br><br>
      <label>
        <input type="checkbox" checked="checked" name="remember"> Remember me
      </label>

    </div>

    <div class="container" style="background-color:#f1f1f1">

      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw"><a href="#">Forgot password?</a></span>

    </div>

  </form>

</div>

<script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
</body>
</html>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Registration</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<?php
require('db.php');
//Jestliže odeslaný form insertuje do db
if (isset($_REQUEST['submit'])) {
	$username = stripcslashes($_REQUEST['username']);
	$username = mysqli_real_escape_string($con,$username);
	$email = stripcslashes($_REQUEST['email']);
	$email = mysqli_real_escape_string($con,$email);
	$password = stripcslashes($_REQUEST['password']);
	$password = mysqli_real_escape_string($con,$password);
	$query = "INSERT INTO users (username, password, email) VALUES ('$username', '".md5($password)."', '$email')";
	$result = mysqli_query($con, $query);
	if ($result) {	
	 echo "<div class='form'>
<h3>You are registered successfully.</h3>
<br/>Click here to <a href='login.php'>Login</a></div>";
        }
    }else{
    }
?>
<div class="form">
<h1>Registration</h1>
<form name="registration" action="" method="post">
<input type="text" name="username" placeholder="Username" required />
<input type="email" name="email" placeholder="Email" required />
<input type="password" name="password" placeholder="Password" required />
<input type="submit" name="submit" value="Register" />
</form>
</div>
</body>
</html>

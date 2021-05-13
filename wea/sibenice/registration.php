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
if (isset($_REQUEST['password'])) {
	$password = stripcslashes($_REQUEST['password']);
	$password = mysqli_real_escape_string($con,$password);
	$confirm_password = stripcslashes($_REQUEST['confirm_password']);
	$confirm_password = mysqli_real_escape_string($con,$confirm_password);
		$email = stripcslashes($_REQUEST['email']);
	$email = mysqli_real_escape_string($con,$email);
	//$trn_date = date("Y-m-d H:i:s");
	 $rsEmails = mysqli_query($con,"SELECT * FROM users WHERE email =  '".$email."'");
	         $numEmails = mysqli_num_rows($rsEmails);

    if($numEmails > 0) {
            echo "email already exists";
        }
        else
	 if ($_POST["password"] !== $_POST["confirm_password"]) {
	 	echo "hesla se neshodují";
	 } else {
	$query = "INSERT INTO users (password, email, confirm_password) VALUES ('".md5($password)."', '$email', '".md5($confirm_password)."')";
	$result = mysqli_query($con, $query);
	if ($result) {	
	 echo "<div class='form'>
<h3>You are registered successfully.</h3>
<br/>Click here to <a href='login.php'>Login</a></div>";
        }
    }
    }else{
?>
<div class="form">
<h1>Registration</h1>
<form name="registration" action="" method="post">
<input type="password" name="password" placeholder="Password" required />
<input type="password" name="confirm_password" placeholder="Password" required />
<input type="email" name="email" placeholder="Email" required />
<input type="submit" name="submit" value="Register" />
</form>
</div>
<?php } ?>
</body>
</html>
</body>
</html>
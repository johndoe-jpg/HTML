<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<link rel="stylesheet" type="text/css" href="style.css">
<body>
	<div class="form">
		<h1>Přihlášení</h1>
	<form method="post">
		<input type="email" name="email" placeholder="email" required>
		<input type="password" name="heslo" placeholder="Heslo" required>
		<input type="submit" name="submit" placeholder="odeslat">
		<h4>Nejste registrováni? Zaregistrujte se <a href="registrationew.php">zde</a></h4>
				<h4>Zapomenuté heslo <a href="zapomenute_heslo.php">zde</a></h4>

	</form>
</div>
</body>
</html>



<?php 
$con = mysqli_connect("localhost", "root", "", "test");
	if (mysqli_connect_error()) {
		echo "Spojení se nepovedlo" . mysqli_connect_error();
	}

	session_start();


	if (isset($_POST['submit'])) {
		$email = $_POST['email'];
		$heslo = $_POST['heslo'];

		$sql = "SELECT * FROM users WHERE email = '$email' AND heslo = '" . md5($heslo) . "'";
		$result = mysqli_query($con, $sql) or die(mysqli_connect_error());
		$rows = mysqli_num_rows($result);
		if ($rows == 1) {
			$_SESSION['email'] = $email;
			$_SESSION['loggedin'] = true;
			header("Location:index.php");
		}else
		{
			echo "zadali jste špatné přihlašovací údaje";
		}
		}
		 ?>
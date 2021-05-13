<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<link rel="stylesheet" type="text/css" href="style.css">
<body>
	<div class="form">
	<form method="post">
		<h2>Registrace</h2>
		<input type="email" name="email" placeholder="email" required><br>
		<input type="password" name="heslo" placeholder="heslo" required><br>
		<input type="password" name="kontrola_hesla" placeholder="kontrola hesla" required><br>
		<input type="submit" name="submit" placeholder="Odeslat">
		<h4>Jste registrováni? Přihlaste se <a href="login.php">zde</a></h4>

	</form>
</div>
</body>
</html>
<?php
	$con = mysqli_connect("localhost", "root","","test");
	if (mysqli_connect_error()) {
		echo "Připojení se nezdařilo" . mysql_connect_error();
	}

	if (isset($_POST['submit'])) {
		$email = $_POST['email'];
		$heslo = $_POST['heslo'];
		$kontrola_hesla = $_POST['kontrola_hesla'];


		$sql_e = "SELECT * FROM users WHERE email = '$email'";
		$res_e = mysqli_query($con, $sql_e);

		if (mysqli_num_rows($res_e) > 0) {
			echo "Email už existuje";
		}else
			if ($_POST["heslo"] !== $_POST["kontrola_hesla"]) {
				echo "hesla se neshodují";
		}
		else{
			$sql = "INSERT INTO users (email, heslo) VALUES ('$email', '".md5($heslo)."')";
			$result = mysqli_query($con, $sql);
			if ($result) {
				echo "Jsme tam";
			}
		}

			}
  ?>
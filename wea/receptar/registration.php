<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<link rel="stylesheet" type="text/css" href="style.css">

<body>
	<div class="form">
<h1>Registration</h1>
<form name="" action="" method="POST">
<input type="email" name="email" placeholder="Email" required />
<input type="password" name="heslo" placeholder="Heslo" required />
<input type="password" name="kontrola_hesla" placeholder="Kontrola hesla" required />
<input type="submit" name="submit" value="Register" />
<a href="login.php"> Přihlásit se</a>
</form>
</div>
</div>
</body>
</html>
	<?php
	session_start();

$con = mysqli_connect("localhost","root","","receptar");
// Check connection
if (mysqli_connect_error())
  {
  echo "Selhalo spojení s MySQL: " . mysqli_connect_error();
  }

//Jestliže odeslaný form insertuje do db
if (isset($_POST['submit'])) {
	$email = stripcslashes($_POST['email']);
	$email = mysqli_real_escape_string($con,$email);
	$heslo = stripcslashes($_POST['heslo']);
	$heslo = mysqli_real_escape_string($con,$heslo);
	$kontrola_hesla = stripcslashes($_POST['kontrola_hesla']);
	$kontrola_hesla = mysqli_real_escape_string($con,$kontrola_hesla);

	
  	$sql_e = "SELECT * FROM users WHERE email='$email'";
  	$res_e = mysqli_query($con, $sql_e);

  	 if(mysqli_num_rows($res_e) > 0){
  	  echo "Sorry... email already taken"; 	
  	 }else
	 if ($_POST["heslo"] !== $_POST["kontrola_hesla"]) {
	 	echo "hesla se neshodují";
	 } else {
	$query = "INSERT INTO users (email, heslo) VALUES ('$email', '".md5($heslo)."')";
	$result = mysqli_query($con, $query);
	if ($result) {	
		echo"JSME TAM ZMRDE";
        }
    }
}
?>

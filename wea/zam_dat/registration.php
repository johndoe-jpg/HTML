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
<input type="text" name="jmeno" placeholder="Jméno" required />
<input type="text" name="prijmeni" placeholder="Příjmení" required />
<input type="password" name="heslo" placeholder="Heslo" required />
<input type="password" name="kontrola_hesla" placeholder="Kontrola hesla" required />
<input type="email" name="email" placeholder="Email" required />
<input type="text" name="nazev_firmy" placeholder="Název firmy" required />
<input type="submit" name="submit" value="Register" />
<a href="login.php"> Přihlásit se</a>
</form>
</div>
</div>
</body>
</html>
	<?php
	session_start();

$con = mysqli_connect("localhost","root","","zam_dat");
// Check connection
if (mysqli_connect_error())
  {
  echo "Selhalo spojení s MySQL: " . mysqli_connect_error();
  }

//Jestliže odeslaný form insertuje do db
if (isset($_POST['submit'])) {
	$jmeno = stripcslashes($_POST['jmeno']);
	$jmeno = mysqli_real_escape_string($con,$jmeno);
	$prijmeni = stripcslashes($_POST['prijmeni']);
	$prijmeni = mysqli_real_escape_string($con,$prijmeni);
	$heslo = stripcslashes($_POST['heslo']);
	$heslo = mysqli_real_escape_string($con,$heslo);
	$kontrola_hesla = stripcslashes($_POST['kontrola_hesla']);
	$kontrola_hesla = mysqli_real_escape_string($con,$kontrola_hesla);
	$email = stripcslashes($_POST['email']);
	$email = mysqli_real_escape_string($con,$email);
	$nazev_firmy = stripcslashes($_POST['nazev_firmy']);
	$nazev_firmy = mysqli_real_escape_string($con,$nazev_firmy);
	
  	$sql_e = "SELECT * FROM users WHERE email='$email'";
  	$res_e = mysqli_query($con, $sql_e);

  	 if(mysqli_num_rows($res_e) > 0){
  	  echo "Sorry... email already taken"; 	
  	 }else
	 if ($_POST["heslo"] !== $_POST["kontrola_hesla"]) {
	 	echo "hesla se neshodují";
	 } else {
	$query = "INSERT INTO users (jmeno, prijmeni, heslo, email, nazev_firmy) VALUES ('$jmeno', '$prijmeni', '".md5($heslo)."','$email', '$nazev_firmy')";
	$result = mysqli_query($con, $query);
	if ($result) {	
		echo"JSME TAM ZMRDE";
        }
    }
}
?>

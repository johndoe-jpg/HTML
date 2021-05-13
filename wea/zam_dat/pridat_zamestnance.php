<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<link rel="stylesheet" type="text/css" href="style.css">

<body>
	<div class="form">
<h1>Přidat oddělení</h1>
<form name="" action="" method="POST">
<input type="text" name="jmeno" placeholder="Jméno" required />
<input type="text" name="prijmeni" placeholder="Prijmeni" required />
<input type="date" name="datum_nastupu" placeholder="Datum nástupu" required />
<input type="submit" name="submit" value="Přidat oddělení" />
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
	$datum_nastupu = stripcslashes($_POST['datum_nastupu']);
	$datum_nastupu = mysqli_real_escape_string($con,$datum_nastupu);

	$query = "INSERT INTO zamestnanci (jmeno, prijmeni, datum_nastupu) VALUES ('$jmeno', '$prijmeni', '$datum_nastupu')";
	$result = mysqli_query($con, $query);
	if ($result) {	
		echo"JSME TAM ZMRDE";
        }
    
}
?>

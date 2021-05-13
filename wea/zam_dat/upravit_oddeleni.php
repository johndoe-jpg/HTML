<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<link rel="stylesheet" type="text/css" href="style.css">

<body>
	<div class="form">
<h1>Upravit oddělení</h1>
<form name="" action="" method="POST">
<input type="text" name="nazev" placeholder="Název" required />
<input type="text" name="zkratka" placeholder="Zkratka" required />
<input type="text" name="mesto" placeholder="Město" required />
<input type="color" name="barva" placeholder="Barva" required />
<input type="text" name="pocet_zam_oddeleni" placeholder="Počet zaměstanců v oddělení" required />
<input type="submit" name="submit" value="Upravit oddělení" />
<input type="hidden" name="id" value="<? echo $_GET['id']; ?>">
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

if(!isset($_GET['id']) && !isset($_POST['submit']))
{
	echo "Chybí parametr ID.";
	return;
}
$id = $_GET['id'];

$query = "SELECT * FROM oddělení WHERE id=$id";
$result = mysqli_query($con, $query);
if ($result) {	
	echo"JSME TAM ZMRDE";
}

//Jestliže odeslaný form insertuje do db
if (isset($_POST['submit'])) {

	$nazev = stripcslashes($_POST['nazev']);
	$nazev = mysqli_real_escape_string($con,$nazev);
	$zkratka = stripcslashes($_POST['zkratka']);
	$zkratka = mysqli_real_escape_string($con,$zkratka);
	$mesto = stripcslashes($_POST['mesto']);
	$mesto = mysqli_real_escape_string($con,$mesto);
	$barva = stripcslashes($_POST['barva']);
	$barva = mysqli_real_escape_string($con,$barva);
	$pocet_zam_oddeleni = stripcslashes($_POST['pocet_zam_oddeleni']);
	$pocet_zam_oddeleni = mysqli_real_escape_string($con,$pocet_zam_oddeleni);


	$query = "UPDATE oddeleni SET nazev='$nazev', zkratka='$zkratka', mesto='$mesto', barva='$barva', pocet_zam_oddeleni='$pocet_zam_oddeleni' WHERE id=$id";
	$result = mysqli_query($con, $query);
	if ($result) {	
		echo"JSME TAM ZMRDE";
        }
    
}

?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>smazat</title>
</head>
<body>
	<h3>Opravdu si přejete smazat toto oddělení?</h3>
<form name="" action="" method="POST">
<input type="submit" name="submit" value="Smazat oddělení" />
</form>
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

/*$query = "SELECT * FROM oddělení WHERE id=$id";
$result = mysqli_query($con, $query);
if ($result) {	
	echo"JSME TAM ZMRDE";
}*/

if (isset($_POST['submit'])){

	$query = "DELETE FROM oddeleni WHERE id=$id";
	$result = mysqli_query($con, $query);
	if ($result) {	
		echo"JSME TAM ZMRDE";
		 header("Location: sprava_oddeleni.php");

        }
    
}

?>
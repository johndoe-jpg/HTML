<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<link rel="stylesheet" type="text/css" href="style.css">

<body>
	<div class="form">
<h1>Přidat recept</h1>
<form name="" action="" method="POST" enctype="multipart/form-data">
<input type="text" name="nazev" placeholder="Název" required />
<input type="text" name="postup" placeholder="Postup" required />
<input type="file" name="image" placeholder="Obrázek" required />
<input type="text" name="doba_pripravy" placeholder="Doba přípravy" required />
<select type="text" name="narocnost" required />
  <option value="snadne" >snadné</option>
  <option value="stredni" >střední</option>
  <option value="narocne">náročné</option>
</select>
<input type="submit" name="submit" value="Přidat recept" />
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
print_r($_POST['image']);
die();
//Jestliže odeslaný form insertuje do db
if (isset($_POST['submit'])) {
	$nazev = stripcslashes($_POST['nazev']);
	$nazev = mysqli_real_escape_string($con,$nazev);
	$postup = stripcslashes($_POST['postup']);
	$postup = mysqli_real_escape_string($con,$postup);
	$image = stripcslashes($_POST['image']);
	$image = mysqli_real_escape_string($con,$image);
	$doba_pripravy = stripcslashes($_POST['doba_pripravy']);
	$doba_pripravy = mysqli_real_escape_string($con,$doba_pripravy);
	$narocnost = stripcslashes($_POST['narocnost']);
	$narocnost = mysqli_real_escape_string($con,$narocnost);

	$query = "INSERT INTO recepty (nazev, postup, image, doba_pripravy, narocnost) VALUES ('$nazev', '$postup', '$image','$doba_pripravy', '$narocnost')";
	$result = mysqli_query($con, $query);
	if ($result) {	
		echo"JSME TAM ZMRDE";
        }
     
}
?>

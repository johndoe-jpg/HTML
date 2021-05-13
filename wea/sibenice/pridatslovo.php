<?php 
	$messagee = "Toto slovo již je v databázi.";

require('db.php');
  if(isset($_POST['submit']))
{
	 $rsSlovo = mysqli_query($con,"SELECT * FROM slova WHERE slovo = '".$_POST["slovo"]."'");
	         $numSlovo = mysqli_num_rows($rsSlovo);

    if($numSlovo > 0) {
echo "<script type='text/javascript'>alert('$messagee');</script>";
        } else {
    $sql = "INSERT INTO slova (slovo) VALUES ('".$_POST["slovo"]."')";

    $result = mysqli_query($con,$sql);
}

    $message = "Slovo úspěšně přidáno!";
    echo "
<div class='form'>
$message<br>
Klikni zde pro návrat na hlavní stránku <a href='index.php'>Zpět na hlavní stránku</a></div>";
}
 ?>


 <!DOCTYPE html>
 <html>
 <head>
 	<title></title>
 </head>
 <body>
 	<form method="POST">
 		<input type="text" name="slovo">
 		<input type="submit" name="submit">
 	</form>
 </body>
 </html>
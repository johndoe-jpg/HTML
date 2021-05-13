

<?php 
	if ($_POST) {
		$email = $_POST['email'];

		$con = mysqli_connect("localhost", "root", "", "test");
		if (mysqli_connect_error()) {
			echo "Spojení se nepovedlo" . mysqli_connect_error();
		}

		$sql = "SELECT * FROM users WHERE email = '$email'";
		$result = mysqli_query($con, $sql);
		if ($result && mysqli_num_rows($result) == 1) {
			$row = mysqli_fetch_assoc($result);
			$kontrolni_otazka = $row['kontrolni_otazka'];
		}else{
			$error = true;
		}
		mysqli_close($con);
	}
 ?>


 <!DOCTYPE html>
 <html lang="en">
 <head>
 	<meta charset="UTF-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
 	<title>Document</title>
 	<link rel="stylesheet" href="style.css">
 </head>
 <body>

 	<div class="form">
 		<?php if (!$_POST || isset($error)) {
 			
 		?>
 		<form action="" method="POST">
 			<input type="email" name="email" placeholder="Email" required>
 			<input type="submit" name="submit" placeholder="submit">
 		</form>

 		<?php 
 			if (isset($error)) {
 				echo "Tento email neexistuje";
 			}
 		}else{
 		 ?>

 		 <form action="zmena_hesla.php" method="POST">
 		 	<?php echo $kontrolni_otazka; ?>:<input type="odpoved" name="odpoved" placeholder="Odpověď" required>
 		 	<input type="password" name="nove_heslo" placeholder="Nové heslo" required>
 		 	<input type="hidden" name="email" value="<?php echo $email; ?>">
 		 	<input type="submit" name="submit" placeholder="Submit">
 		 </form>
 		
 	</div>
 	<?php } ?>
 </body>
 </html>

<?php 
	$con = mysqli_connect("localhost","root","", "test");
	if (mysqli_connect_error()) {
		echo "Spojení se nezdařilo" . mysqli_connect_error();
	}

	if ($_POST) {
		$email = $_POST['email'];
		$odpoved = $_POST['odpoved'];
		$nove_heslo = $_POST['nove_heslo'];

		$sql = "UPDATE users SET heslo = '". md5($nove_heslo) ."' WHERE email = '$email' AND odpoved = '$odpoved';";
		$result = mysqli_query($con, $sql);
		if (mysqli_affected_rows($con) > 0) {
		echo "Heslo bylo úspěšně změněno";
		return;
		}
		else
		{
			echo "Byla zadána špatná odpoveď.";

		}
	}
 ?>


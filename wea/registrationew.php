 <!DOCTYPE html>
 <html>
 <head>
 	<title></title>
 </head>
 <body>
 		<div class="form">
		<h1>Registration</h1>
	<form method="post">
	<input type="email" name="email" placeholder="Email" required>
	<input type="password" name="heslo" placeholder="Heslo" required>
	<input type="password" name="kontrola_hesla" placeholder="Kontrola hesla" required>
	<input type="text" name="kontrolni_otazka" placeholder="Kontrolní otázka" required>
	<input type="text" name="odpoved" placeholder="Odpověď" required>
	<input type="submit" name="submit" placeholder="Submit">
	<h3>Přihlásit se <a href="loginnew.php">zde</a></h3>
	</form>
	</div>

 </body>
 </html>

 <?php 
 		$con = mysqli_connect("localhost", "root","","test");
 		if (mysqli_connect_error()) {
 			echo "Spojení se nezdařilo" . mysqli_connect_error();
 		}



 		if (isset($_POST['submit'])) {
 			$email = $_POST['email'];
 			$heslo = $_POST['heslo'];
 			$kontrola_hesla = $_POST['kontrola_hesla'];
 			$kontrolni_otazka = $_POST['kontrolni_otazka'];
 			$odpoved = $_POST['odpoved'];


 			$sql_e = "SELECT * FROM users WHERE email = '$email'";
 			$res_e = mysqli_query($con, $sql_e);

 			if (mysqli_num_rows($res_e) > 0) {
			echo "Tento email je již zaregistrován";
 			}else if ($_POST['heslo'] !== $_POST['kontrola_hesla']) {
 					echo "hesla se neshodují";
 				}
 				else
 				{
 					$sql = "INSERT INTO users (email, heslo, kontrolni_otazka, odpoved) VALUES ('$email', '" . md5($heslo) . "', '$kontrolni_otazka', '$odpoved')";

 					$results = mysqli_query($con, $sql);
					if ($results) {
					echo "jsme tam";
		}
 				}
 				
 			}
 		
  ?>















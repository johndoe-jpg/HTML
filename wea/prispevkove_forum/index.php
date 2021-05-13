<?php
	session_start();
?>
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<style>
	h1
	{
		font-size: 100px;
	}
</style>
<body>
		<a href="login.php">
		<button>Příhlášení</button>
	</a>
	<a href="registration.php">
		<button>Registrace</button>
	</a>
  <a href="logout.php">
    <button>Odhlásit se</button>
  </a>
<h1>Čauky kotě, jsme tu!</h1>
  <?php if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
    echo "Jméno uživatele: " . $_SESSION['email'] . "!";

} else {
    echo "Uživatel: neregistrovaný";
}    ?>

	
	    <div class="form">
<h1>Přidání příspěvku</h1>
<form name="" action="" method="post">

<?php 
if($_SESSION['user_id'] == '14'):

 ?>
 <h3>ADMIN PRÁVA</h3>
 <input type="text" name="txt" placeholder="Text" required />
    <input type="submit" name="submit" value="Odeslat" />
  <?php else: 

  echo "Jen admin přidává příspěvky.";

   ?>

<?php endif; ?>



</form>
<?php
$con = mysqli_connect("localhost","root","","prispevkove_forum");
// Check connection
if (mysqli_connect_error())
  {
  echo "Selhalo spojení s MySQL: " . mysqli_connect_error();
  }
if (isset($_POST['submit'])) {
	$txt = stripcslashes($_POST['txt']);
	$txt = mysqli_real_escape_string($con,$txt);
	$user = $_SESSION['user_id'];

    $query = "INSERT INTO prispevky (txt, autor_id) VALUES ('$txt',$user)";
	$result = mysqli_query($con, $query);
	if ($result) {	
		echo"Odesláno";
        }
        else{
        	echo "rip";
        }
}
	$query = "SELECT users.jmeno, prispevky.txt, prispevky.vytvoreno FROM users INNER JOIN prispevky ON users.id = prispevky.autor_id WHERE id = '" . $_SESSION['user_id'] . "'";
	$result = mysqli_query($con, $query);
if (mysqli_num_rows($result) > 0) {
// output data of each row
while($row = mysqli_fetch_assoc($result)) {
	echo "autor: " . $row["jmeno"]."<br>";
    echo "txt: " . $row["txt"]."<br>";
    echo "Vytvořeno: " . $row["vytvoreno"]."<br>";
} 
} else {
   echo "0 results";
}
mysqli_close($con);
?>
</body>
</html>
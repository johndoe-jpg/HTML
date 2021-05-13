<?php
session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
</head>
<body>
<h1>Čauky kotě, jsme tu!</h1>
  <?php if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
    echo "Jméno uživatele: " . $_SESSION['email'] . "!";
     echo "<a href='logout.php'>
		<button>Odhlásit se</button>
	</a>";
 echo "<a href='pridat_oddeleni.php'>
		<button>Přidat oddělení</button>
	</a>";
	 echo "<a href='upravit_oddeleni.php'>
		<button>Upravit oddělení</button>
	</a>";
} else {
    echo "Uživatel: neregistrovaný";
     echo "<a href='login.php'>
		<button>Příhlášení</button>
	</a>";
	 echo "<a href='Registrace.php'>
		<button>Registrace</button>
	</a>";
}    ?>




</body>
</html>
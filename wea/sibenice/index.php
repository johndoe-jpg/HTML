<?php
session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <!-- Bootstrap 4 CDN -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

  <title>Šibenice</title>
</head>
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
  <a href="statistiky.php">
    <button>Moje statistiky</button>
  </a>
<div class="container">
  <?php if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
    echo "Jméno uživatele: " . $_SESSION['email'] . "!";
} else {
    echo "Uživatel: neregistrovaný";
}    ?>
  <h1 class="text-center">Šibenice</h1>
  <div class="float-right">Špatné pokusy: <span id='mistakes'>0</span> z <span id='maxWrong'></span></div>
  <div class="text-center">
    <img id='hangmanPic' src="./images/0.jpg" alt="">
    <p>Zkus uhádnout slovo</p>
    <p id="wordSpotlight">Hádané slovo bylo:</p>
    <div id="keyboard"></div>
    <button class="btn btn-info" onClick="reset()">Reset</button>
    <a href="pridatslovo.php">    
  <button class="btn btn-info">Přidat slovo</button>
</a>
  </div> 
</div>

<script src='hangman.js'></script>
</body>
</html>
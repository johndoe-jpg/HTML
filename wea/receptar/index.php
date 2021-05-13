<?php
	session_start();
?>
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
 
<body>
  <?php if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
    echo "Jméno uživatele: " . $_SESSION['email'] . "!";
     echo "<a href='logout.php'>
		<button>Odhlásit se</button>
	</a>";
 echo "<a href='pridat_recept.php'>
		<button>Přidat recept</button>
	</a>";
	 echo "<a href='upravit_oddeleni.php'>
		<button>Seznam receptů</button>
	</a>";
	 echo "<a href='upravit_oddeleni.php'>
		<button>Oblíbené recepty</button>
	</a>";
	 echo "<a href='upravit_oddeleni.php'>
		<button>Odložené recepty</button>
	</a>";
} else {
    echo "Uživatel: neregistrovaný";
     echo "<a href='login.php'>
		<button>Příhlášení</button>
	</a>";
	 echo "<a href='Registrace.php'>
		<button>Registrace</button>
	</a>";
	 echo "<a href='pridat_oddeleni.php'>
		<button>Přidat recept</button>
	</a>";
	 echo "<a href='upravit_oddeleni.php'>
		<button>Seznam receptů</button>
	</a>";
}    


$con = mysqli_connect("localhost","root","","receptar");
// Check connection
if (mysqli_connect_error())
  {
  echo "Selhalo spojení s MySQL: " . mysqli_connect_error();
  }

        $query = "SELECT nazev, image, doba_pripravy, narocnost FROM recepty";
         $result = $con->query($query);
   if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {

echo "<table border='1'>
<tr>
<th>Název</th>
<th>image</th>
<th>doba Připravy</th>
<th>naročnost</th>

</tr>";

while($row = mysqli_fetch_array($result))
{
   echo '
                       <div class="card">
                          <img class="card-img-top" src="data:image/png;base64,'.base64_encode($row['image'] ).'" alt="Card image cap">

                        </div>
                ';
echo "<tr>";
$color = $row['barva'];
echo "<td style='color:$color;'>" . $row['nazev'] . "</td>";
echo  '<img src="data:image/jpeg;base64",' .base64_encode($row['image'] ).'';
echo "<td style='color:$color;'>" . $row['doba_pripravy'] . "</td>";
echo "<td style='color:$color;'>" . $row['narocnost'] . "</td>";


echo "</tr>";
}
echo "</table>";
  }
} else {
  echo "0 results";
}




?>



</body>
</html>
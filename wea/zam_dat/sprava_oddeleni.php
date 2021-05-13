<?php
$con = mysqli_connect("localhost", "root", "", "zam_dat");
// Check connection
if (mysqli_connect_error())
{
    echo "Selhalo spojení s MySQL: " . mysqli_connect_error();
}

session_start();
    $query = "SELECT * FROM oddeleni";
    $result = $con->query($query);
   if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
    //echo "Název: " . $row["nazev"]. " - Zkratka: " . $row["zkratka"]. " - Město " . $row["mesto"]. " - Barva". $row["barva"]. " - Počet zaměstnanců v oddělení". $row["pocet_zam_oddeleni"]."<br>";

echo "<table border='1'>
<tr>
<th>ID</th>
<th>Název</th>
<th>Zkartka</th>
<th>Město</th>
<th>Počet zaměstnanců v oddělení</th>
<th>Možnosti</th>

</tr>";

while($row = mysqli_fetch_array($result))
{

echo "<tr>";
$color = $row['barva'];
echo "<td style='color:$color;'>" . $row['id'] . "</td>";
echo "<td style='color:$color;'>" . $row['nazev'] . "</td>";
echo "<td style='color:$color;'>" . $row['zkratka'] . "</td>";
echo "<td style='color:$color;'>" . $row['mesto'] . "</td>";

echo "<td style='color:$color;'>" . $row['pocet_zam_oddeleni'] . "</td>";
echo "<td><a href=\"upravit_oddeleni.php?id=" . $row['id'] ."\"><button>Upravit</button></a></td>";
echo "<td><a href=\"smazat_oddeleni.php?id=" . $row['id'] ."\"><button>Smazat</button></a></td>";
echo "<td><a href=\"detail_oddeleni.php?id=" . $row['id'] ."\"><button>Detail</button></a></td>";


echo "</tr>";
}
echo "</table>";
  }
} else {
  echo "0 results";
}

?>



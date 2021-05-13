<?php
$conn = mysqli_connect("localhost","root","","registration_login");
// Check connection
if (mysqli_connect_error())
  {
  echo "Selhalo spojení s MySQL: " . mysqli_connect_error();
  }
$sql = "SELECT id FROM users";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
    echo "id: " . $row["id"]. "<br>";
  }
} else {
  echo "0 results";
}
?>
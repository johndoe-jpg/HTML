<?php
$con = mysqli_connect("localhost","root","","registration_login");
echo "joooooooo";
// Check connection
if (mysqli_connect_error())
  {
  echo "Selhalo spojení s MySQL: " . mysqli_connect_error();
  }
?>
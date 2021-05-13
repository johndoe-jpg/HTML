<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<link rel="stylesheet" type="text/css" href="style.css">

<body>
	<div class="form">
<h1>Login</h1>
<form name="login" action="" method="POST">
<input type="text" name="email" placeholder="email" required />
<input type="password" name="heslo" placeholder="Heslo" required />
<input type="submit" name="submit" value="Login" />
</form>
</div>
</div>
</body>
</html>
<?php
$con = mysqli_connect("localhost", "root", "", "prispevkove_forum");
// Check connection
if (mysqli_connect_error())
{
    echo "Selhalo spojení s MySQL: " . mysqli_connect_error();
}

session_start();
if (isset($_POST['email']))
{
    // removes backslashes
    $email = stripslashes($_REQUEST['email']);
    //escapes special characters in a string
    $email = mysqli_real_escape_string($con, $email);
    $heslo = stripslashes($_REQUEST['heslo']);
    $heslo = mysqli_real_escape_string($con, $heslo);
    //Checking is user existing in the database or not
    $query = "SELECT * FROM users WHERE email='$email'and heslo='" . md5($heslo) . "'";
    $result = mysqli_query($con, $query) or die(mysql_error());
    $rows = mysqli_num_rows($result);
    if ($rows == 1)
    {
        $data = mysqli_fetch_assoc($result);
        $_SESSION['user_id'] = $data['id'];      
        $_SESSION['email'] = $email;
        $_SESSION['loggedin'] = true;
        // Redirect user to index.php
        header("Location: index.php");
    }
    else
    {
    	session_destroy();

        echo "<div class='form'>
<h3>email/heslo is incorrect.</h3>
<h3><a href='forgotten_password.php'>Zapomenuté heslo</h3>
<br/>Click here to <a href='login.php'>Login</a></div>";
    }
}

?>


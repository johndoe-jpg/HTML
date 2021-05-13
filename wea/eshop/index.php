<?php 
// Initialize shopping cart class 
include_once 'Cart.class.php'; 
$cart = new Cart; 
 
// Include the database config file 
require_once 'dbConfig.php'; 
?>

<!DOCTYPE html>
<html lang="en">
<head>
<title>PHP Shopping Cart</title>
<meta charset="utf-8">

<!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<!-- Custom style -->
<link href="css/style.css" rel="stylesheet">

</head>
</head>
<body>
<div class="container">
    <h1>PRODUCTS</h1>
	
    <!-- Cart basket -->
    <div class="cart-view">
        <a href="viewCart.php" title="View Cart"><i class="icart"></i> (<?php echo ($cart->total_items() > 0)?$cart->total_items().' Items':'Empty'; ?>)</a>
    </div>
    
    <!-- Product list -->
    <div class="row col-lg-12">
        <?php 
        // Get products from database 
        $result = $db->query("SELECT * FROM products ORDER BY id DESC LIMIT 10"); 
        if($result->num_rows > 0){  
            while($row = $result->fetch_assoc()){ 
        ?>
        <div class="card col-lg-4">
            <div class="card-body">
                <h5 class="card-title"><?php echo $row["name"]; ?></h5>
                <h6 class="card-subtitle mb-2 text-muted">Price: <?php echo '$'.$row["price"].' USD'; ?></h6>
                <p class="card-text"><?php echo $row["description"]; ?></p>
                <a href="cartAction.php?action=addToCart&id=<?php echo $row["id"]; ?>" class="btn btn-primary">Add to Cart</a>
            </div>
        </div>
        <?php } }else{ ?>
        <p>Product(s) not found.....</p>
        <?php } ?>
    </div>
</div>
</body>
</html>
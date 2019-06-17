<?php
	$con = mysql_connect("localhost", "jdw0624","jungdw76052757","jdw0624");

	$userID = $_POST["userID"];
	$userPassword = $_POST["userPassword"];
	$userName = $_POST["userName"];
	$userAge = $_POST["userAge"];

	$statement = mysqli_prepare($con, "insert into user values (?, ?, ?, ?)");
	mysqli_stmt_bind_param($statement, "sssi", $userID, $userPassword, $userName, $userAge);
	mysqli_stmt_execute($statement);


	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>
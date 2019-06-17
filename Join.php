<?php
	$con = new mysqli("localhost", "jdw0624","jungdw76052757","jdw0624");

	$userID = $_POST["userID"];
	$userPassword = $_POST["userPassword"];
	$userName = $_POST["userName"];
	$userAge = $_POST["userAge"];

	$stmt = $con->prepare("insert into user values (?, ?, ?, ?)");
	$stmt->bind_param("sssi", $userID, $userPassword, $userName, $userAge);
	$stmt->execute();

	$response = array();
	$response["success"] = true;

	echo json_encode($response);

	$con->close();
?>
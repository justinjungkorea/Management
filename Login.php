<?php
	$con = new mysqli("localhost", "jdw0624","jungdw76052757","jdw0624");

	$userID = $_POST["userID"];
	$userPassword = $_POST["userPassword"];

	$stmt = $con->prepare("select * from user where userID=? and userPassword=?");
	$stmt->bind_param("ss", $userID, $userPassword);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($userID, $userPassword, $userName, $userAge);


	$response = array();
	$response["success"] = false;

	while($stmt->fetch()){
		$response["success"] = true;
		$response["userID"] = $userID;
		$response["userPassword"] = $userPassword;
		$response["userName"] = $userName;
		$response["userAge"] = $userAge;
	}

	$stmt->close();

	echo json_encode($response);

	$con->close();
?>
	
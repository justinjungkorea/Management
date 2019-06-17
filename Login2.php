<?php
	$con = mysql_connect("localhost", "jdw0624","jungdw76052757","jdw0624");

	$userID = $_POST["userID"];
	$userPassword = $_POST["userPassword"];

	$statement = mysqli_prepare($con, "select * from user where userID=? and userPassword=?");
	mysqli_stmt_bind_param($statement, "ss", $userID, $userPassword);
	mysqli_stmt_execute($statement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $userID, $userPassword, $userName, $userAge);

	$response = array();
	$response["success"] = false;

	while(mysql_stmt_fetch($statement)){
		$response["success"] = true;
		$response["userID"] = $userID;
		$response["userPassword"] = $userPassword;
		$response["userName"] = $userName;
		$response["userAge"] = $userAge;
	}

	echo json_encode($response);
?>
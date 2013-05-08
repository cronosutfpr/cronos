<?php

//	$year = date('Y');
//	$month = date('m');
//
//	echo json_encode(array(
//	
//		array(
//			'id' => 111,
//			'title' => "Event1",
//			'start' => "$year-$month-10",
//			'url' => "http://yahoo.com/"
//		),
//		
//		array(
//			'id' => 222,
//			'title' => "Event2",
//			'start' => "$year-$month-20",
//			'end' => "$year-$month-22",
//			'url' => "http://yahoo.com/"
//		)
//	
//	));



//include "classes/geral.php";
//$geral = new geral();

//$sql = "SELECT * FROM book WHERE classroom_id = " . $idClassroom;
//$sql = "SELECT id, note as title, startdate as start, endDate as end, 'http:\/\/yahoo.com\/' as url FROM book WHERE classroom_id = 64";
//
//$query_reserva = $geral->sql_select($sql);
//$result = $geral->sql_select($sql);
//$arr = array();
//while ($row = mysql_fetch_assoc($result)) {
//    $arr[] = $row;
//}
//echo json_encode($arr);

$year = date('Y');
	$month = date('m');

	echo json_encode(array(
	
		array(
			'id' => 111,
			'title' => "Event1",
			'start' => "$year-$month-10",
			'url' => "http://yahoo.com/"
		),
		
		array(
			'id' => 222,
			'title' => "Event2",
			'start' => "$year-$month-20",
			'end' => "$year-$month-22",
			'url' => "http://yahoo.com/"
		)
	
	));
?>

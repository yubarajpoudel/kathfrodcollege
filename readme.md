College Project
======
*Kathford college application* is the android based mobile application solely developed 
for the purpose of developing the skills and knowledge on development in the mobile platform.
The main purpose of this application is to assist the users to explore about the college and get the instant
updates and information in the instant time.

Designed and Developed By
-------------------------
* Bachelor Of computer Engineering I/II
* Kathford Engineering College 

Ways To Work With:
* Download the SQL file and import it.
*Insert the data in the table for testing.

Download the php Folder:
* Copy all the php files and paste inside the server space
* and your app api is ready for controlling the datamanagement system of the application.


API Used In this Project 
------------------
//register.php



<?php
	$mycon=mysql_connect("localhost","root","root") or die('{"res":"Database Error"}');
	$mydb=mysql_select_db("kathford") or die('{"res":"Database Error"}');
	$action = (isset($_GET['action'])?($_GET['action']):"list");
	switch($action){
		case "list":
			$sql = "SELECT * FROM `register` LIMIT 0, 50"; 
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			$json_array = array();
			while($rows = mysql_fetch_array($myquery)){//`id`, `name`, `password`, `faculty`, `isactive`
					$row_array['id'] = $rows['id'];
					$row_array['name'] = $rows['name'];
					//$row_array['password'] = $rows['password'];
					$row_array['faculty'] = $rows['faculty'];
					$row_array['isactive'] = $rows['isactive'];
				    $row_array['created'] = $rows['created'];

				array_push($json_array,$row_array);	
			}
			$json_result['res'] = 'success';
			$json_result['data'] = $json_array;
			echo json_encode($json_result);
			break;
		case "view":
			if(!isset($_GET['id'])){
				die('{"res":"id not specified"}');
			}
			$sql = "SELECT * FROM `contacts` WHERE `id` = ".$_GET['id'] ; 
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			$row_array;
			while($rows = mysql_fetch_array($myquery)){//`id`, `name`, `password`, `faculty`, `isactive`
				$row_array['id'] = $rows['id'];
				$row_array['name'] = $rows['name'];
				//$row_array['password'] = $rows['password'];
				$row_array['faculty'] = $rows['faculty'];
				$row_array['isactive'] = $rows['isactive'];
				$row_array['created'] = $rows['created'];
				

			}
			$json_result['res'] = 'success';
			$json_result['data'] = $row_array;
			echo json_encode($json_result);
			break;
		
		case "search":
			$q = (isset($_GET['q']))?($_GET['q']):"";
			$sql = "SELECT * FROM `register` WHERE `name` LIKE '%".$q."%'LIMIT 0, 50";
			//echo $sql;
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			$json_array = array();
			while($rows = mysql_fetch_array($myquery)){//`id`, `name`, `password`, `faculty`, `isactive`
				$row_array['id'] = $rows['id'];
				$row_array['name'] = $rows['name'];
				//$row_array['password'] = $rows['password'];
				$row_array['faculty'] = $rows['faculty'];
				$row_array['isactive'] = $rows['isactive'];
				$row_array['created'] = $rows['created'];
			
					array_push($json_array,$row_array);	
			}
			$json_result['res'] = 'success';
			$json_result['data'] = $json_array;
			echo json_encode($json_result);
			break;
		case "login":
			$name = (isset($_GET['name']))?($_GET['name']):"";
			$password = (isset($_GET['password']))?($_GET['password']):"";
			$sql = "SELECT * FROM `register` WHERE `name` = '".$name."' AND `password` = '".$password."'";
			//echo $sql;
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			
			if($rows=mysql_fetch_array($myquery)){//`id`, `name`, `password`, `faculty`, `isactive`
				$json_result['res'] = 'success';
				$json_result['isactive']=$rows['isactive'];
				$json_result['message'] = 'success';
			}
			else{
				$json_result['res'] = 'failed';
				$json_result['message'] = 'Invalid username or password';
			}
			echo json_encode($json_result);
			break;
		case "add":
			$name = (isset($_GET['name'])?($_GET['name']):"guest");
			$lname = (isset($_GET['lname'])?($_GET['lname']):"guest");
			$pass = (isset($_GET['password'])?($_GET['password']):"");
			$fac = (isset($_GET['faculty'])?($_GET['faculty']):"");
			if($name != '' && $pass != '' && $fac != ''){
				$sql = "INSERT INTO `register` (`name`,`lname`, `password`, `faculty`) VALUES ('".$name."', '".$lname."',  '".$pass."', '".$fac."');";
				//echo $sql;
				$myquery=mysql_query($sql);
				if($myquery){
					$json_result['res'] = 'success';
					echo json_encode($json_result);
				}
				else{
					$json_result['res'] = 'error';
					$json_result['message'] = 'Database insertion error';
					echo json_encode($json_result);
				}
			}
			else{
				
					$json_result['res'] = 'error';
					$json_result['message'] = 'Field blank name, password or/and faculty';
					echo json_encode($json_result);
			}
			break;
		 case "newsfeed":
				$sql = "SELECT * FROM `newsfeed` LIMIT 0, 50"; 
			        $myquery = mysql_query($sql) or die('{"res":"Query error"}');
			        $json_array = array();
			       while($rows = mysql_fetch_array($myquery)){//`id`, `name`, `password`, `faculty`, `isactive`
					//$row_array['id'] = $rows['id'];
					$row_array['newsfeed'] = $rows['news'];
					//$row_array['password'] = $rows['password'];
					$row_array['date'] = $rows['date'];
					

				  array_push($json_array,$row_array);	
			         }
			     $json_result['res'] = 'success';
			     $json_result['data'] = $json_array;
			     echo json_encode($json_result);
			     break;
                   case "directory":
                   $sql = "SELECT * FROM `directory`"; 
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			$json_array = array();
			while($rows = mysql_fetch_array($myquery)){//`id`, `name`, `password`, `faculty`, `isactive`
					//$row_array['id'] = $rows['id'];
					$row_array['fname'] = $rows['fname'];
					//$row_array['password'] = $rows['password'];
					$row_array['lname'] = $rows['lname'];
					$row_array['faculty'] = $rows['faculty'];
                                        $row_array['phone'] = $rows['phone'];
					

				array_push($json_array,$row_array);	
			}
			$json_result['res'] = 'success';
			$json_result['data'] = $json_array;
			echo json_encode($json_result);
		     break; 

		default:
				$json_result['res'] = 'error';
				$json_result['message'] = 'Action not defined';
				echo json_encode($json_result);
			break;
	}
?>





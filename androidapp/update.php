<?php

include('DatabaseHandler.php');
$id=$_GET['id'];
$actions =$_GET['action'];
$tablename=$_GET['tname'];
switch($actions)
     {
	 case "active":
	       mysql_query("UPDATE ".$tablename." SET isactive=1 WHERE id=".$id);
	       echo "activated successfully";
		  
		   break;
	 case "deactive" :
	       mysql_query("UPDATE ".$tablename." SET isactive=0 WHERE id=".$id);
	       echo "Deactivated successfully";
		   break;
	 case "delete":
	 mysql_query("DELETE FROM ".$tablename." WHERE id=".$id);
	      echo "Deleted successfully";
		  break;
	 default:
		  echo "no selection made";
		  break;
	 }
	 switch($tablename)
	 {
		 case 'register':
                header("Location: mainhome.php");
                break;
		case 'student':
		        header("Location: studentrecord.php");
				break;
		case 'directory':
		        header("Location: DownloadManager.php");
				break;
		case 'newsfeed':
		        header("Location: DownloadManager.php");
				break;
	    case 'download':
		        header("Location: DownloadManager.php");
				break;
		default:
		        break;
	 }
	 mysql_close($con);
       
	  ?>
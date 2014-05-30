<?php 
$regId = $_GET['regId'];
 
 $con = mysql_connect("localhost","root","root");
 if(!$con){
  die('MySQL connection failed'.mysql_error());
 }
 
 $db = mysql_select_db("kathford",$con);
 if(!$db){
  die('Database selection failed'.mysql_error());
 }
 
 $sql = "INSERT INTO tblregistration (registration_id) values ('$regId')";
 
 if(!mysql_query($sql, $con)){
  die('MySQL query failed'.mysql_error());
 }
 
mysql_close($con);
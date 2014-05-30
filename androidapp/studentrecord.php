<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Untitled Document</title>
<style type="text/css">
#wrapper {
	margin:0 auto;
	width:900px;
}
#header {
	width:100%;
	height:150px;
	background-color:#036;
}
#container {
	height:500px;
	width:100%
}
.box {
	float:left;
	margin-left:5px;
	background-color:#096;
	height:490px;
	margin-top:5px;
	overflow:scroll;
}
#footer {
	height:50px;
	width:100%;
	background-color:#309;
}
</style>
<?php
include('DatabaseHandler.php');
?>
<?php

if(isset($_REQUEST['save'])!="")
{
    $name=$_REQUEST['fname'];
	$lastname=$_REQUEST['lname'];
	$roll=$_REQUEST['rno'];
	$course=$_REQUEST['course'];
	$semesters=$_REQUEST['semester'];
	
	if($name==""||$lastname==""||$roll=="")
	{
		echo"<script>alert('please fill the field correctly');</script>";
	}
	else {
	$sql="INSERT INTO student(fname,lname,rno,course,semester)VALUES('$name','$lastname','$roll','$course','$semesters')";
	$result=mysql_query($sql);
	   if($result)
	   {
		   echo "<script> 
		   alert('Data has been added successfully');
		   </script>";
	   }
	   else {
		   echo "
		   <script>
		   alert('Error in inserting the data');
		   ";
	   }
	}
		 
		 
	}
  ?>
  <?php
  $code="SELECT * FROM student";
  ?>
 
</head>
<body>
<div id="wrapper">
<div id="header"><img src="images/banner.jpg" width="900" height="150"  alt=""/></div>
<div id="container">
<div class="box" style="width:70%">
<h3>List of Registered Students</h3>
<table border="1" width="100%">
  <tr>
    <th scope="col">ID</th>
    <th scope="col">First Name</th>
    <th scope="col">Surname</th>
    <th scope="col">RollNo</th>
    <th scope="col">Course</th>
    <th scope="col">SEMESTER</th>
    <th scope="col">DELETE</th>
   
    
  </tr>
   <?php
   $result=mysql_query($code);
  while($row = mysql_fetch_array($result)) {
  ?>
  <tr>
    <td><?php echo $row['id']; ?></td>
    <td><?php echo $row['fname']; ?></td>
    <td><?php echo $row['lname']; ?></td>
    <td><?php echo $row['rno']; ?></td>
    <td><?php echo $row['course']; ?></td>
    <td><?php echo $row['semester']; ?></td>
    <td><a href="update.php?id=<?php echo $row['id']; ?>& action=<?php echo 'delete'; ?>& tname=<?php echo 'student'; ?>">Delete</a></td>
   
  </tr>
  <?php 
  }
  ?>
  
</table>

</div>
<div class="box" style="width:28%">
 <form method="post" style="padding:5px" >
  <h3>Student Details Record System</h3>
</p>
  <p><b>First Name</b> <br><input type="text" name="fname"> </p>
  </p>
  <p><b>Last Name</b> <br><input type="text" name="lname"> </p>
  </p>
  <p><b>Roll No</b> <br><input type="text" name="rno"> </p>
  </p>
  <p><b>Course</b> <br><select name="course">
            <option>B.E-Computer</option>
            <option>B.E-Electronic</option>
            <option>B.E-Civil</option>
            <option>BScCSIT</option>
            <option>BBA</option></select></p>
  </p>
  <p><b>Semester </b><br><select name="semester">
  <option>I</option>
  <option>II</option>
  <option>III</option>
  <option>IV</option>
  <option>V</option>
  <option>VI</option>
  <option>VII</option>
  <option>VIII</option>
  </select> </p>
  <input type="submit" name="save" value="Save"/>
</form>
</div>
</div>
<div id="footer"><center><font color="#FFFFFF">

<b>Designed and developed by : Yubaraj Poudel <br>
  Phone no: 9842583634 @
  copyright protected</b>
</font></center></div>
</div>
</body>
</html>
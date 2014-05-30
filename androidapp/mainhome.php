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
	height:150px;
	background-color:#0F9;
}

#studentdetail{
	height:auto;
	width:100%;
    margin-top:5px;
	
}
#content {
	
	height:320px;
	width:100%;
}
.box {
	margin-top:5px;
	width:280px;
	margin-left:5px;
	background-color:#039;
	margin-left:10px;
	height:300px;
	float:left;
	overflow:hidden;
	padding:5px;
}
#footer {
	width:100%;
	height:50px;
	background-color:#306;
}
 a {
	 color:#F00;
 }
</style>
<?php
include('DatabaseHandler.php');
?>

<?php 
if(isset($_REQUEST['addno'])!='')
{
	if($_REQUEST['fname']!=='' || $_REQUEST['lname']!=='' ||$_REQUEST['subject']!==''||$_REQUEST['phno']!=='')
	{

	     $name=$_REQUEST['fname'];
		 $surname=$_REQUEST['lname'];
		 $faculty=$_REQUEST['subject'];
		 $phn=$_REQUEST['phno'];
		 
		 $sql="INSERT INTO directory(fname,lname,faculty,phone)VALUES('$name','$surname','$faculty','$phn') ";
		 $result=mysql_query($sql);
		 if(!$result)
		  {
			  echo " 
			      <script>
				  alert('error in connection');
				  
				  </script>
			  
			  ";
		  }
		else {
			echo " 
			<script>
			alert('Your Data is added succesfully');
			</script>
			";
		}  
	}
   

else {
	echo "<script> alert('please fill the form');</script>";
}
}
?>
<?php

if(isset($_REQUEST['news'])!="")
{    $newsevent=$_REQUEST['newsfeed'];
	if($newsevent=="")
	echo "<script>alert('Please type in the message');</script>";
	else
	{   
		$sql = "INSERT INTO newsfeed(news)VALUES('$newsevent')";
		$result=mysql_query($sql);
		if(!$result)
		{
			echo "<script>alert('Error in inserting the news');</script>";
		}
		else {
			echo "<script>alert('News event has been added successfully');</script>";
		}
	}
}
?>

</head>
<body>
<div id="wrapper">
  <div id="header"><img src="images/banner.jpg" width="900" height="150"  alt=""/></div>
  
        <div id="studentdetail"><h3>List of the registered students</h3>
        <?php
		$result = mysql_query("SELECT * FROM register");
            $action1 ="active";
            $action2="deactive";
            $action3="delete";
			$table="register";
			?>
             <table border="1">
              <tr>
    <th width="110" scope="col">ID</th>
    <th width="110" scope="col">First Name</th>
    <th width="110" scope="col">last Name</th>
    <th width="110" scope="col">Faculty</th>
    <th width="110" scope="col">IsActive</th>
    <th width="110" scope="col">DeActivate</th>
    <th width="110" scope="col">Activate</th>
    <th width="110" scope="col">Delete</th>
  </tr>
             <?php
			while($row = mysql_fetch_array($result)) {
			
		?>
      
 
  <tr>
    <td><?php echo $row['id']; ?></td>
    <td><?php echo $row['name']; ?></td>
    <td><?php echo $row['lname']; ?></td>
    <td><?php echo $row['faculty']; ?></td>
    <td><?php echo $row['isactive']; ?></td>
    <td><a href="update.php?id=<?php echo $row['id']; ?>& action=<?php echo $action1; ?>& tname=<?php echo $table; ?>">Activate</a></td>
    <td><a href="update.php?id=<?php echo $row['id']; ?>& action=<?php echo $action2; ?>& tname=<?php echo $table; ?>">Deactivate</a></td>
    <td><a href="update.php?id=<?php echo $row['id']; ?>& action=<?php echo $action3; ?>& tname=<?php echo $table; ?>">Delete</a></td>
  </tr>
  <?php }
 ?>
 <?php
 if(isset($_REQUEST['validate'])!=""){
	 $firstname= $_REQUEST['fname'];
	 $lastname = $_REQUEST['lname'];
	 $faculty=$_REQUEST['course'];
	 
	 $resulta=mysql_query("SELECT * FROM student WHERE fname= '{$firstname}' AND lname= '{$lastname}' AND course=  '{$faculty}'");
	 if(mysql_num_rows($resulta) > 0){
		 echo "
		    <script>
			alert('valid user');
			</script>
		 ";
	 }
	 else
	 {
		 echo "
		    <script>
			alert('Invalid user');
			</script>
		 "; 
	 }
 }
 
?> 
</table>

        </div>
        <div id="content">
        <div class="box">
        <font size="+2"><b>Manage Directory</b></font> /<a href="DownloadManager.php">Manage</a><br>
           <form name="directory" method="post" style="background-color:#9C0; width:250px; margin-left:10px;padding:5px">
            <p>
            <label for="fname"><b>First Name</b></label>
            <input type="text" name="fname" id="fname" placeholder="first name">
            </p><p>
           <label for="lname"><b>Last Name</b></label>
           <input type="text" name="lname" id="textfield2" placeholder="lastname">
           </p><p>
           <label for="fac"><b>Subject</b> </label>&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="text" name="subject" id="textfield2" placeholder="Subject">
           </p><p>
           <label for="phone"><b>Phone No</b></label>&nbsp;
           <input type="text" name="phno" id="textfield3" placeholder="Phone No">
           </p>
           <center>
		<input type="submit" name="addno" value="addno" />
        </center>
        
        </form>
        
        </div>
        <div class="box"><font size="+2"><b>Update NewsFeed</b></font>/<a href="DownloadManager.php">Manage</a><br>
        <form name="newsfeed" method="post" style="background-color:#9C0; width:250px; margin-left:10px;padding:5px">
        <p>
        <label><b>Please Type In Newsevent</b></label><br>
        <textarea rows="9" cols="27" style="overflow:auto" name="newsfeed" placeholder="news event message"></textarea>
       </p>
        <center><input type="submit" name="news" value="Post"/></center><br>
        
        </form>
        </div>
        <div class="box"><font size="+2"><b>Student Validation</b></font>/<a href="studentrecord.php">Manage</a><br>
        <form name="newsfeed" method="post" style="background-color:#9C0; width:250px; margin-left:10px;padding:5px">
          <p>
            <label for="fname"><b>First Name</b></label>
            <input type="text" name="fname" id="fname" placeholder="first name">
          </p>
            <p>
            <label for="fname"><b>Last Name</b></label>
            <input type="text" name="lname" id="fname" placeholder="last name">
            </p>
            <p>
            <label for="fname"><b>Faculty</b></label>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <select name="course">
            <option>B.E-Computer</option>
            <option>B.E-Electronic</option>
            <option>B.E-Civil</option>
            <option>BScCSIT</option>
            <option>BBA</option></select>
            </p>
           <center><input type="submit" value="Check" name="validate"/></center>
           
        </form>
        
        
        </div>
   </div>
    <div id="footer">
    <center><font color="#FFFFFF">

<b>Designed and developed by : Yubaraj Poudel <br>
  Phone no: 9842583634 @
  copyright protected</b>
</font></center></div>
</div>
</body>
</html>

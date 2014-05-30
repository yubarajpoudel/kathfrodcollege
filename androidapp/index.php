<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Untitled Document</title>
<link href="css/main.css" rel="stylesheet" type="text/css"/>
<?php
include('DatabaseHandler.php');
if(isset($_REQUEST['login'])!='')
   {
     if($_REQUEST['adminname']=='' || $_REQUEST['adminpwd']=='')
     {
        echo "please fill the empty field.";
     }
  else
   {
      $result = mysql_query("SELECT fname, password FROM admin");
        while ($row = mysql_fetch_array($result)) {
   
         if($row['fname']==$_REQUEST['adminname'] && $row['password']==$_REQUEST['adminpwd'])
              {	             
			  header("location:mainhome.php");
              }
        }
   }
}
mysql_close($con);
?>
</head>

<body>
<div id="wrapper">

<div id="header"></div>
<div id="container">
<div class="sidebox" style="width:65%" >
<center>
  <h1> Kathfrod ANDROID APP control </h1></center>
 <ul><h2>Instruction</h2>
      <li> Please login first to control</li>
      <li>Provide only valid admin name and password</li>
      <li>Just foolow up the menu once you logged in</li>
      <li>Dont forget to logout once you logged in.</li>
      </ul>
      
     
 
 

</div>
<div class="sidebox" style="background-color:#993;width:33%">
   <center>
   
   <img src="images/homepageimg.jpg" height="140" width="150" style="margin-top:10px"/></center>
     <form method="post" style="padding:10px" >
     <h2>ADMIN LOGIN</h2>
     <p>
     <label>Admin Name</label><br>
     <input type="text" name="adminname" size="30"/>
     </p>
     <p>
     <label>Password</label><br>
     <input type="password" name="adminpwd" size="30"/><br></p>
     
     <input type="submit" value="logIn" name="login"/>
     
            </form>

</div>


</div>
<div id="footer">
<div class="box" style=" width:70px"><a href="http://www.kathford.edu.np/"><img src="images/kathlogo.jpg" height="50" width="70" style="margin-left:10px"/></a></div>
<div class="box" style="width:400px;padding-left:50px">
<center>
<font color="#FF0000">

<b>Designed and developed by : Yubaraj Poudel <br>
  Phone no: 9842583634 @
  copyright protected</b>
</font>
</center>
</div>
</div>

</div>

</body>
</html>

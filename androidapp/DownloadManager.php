<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Untitled Document</title>
<style type="text/css">
	#wrapper {
		margin:0 auto;
		width:800px;
		
	}
    #container {
		width:100%;
		height:370px;
	}
	#header {
		height:150px;
		background-color:#069;
		width:100%;
		background-image:url(images/banner.jpg);
	}
	.box {
		float:left;
		margin-left:10px;
		background-color:#306;
		height:360px;
		margin-top:5px;
	}
	#subcontent1{
		margin-top:5px;
		width:100%;
		height:auto;
		background-color:#960;
	}
	#subcontent2{
		margin-top:5px;
		width:100%;
		height:auto;
		background-color:#960;
	}
	#footer {
		width:100%;
		height:50px;
		margin-top:5px;
		background-color:#0F6;
	}
</style>
<?php
    include('DatabaseHandler.php');?>
    <?php
	if(isset($_REQUEST['download'])){
		//echo "<pre>";print_r($_FILES);die("</pre>");
		//echo json_encode($_POST);exit();
		//die(json_encode($_FILES));
		$catogory=$_REQUEST['catagory'];
		$subject=$_REQUEST['course'];
		$semester =$_REQUEST['semester'];
		$pic=($_FILES['file']['name']);
                $target = $_SERVER[ 'DOCUMENT_ROOT' ]."\downloads\". basename( $_FILES['file']['name']); 
		die(json_encode($target));
		$allowedExts = array(
								  "pdf", 
								  "jpg", 
								  "jpeg"
								 
								); 

		$allowedMimeTypes = array( 
								  'image/jpg',
								  'text/pdf',
								  'image/jpeg',
								  
								);

				$extension = end(explode(".", $_FILES["file"]["name"]));
				
				if (  2097152 < $_FILES["file"]["size"]  ) {
				  die('FILE Is Too Large. Hints max 2Mb');
				}
				
				if ( ! ( in_array($extension, $allowedExts ) ) ) {
				  die('Incorrect File Type');
				}
				 if (file_exists("downloads/" . $_FILES["file"]["name"])) {
                 die( $_FILES["file"]["name"] . " already exists. ");
                 }
		 //Writes the information to the database 
		 $sql="INSERT INTO download(category,faculty,semester,files) VALUES ('$catogory', '$subject', '$semester', '$pic')";
         $result=mysql_query($sql) ; 
		 
		 if(move_uploaded_file($_FILES['file']['tmp_name'], $target)>0&&$result>0) 
                     { 
           echo "<script>alert('The file has been added to the directory');</script>"; 
                  } 
            else { 
                    echo "<script>alert('Sorry, there was a problem uploading your file.');</script>"; 
                   } 
	}
	?>
    
    <?php
	$sql="SELECT * FROM directory";
	$result=mysql_query($sql);
?>
</head>

<body>

<div id="wrapper">
   <div id="header"></div>
   <div id="container">
   		<div class="box" style="width:62%;overflow:scroll">
        <h3 style="color:#FFF">List OF Phone Details Of Kathford Teachers</h3>
         <table border="1" bgcolor="#CC99FF" style="width:100%">
  <tr>
    <th scope="col">ID</th>
    <th scope="col">First Name</th>
    <th scope="col">Last Name</th>
    <th scope="col">Faculty</th>
    <th scope="col">Phone</th>
    <th scope="col">Delete</th>
  </tr>
  <?php
  while($row=mysql_fetch_array($result))
    {
		?>
  <tr>
    <td><?php  echo $row['id']; ?></td>
    <td><?php  echo $row['fname']; ?></td>
    <td><?php  echo $row['lname']; ?></td>
    <td><?php  echo $row['faculty']; ?></td>
    <td><?php  echo $row['phone']; ?></td>
    <td><a href="update.php?id=<?php echo $row['id'];?>&action=<?php echo 'delete';?>&tname=<?php echo 'directory'; ?>">Delete</a></td>
  </tr>
  <?php }?>
 
</table>

        
        </div>
        <div class="box" style="width:35%; height:auto">
   <h3 style="color:#FF0">Update Downloads Here </h3>
           <form method="post"  enctype="multipart/form-data" style="padding:10px;">
        <label><b>Category</b></label><br>
     
        <select name="catagory">
 				<option>IOE RESULT</option>
                <option>Regular Exam Notice</option>
                <option>Back Exam Notice</option>
                <option>Class ROUTINE</option>
                </select>      
        <br/><p>
        <label><b>Faculty</b></label><br>
        <select name="course">
        <option>B.E-Computer</option>
        <option>B.E-Electronic</option>
        <option>B.E-Civil</option>
        <option>BScCSIT</option>
        <option>BBA</option>
        </select></p><p>
        <label><b>Semester</b></label><br>
        <select name="semester">
        <option>I</option>
        <option>II</option>
        <option>III</option>
        <option>IV</option>
        <option>IV</option>
        <option>V</option>
        <option>VI</option>
        <option>VII</option>
        <option>VIII</option>
        </select></p>
        <p>
        <label><b>File</b></label><br>
        
        <input type="file" name="file"/>
        </p>
        <center>
        <input type="submit" name="download" value="Save"><br>
        </center>
        </form>
        
        </div>
   
   </div>
   
   <div id="subcontent1">
   <h3>Manage The News Event</h3>
   <center>
   <table border="1" cellspacing="3" width="80%">
  <tr>
    <th scope="col">Id</th>
    <th scope="col">NewsFeed</th>
    <th scope="col">Posted Date</th>
    <th scope="col">Action</th>
  </tr>
 


   <?php 
   $news ="SELECT * FROM newsfeed";
   $result = mysql_query($news);
   while($rows=mysql_fetch_array($result))
   { ?>
   <tr>
    <td><?php echo $rows['id'];?></td>
    <td><?php echo $rows['news'];?></td>
    <td><?php echo $rows['date'];?></td>
     <td><a href="update.php?id=<?php echo $rows['id']; ?>& action=<?php echo 'delete'; ?>& tname=<?php echo 'newsfeed'; ?>">Delete</a></td>
  </tr>
  <?php
   }
   ?>
   </table>
   </center>
   </div>
   <div id="subcontent2">
      <center>
   <h3> Download file Management</h3>
 
   <table border="1" cellspacing="3" width="80%">
  <tr>
    <th scope="col">Id</th>
    <th scope="col">Thumbnail</th>
    <th scope="col">Category</th>
    <th scope="col">Faculty</th>
    <th scope="col">Semester</th>
    <th scope="col">Files</th>
    <th scope="col">Action</th>
  </tr>
 


   <?php 
   $down ="SELECT * FROM download";
   $result = mysql_query($down);
   while($rows=mysql_fetch_array($result))
   { ?>
   <tr>
    <td><?php echo $rows['id'];?></td>
    <td><?php echo "<img src=downloads/".$rows['files']. " height=\"50px\" width=\"50px\">"; ?></td>
    <td><?php echo $rows['category'];?></td>
    <td><?php echo $rows['faculty'];?></td>
    <td><?php echo $rows['semester'];?></td>
    <td><?php echo $rows['files'];?></td>
    
     <td><a href="update.php?id=<?php echo $rows['id']; ?>& action=<?php echo 'delete'; ?>& tname=<?php echo 'download'; ?>">Delete</a></td>
  </tr>
  <?php
   }
   ?>
   </table>
   </center>
   </div>
      <div id="footer">
    <center><font color="#FFFFFF">

<b>Designed and developed by : Yubaraj Poudel <br>
  Phone no: 9842583634 @
  copyright protected</b>
</font></center>

   
</div></div>
 <?
  mysql_close($con)?>
</body>
</html>
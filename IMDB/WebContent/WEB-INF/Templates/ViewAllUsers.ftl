<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>View users</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>View all users:</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
    <td><b>First Name</b></td>
    <td><b>Last Name</b></td>
    <td><b>User Name</b></td>
  </tr>
 <#list usersList as stu>
  <tr>
    <td>${stu[0]}</td>
    <td>${stu[1]}</td>
    <td>${stu[2]}</td>
    <td><h1>View</h1></td>   
  </tr>
 </#list></table>

  
<p><p>Back to the <a href="index.html"> main window</a>
  
</body>
</html>

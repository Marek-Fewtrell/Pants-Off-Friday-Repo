<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>INB372 - Agile Software Development
Solar Power Calculator Project</title>
</head>

<body>
<br/>!----------Begin Body Area----------!
<br/><h1>ENERGY GENERATION REPORT</h1>
<%
	out.println("Thankyou for using Solar Power Calculator... <br/>");
	out.println("Generating report now...<br/>");
	out.println("Your machine's address is ");
	out.println(request.getRemoteHost());
	java.util.Date date = new java.util.Date();
	out.println("<br/>Report generated on "); 
	out.println(date);
%>
<br/>Insert report here

!----------BeginFooter Area----------!
Insert disclaimer here
!----------End Footer Area----------!
</body>

</html>
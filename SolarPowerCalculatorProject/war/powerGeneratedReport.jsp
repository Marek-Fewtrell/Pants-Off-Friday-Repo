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
	System.out.println("Thankyou for using Solar Power Calculator..."\n);
	System.out.println("Generating report now..."\n);
	System.out.println("Your machine's address is "\n);
	System.out.println(request.getRemoteHost()\n);
	java.util.Date date = new java.util.Date();
	System.out.println("Report generated on "\n);
	System.out.println(date);
%>
<br/>Insert report here

!----------BeginFooter Area----------!
Insert disclaimer here
!----------End Footer Area----------!
</body>

</html>
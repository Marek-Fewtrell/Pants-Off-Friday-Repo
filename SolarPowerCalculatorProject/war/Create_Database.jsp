<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import = "project.server.Database" import = "java.util.Scanner"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Database new_Database = new Database();

Scanner in = new Scanner(System.in);

System.out.println("Please enter the name of the energy supplier");
String supplierName = in.nextLine();
System.out.println("Please enter the Tariff11");
String tariff11 = in.nextLine();
new_Database.setTariff11(supplierName, Double.parseDouble(tariff11));
//new_Database.setTariff11(supplierName, Double.parseDouble(tariff11));
%>
</body>
</html>
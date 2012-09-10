<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import = "project.server.Database" import = "java.util.Scanner" import = "java.util.ArrayList"
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

double shit = new_Database.getTariff11(supplierName);
//System.out.println("Please enter the Tariff11");
//String tariff11 = in.nextLine();
//new_Database.setTariff11(supplierName, Double.parseDouble(tariff11));
ArrayList<String> energyProviders = new_Database.getAllEnergyProviders();

energyProviders = new_Database.getAllEnergyProviders();
for (String bat : energyProviders){
	System.out.println(bat);
}

	System.out.println(shit);
	System.out.println("Please enter the name of the energy supplier");
	supplierName = in.nextLine();
	System.out.println("Please enter the Tariff33");
	String tariff33 = in.nextLine();
	new_Database.setTariff33(supplierName, Double.parseDouble(tariff33), new_Database.getTariff11(supplierName));
//new_Database.setTariff11(supplierName, Double.parseDouble(tariff11));
%>
</body>
</html>
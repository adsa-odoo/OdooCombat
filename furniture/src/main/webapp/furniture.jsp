<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.models.Furniture" %>
<!DOCTYPE html>
<html>
<head>
    <title>Furniture List</title>
</head>
<body>
<h2>Available Furniture</h2>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price per Day</th>
        <th>Availability</th>
    </tr>
    <%
        List<Furniture> furnitureList = (List<Furniture>) request.getAttribute("furnitureList");
        if (furnitureList != null) {
            for (Furniture furniture : furnitureList) {
    %>
    <tr>
        <td><%= furniture.getName() %></td>
        <td><%= furniture.getDescription() %></td>
        <td><%= furniture.getPricePerDay() %></td>
        <td><%= furniture.isAvailable() ? "Yes" : "No" %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>

package com.example.controllers;

import com.example.dao.RentalDao;
import com.example.models.Rental;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/rental")
public class RentalControllers extends HttpServlet {
    private RentalDao rentalDao;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/furniture_rental", "root", "OmPatel@5656");
            rentalDao = new RentalDao(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Error initializing servlet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("user_id"));
            int furnitureId = Integer.parseInt(request.getParameter("furniture_id"));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date rentalDate = dateFormat.parse(request.getParameter("rental_date"));
            Date returnDate = dateFormat.parse(request.getParameter("return_date"));
            
            double totalPrice = Double.parseDouble(request.getParameter("total_price"));
            
            Rental rental = new Rental();
            rental.setUserId(userId);
            rental.setFurnitureId(furnitureId);
            rental.setRentalDate(rentalDate);
            rental.setReturnDate(returnDate);
            rental.setTotalPrice(totalPrice);
            
            boolean success = rentalDao.createRental(rental);
            
            if (success) {
                response.sendRedirect("pages/rental.jsp");
            } else {
                response.sendRedirect("pages/error.jsp");
            }
        } catch (NumberFormatException | ParseException e) {
            throw new ServletException("Error processing rental", e);
        }
    }
}


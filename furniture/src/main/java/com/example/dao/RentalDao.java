package com.example.dao;

import com.example.models.Rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RentalDao {
    private Connection connection;

    public RentalDao(Connection connection) {
        this.connection = connection;
    }

    public boolean createRental(Rental rental) {
        String query = "INSERT INTO rentals (user_id, furniture_id, rental_date, return_date, total_price) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rental.getUserId());
            stmt.setInt(2, rental.getFurnitureId());
            stmt.setDate(3, new java.sql.Date(rental.getRentalDate().getTime()));
            stmt.setDate(4, new java.sql.Date(rental.getReturnDate().getTime()));
            stmt.setDouble(5, rental.getTotalPrice());
            
            int result = stmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            // Consider logging the exception instead of printing stack trace
            e.printStackTrace();
            return false;
        }
    }
}


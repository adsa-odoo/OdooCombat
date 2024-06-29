package com.example.dao;

import com.example.models.Furniture;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FurnitureDao {
    private Connection connection;

    public FurnitureDao(Connection connection) {
        this.connection = connection;
    }

    public List<Furniture> getAllFurniture() {
        List<Furniture> furnitureList = new ArrayList<>();
        String query = "SELECT * FROM furniture";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Furniture furniture = new Furniture();
                furniture.setId(rs.getInt("id"));
                furniture.setName(rs.getString("name"));
                furniture.setDescription(rs.getString("description"));
                furniture.setPricePerDay(rs.getDouble("price_per_day"));
                furniture.setAvailable(rs.getBoolean("available"));
                furnitureList.add(furniture);
            }
            
        } catch (SQLException e) {
            // Consider logging the exception instead of printing stack trace
            e.printStackTrace();
        }
        
        return furnitureList;
    }
}


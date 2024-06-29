package com.example.controllers;

import com.example.dao.FurnitureDao;
import com.example.models.Furniture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/furniture")
public class FurnitureControllers extends HttpServlet {
    private FurnitureDao furnitureDao;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/furniture_rental", "root", "OmPatel@5656");
            furnitureDao = new FurnitureDao(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Error initializing servlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Furniture> furnitureList = null;
        furnitureList = furnitureDao.getAllFurniture();
		request.setAttribute("furnitureList", furnitureList);
		request.getRequestDispatcher("pages/furniture.jsp").forward(request, response);
    }
}

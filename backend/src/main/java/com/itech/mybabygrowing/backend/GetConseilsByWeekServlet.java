package com.itech.mybabygrowing.backend;

import com.google.gson.Gson;
import com.itech.mybabygrowing.backend.models.Conseil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ahmed-PC on 22-05-2015.
 */
public class GetConseilsByWeekServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase dataBase =new DataBase() ;
        String week = req.getParameter("week") ;
        Conseil conseil =dataBase.getConseilByWeek(week);
        Gson gson =new Gson() ;
        resp.getWriter().print(gson.toJson(conseil)) ;
    }
}

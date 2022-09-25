package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.appline.logic.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calculate")
public class ServletCalculator extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
        request.setCharacterEncoding("UTF-8");
        Double first_number = jobj.get("first_number").getAsDouble();
        String operation =  jobj.get("operation").getAsString();
        Double second_number = jobj.get("second_number").getAsDouble();
        Double result = 0.0;

        if (operation.equals("+")) {
            result = first_number + second_number;
        } else if (operation.equals("-")) {
            result = first_number - second_number;
        } else if (operation.equals("*")) {
            result = first_number * second_number;
        } else if (operation.equals("/")) {
            result = first_number/second_number;
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(result));
    }
}

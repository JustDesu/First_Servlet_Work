package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {
    Model model = Model.getInstance();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        int id = jobj.get("id").getAsInt();
        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        Double salary = jobj.get("salary").getAsDouble();
        User user = new User(name, surname, salary);
        model.update(model.getFromList().get(id), id, user);
        response.setContentType("text/html:charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print("<html>" +
                "<h3>Пользователь: " + model.getFromList().get(id).getName() + " " + model.getFromList().get(id).getSurname() + " с зарплатой: " + model.getFromList().get(id).getSalary() + " успешно обновлен</h3>" +
                "<a href=\"index.jsp\">Домой</a>" +
                "</html>");
    }*/
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        int id = jobj.get("id").getAsInt();
        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        Double salary = jobj.get("salary").getAsDouble();
        User user = new User(name, surname, salary);
        model.update(model.getFromList().get(id), id, user);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(model.getFromList()));
    }

}

package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.appline.logic.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {
    Model model = Model.getInstance();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        if (id > 0 && id <= model.getFromList().size()) {
            model.remove(model.getFromList().get(id), id);
            pw.print(gson.toJson(model.getFromList()));
        } else {
            pw.print(gson.toJson("Такого ID не существует"));
        }



    }
}

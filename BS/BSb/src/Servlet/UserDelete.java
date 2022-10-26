package Servlet;

import com.alibaba.fastjson.JSON;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebServlet("/delete")
public class UserDelete extends HttpServlet {

    public static UserService us=new UserService();//初始化服务层对象
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id=request.getParameter("id");
        int i=us.deleteUser(id);
        response.setContentType("text/html;charset=UTF-8");
        String userJson = JSON.toJSONString(i);
        OutputStream out = response.getOutputStream();
        out.write(userJson.getBytes("UTF-8"));
        out.flush();
    }
}
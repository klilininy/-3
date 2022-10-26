package Servlet;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insert")
public class UserInsert extends HttpServlet{

    public static UserService us=new UserService();//初始化服务层对象
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String adress = request.getParameter("adress");
        String phone=request.getParameter("phone");
        System.out.println(name);
        int i=us.insertUser(name,adress,phone);
        System.out.println(i);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("插入成功");
    }
}

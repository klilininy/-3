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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/select")
public class UserSelect extends HttpServlet {
    public static UserService us=new UserService();//初始化服务层对象
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ResultSet rs=us.selectAll();
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        ArrayList<User> users=new ArrayList<User>();
        try {
            while (rs.next()) {
                users.add(new User(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("adress"),
                        rs.getString("phone")));
            }
        }catch (Exception ea){}
        String userJson = JSON.toJSONString(users);
        OutputStream out = response.getOutputStream();
        out.write(userJson.getBytes("UTF-8"));
        out.flush();
    }
}
class User{
    public String id;
    public String name;
    public String adress;
    public String phone;
    public User(String id,String name,String adress,String phone){
        this.id=id;
        this.name=name;
        this.adress=adress;
        this.phone=phone;
    }
}
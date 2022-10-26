package service;
import java.sql.*;
import util.MySqlUtil;

import javax.swing.*;

public class UserService {
    public static MySqlUtil msql=new MySqlUtil();//初始化工具对象
    public ResultSet selectAll(){
        ResultSet list = msql.mySelect("select * from app_opl");
        return list;
    }
    public int deleteUser(String da){
        int i= msql.myUpdate("delete from app_opl where id =\""+da+"\"");
        return i;
    }
    public int insertUser(String name,String adress,String phone){
        int i= msql.myUpdate("insert into app_opl(name,adress,phone)values(\""+name
                +"\",\""+adress+"\",\""+phone+"\")");
        return i;
    }
    public int updateUser(String name,String adress,String phone,String id){
        int i= msql.myUpdate("update app_opl set "+
                "name=\""+name +"\","+
                "adress=\""+adress +"\","+
                "phone=\""+phone +"\" "+
                "where id = "+id);
        return i;
    }
}

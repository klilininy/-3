import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class app {
    public static MySqlUtil msql=new MySqlUtil();//初始化工具对象
    static {
        JFrame frame = new JFrame("个人通讯录");
        JPanel panel = new JPanel();
        JButton button1 = new JButton("添加");
        JButton button2 = new JButton("修改");
        JButton button3 = new JButton("查询");
        JButton button4 = new JButton("删除");

        JTextArea ta=new JTextArea(20 ,50);
        Container container=frame.getContentPane();
        container.add(ta);
        JScrollPane jScrollPane = new JScrollPane(ta);
        container.add(jScrollPane);//放置数据区

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(200, 200, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //按钮事件
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet list = msql.mySelect("select * from app_opl");
                try {
                    ta.setText("");
                    while (list.next()) {
                        ta.append("序号： "+list.getString("id")+" | "
                                +"姓名： "+list.getString("name")+" | "
                                +"地址： "+list.getString("adress")+" | "
                                +"电话： "+list.getString("phone")
                                + "\n");
                    }
                    JOptionPane.showMessageDialog(frame, "查询成功!");
                }catch (Exception ea){

                }
                }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cdlog();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cdlog2();
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dialog = JOptionPane.showInputDialog(frame, "请输入需要删除数据的序号！");
                int da = Integer.parseInt(dialog);
                    try {
                        int i= msql.myUpdate("delete from app_opl where id =\""+da+"\"");
                        if(i>0)JOptionPane.showMessageDialog(frame, "删除成功!");
                    } catch (Exception ea){
                    }
            }
        });
        //按钮事件
    }

    public static void Cdlog() {
        JFrame jFrame = new JFrame("添加新联系人");
        jFrame.setBounds(400, 400, 300, 300);
        JTextField textField1 = new JTextField(20);
        textField1.setText("请输入姓名");
        JTextField textField2 = new JTextField(20);
        textField2.setText("请输入电话");
        JTextField textField3 = new JTextField(20);
        textField3.setText("请输入住址");
        JPanel panel1 = new JPanel(new GridLayout(8, 1));//实例三个文本框
        panel1.add(textField1);
        panel1.add(textField2);
        panel1.add(textField3);
        JButton button = new JButton("添加");
        JPanel panel = new JPanel();
        panel.add(button);
        jFrame.add(panel1);
        jFrame.add(panel, BorderLayout.NORTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   int i= msql.myUpdate("insert into app_opl(name,adress,phone)values(\""+textField1.getText()
                            +"\",\""+textField2.getText()+"\",\""+textField3.getText()+"\")");
                   if(i>0)JOptionPane.showMessageDialog(jFrame, "添加成功!");
                } catch (Exception ea) {
                }
            }
        });
        jFrame.setResizable(false);
        jFrame.setVisible(true);

    }
    public static void Cdlog2() {
        JFrame jFrame = new JFrame("修改联系人信息");
        jFrame.setBounds(600, 200, 300, 400);
        JTextField textField1 = new JTextField(20);
        textField1.setText("请输入要修改的序号");
        JTextField textField2 = new JTextField(20);
        textField2.setText("请输入修改后的姓名");
        JTextField textField3 = new JTextField(20);
        textField3.setText("请输入修改后的电话");
        JTextField textField4 = new JTextField(20);
        textField4.setText("请输入修改后的住址");
        JPanel panel1 = new JPanel(new GridLayout(8, 1));
        panel1.add(textField1);
        panel1.add(textField2);
        panel1.add(textField3);
        panel1.add(textField4);
        JButton button = new JButton("提交");
        JPanel panel = new JPanel();
        panel.add(button);
        jFrame.add(panel1);
        jFrame.add(panel, BorderLayout.NORTH);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i= msql.myUpdate("update app_opl set "+
                                    "name=\""+textField2.getText() +"\","+
                                    "adress=\""+textField3.getText() +"\","+
                                    "phone=\""+textField4.getText() +"\" "+
                                    "where id = "+textField1.getText());
                    if(i>0)JOptionPane.showMessageDialog(jFrame, "修改成功!");
                } catch (Exception ea) {
                }
            }
        });
        jFrame.setResizable(false);
        jFrame.setVisible(true);

    }
    public static void main(String[] args){
    }
}

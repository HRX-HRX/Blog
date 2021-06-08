package service;

import dao.UserInfoDao;
import models.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        int state=-1;
        String msg="";
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        PrintWriter writer=response.getWriter();

        if(username==null||password==null){
            msg="参数不正确";

        }else {
            UserInfo userInfo=new UserInfo();
            userInfo.setUsername(username);
            userInfo.setPassword(password);
            UserInfoDao userInfoDao=new UserInfoDao();
            try{
                boolean res=userInfoDao.add(userInfo);
                if(res){
                    state=200;
                }else {
                    state=100;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        writer.println("{\"state\":"+state+",\"msg\":\""+msg+"\"}");
    }
}

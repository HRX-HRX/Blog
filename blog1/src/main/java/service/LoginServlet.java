package service;

import dao.UserInfoDao;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
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
                state=userInfoDao.isLogin(userInfo)?200:100;
                userInfo = userInfoDao.getUserInfo(userInfo);
                if (userInfo.getId() >= 1) {
                    // 表示登录成功
                    state = 200;
                    // 如果登录成功，那么需要创建一个 session 信息
                    HttpSession session = request.getSession();
                    // 将当前用户存到 session
                    session.setAttribute("userinfo", userInfo);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        HashMap<String,Object> result=new HashMap<>();
        result.put("state",state);
        result.put("msg",msg);
        ResultJSONUtils.writeMap(response,result);

    }
}

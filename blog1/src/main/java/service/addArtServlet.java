package service;

import dao.ArtInfoDao;
import models.ArticleInfo;
import models.UserInfo;
import utils.DBUtils;
import utils.ResultJSONUtils;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class addArtServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        int succ=-1;
        String msg="";
        if(title!=null&&content!=null&&
            !title.equals("")&&!content.equals("")){
            HttpSession session=request.getSession(false);
            if(session!=null&&session.getAttribute("userinfo")!=null){
                UserInfo userInfo=(UserInfo)session.getAttribute("userinfo");
                ArtInfoDao artInfoDao=new ArtInfoDao();
                int uid=userInfo.getId();
                try {
                    succ=artInfoDao.add(title,content,uid);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else{
                msg="非法请求";
            }

        }else{
            msg="非法请求";

        }
        HashMap<String,Object> re=new HashMap<>();
        re.put("succ",succ);
        re.put("msg",msg);
        ResultJSONUtils.writeMap(response,re);
    }
}

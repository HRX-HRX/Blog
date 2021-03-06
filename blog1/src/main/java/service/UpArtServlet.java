package service;

import dao.ArtInfoDao;
import models.ArticleInfo;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class UpArtServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        int id=Integer.parseInt(request.getParameter("id"));
        int succ=-1;
        String msg="";
        if(title!=null&&content!=null&&
                !title.equals("")&&!content.equals("")){
            HttpSession session=request.getSession(false);
            if(session!=null&&session.getAttribute("userinfo")!=null){

               /* ArticleInfo articleInfo=(ArticleInfo)session.getAttribute("articleinfo");
                int id=articleInfo.getId();*/
                ArtInfoDao artInfoDao=new ArtInfoDao();
                try {
                    int k=artInfoDao.update(title,content,id);
                    succ=k;

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

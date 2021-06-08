package service;

import dao.ArtInfoDao;
import models.ArticleInfo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class MyDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取前端参数
        int succ=-1;
        String msg="";
        int id=Integer.parseInt(request.getParameter("id"));
        if(id>=0){
            //2.业务处理

            ArtInfoDao artInfoDao=new ArtInfoDao();
            try{
                int res=artInfoDao.delArtById(id);
                if(res>=0){
                    succ=1;
                   // msg="删除成功";
                }else {

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            msg="参数无效";
        }


        //3.将结果返回前端

        HashMap<String,Object> re=new HashMap<>();
        re.put("succ",succ);
        re.put("msg",msg);
        ResultJSONUtils.writeMap(response,re);

    }
}

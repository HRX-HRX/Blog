package service;

import dao.ArtInfoDao;
import models.ArticleInfo;
import models.vo.ArticleInfoVo;
import org.omg.PortableInterceptor.INACTIVE;
import utils.DBUtils;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArtInfoDao dao=new ArtInfoDao();
        int succ=-1;
        String msg="";
        List<ArticleInfoVo>  list=null;
        int cpage=Integer.parseInt(request.getParameter("cpage"));
        int psize= Integer.parseInt(request.getParameter("psize"));
        try {
            //list=dao.getList();
            list=dao.getListByPage(cpage,psize);
            succ=1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        HashMap<String,Object> re=new HashMap<>();
        re.put("succ",succ);
        re.put("msg",msg);
        re.put("list",list);
        ResultJSONUtils.writeMap(response,re);
    }
}

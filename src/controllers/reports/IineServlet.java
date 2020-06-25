package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class IineServlet
 */
@WebServlet("/Iine")
public class IineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

          EntityManager em =DBUtil.createEntityManager();
          //リクエストパラメーターの取得
          Report r = em.find(Report.class,Integer.parseInt(request.getParameter("id")));
          if(r.getGood() == null) {
              r.setGood(1);
          } else {
              r.setGood(r.getGood() +1);
          }

                //データベースの更新
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                //indexページへリダイレクト
                response.sendRedirect(request.getContextPath()+"/reports/index");
            }
        }

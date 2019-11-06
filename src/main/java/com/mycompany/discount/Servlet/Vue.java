/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.discount.Servlet;

import com.mycompany.discount.JDBC.CodeEntity;
import com.mycompany.discount.JDBC.DAO;
import com.mycompany.discount.JDBC.DataSourceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedago
 */
@WebServlet(name = "Vue", urlPatterns = {"/Vue"})
public class Vue extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new DAO(DataSourceFactory.getDataSource());
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Discount Codes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form>");
            out.println("Code : ");
            out.println("<input name='code'> <br>");
            out.println("Taux");
            out.println("<input name='taux'> <br>");
            out.println("<input type='submit' value='Ajouter'>");
            out.println("<table border=3>");
            out.println("<tr> <th>Code</th> <th>Taux</th> </tr>");
            try{
                String val = request.getParameter("code");
                String val2 = request.getParameter("taux");
                out.printf("%s <br> %s", val,val2);
                dao.addCode(val, Float.parseFloat(val2));
                List<CodeEntity> ce = dao.listingCode();
                for(CodeEntity c : ce){
                    out.printf("<tr> <td>%s</td> <td>%f</td> </tr>",
                            c.code,
                            c.rate);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Vue.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

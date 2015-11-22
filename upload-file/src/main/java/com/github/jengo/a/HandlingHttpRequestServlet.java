package com.github.jengo.a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jengowang on 15/11/20.
 */
public class HandlingHttpRequestServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(HandlingHttpRequestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().write("doGet()");
        LOG.info("doGet()");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username"); //获取username所对应的value
        String password = req.getParameter("password"); //获取password所对应的value

        LOG.info("The received username={} and password={}", username, password);

        // 向请求端发回反馈信息
        PrintWriter out = resp.getWriter();
        out.print("doPost()");
        out.flush();
        out.close();
    }
}

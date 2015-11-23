package com.github.jengo.b;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by jengowang on 15/11/22.
 */
public class HandlingHttpRequestServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(HandlingHttpRequestServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().write("SUCCESS");
        LOG.info("SUCCESS");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //得到绝对文件夹路径，比如"D:\\Tomcat6\\webapps\\test\\upload"
        String path = req.getRealPath("/upload");
        LOG.info("path={}", path);
        //临时文件夹路径
        String repositoryPath = req.getRealPath("/upload/temp");
        LOG.info("repositoryPath={}", repositoryPath);
        //设定临时文件夹为repositoryPath
        factory.setRepository(new File(repositoryPath));
        //设定上传文件的阈值，如果上传文件大于1M，就可能在repository
        //所代表的文件夹中产生临时文件，否则直接在内存中进行处理
        factory.setSizeThreshold(1024 * 1024);

        //得到相对文件夹路径，比如 "/test"
        LOG.info("contextPath={}", req.getContextPath());
        //创建一个ServletFileUpload对象
        ServletFileUpload uploader = new ServletFileUpload(factory);

        try {
            //调用uploader中的parseRequest方法，
            //可以获得请求中的相关内容，
            //即一个FileItem类型的ArrayList。
            //FileItem是在org.apache.commons.fileupload中定义的，
            //它可以代表一个文件，也可以代表一个普通的form field
            ArrayList<FileItem> list = (ArrayList<FileItem>) uploader.parseRequest(req);

            LOG.info("request.size={}", list.size());

            for (FileItem fileItem : list) {
                if (fileItem.isFormField()) { //如果是普通的form field
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString();
                    LOG.info("fileItem.getFieldName()={}", name);
                    LOG.info("fileItem.getString()={}", value);
                } else { //如果是文件
                    String value = fileItem.getName();
                    LOG.info("fileItem.getName()={}", value);
                    int start = value.lastIndexOf("/");
                    String fileName = value.substring(start + 1);
                    LOG.info("fileName={}", fileName);
                    //将其中包含的内容写到path(即upload目录)下名为fileName的文件中
                    fileItem.write(new File(path, fileName));
                }
            }
        } catch (Exception e) {
            LOG.error("upload文件异常", e);
        }

        // 向客户端反馈结果
        PrintWriter out = resp.getWriter();
        out.print("SUCCESS");
        out.flush();
        out.close();
    }
}

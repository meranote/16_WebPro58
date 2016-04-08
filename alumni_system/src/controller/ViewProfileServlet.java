package controller;

import annotation.auth.AuthGuard;
import exception.NoUserFoundException;
import model.Alumni;
import model.Staff;
import model.Teacher;
import model.User;
import model.auth.Authorization;
import model.utility.ResponseCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by meranote on 4/6/2016 AD.
 */
@WebServlet(name = "ViewProfileServlet", urlPatterns = {"/profile", "/profile/*"})
@AuthGuard
public class ViewProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Authorization auth = Authorization.getAuthInstance(session);

        User user = null;

        if(request.getRequestURI().endsWith("/profile") || request.getRequestURI().endsWith("/profile/") ) {
            user = auth.getCurrentUser();
        } else {
            String uri = request.getRequestURI();
            if(!uri.endsWith("/")) uri += "/";
            String[] splits = uri.split("/");

            if(splits[splits.length - 2].equals("profile")) {
                try {
                    user = User.getUserByID(Integer.parseInt(splits[splits.length - 1]));
                } catch (Exception ex) {
                    try {
                        user = User.getUserByUsername(splits[splits.length - 1]);
                    } catch (NoUserFoundException ex2) {
                        ResponseCodeUtils.pushRequestCode(request, ResponseCodeUtils.NO_USER_MODEL_FOUND);
                        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
                        return;
                    }
                }
            }
        }

        request.setAttribute("user", user);
        switch (user.getType()) {
            case ALUMNI: request.setAttribute("alumni", Alumni.getAlumniByUserId(user.getId())); break;
//            case TEACHER: request.setAttribute("teacher", Teacher.getTeacherByUserId(user.getId())); break;
//            case STAFF: request.setAttribute("staff", Staff.getStaffByUserId(user.getId())); break;
        }

        if(ResponseCodeUtils.hasCodeInSession(session)) {
            ResponseCodeUtils.pushRequestCode(request, ResponseCodeUtils.pullSessionCode(session));
        }

        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}

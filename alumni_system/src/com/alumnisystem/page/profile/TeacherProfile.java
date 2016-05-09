package com.alumnisystem.page.profile;

import com.alumnisystem.database.Database;
import com.alumnisystem.factory.TeacherFactory;
import com.alumnisystem.model.Teacher;
import com.alumnisystem.model.User;
import com.alumnisystem.utility.Authorization;
import com.alumnisystem.utility.RouteUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by meranote on 4/10/2016 AD.
 */
public class TeacherProfile extends SimpleTagSupport {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void doTag() throws JspException, IOException {
        HttpServletRequest request = (HttpServletRequest)((PageContext) getJspContext()).getRequest();

        TeacherFactory teacherFactory = new TeacherFactory(Database.getConnection(request));

        Teacher teacher = teacherFactory.findByUserId(user.getId());
        User currentUser = Authorization.getAuthInstance(request).getCurrentUser();

        boolean editable = (currentUser.isAdmin() ||
                (currentUser.getType() == User.Type.TEACHER && teacher.getTeacher_id() == teacherFactory.findByUserId(currentUser.getId()).getTeacher_id()));

        JspWriter out = getJspContext().getOut();
        out.println(
                "<h2>ประวัติส่วนตัว</h2>\n" +
                "<form action=\"" + RouteUtils.generateURL(request, "profile/edit") + "\" method=\"POST\" id=\"teacher-form\" class=\"form-horizontal\">\n" +
                "<input type=\"hidden\" id=\"profilepage-usertype\" name=\"profilepage-usertype\" value=\"" + user.getType() + "\"/>\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"teacher-form-id\" class=\"col-md-3 control-label\">รหัส</label>\n" +
                "<div class=\"col-md-9\">\n" +
                "<input type=\"text\" class=\"form-control\" name=\"teacher-form-id\" id=\"teacher-form-id\" placeholder=\"ID\" value=\"" + teacher.getTeacher_id() + "\" data-lock=\"true\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"teacher-form-pnameth\" class=\"col-md-3 control-label\">คำนำหน้าชื่อ (ภาษาไทย)*</label>\n" +
                "<div class=\"col-md-9\">\n" +
                "<input type=\"text\" class=\"form-control\" name=\"teacher-form-pnameth\" id=\"teacher-form-pnameth\" placeholder=\"Title (TH)\" value=\"" + (teacher.getPname_th() == null ? "" : teacher.getPname_th()) + "\" data-empty=\"false\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"teacher-form-fnameth\" class=\"col-md-3 control-label\">ชื่อ (ภาษาไทย)*</label>\n" +
                "<div class=\"col-md-9\">\n" +
                "<input type=\"text\" class=\"form-control\" name=\"teacher-form-fnameth\" id=\"teacher-form-fnameth\" placeholder=\"Name (TH)\" value=\"" + (teacher.getFname_th() == null ? "" : teacher.getFname_th()) + "\" data-empty=\"false\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"teacher-form-lnameth\" class=\"col-md-3 control-label\">นามสกุล (ภาษาไทย)*</label>\n" +
                "<div class=\"col-md-9\">\n" +
                "<input type=\"text\" class=\"form-control\" name=\"teacher-form-lnameth\" id=\"teacher-form-lnameth\" placeholder=\"Surname (TH)\" value=\"" + (teacher.getLname_th() == null ? "" : teacher.getLname_th()) + "\" data-empty=\"false\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"teacher-form-pnameen\" class=\"col-md-3 control-label\">คำนำหน้าชื่อ (ภาษาอังกฤษ)</label>\n" +
                "<div class=\"col-md-9\">\n" +
                "<input type=\"text\" class=\"form-control\" name=\"teacher-form-pnameen\" id=\"teacher-form-pnameen\" placeholder=\"Title (EN)\" value=\"" + (teacher.getPname_en() == null ? "" : teacher.getPname_en()) + "\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"teacher-form-fnameen\" class=\"col-md-3 control-label\">ชื่อ (ภาษาอังกฤษ)</label>\n" +
                "<div class=\"col-md-9\">\n" +
                "<input type=\"text\" class=\"form-control\" name=\"teacher-form-fnameen\" id=\"teacher-form-fnameen\" placeholder=\"Name (EN)\" value=\"" + (teacher.getFname_en() == null ? "" : teacher.getFname_en()) + "\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"teacher-form-lnameen\" class=\"col-md-3 control-label\">นามสกุล (ภาษาอังกฤษ)</label>\n" +
                "<div class=\"col-md-9\">\n" +
                "<input type=\"text\" class=\"form-control\" name=\"teacher-form-lnameen\" id=\"teacher-form-lnameen\" placeholder=\"Surname (EN)\" value=\"" + (teacher.getLname_en() == null ? "" : teacher.getLname_en()) + "\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"teacher-form-email\" class=\"col-md-3 control-label\">อีเมลล์</label>\n" +
                "<div class=\"col-md-9\">\n" +
                "<input type=\"text\" class=\"form-control\" name=\"teacher-form-email\" id=\"teacher-form-email\" placeholder=\"Email\" value=\"" + (teacher.getEmail() == null ? "" : teacher.getEmail()) + "\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"teacher-form-phone\" class=\"col-md-3 control-label\">เบอร์โทรศัพท์</label>\n" +
                "<div class=\"col-md-9\">\n" +
                "<input type=\"text\" class=\"form-control\" name=\"teacher-form-phone\" id=\"teacher-form-phone\" placeholder=\"Phone number\" value=\"" + (teacher.getPhone() == null ? "" : teacher.getPhone()) + "\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"teacher-form-workstatus\" class=\"col-md-3 control-label\">สถานะ</label>\n" +
                "<div class=\"col-md-9\">\n" +
                "<select class=\"form-control\" name=\"teacher-form-workstatus\" id=\"teacher-form-workstatus\" data-default=\"" + teacher.getWork_status() + "\">\n" +
                "<option value=\"EMPLOYEE\">พนักงาน</option>\n" +
                "<option value=\"OFFICIAL\">ข้าราชการ</option>\n" +
                "</select>\n" +
                "</div>\n" +
                "</div>"
        );
        if(editable) {
            out.println(
                    "<div class=\"form-group\">\n" +
                    "<div class=\"col-sm-offset-3 col-sm-9\">\n" +
                    "<button type=\"button\" id=\"teacher-form-btn\" class=\"btn btn-primary\">แก้ไข</button>\n" +
                    "</div>\n" +
                    "</div>"
            );
        }
        out.println("</form>");
    }

}
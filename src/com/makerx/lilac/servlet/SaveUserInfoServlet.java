package com.makerx.lilac.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.makerx.lilac.entity.UserInfo;
import com.makerx.lilac.mysql.DBObject;

public class SaveUserInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String codeId = req.getParameter("id") == null ? null : req
				.getParameter("id").toString();
		if (codeId != null) {
			UserInfo userInfo = new UserInfo();
			userInfo.setColleage(req.getParameter("colleage") == null ? ""
					: req.getParameter("colleage").toString());
			userInfo.setCompany(req.getParameter("company") == null ? "" : req
					.getParameter("company").toString());
			userInfo.setEntranceTime(req.getParameter("entranceTime") == null ? ""
					: req.getParameter("entranceTime").toString());
			userInfo.setName(req.getParameter("name") == null ? "" : req
					.getParameter("name").toString());
			userInfo.setTelephone(req.getParameter("telephone") == null ? ""
					: req.getParameter("telephone").toString());
			DBObject dbObject = DBObject.instance();
			int result = dbObject.saveUserInfo(userInfo, codeId);

			if (result == 0) {

				req.setAttribute("colleage", userInfo.getColleage());
				req.setAttribute("company", userInfo.getCompany());
				req.setAttribute("entranceTime", userInfo.getEntranceTime());
				req.setAttribute("name", userInfo.getName());
				req.setAttribute("telephone", userInfo.getTelephone());
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("show.jsp");
				dispatcher.forward(req, resp);

			} else {
				resp.setContentType("text/plain");
				resp.getWriter().println("save fail: result is " + result);
			}
		} else {
			resp.setContentType("text/plain");
			resp.getWriter().println("qrcode error");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doGet(req,resp);
	}
}

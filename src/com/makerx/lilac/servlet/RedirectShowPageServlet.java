package com.makerx.lilac.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.makerx.lilac.entity.UserInfo;
import com.makerx.lilac.mysql.DBObject;

public class RedirectShowPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String codeId = req.getParameter("id") == null ? null : req
				.getParameter("id").toString();
		if (codeId != null) {

			DBObject dbObject = DBObject.instance();
			UserInfo userInfo = dbObject.retrieveUserInfo(codeId);
			req.setAttribute("id", codeId);
			RequestDispatcher dispatcher = null;
			if (userInfo == null) {

				dispatcher = req.getRequestDispatcher("index.jsp");
			} else {
				req.setAttribute("colleage", userInfo.getColleage());
				req.setAttribute("company", userInfo.getCompany());
				req.setAttribute("entranceTime", userInfo.getEntranceTime());
				req.setAttribute("name", userInfo.getName());
				req.setAttribute("telephone", userInfo.getTelephone());
				dispatcher = req.getRequestDispatcher("show.jsp");
			}
			dispatcher.forward(req, resp);

		} else {
			resp.setContentType("text/plain");
			resp.getWriter().println("code id :" + req.getParameter("id"));
		}

	}

}

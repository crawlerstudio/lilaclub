package com.makerx.lilac.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.makerx.lilac.entity.InitInfo;
import com.makerx.lilac.mysql.DBObject;

public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		InitInfo info = new InitInfo();

		info.setNfcid(req.getParameter("nfcid").toString());
		info.setSn(req.getParameter("sn").toString());
		info.setCodeid(req.getParameter("codeid").toString());

		DBObject db = DBObject.instance();
		int result = 0;
		result = db.initCard(info);

		if (result != 0) {
			
			throw new Error("save fail: result is " + result+"\n"+"// return 0 = success	// 1 = qrcode exist	// 2 = insert fail");
		}
	}
}

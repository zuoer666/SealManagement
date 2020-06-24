package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clas.DingDan;
import com.service.Service;

public class test extends HttpServlet  {
	private static final long serialVersionUID = -4415294210787731608L;

	/**
	 * The doGet method of the Server let.
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// ������Ϣ
		String chaxunshangji = request.getParameter("chaxunshangji");
		System.out.println("-- chaxunshangji:" +chaxunshangji);

		// �½��������
		Service serv = new Service();
		
		
		// ��֤����
//		boolean loged = serv.shifoucunzaishangji(chaxunshangji);
//		if (loged) {
//			System.out.print("shangjizuncai");
//			//request.getSession().setAttribute("username", username);
//			
//			out.print("shangjizuncai");
//			// response.sendRedirect("welcome.jsp");
//		} else {
//			System.out.print("shangjibucunzai");
//			out.print("shangjibucunzai");
//		}
		out.print("fuwuqi zhengchang");
		 // ������Ϣ
//		 response.setCharacterEncoding("UTF-8");
//		 response.setContentType("text/html");
//		 PrintWriter out = response.getWriter();
//		 out.print("�û�����" + username);
//		 out.print("���룺" + password);
		 out.flush();
		 out.close();

	}

	/**
	 * The doPost method of the Server let.
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ����Ϊ���ֻ����������룬��������
		// System.out.println("u1--"+username);
		// System.out.println("u2--"+username);

	}

}

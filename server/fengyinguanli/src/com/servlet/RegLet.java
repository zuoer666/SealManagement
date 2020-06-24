package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

public class RegLet extends HttpServlet {

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
		String r_username = request.getParameter("r_username");
		//fabu_user = new String(fabu_user.getBytes("ISO-8859-1"), "UTF-8");
		String r_name = request.getParameter("r_name");
		//fabu_title = new String(fabu_title.getBytes("ISO-8859-1"), "UTF-8");
		String r_zhiwu = request.getParameter("r_zhiwu");
		//fabu_content = new String(fabu_content.getBytes("ISO-8859-1"), "UTF-8");
		String r_shangji = request.getParameter("r_shangji");
		//fabu_money = new String(fabu_money.getBytes("ISO-8859-1"), "UTF-8");
		String r_pwd = request.getParameter("r_pwd");
		//fabu_time = new String(fabu_time.getBytes("ISO-8859-1"), "UTF-8");
		String r_quanxian = request.getParameter("r_quanxian");
		System.out.println("-- r_username:" +r_username + "-- r_name:" + r_name + "--r_zhiwu:"+ r_zhiwu+ "-- r_shangji:" +r_shangji + "-- r_pwd:" + r_pwd + "--r_quanxian:"+ r_quanxian);

		// �½��������?
		Service serv = new Service();
		
		
		// ��֤����
		boolean loged = serv.register(r_username,r_name, r_zhiwu,r_shangji,r_pwd,r_quanxian);
		if (loged) {
			System.out.print("Succss");
			//request.getSession().setAttribute("username", username);
			
			out.print("11");
			// response.sendRedirect("welcome.jsp");
		} else {
			System.out.print("Failed");
			out.print("22");
		}

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
		this.doGet(request, response); 
	}
}
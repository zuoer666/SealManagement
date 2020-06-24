package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

public class FengYinChaiChu extends HttpServlet {

	private static final long serialVersionUID = -4415294210787731608L;

	/**
	 * The doGet method of the Server let.
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 锟斤拷锟斤拷锟斤拷息
		String fengyinid = request.getParameter("fengyinid");
		//fabu_user = new String(fabu_user.getBytes("ISO-8859-1"), "UTF-8");
		String dianbiaoid = request.getParameter("dianbiaoid");
		//fabu_title = new String(fabu_title.getBytes("ISO-8859-1"), "UTF-8");
		String chaichurenrenid = request.getParameter("chaichurenrenid");
		//fabu_content = new String(fabu_content.getBytes("ISO-8859-1"), "UTF-8");
		String chaichushijian = request.getParameter("chaichushijian");
		System.out.println("-- fengyinid:" +fengyinid + "-- dianbiaoid:" + dianbiaoid + "--chaichurenrenid:"+ chaichurenrenid+ "-- chaichushijian:" +chaichushijian);

		// 锟铰斤拷锟斤拷锟斤拷锟斤拷锟�
		Service serv = new Service();
		
		
		// 锟斤拷证锟斤拷锟斤拷
		boolean loged = serv.fengyinchaichu(fengyinid,dianbiaoid, chaichurenrenid,chaichushijian);
		if (loged) {
			System.out.print("Succss");
			//request.getSession().setAttribute("username", username);
			
			out.print("11");
			// response.sendRedirect("welcome.jsp");
		} else {
			System.out.print("Failed");
			out.print("22");
		}

		 // 锟斤拷锟斤拷锟斤拷息
//		 response.setCharacterEncoding("UTF-8");
//		 response.setContentType("text/html");
//		 PrintWriter out = response.getWriter();
//		 out.print("锟矫伙拷锟斤拷锟斤拷" + username);
//		 out.print("锟斤拷锟诫：" + password);
		 out.flush();
		 out.close();

	}

	/**
	 * The doPost method of the Server let.
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 锟斤拷锟斤拷为锟斤拷锟街伙拷锟斤拷锟斤拷锟斤拷锟斤拷锟诫，锟斤拷锟斤拷锟斤拷锟斤拷
		// System.out.println("u1--"+username);
		// System.out.println("u2--"+username);
		this.doGet(request, response); 
	}
}

package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clas.GetBasicInfoClass;
import com.service.Service;

public class GetBasicInfo extends HttpServlet {
	private static final long serialVersionUID = 369840050351775312L;

	/**
	 * The doGet method of the Server let.
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		try{
			PrintWriter out = response.getWriter();
			
			String nameString = request.getParameter("username");

			Service serv = new Service();
			List<GetBasicInfoClass> dingdans = serv.getbasicinfo(nameString);
			StringBuffer sb = new StringBuffer();
			sb.append('[');
			for(GetBasicInfoClass dingdan : dingdans){
				sb.append('{').append("\"name\":").append("\""+dingdan.getName()+"\"").append(",");
				sb.append("\"banzu\":").append("\""+dingdan.getBanzu()+"\"").append(",");
				sb.append("\"quanxian\":").append("\""+dingdan.getQuanxian()+"\"");
				sb.append('}').append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append(']');
			String a = new String(sb);		
			out.print(a);
    		out.flush();
			out.close();
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
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


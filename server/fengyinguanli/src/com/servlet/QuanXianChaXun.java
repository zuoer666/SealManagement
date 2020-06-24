package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

public class QuanXianChaXun extends HttpServlet {

	private static final long serialVersionUID = 369840050351775312L;

	/**
	 * The doGet method of the Server let.
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 鎺ユ敹淇℃伅
		String username = request.getParameter("username");
		username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
		
		System.out.println(username + "权限查询" );

		// 鏂板缓鏈嶅姟瀵硅薄
		Service serv = new Service();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 楠岃瘉澶勭悊
		String loged = serv.quanxianchaxun(username);
		if (loged.equals("1")) {
			System.out.print("quanxian1");
			//request.getSession().setAttribute("username", username);
			
			out.print("1");
			// response.sendRedirect("welcome.jsp");
		} else if(loged.equals("2")) {
			System.out.print("quanxian1");
			out.print("2");
		}else if(loged.equals("3")) {
			System.out.print("quanxian3");
			out.print("3");
		}

		 // 杩斿洖淇℃伅
//		 response.setCharacterEncoding("UTF-8");
//		 response.setContentType("text/html");
//		 PrintWriter out = response.getWriter();
//		 out.print("鐢ㄦ埛鍚嶏細" + username);
//		 out.print("瀵嗙爜锛�" + password);
		 out.flush();
		 out.close();

	}

	/**
	 * The doPost method of the Server let.
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 娴嬭瘯涓轰綍鎵嬫満绔腑鏂囦贡鐮侊紝鐢佃剳姝ｅ父
		// System.out.println("u1--"+username);
		// System.out.println("u2--"+username);

	}

}

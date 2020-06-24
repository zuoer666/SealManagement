package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.clas.BiaoHaoChaXunClass;
import com.clas.CaoZuoYuanAnZhuangClass;
import com.clas.CaoZuoYuanChaiChuClass;
import com.clas.CaoZuoYuanXunJianClass;
import com.clas.DingDan;
import com.clas.FengYinChaXunClass;
import com.clas.GetBasicInfoClass;
import com.clas.GetNameAndBanZuClass;
import com.clas.LingYongClass;
import com.clas.PinFanAnZhuangClass;
import com.clas.XunJianClass;
import com.db.DBManager;

public class Service {
	public List<PinFanAnZhuangClass> pinfananzhuang() {
	 	//String fabu_user,String fabu_title, String fabu_content,String fabu_money,String fabu_time

		Date dateanzhungshijian;
		 	String dianbiaohao;
	 	    String anzhungshijian;
	 	    String dili;
	 	    String dianbiaoxiang;
	 	    String fengyinhao;
		List<PinFanAnZhuangClass> mLists = new ArrayList<PinFanAnZhuangClass>();
		String regSql = "select id,dianbiaoid,anzhuangshijian,diliweizhi,biaoxiangid,fengyinid from anzhuangyuchaichu where anzhuangshijian>DATE_SUB(CURDATE(), INTERVAL 3 MONTH) HAVING COUNT(dianbiaoid) >2 order by ID desc";
	     DBManager sql = DBManager.createInstance();
	     sql.connectDB();

	     ResultSet ret = sql.executeQuery(regSql);
	     
	     try {
	    	
			
			if(ret.next()){
				System.out.println(ret.getString(2)
			 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
			 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6)); 
				dianbiaohao = ret.getString(2);
				dateanzhungshijian = ret.getDate(3);
				anzhungshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
				dili = ret.getString(4);
				dianbiaoxiang = ret.getString(5);
				fengyinhao = ret.getString(6);
				mLists.add(new PinFanAnZhuangClass(dianbiaohao,anzhungshijian,dili,dianbiaoxiang,fengyinhao));
				while(ret.next()){ 
					System.out.println(ret.getString(2)
						 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
						 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6)); 
							dianbiaohao = ret.getString(2);
							dateanzhungshijian = ret.getDate(3);
							anzhungshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
							dili = ret.getString(4);
							dianbiaoxiang = ret.getString(5);
							fengyinhao = ret.getString(6);
							mLists.add(new PinFanAnZhuangClass(dianbiaohao,anzhungshijian,dili,dianbiaoxiang,fengyinhao));
					 }
			}else{
				mLists.add(new PinFanAnZhuangClass(regSql, regSql, regSql, regSql, regSql));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    sql.closeDB();
	    return mLists;
	 }

	public List<GetBasicInfoClass> getbasicinfo(String username) {
	 	//String fabu_user,String fabu_title, String fabu_content,String fabu_money,String fabu_time

		
		String name;
	    String banzu;
	    String quanxian;

		List<GetBasicInfoClass> mLists = new ArrayList<GetBasicInfoClass>();
		String regSql = "select name,banzu,quanxian from user where username = '"+username+"'";
	     DBManager sql = DBManager.createInstance();
	     sql.connectDB();

	     ResultSet ret = sql.executeQuery(regSql);
	     
	     try {
	    	
			
			if(ret.next()){
				System.out.println(ret.getString(1)
			 	+ "\t"+ ret.getString(2)); 
				name = ret.getString(1);
				banzu = ret.getString(2);
				quanxian= ret.getString(3);
				
				mLists.add(new GetBasicInfoClass(name,banzu,quanxian));
				
			}else{
				mLists.add(new GetBasicInfoClass("","",""));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    sql.closeDB();
	    return mLists;
	 }
	
	public List<GetNameAndBanZuClass> getnameandbanzu(String username) {
	 	//String fabu_user,String fabu_title, String fabu_content,String fabu_money,String fabu_time

		
		String name;
	    String banzu;

		List<GetNameAndBanZuClass> mLists = new ArrayList<GetNameAndBanZuClass>();
		String regSql = "select name,banzu from user where username = '"+username+"'";
	     DBManager sql = DBManager.createInstance();
	     sql.connectDB();

	     ResultSet ret = sql.executeQuery(regSql);
	     
	     try {
	    	
			
			if(ret.next()){
				System.out.println(ret.getString(1)
			 	+ "\t"+ ret.getString(2)); 
				name = ret.getString(1);
				banzu = ret.getString(2);
				
				mLists.add(new GetNameAndBanZuClass(name,banzu));
				
			}else{
				mLists.add(new GetNameAndBanZuClass("",""));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    sql.closeDB();
	    return mLists;
	 }
public List<LingYongClass> tongjilingyong() {
	 	//String fabu_user,String fabu_title, String fabu_content,String fabu_money,String fabu_time

		Date dateanzhungshijian;
		Date datechaichushijian;
		String lingyongren;
	    String lingyongrenshijian;
	    String fengyinhao;
		List<LingYongClass> mLists = new ArrayList<LingYongClass>();
		String regSql = "select id,lingyongren,time,fengyinid from fengyinLingyong where fengyinid not in (select fengyinid from anzhuangyuchaichu) order by ID desc";
	     DBManager sql = DBManager.createInstance();
	     sql.connectDB();

	     ResultSet ret = sql.executeQuery(regSql);
	     
	     try {
	    	
			
			if(ret.next()){
				System.out.println(ret.getString(2)
			 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)); 
				lingyongren = ret.getString(2);
				dateanzhungshijian = ret.getDate(3);
				lingyongrenshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
				fengyinhao = ret.getString(4);
				mLists.add(new LingYongClass(lingyongren,lingyongrenshijian,fengyinhao));
				while(ret.next()){ 
					System.out.println(ret.getString(2)
						 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)); 
							lingyongren = ret.getString(2);
							dateanzhungshijian = ret.getDate(3);
							lingyongrenshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
							fengyinhao = ret.getString(4);
							mLists.add(new LingYongClass(lingyongren,lingyongrenshijian,fengyinhao));
					 }
			}else{
//				
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    sql.closeDB();
	    return mLists;
	 }

public List<XunJianClass> tongjixunjian() {
	 	//String fabu_user,String fabu_title, String fabu_content,String fabu_money,String fabu_time

		Date dateanzhungshijian;
		Date datechaichushijian;
		 	String dianbiaohao;
	 	    String anzhungshijian;
	 	    String dili;
	 	    String dianbiaoxiang;
	 	    String fengyinhao;
		List<XunJianClass> mLists = new ArrayList<XunJianClass>();
		String regSql = "select id,dianbiaoid,anzhungshijian,diliweizhi,biaoxiangid,fengyinid from anzhuangyuchaichu where dianbiaoid not in (select biaohaoid from xunjian) order by ID desc";
	     DBManager sql = DBManager.createInstance();
	     sql.connectDB();

	     ResultSet ret = sql.executeQuery(regSql);
	     
	     try {
	    	
			
			if(ret.next()){
				System.out.println(ret.getString(2)
			 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
			 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6)); 
				dianbiaohao = ret.getString(2);
				dateanzhungshijian = ret.getDate(3);
				anzhungshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
				dili = ret.getString(4);
				dianbiaoxiang = ret.getString(5);
				fengyinhao = ret.getString(6);
				mLists.add(new XunJianClass(dianbiaohao,anzhungshijian,dili,dianbiaoxiang,fengyinhao));
				while(ret.next()){ 
					System.out.println(ret.getString(2)
						 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
						 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6)); 
							dianbiaohao = ret.getString(2);
							dateanzhungshijian = ret.getDate(3);
							anzhungshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
							dili = ret.getString(4);
							dianbiaoxiang = ret.getString(5);
							fengyinhao = ret.getString(6);
							mLists.add(new XunJianClass(dianbiaohao,anzhungshijian,dili,dianbiaoxiang,fengyinhao));
					 }
			}else{
//				
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    sql.closeDB();
	    return mLists;
	 }
public List<CaoZuoYuanXunJianClass> caozuoyuanxunjian(String caozuoyuan,String timekaishi,String timejieshu) {
	 	//String fabu_user,String fabu_title, String fabu_content,String fabu_money,String fabu_time

		Date dateanzhungshijian;
		Date datechaichushijian;
		
		String dianbiaohao;
	    String xunjianshijian;
	    String dili;
	    String dianbiaoxiang;
	    String fengyinhao;
		
		List<CaoZuoYuanXunJianClass> mLists = new ArrayList<CaoZuoYuanXunJianClass>();
		String regSql = "select id,dianbiaoid,time,diliweizhi,biaoxianghao,fengyinid from xunjian where xunjianyuanid = '"+caozuoyuan+"' order by ID desc";
	     DBManager sql = DBManager.createInstance();
	     sql.connectDB();

	     ResultSet ret = sql.executeQuery(regSql);
	     
	     try {
	    	
			
			if(ret.next()){
				System.out.println(ret.getString(2)
			 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
			 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6)); 
				dianbiaohao = ret.getString(2);
				dateanzhungshijian = ret.getDate(3);
				xunjianshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
				dili = ret.getString(4);
				dianbiaoxiang = ret.getString(5);
				fengyinhao = ret.getString(6);
				mLists.add(new CaoZuoYuanXunJianClass(dianbiaohao,xunjianshijian,dili,dianbiaoxiang,fengyinhao));
				while(ret.next()){ 
					System.out.println(ret.getString(2)
						 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
						 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6)); 
							dianbiaohao = ret.getString(2);
							dateanzhungshijian = ret.getDate(3);
							xunjianshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
							dili = ret.getString(4);
							dianbiaoxiang = ret.getString(5);
							fengyinhao = ret.getString(6);
							mLists.add(new CaoZuoYuanXunJianClass(dianbiaohao,xunjianshijian,dili,dianbiaoxiang,fengyinhao));
					 }
			}else{
//				
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    sql.closeDB();
	    return mLists;
	 }
public List<CaoZuoYuanChaiChuClass> caozuoyuanchaichu(String caozuoyuan,String timekaishi,String timejieshu) {
 	//String fabu_user,String fabu_title, String fabu_content,String fabu_money,String fabu_time

	Date dateanzhungshijian;
	Date datechaichushijian;
	String dianbiaohao;
    String chaichushijian;
    String dili;
    String dianbiaoxiang;
    String fengyinhao;
	
	List<CaoZuoYuanChaiChuClass> mLists = new ArrayList<CaoZuoYuanChaiChuClass>();
	String regSql = "select id,dianbiaoid,chaichushijian,diliweizhi,biaoxiangid,fengyinid from anzhuangyuchaichu where chaichurenid = '"+caozuoyuan+"' order by ID desc";
     DBManager sql = DBManager.createInstance();
     sql.connectDB();

     ResultSet ret = sql.executeQuery(regSql);
     
     try {
    	
		
		if(ret.next()){
			System.out.println(ret.getString(2)
		 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
		 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6)); 
			dianbiaohao = ret.getString(2);
			dateanzhungshijian = ret.getDate(3);
			chaichushijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
			dili = ret.getString(4);
			dianbiaoxiang = ret.getString(5);
			fengyinhao = ret.getString(6);
			mLists.add(new CaoZuoYuanChaiChuClass(dianbiaohao,chaichushijian,dili,dianbiaoxiang,fengyinhao));
			while(ret.next()){ 
				System.out.println(ret.getString(2)
					 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
					 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6)); 
						dianbiaohao = ret.getString(2);
						dateanzhungshijian = ret.getDate(3);
						chaichushijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
						dili = ret.getString(4);
						dianbiaoxiang = ret.getString(5);
						fengyinhao = ret.getString(6);
						mLists.add(new CaoZuoYuanChaiChuClass(dianbiaohao,chaichushijian,dili,dianbiaoxiang,fengyinhao));
				 }
		}else{
//			
//			mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//			mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//			mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    sql.closeDB();
    return mLists;
 }
public List<CaoZuoYuanAnZhuangClass> caozuoyuananzhuang(String caozuoyuan,String timekaishi,String timejieshu) {
	 	//String fabu_user,String fabu_title, String fabu_content,String fabu_money,String fabu_time

		Date dateanzhungshijian;
		Date datechaichushijian;
		 	String dianbiaohao;
	 	    String anzhungshijian;
	 	    String dili;
	 	    String dianbiaoxiang;
	 	    String fengyinhao;
		List<CaoZuoYuanAnZhuangClass> mLists = new ArrayList<CaoZuoYuanAnZhuangClass>();
		String regSql = "select id,dianbiaoid,anzhungshijian,diliweizhi,biaoxiangid,fengyinid from anzhuangyuchaichu where anzhuangrenid = '"+caozuoyuan+"' order by ID desc";
	     DBManager sql = DBManager.createInstance();
	     sql.connectDB();

	     ResultSet ret = sql.executeQuery(regSql);
	     
	     try {
	    	
			
			if(ret.next()){
				System.out.println(ret.getString(2)
			 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
			 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6)); 
				dianbiaohao = ret.getString(2);
				dateanzhungshijian = ret.getDate(3);
				anzhungshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
				dili = ret.getString(4);
				dianbiaoxiang = ret.getString(5);
				fengyinhao = ret.getString(6);
				mLists.add(new CaoZuoYuanAnZhuangClass(dianbiaohao,anzhungshijian,dili,dianbiaoxiang,fengyinhao));
				while(ret.next()){ 
					System.out.println(ret.getString(2)
						 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
						 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6)); 
							dianbiaohao = ret.getString(2);
							dateanzhungshijian = ret.getDate(3);
							anzhungshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
							dili = ret.getString(4);
							dianbiaoxiang = ret.getString(5);
							fengyinhao = ret.getString(6);
							mLists.add(new CaoZuoYuanAnZhuangClass(dianbiaohao,anzhungshijian,dili,dianbiaoxiang,fengyinhao));
					 }
			}else{
//				
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    sql.closeDB();
	    return mLists;
	 }
public List<BiaoHaoChaXunClass> biaohaochaxun(String biaohao) {
	 	//String fabu_user,String fabu_title, String fabu_content,String fabu_money,String fabu_time

		Date dateanzhungshijian;
		Date datechaichushijian;
	    String anzhungren;
	    String anzhungshijian;
	    String chaichuren;
	    String chaichushijian;
	    String dili;
	    String dianbiaoxiang;
	    String fengyinhao;
		List<BiaoHaoChaXunClass> mLists = new ArrayList<BiaoHaoChaXunClass>();
		String regSql = "select id,anzhungren,anzhungshijian,chaichurenid,chaichushijian,diliweizhi,biaoxiangid,fengyinid from anzhuangyuchaichu where dianbiaoid = '"+biaohao+"' order by ID desc";
	     DBManager sql = DBManager.createInstance();
	     sql.connectDB();

	     ResultSet ret = sql.executeQuery(regSql);
	     
	     try {
			
			if(ret.next()){
				System.out.println(ret.getString(2)
			 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
			 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6) + "\t"+ ret.getString(7) + "\t"+ ret.getString(8)); 
				anzhungren = ret.getString(2);
				dateanzhungshijian = ret.getDate(3);
				anzhungshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
				chaichuren = ret.getString(4);
				datechaichushijian = ret.getDate(5);
				chaichushijian=dateToString(datechaichushijian,"yyyy-MM-dd HH:mm:ss");
				dili = ret.getString(6);
				dianbiaoxiang = ret.getString(7);
				fengyinhao = ret.getString(8);
				mLists.add(new BiaoHaoChaXunClass(anzhungren,anzhungshijian,chaichuren,chaichushijian,dili,dianbiaoxiang,fengyinhao));
				while(ret.next()){ 
					 System.out.println(ret.getString(2)
					 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
					 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6) + "\t"+ ret.getString(7) + "\t"+ ret.getString(8)); 
					 	anzhungren = ret.getString(2);
						dateanzhungshijian = ret.getDate(3);
						anzhungshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
						chaichuren = ret.getString(4);
						datechaichushijian = ret.getDate(5);
						chaichushijian=dateToString(datechaichushijian,"yyyy-MM-dd HH:mm:ss");
						dili = ret.getString(6);
						dianbiaoxiang = ret.getString(7);
						fengyinhao = ret.getString(8);
						mLists.add(new BiaoHaoChaXunClass(anzhungren,anzhungshijian,chaichuren,chaichushijian,dili,dianbiaoxiang,fengyinhao));
					 }
			}else{
//				
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    sql.closeDB();
	    return mLists;
	 }
public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }
public List<FengYinChaXunClass> fengyinchaxun(String fengyinhao) {
	 	//String fabu_user,String fabu_title, String fabu_content,String fabu_money,String fabu_time

		Date dateanzhungshijian;
		Date datechaichushijian;
	    String anzhungren;
	    String anzhungshijian;
	    String chaichuren;
	    String chaichushijian;
	    String dili;
	    String dianbiaoxiang;
	    String dianbiaohao;
		List<FengYinChaXunClass> mLists = new ArrayList<FengYinChaXunClass>();
		String regSql = "select id,anzhungren,anzhungshijian,chaichurenid,chaichushijian,diliweizhi,biaoxiangid,dianbiaoid from anzhuangyuchaichu where fengyinid = '"+fengyinhao+"' order by ID desc";
	     DBManager sql = DBManager.createInstance();
	     sql.connectDB();

	     ResultSet ret = sql.executeQuery(regSql);
	     
	     try {
			
			if(ret.next()){
				System.out.println(ret.getString(2)
			 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
			 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6) + "\t"+ ret.getString(7) + "\t"+ ret.getString(8)); 
				anzhungren = ret.getString(2);
				dateanzhungshijian = ret.getDate(3);
				anzhungshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
				chaichuren = ret.getString(4);
				datechaichushijian = ret.getDate(5);
				chaichushijian=dateToString(datechaichushijian,"yyyy-MM-dd HH:mm:ss");
				dili = ret.getString(6);
				dianbiaoxiang = ret.getString(7);
				dianbiaohao = ret.getString(8);
				mLists.add(new FengYinChaXunClass(anzhungren,anzhungshijian,chaichuren,chaichushijian,dili,dianbiaoxiang,dianbiaohao));
				while(ret.next()){ 
					 System.out.println(ret.getString(2)
					 	+ "\t"+ ret.getString(3) + "\t"+ ret.getString(4)
					 	+ "\t"+ ret.getString(5) + "\t"+ ret.getString(6) + "\t"+ ret.getString(7) + "\t"+ ret.getString(8)); 
					 	anzhungren = ret.getString(2);
						dateanzhungshijian = ret.getDate(3);
						anzhungshijian=dateToString(dateanzhungshijian,"yyyy-MM-dd HH:mm:ss");
						chaichuren = ret.getString(4);
						datechaichushijian = ret.getDate(5);
						chaichushijian=dateToString(datechaichushijian,"yyyy-MM-dd HH:mm:ss");
						dili = ret.getString(6);
						dianbiaoxiang = ret.getString(7);
						dianbiaohao = ret.getString(8);
						mLists.add(new FengYinChaXunClass(anzhungren,anzhungshijian,chaichuren,chaichushijian,dili,dianbiaoxiang,dianbiaohao));
					 }
			}else{
//				
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
//				mLists.add(new DingDan(0,"******","**","*********************************************************************************","***********","****","1998-6-1"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    sql.closeDB();
	    return mLists;
	 }
public Boolean xunjian(String time,String fengyinid,String dianbiaoid,String dianbiaoxiangid,String xunjianrenrenid,String dili) {
    	
	Date date = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	try {
		date = (Date) sdf.parse(time);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		String regSql = "insert into xunjian (time,fengyinid,biaohaoid,biaoxianghao,xunjianyuanid,diliweizhi) values('"
				+ date + "','" + fengyinid +  "','" +dianbiaoid + "','" + dianbiaoxiangid +  "','"  + xunjianrenrenid + "','" + dili + "') ";
		
		System.out.println(regSql);
        // 锟斤拷取Sql锟斤拷询锟斤拷锟�
        //String regSql = "insert into user values('"+ username+ "','"+ password+ "') ";

        // 锟斤拷取DB锟斤拷锟斤拷
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
        
        return false;
    }
public String quanxianchaxun(String username){
		String quan = new String("0");
    	String logSql = "select quanxian from user where username ='" + username
                + "'";

        // 锟斤拷取DB锟斤拷锟斤拷
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        ResultSet ret = sql.executeQuery(logSql);
	     try {
	    	 if(ret.next()){
	    		 	quan = ret.getString(1);
					
				}else{
					quan = "0";
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    sql.closeDB();
	    return quan;
        
    }
public Boolean fengyinchaichu(String fengyinid,String dianbiaoid,String chaichurenrenid,String chaichushijian) {
    	
	Date date = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	try {
		date = (Date) sdf.parse(chaichushijian);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//		String regSql = "insert into fengyinlingyong (fengyinid,dianbiaoid) values('"
//				+ fengyinid + "','" + dianbiaoid +  "','" +dianbiaoxiangid + "','" + anzhuangrenid +  "','"  + anzhuangshijian + "','" + dili + "') ";
//		
		
	 	
		String regSql = "update anzhuangyuchaichu set chaichurenid ='"
				+ chaichurenrenid +"',chaichushijian='"+date + "'where fengyinid = '"+fengyinid+"'";
				
			System.out.println(regSql);
	     // 锟斤拷取Sql锟斤拷询锟斤拷锟�
	     //String regSql = "insert into user values('"+ username+ "','"+ password+ "') ";

	     // 锟斤拷取DB锟斤拷锟斤拷
	     DBManager sql = DBManager.createInstance();
	     sql.connectDB();

	     int ret = sql.executeUpdate(regSql);
	     if (ret != 0) {
	         sql.closeDB();
	         return true;
	     }
	     sql.closeDB();
	     
	     return false;
    }
public Boolean fengyinanzhuang(String fengyinid,String dianbiaoid,String dianbiaoxiangid,String anzhuangrenid,String anzhuangshijian,String dili) {
 	
	Date date = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	try {
		date = (Date) sdf.parse(anzhuangshijian);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	
		String regSql = "insert into anzhuangyuchaichu (fengyinid,dianbiaoid,biaoxiangid,anzhuangrenid,anzhuangshijian,diliweizhi) values('"
				+ fengyinid + "','" + dianbiaoid +  "','" +dianbiaoxiangid + "','" + anzhuangrenid +  "','"  + date + "','" + dili + "') ";
		
		System.out.println(regSql);
        // 锟斤拷取Sql锟斤拷询锟斤拷锟�
        //String regSql = "insert into user values('"+ username+ "','"+ password+ "') ";

        // 锟斤拷取DB锟斤拷锟斤拷
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
        
        return false;
    }
public Boolean shifoucunzaipizuihou(String fengyinid2){
		int a= Integer.parseInt(fengyinid2);
		String b = Integer.toString(a);
    	String logSql = "select * from fengyinlingyong where fengyinid ='" + b
                + "'";

        // 锟斤拷取DB锟斤拷锟斤拷
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        try {
            ResultSet rs = sql.executeQuery(logSql);
            if (rs.next()) {
                sql.closeDB();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return false;
        
    }
public Boolean dangelingqu(String fengyinid,String username,String  time) {
    	
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			date = (Date) sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		String regSql = "insert into fengyinlingyong (fengyinid,lingyongren,time) values('"
				+ fengyinid + "','" + username +  "','" +date + "') ";
		
		System.out.println(regSql);
        // 锟斤拷取Sql锟斤拷询锟斤拷锟�
        //String regSql = "insert into user values('"+ username+ "','"+ password+ "') ";

        // 锟斤拷取DB锟斤拷锟斤拷
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
        
        return false;
    }
public Boolean shifoucunzaishangji(String username){
	    	String logSql = "select * from user where name ='" + username
	                + "'";

	        // 锟斤拷取DB锟斤拷锟斤拷
	        DBManager sql = DBManager.createInstance();
	        sql.connectDB();
	        try {
	            ResultSet rs = sql.executeQuery(logSql);
	            if (rs.next()) {
	                sql.closeDB();
	                return true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        sql.closeDB();
	        return false;
	        
	    }
public Boolean shifoucunzai(String username){
	    	String logSql = "select * from user where username ='" + username
	                + "'";

	        // 锟斤拷取DB锟斤拷锟斤拷
	        DBManager sql = DBManager.createInstance();
	        sql.connectDB();
	        try {
	            ResultSet rs = sql.executeQuery(logSql);
	            if (rs.next()) {
	                sql.closeDB();
	                return true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        sql.closeDB();
	        return false;
	        
	    }
public Boolean register(String r_username,String r_name,String  r_zhiwu,String r_shangji,String r_pwd,String r_quanxian) {
	    	
	        Boolean a = shifoucunzai(r_username);
	        if(a){
	        	return false;
	        }
	    	
			String regSql = "insert into user (username,name,zhiwu,shangji,password,quanxian) values('"
					+ r_username + "','" + r_name +  "','" +r_zhiwu + "','" + r_shangji + "','" + r_pwd + "','" + r_quanxian + "') ";
			
			System.out.println(regSql);
	        // 锟斤拷取Sql锟斤拷询锟斤拷锟�
	        //String regSql = "insert into user values('"+ username+ "','"+ password+ "') ";

	        // 锟斤拷取DB锟斤拷锟斤拷
	        DBManager sql = DBManager.createInstance();
	        sql.connectDB();

	        int ret = sql.executeUpdate(regSql);
	        if (ret != 0) {
	            sql.closeDB();
	            return true;
	        }
	        sql.closeDB();
	        
	        return false;
	    }
public Boolean login(String username, String password) {

        // 锟斤拷取Sql锟斤拷询锟斤拷锟�
        String logSql = "select * from user where username ='" + username
                + "' and password ='" + password + "'";

        // 锟斤拷取DB锟斤拷锟斤拷
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        // 锟斤拷锟斤拷DB锟斤拷锟斤拷
        try {
            ResultSet rs = sql.executeQuery(logSql);
            if (rs.next()) {
                sql.closeDB();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return false;
    }

}
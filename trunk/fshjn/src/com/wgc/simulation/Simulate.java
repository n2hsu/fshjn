package com.wgc.simulation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;


public class Simulate extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String path = "../webapps/wgc/xml";
	int flag,flag1,flag2,flag3,flag4 = 0;
	Thread th1,th2 = new Thread();
	 private final Timer timer = new Timer(); 
	 private MyTask myTask1 = new MyTask("myTask1");
	 private MyTask myTask2 = new MyTask("myTask2");
	 
	 static class MyTask extends java.util.TimerTask{
		String info;
		public MyTask(String s){
			this.info = s;
		}
        public String getInfo(){
            return info;
        }

	    public void run(){
	    	if(this.getInfo()=="myTask1"){
	    		try{
	    			//System.out.println(System.getProperty("user.dir"));
	    			Writexml myxml=new Writexml(path+"/Comptem.xml");////模拟产生传感器温度
	    			myxml.toWrite();
	    			myxml.toSave();
	    			//System.out.print("电脑温度  数据更新成功！！\n");
	    			  }
	    			  catch(ParserConfigurationException exp){
	    		  exp.printStackTrace();
	    		  System.out.println("电脑温度写入失败！！");
	    			} catch (ClassNotFoundException e) {
	    				 
	    				e.printStackTrace();
	    			} 
	    		  
	    			try{
	    		 		  Writexml myxml1=new Writexml(path+"/Projector.xml");//模拟产生 投影仪温度
	    		 		  myxml1.toWrite1();		  
	    		 		  myxml1.toSave();
	    		 		 // System.out.print("投影仪温度  数据更新成功！！\n");
	    		 		  }
	    		 		  catch(ParserConfigurationException exp){
	    		 		   exp.printStackTrace();
	    		 		   System.out.println("投影仪温度信息写入失败！！");
	    		 		  } catch (ClassNotFoundException e) {
	    					 
	    					e.printStackTrace();
	    				}
	    		 		  
	    			try{
	    				 Writexml myxml2=new Writexml(path+"/Classtem.xml");//模拟产生 教室温度
	    				 myxml2.toWrite2(0);
	    				 myxml2.toSave();
	    				 //System.out.print("教室温度  数据更新成功！！\n");
	    				}
	    				 catch(ParserConfigurationException exp){
	    				 	 exp.printStackTrace();
	    				 	 System.out.println("教室温度信息写入失败！！");
	    				 	 }
	    				 		  
	    			try{
	    				Writexml myxml3=new Writexml(path+"/Classlight.xml");//模拟产生教室光线
	    				myxml3.toWrite3();		  
	    				myxml3.toSave();
	    				//System.out.print("教室光线  数据更新成功！！\n");
	    				}
	    				catch(ParserConfigurationException exp){
	    					exp.printStackTrace();
	    					 System.out.println("教室光线 信息写入失败！！");
	    					}			
	    			  
	    	 	 try{
	    		 	 Writexml myxml5=new Writexml(path+"/ClassStu.xml");//模拟产生 教室即时人数
	    		 	 myxml5.toWrite4();		  
	    		 	 myxml5.toSave();
	    		 	// System.out.print("教室即时人数  数据更新成功！！\n");
	    		 	 }
	    		 	 catch(ParserConfigurationException exp){
	    		 	 exp.printStackTrace();
	    		 	 System.out.println("教室即时人数信息写入失败！！");
	    		 	 } catch (ClassNotFoundException e) {
	    				 
	    				e.printStackTrace();
	    		 	 	} catch (SQLException e) {
						 
						e.printStackTrace();
					}

	    		 	 try{
	    	 		  Writexml myxml6=new Writexml(path+"/Station.xml");//模拟产生 站台即时人数
	    	 		  myxml6.toWrite5();		  
	    	 		  myxml6.toSave();
	    	 		 // System.out.print("站台即时人数  数据更新成功！！\n");
	    	 		  }
	    	 		  catch(ParserConfigurationException exp){
	    	 		   exp.printStackTrace();
	    	 		   System.out.println("站台即时人数信息写入失败！！");
	    	 		  	} catch (ClassNotFoundException e) {
						 
						e.printStackTrace();
					}
	    	 		  
	    	 		 try {										//模拟车位个数和上课考勤情况
	    	 			Sqlupdate.sqlupdate();
	    	 			//System.out.print("车位个数和上课考勤  数据更新成功！！\n");
	    			} catch (ClassNotFoundException e) {
	    				 
	    				e.printStackTrace();
	    			}
	    			
	    	}//if语句结束
		 	
	    	if(this.getInfo()=="myTask2"){
    			try{
   				 Writexml myxml2=new Writexml(path+"/Classtem.xml");//模拟产生 教室温度
   				 myxml2.toWrite2(10);						//5代表在原来的基础上增加5摄氏度
   				 myxml2.toSave();
   				 //System.out.println("教室温度  数据更新成功！！");
   				}
   				 catch(ParserConfigurationException exp){
   				 	 exp.printStackTrace();
   				 	 System.out.println("教室温度信息写入失败！！");
   				 	 }
	    	}
  		    	
	    }//run语句结束

	}	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String str1 = req.getParameter("bt1").trim();
		String str = new String(str1.getBytes("iso-8859-1"),"gb2312");		
		//System.out.println(str);
		
		if(str.equals("开始模拟数据")){
			if(flag==0){
				//System.out.println("开始模拟数据！！");
				flag=1;
					try{
						myTask1 = new MyTask("myTask1");
						timer.schedule(myTask1, 0, 5000);
					}catch(IllegalStateException e){}
			}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("停止模拟数据")){
			//System.out.println("停止模拟数据！！");
			myTask1.cancel();
			myTask2.cancel();
			flag=0;flag1=0;flag3=0;
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("天 气 炎 热")){
			if(flag1==0){
				//System.out.println("天气炎热！！");
				flag1=1;flag2=0;
				myTask1.cancel();
				try{
					myTask2 = new MyTask("myTask2");
					timer.schedule(myTask2, 0, 5000);
				}catch(IllegalStateException e){}	 
			}
			resp.sendRedirect("leftFrame.jsp");	
		}
		
		if(str.equals("天 气 正 常")){
			//System.out.println("天气转为正常！！");
			myTask2.cancel();flag1=0;
			try{
				if(flag2==0){
					flag2=1;
					myTask1 = new MyTask("myTask1");
					timer.schedule(myTask1, 0, 5000);
				}

			}catch(IllegalStateException e){}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("模 拟 闯 入")){
			//System.out.println("模 拟 闯 入！！");
			if(flag3==0){
				flag3=1;flag4=0;
	    		try{
	    			Writexml myxml7=new Writexml(path+"/Brokeinto.xml");//模拟产生教室光线
	    			myxml7.toWrite6(0,null);
	    			myxml7.toSave();
	    			//System.out.print("人员闯入信息更新成功！！\n");
	    			}
	    			catch(ParserConfigurationException exp){
	    				exp.printStackTrace();
	    				 System.out.println("人员闯入信息写入失败！！");
	    				} catch (ClassNotFoundException e) {
	    				e.printStackTrace();
	    			}
			}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("手动模拟闯入")){
			//System.out.println("手动模拟闯入！！");
			String sele1 = req.getParameter("sele1").trim();
			String sele2 = req.getParameter("sele2").trim();
			String sele3 = req.getParameter("sele3").trim();
			String se = sele1 + sele2 + sele3;                  //se为cqgmc，即教室号

			flag4=0;
    		try{
    			Writexml myxml7=new Writexml(path+"/Brokeinto.xml");
    			myxml7.toWrite6(0,se);
    			myxml7.toSave();
    			//System.out.print("人员闯入信息更新成功！！\n");
    			}
    			catch(ParserConfigurationException exp){
    				exp.printStackTrace();
    				 System.out.println("人员闯入信息写入失败！！");
    				} catch (ClassNotFoundException e) {
    				e.printStackTrace();
    			}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("解 除 警 报")){
			if(flag4==0){
				flag4=1;flag3=0;
	    		try{
	    			Writexml myxml7=new Writexml(path+"/Brokeinto.xml");//模拟产生教室光线
	    			myxml7.toWrite6(1,null);
	    			myxml7.toSave();
	    			//System.out.print("警报解除！！\n");
	    			}
	    			catch(ParserConfigurationException exp){
	    				exp.printStackTrace();
	    				} catch (ClassNotFoundException e) {
	    					
	    				e.printStackTrace();
	    			}  
			}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("模 拟 损 坏")){
			//System.out.println("模 拟 损 坏！！");
			String sele1 = req.getParameter("sele1").trim();
			String sele2 = req.getParameter("sele2").trim();
			String sele3 = req.getParameter("sele3").trim();
			String se = sele1 + sele2 + sele3;                  //se为cqgmc，即教室号
			
			String che1 = req.getParameter("che1");
			String che2 = req.getParameter("che2");
			
			if(che1!=null){
				String sql = "update senser set sfsh='1' where cgqmc='"+se+"' and cgqlxID='1'";
				try {
					Sqlupdate.sqlupdate1(sql);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			if(che2!=null){
				String sql = "update senser set sfsh='1' where cgqmc='"+se+"' and cgqlxID='2'";
				try {
					Sqlupdate.sqlupdate1(sql);
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				}
			}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("全 部 修 复")){
			//System.out.println("全 部 修 复！！");
			String sql = "update senser set sfsh='0' where sfsh='1'";
			try {
				Sqlupdate.sqlupdate1(sql);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("确  定")){
			myTask1.cancel();flag=0;flag1=0;
			String sele4 = req.getParameter("sele4").trim();
			String[] list = new String[22];
			
			if(sele4.equals("1")){
				for(int i=0;i<8;i++){
					String sele = "sele" + (i+5);
					list[i] = req.getParameter(sele).trim();
				}
				for(int i=8;i<22;i++){
					list[i] = "0";
				}
			}
			if(sele4.equals("2")){
				for(int i=0;i<8;i++){
					list[i] = "0";
				}
				for(int i=8;i<14;i++){
					String sele = "sele" + (i-3);
					list[i] = req.getParameter(sele).trim();
				}
				for(int i=14;i<22;i++){
					list[i] = "0";
				}
			}
			if(sele4.equals("3")){
				for(int i=0;i<14;i++){
					list[i] = "0";
				}
				for(int i=14;i<22;i++){
					String sele = "sele" + (i-9);
					list[i] = req.getParameter(sele).trim();
				}
			}
   		 	 try{
   	 		  Writexml myxml8=new Writexml(path+"/Station.xml");//模拟产生 站台即时人数
   	 		  myxml8.toWrite7(list);
   	 		  myxml8.toSave();
   	 		 // System.out.print("手动站台即时人数  数据更新成功！！\n");
   	 		  }catch (ParserConfigurationException e) {
   	 			  
				e.printStackTrace();
			}
   	 		resp.sendRedirect("leftFrame.jsp");
		}
		
	}
}

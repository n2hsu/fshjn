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
	    			Writexml myxml=new Writexml(path+"/Comptem.xml");////ģ������������¶�
	    			myxml.toWrite();
	    			myxml.toSave();
	    			//System.out.print("�����¶�  ���ݸ��³ɹ�����\n");
	    			  }
	    			  catch(ParserConfigurationException exp){
	    		  exp.printStackTrace();
	    		  System.out.println("�����¶�д��ʧ�ܣ���");
	    			} catch (ClassNotFoundException e) {
	    				 
	    				e.printStackTrace();
	    			} 
	    		  
	    			try{
	    		 		  Writexml myxml1=new Writexml(path+"/Projector.xml");//ģ����� ͶӰ���¶�
	    		 		  myxml1.toWrite1();		  
	    		 		  myxml1.toSave();
	    		 		 // System.out.print("ͶӰ���¶�  ���ݸ��³ɹ�����\n");
	    		 		  }
	    		 		  catch(ParserConfigurationException exp){
	    		 		   exp.printStackTrace();
	    		 		   System.out.println("ͶӰ���¶���Ϣд��ʧ�ܣ���");
	    		 		  } catch (ClassNotFoundException e) {
	    					 
	    					e.printStackTrace();
	    				}
	    		 		  
	    			try{
	    				 Writexml myxml2=new Writexml(path+"/Classtem.xml");//ģ����� �����¶�
	    				 myxml2.toWrite2(0);
	    				 myxml2.toSave();
	    				 //System.out.print("�����¶�  ���ݸ��³ɹ�����\n");
	    				}
	    				 catch(ParserConfigurationException exp){
	    				 	 exp.printStackTrace();
	    				 	 System.out.println("�����¶���Ϣд��ʧ�ܣ���");
	    				 	 }
	    				 		  
	    			try{
	    				Writexml myxml3=new Writexml(path+"/Classlight.xml");//ģ��������ҹ���
	    				myxml3.toWrite3();		  
	    				myxml3.toSave();
	    				//System.out.print("���ҹ���  ���ݸ��³ɹ�����\n");
	    				}
	    				catch(ParserConfigurationException exp){
	    					exp.printStackTrace();
	    					 System.out.println("���ҹ��� ��Ϣд��ʧ�ܣ���");
	    					}			
	    			  
	    	 	 try{
	    		 	 Writexml myxml5=new Writexml(path+"/ClassStu.xml");//ģ����� ���Ҽ�ʱ����
	    		 	 myxml5.toWrite4();		  
	    		 	 myxml5.toSave();
	    		 	// System.out.print("���Ҽ�ʱ����  ���ݸ��³ɹ�����\n");
	    		 	 }
	    		 	 catch(ParserConfigurationException exp){
	    		 	 exp.printStackTrace();
	    		 	 System.out.println("���Ҽ�ʱ������Ϣд��ʧ�ܣ���");
	    		 	 } catch (ClassNotFoundException e) {
	    				 
	    				e.printStackTrace();
	    		 	 	} catch (SQLException e) {
						 
						e.printStackTrace();
					}

	    		 	 try{
	    	 		  Writexml myxml6=new Writexml(path+"/Station.xml");//ģ����� վ̨��ʱ����
	    	 		  myxml6.toWrite5();		  
	    	 		  myxml6.toSave();
	    	 		 // System.out.print("վ̨��ʱ����  ���ݸ��³ɹ�����\n");
	    	 		  }
	    	 		  catch(ParserConfigurationException exp){
	    	 		   exp.printStackTrace();
	    	 		   System.out.println("վ̨��ʱ������Ϣд��ʧ�ܣ���");
	    	 		  	} catch (ClassNotFoundException e) {
						 
						e.printStackTrace();
					}
	    	 		  
	    	 		 try {										//ģ�⳵λ�������Ͽο������
	    	 			Sqlupdate.sqlupdate();
	    	 			//System.out.print("��λ�������Ͽο���  ���ݸ��³ɹ�����\n");
	    			} catch (ClassNotFoundException e) {
	    				 
	    				e.printStackTrace();
	    			}
	    			
	    	}//if������
		 	
	    	if(this.getInfo()=="myTask2"){
    			try{
   				 Writexml myxml2=new Writexml(path+"/Classtem.xml");//ģ����� �����¶�
   				 myxml2.toWrite2(10);						//5������ԭ���Ļ���������5���϶�
   				 myxml2.toSave();
   				 //System.out.println("�����¶�  ���ݸ��³ɹ�����");
   				}
   				 catch(ParserConfigurationException exp){
   				 	 exp.printStackTrace();
   				 	 System.out.println("�����¶���Ϣд��ʧ�ܣ���");
   				 	 }
	    	}
  		    	
	    }//run������

	}	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String str1 = req.getParameter("bt1").trim();
		String str = new String(str1.getBytes("iso-8859-1"),"gb2312");		
		//System.out.println(str);
		
		if(str.equals("��ʼģ������")){
			if(flag==0){
				//System.out.println("��ʼģ�����ݣ���");
				flag=1;
					try{
						myTask1 = new MyTask("myTask1");
						timer.schedule(myTask1, 0, 5000);
					}catch(IllegalStateException e){}
			}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("ֹͣģ������")){
			//System.out.println("ֹͣģ�����ݣ���");
			myTask1.cancel();
			myTask2.cancel();
			flag=0;flag1=0;flag3=0;
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("�� �� �� ��")){
			if(flag1==0){
				//System.out.println("�������ȣ���");
				flag1=1;flag2=0;
				myTask1.cancel();
				try{
					myTask2 = new MyTask("myTask2");
					timer.schedule(myTask2, 0, 5000);
				}catch(IllegalStateException e){}	 
			}
			resp.sendRedirect("leftFrame.jsp");	
		}
		
		if(str.equals("�� �� �� ��")){
			//System.out.println("����תΪ��������");
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
		
		if(str.equals("ģ �� �� ��")){
			//System.out.println("ģ �� �� �룡��");
			if(flag3==0){
				flag3=1;flag4=0;
	    		try{
	    			Writexml myxml7=new Writexml(path+"/Brokeinto.xml");//ģ��������ҹ���
	    			myxml7.toWrite6(0,null);
	    			myxml7.toSave();
	    			//System.out.print("��Ա������Ϣ���³ɹ�����\n");
	    			}
	    			catch(ParserConfigurationException exp){
	    				exp.printStackTrace();
	    				 System.out.println("��Ա������Ϣд��ʧ�ܣ���");
	    				} catch (ClassNotFoundException e) {
	    				e.printStackTrace();
	    			}
			}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("�ֶ�ģ�ⴳ��")){
			//System.out.println("�ֶ�ģ�ⴳ�룡��");
			String sele1 = req.getParameter("sele1").trim();
			String sele2 = req.getParameter("sele2").trim();
			String sele3 = req.getParameter("sele3").trim();
			String se = sele1 + sele2 + sele3;                  //seΪcqgmc�������Һ�

			flag4=0;
    		try{
    			Writexml myxml7=new Writexml(path+"/Brokeinto.xml");
    			myxml7.toWrite6(0,se);
    			myxml7.toSave();
    			//System.out.print("��Ա������Ϣ���³ɹ�����\n");
    			}
    			catch(ParserConfigurationException exp){
    				exp.printStackTrace();
    				 System.out.println("��Ա������Ϣд��ʧ�ܣ���");
    				} catch (ClassNotFoundException e) {
    				e.printStackTrace();
    			}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("�� �� �� ��")){
			if(flag4==0){
				flag4=1;flag3=0;
	    		try{
	    			Writexml myxml7=new Writexml(path+"/Brokeinto.xml");//ģ��������ҹ���
	    			myxml7.toWrite6(1,null);
	    			myxml7.toSave();
	    			//System.out.print("�����������\n");
	    			}
	    			catch(ParserConfigurationException exp){
	    				exp.printStackTrace();
	    				} catch (ClassNotFoundException e) {
	    					
	    				e.printStackTrace();
	    			}  
			}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("ģ �� �� ��")){
			//System.out.println("ģ �� �� ������");
			String sele1 = req.getParameter("sele1").trim();
			String sele2 = req.getParameter("sele2").trim();
			String sele3 = req.getParameter("sele3").trim();
			String se = sele1 + sele2 + sele3;                  //seΪcqgmc�������Һ�
			
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
		
		if(str.equals("ȫ �� �� ��")){
			//System.out.println("ȫ �� �� ������");
			String sql = "update senser set sfsh='0' where sfsh='1'";
			try {
				Sqlupdate.sqlupdate1(sql);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			resp.sendRedirect("leftFrame.jsp");
		}
		
		if(str.equals("ȷ  ��")){
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
   	 		  Writexml myxml8=new Writexml(path+"/Station.xml");//ģ����� վ̨��ʱ����
   	 		  myxml8.toWrite7(list);
   	 		  myxml8.toSave();
   	 		 // System.out.print("�ֶ�վ̨��ʱ����  ���ݸ��³ɹ�����\n");
   	 		  }catch (ParserConfigurationException e) {
   	 			  
				e.printStackTrace();
			}
   	 		resp.sendRedirect("leftFrame.jsp");
		}
		
	}
}

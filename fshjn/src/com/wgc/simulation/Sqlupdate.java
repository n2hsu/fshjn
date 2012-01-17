package com.wgc.simulation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class Sqlupdate {
	static String user = "sa";
	static String psw = "sql123";
	static Connection con=null;
	static Statement stmt=null;
	static ResultSet stu_rs;
	static ResultSet course;
	
	 //����0-10�����
	public static int random(){
		double rand1 = Math.random()*10;
		int rand = new Double(rand1).intValue();
		return(rand);		
	}	

	//��ȡ��ǰ���ڣ�����������ʱ���룩
	public static String nyrsfm(){
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR); 
		int month = today.get(Calendar.MONTH)+1;
		int day = today.get(Calendar.DAY_OF_MONTH);
		int hour = today.get(Calendar.HOUR_OF_DAY);
		int minute = today.get(Calendar.MINUTE);
		int second = today.get(Calendar.SECOND); 
		return(year +"-"+ month +"-"+ day +" "+ hour +":"+ minute +":"+ second);
	}
	
	//��ȡ��ǰ���ڣ����������գ�
	public static String nyr(){
		Calendar today = Calendar.getInstance();		
		int year = today.get(Calendar.YEAR); 
		int month = today.get(Calendar.MONTH)+1;
		int day = today.get(Calendar.DAY_OF_MONTH);
		return(year +"-"+ month +"-"+ day);
	}
	

	public static void sqlupdate() throws ClassNotFoundException{
		Calendar today = Calendar.getInstance();
		int week = today.get(Calendar.DAY_OF_WEEK)-1;
		int hour = today.get(Calendar.HOUR_OF_DAY);
		int minute = today.get(Calendar.MINUTE);
		int second = today.get(Calendar.SECOND);
		int time = hour*60+minute;
		String update = null;
		String insert = null;
		
//ģ�������ѧ¥��λ��,ÿ����ѧ¥��λ����Ϊ30��-----------------------------------------------------------------------
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
			stmt = con.createStatement();
			
			if(week==0||week==6){			//������������0-5����
				for(int jxl=1;jxl<6;jxl++){//jxl�����ѧ¥��
					String jh=Integer.toString(jxl);
					update = "update [test].[dbo].[building_info] set yycws='"+Integer.toString(random()/2)+"' where lh='"+jh+"'";
					stmt.execute(update);
				}
			}else{
				if(time>=450&&time<=720||time>=780&&time<=1080){		//7:30-12:00��13:00-18:00 ��20-30��
					for(int jxl=1;jxl<6;jxl++){//jxl�����ѧ¥��
						String jh=Integer.toString(jxl);
						update = "update [test].[dbo].[building_info] set yycws='"+Integer.toString(random()+20)+"' where lh='"+jh+"'";
						stmt.execute(update);
					}	
				}else{
					  if(time>720&&time<780||time>1080&&time<1200){    //12:00-13:00��18:00-20:00��5-15��
							for(int jxl=1;jxl<6;jxl++){//jxl�����ѧ¥��
								String jh=Integer.toString(jxl);
								update = "update [test].[dbo].[building_info] set yycws='"+Integer.toString(random()+5)+"' where lh='"+jh+"'";
								stmt.execute(update);
							}
					  }else{											//����ʱ���г�����Ϊ0
							for(int jxl=1;jxl<6;jxl++){//jxl�����ѧ¥��
								String jh=Integer.toString(jxl);				
								update = "update [test].[dbo].[building_info] set yycws='0' where lh='"+jh+"'"; 
								stmt.execute(update);
							}		  
					  }	  
				}
			
			}		

//ģ���Ͽο���,����ʱ��Ϊ8:10,10:25,13:40,15:55,18:10---------------------------------------------------------------------
		if(time==490&&second<5||time==625&&second<5
			||time==820&&second<5||time==955&&second<5
			||time==1090&&second<5){			//second<5��Ϊ��ʹ��ִֵ��һ��
			//�ܴ��ַ���
			String[] week2={"����","��һ","�ܶ�","����","����","����","����"};
			String week1 = week2[week];//week������
	
			//�������ֻ�����֯sql���
			String[] jslist = {"1","2","3","4","5"};
			String js = null;
			String str= null;
			if(time>=480&&time<=575){
				js = jslist[0];	
				str="SELECT distinct bjm,kch FROM course_list where xq='"+week1+"' and js='"+js+"'";
			}			
			else{
				if(time>=615&&time<=710){
					js = jslist[1];
					str="SELECT distinct bjm,kch FROM course_list where xq='"+week1+"' and js='"+js+"'";
				}			
				else{
					if(time>=810&&time<=905){
						js = jslist[2];
						str="SELECT distinct bjm,kch FROM course_list where xq='"+week1+"' and js='"+js+"'";
					}
					else{
						if(time>=945&&time<=1040){
							js = jslist[3];
							str="SELECT distinct bjm,kch FROM course_list where xq='"+week1+"' and js='"+js+"'";
						}
						else{
							if(time>=1080&&time<=1175){
								js = jslist[4];
								str="SELECT distinct bjm,kch FROM course_list where xq='"+week1+"' and js='"+js+"'";
							}
						}
					}
				}
			}
			//System.out.println(str);
	
			//�ҳ������Ͽεİ༶�κͳ���
		if(str!=null){
			course = stmt.executeQuery(str);
			ArrayList<String> a = new ArrayList<String>();
			ArrayList<String> a1 = new ArrayList<String>();	
			while(course.next())
			{
			  a.add(course.getString(1));
			  a1.add(course.getString(2));	
			}
			String[] bjm = new String[a.size()];
			String[] kch = new String[a1.size()];
			for(int i = 0; i < a.size(); i++)
			{
			  bjm[i]= (String)a.get(i);
			  kch[i]= (String)a1.get(i).trim();
			}
			
	/*		//����༶���Ϳγ̺�
			for(int i = 0; i < a.size(); i++)
			{
				System.out.print(bjm[i]+"  ");
			}
			
			for(int i = 0; i < a.size(); i++)
			{
				System.out.print(kch[i]+"  ");
			}
	*/
			//�Ͽεİ༶�м�����ѭ������,ÿ�ν���һ���࿼��
			if(bjm.length!=0){
			for(int q=0;q<bjm.length;q++){
				//��ѡ����ѧ�ű������
				stu_rs = stmt.executeQuery("SELECT xh FROM stu_info where bjm='"+bjm[q]+"'");	 
				ArrayList<String> a2 = new ArrayList<String>();		
				while(stu_rs.next())
				{
				  a2.add(stu_rs.getString(1));
				}
				String[] list = new String[a2.size()];		
				for(int i = 0; i < a2.size(); i++)
				{
				  list[i]= (String)a2.get(i).trim();
				}		

				//ѡ��һ��ǰn-3��ѧ�ţ�����ʹ�������������������ظ���nΪ����������Ϊһ��������3�˳ٵ������Σ�
				for(int j=0; j<a2.size()-3; j++){
					  //System.out.println("ѧ��["+j+"]"+ list[j]);					  
					  insert="insert into attendance(xjh,kch,skrq,jlsj) values ('"+list[j]+"','"+kch[q]+"','"+nyr()+"','"+nyrsfm()+"')";
					  stmt.execute(insert);
					  //System.out.println(insert);
				}				
				
				stu_rs.close();
			}
		  }		
		}//if(str!=null)����
	}//���ڽ���
		
//�豸��,ÿ��8:00ʹ�豸�𻵣�22:00�޸�----------------------------------------------------------------------
	if(time==480&&second<5){			//second<5��Ϊ��ʹ��ִֻ��һ��
			for(int j=0;j<3;j++){
			String sql;
			String[] a1={"1","2","12"};
			stu_rs = stmt.executeQuery("select cgqID from senser where cgqlxID='"+a1[j]+"'");
			
			//ѡ������ID
			ArrayList<String> a = new ArrayList<String>();		
			while(stu_rs.next())
			{
			  a.add(stu_rs.getString(1));		  
			}		
			String list[] = new String[a.size()];		
			for(int i = 0; i < a.size(); i++)
			{
			  list[i]= (String)a.get(i);
			}
			
			/*
			for(int i=0;i<list.length;i++){
				
				System.out.print(list[i].trim()+"  ");
			}
			System.out.println("");
			*/
			
			int w=5;
			if(j==2)
				w=10;			
			for(int i=0;i<w;i++){
				double rand1 = Math.random()*list.length;
				int rand = new Double(rand1).intValue();				
				sql = "update senser set sfsh='1' where cgqID='"+list[rand].trim()+"'";
				//System.out.println(sql);
				stmt.execute(sql);
			}
			stu_rs.close();
		}	
	}
	if(time==1320)
		stmt.execute("update senser set sfsh='0' where sfsh='1'");
//�豸�����------------------------------------------------------------------------------------		
	

		
		

		
		stmt.close();
		con.close(); 
		}catch(SQLException e){
				e.printStackTrace(); 
			}

		
		
		
		
	}//sqlupdate��������

//�ֶ�ģ����
	public static void sqlupdate1(String s) throws ClassNotFoundException{
	try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con1 = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
		Statement stmt1 = con1.createStatement();
		
		stmt1.execute(s);
		
		stmt1.close();
		con1.close(); 
		}catch(SQLException e){
				e.printStackTrace(); 
			}
}
	
}
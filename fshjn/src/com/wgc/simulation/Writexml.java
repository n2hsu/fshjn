package com.wgc.simulation;

import java.io.*;
import java.util.Calendar;

import org.w3c.dom.*; 

import javax.xml.parsers.*; 
import javax.xml.transform.*; 
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult;

import flex.messaging.io.ArrayList;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Writexml{
	private Document document;
	private String filename;
	static String user = "sa";
	static String psw = "sql123";	
	static String app = null;
	String str=null;
	String str_insert=null;
	String[] jsh=null;
	String[] list=null;
	String[] list1=null;
	String[] list2=null;
	String[] list3=null;
	 String lh=null;
	 String lc=null;
	 String js1=null;
	 String lon=null;
		static Connection con=null;
		static Statement stmt_com=null;
		static ResultSet rs;


//创建xml表
 public Writexml(String name) throws ParserConfigurationException{
  filename=name;
  DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
  DocumentBuilder builder=factory.newDocumentBuilder();
  document=builder.newDocument();
 }

 //保存xml
 public void toSave(){
	  try{
	   TransformerFactory tf=TransformerFactory.newInstance();
	   Transformer transformer=tf.newTransformer();
	   DOMSource source=new DOMSource(document);
	   transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
	   transformer.setOutputProperty(OutputKeys.INDENT,"yes");
	   PrintWriter pw=new PrintWriter(new FileOutputStream(filename));
	   StreamResult result=new StreamResult(pw);
	   transformer.transform(source,result);
	  }
	  catch(TransformerException mye){
	   mye.printStackTrace();
	  }
	  catch(IOException exp){
	   exp.printStackTrace();
	  }
	 }

 //产生0-10随机数
public static int random(){
	
	double rand1 = Math.random()*10;
	int rand = new Double(rand1).intValue();
	return(rand);
	
}
 
//获取当前日期
public static String timer(){
	Calendar today = Calendar.getInstance();
	
	int year = today.get(Calendar.YEAR); 
	int month = today.get(Calendar.MONTH)+1;
	int day = today.get(Calendar.DAY_OF_MONTH);
	int hour = today.get(Calendar.HOUR_OF_DAY);
	int minute = today.get(Calendar.MINUTE);
	int second = today.get(Calendar.SECOND); 
	return(year +"-"+ month +"-"+ day +" "+ hour +":"+ minute +":"+ second);
}

//模拟产生电脑温度，已存入数据库
@SuppressWarnings("unchecked")
public void toWrite() throws ClassNotFoundException{
	Statement stmt=null;
	Calendar today = Calendar.getInstance();
	Element root=document.createElement("computer");
	document.appendChild(root);
	String tim = timer();

	//周次字符化
	int week1 = today.get(Calendar.DAY_OF_WEEK)-1;
	String[] week2={"周日","周一","周二","周三","周四","周五","周六"};
	String week=null;
	week = week2[week1];//week是星期
	//System.out.println(week);

	
/* 8:00----480;9:35----575;
* 
* 10:15---615;11:50---710;
* 
* 13:30---810;15:05---905;
* 
* 15:45---945;17:20---1040;
* 
* 18:00---1080;19:35---1175;
*/	
	//节数数字化
	int hour = today.get(Calendar.HOUR_OF_DAY);
	int minute = today.get(Calendar.MINUTE);
	int time = hour*60+minute;
	int iii=0;
	String[] jslist = {"1","2","3","4","5"};
	String js = null;
	if(time>=480&&time<=575){
		js = jslist[0];	
		str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
	}			
	else{
		if(time>=615&&time<=710){
			js = jslist[1];
			str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
		}			
		else{
			if(time>=810&&time<=905){
				js = jslist[2];
				str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
			}
			else{
				if(time>=945&&time<=1040){
					js = jslist[3];
					str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
				}
				else{
					if(time>=1080&&time<=1175){
						js = jslist[4];
						str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
					}
				}
			}
		}
	}
	
	//System.out.print("今天是"+timer()+"  "+week+"    第"+js+"节      ");

//str为空说明没有教室上课，此时无需连接数据库查询
if(str!=null){	
	try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
		stmt = con.createStatement();
		rs = stmt.executeQuery(str);
 		
		//找出正在上课的教室
		ArrayList a = new ArrayList();		
		while(rs.next())
		{
		  a.add(rs.getString(1));		  
		}		
		jsh = new String[a.size()];		
		for(int i = 0; i < a.size(); i++)
		{
		  jsh[i]= (String)a.get(i);
		}
		rs.close();
		stmt.close();
		con.close();
		iii=a.size();
		
	}catch(SQLException e){
		e.printStackTrace(); 
	}
}else{
	iii=0;
}


//找出电脑温度的ID即传感器ID，其类型ID为1（注意：此项不能并入上列查询，当无教室上课时，上面的查询不进行，则无list值，此时下面无法进行）
try{
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
	stmt = con.createStatement();
	rs = stmt.executeQuery("select cgqID from senser where cgqlxID='1'");
	ArrayList a1 = new ArrayList();
	while(rs.next()){
		a1.add(rs.getString(1));
	}
	list = new String[a1.size()];
	for(int i = 0; i < a1.size(); i++){
		list[i]= (String)a1.get(i);
	}
	rs.close();
	stmt.close();
	}catch(SQLException e){
		e.printStackTrace(); 
	}

//无任何意义，只是为了输出
	/*
if(iii==0)
	System.out.println("当前没有教室上课！！");
else{
	System.out.print("当前上课的教室有： ");
	for(int i3=0;i3<iii;i3++){
		System.out.print(jsh[i3]+"  ");
	}
	System.out.print("\n");
}
*/
int index = 0;

//连接数据库，写入数据并写入对应xml表
try{
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
	stmt = con.createStatement();
	
//电脑温度   
for(int j=1;j<6;j++){               //i代表教室c代表楼层j代表教学楼
	 int pan = 0;
	 for(int c=1;c<6;c++){
		 for(int i=1;i<11;i++){
			 String lh=Integer.toString(j);
			 String lc=Integer.toString(c);
			if(i>9)
				js1=Integer.toString(i); 
			else
				js1="0"+Integer.toString(i);
			 
			 lon=lh+lc+js1;
			// System.out.print(lon+"  ");
			 
			if(iii==0){		//说明没有教室上课,全部温度为20度	
				 Element title= document.createElement("comtem");
				 root.appendChild(title);		
				 
				 Element id=document.createElement("id");
				 id.appendChild(document.createTextNode(lon));
				 title.appendChild(id);	
				 
				 Element value=document.createElement("value");
				 value.appendChild(document.createTextNode("20"));
				 title.appendChild(value);
				 
				 Element content=document.createElement("time");
				 content.appendChild(document.createTextNode(tim));
				 title.appendChild(content);
				 				 
				str_insert = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list[index++].trim()+"','20','"+tim+"')" ;
				//System.out.println(lon+"  "+str_insert);
				stmt.execute(str_insert);
				 
			 }else{				 //上课时的温度为50-70度
				 for(int i2=0;i2<iii;i2++){
					if(lon.equals(jsh[i2])){
						pan = 1;
						break;
					}
					else
						pan = 0;
				 }
				 
					if(pan==1){               //把上课的截取出来
						 //System.out.println(lon);
						String wendu = Integer.toString(random()*2+50);
						 Element title= document.createElement("comtem");
						 root.appendChild(title);	
						 
						 Element id=document.createElement("id");
						 id.appendChild(document.createTextNode(lon));
						 title.appendChild(id);	
						 
						 Element value=document.createElement("value");
						 value.appendChild(document.createTextNode(wendu));
						 title.appendChild(value);			 
						 
						 Element content=document.createElement("time");
						 content.appendChild(document.createTextNode(tim));
						 title.appendChild(content);
						 
						str_insert = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list[index++].trim()+"','"+wendu+"','"+tim+"')" ;
						//System.out.println(lon+"  "+str_insert);
						stmt.execute(str_insert);
						 
					 }else{				//不上课时为20度  
						 Element title= document.createElement("comtem");
						 root.appendChild(title);								 

						 Element id=document.createElement("id");
						 id.appendChild(document.createTextNode(lon));
						 title.appendChild(id);	
						 
						 
						 Element value=document.createElement("value");
						 value.appendChild(document.createTextNode("20"));
						 title.appendChild(value);			 
						 
						 Element content=document.createElement("time");
						 content.appendChild(document.createTextNode(tim));
						 title.appendChild(content);		
						 
						str_insert = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list[index++].trim()+"','20','"+tim+"')" ;
						//System.out.println(lon+"  "+str_insert);
						stmt.execute(str_insert);
				}				 
			 }	 
		 }
	 }
 }
stmt.close();
con.close(); 
}catch(SQLException e){
		e.printStackTrace(); 
	}
}

//模拟产生投影仪温度
@SuppressWarnings("unchecked")
public void toWrite1() throws ClassNotFoundException{
	Statement stmt1=null;
	Calendar today = Calendar.getInstance();
	Element root=document.createElement("projector");
	document.appendChild(root);
	String tim = timer();

	//周次字符化
	int week1 = today.get(Calendar.DAY_OF_WEEK)-1;
	String[] week2={"周日","周一","周二","周三","周四","周五","周六"};
	String week=null;
	week = week2[week1];//week是星期
	//System.out.println(week);

	//节数数字化
	int hour = today.get(Calendar.HOUR_OF_DAY);
	int minute = today.get(Calendar.MINUTE);
	int time = hour*60+minute;
	int iii=0;
	String[] jslist = {"1","2","3","4","5"};
	String js = null;
	if(time>=480&&time<=575){
		js = jslist[0];	
		str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
	}			
	else{
		if(time>=615&&time<=710){
			js = jslist[1];
			str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
		}			
		else{
			if(time>=810&&time<=905){
				js = jslist[2];
				str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
			}
			else{
				if(time>=945&&time<=1040){
					js = jslist[3];
					str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
				}
				else{
					if(time>=1080&&time<=1175){
						js = jslist[4];
						str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
					}
				}
			}
		}
	}

//str为空说明没有教室上课，此时无需连接数据库查询
if(str!=null){	
	try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
		stmt1 = con.createStatement();
		rs = stmt1.executeQuery(str);
 
		ArrayList a = new ArrayList();		
		while(rs.next())
		{
		  a.add(rs.getString(1));
		}		
		jsh = new String[a.size()];		
		for(int i = 0; i < a.size(); i++)
		{
		  jsh[i]= (String)a.get(i);
		}
		rs.close();
		stmt1.close();
		con.close();
		iii=a.size();
		
	}catch(SQLException e){
		e.printStackTrace(); 
	}
}else{
	iii=0;
}	

int index = 0;

//找出投影仪的ID即传感器ID，其传感器类型ID为2
try{
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
	stmt1 = con.createStatement();
	rs = stmt1.executeQuery("select cgqID from senser where cgqlxID='2'");
	ArrayList a1 = new ArrayList();		
	while(rs.next())
	{
	  a1.add(rs.getString(1));
	}		
	list1 = new String[a1.size()];		
	for(int i = 0; i < a1.size(); i++)
	{
	  list1[i]= (String)a1.get(i);
	}
	
	rs.close();
	stmt1.close();
	con.close();
	
}catch(SQLException e){
	e.printStackTrace(); 
}

try{
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
	stmt1 = con.createStatement();
//投影仪温度   上课时的温度为60-80度，不上课时为20度
for(int j=1;j<6;j++){               //i代表教室c代表楼层j代表教学楼
	 int pan = 0;
	 for(int c=1;c<6;c++){
		 for(int i=1;i<11;i++){
			 String lh=Integer.toString(j);
			 String lc=Integer.toString(c);
			if(i>9)
				js1=Integer.toString(i); 
			else
				js1="0"+Integer.toString(i);
			 
			lon=lh+lc+js1;
			// System.out.print(lon+"  ");
			 
			if(iii==0){				
				 Element title=document.createElement("projectortem");
				 root.appendChild(title);

				 Element id=document.createElement("id");
				 id.appendChild(document.createTextNode(lon));
				 title.appendChild(id);	
				 
				 
				 Element value=document.createElement("value");
				 value.appendChild(document.createTextNode("20"));
				 title.appendChild(value);
				 
				 Element content=document.createElement("time");
				 content.appendChild(document.createTextNode(tim));
				 title.appendChild(content);
				 
				str_insert = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list1[index++].trim()+"','20','"+tim+"')" ;
				//System.out.println(lon+"  "+str_insert);
				stmt1.execute(str_insert);
				 
			 }else{				 
				 for(int i2=0;i2<iii;i2++){
						if(lon.equals(jsh[i2])){
							pan = 1;
							break;
						}
						else 
							pan = 0;
					 }
					 
						if(pan==1){
							String wendu = Integer.toString(random()*2+60);
							
						 Element title=document.createElement("projectortem");
						 root.appendChild(title);

						 Element id=document.createElement("id");
						 id.appendChild(document.createTextNode(lon));
						 title.appendChild(id);	
						 						 
						 Element value=document.createElement("value");
						 value.appendChild(document.createTextNode(wendu));
						 title.appendChild(value);
						 
						 Element content=document.createElement("time");
						 content.appendChild(document.createTextNode(tim));
						 title.appendChild(content);

						 str_insert = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list1[index++].trim()+"','"+wendu+"','"+tim+"')" ;
						 //System.out.println(lon+"  "+str_insert);
						stmt1.execute(str_insert);	 
						 
						 
					 }else{
						 Element title=document.createElement("projectortem");
						 root.appendChild(title);
						 
						 Element id=document.createElement("id");
						 id.appendChild(document.createTextNode(lon));
						 title.appendChild(id);							 
						 
						 Element value=document.createElement("value");
						 value.appendChild(document.createTextNode("20"));
						 title.appendChild(value);
						 
						 Element content=document.createElement("time");
						 content.appendChild(document.createTextNode(tim));
						 title.appendChild(content);	
						 
						str_insert = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list1[index++].trim()+"','20','"+tim+"')" ;
						//System.out.println(lon+"  "+str_insert);
						stmt1.execute(str_insert);
					 }
				 }
			 }	 
		 }
	 
}
stmt1.close();
con.close(); 
}catch(SQLException e){
		e.printStackTrace(); 
	}
	
}

//教室温度，这里只考虑了夏天
public void toWrite2(int re){		//re参数代表炎热传递参数，在原来的基础上增加5摄氏度
	Calendar today = Calendar.getInstance();
	Element root=document.createElement("class");
	document.appendChild(root);
	String tim = timer();
	
	int hour = today.get(Calendar.HOUR_OF_DAY);
	int minute = today.get(Calendar.MINUTE);
	int time = hour*60+minute;

	if(time>=600&&time<=930){          //10:00-15:30 教室温度设为30-40度
	  for(int j=1;j<6;j++){               //i代表教室c代表楼层j代表教学楼
		   for(int c=1;c<6;c++){
			  for(int i=1;i<11;i++){
				 String lh=Integer.toString(j);
				 String lc=Integer.toString(c);
				if(i>9)
					js1=Integer.toString(i); 
				else
					js1="0"+Integer.toString(i);
				 
				lon=lh+lc+js1;
		 
				 Element title=document.createElement("classtemp");
				 root.appendChild(title);
				 
				 Element id=document.createElement("id");
				 id.appendChild(document.createTextNode(lon));
				 title.appendChild(id);	

				 Element value=document.createElement("value");
				 value.appendChild(document.createTextNode(Integer.toString(random()+30+re)));
				 title.appendChild(value);
				 
				 Element content=document.createElement("time");
				 content.appendChild(document.createTextNode(tim));
				 title.appendChild(content);			 		 
			   }
		   }
	  }
	} else{                          //其他时间设为20-30度
		for(int j=1;j<6;j++){               //i代表教室c代表楼层j代表教学楼
			 for(int c=1;c<6;c++){
				 for(int i=1;i<11;i++){
					 String lh=Integer.toString(j);
					 String lc=Integer.toString(c);
					if(i>9)
						js1=Integer.toString(i); 
					else
						js1="0"+Integer.toString(i);
					 
					lon=lh+lc+js1;
			 
					 Element title=document.createElement("classtemp");
					 root.appendChild(title);

					 Element id=document.createElement("id");
					 id.appendChild(document.createTextNode(lon));
					 title.appendChild(id);	

					 if(re==10){
						 re+=10;
					 }
					 Element value=document.createElement("value");
					 value.appendChild(document.createTextNode(Integer.toString(random()+20+re)));
					 title.appendChild(value);
					 
					 Element content=document.createElement("time");
					 content.appendChild(document.createTextNode(tim));
					 title.appendChild(content);
				 }
			 }
		}	 
	}

	
}

//模拟教室光线
public void toWrite3(){
	Calendar today = Calendar.getInstance();
	Element root=document.createElement("classlight");
	document.appendChild(root);
	String tim = timer();
	
	int hour = today.get(Calendar.HOUR_OF_DAY);
	int minute = today.get(Calendar.MINUTE);
	int time = hour*60+minute;
	
	
	if(time<=240||time>=1200){          //4:00以前，20:00以后 教室光线强度为0
	   for(int j=1;j<6;j++){               //i代表教室c代表楼层j代表教学楼
		   for(int c=1;c<6;c++){
		 	 for(int i=1;i<11;i++){
				 String lh=Integer.toString(j);
				 String lc=Integer.toString(c);
				if(i>9)
					js1=Integer.toString(i); 
				else
					js1="0"+Integer.toString(i);
				 
				 lon=lh+lc+js1;
		 
				 Element title=document.createElement("classlight");
				 root.appendChild(title);

				 Element id=document.createElement("id");
				 id.appendChild(document.createTextNode(lon));
				 title.appendChild(id);	
				 
				 Element value=document.createElement("value");
				 value.appendChild(document.createTextNode("0"));
				 title.appendChild(value);
				 
				 Element content=document.createElement("time");
				 content.appendChild(document.createTextNode(tim));
				 title.appendChild(content);
			 }
		  }
	   } 
	}
	if(time>240&&time<=420||time>=1080&&time<1200){         //4:00-7:00和18:00-20:00教室光线强度为0-100
	   for(int j=1;j<6;j++){               //i代表教室c代表楼层j代表教学楼
		   for(int c=1;c<6;c++){
		 	 for(int i=1;i<11;i++){
				 String lh=Integer.toString(j);
				 String lc=Integer.toString(c);
				if(i>9)
					js1=Integer.toString(i); 
				else
					js1="0"+Integer.toString(i);
				 
				 lon=lh+lc+js1;
		 
				 Element title=document.createElement("classlight");
				 root.appendChild(title);

				 Element id=document.createElement("id");
				 id.appendChild(document.createTextNode(lon));
				 title.appendChild(id);	
				 
				 Element value=document.createElement("value");
				 value.appendChild(document.createTextNode(Integer.toString(random()*10)));
				 title.appendChild(value);
				 
				 Element content=document.createElement("time");
				 content.appendChild(document.createTextNode(tim));
				 title.appendChild(content);
			 }
		  }
	   } 
	}
	if(time>420&&time<=600||time>=900&&time<1080){         //7:00-10:00和15:00-18:00教室光线强度为100-200
	   for(int j=1;j<6;j++){               //i代表教室c代表楼层j代表教学楼
		   for(int c=1;c<6;c++){
		 	 for(int i=1;i<11;i++){
				 String lh=Integer.toString(j);
				 String lc=Integer.toString(c);
				if(i>9)
					js1=Integer.toString(i); 
				else
					js1="0"+Integer.toString(i);
				 
				 lon=lh+lc+js1;
		 
				 Element title=document.createElement("classlight");
				 root.appendChild(title);

				 Element id=document.createElement("id");
				 id.appendChild(document.createTextNode(lon));
				 title.appendChild(id);	
				 
				 Element value=document.createElement("value");
				 value.appendChild(document.createTextNode(Integer.toString(random()*10+100)));
				 title.appendChild(value);
				 
				 Element content=document.createElement("time");
				 content.appendChild(document.createTextNode(tim));
				 title.appendChild(content);
			 }
		  }
	   } 
	}if(time>600&&time<900){                //10:00-15:00教室光线强度为200-300
	   for(int j=1;j<6;j++){               //i代表教室c代表楼层j代表教学楼
		   for(int c=1;c<6;c++){
		 	 for(int i=1;i<11;i++){
				 String lh=Integer.toString(j);
				 String lc=Integer.toString(c);
				if(i>9)
					js1=Integer.toString(i); 
				else
					js1="0"+Integer.toString(i);
				 
				 lon=lh+lc+js1;
		 
				 Element title=document.createElement("classlight");
				 root.appendChild(title);

				 Element id=document.createElement("id");
				 id.appendChild(document.createTextNode(lon));
				 title.appendChild(id);	
				 
				 Element value=document.createElement("value");
				 value.appendChild(document.createTextNode(Integer.toString(random()*10+200)));
				 title.appendChild(value);
				 
				 Element content=document.createElement("time");
				 content.appendChild(document.createTextNode(tim));
				 title.appendChild(content);
			 }
		  }
	   } 
	}

	
}

//模拟产生 教室即时人数  已经解决上课问题
@SuppressWarnings("unchecked")
public void toWrite4() throws ClassNotFoundException, SQLException{
	Statement stmt2=null;
	Calendar today = Calendar.getInstance();
	
	Element root=document.createElement("Class");
	document.appendChild(root);
	String tim = timer();
	
	int week1 = today.get(Calendar.DAY_OF_WEEK)-1;
	String[] week2={"周日","周一","周二","周三","周四","周五","周六"};
	String week=null;
	week = week2[week1];//week是星期
	
	int hour = today.get(Calendar.HOUR_OF_DAY);
	int minute = today.get(Calendar.MINUTE);
	int time = hour*60+minute;
	int iii = 0,renshu=0,z=0;
	String[] jslist = {"1","2","3","4","5"};
	String[] bjm=null;
	String js = null;
	if(time>=480&&time<=575){
		js = jslist[0];	
		str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
	}			
	else{
		if(time>=615&&time<=710){
			js = jslist[1];
			str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
		}			
		else{
			if(time>=810&&time<=905){
				js = jslist[2];
				str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
			}
			else{
				if(time>=945&&time<=1040){
					js = jslist[3];
					str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
				}
				else{
					if(time>=1080&&time<=1175){
						js = jslist[4];
						str="SELECT distinct jsh FROM course_list where xq='"+week+"' and js='"+js+"'";
					}
				}
			}
		}
}

if(str!=null){	
	try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
		stmt2 = con.createStatement();
		rs = stmt2.executeQuery(str);
 
		ArrayList a = new ArrayList();		
		while(rs.next())
		{
		  a.add(rs.getString(1));
		}		
		jsh = new String[a.size()];		
		for(int i = 0; i < a.size(); i++)
		{
		  jsh[i]= (String)a.get(i);
		}
		
		//选出上课班级
		rs=stmt2.executeQuery("SELECT distinct bjm FROM course_list where xq='"+week+"' and js='"+js+"'");
		ArrayList a1 = new ArrayList();		
		while(rs.next())
		{
		  a1.add(rs.getString(1));
		}		
		bjm = new String[a1.size()];		
		for(int i = 0; i < a1.size(); i++)
		{
		  bjm[i]= (String)a1.get(i);
		}		

		iii=a.size();
		rs.close();
		rs.close();
		stmt2.close();
		con.close();
		
		
	}catch(SQLException e){
		e.printStackTrace(); 
	}
}else{
	iii=0;
}

if(time>=480&&time<=710||time>=780&&time<=1200){  //8:00-11:50和13:00-20:00教室人数为0-70
	for(int j=1;j<6;j++){               //i代表教室c代表楼层j代表教学楼
		 for(int c=1;c<6;c++){
			 for(int i=1;i<11;i++){
				 String lh=Integer.toString(j);
				 String lc=Integer.toString(c);
				if(i>9)
					js1=Integer.toString(i); 
				else
					js1="0"+Integer.toString(i);
				 
				 lon=lh+lc+js1;
				// System.out.print(lon+"  ");

				 if(iii!=0){
					 int pan=0;
					 for(int k=0;k<iii;k++){
						 if(lon.equals(jsh[k])){//这里是上课的班级，定为有120人来上课（总共有两个班一起上，每班有63人）
							try{
								Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
								con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
								stmt2 = con.createStatement();	
								//计算上课人数
							rs=null;
							rs=stmt2.executeQuery("SELECT xh FROM [test].[dbo].[stu_info] where bjm='"+bjm[z].trim()+"' or bjm='"+bjm[z+1].trim()+"'");
							ArrayList a2 = new ArrayList();
							z=z+2;
							while(rs.next())
							{
								a2.add(rs.getString(1));
							}		
								renshu = a2.size()-6;
								//System.out.println("a2="+a2.size());						 
							 
							 Element title= document.createElement("Class_num");
							 root.appendChild(title);

							 Element id=document.createElement("id");
							 id.appendChild(document.createTextNode(lon));
							 title.appendChild(id);	
							 
							 Element value = document.createElement("value");
							 value.appendChild(document.createTextNode(""+renshu));
							 title.appendChild(value);				 
							 
							 Element content=document.createElement("time");
							 content.appendChild(document.createTextNode(tim));
							 title.appendChild(content);
							 
							 //System.out.println("lon="+lon);
							 //System.out.println("break了一次！！i="+i);
								rs.close();
								stmt2.close();
								con.close();
							 
							 pan=1;
							 break;							
							}catch(SQLException e){
								e.printStackTrace(); 
							}
						 }							 
					 }
					 if(pan==1)
						 continue;					 
				 }		
				 Element title= document.createElement("Class_num");
				 root.appendChild(title);

				 Element id=document.createElement("id");
				 id.appendChild(document.createTextNode(lon));
				 title.appendChild(id);	
				 
				 Element value = document.createElement("value");
				 value.appendChild(document.createTextNode(Integer.toString(random()*7)));
				 title.appendChild(value);				 
				 
				 Element content=document.createElement("time");
				 content.appendChild(document.createTextNode(tim));
				 title.appendChild(content);				 
			 }
	 	}
	}
}else{
	if(time>710&&time<780||time>1200&&time<1320){  //11:50-13:00和20:00-22:00教室人数为0-10
		for(int j=1;j<6;j++){               //i代表教室c代表楼层j代表教学楼
			 for(int c=1;c<6;c++){
				 for(int i=1;i<11;i++){
					 String lh=Integer.toString(j);
					 String lc=Integer.toString(c);
					if(i>9)
						js1=Integer.toString(i); 
					else
						js1="0"+Integer.toString(i);
					 
					lon=lh+lc+js1;
					// System.out.print(lon+"  ");
		 
					 Element title= document.createElement("Class_num");
					 root.appendChild(title);

					 Element id=document.createElement("id");
					 id.appendChild(document.createTextNode(lon));
					 title.appendChild(id);	
					 
					 Element value = document.createElement("value");
					 value.appendChild(document.createTextNode(Integer.toString(random())));
					 title.appendChild(value);					 
					 
					 Element content=document.createElement("time");
					 content.appendChild(document.createTextNode(tim));
					 title.appendChild(content);
				 }
		 	}
	 	}
	}else{					//其余时间教室人数为0
			for(int j=1;j<6;j++){               //i代表教室c代表楼层j代表教学楼
				 for(int c=1;c<6;c++){
					 for(int i=1;i<11;i++){
						 String lh=Integer.toString(j);
						 String lc=Integer.toString(c);
						if(i>9)
							js1=Integer.toString(i); 
						else
							js1="0"+Integer.toString(i);
						 
						lon=lh+lc+js1;
						// System.out.print(lon+"  ");
			 
						 Element title= document.createElement("Class_num");
						 root.appendChild(title);

						 Element id=document.createElement("id");
						 id.appendChild(document.createTextNode(lon));
						 title.appendChild(id);	
						 
						 Element value = document.createElement("value");
						 value.appendChild(document.createTextNode("0"));
						 title.appendChild(value);						 
						 
						 Element content=document.createElement("time");
						 content.appendChild(document.createTextNode(tim));
						 title.appendChild(content);

					 }
			 	}
		 	}
		}	
}	

}

//模拟产生 站台即时人数
@SuppressWarnings("unchecked")
public void toWrite5() throws ClassNotFoundException{
	Statement stmt3=null;
	Calendar today = Calendar.getInstance();
	Element root = document.createElement("station");
	document.appendChild(root);
	String tim = timer();
	String str1 = null;
	
	int week1 = today.get(Calendar.DAY_OF_WEEK)-1;
	int hour = today.get(Calendar.HOUR_OF_DAY);
	int minute = today.get(Calendar.MINUTE);
	int time = hour*60+minute;
/*站台最大人数为10	
* 已经分开周六和周日的情况
7:30-8:10和9:35-10:20和11:55-12:10和13:00-13:30和15:10-15:45和	17:25-18:00和19:40-20:00站台人数为5-10人
*/
try{
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
	stmt3 = con.createStatement();
	
	rs = stmt3.executeQuery("select cgqID from senser where cgqlxID='10'");
	ArrayList a1 = new ArrayList();
	while(rs.next()){
		a1.add(rs.getString(1));
	}
	list = new String[a1.size()];
	for(int i = 0; i < a1.size(); i++){
		list[i]= (String)a1.get(i);
	}
	rs.close();
	
	int index=0;
if(week1==1||week1==2||week1==3||week1==4||week1==5){  //周一-----周五情况
	if(time>=450&&time<=490||time>=575&&time<=620||time>=715&&time<730||time>=780&&time<=810||
		time>=910&&time<=945||time>=1045&&time<=1080||time>=1180&&time<=1200){
	for(int j=1;j<4;j++){//j代表路线，假设有三条路线；i代表站台个数，假设有5个站台
		for(int i=1;i<9;i++){
			if(j==2&&i==7){
				index = index+2;
				break;
			}
				
			String v = Integer.toString(random()/3+7);
			Element title = document.createElement("Station_num");
			root.appendChild(title);

			 Element id=document.createElement("id");
			 id.appendChild(document.createTextNode(""+j+i));
			 title.appendChild(id);	
			 
			Element value = document.createElement("value");
			value.appendChild(document.createTextNode(v));
			title.appendChild(value);
			
			 Element content=document.createElement("time");
			 content.appendChild(document.createTextNode(tim));
			 title.appendChild(content);
			 
			 str1 = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list[index++].trim()+"','"+v+"','"+tim+"')" ;
			 //System.out.println(str1);
			 stmt3.execute(str1);
		 }
	  }	
 }else{ if(time>360&&time<1350){										//其余时间为0-5人
		for(int j=1;j<4;j++){
			for(int i=1;i<9;i++){
				if(j==2&&i==7){
					index = index+2;
					break;
				}			
				String v1 = Integer.toString(random()/2);
				Element title = document.createElement("Station_num");
				root.appendChild(title);

				 Element id=document.createElement("id");
				 id.appendChild(document.createTextNode(""+j+i));
				 title.appendChild(id);	
				 
				Element value = document.createElement("value");
				value.appendChild(document.createTextNode(v1));
				title.appendChild(value);
				
				 Element content=document.createElement("time");
				 content.appendChild(document.createTextNode(tim));
				 title.appendChild(content);	
				 
				 str1 = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list[index++].trim()+"','"+v1+"','"+tim+"')" ;
				 //System.out.println(str1);
				 stmt3.execute(str1);
			 }
		  }
     }else{
    	 for(int j=1;j<4;j++){
 			for(int i=1;i<9;i++){
 				if(j==2&&i==7){
 					index = index+2;
 					break;
 				}			
 				String v1 = "0";
 				Element title = document.createElement("Station_num");
 				root.appendChild(title);

 				 Element id=document.createElement("id");
 				 id.appendChild(document.createTextNode(""+j+i));
 				 title.appendChild(id);	
 				 
 				Element value = document.createElement("value");
 				value.appendChild(document.createTextNode(v1));
 				title.appendChild(value);
 				
 				 Element content=document.createElement("time");
 				 content.appendChild(document.createTextNode(tim));
 				 title.appendChild(content);	
 				 
 				 str1 = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list[index++].trim()+"','"+v1+"','"+tim+"')" ;
 				 //System.out.println(str1);
 				 stmt3.execute(str1);
 			 }
 		  }
     }
 }
 }else{                       //周六和周日站台人数为0-10
	 if(time>360&&time<1350){
		for(int j=1;j<4;j++){//j代表路线，假设有三条路线；i代表站台个数
			for(int i=1;i<9;i++){
				if(j==2&&i==7){
					index = index+2;
					break;
				}
				String v = Integer.toString(random());				
				Element title = document.createElement("Station_num");
				root.appendChild(title);

				 Element id=document.createElement("id");
				 id.appendChild(document.createTextNode(""+j+i));
				 title.appendChild(id);	
				 
				Element value = document.createElement("value");
				value.appendChild(document.createTextNode(Integer.toString(random())));
				title.appendChild(value);
				
				 Element content=document.createElement("time");
				 content.appendChild(document.createTextNode(tim));
				 title.appendChild(content);
				 
				 str1 = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list[index++].trim()+"','"+v+"','"+tim+"')" ;
				 //System.out.println(str1);
				 stmt3.execute(str1);
			 }
		  }
 		}else{
 			for(int j=1;j<4;j++){
 				for(int i=1;i<9;i++){
 					if(j==2&&i==7){
 						index = index+2;
 						break;
 					}			
 					String v1 = "0";
 					Element title = document.createElement("Station_num");
 					root.appendChild(title);

 					 Element id=document.createElement("id");
 					 id.appendChild(document.createTextNode(""+j+i));
 					 title.appendChild(id);	
 					 
 					Element value = document.createElement("value");
 					value.appendChild(document.createTextNode(v1));
 					title.appendChild(value);
 					
 					 Element content=document.createElement("time");
 					 content.appendChild(document.createTextNode(tim));
 					 title.appendChild(content);	
 					 
 					 str1 = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list[index++].trim()+"','"+v1+"','"+tim+"')" ;
 					 //System.out.println(str1);
 					 stmt3.execute(str1);
 				 }
 			  }
 		}
 }
Calendar now = Calendar.getInstance();		
int year = now.get(Calendar.YEAR); 
int month = now.get(Calendar.MONTH)+1;
int day = now.get(Calendar.DAY_OF_MONTH);
String t = year+"-"+month+"-"+day;

stmt3.execute("delete from test.dbo.senser_note where jlsj<'"+t+"'");//删除两天前是所有记录
stmt3.close();
con.close(); 
}catch(SQLException e){
		e.printStackTrace(); 
	}
}

//模拟人员闯入
@SuppressWarnings("unchecked")
public void toWrite6(int t,String s) throws ClassNotFoundException{
	Statement stmt4=null;
	String tim = timer();
	 try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", user, psw);
			stmt4 = con.createStatement();
			rs = stmt4.executeQuery("select cgqID,cgqmc from senser where cgqlxID='8'");
			
			ArrayList a = new ArrayList();
			ArrayList a1 = new ArrayList();	
			while(rs.next())
			{
			  a.add(rs.getString(1));
			  a1.add(rs.getString(2));
			}
			String[] list2 = new String[a.size()];  //list2是cgqID，向数据库写
			String[] list3 = new String[a1.size()]; //list3是cgqmc，向xml表写
			for(int i = 0; i < a.size(); i++)
			{
			  list2[i]= ((String)a.get(i)).trim();
			  list3[i]= ((String) a1.get(i)).trim();
			}
			if(t==0){
				if(s==null){
				int qwe = random()*25;
				app = list3[qwe];				//app临时存储
				String sql12 = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+list2[qwe]+"','1','"+tim+"')" ;
				// System.out.println(sql12);
				stmt4.execute(sql12);
				}else{
					app = s;				//app临时存储
					String sql12 = "insert into test.dbo.senser_note(cgqID,cgqz,jlsj) values ('"+app+"','1','"+tim+"')" ;
					// System.out.println(sql12);
					stmt4.execute(sql12);
				}
			}
			
			
			Element root=document.createElement("data");
			document.appendChild(root);
				 
			 Element id = document.createElement("id");
			 id.appendChild(document.createTextNode(app));
			 root.appendChild(id);
			 
			 Element value = document.createElement("value");
			 value.appendChild(document.createTextNode(""+t));
			 root.appendChild(value);
			 
			 Element content=document.createElement("time");
			 content.appendChild(document.createTextNode(tim));
			 root.appendChild(content);
			
			 //System.out.println("cgqID="+list2[qwe].trim()+"  cgqmc="+list3[qwe]);
			

	 
	 rs.close();
	 stmt4.close();
	 con.close(); 
	 }catch(SQLException e){
	 		e.printStackTrace(); 
	 	} 
}

//手动模拟站台人数
public void toWrite7(String[] list){
	Element root = document.createElement("station");
	document.appendChild(root);
	String tim = timer();
	int a = 0;
	
	for(int j=1;j<4;j++){//j代表路线，假设有三条路线；i代表站台个数
		for(int i=1;i<9;i++){
			if(j==2&&i==7){
				break;
			}			
			Element title = document.createElement("Station_num");
			root.appendChild(title);

			 Element id=document.createElement("id");
			 id.appendChild(document.createTextNode(""+j+i));
			 title.appendChild(id);
			 
			Element value = document.createElement("value");
			value.appendChild(document.createTextNode(list[a++]));
			title.appendChild(value);
			
			 Element content=document.createElement("time");
			 content.appendChild(document.createTextNode(tim));
			 title.appendChild(content);

		 }
	  }	 
}

} 

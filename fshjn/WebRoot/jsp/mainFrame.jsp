<%@ page language="java" import="java.util.*" pageEncoding="gb2312" errorPage="mainFrame.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page language="java" 
    import="javax.xml.parsers.DocumentBuilder,javax.xml.parsers.DocumentBuilderFactory"
    import="java.io.File,org.w3c.dom.*,java.net.*,java.text.*,javax.xml.parsers.*,org.xml.sax.*"
 %>
<%response.setHeader("Refresh","5");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>��ʾ</title>
  </head>
  
  <body bgcolor="#91e4fc">
    <%
    HttpSession session1 = request.getSession();
    HttpSession session2 = request.getSession();
    String txt = null;
    
  InputSource classtem = new InputSource("../webapps/wgc/xml/Classtem.xml");
     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
     DocumentBuilder db = dbf.newDocumentBuilder();
     Document doc = db.parse(classtem);
	 
	 NodeList time = doc.getElementsByTagName("time");
	 String t= time.item(0).getFirstChild().getNodeValue();	 
	 NodeList value = doc.getElementsByTagName("value");
	 int v= Integer.parseInt(value.item(1).getFirstChild().getNodeValue());

if((session1.getAttribute("tinfo")+"").equals(t)){
	txt = " �ϴ�����ģ��ʱ���ǣ�"+t+"<br>��ǰһ�������Ѿ�ֹͣģ�⣡��<br>";
	out.println(txt);
	session.setAttribute("info",txt);
}else{
	 session1.setAttribute("tinfo",t);
	 if(v<40){
	 
	 if(session.getAttribute("info")!=null){
     	txt = (session.getAttribute("info")+"")+t+" ģ����������1042��<br>";
	 }else{
	 	txt = " �ϴ�����ģ��ʱ���ǣ�"+t+"<br>"; 
	 }
     session.setAttribute("info",txt);
     out.println(session.getAttribute("info"));
     	if(txt.length()>700){
     		session.setAttribute("info","");
    	 }
     }else{
          txt = (session.getAttribute("info")+"")+t+" ��������ģ���У��¶�Ϊ40-50��<br>";
    	  session.setAttribute("info",txt);
    	  out.println(txt);
     }
}


  InputSource broke = new InputSource("../webapps/wgc/xml/Brokeinto.xml");
     DocumentBuilderFactory db1 = DocumentBuilderFactory.newInstance();
     DocumentBuilder db2 = db1.newDocumentBuilder();
     Document doc2 = db2.parse(broke);

	 NodeList bro = doc2.getElementsByTagName("id");
	 String broID= bro.item(0).getFirstChild().getNodeValue();
	 NodeList value1 = doc2.getElementsByTagName("value");
	 int b=Integer.parseInt(value1.item(0).getFirstChild().getNodeValue());
	 NodeList tt = doc2.getElementsByTagName("time");
	 String ttt = tt.item(0).getFirstChild().getNodeValue();
	
	if(b==0){
		if((session2.getAttribute("info1")+"").equals("null")){
			session2.setAttribute("info1","0");
	 		txt = (session.getAttribute("info")+"")+ttt+" ����ķ����Ϊ��"+broID+"<br>";
     		session.setAttribute("info",txt);
     		out.println(ttt+" ����ķ����Ϊ��"+broID+"<br>");
		}
	}else{
		if((session2.getAttribute("info1")+"").equals("0")){
		txt = (session.getAttribute("info")+"")+" �����������<br>";
		session.setAttribute("info",txt);
		session2.setAttribute("info1","null");
		}
		}

%>
  </body>
</html>

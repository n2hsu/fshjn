<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>温馨提示</title>
    <style type="text/css">
<!--
.STYLE1 {color: #CC3333}
-->
    </style>
</head>
  
  <body background="../images/JspPic/4.jpg">
     小提示：<br/>
     <span class="STYLE1">开始模拟数据：</span>模拟的数据有：教室温度250个，教室光线强度250个,电脑温度250个，投影仪温度250个，
     站台人数22个，电脑损坏5个，投影仪损坏5个，电灯损坏10个，其中电脑、投影仪、电灯损坏只在每天8:00模拟损坏，当天21:00模拟修好，其余数据每5秒模拟一次。<br>
     <span class="STYLE1">停止模拟数据：</span>停止一切数据的模拟。<br>
     <span class="STYLE1">天气炎热：</span>模拟教室温度250个，气温范围：40-50度。<br>
    <span class="STYLE1">天气正常：</span>开始模拟气温正常数据，10:30-15:30为30-40度，其余20-30度。<br>
    <span class="STYLE1">模拟闯入：</span>请您打开音响，系统自动模拟一个房间被闯入，您只有取消警报后才能模拟下一次。<br>
    <span class="STYLE1">消除警报：</span>解除警报。<br>
    <span class="STYLE1">模拟损坏：</span>点击后系统就会按照您选择的类别、楼号、楼层、教室进行损坏模拟，例如：类别选电脑，楼号为1，楼层为1，教室为01，
	代表1101教室的电脑已经模拟损坏，可到前台"教室信息--设备信息"里查看结果。<br>
    <span class="STYLE1">全部修复：</span>将所有损坏的电器修复，包括电脑、投影仪、电灯。<br>
    <span class="STYLE1">手动模拟闯入：</span>教室号=楼号+楼层+教室，其中楼号：1对应信息楼，2对应材料楼，3对应文学楼，4对应外语楼，5对应经济楼。<br>
    <span class="STYLE1">解除警报：</span>解除警报。<br>
    <span class="STYLE1">确 定：</span>点击后，站台人数会更新为您所选的(注意：2线路的七、八站台已废弃)，同时系统的正常数据会停止模拟，可到前台"校园公交--站台信息"里查看结果。<br>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>后台模拟程序</title>
  <script type="text/javascript">
  var jsh = 0;

     function Form3click1(){
  document.form3.action="leftFrame";
  alert("执行完毕!!");
  }
  
  function Form1click(){
  document.form1.action="leftFrame";
  }
  
    function Form1click1(){
    jsh = 0;
  document.form1.action="leftFrame";
  } 
  
  function myclick(){
  if( !document.form2.che1.checked && !document.form2.che2.checked){
  	alert("请选择类别!!");
  }else{
    var se1 = document.getElementById("sele1");
    var s1 = se1.options[se1.selectedIndex].text;
    var se2 = document.getElementById("sele2");
    var s2 = se2.options[se2.selectedIndex].text;
    var se3 = document.getElementById("sele3");
    var s3 = se3.options[se3.selectedIndex].text;
    cgqmc = s1 + s2 + s3;
    
   	document.form2.action="leftFrame";
   	if(document.form2.che1.checked)
   	 alert(cgqmc+"教室的电脑已损坏!!");
   	if(document.form2.che2.checked)
   	 alert(cgqmc+"教室的投影仪已损坏!!");
  }  
  }
  function myclick1(){
  	document.form2.action="leftFrame";
  	alert("全部修复已完成!!");
  }
    function myclick2(){
    var se1 = document.getElementById("sele1");
    var s1 = se1.options[se1.selectedIndex].text;
    var se2 = document.getElementById("sele2");
    var s2 = se2.options[se2.selectedIndex].text;
    var se3 = document.getElementById("sele3");
    var s3 = se3.options[se3.selectedIndex].text;
    cgqmc = s1 + s2 + s3;
    if(jsh==0){
    jsh = 1;
  	document.form2.action="leftFrame";
  	}else{  	
  	 alert("请先解除警报!!");
  	}
  }
    function myclick3(){
    jsh = 0;
  	document.form2.action="leftFrame";
  }
  
  </script>
  <style type="text/css">
<!--
.STYLE1 {color: #FF66CC}
-->
  </style>
  </head>
  <body background="../images/JspPic/2.jpg">
 <form name="form1" method="get">
	  <label><span class="STYLE1">系统自动模拟</span></label>
	  <br><br>
              <input type="submit" value="开始模拟数据" name="bt1" id="bt1" onClick="Form1click()"/>
              <input type="submit" value="停止模拟数据" name="bt1" id="bt2" onClick="Form1click()"/>
<br><br>
			  <input type="submit" value="天 气 炎 热 " name="bt1" id="bt3" onClick="Form1click()"/>
              <input type="submit" value="天 气 正 常" name="bt1" id="bt4" onClick="Form1click()"/>
<br><br>
		<input type="submit" value=" 模 拟 闯 入" name="bt1" id="bt5" onClick="Form1click()"/>
		<input type="submit" value="解 除 警 报" name="bt1" id="bt6" onClick="Form1click1()"/>
 </form>
 
<form name="form2" method="get">
	  <label><span class="STYLE1">人工损坏闯入模拟</span></label>
	  <br><br>类别：
	<input type="checkbox" id="che1" name="che1" value="1" checked="checked"/>电脑
	<input type="checkbox" id="che2" name="che2" value="2"/>投影仪<br><br>
	楼号<select id="sele1" name="sele1">  
	<option>1</option>
    <option>2</option>
    <option>3</option>
    <option>4</option>
    <option>5</option>
   </select>
   	楼层<select id="sele2" name="sele2">
	<option>1</option>
    <option>2</option>
    <option>3</option>
    <option>4</option>
    <option>5</option>
   </select>
    教室<select id="sele3" name="sele3">  
	<option>01</option>
    <option>02</option>
    <option>03</option>
    <option>04</option>
    <option>05</option>
	<option>06</option>
    <option>07</option>
    <option>08</option>
    <option>09</option>
    <option>10</option>
   </select><br><br>
    <input type="submit" value="模 拟 损 坏" name="bt1" id="bt7" onClick="myclick()"/>
    <input type="submit" value="全 部 修 复" name="bt1" id="bt8" onClick="myclick1()"/><br><br>
	<input type="submit" value="手动模拟闯入" name="bt1" id="bt9" onClick="myclick2()"/>
	<input type="submit" value="解 除 警 报" name="bt1" id="bt10" onClick="myclick3()"/>
</form>
<form name="form3" method="get">
<label><span class="STYLE1">人工站台人数模拟</span></label>
<br><br> <label id="lb1">2线路的七、八站台已废弃</label><br><br>
<label>线路：</label>     
<select id="sele4" name="sele4" onClick="form3click()">
	<option>1</option>
    <option>2</option>
    <option>3</option>
   </select><br><br>
   <label>一站台</label>
<select id="sele5" name="sele5">
	<option>1</option>
    <option>2</option>
    <option>3</option>
	<option>4</option>
    <option>5</option>
    <option>6</option>
	<option>7</option>
    <option>8</option>
    <option>9</option>
	<option>0</option>
   </select>
   <label>二站台</label>
<select id="sele6" name="sele6">
	<option>1</option>
    <option>2</option>
    <option>3</option>
	<option>4</option>
    <option>5</option>
    <option>6</option>
	<option>7</option>
    <option>8</option>
    <option>9</option>  
	<option>0</option>
   </select><br><br>
   <label>三站台</label>
<select id="sele7" name="sele7">
	<option>1</option>
    <option>2</option>
    <option>3</option>
	<option>4</option>
    <option>5</option>
    <option>6</option>
	<option>7</option>
    <option>8</option>
    <option>9</option>  
	<option>0</option>
   </select>
   <label>四站台</label>
<select id="sele8" name="sele8">
	<option>1</option>
    <option>2</option>
    <option>3</option>
	<option>4</option>
    <option>5</option>
    <option>6</option>
	<option>7</option>
    <option>8</option>
    <option>9</option>  
	<option>0</option>
   </select><br><br>
   <label>五站台</label>
<select id="sele9" name="sele9">
	<option>1</option>
    <option>2</option>
    <option>3</option>
	<option>4</option>
    <option>5</option>
    <option>6</option>
	<option>7</option>
    <option>8</option>
    <option>9</option>  
	<option>0</option>
   </select>
   <label>六站台</label>
<select id="sele10" name="sele10">
	<option>1</option>
    <option>2</option>
    <option>3</option>
	<option>4</option>
    <option>5</option>
    <option>6</option>
	<option>7</option>
    <option>8</option>
    <option>9</option>  
	<option>0</option>
   </select><br><br>
   <label>七站台</label>
<select id="sele11" name="sele11">
	<option>1</option>
    <option>2</option>
    <option>3</option>
	<option>4</option>
    <option>5</option>
    <option>6</option>
	<option>7</option>
    <option>8</option>
    <option>9</option>  
	<option>0</option>
   </select>
   <label>八站台</label>
<select id="sele12" name="sele12">
	<option>1</option>
    <option>2</option>
    <option>3</option>
	<option>4</option>
    <option>5</option>
    <option>6</option>
	<option>7</option>
    <option>8</option>
    <option>9</option>  
	<option>0</option>
   </select><br><br>
<input type="submit" value="确  定" name="bt1" id="bt11" onClick="Form3click1()"/>
</form>
 
   </body>
</html>

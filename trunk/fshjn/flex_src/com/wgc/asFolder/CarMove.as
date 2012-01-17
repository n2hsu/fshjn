package com.wgc.asFolder{ 
 import flash.geom.Point; 
 import mx.effects.Move; 

	 public class CarMove extends Move{ 
	
		  public function CarMove(p1:Point,p2:Point,target:Object){ 
		
			   var carMove:Move=new Move(); 
			   this.target=target;
			   this.xFrom=p1.x;this.yFrom=p1.y; 
			   this.xTo=p2.x;this.yTo=p2.y; 
			   this.play(); 
			   this.duration=5000; 
		  } 
	
	 } 
}

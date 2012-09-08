package com.sera.util;

import java.util.ArrayList;
import java.util.List;


public class ElemanTip {
   public static ElemanTip degiskenler=null;
   public static ElemanTip getInstance(){
	   if(degiskenler==null) 
		   return degiskenler=new ElemanTip();
	       return degiskenler;
   }
   
   public List<String> agacElemanlar=(List<String>) new ElemanTip();
   
   public List<String> getagacElemanlar() {
	return agacElemanlar;
}

public void setagacElemanlar(List<String> agacElemanlar) {
	this.agacElemanlar = agacElemanlar;
}

public ElemanTip(){
	   agacElemanlar=new ArrayList<String>();
	   agacElemanlar.add("Kök");
	   agacElemanlar.add("Yaprak");
	   agacElemanlar.add("Dal");
   }
	

}

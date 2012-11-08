<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Değer Giriş Ekranı</title>
<link rel="stylesheet" href="<c:url value="/resources/css/form/general.css"/>"></link> 
<link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>" rel="stylesheet" type="text/css" />  
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/form.css"/>" type="text/css" />  
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/yapi/agac.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/zebra/zebra_dialog.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/zebra_dialog.css"/>" type="text/css" />

<script>
  $(document).ready(function(){
    $("#formgiris").validate();
  });
  </script>
<script type="text/javascript">
       var globalid;
       var dateKontrol;
       function girisKaydet(){
    	   $.ajax({ 
   	        type: "POST",
   	        url: $("#formgiris").attr('action'),
   	        data:$("#formgiris").serialize(),
   	        cache: false,
   	        async: false,
   	        error: function(e){
   	        $.Zebra_Dialog('Error: ' + e);
               } 
   	        });
    	   $.Zebra_Dialog('Kaydedildi');
    	   return true;
    	   
        }
	        function doAjaxPost(aydi,seviye,tip,tip2) {
	        	
	        if (dateKontrol==1){	
	        // get the form values
	           globalid=aydi;
	           var sev=seviye;
	           var tipp=tip;
	           //tip2 lazım oldu. çünkü sabit ise form gelmeyecek. elle ise gelecek
	           var tipp2=tip2;
	          // var tirnak="'";
	        
	        $.ajax({ 
	        type: "POST",
	        url: "/sera/cenvsabit/sabitGir/dalgetir.htm",
	        data: "id=" + globalid ,
	        cache: false,
	        success: function(response){
	        // we have the response
	        
	        var elements=$('.katmanlar').filter(function(){
		    	return (this.id.replace('div','')>sev);
		    });
	        
	        $(".yaprakdiv").remove();
	        $(elements).remove();
	           
		        for(var i =0 ; i < response.length ; i++){
		        		
		        	//$("input:[id>"+response[i].seviye+"]").remove();
					    if (i==0){
					    	 $("#kok").append('<table width="690px" align="center" id="div'+response[i].seviye+'" class="katmanlar"><tbody><tr></tr></tbody></table>');  
					    }
					
					    
					    $("#div"+response[i].seviye).append(
					    		'<table width="230px" align="left" class="table_sil">'+
					    		'<tr align="center">'+
					    		'<input type="submit" class="detay_dugme" '+
		                		'value="'+
		        		         response[i].baslik+
		        		        '" onclick="doAjaxPost('+
		        		         response[i].id+
		        				','+response[i].seviye+
		        				',\''+response[i].tip1+'\''+
		        				',\''+response[i].tip2+'\')"'+
		        				'/>'+
		        				'</tr>'+
		        				'</table>');
					    
				   if(i+1==response.length){
					   $("#div"+response[i].seviye).append('<tr><div class="cizgi2"></div></tr>');
				   } 	
		        	
		                         
		                
		        }
		        
		    if(tipp=="Yaprak" && tipp2=="Elle"){
	        	$("#kok").append('<div align="center" class="yaprakdiv" id="yaprakid"></div>');
	        	$("#yaprakid").append('<br/><form:form style="margin-left:-70px;" onsubmit="girisKaydet();return false;" cssClass="formstil" name="formgiris" id="formgiris" action="/sera/cenvgiris/giriskaydet.htm" method="POST"  modelAttribute="cenvgiris" enctype="multipart/form-data">'+
	        	'<form:hidden path="baslikId" size="40" value="'+
	        	aydi+
	        	'"/><br/>'+
	        	'<form:hidden id="girisid" path="id" size="40" value="3" />'+
	        	'<table>'+
	        	'<tr><td class="formyazi" align="right">Dönem:</td>'+
	        	'<td class="inputyazi"><form:input id="txtDate" readonly="true" path="tarih" size="20" class="required" minlength="7" maxlength="7"/>(Örn:2012-01)</td>'+
	        	'</tr>'+
	        	'<tr>'+
    	        '<td class="formyazi" align="right">Değer:</td>'+
	        	'<td class="inputyazi"><form:input id="deger" class="required"  path="deger" size="20"/></td>'+
	        	'</tr>'+
	        	'<tr><td></td>'+
    			'<td class="submit"><input type="submit" class="submit" id="idsubmit" value="Kaydet"></input></td>'+
    			'</tr>'+
    			'</table>'+
    			'</form:form>');
	        	 //üstte bulunan döneme ne yazıldı ise buraya da onu yazmamız lazım
	        	$("#txtDate").val($("#ustDonem").val());
	        	 $("#txtDate").focus(); 
	        	 //form oluştuktan sonra validate olayını deniyoruz. inşallah tutar
	        	 $("#formgiris").validate();
	        }
		        
		    
	               
	        },
	        error: function(e){
	        $.Zebra_Dialog('Error: ' + e);
            } 
	        }); 
	        }
	        }
	        
	         	//bir üst jquery versiyonunda live yerine on kullan
	        		  $("#ustDonem").live('blur',
		        		   function() {
		        		      if(!validateDate("ustDonem"))
		        		      { 
		        		          $.Zebra_Dialog('Geçersiz Tarih!!!');
		        		          dateKontrol=-1;
		        		      }else{
		        		    	  dateKontrol=1;
		        		      }
		        		   }
	        		  );	
	        		 
	        		//Functions Starts
	        		function validateDate(txtDate){
	        		   var txtVal = document.getElementById(txtDate).value;
	        		   var filter = new RegExp("([1-2][0-9][0-9][0-9])([-])(0[123456789]|10|11|12)");
	        		   if(filter.test(txtVal))
	        		      return true;
	        		   else
	        		     return false;
	        		}​
	        		//Functions Ends
	        </script>
<script>
            $("#txtDate").live('blur',function kayitVarMiAjax() {
		        // get the form values
		           //var id=aydi;
		           var tarih=$("#txtDate").val();
		          // var tirnak="'";
		        
		        $.ajax({
		        type: "POST",
		        url: "/sera/cenvgiris/kayitkontrol.htm",
		        data: "tarih="+tarih+"&id="+globalid,
		        cache: false,
		        success: function(response){
		        // we have the response
		        
		           $("#deger").val(response.deger);
		        //eğer gelen değer var ise formu güncelle olarak değiştir
		         //başla
		        if($("#deger").val().length>0){
		        	$("#formgiris").attr("action","/sera/cenvgiris/girisguncelle.htm");
		        	$("#idsubmit").attr("value","Güncelle");
		        	$("#girisid").attr("value",response.id);
		        	//form a id ekle
		        	
		        }
		        else {
		        	$("#formgiris").attr("action","/sera/cenvgiris/giriskaydet.htm");
		        	$("#idsubmit").attr("value","Kaydet");
		        	$("#girisid").attr("value",response.id);
		        }
			    //bitir     
		        },
		        error: function(e){
		        $.Zebra_Dialog('Error: ' + e);
	            } 
		        });
		        }
	        );
	        
	       
	        
	        function anaDonemDegis(){
	        	
	        	$("#txtDate").val($("#ustDonem").val());
	        	//mecbur değişir değişmez o elemana gitmeli yoksa kontrol edemiyoruz
	        	$("#txtDate").focus(); 
	        	
	        }
	         
	        </script> 
</head>
<body class="genel">
   
	<jsp:include page="/WEB-INF/jsp/ana_sayfa/header.jsp" />
	<c:choose>
	<c:when test="${isAuthenticated=='true'}">
	<div class="orta_div_sag">
		<table width="100%">
			<tr>
				<td>
					<table width="230px" align="left" class="kok_table_giris">
						<tbody>
						    <tr align="center" style="font-weight: bold;">
						        <td>Dönem (Örn:2012-01)<input type="text" id="ustDonem"  
						        maxlength="7" size="20" onchange="anaDonemDegis()"></input></td>
						    </tr>
							<tr align="center" style="font-weight: bold;">
								<td><input type="submit" class="detay_dugme"
									value="${kok.baslik}"
									onclick="doAjaxPost(${kok.id},${kok.seviye},'${kok.tip1}','${kok.tip2}')" />
								</td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</table>
		<div align="center" id="kok"></div>
		<div class="formspacer"></div>
	</div>
	</c:when>
	<c:otherwise>
		<div class="orta_div_sag">
			Bu içeriğe erişmek için giriş yapmalısınız.
		</div>
	</c:otherwise>
	</c:choose> 
	
	</div>
<%@include file="/WEB-INF/jsp/ana_sayfa/footer.jsp" %>
</body>
</html>
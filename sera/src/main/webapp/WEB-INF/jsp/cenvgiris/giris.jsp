<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Değer Giriş Ekranı</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/form/general.css"/>"></link>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/ana_sayfa/form.css"/>"
	type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/ana_sayfa/menu.css"/>"
	type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/yapi/agac.css"/>" type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>"
	type="text/css" />
<script type="text/javascript"
	src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
<script>
  $(document).ready(function(){
    $("#formgiris").validate();
  });
  </script>
<script type="text/javascript">
       var globalid;
	        function doAjaxPost(aydi,seviye,tip,tip2) {
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
	        	$("#kok").append('<div style="margin-left:185px" class="yaprakdiv" id="yaprakid"></div>');
	        	$("#yaprakid").append('<br/><form:form cssClass="formstil" name="formgiris" id="formgiris" action="/sera/cenvgiris/giriskaydet.htm" method="POST"  modelAttribute="cenvgiris" enctype="multipart/form-data">'+
	        	'<form:hidden path="baslikId" size="40" value="'+
	        	aydi+
	        	'"/><br/>'+
	        	'<form:hidden id="girisid" path="id" size="40" value="3" />'+
	        	'<table>'+
	        	'<tr><td class="formyazi" align="right">Dönem:</td>'+
	        	'<td><form:input id="txtDate" path="tarih" size="20" class="required" minlength="7" maxlength="7"/>(Örn:03-2012)</td>'+
	        	'</tr>'+
	        	'<tr>'+
    	        '<td class="formyazi" align="right">Değer:</td>'+
	        	'<td><form:input id="deger" class="required"  path="deger" size="20"/></td>'+
	        	'</tr>'+
	        	'<tr><td></td>'+
    			'<td class="submit"><input type="submit" id="idsubmit" value="Kaydet"></input></td>'+
    			'</tr>'+
    			'</table>'+
    			'</form:form>');
	        }
		        
		     
	               
	        },
	        error: function(e){
	        alert('Error: ' + e);
            } 
	        });
	        }
	        </script>
<script type="text/javascript">
	         	//bir üst jquery versiyonunda live yerine on kullan
	        		  $("#txtDate").live('blur',
		        		   function() {
		        		      if(!validateDate("txtDate"))
		        		      {
		        		          alert('Geçersiz Tarih!!!');
		        		      }
		        		   }
	        		  );	
	        		 
	        		//Functions Starts
	        		function validateDate(txtDate){
	        		   var txtVal = document.getElementById(txtDate).value;
	        		   var filter = new RegExp("(0[123456789]|10|11|12)([-])([1-2][0-9][0-9][0-9])");
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
			    //bitir     
		        },
		        error: function(e){
		        alert('Error: ' + e);
	            } 
		        });
		        }
	        );
	        </script>
</head>
<body class="genel">

	<jsp:include page="/WEB-INF/jsp/ana_sayfa/header.jsp" />
	<div class="orta_div_sag">
		<table width="100%">
			<tr>
				<td>
					<table width="230px" align="left" class="kok_table_giris">
						<tbody>
							<tr align="center">
								<td><input type="submit" class="detay_dugme"
									value="${kok.baslik}"
									onclick="doAjaxPost(${kok.id},${kok.seviye},'${kok.tip1}','${kok.tip2}')" />
								</td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</table>
		<div id="kok"></div>
		<div class="formspacer"></div>
	</div>
	</div>

</body>
</html>
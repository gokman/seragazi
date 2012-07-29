<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-9" />
<title>Dönem Değerlerini Hesapla</title>
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
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
	        		   if(filter.test(txtVal)){
	        			  
	        		      return true;
	        		   }else{
	        			  
	        		     return false;
	        		   }
	        		}​
	        		//Functions Ends
	        		function donemHesapla(){
	        			var donem=$("#txtDate").val();
	        			var globalKontrol=false;
	        			var kontrol=0;
	        			//girişler tam mı onu kontrol et
	        			//başla
	        			 $.ajax({
	        type: "POST",
	        url: "/sera/donemsonuc/girisKayitKontrol.htm",
	        data: "donem=" + donem ,
	        cache: false,
	        success: function(response){
	        // we have the response
	        
	        globalKontrol=response;  
	        
	        
	        if(globalKontrol==false){
    			alert('Bu döneme ait değer girişleri eksik');	
    			kontrol=0;
    		}
	        
	        
	        
	        if(validateDate("txtDate")&&globalKontrol==true){
	        	
	        	 kontrol=1;
				  // document.donemSonucForm.submit();
			}else{
				null;
			}
	               
	        },
	        error: function(e){
	        alert('Error: ' + e);
            } 
	        });
	        //bitir
	        
	        //donem sonuç kaydı var mı onu kontrol et
	        //başla
	        //girişler tam mı onu kontrol et
	        			//başla
	        			 $.ajax({
	        type: "POST",
	        url: "/sera/donemsonuc/donemSonucKayitKontrol.htm",
	        data: "donem=" + donem ,
	        cache: false,
	        success: function(response){
	        // we have the response
	        
	        globalKontrol=response;  
	        if(globalKontrol==true){
	        	
	           alert('Bu döneme ait sonuç kayıtları bulunmaktadır. Devam edemezsiniz.');
	        }
	        
	        //eğer kontrolden true geldi ise kayıt var demektir o halde işleme devam etme
	        //kontrolden false gelecek ve yukarıdaki kontrol değeri de 1 gelecek
	        if(globalKontrol==false && kontrol==1){
	        	document.donemSonucForm.submit();
	        }
	               
	        },
	        error: function(e){
	        alert('Error: ' + e);
            } 
	        });
	        //bitir
	        //bitir
	        			
	        			
	        		}
	        </script> 
	        <script>
  $(document).ready(function(){
    $("#donemSonucForm").validate();
  });
  </script>
  
  <script type="text/javascript"></script>
<style type="text/css">
* { font-family: Verdana; font-size: 12px; }
label.error { float: none; color: red; padding-left: .5em; vertical-align: top; }
.submit { margin-left: 12em; }
</style>
  <script>
  $(document).ready(function(){
    $("#commentForm").validate();
  });
  </script>
  
	        
</head>
<body class="genel">
<%@include file="/WEB-INF/jsp/ana_sayfa/header.jsp" %>
<!-- ana bolum -->
    <c:choose>
	<c:when test="${isAuthenticated=='true'}">
	<div class="orta_div_sag">
	<form:form cssStyle="padding-left:50px;padding-top:50px" id="donemSonucForm" name="donemSonucForm" cssClass="formstil" action="/sera/donemsonuc/donemsonuchesapla.htm" method="POST"  modelAttribute="donemsonuc" enctype="multipart/form-data">
	<table>
	<tr>
	<td><a>Dönem(mm-yyyy) : </a></td>
	<td><form:input id="txtDate" class="required" path="donem" minlength="7" maxlength="7" size="20px"/>
	<form:errors path="donem"></form:errors>
	</td>
	</tr>
	<tr>
	<td></td>
	<td class="submit"><input type="submit" onclick="donemHesapla(); return false;" value="Hesapla" ></input></td>
	</tr>
	</table>
	</form:form>
	
	</div>
	</c:when>
	<c:otherwise>
		<div class="orta_div_sag">
			Bu icerige erismek icin giris yapmalisiniz.
		</div>
	</c:otherwise>
	</c:choose>	
	</div>	
<%@include file="/WEB-INF/jsp/ana_sayfa/footer.jsp" %>
</body>
</html>

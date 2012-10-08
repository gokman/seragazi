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

<script type="text/javascript" src="<c:url value="/resources/js/zebra/zebra_dialog.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/style.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/zebra_dialog.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/ir_black.css"/>" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/zebra/highlight.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zebra/functions.js"/>"></script>
<script>

function donemSonucKaydetBasla(){
	
	 $.Zebra_Dialog('Devam etmek istiyor musunuz?', {
	    'type':     'question', 
	    'title':    '',
	    'buttons':  [
						{caption: 'Vazgeç', callback: function() { }},
	                    {caption: 'Kaydet', callback: function() { donemHesapla()}},
	                ]
	});	
	
	
}

function donemSonucSilBasla(){
	
	 $.Zebra_Dialog('Devam etmek istiyor musunuz?', {
	    'type':     'question', 
	    'title':    '',
	    'buttons':  [
						{caption: 'Vazgeç', callback: function() { }},
	                    {caption: 'Evet', callback: function() { donemSonucSil()}},
	                ]
	});	
	
	
}

function donemSonucSil(){
	
	//öncelikle tarihi kontrol et
	 if(!validateDate("txtDate"))
		        		      {
		        		          alert('Geçersiz Tarih!!!');
		        		          return;
		        		          
		        		      }
	
	//başla
	//donem sonucu var mı yok mu onu kontrol et daha sonra silme işlemini yap
	 $.ajax({
type: "POST",
url: "/sera/donemsonuc/donemSonucKayitKontrol.htm",
data: "donem=" + $("#txtDate").val(),
cache: false,
async: false,
success: function(response){
// we have the response

globalKontrol=response;  
if(globalKontrol==false){

alert('Bu döneme ait sonuç kayıtları bulunmamaktadır.');

}
//kayıtlar var ise sil
else{
	ajaxDonemSonucSil($("#txtDate").val());
}

},
error: function(e){
alert('Error dönemsonuç: ' + e);
} 
});
//bitir
	 
	
}

function ajaxDonemSonucSil(donem){
	//başla
	 $.ajax({
type: "POST",
url: "/sera/donemsonuc/donemsonucsil.htm",
data: "donem=" + donem,
cache: false,
async: false,
success: function(response){
//we have the response
  
alert('Silindi'+response);

},
error: function(e){
alert('Error dönemsonuç: ' + e);
} 
});
//bitir
}

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
	        		   var filter = new RegExp("([1-2][0-9][0-9][0-9])([-])(0[123456789]|10|11|12)");
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
	        			var kontrol=0,kontrol2=0;
	        			//girişler tam mı onu kontrol et
	        			//başla
	        			 $.ajax({
	        type: "POST",
	        url: "/sera/donemsonuc/girisKayitKontrol.htm",
	        data: "donem=" + donem ,
	        cache: false,
	        async: false,
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
	        alert('Error giriş: ' + e);
            } 
	        });
	        //bitir
	        
	        //hesaplama kayıtları tam mı
	        //başla
	        
	         $.ajax({
	        type: "POST",
	        url: "/sera/donemsonuc/hesaplamaKayitKontrol.htm",
	        cache: false,
	        async: false,
	        success: function(response){
	        // we have the response
	        
	        globalKontrol=response;  
	        
	        
	        if(globalKontrol==false){
    			alert('Hesaplama Tablo Kayıtları Eksik');	
    			kontrol2=0;
    		}else{
    			kontrol2=1;
    		}
	        
	              
	        },
	        error: function(e){
	        alert('Error hesaplama: ' + e);
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
	        async: false,
	        success: function(response){
	        // we have the response
	        
	        globalKontrol=response;  
	        if(globalKontrol==true){
	        	
	           alert('Bu döneme ait sonuç kayıtları bulunmaktadır. Devam edemezsiniz.');
	           
	        }
	        
	        //eğer kontrolden true geldi ise kayıt var demektir o halde işleme devam etme
	        //kontrolden false gelecek ve yukarıdaki kontrol değeri de 1 gelecek
	        if(kontrol==1 && kontrol2==1 && globalKontrol==false){
	        	document.donemSonucForm.submit();
	        }
	               
	        },
	        error: function(e){
	        alert('Error dönemsonuç: ' + e);
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
	<form:form cssStyle="padding-left:50px;padding-top:40px;" id="donemSonucForm" name="donemSonucForm" cssClass="formstil" action="/sera/donemsonuc/donemsonuchesapla.htm" method="POST"  modelAttribute="donemsonuc" enctype="multipart/form-data">
	<table>
	<tr>
	<td><a>Dönem (Örn:2012-01): </a></td>
	<td><form:input id="txtDate" class="required" path="donem" minlength="7" maxlength="7" size="20px"/>
	<form:errors path="donem"></form:errors>
	</td>
	</tr>
	<tr>
	<td></td>
	<td class="submit">
	<input type="submit" onclick="donemSonucKaydetBasla(); return false;" value="Hesapla" ></input>
	<input type="button" onclick="donemSonucSilBasla();" value="Sil" ></input>
	</td>
	</tr>
	</table>
	</form:form>
	
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

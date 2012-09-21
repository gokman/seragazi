<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Değer Giriş Raporu</title>
<link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<!--<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/main.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" /> -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
  <script>
	  $(document).ready(
		function(){ 
			
			$.validator.addMethod("donem",function(value,element){
			
			var filter = new RegExp("(0[123456789]|10|11|12)([-])([1-2][0-9][0-9][0-9])");
				
				return filter.test(value);		
				},"Geçerli bir dönem giriniz."
				);
	          
		        $.validator.addClassRules({
		          donem:{donem:true}
			    });
	        
	        $("#formrapor").validate();
	       
	  });  
  </script>
</head>
<body>
   
	 <c:choose> 
	<c:when test="${isAuthenticated=='true'}">
	<div>
	<fieldset style="width:550px;height:auto;margin-left: auto;margin-right: auto;margin-bottom:20px;border-color: #2581C5;">
	<legend style="font-weight: bold;">Sorgula</legend>
		<form:form cssClass="formrapor" name="formrapor"  id="formrapor"  
		           action="/sera/cenvgiris/degergirisraporsorgu.htm" method="POST" modelAttribute="raporParam" commandName="raporParam"
		           enctype="multipart/form-data">
		        	<table>
			         <tr>
			          <td class="formyazi" align="right">Dönem Başlangıç >= :</td>
			          <td><form:input style="width:150px;"  id="textfield"  path="baslangic" 
			                           size="10" maxlength="7"  class="required donem"/></td>
			         </tr>
			         <tr> 
		    	      <td class="formyazi" align="right">Dönem Bitiş <= :</td>
			          <td><form:input style="width:150px;" id="textfield2" class="required donem" path="bitis" maxlength="7"  size="10"/></td>
			         </tr> 
			         <tr>
			          <td></td>
		    		  <td class="submit"><input type="submit" class="submit" id="idsubmit" value="Çalıştır"></input></td>
		    		 </tr>
	    			</table> 
	    </form:form>
    </fieldset>
<center>	
<%@include file="/WEB-INF/jsp/report/cenvgirisreport.htm" %>
<a href="<c:url value="/cenvgiris/pdf/cenvgirisreport.pdf"/>">PDF İndir</a>
</center>
	</div>
	</c:when>
	<c:otherwise>
		<div class="orta_div_sag">
			Bu icerige erismek icin giris yapmalisiniz.
		</div>
	</c:otherwise>
	</c:choose>
</body>
</html>
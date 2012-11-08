<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Dönem Sonucu Raporu</title>
<link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<!--<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/main.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" /> -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
<script type="text/javascript">
$(document).ready(
		function(){ 
			
			$.validator.addMethod("donem",function(value,element){
			
			var filter = new RegExp("([1-2][0-9][0-9][0-9])([-])(0[123456789]|10|11|12)");
				
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
		           action="/sera/donemsonuc/donemsonucraporsorgu.htm" method="POST" modelAttribute="raporParam" commandName="raporParam"
		           enctype="multipart/form-data">
		        	<table>
			         <tr>
			          <td class="formyazi" align="right">Dönem Başlangıç >= :</td>
			          <td class="inputyazi"><form:input style="width:150px;" class="donem"  id="textfield"  path="donembasla" 
			                           size="10" maxlength="7" /></td>
			         </tr>
			         <tr> 
		    	      <td class="formyazi" align="right">Dönem Bitiş <= :</td>
			          <td class="inputyazi"><form:input style="width:150px;" class="donem" id="textfield2"  path="donembitis" maxlength="7"  size="10"/></td>
			         </tr> 
			         <tr> 
		    	      <td class="formyazi" align="right">Başlık :</td>
			          <td class="inputyazi"><form:input style="width:150px;" id="textfield3"  path="baslik" maxlength="15"  size="10"/></td>
			         </tr> 
			         <tr>
			          <td></td>
		    		  <td class="submit"><input type="submit" class="submit" id="idsubmit" value="Çalıştır"></input></td>
		    		 </tr>
	    			</table> 
	    </form:form>
    </fieldset>
<center>	
<%@include file="/WEB-INF/jsp/report/cenvdonemsonucreport.htm" %>
</center>
	</div>
	</c:when>
	<c:otherwise>
		<div class="orta_div_sag">
			Bu içeriğe erişmek için giriş yapmalısınız.
		</div>
	</c:otherwise>
	</c:choose>	
	
</body>
</html>
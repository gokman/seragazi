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

</head>
<body>
   
	 <c:choose> 
	<c:when test="${isAuthenticated=='true'}">
	<div>
	<fieldset style="width:550px;height:auto;margin-left: auto;margin-right: auto;margin-bottom:20px;border-color: #2581C5;">
	<legend style="font-weight: bold;">Sorgula</legend>
		<form:form cssClass="formrapor" name="formrapor"  id="formrapor"  
		           action="/sera/cenvyapi/degerlisteraporsorgu.htm" method="POST" modelAttribute="raporParam" commandName="raporParam"
		           enctype="multipart/form-data">
		        	<table>
			         <tr>
			          <td></td>
		    		  <td class="submit"><input type="submit" class="submit" id="idsubmit" value="Çalıştır"></input></td>
		    		 </tr>
	    			</table> 
	    </form:form>
    </fieldset>
<center>	
<%@include file="/WEB-INF/jsp/report/cenvdegerlistereport.htm" %>
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
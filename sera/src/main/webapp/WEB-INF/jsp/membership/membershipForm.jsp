<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>

<script>
$(document).ready(function(){
    $("#cmaForm").validate({
    	rules:{
    	pass2:{
    	equalTo:"#pass"	
    	}
    	}
    	
    });
});
	
</script>	

<link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
	</head>

<body class="genel">
	
					<jsp:include page="/WEB-INF/jsp/ana_sayfa/header.jsp" />
<div class="orta_div_sag">	
<c:url value="/login/membershipFormSave.htm" var="saveMember"></c:url>
<form:form  name="cmaForm" id="cmaForm" action="${saveMember}" method="POST" modelAttribute="user"  enctype="multipart/form-data" >

				<fieldset style="width:500px;height:auto;margin-left: auto;margin-right: auto;margin-bottom:20px;border-color: #2581C5;">
	<legend style="font-weight: bold;">Üye Kayıt Formu</legend>	
				<table>
				
				<tr>
				<td class="formyazi" align="right">İsim:</td>
				<td>
				<form:input   path="name" name="name" class="required"  maxlength="50" />
				</td>
				</tr>
				
				<tr>
				<td class="formyazi" align="right">Soy İsim:</td>
				<td>
				<form:input path="surname" name="surname" class="required"  maxlength="50" />
				</td>
				</tr>
				
				<tr>
				<td class="formyazi" align="right">Kullanıcı Adı:</td>
				<td>
				<form:input  path="username" name="username" class="required"  maxlength="50" />
				</td>
				</tr>
				
				<tr>
				<td class="formyazi" align="right">Şifre:</td>
				<td>
				<form:input type="password"  path="password" name="pass" id="pass"  class="required"  maxlength="50" />
				</td>
				</tr>
				
				<tr>
				<td class="formyazi" align="right">Şifre Tekrar:</td>
				<td>
				<input type="password"  name="pass2"  class="required" id="pass2"  maxlength="50" />
				</td>
				</tr>
				
				<tr>
				<td class="formyazi" align="right">Email:</td>
				<td>
				<form:input  path="email" name="email" class="required email"  maxlength="50" />
				</td>
				</tr>
				
				<tr><td></td>
				<td class="submit">
				<input name="kaydet" type="submit" id="kaydet" value="Kaydet" class="submit" alt="Submit" title="Submit"/>
				</td>
				</tr>
				</table>
				</fieldset> 
</form:form>
</div>
					<jsp:include page="/WEB-INF/jsp/ana_sayfa/footer.jsp" />
		
</body>
</html>
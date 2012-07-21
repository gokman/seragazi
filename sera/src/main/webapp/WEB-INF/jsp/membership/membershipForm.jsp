<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-9" />
	
			<!-- Acordion form js and css -->
			<script type="text/javascript">
			<!--
			function isNumericKey(e) {
			   var k = document.all ? e.keyCode : e.which;
			   return ((k > 47 && k < 58) || k == 8);
			}
			function extractNumeric(str) {
			   return str.replace(/\D/g,"");
			}
			// -->
			</script>
	
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery-1.2.1.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery-1.2.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.form.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.metadata.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.maskedinput-1.0.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.dimensions.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.accordion.js"/>"></script>
			
<link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/form.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
	</head>

<body class="genel">
	
					<jsp:include page="/WEB-INF/jsp/ana_sayfa/header.jsp" />
<div class="orta_div_sag">	
<c:url value="/login/membershipFormSave.htm" var="saveMember"></c:url>
<form:form  name="cmaForm" id="cmaForm" action="${saveMember}" method="POST" modelAttribute="user"  enctype="multipart/form-data" >

				<fieldset>
				<legend> Üyelik Bilgileri</legend> 
				<div class="requiredNotice">* Zorunlu Alan</div>
				<h3 class="stepHeader">Üye Kayıt Formu</h3>
				<table>
				<tr>
				<td>
				<label for="name" class="input required">İsim:</label>
				</td>
				<td>
				<form:input   path="name" name="name" class="formtext"  maxlength="50" />
				<form:errors  path="name"></form:errors>
				</td>
				</tr>
				
				<tr>
				<td>
				<label for="surname" class="input required">Soyisim:</label>
				</td>
				<td>
				<form:input path="surname" name="surname" class="formtext"  maxlength="50" />
				<form:errors  path="surname"></form:errors>
				</td>
				</tr>
				
				<tr>
				<td>
				<label for="username" class="input required">Kullanıcı adı:</label>
				</td>
				<td>
				<form:input  path="username" name="username" class="formtext"  maxlength="50" />
				<form:errors  path="username"></form:errors>
				</td>
				</tr>
				<tr>
				<td>
				<label for="password" class="input required">Şifre:</label>
				</td>
				<td>
				<form:input  path="password" name="password"  class="formtext"  maxlength="50" />
				<form:errors  path="password"></form:errors>
				</td>
				</tr>
				<tr>
				<td>
				<label for="email" class="input required">Email:</label>
				</td>
				<td>
				<form:input  path="email" name="email" class="formtext"  maxlength="50" />
				<form:errors  path="email"></form:errors>
				</td>
				</tr>
				
				<tr><td></td>
				<td>
				<div id="divdugme" class="buttonWrapper">
				<input name="kaydet" type="submit" id="kaydet" value="Kaydet" class="submitbutton" alt="Submit" title="Submit"/>
				</div>
				</td>
				</tr>
				</table>
				</fieldset> 
</form:form>
</div>
					<jsp:include page="/WEB-INF/jsp/ana_sayfa/footer.jsp" />
		
</body>
</html>
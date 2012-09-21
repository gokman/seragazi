<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>

	<script>
		$(document).ready(function(){
		    $("#cmaForm").validate();
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
	
<!-- start -->

	<c:url value="/login/sendForgottenPassword.htm" var="sendPassword"></c:url>
	<form  name="cmaForm" id="cmaForm" action="${sendPassword}"  enctype="multipart/form-data" >



				<fieldset style="width:500px;height:auto;margin-left: auto;margin-right: auto;margin-bottom:20px;border-color: #2581C5;">
				<legend>Şifremi Unuttum</legend> 
                <table>
                <tr>
                <td colspan="2">
				<h3>Şifrenizin mail adresinize gönderilmesi için aşağıdaki alana mail adresinizi giriniz.</h3>
                </td>
                </tr>
                <tr>
                <td class="formyazi" align="right">Email</td>
				<td>
				<input  name="email"  class="required email" title="Email" maxlength="50"  onblur="recordClientCity.value = this.value" />
                </td>
                </tr>
                <tr>
                <td></td>
                <td class="submit">
				<input name="submit" type="submit" id="submit" value="Submit" class="submitbutton" alt="Submit" title="Submit"></input>
				</td>
				</tr>
				</table>
		</fieldset> 
	</form>

<!--  end -->
</div>
					<jsp:include page="/WEB-INF/jsp/ana_sayfa/footer.jsp" />


</body>
</html>
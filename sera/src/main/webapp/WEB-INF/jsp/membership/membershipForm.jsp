<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-9" />
<title>Üye Kayıt Sayfası</title>		
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
 

<script type="text/javascript">

/*$("#username").live('blur', function(){
	var xx=$("#username").val();
	usernameUniqueFunc(xx);	
});

$("#email").live('blur', function(){
	var xx=$("#email").val();
	emailUniqueFunc(xx);	
});*/



function emailUniqueFunc(email){
	var sonuc=false;
	//başla
	$.ajax({
		type: "GET",
		url: "/sera/login/emailUnique.htm",
		data: "email="+email,
		cache: false, 
		async: false,
		success: function(response){
		//we have the response 
		if(response==true){
			sonuc=false;
		}else{
			sonuc=true;
		}
		
		},
		error: function(e){
		$.Zebra_Dialog('Error giriş: ' + e);
		} 
	});
	//bitir
	return sonuc;
	}
	
</script>	
<script  type="text/javascript">
$(document).ready(function(){
	
	function usernameUniqueFunc(username){
		var sonuc=false;
		//başla
		$.ajax({
			type: "GET",
			url: "/sera/login/usernameUnique.htm",
			data: "username="+username,
			cache: false, 
			async: false,
			success: function(response){
			//we have the response 
			if(response==true){
			   //kayıt vardır o halde false döndür
			   sonuc=false;
			}else{
			   sonuc=true;
			}
			
			},
			error: function(e){
			$.Zebra_Dialog('Error giriş: ' + e);
			} 
		});
		//bitir	
		return sonuc;
		}
	 
	$.validator.addMethod("usernamerepeat",function(value,element){
	     
		
		
		return usernameUniqueFunc(value);		
		},"Kullanıcı adı mevcut."
		);
      
        $.validator.addClassRules({
        	usernamerepeat:{usernamerepeat:true}
	    });	
        
    $.validator.addMethod("emailrepeat",function(value,element){
   	     
    		
    		
    		return emailUniqueFunc(value);		
    		},"Email adresi mevcut."
    		);
          
            $.validator.addClassRules({
            	emailrepeat:{emailrepeat:true}
    	    });	   
        
	
    $("#cmaForm").validate({
    	rules:{
    	pass2:{
    	equalTo:"#pass"	
    	}
    	}
    	
    });
});
</script>

	</head>

<body class="genel">
	
					<%@include file="/WEB-INF/jsp/ana_sayfa/header.jsp" %>
<div class="orta_div_sag">	
<c:url value="/login/membershipFormSave.htm" var="saveMember"></c:url>
<form:form  name="cmaForm" id="cmaForm" action="${saveMember}" method="POST" modelAttribute="user"  enctype="multipart/form-data" >

				<fieldset style="width:500px;height:auto;margin-left: auto;margin-right: auto;margin-bottom:20px;border-color: #2581C5;">
	<legend style="font-weight: bold;">Üye Kayıt Formu</legend>	
				<table>
				
				<tr>
				<td class="formyazi" align="right">İsim:</td>
				<td class="inputyazi">
				<form:input   path="name" name="name" class="required"  maxlength="50" />
				</td>
				</tr>
				
				<tr>
				<td class="formyazi" align="right">Soy İsim:</td>
				<td class="inputyazi">
				<form:input path="surname" name="surname" class="required"  maxlength="50" />
				</td>
				</tr>
				
				<tr>
				<td class="formyazi" align="right">Kullanıcı Adı:</td>
				<td class="inputyazi">
				<form:input id="username"  path="username" name="username" class="required usernamerepeat"  maxlength="50" />
				</td>
				</tr>
				
				<tr>
				<td class="formyazi" align="right">Şifre:</td>
				<td class="inputyazi">
				<form:input type="password"  path="password" name="pass" id="pass"  class="required"  maxlength="50" />
				</td>
				</tr>
				
				<tr>
				<td class="formyazi" align="right">Şifre Tekrar:</td>
				<td class="inputyazi">
				<input type="password"  name="pass2"  class="required" id="pass2"  maxlength="50" />
				</td>
				</tr>
				
				<tr>
				<td class="formyazi" align="right">Email:</td>
				<td class="inputyazi">
				<form:input  path="email" name="email" class="required email emailrepeat"  maxlength="50" />
				</td>
				</tr>
				
				<tr><td></td>
				<td class="submit">
				<input name="kaydet"  type="submit" id="kaydet" value="Kaydet" class="submit" alt="Submit" title="Submit"/>
				</td>
				</tr>
				</table>
				</fieldset> 
</form:form>
</div>
					<%@include file="/WEB-INF/jsp/ana_sayfa/footer.jsp"  %>
		
</body>
</html>
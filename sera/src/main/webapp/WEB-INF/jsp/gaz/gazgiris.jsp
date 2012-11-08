<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gazlar</title>
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
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
  $(document).ready(function(){
    $("#formbirim").validate();
    var kontrol=${gazim.id};
    if (kontrol!=0){ 
    	$("#formbirim").attr("action","/sera/gaz/gazguncelle.htm");
    	$("#idsubmit").attr("value","Güncelle");
    }
  });
</script>
 <script>

</script>
  <script type="text/javascript">
 /* function birimkaydet(){ 
		$("#formbirim").validate({
		 	submitHandler : function(form){
		 		form.submit();
			} 
		});
	}*/
  
  function gazSil(id){
	  
	  $.Zebra_Dialog('Bu değeri silmek üzeresiniz. Emin misiniz?', {
		    'type':     'question',
		    'title':    '',
		    'buttons':  [
							{caption: 'Vazgeç', callback: function() { }},
		                    {caption: 'Sil', callback: function() { ajaxSil(id)}},
		                ]
		});	
	  
	 
  }
  
  function ajaxSil(id){
	  $.ajax({
	        type: "POST",
	        url: "/sera/gaz/gazSil.htm",
	        data: "id=" + id ,
	        cache: false,
	        success: function(response){
	        // we have the response
	        
	        	location.reload();
		      
	               
	        },
	        error: function(e){
	        $.Zebra_Dialog('Error: ' + e);
        } 
	        });
	  
  }
  </script>
  <script>
  function gazGuncelle(id){
	  
  window.location="/sera/gaz/gazguncelle/"+id+".htm";
	  
  }
  </script>
</head>
<body class="genel">
<jsp:include page="/WEB-INF/jsp/ana_sayfa/header.jsp" />
<c:choose>
	<c:when test="${isAuthenticated=='true'}">
	<div class="orta_div_sag">
<fieldset style="width:500px;height:auto;margin-left: auto;margin-right: auto;margin-bottom:20px;border-color: #2581C5;">
	<legend style="font-weight: bold;">Gaz Kayıt</legend>	
<form:form cssClass="formstil" name="formbirim"  id="formbirim"  action="/sera/gaz/gazkaydet.htm" method="POST"  modelAttribute="gaz" enctype="multipart/form-data">
	         <form:hidden path="id" value="${gazim.id}"/>  
	        	<table>
	        	<tr><td class="formyazi" align="right">Değer:</td>
	        	<td class="inputyazi"><form:input  id="name" value="${gazim.name}" path="name" size="30" class="required"/></td>
	        	</tr>
	        	<tr><td></td>
    			<td class="submit"><input type="submit" class="submit" id="idsubmit" value="Kaydet"></input></td>
    			</tr>
    			</table> 
</form:form>
</fieldset>
  
<table style="padding-left:30px;padding-top:100px;margin-left: auto;margin-right: auto;"> 
<tr>
<td width="80px" class="formyazi">Değer</td>
<td></td>
<td></td> 
</tr>   			
<c:forEach var="gaz" items="${gazlar}">
<tr>
<td>${gaz.name}</td>
<td class="submit"><input type="button" onclick="gazSil(${gaz.id})" value="Sil"/></td>
<td class="submit"><input type="button" onclick="gazGuncelle(${gaz.id})" value="Güncelle"/></td>
</tr>
</c:forEach>
</table>
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
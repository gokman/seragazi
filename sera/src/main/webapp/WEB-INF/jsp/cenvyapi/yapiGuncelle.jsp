<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css"/>

<!-- 
<div id="sayfa_baslik" style="padding-left:50px;padding-top:30px"><a>Yapıyı oluşturunuz</a></div>
 -->
 
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/form.css"/>" type="text/css" />

<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$("#checked").live(
	'val',
	function(){
		if($("#checked").val()=="Kök"){
			//show the hidden div
			$("#parentdiv").hide("fast");
			$("#hesapdiv1").show("fast");
			$("#hesapdiv2").hide("fast");
		}
		else if($("#checked").val()=="Dal"){
			//show the hidden div
			$("#parentdiv").show("fast");
			$("#hesapdiv1").show("fast");
			$("#hesapdiv2").hide("fast");
		}
		else
		{
			//otherwise, hide it
			$("#parentdiv").show("fast");
			$("#hesapdiv2").show("fast");
			$("#hesapdiv1").hide("fast");
		}
		
	}
	);
	
	
   
	if($("#checked").val()=="Kök"){
		//show the hidden div
		$("#parentdiv").hide("fast");
		$("#hesapdiv1").show("fast");
		$("#hesapdiv2").hide("fast");
	}
	else if($("#checked").val()=="Dal"){
		//show the hidden div
		$("#parentdiv").show("fast");
		$("#hesapdiv1").show("fast");
		$("#hesapdiv2").hide("fast");
	}
	else
	{
		//otherwise, hide it
		$("#parentdiv").show("fast");
		$("#hesapdiv2").show("fast");
		$("#hesapdiv1").hide("fast");
	}
	
	// Add onclick handler to checkbox w/id checkme
   $("#checked").change(
	function ekleme(){
		if($("#checked").val()=="Yaprak"){
				//otherwise, hide it
				$("#parentdiv").show("fast");
				//ağaç tipine göre hesaplama listesini güncelle
				$("#hesapdiv2").show("fast");
				$("#hesapdiv1").hide("fast");
			}
			else if($("#checked").val()=="Dal"){
				//otherwise, hide it
				$("#parentdiv").show("fast");
				//ağaç tipine göre hesaplama listesini güncelle
				$("#hesapdiv1").show("fast");
				$("#hesapdiv2").hide("fast");
			}
			else
			{
				//show the hidden div
				$("#parentdiv").hide("fast");
				//ağaç tipine göre hesaplama listesini güncelle
				$("#hesapdiv1").show("fast");
				$("#hesapdiv2").hide("fast");
			}	
			
	}	     
   );
	
   if($("#hesapliste2").val()=="Sabit"){
		//show the hidden div
	   $("#sabitdiv").show("fast");
	}
	else
	{
		//otherwise, hide it
		$("#sabitdiv").hide("fast");
	}
	
	$("#hesapliste2").change(
	function sabitekle(){
		if($("#hesapliste2").val()=="Sabit"){
			$("#sabitdiv").show("fast");	
		}
		else{
			$("#sabitdiv").hide("fast");
		}
		
	}		
	
	);
		
});


</script>
</head>
<body class="genel">
<%@include file="/WEB-INF/jsp/ana_sayfa/header.jsp" %>
<div class="orta_div_sag">
<form:form cssStyle="padding-left:50px;padding-top:50px" cssClass="formstil" action="/sera/cenvyapi/yapiGuncelle.htm" method="POST"  modelAttribute="cenvdeger" enctype="multipart/form-data">
<table>
<form:hidden path="id" value="${cenvDoluVeriler.id}"/>
<tr><td>
<a>Sınıf </a>
</td>
<td><form:select path="tip1" id="checked" name="checked">
<form:option value="${cenvDoluVeriler.tip1}" label="${cenvDoluVeriler.tip1}"/>
<form:option  value="Yaprak" label="Yaprak"/>
<form:option value="Dal" label="Dal"/>
<c:choose>
<c:when test="${kokKontrol==false}">
<form:option value="Kök" label="Kök"/>
</c:when>
</c:choose>
</form:select>
<form:errors path="tip1"></form:errors>
</td>
</tr>
<tr><td>
<a>Başlık </a>
</td><td>
<form:input id="textfield" path="baslik" maxlength="40" size="40" value="${cenvDoluVeriler.baslik}" />
<form:errors path="baslik"></form:errors>
</td>
</tr>
<tr><td>
<a>Birim </a>
</td><td><form:input id="textfield" path="birim" maxlength="20" size="20" value="${cenvDoluVeriler.birim}" />
<form:errors path="birim"></form:errors>
</td>
</tr>
<tr><td>
<a>Değer Tipi  </a>
</td><td>

    <!-- kok seçili ise -->
    <div id="hesapdiv1">
         <form:select  path="tip2" id="hesapliste1" >
	               <c:choose>
	               <c:when test="${cenvDoluVeriler.tip1=='Dal'}">
							<form:option value="${cenvDoluVeriler.tip2}" label="${cenvDoluVeriler.tip2}"/>
				   </c:when>
				   <c:when test="${cenvDoluVeriler.tip1=='Kök'}">
						<form:option value="${cenvDoluVeriler.tip2}" label="${cenvDoluVeriler.tip2}"/>
			       </c:when>
				<!-- <c:otherwise>
				            <form:option value="" label=""/>
				   </c:otherwise>-->
				   </c:choose>
			   
						<form:option  value="Hesap" label="Hesap"/>
						</form:select>
						<form:errors path="tip2"></form:errors>
    </div>
    <!-- yaprak veya dal seçili ise -->
    <div id="hesapdiv2">
         <form:select  path="tip2" id="hesapliste2" >
         <c:choose>
              <c:when test="${cenvDoluVeriler.tip1=='Yaprak'}">
						<form:option value="${cenvDoluVeriler.tip2}" label="${cenvDoluVeriler.tip2}"/>
			  </c:when>
			<!--   <c:otherwise>
			            <form:option value="" label=""/>
			  </c:otherwise>   -->
	     </c:choose>
						<form:option value="Sabit" label="Sabit"/>
						<form:option value="Elle" label="Elle"/>
		</form:select>

						<form:errors path="tip2"></form:errors>
    </div>

</td></tr>

<tr id="sabitdiv">
<td><a>Sabit Değer </a></td>
<form:hidden path="sabitId" value="${sabitdeger.id}"></form:hidden>
<td><form:input id="textfield" path="sabit" maxlength="20" size="20" value="${sabitdeger.sabit}"  />
<form:errors path="sabit"></form:errors></td>
</tr>

<tr id="parentdiv"><td>
<a>Bağlı Olduğu Bölüm </a>
</td><td>
<form:select path="parentId">
<form:option value="${parentOlayi2.id}" label="${parentOlayi2.baslik}"/>
<form:options items="${parentOlayi}" itemValue="id" itemLabel="baslik"/>
</form:select>
<form:errors path="parentId"></form:errors>
</td></tr>

<tr>
<td></td>
<td>
<input id="button"  type="submit"  value="Güncelle"  />
</td>
</tr>
</table>
</form:form>
</div>
</div>
<%@include file="/WEB-INF/jsp/ana_sayfa/footer.jsp" %>
</body>
</html>
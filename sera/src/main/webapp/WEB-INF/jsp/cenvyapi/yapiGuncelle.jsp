<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
 <link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	
	$("#checked").live(
	'val',
	function(){
		if($("#checked").val()=="Kök"){
			//show the hidden div
			$("#parentdiv").hide("slow");
			//$("#hesapdiv1").show("fast");
	        /* $('#hesapliste1')
         .empty().append($("<option></option>")
         .attr("value","Hesap")
         .text("Hesap"));*/
			//$("#hesapdiv2").hide("fast");
		}
		else if($("#checked").val()=="Dal"){
			//show the hidden div
			$("#parentdiv").show("slow");
			//$("#hesapdiv1").show("fast");
			//$("#hesapdiv2").hide("fast");
		}
		else
		{
			//otherwise, hide it
			$("#parentdiv").show("slow");
			//$("#hesapdiv2").show("fast");
			/*$("#hesapliste1").empty().append('<option  value=""></option>');
			$("#hesapliste1").append('<option  value="Sabit">Sabit</option>');
			$("#hesapliste1").append('<option  value="Elle">Elle</option>');*/
			//$("#hesapdiv1").hide("fast");
		}
		
	}
	);
	
	
   
	if($("#checked").val()=="Kök"){
		//show the hidden div
		$("#parentdiv").hide("slow");
		//$("#hesapdiv1").show("fast");
		$("#hesapliste1").empty().append('<option  value="Hesap">Hesap</option>');
		$("#sabitdiv").hide("slow");
		$("#sabit").attr('class','');
		//$("#hesapdiv2").hide("fast");
	}
	else if($("#checked").val()=="Dal"){
		//show the hidden div
		$("#parentdiv").show("slow");
		//$("#hesapdiv1").show("fast");
		$("#hesapliste1").empty().append('<option  value="Hesap">Hesap</option>');
		$("#sabitdiv").hide("slow");
		$("#sabit").attr('class','');
		//$("#hesapdiv2").hide("fast");
	}
	else
	{
		//otherwise, hide it
		$("#parentdiv").show("slow");
		//$("#hesapdiv2").show("fast");
		
		$("#hesapliste1").empty().append('<option  value="${cenvDoluVeriler.tip2}">${cenvDoluVeriler.tip2}</option>');
		if("${cenvDoluVeriler.tip2}"=="Elle"){
			$("#hesapliste1").append('<option  value="Sabit">Sabit</option>');
		}else{
			$("#hesapliste1").append('<option  value="Elle">Elle</option>');
		}
		//$("#hesapdiv1").hide("fast");
	}
	
	// Add onclick handler to checkbox w/id checkme
   $("#checked").change(
	function ekleme(){
		if($("#checked").val()=="Yaprak"){
				//otherwise, hide it
				$("#parentdiv").show("slow");
				//ağaç tipine göre hesaplama listesini güncelle
				//$("#hesapdiv2").show("fast");
				$("#hesapliste1").empty().append('<option  value=""></option>');
				$("#hesapliste1").append('<option  value="Sabit">Sabit</option>');
			    $("#hesapliste1").append('<option  value="Elle">Elle</option>');
				//$("#hesapdiv1").hide("fast");
			}
			else if($("#checked").val()=="Dal"){
				//otherwise, hide it
				$("#parentdiv").show("slow");
				//ağaç tipine göre hesaplama listesini güncelle
				//$("#hesapdiv1").show("fast");
				//$("#hesapliste1").empty().append('<option  value=""></option>');
				$("#hesapliste1").empty().append('<option  value="Hesap">Hesap</option>');
				//$("#hesapdiv2").hide("fast");
			}
			else
			{
				//show the hidden div
				$("#parentdiv").hide("fast");
				//ağaç tipine göre hesaplama listesini güncelle
				//$("#hesapdiv1").show("fast");
				//$("#hesapliste1").empty().append('<option  value=""></option>');
				$("#hesapliste1").empty().append('<option  value="Hesap">Hesap</option>');
				//$("#hesapdiv2").hide("fast");
			}	
			
	}	     
   );
	
   $("#hesapliste1").focus(
			
	function(){
				
    if($("#hesapliste1").val()=="Sabit"){
		 //show the hidden div
	     $("#sabitdiv").show("slow");
	     $("#sabit").attr('class','required number');
	}
	else
	{
		//otherwise, hide it
		$("#sabitdiv").hide("slow");
		$("#sabit").attr('class','');
	}
			});
	
	$("#hesapliste1").change(
	function sabitekle(){
		if($("#hesapliste1").val()=="Sabit"){
			$("#sabitdiv").show("slow");	
			$("#sabit").attr('class','required number');
		}
		else{
			$("#sabitdiv").hide("slow");
			$("#sabit").attr('class','');
		}
		
	});
	
	
				if($("#hesapliste1").val()=="Sabit"){
					$("#sabitdiv").show("slow");	
					$("#sabit").attr('class','required number');
				}
				else{
					$("#sabitdiv").hide("slow");
					$("#sabit").attr('class','');
				}
			
		
});


</script>

<script>
function yapiKaydet(){
	var parentId;
	var tip1;
	var globalResponse="";
	tip1=$("#checked").val();
	parentId=$("#parentId").val();
	//başla
    //bağlı olduğu bölümün yaprağı var mı onu kontrol et
    //yaprak var ise dal kaydetmeye izin verme.
    //dal var ise de yaprak kaydetmeye izin verme
    			//başla
    			 $.ajax({
    type: "POST",
    url: "/sera/cenvyapi/cocukTipOgren.htm",
    data: "parentId=" + parentId,
    cache: false,
    success: function(response){
    // dönen tip seçilen tipe eşit ise kaydet
    globalResponse=response;
    if(response!=""){
	    if(tip1==globalResponse){
	    	
	    	$("#yapiKayitForm").validate({
	    	 	submitHandler : function(form){
	    	 		
	    			form.submit();
	    		}
	    	});
						
						
	    	
	    }else{
	       $.Zebra_Dialog('Bu bölümün alt sınıfı olan '+response+' tipinde sınıf seçmelisiniz');
	    }
    }else{
    	$("#yapiKayitForm").validate({
    	 	submitHandler : function(form){
    	 		
    			form.submit();
    		}
    	});
    }
        
    }
    });
    //bitir
	
	
}
</script>

</head>
<body class="genel">
<%@include file="/WEB-INF/jsp/ana_sayfa/header.jsp" %>
<div class="orta_div_sag">
<label id="kayitKontrol">${kayitKontrol}</label>
 <c:choose> 
	<c:when test="${isAuthenticated=='true'}">
<form:form id="yapiKayitForm" onsubmit="yapiKaydet(); return false;"   class="cmaForm" name="yapiKayitForm" cssClass="formstil" cssStyle="padding-left:50px;padding-top:50px"  action="/sera/cenvyapi/yapiGuncelle.htm" method="POST"  modelAttribute="cenvdeger" enctype="multipart/form-data">
<table >
<form:hidden path="id" value="${cenvDoluVeriler.id}"/>
<tr><td>
<a>Sınıf </a>
</td>
<td><form:select  class="required" path="tip1" id="checked" name="checked">
<form:option value="${cenvDoluVeriler.tip1}" label="${cenvDoluVeriler.tip1}"/>
<c:choose>
<c:when test="${kokKontrol==false}">
<form:option value="Kök" label="Kök"/>
</c:when>
</c:choose>
</form:select>
<form:errors path="tip1"></form:errors>
</td>
</tr>

<tr id="parentdiv"><td>
<a>Bağlı Olduğu Bölüm </a>
</td><td>
<form:select name="parentId" id="parentId" class="required" path="parentId">
<form:option value="${parentOlayi2.id}" label="${parentOlayi2.baslik}"/>
</form:select>
<form:errors path="parentId"></form:errors>
</td></tr>

<tr><td>
<a>Başlık </a>
</td>
<td class="inputyazi">
<form:input name="baslik" class="required" value="${cenvDoluVeriler.baslik}" id="baslik" path="baslik" maxlength="100" size="100"  />
<form:errors path="baslik"></form:errors>
</td>
</tr>
<tr><td>
<a>Birim </a>
</td>
<td>
<form:select class="required" path="birim" id="checked2" name="checked2">
<form:options items="${birimler}" itemValue="deger" itemLabel="deger"/>
</form:select>
<form:errors path="birim"></form:errors>
</td>
</tr>
<tr><td>
<a>Değer Tipi  </a>
</td><td>

    <div id="hesapdiv1">
         <form:select name="hesapliste1" class="required"  path="tip2" id="hesapliste1" >
                        <form:option  value="${cenvDoluVeriler.tip2}" label="${cenvDoluVeriler.tip2}"/>
						<form:option  value="Hesap" label="Hesap"/>
						</form:select>
						<form:errors path="tip2"></form:errors>
    </div>
</td></tr>

<tr id="sabitdiv">
<td><a>Sabit Değer </a></td>
<form:hidden path="sabitId" value="${sabitdeger.id}"></form:hidden>
<td class="inputyazi">
<form:input  name="sabit"  id="sabit" path="sabit" maxlength="20" size="20" value="${sabitdeger.sabit}"  />
<form:errors path="sabit"></form:errors>
</td>
</tr>


<tr>
<td></td>
<td class="submit">
<input id="button" type="submit"  value="Kaydet"  />
</td>
</tr>
</table>
</form:form>
</c:when>
	<c:otherwise>
		<div class="orta_div_sag">
			Bu içeriğe erişmek için giriş yapmalısınız.
		</div>
	</c:otherwise>
	</c:choose>	
</div>
</div>
<%@include file="/WEB-INF/jsp/ana_sayfa/footer.jsp" %>
</body>
</html>
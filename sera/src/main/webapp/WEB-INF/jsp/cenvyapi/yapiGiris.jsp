<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Yapı Oluşturma Sayfası</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery.ui.core.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery-ui-1.8.22.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery.cookie.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dynatree/ui.dynatree.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery.dynatree-1.2.2.js"/>"></script>

<link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/css/form/form2.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<c:url value="/resources/js/zebra/zebra_dialog.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/zebra_dialog.css"/>" type="text/css" />

<!-- ağacı pencere içerisinde göstermemizi sağlayan jscript kodları bulunmaktadır  -->
<script type="text/javascript">
	function agacGetir(){
		$("#tree").empty(); 
	 	$("#tree").dynatree("destroy");
		$.ajax({
	        type: "POST",
	        url: "/sera/cenvyapi/listTumAgac.htm",
	        cache: false,
	        async: false,
	        success: function(response){
	        // we have the response
	        //kökü ekle öncelikle
	           for(var i=0;i<response.length;i++){
	        	   if(response[i].seviye==0){
	        		   $("#tree").append(
	        				   '<ul>'+ 
	        				   '<li  id="li'+response[i].id+'" class="folder">'+response[i].baslik+
	        				   '<ul id="ul'+response[i].id+'"></ul>'+
	        				   '</ul>'
	        				            );
	        	   }else{
		        		  //şimdi iç içe ekleye ekleye gideceğiz ve böylece elemanlar baba oğul şeklinde dizilecek
		        		  //yaprakların class ı folder olmayacak
		        		  if(response[i].tip1=="Yaprak"){
		        			  $("#ul"+response[i].parentId).append(
			        				   '<li  id="li'+response[i].id+'">'+response[i].baslik+
			        				   '<ul id="ul'+response[i].id+'"></ul>'
			        				            ); 
		        		  }else{
		        		      $("#ul"+response[i].parentId).append(
		        				   '<li  id="li'+response[i].id+'" class="folder">'+response[i].baslik+
		        				   '<ul id="ul'+response[i].id+'"></ul>'
		        				            );
		        		  }
	        	   }
	           }
	        
	        },
	        error: function(e){
	        $.Zebra_Dialog('Error: ' + e);
	        } 
	        });
		$("#tree").dynatree();
		$("#tree").dynatree("getRoot").visit(function(node){
			node.expand(true);
		});
	} 
	
	   </script> 
<!--  ağaç yapısı kodu bitiş  -->


<script type="text/javascript">  
$(document).ready(function(){	
     agacGetir();
	//Functions Starts
	function validateNum(txtNum){
		   var strValidChars = "0123456789.-";
		   var strChar;
		   var blnResult = true;

		   if (txtNum.length == 0) return true;

		   //  test strString consists of valid characters listed above
		   for (i = 0; i < txtNum.length && blnResult == true; i++)
		      {
		      strChar = txtNum.charAt(i);
		      if (strValidChars.indexOf(strChar) == -1)
		         {
		         blnResult = false;
		         }
		      }
		   return blnResult;
	}​
	//Functions Ends
	
	$("#checked").live(
	'val',
	function(){
		if($("#checked").val()=="Kök"){
			//show the hidden div
			$("#parentdiv").hide("slow");
			$("#parentId").attr('class','');
			//$("#hesapdiv1").show("fast");
	         $('#hesapliste1')
         .empty().append($("<option></option>")
         .attr("value","Hesap")
         .text("Hesap"));
			//$("#hesapdiv2").hide("fast");
		}
		else if($("#checked").val()=="Dal"){
			//show the hidden div
			$("#parentdiv").show("slow");
			$("#parentId").attr('class','required');
			//$("#hesapdiv1").show("fast");
		// $("#hesapliste1").empty().append('<option  value=""></option>');
         $("#hesapliste1").empty().append('<option  value="Hesap">Hesap</option>');
			//$("#hesapdiv2").hide("fast");
		}
		else
		{
			//otherwise, hide it
			$("#parentdiv").show("slow");
			$("#parentId").attr('class','required');
			//$("#hesapdiv2").show("fast");
			$("#hesapliste1").empty().append('<option  value=""></option>');
			$("#hesapliste1").append('<option  value="Sabit">Sabit</option>');
			$("#hesapliste1").append('<option  value="Elle">Elle</option>');
			//$("#hesapdiv1").hide("fast");
		}
		
	}
	);
	$("#parentdiv").hide("fast"); 
	$("#sabitdiv").hide("fast");
	$("#sabit").attr('class','');	
   
	/*if($("#checked").val()=="Kök"){
		//show the hidden div
		$("#parentdiv").hide("slow");
		//$("#hesapdiv1").show("fast");
		//$("#hesapliste1").empty().append('<option  value=""></option>');
		$("#hesapliste1").empty().append('<option  value="Hesap">Hesap</option>');
		//$("#hesapdiv2").hide("fast");
	}
	else if($("#checked").val()=="Dal"){
		//show the hidden div
		$("#parentdiv").show("slow");
		//$("#hesapdiv1").show("fast");
		//$("#hesapliste1").empty().append('<option  value=""></option>');
		$("#hesapliste1").empty().append('<option  value="Hesap">Hesap</option>');
		//$("#hesapdiv2").hide("fast");
	}
	else
	{
		//otherwise, hide it
		$("#parentdiv").show("slow");
		//$("#hesapdiv2").show("fast");
		$("#hesapliste1").empty().append('<option  value=""></option>');
		$("#hesapliste1").append('<option  value="Sabit">Sabit</option>');
			$("#hesapliste1").append('<option  value="Elle">Elle</option>');
		//$("#hesapdiv1").hide("fast");
	}*/
	
	// Add onclick handler to checkbox w/id checkme
   $("#checked").change(
	function ekleme(){
		if($("#checked").val()=="Yaprak"){
				//otherwise, hide it
				$("#parentdiv").show("slow");
				$("#parentId").attr('class','required');
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
				$("#parentId").attr('class','required');
				
				
				$("#sabit").attr('class','');	 
				$("#sabitdiv").hide("slow");
				$("#sabit").attr("value","")
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
				$("#parentId").attr('class','');
				//ağaç tipine göre hesaplama listesini güncelle
				//$("#hesapdiv1").show("fast");
				$("#hesapliste1").empty().append('<option  value=""></option>');
				$("#hesapliste1").append('<option  value="Hesap">Hesap</option>');
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
			$("#sabit").attr("value","")
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
			$("#sabit").attr("value","")
		}
		
	}		
	
	);
		
});


</script>
<script>
  $(document).ready(function(){
    $("#yapiKayitForm").validate();
  });
</script> 
<script>
function cocukKontrol(){
	var parentId,sonuc=1;
	var tip1;
	var globalResponse="";
	tip1=$("#checked").val();
	parentId=$("#parentId").val();
	//başla
    //bağlı olduğu bölümün yaprağı var mı onu kontrol et
    //yaprak var ise dal kaydetmeye izin verme.
    //dal var ise de yaprak kaydetmeye izin verme
  
   if(parentId!=""){
   //başla
  $.ajax({
    type: "POST",
    url: "/sera/cenvyapi/cocukTipOgren.htm",
    data: "parentId=" + parentId,
    cache: false,
    async: false,
    success: function(response){
    // dönen tip seçilen tipe eşit ise kaydet
    globalResponse=response;
    if(response!=""){
	    if(tip1!=globalResponse){	    	
	    	
		       $.Zebra_Dialog('Bu bölümün alt sınıfı olan '+response+' tipinde sınıf seçmelisiniz');	
		       sonuc=-1;
	    }
    }
        
    }
    });
    //bitir	
   }
	if(sonuc==1){
		//sonuc bir ise sorun yoktur kayıt işlemini yapabiliriz
		return true;
	}
	
		return false;
	
}


</script>

</head>
<body class="genel">
<%@include file="/WEB-INF/jsp/ana_sayfa/header.jsp" %>

<div class="orta_div_sag">
<label id="kayitKontrol">${kayitKontrol}</label>
 <c:choose> 
	<c:when test="${isAuthenticated=='true'}">
	
<table>
<tr><td>

<form:form id="yapiKayitForm"  class="cmaForm" onsubmit="return cocukKontrol();" name="yapiKayitForm" cssClass="formstil" cssStyle="padding-left:50px;padding-top:50px"  action="/sera/cenvyapi/yapiKaydet.htm" method="POST"  modelAttribute="cenvdeger" enctype="multipart/form-data">
<table >
<form:hidden path="id" value="${cenvDoluVeriler.id}"/>

<tr><td>
<a>Sınıf </a>
</td>
<td><form:select class="required" path="tip1" id="checked" name="checked">
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

<tr id="parentdiv"><td>
<a>Bağlı Olduğu Bölüm </a>
</td><td>
<form:select name="parentId" class="required" id="parentId" path="parentId">
<form:option value="${parentOlayi2.id}" label="${parentOlayi2.baslik}"/>
<form:options items="${parentOlayi}" itemValue="id" itemLabel="baslik"/>
</form:select>
<form:errors path="parentId"></form:errors>
</td></tr>

<tr><td>
<a>Başlık </a>
</td>
<td class="inputyazi">
<form:input name="baslik" class="required " id="baslik" path="baslik" maxlength="100" size="100"  />
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
<tr id="degertipdiv"><td>
<a>Değer Tipi  </a>
</td><td>

    <div id="hesapdiv1">
         <form:select name="hesapliste1" class="required"  path="tip2" id="hesapliste1" >
						<form:option  value="Hesap" label="Hesap"/>
						</form:select>
						<form:errors path="tip2"></form:errors>
    </div>
</td></tr>

<tr id="sabitdiv">
<td><a>Sabit Değer </a></td>
<form:hidden path="sabitId" value="${sabitdeger.id}"></form:hidden>
<td class="inputyazi">
<form:input  name="sabit" class="required"  id="sabit" path="sabit" maxlength="20" size="20" value="${sabitdeger.sabit}"  />
<form:errors path="sabit"></form:errors>
</td>
</tr>

<tr>
<td></td>
<td class="submit">
<input id="button" class="submit"  type="submit"  value="Kaydet"  />
</td>
</tr>
</table>
</form:form>
</td>
<td style="padding-left: 300px;padding-top:50px;">
<table align="right"  frame="border"><tr><td  width="350px" height="400px">
<input src="<c:url value="/resources/image/refresh.png"/>" onclick="agacGetir();" type="image" alt="submit"></input>
<div style="width:100%;height:100%;overflow:scroll;" id="tree"></div>
</td>
</tr>
</table>
</td></tr>
</table>

</c:when>
	<c:otherwise>
		<div class="orta_div_sag">
			Bu icerige erismek icin giris yapmalisiniz.
		</div>
	</c:otherwise>
	</c:choose>	
</div>
</div>
<%@include file="/WEB-INF/jsp/ana_sayfa/footer.jsp" %>
</body>
</html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Ağaç</title>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery.ui.core.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery-ui-1.8.22.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery.cookie.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dynatree/ui.dynatree.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery.dynatree-1.2.2.js"/>"></script>

 <link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>" rel="stylesheet" type="text/css" />
 <link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/css/yapi/agac.css"/>" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/css/jquery.ui.all.css"/>" type="text/css" />
  
<script type="text/javascript" src="<c:url value="/resources/js/zebra/zebra_dialog.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/style.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/zebra_dialog.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/ir_black.css"/>" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/zebra/highlight.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zebra/functions.js"/>"></script>

<script type="text/javascript">
	$(document).ready(function (){
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
	        			   $("#tree").append('<ul>'+ 
	        				   '<li  id="li'+response[i].id+'"  class="folder">'+
	        				   response[i].baslik+
	        				   '<ul id="ul'+response[i].id+'"></ul>'+
	        				   '</ul>'
	        				   );
	        	   }else{
		        		  //şimdi iç içe ekleye ekleye gideceğiz ve böylece elemanlar baba oğul şeklinde dizilecek
		        		  //yaprakların class ı folder olmayacak
		        		  if(response[i].tip1=="Yaprak"){
		        			  $("#ul"+response[i].parentId).append(
			        				   '<li  id="li'+response[i].id+'">'+
			        				   response[i].baslik+
			        				   '<ul id="ul'+response[i].id+'"></ul>'
			        				            ); 
		        		  }else{
		        		      $("#ul"+response[i].parentId).append(
		        				   '<li  id="li'+response[i].id+'" class="folder">'+
		        				   response[i].baslik+
		        				   '<ul id="ul'+response[i].id+'"></ul>'
		        				            );
		        		  }
	        	   }
	           }
	        
	        },
	        error: function(e){
	        $.Zebra_Dialog('Error1: ' + e);
	        } 
	        });
		
	
       
         
    }); 
	$(function() {
		
		 // Attach the dynatree widget to an existing <div id="tree"> element
       // and pass the tree options as an argument to the dynatree() function:
       $("#tree").dynatree({
           onActivate: function(node) {
           	
              $("#sil_dugme").attr('onclick',"onayAl("+node.data.key.substring(2)+")");
             	$( "#dialog-form" ).dialog( "open" );
           }
       });
       $("#tree").dynatree("getRoot").visit(function(node){
			node.expand(true);
		});
       
	});
</script>
<script>    
	
	$(function() {	
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
		
		$( "#dialog-form" ).dialog({
			autoOpen: false,
			modal: true,
			height:90,
			width:170,
			resizable:false,
			title:"İşlem Seçiniz",
			close: function() {
				$( this ).dialog( "close" );
			}
		});
	});

	        function yapiSil(id) {
	        
	        $.ajax({
	        type: "POST",
	        url: "/sera/cenvyapi/sil.htm",
	        data: "id=" + id ,
	        cache: false,
	        success: function(response){
	        // we have the response
	        if(response=="1"){
	        	$.Zebra_Dialog("Silindi");
	        	location.reload();
	        	
	        }
		           
	        },
	        error: function(e){
	        $.Zebra_Dialog('Error2: ' + e);
            } 
	        });
	        }
	        
	function onayAl(id) {
		$("#dialog-form").dialog("destroy");
		$.Zebra_Dialog('Bunu ve tüm alt sınıflarını silmek istiyor musunuz?', {
		    'type':     'question',
		    'title':    '',
		    'buttons':  [
							{caption: 'Hayır', callback: function() { }},
		                    {caption: 'Evet', callback: function() { yapiSil(id)}},
		                ]
		});		
	}
	</script> 

</head>
<body class="genel">

   
	<jsp:include page="/WEB-INF/jsp/ana_sayfa/header.jsp" />
	<c:choose>
	<c:when test="${isAuthenticated=='true'}">
	<div class="orta_div_sag">
	<div id="dialog-form">
	<input type="button" id="sil_dugme" value="Sil" onclick=""></input>
	<input type="button" value="Güncelle"></input>
	</div>
		<div id="tree"></div>
		
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
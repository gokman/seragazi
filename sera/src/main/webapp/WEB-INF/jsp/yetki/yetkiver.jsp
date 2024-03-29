<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Yetki Verme Ekranı</title>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
  <link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>" rel="stylesheet" type="text/css" />
 <link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />  
 <link rel="stylesheet" href="<c:url value="/resources/css/yapi/agac.css"/>" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dynatree/ui.dynatree.css"/>" />  
 
<script type="text/javascript" src="<c:url value="/resources/js/zebra/zebra_dialog.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/style.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/zebra_dialog.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/ir_black.css"/>" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/zebra/highlight.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zebra/functions.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery.ui.core.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery-ui-1.8.22.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery.cookie.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/dynatree/jquery.dynatree-1.2.2.js"/>"></script>

<script type="text/javascript">
	$(document).ready(function (){
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
	        		   $("#tree").append(  '<ul>'+ 
				        				   '<li  id="li'+response[i].id+'" class="folder">'+response[i].baslik+
				        				   '<input style="margin-left:5px" class="yetkiver_dugme" type="image"  '+ 
										   'id="kutu'+response[i].id+'"'+ 
										   'onclick="yetkiEkrani('+response[i].id+')"  />'+
				        				   '<ul id="ul'+response[i].id+'"></ul>'+
				        				   '</ul>' );
	        	   }else{
		        		  //şimdi iç içe ekleye ekleye gideceğiz ve böylece elemanlar baba oğul şeklinde dizilecek
		        		  //yaprakların class ı folder olmayacak
		        		  if(response[i].tip1=="Yaprak"){
		        			  $("#ul"+response[i].parentId).append(
			        				   '<li  id="li'+response[i].id+'">'+response[i].baslik+
			        				   '<input style="margin-left:5px" class="yetkiver_dugme" type="image"  '+ 
									   'id="kutu'+response[i].id+'"'+ 
									   'onclick="yetkiEkrani('+response[i].id+')"  />'+
			        				   '<ul id="ul'+response[i].id+'"></ul>'
			        				            ); 
		        		  }else{
		        		      $("#ul"+response[i].parentId).append(
		        				   '<li  id="li'+response[i].id+'" class="folder">'+response[i].baslik+
		        				   '<input style="margin-left:5px" class="yetkiver_dugme" type="image"  '+ 
								   'id="kutu'+response[i].id+'"'+ 
								   'onclick="yetkiEkrani('+response[i].id+')"  />'+
		        				   '<ul id="ul'+response[i].id+'"></ul>'
		        				            );
		        		  }
	        	   }
	        	   yetkiVarMi(response[i].id);
	           }
	        
	        },
	        error: function(e){
	        $.Zebra_Dialog('Error: ' + e);
	        } 
	        });
	});
	$(function(){
	    // Attach the dynatree widget to an existing <div id="tree"> element
	    // and pass the tree options as an argument to the dynatree() function:
	    $("#tree").dynatree({
	    		
	    });
	    $("#tree").dynatree("getRoot").visit(function(node){
			node.expand(true);
		});
	});

</script>

<script type="text/javascript">
$(document).ready(function(){
	kullaniciDegis($("#checked3").val());
});



       var globalid;
       var dateKontrol;
	        
	        function yetkiEkrani(elemanId){
	        	
	        	 $.Zebra_Dialog('Devam etmek istiyor musunuz?', {
	        		    'type':     'question', 
	        		    'title':    '',
	        		    'buttons':  [
	        							{caption: 'Hayır', callback: function() { }},
	        		                    {caption: 'Evet', callback: function() { yetkiDegistir(elemanId,$("#checked3").val())}},
	        		                ]
	        	});	
	        	
	        }
	        
	        function yetkiVarMi(elemanId){
	        	var yeniId="#kutu"+elemanId;
	        	var sonuc=false;
	        	$.ajax({
	    	        type: "POST",
	    	        url: "/sera/yetki/controlyetkiVarMi.htm",
	    	        data: "id=" + elemanId+"&userId="+$("#checked3").val() ,
	    	        cache: false,
	    	        async: false,
	    	        success: function(response){
	    	        // we have the response
	    	        
	    	       if (response==true){
	    	    	  
	    	    	   $(yeniId).attr('src','<c:url value="/resources/image/yesil.jpg" />');
	    	    	   //kayıt işlemi var
	    	    	   sonuc=true;
	    	       }else{
	    	    	   $(yeniId).attr('src','<c:url value="/resources/image/kirmizi.jpg" />');
	    	    	   
	    	       }
	    	        
	    		          
	    	       },
	    	       error: function(e){
	    	       $.Zebra_Dialog('Error: ' + e);
	               } 
	    	       });
	        	return sonuc;
	        	
	        }
	       
	        function kullaniciDegis(userId){
	        	$.ajax({
	    	        type: "POST",
	    	        url: "/sera/yetki/controlkokyetkiVarMi.htm",
	    	        data: "userId="+$("#checked3").val() ,
	    	        cache: false,
	    	        async: false,
	    	        success: function(response){
	    	        // we have the response
	    	        
	    	       if (response==true){
	    	    	   $("#kutukokyetkiid").attr('value','<c:url value="/resources/image/yesil.jpg" />');
	    	    	  
	    	       }else{
	    	    	  // $.Zebra_Dialog('yeni id='+yeniId);
	    	    	   $("#kutukokyetkiid").attr('value','<c:url value="/resources/image/kirmizi.jpg" />');  
	    	    	   
	    	       }
	    	        
	    	        
	    		          
	    	        },
	    	        error: function(e){
	    	        $.Zebra_Dialog('Error: ' + e);
	                } 
	    	        }); 
	        	
	        }
	        
	        //yetki değiştirme işlemi burada yapılır
	        function yetkiDegistir(elemanId,userId){
	        	//yetki verme mi kaldırma mı yapıldığınını değerde tut
	        	var yetkiIslemTip;
	        	$.ajax({
	    	        type: "POST",
	    	        url: "/sera/yetki/yetkiDegistir.htm",
	    	        data: "id=" + elemanId+"&userId="+userId ,
	    	        cache: false,
	    	        async: false
	    	        });
	        	/*bu bölümü iptal etmemin nedeni ise ağaç elemanlarını kapatıp açınca eleman ilk haline dönüyor. 
	        	Bunun çaresini bulamadım. Ben de anlık değişim yerine 
	        	doğrudan sayfayı yeniliyorum.*/
	        	
	        	//işlemi yaptıktan hemen sonra yazıyı değiştirmemiz lazım
	        	//bunu şimdilik iptal ettim sayfayı yeniliyorum işimi görüyor
	        	yetkiIslemTip=yetkiVarMi(elemanId);
	        	//yetki kutucuğundaki yazıyı yukarıdaki yöntem ile değiştirdikten sonra aynı yazıyı tüm babalar(parent) a uygula
	        	//bunu da iptal ediyorum aynı şekilde
	        	yetkiYaziGuncelle(elemanId,userId,yetkiIslemTip);
	        	
	        	//location.reload();
	        }
	        
	        function yetkiYaziGuncelle(elemanId,userId,yetkiIslemTip){
	        	var yeniId,mevcutKutuId;
	        	mevcutKutuId="#kutu"+elemanId;
	            
	        	$.ajax({
	    	        type: "POST",
	    	        url: "/sera/cenvyapi/getAllTieds.htm",
	    	        data: "elemanId="+elemanId+"&userId="+userId,
	    	        cache: false,
	    	        async: false,
	    	        success: function(response){
	    	        // elimizde bu elamanın tüm babaları(parents)nın id si mevcut
	    	         if(yetkiIslemTip==true){
	    	        	 $(mevcutKutuId).focus();
	    	        	 $(mevcutKutuId).attr('src','<c:url value="/resources/image/yesil.jpg" />');
	    	        	 $(mevcutKutuId).blur();
	    	        	 
	    	         }else{
	    	        	 $(mevcutKutuId).focus();
	    	        	 $(mevcutKutuId).attr('src','<c:url value="/resources/image/kirmizi.jpg" />');
	    	        	 $(mevcutKutuId).blur();
	    	         }
	    	         for(var i =0 ; i < response.length ; i++){
	    	        	    //değiştireceğimiz kutu idsi artık elimizde
	    	        	   
			    	        yeniId="#kutu"+response[i];
			    	        if(yetkiIslemTip==true){
			    	        	
			    	        	$(yeniId).attr('src','<c:url value="/resources/image/yesil.jpg" />');
			    	        	
			    	        	
			    	        }else{
			    	        	
			    	        	$(yeniId).attr('src','<c:url value="/resources/image/kirmizi.jpg" />');
			    	        	
			    	        	
			    	        }
			    	    	   
			    	       
	    	         }    
	    		          
	    	        },
	    	        error: function(e){
	    	        $.Zebra_Dialog('Error: ' + e);
	                } 
	    	        }); 
	        	
	        }
	          
	        </script>
</head>
<body class="genel">
   
	<jsp:include page="/WEB-INF/jsp/ana_sayfa/header.jsp" />
	<c:choose>
	<c:when test="${isAuthenticated=='true'}">
	<div class="orta_div_sag">
		<table width="100%">
		    <tr>
		    <td align="center" style="padding-bottom: 20px;">
		    <form:form  action="xxx.htm" method="POST"  modelAttribute="user" enctype="multipart/form-data">
		    <form:select onchange="kullaniciDegis(${user.userId})"  path="username" id="checked3" name="checked3"> 
		    <c:forEach var="user" items="${users}"> 
		      <form:option value="${user.userId}"><c:out value="${user.name} ${user.surname}" /></form:option>
		    </c:forEach>
			</form:select>
			</form:form>
			</td>
		    </tr>
			<tr>
				<td>
					<div id="tree"></div>	
				</td>
			</tr>
		</table>
		<div align="center" id="kok"></div>
		<div class="formspacer"></div>
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
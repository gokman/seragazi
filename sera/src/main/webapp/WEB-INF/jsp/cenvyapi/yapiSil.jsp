<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Yapı Silme Ekranı</title>

<link rel="stylesheet" href="<c:url value="/resources/css/form/general.css"/>"></link>
<link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/form.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/yapi/agac.css"/>" type="text/css" />

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zebra/zebra_dialog.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/style.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/zebra_dialog.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/ir_black.css"/>" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/zebra/highlight.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zebra/functions.js"/>"></script>

<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
 
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
   <script>
  $(document).ready(function(){
    $("#formgiris").validate();
  });
  </script>
<script type="text/javascript">
       var globalid;
	        function doAjaxPost(aydi,seviye,tip,tip2) {
	        // get the form values
	           globalid=aydi;
	           var sev=seviye;
	           var tipp=tip;
	           //tip2 lazım oldu. çünkü sabit ise form gelmeyecek. elle ise gelecek
	           var tipp2=tip2;
	          // var tirnak="'";
	        
	        $.ajax({
	        type: "POST",
	        url: "/sera/cenvsabit/sabitGir/dalgetir.htm",
	        data: "id=" + globalid ,
	        cache: false,
	        success: function(response){
	        // we have the response
	        
	        var elements=$('.katmanlar').filter(function(){
		    	return (this.id.replace('div','')>sev);
		    });
	        
	        $(".yaprakdiv").remove();
	        $(elements).remove();
	           
		        for(var i =0 ; i < response.length ; i++){
		        	
		        	 if (i==0){
					      $("#kok").append('<table width="600px" align="center" id="div'+response[i].seviye+'" class="katmanlar"><tbody><tr></tr></tbody></table>');
					      
					    }
		        		
		        	
					    	$("#div"+response[i].seviye).append(
						    		'<table width="200px" align="left" class="table_sil">'+
						    		'<tr align="center">'+
						    		'<input type="submit" class="baslik_dugme" '+
			                		'value="'+
			        		         response[i].baslik+
			        		        '" onclick="doAjaxPost('+
			        		         response[i].id+
			        				','+response[i].seviye+
			        				',\''+response[i].tip1+'\''+
			        				',\''+response[i].tip2+'\')"'+
			        				'/>'+
			        				'</tr><tr align="center">'+
			        				'<input type="submit" class="sil_dugme" '+
			                		'value="Sil" onclick="onayAl('+
			        		         response[i].id+
			        		        ')"'+
			        				'/>'+
						    		'</tr>'+
			        				'</table>');
					   if(i+1==response.length){
						   $("#div"+response[i].seviye).append('<tr><div class="cizgi"></div></tr>');
					   } 	
					    	
			     }
		
		           
	        },
	        error: function(e){
	        alert('Error: ' + e);
            } 
	        });
	        }
	        </script>
	        <script type="text/javascript">
	        function yapiSil(id) {
	        
	        $.ajax({
	        type: "POST",
	        url: "/sera/cenvyapi/sil.htm",
	        data: "id=" + id ,
	        cache: false,
	        success: function(response){
	        // we have the response
	        if(response=="1"){
	        	alert("Silindi");
	        	location.reload();
	        }
		           
	        },
	        error: function(e){
	        alert('Error: ' + e);
            } 
	        });
	        }
	        </script>
	    <script>
	function onayAl(id) {
		
		$.Zebra_Dialog('Bunu ve tüm alt sınıflarını silmek istiyor musunuz?', {
		    'type':     'question',
		    'title':    '',
		    'buttons':  [
							{caption: 'Vazgeç', callback: function() { }},
		                    {caption: 'Sil', callback: function() { yapiSil(id)}},
		                ]
		});		
	}
	</script> 
	        
</head>
<body class="genel">
	
					<%@include file="/WEB-INF/jsp/ana_sayfa/header.jsp" %>
	<c:choose>
	<c:when test="${isAuthenticated=='true'}">
<div class="orta_div_sag">
<table width="100%"><tr><td>
<table width="200px"  align="left" class="kok_table_sil">
				<tbody><tr align="center"><td>
				<input type="submit" class="baslik_dugme" value="${kok.baslik}" onclick="doAjaxPost(${kok.id},${kok.seviye},'${kok.tip1}','${kok.tip2}')"/>
			    </td></tr><tr align="center"><td>
			    <input type="submit" class="sil_dugme" value="Sil" onclick="onayAl(${kok.id})" />
			    </td></tr></tbody>
</table>
</td></tr></table>
<div align="center"  id="kok">

</div>
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
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sabit Giriş Ekranı</title>
<link rel="stylesheet" href="<c:url value="/resources/css/form/general.css"/>"></link>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript">
	        function doAjaxPost(aydi,seviye,tip) {
	        // get the form values
	           var id=aydi;
	           var sev=seviye;
	           var tipp=tip;
	          // var tirnak="'";
	        
	        $.ajax({
	        type: "POST",
	        url: "/sera/cenvsabit/sabitGir/dalgetir.htm",
	        data: "id=" + id ,
	        cache: false,
	        success: function(response){
	        // we have the response
	        
	        var elements=$('.katmanlar').filter(function(){
		    	return (this.id.replace('div','')>sev);
		    });
	        
	        $(".yaprakdiv").remove();
	        $(elements).remove();
	           
		        for(var i =0 ; i < response.length ; i++){
		        		
		        	//$("input:[id>"+response[i].seviye+"]").remove();
					    if (i==0){
					      $("#kok").append('<div id="div'+response[i].seviye+'" class="katmanlar"></div>');
					    }
					    
					    $("#div"+response[i].seviye).append('<input type="submit" '+
		                		'value="'
		        		+response[i].baslik+
		        		'" onclick="doAjaxPost('+response[i].id+
		        				','+response[i].seviye+
		        				',\''+response[i].tip1+'\')" /><br>');
		        	
		                             
		                
		        }
		        
		    if(tipp=="Yaprak"){
	        	$("#kok").append('<div class="yaprakdiv" id="yaprakid"></div>');
	        	$("#yaprakid").append('<br/><form:form action="/sera/cenvsabit/degerkaydet.htm" method="POST"  modelAttribute="cenvsabit" enctype="multipart/form-data">'+
    	        'Değer Girin:<form:input path="deger" size="50"/><br/>'+
    	        '<form:hidden path="hasId" value="'+
    	         aydi+
    	        '"/>'+
    			'<input type="submit" value="Kaydet"></input>'+
    			'</form:form>');
	        }
		        
		     
	               
	        },
	        error: function(e){
	        alert('Error: ' + e);
            } 
	        });
	        }
	        </script>

</head>
<body>
<!-- <a href="/sera/cenvsabit/sabitGir/${kok.id}.htm" id="kok"  >${kok.baslik}</a> -->
<input type="submit" value="${kok.baslik}" onclick="doAjaxPost(${kok.id},${kok.seviye},'${kok.tip1}')" /> 
<!-- <input type="hidden" id="id" value="${kok.id}">  -->
<div  id="kok"></div>
<div class="formspacer"></div>
<!--<c:forEach var="dal" items="${dallar}" varStatus="rowCounter">
<a href="/sera/cenvsabit/sabitGir/${dal.id}.htm" id="kok">${dal.baslik}</a>
</c:forEach>-->

 
</body>
</html>
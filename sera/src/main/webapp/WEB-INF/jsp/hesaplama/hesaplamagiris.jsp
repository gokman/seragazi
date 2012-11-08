<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Hesaplama Ekranı</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/form/general.css"/>"></link>
<link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/ana_sayfa/form.css"/>"
	type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/ana_sayfa/menu.css"/>"
	type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/yapi/agac.css"/>" type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>"
	type="text/css" />
<style>
#sortable {
	list-style-type: none;
	margin: 0;
	margin-top: 40px;
	padding: 0;
}

#sortable li {
	background-color: #EAEAEA;
	vertical-align: middle;
	margin: 3px;
	padding-top: 10px;
	padding-left: 1px;
	padding-right: 1px;
	float: left;
	width: 80px;
	height: 50px;
	font-size: 18px;
	font-stretch: narrower;
	text-align: center;
}

#islemgosterdivid {
	margin-top: 30px;
}

#kutubaslikid {
	text-align: center;
	font-weight: bold;
	font-size: 24px;
	font-family: Arial;
	margin-top: 30px;
}
</style>
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery.ui.all.css"/>"
	type="text/css" />

<script type="text/javascript"
	src="<c:url value="/resources/js/drag_drop/jquery-1.7.2.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/drag_drop/jquery-ui-1.8.22.custom.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/drag_drop/jquery.ui.draggable.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/drag_drop/jquery.ui.droppable.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/drag_drop/jquery.ui.widget.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/drag_drop/jquery.ui.mouse.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/drag_drop/jquery.ui.core.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/drag_drop/jquery.ui.resizable.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/drag_drop/jquery.ui.position.js"/>"></script>
	
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/zebra/zebra_dialog.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/style.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/zebra_dialog.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/zebra/ir_black.css"/>" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/zebra/highlight.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zebra/functions.js"/>"></script>	
<script>
$(document).ready(function(){
	var globalkutular;
  });
</script>
<script>
	$(function() {
		$( "#sortable" ).sortable();
		$( "#sortable" ).disableSelection();
	});
</script>
<script>
	var neyetikladi=0;
	var islem;
	function islemGoster(){
	    islem="@",gorunenislem="",tempislem="";
	    //id leri tutuyor
	    var result=$('#sortable').sortable('toArray');
	    
		for (var i=0;i<result.length;i++){
		     islem=islem+result[i]+"@";
		    
		     //kullanıcıya gösterme amaçlı düzenleme yapıyoruz
		     //tempislem="#"+result[i];
			// gorunenislem=gorunenislem+$(tempislem).text()+" ";
			
		}
		
			 $.Zebra_Dialog("kaydedilen işlem="+islem);
			 //$.Zebra_Dialog("kullanıcının göreceği işlem="+gorunenislem);
		
	}
		function pencere(aydi) {
		        neyetikladi="#"+aydi;
				$( "#dialog-form" ).dialog( "open" );
			}
		
	 
		 
		 function EnAltDalMi(aydi,seviye,tip,tip2){
	        	$.ajax({
			        type: "POST",
			        url: "/sera/hesaplama/kontrolEnAltDal.htm",
			        data: "id=" + aydi ,
			        cache: false,
			        success: function(response){
			        // response 1 ise formu getir
			            
			        //response 0 ise dalları getirmeye devam et
			        if (response==0){
			        	DalListele(aydi,seviye,tip,tip2);
			        }else{
			       //1 ise yaprakları ve çevresel faktörleri kutucuk halinde getir
			            $("#kutucukdivid").empty();
			            $("#islemgosterdivid").empty();
			        	kutucukGetir(aydi);
			        }
			       
				             
			        },
			        error: function(e){
			        $.Zebra_Dialog('Error: ' + e);
		            } 
			        });
	        	
	        }
		 
		 
		 function kutucukGetir(aydi){
		       
		        $.ajax({
		        type: "POST",
		        url: "/sera/hesaplama/yaprakFaktorGetir.htm",
		        data: "id=" + aydi ,
		        cache: false,
		        datatype: "json",
		        success: function(response){
		        	globalkutular=response;
		        /*************************************************************/
		        // kutucukları elde eder etmez kullanıcıya bir form açacağız.
		        //bu formda kullanıcıya hangi gaz için hesaplama yapacağını soracağız
		        //ve hesaplamaya dahil olacak yaprakları seçmesini isteyeceğiz.
		        //şimdi elimizdeki kutuları açılacak forma ekleyelim
		        $("#kutuelemanlardivid").empty();
		        for (var i=0;i<response.length;i++){
				        $("#kutuelemanlardivid").append(
				        '<input type="checkbox" checked="yes" value="'+i+'" />'
				        +response[i].aciklama+'<br />'	
				        );
		        } 
		        //şimdi de formu açalım
		        $( "#dialog-form2" ).dialog( "open" );
		        //formun içine hesaplanacak dalın da id sini gönderiyorum
                $("#hesapladalid").val(aydi);
		        /*************************************************************/
		        
		         
		        },
		        error: function(e){
		        $.Zebra_Dialog('Error: ' + e);
	            } 
		        });
	        }
		 
		 
		 function DalListele(aydi,seviye,tip,tip2){
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
	        $("#kutucukdivid").empty();
	           
		        for(var i =0 ; i < response.length ; i++){
		        		
		        	//$("input:[id>"+response[i].seviye+"]").remove();
					    if (i==0){
					    	 $("#kok").append('<table width="690px" align="center" id="div'+response[i].seviye+'" class="katmanlar"><tbody><tr></tr></tbody></table>');  
					    }
					
					    
					    $("#div"+response[i].seviye).append(
					    		'<table width="230px" align="left" class="table_sil">'+
					    		'<tr align="center">'+
					    		'<input type="submit" class="detay_dugme" '+
		                		'value="'+
		        		         response[i].baslik+
		        		        '" onclick="doAjaxPost('+
		        		         response[i].id+
		        				','+response[i].seviye+
		        				',\''+response[i].tip1+'\''+
		        				',\''+response[i].tip2+'\')"'+
		        				'/>'+
		        				'</tr>'+
		        				'</table>');
					    
				   if(i+1==response.length){
					   $("#div"+response[i].seviye).append('<tr><div class="cizgi2"></div></tr>');
				   } 	
		               
		        }
		             
	        },
	        error: function(e){
	        $.Zebra_Dialog('Error: ' + e);
            } 
	        });
        }
		 
		
		 function hesapKayitKontrol(id,detay){
			 var sonuc=null;
	        	$.ajax({
			        type: "POST",
			        url: "/sera/hesaplama/kayitVarMi.htm",
			        data: "id=" + id+"&detay="+detay ,
			        cache: false,
			        async:false,
			        success: function(response){
			        
				    sonuc=response;
			        	
			        },
			        error: function(e){
			        $.Zebra_Dialog('Error: ' + e);
		            }
			        });
	        	
	        	return sonuc;
	        	
	        }
		 
		 
		//kullanıcı gazı ve elemanları seçtikten sonra kutuları ekrana getiriyoruz
	        function kutucukSonHalGetir(aydi,kutularim){
                 var gaztipi="";
	        	 for(var i =0 ; i < kutularim.length ; i++){
	        		  if (i==0){ 
					    	 $("#kutucukdivid").append(
					    	 '<fieldset><a id="kutubaslikid">Dal İçin Hesap Biçimi Oluştur</a><br/>'+
					    	 '<ul id="sortable"></ul></fieldset>');
					    	 $( "#sortable" ).sortable();
					 	     $( "#sortable" ).disableSelection(); 
					    	 //her kutucuğun başında ve sonunda boş kutucuk olmalı
					    	 //bu nedenle ilk önce boş kutucuğu koyuyoruz
					    	 //bunun id si de farklı olmalı o nedenle büyük bir sayı veriyoruz
					    	 $("#sortable").append(
							    		'<li id="i'    
							    		+1000+
							    		'" '+
							    		'name="islem" onDblClick="pencere(this.id)">'+
							    		' </li>'
							     );
					    }
					    
					    $("#sortable").append(
					    		'<li id="'
					    		+kutularim[i].tip.substr(0,1)+kutularim[i].id+
					    		'" title="'+kutularim[i].aciklama+'">'
					    		+kutularim[i].aciklama.substr(0,5)+
					    		'...</li>'
					     );
					    $("#sortable").append(
					    		'<li id="i'
					    		+i+
					    		'" '+
					    		'name="islem" onDblClick="pencere(this.id)">'+
					    		' </li>'
					     );  
					   
					     
					   /* 
					      doğrudan $("#gazlistid").val() şeklinde yerleştiremedik. hata verdi. bu yüzden
					      değişkene atadık
					   */
					   gaztipi=$("#gazlistid").val();
					    if (i+1==kutularim.length){
					    	$("#islemgosterdivid").append(
					    			 '<fieldset style="display:inline">'+
										'<table style="width:250px;"><tr>'+
										'<td><a style="font-weight: bold;">Hesaplanacak Gaz </a></td>'+
										'<td>'+
										'<a>'+$("#gazlistid").val()+'</a>'+
										'</td></tr><tr>'+
										'<td class="submit" colspan="2"><input style="padding-left:20px;" type="submit" onclick="hesapkaydet('+aydi+',\''+gaztipi+'\')"'+ 
										' value="Kaydet" ></input>'+
										'</td>'+
										'</tr></table>'+ 
										'</fieldset>'		
					    	);
					    }
					    
	        	}  
	        	
	        	
	        }
		
		
		
	        function hesapkaydet(aydi,gaztip){
	        	
	        	 $.Zebra_Dialog('Devam etmek istiyor musunuz?', {
	     		    'type':     'question',
	     		    'title':    '',
	     		    'buttons':  [
	     							{caption: 'Vazgeç', callback: function() { }},
	     		                    {caption: 'Kaydet', callback: function() { islemkaydet(aydi,gaztip)}},
	     		                ]
	     		});	
	        	
	        	
	        }
	        
	        
	 function islemkaydet(aydi,gaztip){
			 
			 //önce kaydedilecek işlemi ayarla
			 var kaydedilecekislem="@";
			 var result=$('#sortable').sortable('toArray');
			    
				for (var i=0;i<result.length;i++){
					kaydedilecekislem=kaydedilecekislem+result[i]+"@";
				}
	        	
	        	$.ajax({
			        type: "POST",
			        url: "/sera/hesaplama/islemkaydet.htm",
			        data: "hesap=" + kaydedilecekislem+"&id="+aydi+"&detay="+gaztip ,
			        cache: false,
			        success: function(response){
			             
			        	location.reload();
			        	
				             
			        },
			        error: function(e){
			        $.Zebra_Dialog('Error: ' + e);
		            } 
			        });
	        	
	        }	
	        
	        
	        function hesapGuncelle(aydi,gaztip){
	        	
	        	 $.Zebra_Dialog('Devam etmek istiyor musunuz?', {
	     		    'type':     'question',
	     		    'title':    '',
	     		    'buttons':  [
	     							{caption: 'Vazgeç', callback: function() { }},
	     		                    {caption: 'Güncelle', callback: function() { islemGuncelle(aydi,gaztip)}},
	     		                ]
	     		});	
	        	
	        	
	        }
	        
	        
	 function islemGuncelle(aydi,gaztip){
			 
			 //önce kaydedilecek işlemi ayarla
			 var kaydedilecekislem="@";
			 var result=$('#sortable').sortable('toArray');
			    
				for (var i=0;i<result.length;i++){
					
					kaydedilecekislem=kaydedilecekislem+result[i]+"@";
				}
				
	        	
	        	$.ajax({
			        type: "POST",
			        url: "/sera/hesaplama/islemguncelle.htm",
			        data: "hesap=" + kaydedilecekislem+"&id="+aydi+"&detay="+gaztip ,
			        cache: false,
			        success: function(response){
			             
			        	location.reload();
			        	
				             
			        },
			        error: function(e){
			        $.Zebra_Dialog('Error: ' + e);
		            } 
			        });
	        	
	        }	
			 
		
		
		
	        function kutucukSonGuncelHalGetir(aydi,kutularim){
                var gaztipi="";
	        	 for(var i =0 ; i < kutularim.length ; i++){
	        		  if (i==0){ 
					    	 $("#kutucukdivid").append(
					    	 '<fieldset><a id="kutubaslikid">Dal İçin Hesap Biçimi Oluştur</a><br/>'+
					    	 '<ul id="sortable"></ul></fieldset>');
					    	 $( "#sortable" ).sortable();
					 	     $( "#sortable" ).disableSelection(); 
					    	 //her kutucuğun başında ve sonunda boş kutucuk olmalı
					    	 //bu nedenle ilk önce boş kutucuğu koyuyoruz
					    	 //bunun id si de farklı olmalı o nedenle büyük bir sayı veriyoruz
					    	 $("#sortable").append(
							    		'<li id="i'    
							    		+1000+
							    		'" '+
							    		'name="islem" onDblClick="pencere(this.id)">'+
							    		' </li>'
							     );
					    }
					    
					    $("#sortable").append(
					    		'<li id="'
					    		+kutularim[i].tip.substr(0,1)+kutularim[i].id+
					    		'" title="'+kutularim[i].aciklama+'">'
					    		+kutularim[i].aciklama.substr(0,5)+
					    		'...</li>'
					     );
					    $("#sortable").append(
					    		'<li id="i'
					    		+i+
					    		'" '+
					    		'name="islem" onDblClick="pencere(this.id)">'+
					    		' </li>'
					     );  
					   
					     
					   /* 
					      doğrudan $("#gazlistid").val() şeklinde yerleştiremedik. hata verdi. bu yüzden
					      değişkene atadık
					   */
					   gaztipi=$("#gazlistid").val();
					    if (i+1==kutularim.length){
					    	$("#islemgosterdivid").append(
					    			 '<fieldset style="display:inline">'+
										'<table style="width:250px;"><tr>'+
										'<td><a style="font-weight: bold;">Hesaplanacak Gaz </a></td>'+
										'<td>'+
										'<a>'+$("#gazlistid").val()+'</a>'+
										'</td></tr><tr>'+
										'<td class="submit" colspan="2" style="padding:2px;"><input style="margin:2px;" type="submit" onclick="hesapGuncelle('+aydi+',\''+gaztipi+'\')"'+ 
										' value="Güncelle" ></input>'+
										'</td>'+
										'</tr></table>'+ 
										'</fieldset>'		
					    	);
					    }
					    
	        	}  
	        	
	        	
	        }
</script>
<script>
	$(function() {
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
		
		
		$( "#dialog-form" ).dialog({
			autoOpen: false,
			modal: true,
			buttons: {
				"Oluştur": function() {
				 
					$(neyetikladi).empty().append($("#islem").val());
					$(neyetikladi).attr('id',$("#islem").val());
                    $( this ).dialog( "close" );
                    $("#islem").val("");
				},
				İptal: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
			}
		});

	
	});
</script>

<script type="text/javascript">
       var globalid;
	        function doAjaxPost(aydi,seviye,tip,tip2) {
	        	
	        	EnAltDalMi(aydi,seviye,tip,tip2);
	        	
	        	
	        }
	        </script>
	      
	        <script>
	        $(function(){
	        	$( "#dialog:ui-dialog" ).dialog( "destroy" );
	        	
	        	$( "#dialog-form2" ).dialog({
	    			autoOpen: false,
	    			modal: true,
	    			buttons: {
	    				"Tamam": function() {
	    					//tiklenmeyenleri globalkutular listesinden çıkaracağız.
	    					//globalkutular[i]=null yapacağız
	    					var tempkutular= new Array();
	    					var counter=0;
	    					$("input[type='checkbox']:checked").each(
							    function() {
							    	
							    	  tempkutular[counter]=globalkutular[$(this).val()];
							    	
							    	
							    	counter=counter+1;
							    	 
							    	
							    }
							);
	    					globalkutular=tempkutular;
	    					/*
	    					  kayıt ilk defa yapılıyorsa kutucuksonhalgetir i çalıştır
	    					  var ise kutucuksonguncelhalgetir i çalıştır
	    					*/
	    					//dal id ile gazı gnderip kontrol et
	    					var kayitkontrol=hesapKayitKontrol($("#hesapladalid").val(),$("#gazlistid").val());
	    				
	    					if (kayitkontrol==0){
                                kutucukSonHalGetir($("#hesapladalid").val(),globalkutular);
	    					}else{
	    						//kayıt var ise güncelleme ekranını getir
	    						kutucukSonGuncelHalGetir($("#hesapladalid").val(),globalkutular);
	    						
	    					}
                            $( this ).dialog( "close" );
	    				},
	    				İptal: function() {
	    					$( this ).dialog( "close" );
	    				}
	    			},
	    			close: function() {
	    			}
	    		});

	        	 
	        	
	        });
	        </script>
</head> 
<body class="genel">

	<jsp:include page="/WEB-INF/jsp/ana_sayfa/header.jsp" />
	<c:choose>
		<c:when test="${isAuthenticated=='true'}">
			<div class="orta_div_sag">

				<div id="dialog-form" title="İşlem Gir  ">
					<form>
						<fieldset>
							<label for="islem" style="font-size: 10px;">İşlem</label> <input
								type="text" name="islem" id="islem" />
							<!-- class="text ui-widget-content ui-corner-all" -->
						</fieldset>
					</form>
				</div>
				
				<div id="dialog-form2" title="Hesabı yapılacak gazı ve elemanları seçiniz...">
				<input type="hidden" id="hesapladalid" />
					<form>
					<fieldset>
						<label for="gaz">Gaz </label>
						<select name="gaz" id="gazlistid">
							<c:forEach items="${gazlar}" var="gazim">
							<option value="${gazim.name}">${gazim.name}</option>
							</c:forEach>  
						</select>
						<label for="kutu" id="kutulabelid">Elemanlar</label>
						<div id="kutuelemanlardivid"><</div>
						</fieldset>
					</form>
				</div>



				<table width="100%">
					<tr>
						<td>
							<table width="230px" align="left" class="kok_table_giris">
								<tbody>
									<tr align="center">
										<td><input type="submit" class="detay_dugme"
											value="${kok.baslik}"
											onclick="doAjaxPost(${kok.id},${kok.seviye},'${kok.tip1}','${kok.tip2}')" />
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
				<div id="kok"></div>
				<div id="kutucukdivid" style="width: 100%;margin-left: 10px;margin-top:20px;margin-right: auto;"></div>
				<div class="formspacer"></div>
				<center>
					<div id="islemgosterdivid"></div>
				</center>
				<div class="formspacer"></div>
			</div>
		</c:when>
		
		<c:otherwise>
			<div class="orta_div_sag">Bu içeriğe erişmek için giriş
				yapmalısınız.</div>
		</c:otherwise>
	</c:choose>
	</div>
	<%@include file="/WEB-INF/jsp/ana_sayfa/footer.jsp"%>
</body>
</html>
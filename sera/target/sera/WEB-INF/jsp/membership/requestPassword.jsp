<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
			<!-- Acordion form js and css -->
			<script type="text/javascript">
			<!--
			function isNumericKey(e) {
			   var k = document.all ? e.keyCode : e.which;
			   return ((k > 47 && k < 58) || k == 8);
			}
			function extractNumeric(str) {
			   return str.replace(/\D/g,"");
			}
			// -->
			</script>
			<link rel="stylesheet" href="<c:url value="/resources/css/form/customer_form.css"/>"></link>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery-1.2.1.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery-1.2.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.form.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.metadata.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.maskedinput-1.0.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.dimensions.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.accordion.js"/>"></script>
			
			<!-- date picker -->
			
				<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/datepicker.css"/>" />
			    <link rel="stylesheet" href="<c:url value="/resources/css/datepicker/layout.css"/>" />
			
			<!--	 
				<script type="text/javascript" src="<c:url value="/resources/js/datepicker/datepicker.js"/>"></script>
			    
			    <script type="text/javascript" src="<c:url value="/resources/js/datepicker/eye.js"/>"></script>
			    
			    <script type="text/javascript" src="<c:url value="/resources/js/datepicker/utils.js"/>"></script>
			    
			    <script type="text/javascript" src="<c:url value="/resources/js/datepicker/layout.js"/>"></script>
			    
			    <script type="text/javascript" src="<c:url value="/resources/js/datepicker/jquery.js"/>"></script>
			  -->
			
			<script type="text/javascript">
			// JQUERY FOR THIS PAGE
			
				$(document)
						.ready(
								
								
								function() {
									// add * to required field labels
									$('label.required').append(
											'&nbsp;<strong>*</strong>&nbsp;');
			
									// IF SAFARI
									if ($.browser.safari) {
										// this alert shows safari detection
										//$( function() { alert("this is safari!"); } ); 
										// hide panels 2&3
										$('#sf2').hide();
										$('#sf3').hide();
										var current = 0;
										var v = $("#cmaForm")
												.validate(
														{
															subformRequired : function(
																	element) {
																return !(current == 0
																		&& element
																				.parents("#sf1").length
																		|| current == 1
																		&& element
																				.parents("#sf2").length || current == 2
																		&& element
																				.parents("#sf3").length);
															},
															errorClass : "warning",
															errorElement : "label",
															onkeyup : false,
															onblur : false
														});
										// back buttons do not need to run validation
										$("#sf2 .prevbutton").click(function() {
											//accordion.activate(0);
											current = 0;
											$('#sf2').slideUp();
											$('#sf1').slideDown();
										});
										$("#sf3 .prevbutton").click(function() {
											//accordion.activate(1);
											$('#sf3').slideUp();
											$('#sf2').slideDown();
											current = 1;
										});
										// these buttons all run the validation, overridden by specific targets above
										$(".open2").click(function() {
											if (v.form()) {
												$('#sf2').slideUp();
												$('#sf3').slideDown();
												//    accordion.activate (2);
												current = 2;
											}
										});
										$(".open1").click(function() {
											if (v.form()) {
												$('#sf1').slideUp();
												$('#sf2').slideDown();
												//    accordion.activate (1);
												current = 1;
											}
										});
										$(".open0").click(function() {
											if (v.form()) {
												$('#sf2').slideUp();
												$('#sf1').slideDown();
												//    accordion.activate (0);
												current = 0;
											}
										});
										// IF NOT SAFARI
									} else {
										// accordion functions
										var accordion = $("#stepForm").accordion({
											autoheight : true
										});
										var current = 0;
										var v = $("#cmaForm")
												.validate(
														{
															subformRequired : function(
																	element) {
																return !(current == 0
																		&& element
																				.parents("#sf1").length
																		|| current == 1
																		&& element
																				.parents("#sf2").length || current == 2
																		&& element
																				.parents("#sf3").length);
															},
															errorClass : "warning",
															errorElement : "label",
															onkeyup : false,
															onblur : false
														});
										// back buttons do not need to run validation
										$("#sf2 .prevbutton").click(function() {
											accordion.activate(0);
											current = 0;
										});
										$("#sf3 .prevbutton").click(function() {
											accordion.activate(1);
											current = 1;
										});
										// these buttons all run the validation, overridden by specific targets above
										$(".open2").click(function() {
											if (v.form()) {
												accordion.activate(2);
												current = 2;
											}
										});
										$(".open1").click(function() {
											if (v.form()) {
												accordion.activate(1);
												current = 1;
											}
										});
										$(".open0").click(function() {
											if (v.form()) {
												accordion.activate(0);
												current = 0;
											}
										});
			
										//end safari IF
									}
			
									// masked inputs
									$(function($) {
										$("#recordClientPhone").mask("(999) 999-9999");
										$("#recordClientPhoneAlt").mask("(999) 999-9999");
										$("#recordClientZip").mask("99999");
										$("#recordPropertyZip").mask("99999");
										$("#recordPurchaseZip").mask("99999");
									});
								});
			</script>
			
			
			
			<!-- css for this page -->
			<style type="text/css">
			/* ------ one ------------*/
			body .mainText{
			font-family:Arial, Helvetica, sans-serif;
			font-size:12px;
			}
			#demoText h1, .mainText h1{
			font-size:130%;
			color:#0099FF;
			text-decoration:none;
			font-family:Arial, Helvetica, sans-serif;
			margin:5px 4px 5px 24px;
			background:none;
			padding:0;
			border:none;
			text-transform:capitalize;
			}
			.mainText h2{
			font-size:110%;
			color:#000033;
			font-family:Arial, Helvetica, sans-serif;
			text-decoration:none;
			background:none;
			margin:4px 32px 6px 22px;
			text-transform:capitalize;
			}
			.mainText h3{
			font-size:120%;
			font-weight:900;
			margin:14px 0 0 0;
			text-align:center;
			color:#000033;
			}
			.mainText table{
			width:95%;
			border:1px solid #0099FF;
			border-collapse:collapse;
			margin:18px 7px;
			}
			.mainText table td{
			background-color:#99CCFF;
			color:#000033;
			padding:4px;
			}
			.mainText table th{
			background-color:#000033;
			color:#99CCFF;
			padding:4px;
			}
			.mainText .linkPar a{
			color:#000033;
			text-decoration:underline;
			}
			.mainText .linkPar a:hover{
			color:#660033;
			text-decoration:none;
			font-weight:900;
			}
			.pusher{
			cursor:pointer;
			padding:3px 10px 3px 22px;
			font-weight:900;
			font-size:14px;
			}
			/* ------------- form specific styles are here  -------------- */
			
			fieldset {
			margin:0;
			border:1px solid #C3DE00;
			padding: 10px;
			/*border:none;
			padding:0;*/
			color:#7563A5;
			}
			legend {
			background-color:#FFFFFF;
			text-align: center;
			color: #097981;
			padding:0 12px;
			}
			label {
			text-align: right;
			width: 298px;
			border-right: 1px dotted #099;
			padding-right: 5px;
			margin:0 0 8px 0;
			float: left;
			clear: left;
			display: block;
			color: #7563A5;
			}
			label.checkbox, label.textarea{
			border:none;
			}
			label.lgfield{
			border:none;
			text-align:center;
			clear:both;
			float:none;
			width:100%;
			}
			fieldset input, fieldset select, fieldset textarea {
			margin-left: 10px;
			margin-bottom: 8px;
			}
			select.longfield{
			margin:0 0 0 115px;
			}
			input[type="radio"],input[type="checkbox"] {
			margin: 2px 0 0 4px;
			}
			textarea {
			width:250px;
			float:left;
			}
			
			/*Get Help Form Styles*/
			p.formDisclaimer{
			text-align:center;
			margin:32px 24px 12px 0;
			font-style:italic;
			}
			div.buttonWrapper{
			margin:28px 0 14px 0;
			clear:both;
			text-align:center;
			}
			.formspacer{
			height:1em;
			clear:both;
			}
			.hideField{
			display:none;
			}
			.pushOpen{
			height:18em;
			}
			/* ----- error message for field validation ----- */
			#stepForm label.warning{
			text-align: left;
			width:auto;
			padding:0;
			margin:0 0 0 10px;
			float: none;
			clear: none;
			display: inline;
			color:#CC3366;
			font-size:10px; 
			border:none;
			border-top:1px dotted #CC3366;
			}
			div.requiredNotice{
			width:140px;
			float:right;
			margin:0 24px 0 0;
			padding:0;
			}
			h3.stepHeader{
			text-align:left;
			font-size:16px;
			font-weight:bold;
			margin:0 0 24px 24px;
			color:#676cac;
			}
			ul#stepForm, ul#stepForm li{
			margin:0;
			padding:0;
			}
			ul#stepForm li{
			list-style:none;
			}
			
			
			/* Form Buttons  */
			input.submitbutton, .nextbutton, .prevbutton{
			width:100px;
			height:40px;
			background-color:#663399;
			padding:4px;
			border:1px solid #339933;
			cursor:pointer;
			text-align:center;
			color:#FFFFFF;
			margin:7px;
			}
			input.submitbutton{
			background-color:#006699;
			}
			</style> 
	</head>
<body>	
	<a href="<c:url value="/index.htm"/>">Anasayfa</a>	<div id="wrap">
	<!-- content-wrap starts here -->
	<div id="content-wrap">
	<div id="main">
	<!-- Acordion form js and css end -->
	<c:url value="/login/sendForgottenPassword.htm" var="sendPassword"></c:url>
	<form:form  name="cmaForm" id="cmaForm" action="${sendPassword}" method="POST"   enctype="multipart/form-data" >

		<ul id="stepForm" class="ui-accordion-container">
			<li  id="sf1">
			<div class="ui-accordion-left"></div>
			<a href='#' class="ui-accordion-link">
				<div class="ui-accordion-right"></div>
			</a>
			<div>


				<fieldset>
				<legend> Sifremi Unuttum</legend> 
				<div class="formspacer" > </div>

				<h3>Sifrenizin mail adresinize gonderilmesi icin asagidaki alana mail adresinizi giriniz.</h3>
				<br />

				<label for="email" class="inputclass {required:true, email:true}">Email :</label>
				<input  type ="email" name="email" id="email" class="inputclass {required:true}" title="Email" maxlength="50"  onblur="recordClientCity.value = this.value" />
				<br />					

				<div class="buttonWrapper">

				<input name="submit" type="submit" id="submit" value="Submit" class="submitbutton" alt="Submit" title="Submit">
				
				</div>
		</fieldset> 
		</div>
		</li>
			<!-- end of stage 1 -->
</li>
			
						
	</form:form>
</body>
</html>
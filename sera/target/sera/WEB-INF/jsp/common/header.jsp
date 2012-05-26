<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<c:url value="/login/membershipForm.htm" var="memberForm"/>

<li><a href="${logoutUrl}"><fmt:message key="logout" /></a></li>
<li><a href="${memberForm}"><fmt:message key="membership" /></a></li>
<li><a href="${memberForm}"><fmt:message key="addArticle" /></a></li>
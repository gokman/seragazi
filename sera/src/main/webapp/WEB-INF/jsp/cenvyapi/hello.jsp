
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<style type="text/css">
body {
	background-color: #65853A;
}
</style>
<body>
<h2>hello test  </h2>
<br>
${aydi}
<br>
@Transactional(propagation = Propagation.REQUIRED, readOnly = false) 
	public void updateCustomer(Customer cust) {
		customerDao.updateCustomer(cust);
		
	}
</body>
</html>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Registered Users Details</title>
</head>
<body>
<h2>Registered Users</h2>
<table width="50%">
    <tr>
   		<th>SNO.</th>
        <th>FirstName</th>
        <th>Lastname</th>
        <th>Email</th>
        <th>Phone</th>
    </tr>
    <c:forEach items="${userdetails}" var="userdetail" varStatus="status">
        <tr>
        	<td align="center">${status.count}</td>
            <td>${userdetail.firstname}</td>
            <td>${userdetail.lastname}</td>
            <td>${userdetail.emailid}</td>
            <td>${userdetail.phonenumber}</td>
        </tr>
    </c:forEach>
</table> 
<br/>
<input type="button" value="Back" onclick="javascript:history.back()"/>
</body>
</html>
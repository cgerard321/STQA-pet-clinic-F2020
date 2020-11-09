<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<petclinic:layout pageName="appointment">
<head>
    <title>Title</title>
</head>
<body>
<h1>Book an appointment</h1>

<br>
<h2>Available Dates</h2>
<table class="table table-striped table-bordered table-hover">
    <thead class="thead-dark">
    <tr>
        <th>Options</th>
        <th>Day</th>
        <th>Veterinarian</th>
        <th>Specialty</th>
    </tr>
    </thead>
        <%-- Retrieving veterinarian's table data
        acts as a holder for the moment--%>
    <c:set var="flag" value="0" scope="page"/>
    <c:forEach items="${vetAppoint.vetList}" var="vet">
        <c:set var="flag" value="${flag + 1}" scope="page"/>
        <tr>
            <td>
            <c:out value="${flag}"/></td>
            <td>Not set yet</td>
            <td><c:out value="${vet.firstName} ${vet.lastName}"/></td>
            <td><c:forEach var="specialty" items="${vet.specialties}">
                <c:out value="${specialty.name} "/>
            </c:forEach>
                <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
            </td>
        </tr>
    </c:forEach>

</table>

<form>
    <p>Choose your appointment: </p>
    <select name="appointNumber">
        <c:forEach var="count" begin="1" end="${flag}">
        <option value="<c:out value="${count}"/>"><c:out value="${count}"/></option>
        </c:forEach>
    </select>
    <br><br>
    <input type="button" value="Set Appointment"/>
</form>

</body>
</petclinic:layout>

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">

    <h2 id="vetInformation">Veterinarian Information</h2>

    <table class="table table-striped" aria-describedby="vetInformation">
        <tr>
            <th id="name">Name</th>
            <td headers="name"><strong><c:out value="${vet.firstName} ${vet.lastName}"/></strong></td>
        </tr>
        <tr>
            <th id="specialty">Specialties</th>
            <td headers="specialty">
                <c:forEach var="specialty" items="${vet.specialties}">
                    <c:out value="${specialty.name} "/>
                </c:forEach>
                <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
            </td>
        </tr>
    </table>

    <br/>
    <br/>
    <br/>
    <h2 id="Reminders">Reminders</h2>

    <table class="table table-striped" aria-describedby="reminders">
        <c:forEach var="vet" items="${vetrs.reminders}">
            <tr>
                <td headers="eventDate"><petclinic:localDate date="${vet.event_date}" pattern="yyyy-MM-dd"/></td>
                <td headers="eventDescription"><c:out value="${vet.event_description}"/></td>
            </tr>
        </c:forEach>
    </table>
</petclinic:layout>


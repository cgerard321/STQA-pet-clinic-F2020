<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="pets">

    <h2 id="ownerInformation">Pet Information</h2>

    <table class="table table-striped" aria-describedby="pets">
        <tr>
            <th id="name">Name</th>
            <td headers="name"><strong><c:out value="${pet.name}"/></strong></td>
        </tr>
        <tr>
            <th id="owner">Owner</th>
            <td headers="owner"><strong><c:out value="${owner.firstName} ${owner.lastName}"/></strong></td>
        </tr>
        <tr>
            <th id="type">Type</th>
            <td headers="type"><strong><c:out value="${pet.type.name}"/></strong></td>
        </tr>
        <tr>
            <th id="birthDate">Birth Date</th>
            <td headers="address">
                <petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/>
            </td>
        </tr>
        <tr>
            <th id="address">Address</th>
            <td headers="address"><c:out value="${owner.address}"/></td>
        </tr>
        <tr>
            <th id="city">City</th>
            <td headers="city"><c:out value="${owner.city}"/></td>
        </tr>
        <tr>
            <th id="telephone">Telephone</th>
            <td headers="telephone"><c:out value="${owner.telephone}"/></td>
        </tr>
        <tr>
            <th id="email">Email</th>
            <td headers="email"><c:out value="${owner.email}"/></td>
        </tr>

        <tr>
            <th id="height">Height(In)</th>
            <td headers="name"><strong><c:out value="${pet.height}"/></strong></td>
        </tr>

        <tr>
            <th id="weight">Weight(lbs)</th>
            <td headers="name"><strong><c:out value="${pet.weight}"/></strong></td>
        </tr>
    </table>


    <h2 id="Visits">Visits</h2>

    <table class="table-condensed" aria-describedby="petsAndVisits">
        <thead>
        <tr>
            <th id="visitDate">Visit Date</th>
            <th id="visitDescription">Description</th>
        </tr>
        </thead>
        <c:forEach var="visit" items="${pet.visits}">
            <tr>
                <td headers="visitDate"><petclinic:localDate date="${visit.date}" pattern="yyyy-MM-dd"/></td>
                <td headers="visitDescription"><c:out value="${visit.description}"/></td>
            </tr>
        </c:forEach>
    </table>


</petclinic:layout>

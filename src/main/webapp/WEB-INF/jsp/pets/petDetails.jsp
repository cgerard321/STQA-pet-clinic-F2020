<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="pets">

    <h2 id="ownerInformation">Pet Information</h2>
    <img src="<c:out value="${pet.imageURL}"/>" width="150" height="150">
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
            <td headers="birthDate">
                <petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/>
            </td>
        </tr>
        <tr>
            <th id="age">Age</th>
            <td headers="age"><strong><c:out value="${petAge}"/></strong>
            </td>
        </tr>
        <tr>
            <th id="rating">Rating</th>
            <td headers="rating"><strong>
                <c:choose>
                    <c:when test="${totalRating != null}">
                        <c:out value="${totalRating}"/>
                    </c:when>
                    <c:otherwise>
                        N/A
                    </c:otherwise>
                </c:choose>
            </strong>
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

    </table

    <form:form method="POST" action="/spring_framework_petclinic_war/pets/${pet.id}/remove">
        <button type="submit" style="margin-top:30px" name="deletePet" value="${pet.id}"
                onclick="return confirm('Are you sure you want to remove ${pet.name} from the system')">
            Delete Pet
        </button>
        <!--remove the new pet -->
    </form:form>


</petclinic:layout>

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="ratings">
    <body>
    <h1>View Pet Ratings</h1>

    <h2>Available Pets</h2>

    <table id="petsTable" class="table table-striped table-bordered table-hover" aria-describedby="pets">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Type</th>
            <th scope="col">Owner</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${petsList}" var="pet">
            <tr>
                <td>
                    <spring:url value="/ratings/findPetRatings/{petId}/petRatings" var="petUrl">
                        <spring:param name="petId" value="${pet.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(petUrl)}"><c:out value="${pet.name}"/></a>
                </td>
                <td>
                    <c:out value="${pet.type.name}"/>
                </td>
                <td>
                    <c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </body>
</petclinic:layout>


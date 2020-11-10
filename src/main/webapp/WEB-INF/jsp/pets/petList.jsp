<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- -->
<petclinic:layout pageName="pets">
    <h2 id="pets">Pets</h2>

    <table id="petsTable" class="table table-striped" aria-describedby="pets">
        <thead>
        <tr>
            <th scope="col" style="width: 150px;">Name</th>
            <th scope="col" style="width: 150px;">Birth Day</th>
            <th scope="col">Owner</th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col" style="width: 150px">Type</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="pet">
            <tr>
                <td>
                    <spring:url value="/owners/{ownerId}/pets/{petId}/edit" var="petUrl">
                        <spring:param name="ownerId" value="${pet.owner.id}"/>
                        <spring:param name="petId" value="${pet.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(petUrl)}"><c:out value="${pet.name}"/></a>
                </td>
                <td>
                    <c:out value="${pet.birthDate}"/>
                </td>
                <td>
                    <c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/>
                </td>
                <td>
                <button type="button" name="addPet">Add Pet</button><!--add the new pet -->
            </td>
                <td>
                    <button type="button" name="deletePet">Delete Pet</button><!--remove the new pet -->
                </td>
                <td>
                    <c:out value="${pet.type.name}"/>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

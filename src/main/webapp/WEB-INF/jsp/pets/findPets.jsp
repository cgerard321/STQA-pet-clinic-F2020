<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!-- -->
<petclinic:layout pageName="pets">
    <h2 id="pets">Pets</h2>

    <c:choose>
        <c:when test="${selections.toArray()[0] != null}">
            <table id="petsTable" class="table table-striped" aria-describedby="pets">
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Birth Day</th>
                    <th scope="col">Owner</th>
                    <th scope="col">Type</th>
                    <th></th>
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
                        <td style="text-transform:capitalize;">
                            <c:out value="${pet.type.name}"/>
                        </td>
                        <form:form method="POST" action="/spring_framework_petclinic_war/pets/${pet.id}/remove">
                            <td>
                                <button type="submit" name="deletePet" value="${pet.id}"
                                        onclick="return confirm('Are you sure you want to remove ${pet.name} from the system')">
                                    Delete Pet
                                </button>
                                <!--remove the new pet -->
                            </td>
                        </form:form>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="container" style="margin: 2em 0em;font-weight: bold;">There is no pet registered in the Pet Clinic
                right now.</p>
        </c:otherwise>
    </c:choose>

    <div class="col-sm-3">
        <a href="${fn:escapeXml(petUrl)}">
            <button type="button" name="addPet">Add Pet</button>
        </a>
        <input type="text" id="addPet" name="addPet"><!--add the new pet -->
    </div>

</petclinic:layout>



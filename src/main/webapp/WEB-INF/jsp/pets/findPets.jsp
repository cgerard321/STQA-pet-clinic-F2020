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

    <%--    Check if there's pets in the petclinic--%>
    <c:choose>
        <%--        There's at least one pet--%>
        <c:when test="${selections.toArray()[0] != null}">
            <table id="petsTable" class="table table-striped table-hover" aria-describedby="pets">
                <thead>
                <tr>
                    <th scope="col" style="width: 30px;"></th>
                    <th scope="col">Name</th>
                    <th scope="col">Birth Day</th>
                    <th scope="col">Owner</th>
                    <th scope="col">Type</th>
                    <th scope="col" style="width: 30px;"></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${selections}" var="pet">
                    <tr>
                        <td>
                            <!-- Edit Pet -->
                            <spring:url value="/owners/{ownerId}/pets/{petId}/edit" var="petUrl">
                                <spring:param name="ownerId" value="${pet.owner.id}"/>
                                <spring:param name="petId" value="${pet.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(petUrl)}">
                                <button type="button" class="btn btn-link btnLink" style="padding: 5px">
                                        <%--                                <span--%>
                                        <%--                                        class="glyphicon glyphicon-pencil"--%>
                                        <%--                                        aria-hidden="true"></span>--%>
                                    <span aria-hidden="true"><i class="fi-pencil"></i></span>
                                </button>
                            </a>
                        </td>
                        <td>

                            <!--view details about pet -->
                            <spring:url value="/owners/{ownerId}/pets/{petId}/view" var="detailUrl">
                                <spring:param name="ownerId" value="${pet.owner.id}"/>
                                <spring:param name="petId" value="${pet.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(detailUrl)}" style="font-weight: bold;"><c:out
                                    value="${pet.name}"/></a>
                        </td>
                        <td>
                            <c:out value="${pet.birthDate}"/>
                        </td>
                        <td>
                            <spring:url value="/owners/{ownerId}.html" var="ownerUrl">
                                <spring:param name="ownerId" value="${pet.owner.id}"/>
                            </spring:url>
                            <a data-toggle="tooltip" data-html="true" data-placement="right"
                               data-original-title="<img style='width:60%;' src='${pet.owner.profile_picture}' />"
                               title="" class="ownerHover" href="${fn:escapeXml(ownerUrl)}">
                                <c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/>
                            </a>
                        </td>
                        <td style="text-transform:capitalize;">
                            <c:out value="${pet.type.name}"/>
                        </td>
                        <td>
                            <spring:url value="/pets/${pet.id}/remove" var="petRemoveUrl"/>
                            <form:form method="POST" action="${fn:escapeXml(petRemoveUrl)}"
                                       cssStyle="display: inline-block">
                                <button type="submit" name="deletePet" class="btn btn-link btnLink" value="${pet.id}"
                                        onclick="return confirm('Are you sure you want to remove ${pet.name} from the system')"
                                        style="padding: 5px">
                                    <span aria-hidden="true"><i class="fi-trash"></i></span>
                                </button>
                                <!--remove the new pet -->

                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <%--        No pet found--%>
        <c:otherwise>
            <p class="container" style="margin: 2em 0em;font-weight: bold;">There is no pet registered in the Pet Clinic
                right now.</p>
        </c:otherwise>
    </c:choose>

    <input class="col-sm-3">
    <%--    <a href="${fn:escapeXml(petUrl)}">--%>
    <button type="button" name="addPet">Add Pet</button>
    <%--    </a>--%>
    <input type="text" id="addPet" name="addPet"><!--add the new pet -->

</petclinic:layout>


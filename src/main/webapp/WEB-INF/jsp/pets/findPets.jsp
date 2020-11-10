<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!-- -->
<petclinic:layout pageName="pets">
    <h2 id="pets">Pets</h2>

    <table id="petsTable" class="table table-striped" aria-describedby="pets">
        <thead>
        <tr>
            <th scope="col" >Name</th>
            <th scope="col" >Birth Day</th>
            <th scope="col" >Owner</th>
            <th scope="col" >Type</th>
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
                <td >
                    <c:out value="${pet.birthDate}"/>
                </td>
                <td>
                    <c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/>
                </td>

                <td style = "text-transform:capitalize;">
                    <c:out value="${pet.type.name}"/>
                </td>

                <td>
                    <button type="button" name="deletePet">Delete Pet</button><!--remove the new pet -->
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


        <div class="col-sm-3">
            <a href ="${fn:escapeXml(petUrl)}"> <button type="button" name="addPet" >Add Pet</button></a>
            <input type ="text" id="addPet" name="addPet"><!--add the new pet -->
</div>

</petclinic:layout>



<petclinic:layout pageName="pets">
    <h2>Pets</h2>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" alt="A cat and a dog" src="${petsImage}"/>
        </div>
    </div>
</petclinic:layout>



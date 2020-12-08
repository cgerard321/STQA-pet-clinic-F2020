<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<spring:url value="/vets/available" var="vetsAvailableUrl" />
<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            async function updateAvailabilities(input) {
                const availableVets = $("#availableVets");
                availableVets.empty();

                const date = new Date(input.value);
                if (isNaN(date)) {
                    return;
                }

                const result = await fetch('${vetsAvailableUrl}?dayId=' + (date.getDay() + 1));
                if (result.ok) {
                    const vets = await result.json();
                    if (vets.length === 0) {
                        $("<li>No vet is available that day :(</li>").appendTo(availableVets)
                    } else {
                        for (const vet of vets) {
                            $("<li></li>")
                                    .text(vet.firstName + ' ' + vet.lastName)
                                    .appendTo(availableVets);
                        }
                    }
                }
            }

            $(function () {
                const date = $("#date");
                    date
                        .datepicker({dateFormat: 'yy/mm/dd'})
                        .on('change', event => {
                            updateAvailabilities(event.target);
                        });

                updateAvailabilities(date[0]);
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${visit['new']}">New </c:if>Visit</h2>

        <span id="pet"><strong>Pet</strong></span>
        <table class="table table-striped" aria-describedby="pet">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Birth Date</th>
                <th scope="col">Type</th>
                <th scope="col">Owner</th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${visit.pet.name}"/></td>
                <td><petclinic:localDate date="${visit.pet.birthDate}" pattern="yyyy/MM/dd"/></td>
                <td><c:out value="${visit.pet.type.name}"/></td>
                <td><c:out value="${visit.pet.owner.firstName} ${visit.pet.owner.lastName}"/></td>
            </tr>
        </table>

        <form:form modelAttribute="visit" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Date" name="date"/>
                <petclinic:inputField label="Description" name="description"/>
            </div>

            The following vets are available that day:
            <ul id="availableVets"></ul>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${visit.pet.id}"/>
                    <button class="btn btn-default" type="submit" onclick="return confirm('Are you sure you would like to add this visit?')">Add Visit</button>
                </div>
            </div>
        </form:form>

        <br/>
        <strong id="previousVisits">Previous Visits</strong>
        <table class="table table-striped" aria-describedby="previousVisits">
            <tr>
                <th scope="col">Date</th>
                <th scope="col">Description</th>
            </tr>
            <c:forEach var="visit" items="${visit.pet.visits}">
                <c:if test="${!visit['new']}">
                    <tr>
                        <td><petclinic:localDate date="${visit.date}" pattern="yyyy/MM/dd"/></td>
                        <td><c:out value="${visit.description}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </jsp:body>

</petclinic:layout>

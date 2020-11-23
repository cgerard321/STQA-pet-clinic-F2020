<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="ratings">
    <h2>
    New Rating
    </h2>
    <form:form modelAttribute="rating" class="form-horizontal" >
        <div class="form-group has-feedback">
        <petclinic:inputField label="Username" name="username" />
            <div class="form-group ">
                <label class="col-sm-2 control-label">Rating</label>
                <div class="col-sm-10">
                    <input type="number" id="rating" name="rating" max="10" min="1" maxlength="2" class="form-control" required>
                </div>
            </div>


            <div class="control-group">
                <petclinic:selectField name="pet" label="Pet " names="${pets}" size="5"/>
<%--    <label>Veterinarian ID</label>--%>
<%--    <select name="petId">--%>
<%--        <c:forEach items="${pets}" var="pet">--%>

<%--            <option><c:out value="${pet.name}"/></option>--%>

<%--        </c:forEach>--%>
<%--    </select>--%>
            </div>
<%--            <label class="col-sm-2 control-label">Username</label>--%>

<%--            <c:forEach begin="0" end="9" step="1" varStatus="rating">--%>
<%--                        <c:out value="${rating.count}" />--%>
<%--                </c:forEach>--%>
<%--                <petclinic:selectField name="rating" label="Rating " names="${ratings}" size="5"/>--%>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="petId" value="${rating.pet.id}"/>
                <button class="btn btn-default" type="submit">Add Rating</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>

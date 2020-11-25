<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">

    <h2>Find Owners</h2>

    <spring:url value="/owners.html" var="formUrl"/>
    <form:form modelAttribute="owner" action="${fn:escapeXml(formUrl)}" method="get" class="form-horizontal"
               id="search-owner-form">
        <div class="form-group">
            <div class="control-group" id="lastName">
                <label class="col-sm-2 control-label">Last name</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="lastName" size="30" maxlength="50"/>
                    <span class="help-inline"><form:errors path="*"/></span>
        <%--<form:select path="*" id="dropdownOptions"> </form:select>--%>
                        <%--select added by lucas-cimino--%>
                    <select id="dropdownOptions" name="dropdownOptions">
                        <option value="OptionId" label="Id"></option>
                        <option value="OptionFirstName" label="First Name"></option>
                        <option value="OptionLastName" label="Last Name"></option>
                        <option value="OptionAddress" label="Address"></option>
                        <option value="OptionCity" label="City"></option>
                        <option value="OptionTelephone" label="Telephone"></option>
                        <option value="OptionEmail" label="Email"></option>
                    </select>
                </div>

            </div>

        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Find Owner</button>
            </div>
        </div>

    </form:form>

    <br/>
    <a class="btn btn-default" href='<spring:url value="/owners/new" htmlEscape="true"/>'>Add Owner</a>
</petclinic:layout>

<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%><%@
taglib
	uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%><%@
taglib
	uri="http://liferay.com/tld/theme" prefix="liferay-theme"%><%@
taglib
	uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>

<portlet:actionURL name="upload" var="uploadURL" />
<img src="<%=request.getContextPath() %>/helppic.jpg" alt="image here">
<aui:form action="<%=uploadURL%>" method="POST"
	enctype="multipart/form-data">
	<aui:input name="x" label="Move right" type="number" helpMessage="If you increase the X value, your field moves to the right" helpTextCssClass="helpclass"/>
	<aui:input name="y" label="Move up" type="number" helpMessage="If you increase the Y value, the field moves upwards.
	"/>
	<aui:input name="width" label="width of the field" type="number" placeholder="120"/>
	<aui:input name="height" label="height of the field" type="number" placeholder="30"/>
	<aui:input name="file" type="file" />
	<aui:button type="submit" value="submit" />
</aui:form>
<aui:button
	onClick="http://localhost:8080/documents/37729/44503/form.pdf" value="download"/>

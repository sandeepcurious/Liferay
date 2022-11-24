<%@ include file="../init.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%><%@
taglib
	uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%><%@
taglib
	uri="http://liferay.com/tld/theme" prefix="liferay-theme"%><%@
taglib
	uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>

<%@ taglib uri="http://liferay.com/tld/frontend"
	prefix="liferay-frontend"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security"%>
	
<%@ page import="java.util.List"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@ page import="com.liferay.petra.string.StringPool"%>
<%@ page import="com.liferay.portal.kernel.model.PersistedModel"%>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry"%>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ page import="com.curious.guestbook.model.Guestbook"%>
<%@ page import="com.curious.guestbook.service.EntryLocalServiceUtil"%>
<%@ page
	import="com.curious.guestbook.service.GuestbookLocalServiceUtil"%>
<%@ page import="com.curious.guestbook.model.Entry"%>

<%
	long guestbookId= ParamUtil.getLong(request, "guestbookId");
	Guestbook guestbook = null;
	if(guestbookId>0){
		guestbook = GuestbookLocalServiceUtil.getGuestbook(guestbookId);
	}
	System.out.println(guestbookId);
%>

<portlet:renderURL var="viewUrl">
	<portlet:param name="mvcPath" value="/guestbookadminportlet/view.jsp"/>
</portlet:renderURL>

<portlet:actionURL
	name='<%=guestbook == null ? "addGuestbook" : "updateGuestbook"%>'
	var="editGuestbookUrl"	/>

<aui:form action="<%=editGuestbookUrl%>" name="fm">
	<aui:model-context bean="<%=guestbook%>" model="<%=Guestbook.class%>" />
	<aui:input name="guestbookId" type="hidden"
		value='<%=guestbook == null ? "" : guestbook.getGuestbookId()%>' />
	<aui:fieldset>
		<aui:input name="name" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
		<aui:button type="cancel" onClick="<%=viewUrl%>" />
	</aui:button-row>
</aui:form>

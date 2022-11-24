<%@ include file="../init.jsp"%>

<%
	long entryId = ParamUtil.getLong(renderRequest, "entryId");

	Entry entry = null;
	if (entryId > 0) {
		entry = EntryLocalServiceUtil.getEntry(entryId);
	}

	long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
%>

<portlet:renderURL var="viewUrl">
	<portlet:param name="mvcPath" value="/guestbookwebportlet/view.jsp" />
</portlet:renderURL>

<portlet:actionURL name="addEntry" var="addEntryURL" />

<aui:form action="<%=addEntryURL%>" name="<portlet:namespace/>fm">
	<aui:model-context bean="<%=entry%>" model="<%=Entry.class%>"></aui:model-context>

	<aui:fieldset>
		<aui:input name="name"></aui:input>
		<aui:input name="email"></aui:input>
		<aui:input name="message"></aui:input>
		<aui:input name="entryId" type="hidden"></aui:input>
		<aui:input name="guestbookId" type="hidden"
			value='<%=entry == null ? guestbookId : entry.getGuestbookId()%>'></aui:input>
	</aui:fieldset>
	<aui:button-row>
		<aui:button type="submit"></aui:button>
		<aui:button type="cancel" onClick="<%=viewUrl.toString()%>"></aui:button>
	</aui:button-row>
</aui:form>
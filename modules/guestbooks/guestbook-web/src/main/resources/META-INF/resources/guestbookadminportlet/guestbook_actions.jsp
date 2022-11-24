<%@ include file="../init.jsp"%>

<%
	String mvcPath=ParamUtil.getString(request, "mvcPath");
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	Guestbook guestbook= (Guestbook)row.getObject();
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="editUrl">
		<portlet:param name="guestbookId" value="<%=String.valueOf(guestbook.getGuestbookId()) %>"/>
		<portlet:param name="mvcPath" value="/guestbookadminportlet/edit_guestbook.jsp"/>
	</portlet:renderURL>
	
	<liferay-ui:icon image="edit" message="Edit" url="<%=editUrl.toString() %>"/>
	
	<portlet:actionURL name="deleteGuestbook" var="deleteUrl">
		<portlet:param name="guestbookId" value="<%=String.valueOf(guestbook.getGuestbookId()) %>"/>
	</portlet:actionURL>
	
	<liferay-ui:icon-delete image="delete" url="<%=deleteUrl.toString() %>" />
	
	<c:if test="<%=GuestbookModelPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.PERMISSIONS) %>">
		
		<liferay:security:permissionURL
			modelResource="<%=Guestbook.class.getName() %>"
			modelResourceDescription=<%=guestbook.getName() %>
			resourcePrimKey=<%=String.valueOf(guestbook.getGuestbookId()) %>
			var="permissionsURL"/>
		
	</c:if>
</liferay-ui:icon-menu>
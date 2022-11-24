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
<%@ taglib uri="http://liferay.com/tld/security"
	prefix="liferay-security"%>
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

<%@ page import="com.curious.guestbook.web.internal.security.permission.resource.GuestbookModelPermission" %>
<%@ page import="com.curious.guestbook.web.internal.security.permission.resource.GuestbookPermission" %>
<%@ page import="com.curious.guestbook.web.internal.security.permission.resource.GuestbookEntryPermission" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.kernel.security.permission.ActionKeys" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />
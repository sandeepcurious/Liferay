package com.curious.guestbook.web.internal.security.permission.resource;

import com.curious.guestbook.model.Entry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = GuestbookEntryPermission.class)
public class GuestbookEntryPermission {

	public static boolean contains(PermissionChecker permissionChecker, Entry entry, String actionId)
			throws PortalException {
		return _guestbookEntryModelResourcePermission.contains(permissionChecker, entry, actionId);
	}

	public static boolean contains(PermissionChecker permissionChecker, long entryId, String actionId)
			throws PortalException {
		 return _guestbookEntryModelResourcePermission.contains(permissionChecker,
		 entryId, actionId);
	}

	@Reference(target = "(model.class.name=com.curious.guestbook.model.Entry)", unbind = "-")
	protected void setEntryModelPermission(ModelResourcePermission<Entry> modelResourcePermission) {
		_guestbookEntryModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<Entry> _guestbookEntryModelResourcePermission;
}

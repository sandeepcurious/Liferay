package com.curious.guestbook.internal.security.permission.resource.definition;

import com.curious.guestbook.constants.GuestbookConstants;
import com.curious.guestbook.constants.GuestbookPortletKeys;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.StagedPortletPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.PortletResourcePermissionDefinition;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate=true, service = PortletResourcePermissionDefinition.class)
public class GuestbookPortletResourcePermissionDefinition implements PortletResourcePermissionDefinition{

	@Override
	public PortletResourcePermissionLogic[] getPortletResourcePermissionLogics() {
		// TODO Auto-generated method stub
		return new PortletResourcePermissionLogic[] {
				new StagedPortletPermissionLogic(_stagingPermission, GuestbookPortletKeys.GUESTBOOK)
		};
	}

	@Override
	public String getResourceName() {
		// TODO Auto-generated method stub
		return GuestbookConstants.RESOURCE_NAME;
	}
	
	@Reference
	private StagingPermission _stagingPermission;
}

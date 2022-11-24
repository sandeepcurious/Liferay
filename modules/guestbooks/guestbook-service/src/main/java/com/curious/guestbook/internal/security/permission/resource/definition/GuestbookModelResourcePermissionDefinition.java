package com.curious.guestbook.internal.security.permission.resource.definition;

import com.curious.guestbook.constants.GuestbookConstants;
import com.curious.guestbook.constants.GuestbookPortletKeys;
import com.curious.guestbook.model.Guestbook;
import com.curious.guestbook.service.GuestbookLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.WorkflowedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.ModelResourcePermissionDefinition;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;

import java.util.function.Consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ModelResourcePermissionDefinition.class)
public class GuestbookModelResourcePermissionDefinition implements ModelResourcePermissionDefinition<Guestbook> {

	@Override
	public Guestbook getModel(long guestbookId) throws PortalException {
		return _guestbookLocalService.getGuestbook(guestbookId);
	}

	@Override
	public Class<Guestbook> getModelClass() {
		// TODO Auto-generated method stub
		return Guestbook.class;
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		// TODO Auto-generated method stub
		return _portletResourcePermission;
	}

	@Override
	public long getPrimaryKey(Guestbook guestbook) {
		// TODO Auto-generated method stub
		return guestbook.getGuestbookId();
	}

	@Override
	public void registerModelResourcePermissionLogics(ModelResourcePermission<Guestbook> modelResourcePermission,
			Consumer<ModelResourcePermissionLogic<Guestbook>> modelResourcePermissionLogicConsumer) {
		modelResourcePermissionLogicConsumer.accept(new StagedModelPermissionLogic<Guestbook>(_stagingPermission,
				GuestbookPortletKeys.GUESTBOOK, Guestbook::getGuestbookId));
		modelResourcePermissionLogicConsumer.accept(new WorkflowedModelPermissionLogic<Guestbook>(_workflowPermission,
				modelResourcePermission, _groupLocalService, Guestbook::getGuestbookId));
	}

	@Reference(target = "(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private GuestbookLocalService _guestbookLocalService;

	@Reference
	private StagingPermission _stagingPermission;

	@Reference
	private WorkflowPermission _workflowPermission;

	@Reference
	private GroupLocalService _groupLocalService;

}

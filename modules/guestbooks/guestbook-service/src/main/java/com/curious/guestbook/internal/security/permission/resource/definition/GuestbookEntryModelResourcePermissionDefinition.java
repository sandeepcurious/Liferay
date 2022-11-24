package com.curious.guestbook.internal.security.permission.resource.definition;

import com.curious.guestbook.constants.GuestbookConstants;
import com.curious.guestbook.constants.GuestbookPortletKeys;
import com.curious.guestbook.model.Entry;
import com.curious.guestbook.service.EntryLocalService;
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
public class GuestbookEntryModelResourcePermissionDefinition implements ModelResourcePermissionDefinition<Entry> {

	@Override
	public Entry getModel(long entryId) throws PortalException {
		// TODO Auto-generated method stub
		return _entryLocalService.getEntry(entryId);
	}

	@Override
	public Class<Entry> getModelClass() {
		// TODO Auto-generated method stub
		return Entry.class;
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		// TODO Auto-generated method stub
		return _portletResourcePermission;
	}

	@Override
	public long getPrimaryKey(Entry entry) {
		// TODO Auto-generated method stub
		return entry.getEntryId();
	}

	@Override
	public void registerModelResourcePermissionLogics(ModelResourcePermission<Entry> modelResourcePermission,
			Consumer<ModelResourcePermissionLogic<Entry>> modelResourcePermissionLogicConsumer) {
		modelResourcePermissionLogicConsumer.accept(new StagedModelPermissionLogic<Entry>(_stagingPermission,
				GuestbookPortletKeys.GUESTBOOK, Entry::getEntryId));
		modelResourcePermissionLogicConsumer.accept(new WorkflowedModelPermissionLogic<Entry>(_workflowPermission,
				modelResourcePermission, _groupLocalService, Entry::getEntryId));
	}

	@Reference
	private EntryLocalService _entryLocalService;

	@Reference(target = "(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private StagingPermission _stagingPermission;

	@Reference
	private WorkflowPermission _workflowPermission;

	@Reference
	private GroupLocalService _groupLocalService;

}

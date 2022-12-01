package com.curious.guestbook.search;

import com.curious.guestbook.model.Guestbook;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchRegistrarHelper;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

//this class registers Guestbooks with search framework 

@Component(immediate = true)
public class GuestbookSearchRegistrar {

	@Activate
	public void activate(BundleContext bundleContext) {
		
		_serviceRegistration = modelSearchRegistrarHelper.register(Guestbook.class, bundleContext,
				modelSearchDefinition -> {
					modelSearchDefinition.setDefaultSelectedFieldNames(Field.ASSET_TAG_NAMES, Field.COMPANY_ID,
							Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.GROUP_ID, Field.MODIFIED_DATE,
							Field.SCOPE_GROUP_ID, Field.UID);
					modelSearchDefinition.setDefaultSelectedLocalizedFieldNames(Field.DESCRIPTION, Field.TITLE);
					modelSearchDefinition.setModelIndexWriteContributor(modelIndexerWriterContributor);
					modelSearchDefinition.setModelSummaryContributor(modelSummaryContributor);
				});
	}

	@Deactivate
	public void deactivate() {
		_serviceRegistration.unregister();
	}

	@Reference(target = "(indexer.class.name=com.curious.guestbook.model.Guestbook)")
	protected ModelIndexerWriterContributor<Guestbook> modelIndexerWriterContributor;

	@Reference
	protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;

	@Reference(target = "(indexer.class.name=com.curious.guestbook.model.Guestbook)")
	protected ModelSummaryContributor modelSummaryContributor;

	private ServiceRegistration<?> _serviceRegistration;
}

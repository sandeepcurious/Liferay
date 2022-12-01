package com.curious.guestbook.search.spi.model.index.contributor;

import com.curious.guestbook.model.Guestbook;
import com.curious.guestbook.service.GuestbookLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

//This class is invoked when we batch reindex from the portal control panel

@Component(immediate = true, property = "indexer.class.name=com.curious.guestbook.model.Guestbook", service = ModelIndexerWriterContributor.class)
public class GuestbookModelIndexerWriterContributor implements ModelIndexerWriterContributor<Guestbook> {

	@Override
	public void customize(BatchIndexingActionable batchIndexingActionable,
			ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

		batchIndexingActionable.setPerformActionMethod((Guestbook guestbook) -> {
			Document document = modelIndexerWriterDocumentHelper.getDocument(guestbook);
			batchIndexingActionable.addDocuments(document);
		});
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {

		return dynamicQueryBatchIndexingActionableFactory
				.getBatchIndexingActionable(guestbookLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(Guestbook guestbook) {

		return guestbook.getCompanyId();
	}

	@Reference
	protected GuestbookLocalService guestbookLocalService;

	@Reference
	protected DynamicQueryBatchIndexingActionableFactory dynamicQueryBatchIndexingActionableFactory;

}

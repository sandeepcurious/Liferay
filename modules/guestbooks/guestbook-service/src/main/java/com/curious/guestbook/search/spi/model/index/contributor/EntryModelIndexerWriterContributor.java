package com.curious.guestbook.search.spi.model.index.contributor;

import com.curious.guestbook.model.Entry;
import com.curious.guestbook.service.EntryLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

//This class is invoked when we batch reindex from the portal control panel

@Component(immediate = true, property = "indexer.class.name=com.curious.guestbook.model.Entry", service = ModelIndexerWriterContributor.class)
public class EntryModelIndexerWriterContributor implements ModelIndexerWriterContributor<Entry> {
	@Override
	public void customize(BatchIndexingActionable batchIndexingActionable,
			ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

		batchIndexingActionable.setPerformActionMethod((Entry entry) -> {
			Document document = modelIndexerWriterDocumentHelper.getDocument(entry);
			batchIndexingActionable.addDocuments(document);
		});
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {

		return dynamicQueryBatchIndexingActionableFactory
				.getBatchIndexingActionable(entryLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(Entry entry) {

		return entry.getCompanyId();
	}

	@Reference
	protected EntryLocalService entryLocalService;

	@Reference
	protected DynamicQueryBatchIndexingActionableFactory dynamicQueryBatchIndexingActionableFactory;
}

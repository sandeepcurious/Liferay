package com.curious.guestbook.search;

import com.curious.guestbook.model.Entry;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

//The `reindex` method of the interface is called when a Guestbook is updated.
//The entry documents are re-indexed to include the update Guestbook title.
@Component(immediate = true, service = EntryBatchReindexer.class)
public class EntryBatchReindexerImpl implements EntryBatchReindexer {

	@Override
	public void reindex(long guestbookId, long companyId) {
		BatchIndexingActionable batchIndexingActionable = indexerWriter.getBatchIndexingActionable();
		batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {
			Property guestbookIdProperty = PropertyFactoryUtil.forName("guestbookId");
			dynamicQuery.add(guestbookIdProperty.eq(guestbookId));
		});
		batchIndexingActionable.setPerformActionMethod((Entry entry) -> {
			Document document = indexerDocumentBuilder.getDocument(entry);
			batchIndexingActionable.addDocuments(document);
		});
		batchIndexingActionable.performActions();

	}

	@Reference(target = "(indexer.class.name=com.curious.guestbook.model.Entry)")
	protected IndexerDocumentBuilder indexerDocumentBuilder;

	@Reference(target = "(indexer.class.name=com.curious.guestbook.model.Entry)")
	protected IndexerWriter<Entry> indexerWriter;
}

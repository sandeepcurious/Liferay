package com.curious.guestbook.search.spi.model.index.contributor;

import com.curious.guestbook.model.Entry;
import com.curious.guestbook.model.Guestbook;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

//This class controls which fields are indexed.contribute method is called when there is an add/update on guestbook

@Component(immediate = true, property = "indexer.class.name=com.curious.guestbook.model.Entry", service = ModelDocumentContributor.class)
public class EntryModelDocumentContributor implements ModelDocumentContributor<Entry> {
	@Override
	public void contribute(Document document, Entry entry) {

		try {
			document.addText("entryEmail", entry.getEmail());

			String title = HtmlUtil.extractText(entry.getName());
			document.addText(Field.TITLE, title);

			String content = entry.getMessage();
			document.addText(Field.CONTENT, content);

			document.addDate(Field.MODIFIED_DATE, entry.getModifiedDate());

			for (Locale locale : LanguageUtil.getAvailableLocales(entry.getGroupId())) {
				String languageId = LocaleUtil.toLanguageId(locale);
				document.addText(LocalizationUtil.getLocalizedName(Field.CONTENT, languageId), content);
				document.addText(LocalizationUtil.getLocalizedName(Field.TITLE, languageId), title);
			}

		} catch (Exception e) {

			if (_log.isWarnEnabled()) {
				_log.warn("Unable to index guestbook Entry " + entry.getEntryId(), e);
			}
		}

	}

	private static final Log _log = LogFactoryUtil.getLog(EntryModelDocumentContributor.class);

}

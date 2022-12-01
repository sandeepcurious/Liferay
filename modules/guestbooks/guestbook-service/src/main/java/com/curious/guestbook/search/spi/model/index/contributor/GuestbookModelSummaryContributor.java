package com.curious.guestbook.search.spi.model.index.contributor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = "indexer.class.name=com.curious.guestbook.model.Guestbook", service = ModelSummaryContributor.class)
public class GuestbookModelSummaryContributor implements ModelSummaryContributor {

	@Override
	public Summary getSummary(Document document, Locale locale, String snippet) {
		String languageId = LocaleUtil.toLanguageId(locale);
		return _createSummary(document, LocalizationUtil.getLocalizedName(Field.DESCRIPTION, languageId),
				LocalizationUtil.getLocalizedName(Field.TITLE, languageId));
	}

	private Summary _createSummary(Document document, String descriptionField, String titleField) {
		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		String title = document.get(prefix + titleField, titleField);
		String description = document.get(prefix + descriptionField, descriptionField);
		Summary summary = new Summary(title, description);
		summary.setMaxContentLength(200);
		return summary;
	}
}

/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.curious.guestbook.service.impl;

import com.curious.guestbook.exception.GuestbookNameException;
import com.curious.guestbook.model.Entry;
import com.curious.guestbook.model.Guestbook;
import com.curious.guestbook.service.base.GuestbookLocalServiceBaseImpl;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sandeep
 */
@Component(property = "model.class.name=com.curious.guestbook.model.Guestbook", service = AopService.class)
public class GuestbookLocalServiceImpl extends GuestbookLocalServiceBaseImpl {
	@Indexable(type = IndexableType.REINDEX)
	public Guestbook addGuestbook(long userId, String name, ServiceContext serviceContext) throws PortalException {
		long groupId = serviceContext.getScopeGroupId();

		User user = userLocalService.getUserById(userId);
		Date now = new Date();

		validate(name);

		long guestbookId = counterLocalService.increment();

		Guestbook guestbook = guestbookPersistence.create(guestbookId);

		guestbook.setUuid(serviceContext.getUuid());
		guestbook.setUserId(userId);
		guestbook.setGroupId(groupId);
		guestbook.setCompanyId(user.getCompanyId());
		guestbook.setUserName(user.getFullName());
		guestbook.setCreateDate(serviceContext.getCreateDate(now));
		guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
		guestbook.setName(name);
		guestbook.setExpandoBridgeAttributes(serviceContext);
		guestbookPersistence.update(guestbook);
		resourceLocalService.addModelResources(guestbook, serviceContext);

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId, groupId, guestbook.getCreateDate(),
				guestbook.getModifiedDate(), Guestbook.class.getName(), guestbookId, guestbook.getUuid(), 0,
				serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), true, true, null, null, null,
				null, ContentTypes.TEXT_HTML, guestbook.getName(), null, null, null, null, 0, 0, null);

		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
				AssetLinkConstants.TYPE_RELATED);
		return guestbook;
	}
	@Indexable(type = IndexableType.REINDEX)
	public Guestbook updateGuestbook(long userId, long guestbookId, String name, ServiceContext serviceContext)
			throws PortalException {
		Date now = new Date();
		validate(name);
		Guestbook guestbook = getGuestbook(guestbookId);

		User user = userLocalService.getUserById(userId);
		guestbook.setUserId(userId);
		guestbook.setUserName(user.getFullName());
		guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
		guestbook.setExpandoBridgeAttributes(serviceContext);
		guestbook.setName(name);

		guestbookPersistence.update(guestbook);

		resourceLocalService.updateModelResources(guestbook, serviceContext);

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId, guestbook.getGroupId(),
				guestbook.getCreateDate(), guestbook.getModifiedDate(), Guestbook.class.getName(), guestbookId,
				guestbook.getUuid(), 0, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), true,
				true, guestbook.getCreateDate(), null, null, null, ContentTypes.TEXT_HTML, guestbook.getName(), null,
				null, null, null, 0, 0, serviceContext.getAssetPriority());

		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
				AssetLinkConstants.TYPE_RELATED);

		return guestbook;
	}
	@Indexable(type = IndexableType.DELETE)
	public Guestbook deleteGuestbook(long guestbookId, ServiceContext serviceContext) throws PortalException {
		Guestbook guestbook = guestbookLocalService.getGuestbook(guestbookId);

		List<Entry> entries = entryLocalService.getEntries(serviceContext.getScopeGroupId(), guestbookId);
		for (Entry entry : entries) {
			entryLocalService.deleteEntry(entry.getEntryId());
		}

		guestbook = deleteGuestbook(guestbook);

		resourceLocalService.deleteResource(guestbook, ResourceConstants.SCOPE_INDIVIDUAL);
		
		AssetEntry assetEntry= assetEntryLocalService.fetchEntry(Guestbook.class.getName(), guestbookId);
		
		assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
		
		assetEntryLocalService.deleteAssetEntry(assetEntry);
		
		return guestbook;
	}

	public List<Guestbook> getGuestbooks(long groupId) {
		return guestbookPersistence.findByGroupId(groupId);
	}

	public List<Guestbook> getGuestbooks(long groupId, int start, int end) {
		return guestbookPersistence.findByGroupId(groupId, start, end);
	}

	public List<Guestbook> getGuestbooks(long groupId, int start, int end, OrderByComparator<Guestbook> obc) {
		return guestbookPersistence.findByGroupId(groupId, start, end, obc);
	}

	public int getGuestbooksCount(long groupId) {
		return guestbookPersistence.countByGroupId(groupId);
	}

	private void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new GuestbookNameException();
		}
	}

	@Reference
	protected com.curious.guestbook.service.EntryLocalService entryLocalService;
}
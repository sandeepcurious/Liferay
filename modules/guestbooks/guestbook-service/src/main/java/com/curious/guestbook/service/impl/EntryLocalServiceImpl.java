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

import com.curious.guestbook.exception.EntryEmailException;
import com.curious.guestbook.exception.EntryMessageException;
import com.curious.guestbook.exception.EntryNameException;
import com.curious.guestbook.model.Entry;
import com.curious.guestbook.service.base.EntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sandeep
 */
@Component(
	property = "model.class.name=com.curious.guestbook.model.Entry",
	service = AopService.class
)
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {
	public Entry addEntry(long userId, long guestbookId, String name, String email, String message,
			ServiceContext serviceContext) throws PortalException , SystemException{

		long groupId = serviceContext.getScopeGroupId();

		User user = userLocalService.getUser(userId);
		Date now = new Date();
		validate(name, email, message);
		long entryId = counterLocalService.increment();
		Entry entry = entryPersistence.create(entryId);

		entry.setUuid(serviceContext.getUuid());
		entry.setUserId(userId);
		entry.setUserName(user.getFullName());
		entry.setGroupId(groupId);
		entry.setCreateDate(serviceContext.getCreateDate(now));
		entry.setModifiedDate(serviceContext.getModifiedDate(now));
		entry.setGuestbookId(guestbookId);
		entry.setExpandoBridgeAttributes(serviceContext);
		entry.setName(name);
		entry.setEmail(email);

		entry.setMessage(message);
		entryPersistence.update(entry);
		resourceLocalService.addResources(user.getCompanyId(), groupId, userId, Entry.class.getName(), entryId, false, true, true);
		return entry;
	}
	
	public Entry updateEntry(long userId, long guestbookId, long entryId, String name, String email, String message,
			ServiceContext serviceContext) throws PortalException, SystemException {
		Entry entry = entryLocalService.getEntry(entryId);
		validate(name, email, message);
		Date now = new Date();

		User user = userLocalService.getUser(userId);
		entry.setGuestbookId(guestbookId);
		entry.setUserId(userId);
		entry.setUserName(user.getFullName());
		entry.setModifiedDate(serviceContext.getCreateDate(now));
		entry.setName(name);
		entry.setEmail(email);
		entry.setMessage(message);
		entryPersistence.update(entry);
		resourceLocalService.updateModelResources(entry, serviceContext);
		return entry;
	}

	public Entry deleteEntry(long entryId, ServiceContext serviceContext) throws PortalException {
		Entry entry = getEntry(entryId);
		entry = deleteEntry(entry);
		resourceLocalService.deleteResource(entry, ResourceConstants.SCOPE_INDIVIDUAL);
		return entry;
	}
	
	public List<Entry> getEntries(long groupId, long guestbookId){
		return entryPersistence.findByG_G(groupId, guestbookId);
	}
	
	public List<Entry> getEntries(long groupId, long guestbookId, int start, int end){
		return entryPersistence.findByG_G(groupId, guestbookId, start, end);
	}
	
	public List<Entry> getEntries(long groupId, long guestbookId, int start, int end, OrderByComparator<Entry> obc) {

		return entryPersistence.findByG_G(groupId, guestbookId, start, end, obc);
	}

	public int getEntriesCount(long groupId, long guestbookId) {
		return entryPersistence.countByG_G(groupId, guestbookId);
	}

	private void validate(String name, String email, String message) throws PortalException {
		if (Validator.isNull(name)) {
			throw new EntryNameException();
		}

		if (!Validator.isEmailAddress(email)) {
			throw new EntryEmailException();
		}

		if (Validator.isNull(message)) {
			throw new EntryMessageException();
		}
	}
}
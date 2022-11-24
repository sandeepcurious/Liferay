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

package com.curious.guestbook.service;

import com.curious.guestbook.model.Entry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Entry. This utility wraps
 * <code>com.curious.guestbook.service.impl.EntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Sandeep
 * @see EntryLocalService
 * @generated
 */
public class EntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.curious.guestbook.service.impl.EntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entry the entry
	 * @return the entry that was added
	 */
	public static Entry addEntry(Entry entry) {
		return getService().addEntry(entry);
	}

	public static Entry addEntry(
			long userId, long guestbookId, String name, String email,
			String message,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getService().addEntry(
			userId, guestbookId, name, email, message, serviceContext);
	}

	/**
	 * Creates a new entry with the primary key. Does not add the entry to the database.
	 *
	 * @param entryId the primary key for the new entry
	 * @return the new entry
	 */
	public static Entry createEntry(long entryId) {
		return getService().createEntry(entryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entry the entry
	 * @return the entry that was removed
	 */
	public static Entry deleteEntry(Entry entry) {
		return getService().deleteEntry(entry);
	}

	/**
	 * Deletes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entryId the primary key of the entry
	 * @return the entry that was removed
	 * @throws PortalException if a entry with the primary key could not be found
	 */
	public static Entry deleteEntry(long entryId) throws PortalException {
		return getService().deleteEntry(entryId);
	}

	public static Entry deleteEntry(
			long entryId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().deleteEntry(entryId, serviceContext);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.curious.guestbook.model.impl.EntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.curious.guestbook.model.impl.EntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Entry fetchEntry(long entryId) {
		return getService().fetchEntry(entryId);
	}

	/**
	 * Returns the entry matching the UUID and group.
	 *
	 * @param uuid the entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching entry, or <code>null</code> if a matching entry could not be found
	 */
	public static Entry fetchEntryByUuidAndGroupId(String uuid, long groupId) {
		return getService().fetchEntryByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.curious.guestbook.model.impl.EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of entries
	 */
	public static List<Entry> getEntries(int start, int end) {
		return getService().getEntries(start, end);
	}

	public static List<Entry> getEntries(long groupId, long guestbookId) {
		return getService().getEntries(groupId, guestbookId);
	}

	public static List<Entry> getEntries(
		long groupId, long guestbookId, int start, int end) {

		return getService().getEntries(groupId, guestbookId, start, end);
	}

	public static List<Entry> getEntries(
		long groupId, long guestbookId, int start, int end,
		OrderByComparator<Entry> obc) {

		return getService().getEntries(groupId, guestbookId, start, end, obc);
	}

	/**
	 * Returns all the entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the entries
	 * @param companyId the primary key of the company
	 * @return the matching entries, or an empty list if no matches were found
	 */
	public static List<Entry> getEntriesByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getEntriesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching entries, or an empty list if no matches were found
	 */
	public static List<Entry> getEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Entry> orderByComparator) {

		return getService().getEntriesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of entries.
	 *
	 * @return the number of entries
	 */
	public static int getEntriesCount() {
		return getService().getEntriesCount();
	}

	public static int getEntriesCount(long groupId, long guestbookId) {
		return getService().getEntriesCount(groupId, guestbookId);
	}

	/**
	 * Returns the entry with the primary key.
	 *
	 * @param entryId the primary key of the entry
	 * @return the entry
	 * @throws PortalException if a entry with the primary key could not be found
	 */
	public static Entry getEntry(long entryId) throws PortalException {
		return getService().getEntry(entryId);
	}

	/**
	 * Returns the entry matching the UUID and group.
	 *
	 * @param uuid the entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching entry
	 * @throws PortalException if a matching entry could not be found
	 */
	public static Entry getEntryByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return getService().getEntryByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entry the entry
	 * @return the entry that was updated
	 */
	public static Entry updateEntry(Entry entry) {
		return getService().updateEntry(entry);
	}

	public static Entry updateEntry(
			long userId, long guestbookId, long entryId, String name,
			String email, String message,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getService().updateEntry(
			userId, guestbookId, entryId, name, email, message, serviceContext);
	}

	public static EntryLocalService getService() {
		return _service;
	}

	private static volatile EntryLocalService _service;

}
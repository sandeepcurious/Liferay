package com.curious.guestbook.web.asset.model;

import com.curious.guestbook.constants.GuestbookConstants;
import com.curious.guestbook.constants.GuestbookPortletKeys;
import com.curious.guestbook.model.Guestbook;
import com.curious.guestbook.service.GuestbookLocalService;
import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = "javax.portlet.name="
		+ GuestbookPortletKeys.GUESTBOOK, service = AssetRendererFactory.class)
public class GuestbookAssetRendererFactory extends BaseAssetRendererFactory<Guestbook> {

	public static final String CLASS_NAME = Guestbook.class.getName();

	public static final String TYPE = "guestbook";

	private static final boolean _LINKABLE = true;

	public GuestbookAssetRendererFactory() {
		setClassName(CLASS_NAME);
		setLinkable(_LINKABLE);
		setPortletId(GuestbookPortletKeys.GUESTBOOK);
		setSearchable(true);
		setSelectable(true);
	}

	@Override
	public AssetRenderer<Guestbook> getAssetRenderer(long classPK, int type) throws PortalException {
		Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);
		GuestbookAssetRenderer guestbookAssetRenderer = new GuestbookAssetRenderer(guestbook);
		guestbookAssetRenderer.setAssetDisplayPageFriendlyURLProvider(_assetDisplayPageFriendlyURLProvider);
		guestbookAssetRenderer.setAssetRendererType(type);
		guestbookAssetRenderer.setServletContext(_servletContext);

		return guestbookAssetRenderer;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	public PortletURL getURLAdd(LiferayPortletRequest request, LiferayPortletResponse response, long classTypeId) {
		PortletURL portletURL = _portal.getControlPanelPortletURL(request, getGroup(request),
				GuestbookPortletKeys.GUESTBOOK, 0, 0, PortletRequest.RENDER_PHASE);
		portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_guestbook");
		portletURL.setParameter("showback", Boolean.FALSE.toString());
		return portletURL;
	}

	@Override
	public boolean isLinkable() {
		return _LINKABLE;
	}

	@Override
	public String getIconCssClass() {
		return "bookmarks";
	}

//	@Reference(target = "(osgi.web.symbolicname=com.curious.guestbook.web)")
//	public void setServletContext(ServletContext servletContext) {
//		_servletContext = servletContext;
//	}

	@Reference(unbind = "-")
	protected void setGuestbookLocalService(GuestbookLocalService guestbookLocalService) {
		_guestbookLocalService = guestbookLocalService;
	}

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker, long classPK, String actionId) throws Exception {
		// TODO Auto-generated method stub
		return _guestbookModelResourcePermission.contains(permissionChecker, classPK, actionId);
	}

	@Override
	public boolean hasAddPermission(PermissionChecker permissionChecker, long groupId, long classTypeId)
			throws Exception {

		return _guestbookModelResourcePermission.contains(permissionChecker, groupId, ActionKeys.ADD_ENTRY);
	}

	@Reference
	private AssetDisplayPageFriendlyURLProvider _assetDisplayPageFriendlyURLProvider;

	@Reference
	private GuestbookLocalService _guestbookLocalService;

	@Reference(target = "(model.class.name=com.curious.guestbook.model.Guestbook)", unbind = "-")
	private ModelResourcePermission<Guestbook> _guestbookModelResourcePermission;

	@Reference
	private Portal _portal;

	@Reference(target = "(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private PortletURLFactory _portletURLFactory;

	@Reference(target = "(osgi.web.symbolicname=com.curious.guestbook.web)")
	private ServletContext _servletContext;
}

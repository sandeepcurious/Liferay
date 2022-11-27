package com.curious.guestbook.web.asset.model;

import com.curious.guestbook.constants.GuestbookPortletKeys;
import com.curious.guestbook.model.Guestbook;
import com.curious.guestbook.web.internal.security.permission.resource.GuestbookModelPermission;
import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuestbookAssetRenderer extends BaseJSPAssetRenderer<Guestbook> {

	public GuestbookAssetRenderer(Guestbook guestbook) {
		_guestbook = guestbook;
	}

//	public GuestbookAssetRenderer(Guestbook guestbook, ModelResourcePermission<Guestbook> modelResourcePermission) {
//		_guestbook = guestbook;
//		_guestbookModelResourcePermission = modelResourcePermission;
//	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) throws PortalException {
		return GuestbookModelPermission.contains(permissionChecker, _guestbook, ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) throws PortalException {
		return GuestbookModelPermission.contains(permissionChecker, _guestbook, ActionKeys.VIEW);
	}

	@Override
	public Guestbook getAssetObject() {
		// TODO Auto-generated method stub
		return _guestbook;
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		return _guestbook.getGroupId();
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return _guestbook.getUserId();
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return _guestbook.getUserName();
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return _guestbook.getUuid();
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return _guestbook.getClass().getName();
	}

	@Override
	public long getClassPK() {
		// TODO Auto-generated method stub
		return _guestbook.getGuestbookId();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		// TODO Auto-generated method stub
		return "NAME: " + _guestbook.getName();
	}

	@Override
	public String getTitle(Locale locale) {
		return _guestbook.getName();
	}

	@Override
	public int getStatus() {
		return _guestbook.getStatus();
	}

	@Override
	public boolean include(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String template) throws Exception {
		httpServletRequest.setAttribute("GUESTBOOK", _guestbook);
		httpServletRequest.setAttribute("HtmlUtil", HtmlUtil.getHtml());
		httpServletRequest.setAttribute("StringUtil", new StringUtil());
		return super.include(httpServletRequest, httpServletResponse, template);
	}

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) || template.equals(TEMPLATE_FULL_CONTENT)) {
			httpServletRequest.setAttribute("gbs_guestbook", _guestbook);
			return "/asset/guestbook" + template + ".jsp";
		}
		return null;
	}

	@Override
	public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse) throws PortalException {
		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
				getControlPanelPlid(liferayPortletRequest), GuestbookPortletKeys.GUESTBOOK,
				PortletRequest.RENDER_PHASE);
		portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_guestbook");
		portletURL.setParameter("guestbookId", String.valueOf(_guestbook.getGuestbookId()));
		portletURL.setParameter("showback", Boolean.FALSE.toString());
		return portletURL;
	}

	@Override
	public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, String noSuchEntryRedirect) throws Exception {
		if (_assetDisplayPageFriendlyURLProvider != null) {
			ThemeDisplay themeDisplay = (ThemeDisplay) liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String friendlyURL = _assetDisplayPageFriendlyURLProvider.getFriendlyURL(getClassName(), getClassPK(),
					themeDisplay);
			if (Validator.isNotNull(friendlyURL)) {
				return friendlyURL;
			}
		}
		
		try {
			long plid = PortalUtil.getPlidFromPortletId(_guestbook.getGroupId(), GuestbookPortletKeys.GUESTBOOK);
			PortletURL portletURL;
			if (plid == LayoutConstants.DEFAULT_PLID) {
				portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(liferayPortletRequest),
						GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);
			} else {
				portletURL = PortletURLFactoryUtil.create(liferayPortletRequest, GuestbookPortletKeys.GUESTBOOK,
						PortletRequest.RENDER_PHASE);
			}

			portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/view");
			portletURL.setParameter("guestbookId", String.valueOf(_guestbook.getGuestbookId()));

			String currentUrl = PortalUtil.getCurrentURL(liferayPortletRequest);

			portletURL.setParameter("redirect", currentUrl);

			return portletURL.toString();
		} catch (PortalException pe) {
			logger.log(Level.SEVERE, pe.getMessage());
		} catch (SystemException se) {
			logger.log(Level.SEVERE, se.getMessage());
		}
		return noSuchEntryRedirect;
	}

	@Override
	public String getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) throws Exception {
		return super.getURLView(liferayPortletResponse, windowState);
	}
	
	public void setAssetDisplayPageFriendlyURLProvider(AssetDisplayPageFriendlyURLProvider assetDisplayPageFriendlyURLProvider) {
		_assetDisplayPageFriendlyURLProvider= assetDisplayPageFriendlyURLProvider;
	}

	private Guestbook _guestbook;
	private AssetDisplayPageFriendlyURLProvider _assetDisplayPageFriendlyURLProvider;
	private Logger logger = Logger.getLogger(this.getClass().getName());
}

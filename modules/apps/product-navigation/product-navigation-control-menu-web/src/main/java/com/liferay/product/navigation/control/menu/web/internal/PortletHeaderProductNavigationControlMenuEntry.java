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

package com.liferay.product.navigation.control.menu.web.internal;

import com.liferay.application.list.PanelAppRegistry;
import com.liferay.application.list.PanelCategoryRegistry;
import com.liferay.application.list.display.context.logic.PanelCategoryHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.product.navigation.control.menu.BaseJSPProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys;
import com.liferay.product.navigation.control.menu.web.internal.constants.ProductNavigationControlMenuWebKeys;
import com.liferay.product.navigation.global.menu.configuration.GlobalMenuInstanceConfiguration;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 */
@Component(
	immediate = true,
	property = {
		"product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.TOOLS,
		"product.navigation.control.menu.entry.order:Integer=100"
	},
	service = ProductNavigationControlMenuEntry.class
)
public class PortletHeaderProductNavigationControlMenuEntry
	extends BaseJSPProductNavigationControlMenuEntry
	implements ProductNavigationControlMenuEntry {

	@Override
	public String getIconJspPath() {
		return "/entries/portlet_header.jsp";
	}

	@Override
	public boolean includeIcon(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		httpServletRequest.setAttribute(
			ProductNavigationControlMenuWebKeys.PORTLET_TITLE,
			portletDisplay.getTitle());

		return super.includeIcon(httpServletRequest, httpServletResponse);
	}

	@Override
	public boolean isShow(HttpServletRequest httpServletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (_isEnableGlobalMenu(themeDisplay.getCompanyId()) &&
			_isGlobalMenuApp(themeDisplay)) {

			return false;
		}

		Layout layout = themeDisplay.getLayout();

		if (!layout.isTypeControlPanel()) {
			return false;
		}

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		if (portletDisplay == null) {
			return false;
		}

		return super.isShow(httpServletRequest);
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.product.navigation.control.menu.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	private boolean _isEnableGlobalMenu(long companyId) {
		try {
			GlobalMenuInstanceConfiguration globalMenuInstanceConfiguration =
				_configurationProvider.getCompanyConfiguration(
					GlobalMenuInstanceConfiguration.class, companyId);

			if (globalMenuInstanceConfiguration.enableGlobalMenu()) {
				return true;
			}
		}
		catch (ConfigurationException configurationException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to get global menu instance configuration",
					configurationException);
			}
		}

		return false;
	}

	private boolean _isGlobalMenuApp(ThemeDisplay themeDisplay) {
		if (Validator.isNull(themeDisplay.getPpid())) {
			return false;
		}

		PanelCategoryHelper panelCategoryHelper = new PanelCategoryHelper(
			_panelAppRegistry, _panelCategoryRegistry);

		if (!panelCategoryHelper.isGlobalMenuApp(themeDisplay.getPpid())) {
			return false;
		}

		Layout layout = themeDisplay.getLayout();

		if ((layout != null) && !layout.isTypeControlPanel()) {
			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletHeaderProductNavigationControlMenuEntry.class);

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private PanelAppRegistry _panelAppRegistry;

	@Reference
	private PanelCategoryRegistry _panelCategoryRegistry;

}
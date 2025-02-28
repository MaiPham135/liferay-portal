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

package com.liferay.dynamic.data.mapping.internal.model.listener;

import com.liferay.dynamic.data.mapping.internal.petra.executor.DDMFormInstanceReportPortalExecutor;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReport;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceReportLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcos Martins
 */
@Component(immediate = true, service = ModelListener.class)
public class DDMFormInstanceModelListener
	extends BaseModelListener<DDMFormInstance> {

	@Override
	public void onAfterCreate(DDMFormInstance ddmFormInstance)
		throws ModelListenerException {

		try {
			_ddmFormInstanceReportLocalService.addFormInstanceReport(
				ddmFormInstance.getFormInstanceId());
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				StringBundler sb = new StringBundler(4);

				sb.append("Unable to update dynamic data mapping form ");
				sb.append("instance report for dynamic data mapping form ");
				sb.append("instance ");
				sb.append(ddmFormInstance.getFormInstanceId());

				_log.warn(sb.toString(), exception);
			}
		}
	}

	@Override
	public void onBeforeRemove(DDMFormInstance ddmFormInstance)
		throws ModelListenerException {

		try {
			DDMFormInstanceReport ddmFormInstanceReport =
				_ddmFormInstanceReportLocalService.
					getFormInstanceReportByFormInstanceId(
						ddmFormInstance.getFormInstanceId());

			_ddmFormInstanceReportPortalExecutor.execute(
				() ->
					_ddmFormInstanceReportLocalService.
						deleteDDMFormInstanceReport(
							ddmFormInstanceReport.getFormInstanceReportId()));
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				StringBundler sb = new StringBundler(4);

				sb.append("Unable to update dynamic data mapping form ");
				sb.append("instance report for dynamic data mapping form ");
				sb.append("instance ");
				sb.append(ddmFormInstance.getFormInstanceId());

				_log.warn(sb.toString(), exception);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMFormInstanceModelListener.class);

	@Reference
	private DDMFormInstanceReportLocalService
		_ddmFormInstanceReportLocalService;

	@Reference
	private DDMFormInstanceReportPortalExecutor
		_ddmFormInstanceReportPortalExecutor;

}
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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the KaleoTask service. Represents a row in the &quot;KaleoTask&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>KaleoTaskModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoTaskImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskImpl
 * @generated
 */
public class KaleoTaskModelImpl
	extends BaseModelImpl<KaleoTask> implements KaleoTaskModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo task model instance should use the <code>KaleoTask</code> interface instead.
	 */
	public static final String TABLE_NAME = "KaleoTask";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"kaleoTaskId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"kaleoDefinitionId", Types.BIGINT},
		{"kaleoDefinitionVersionId", Types.BIGINT},
		{"kaleoNodeId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoTaskId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoNodeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table KaleoTask (mvccVersion LONG default 0 not null,kaleoTaskId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoDefinitionId LONG,kaleoDefinitionVersionId LONG,kaleoNodeId LONG,name VARCHAR(200) null,description STRING null)";

	public static final String TABLE_SQL_DROP = "drop table KaleoTask";

	public static final String ORDER_BY_JPQL =
		" ORDER BY kaleoTask.kaleoTaskId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY KaleoTask.kaleoTaskId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long KALEODEFINITIONVERSIONID_COLUMN_BITMASK = 2L;

	public static final long KALEONODEID_COLUMN_BITMASK = 4L;

	public static final long KALEOTASKID_COLUMN_BITMASK = 8L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public KaleoTaskModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoTaskId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoTaskId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoTaskId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTask.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTask.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<KaleoTask, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<KaleoTask, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoTask, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((KaleoTask)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<KaleoTask, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<KaleoTask, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(KaleoTask)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<KaleoTask, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<KaleoTask, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, KaleoTask>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			KaleoTask.class.getClassLoader(), KaleoTask.class,
			ModelWrapper.class);

		try {
			Constructor<KaleoTask> constructor =
				(Constructor<KaleoTask>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<KaleoTask, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<KaleoTask, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<KaleoTask, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<KaleoTask, Object>>();
		Map<String, BiConsumer<KaleoTask, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<KaleoTask, ?>>();

		attributeGetterFunctions.put("mvccVersion", KaleoTask::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<KaleoTask, Long>)KaleoTask::setMvccVersion);
		attributeGetterFunctions.put("kaleoTaskId", KaleoTask::getKaleoTaskId);
		attributeSetterBiConsumers.put(
			"kaleoTaskId",
			(BiConsumer<KaleoTask, Long>)KaleoTask::setKaleoTaskId);
		attributeGetterFunctions.put("groupId", KaleoTask::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<KaleoTask, Long>)KaleoTask::setGroupId);
		attributeGetterFunctions.put("companyId", KaleoTask::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<KaleoTask, Long>)KaleoTask::setCompanyId);
		attributeGetterFunctions.put("userId", KaleoTask::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<KaleoTask, Long>)KaleoTask::setUserId);
		attributeGetterFunctions.put("userName", KaleoTask::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<KaleoTask, String>)KaleoTask::setUserName);
		attributeGetterFunctions.put("createDate", KaleoTask::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<KaleoTask, Date>)KaleoTask::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", KaleoTask::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<KaleoTask, Date>)KaleoTask::setModifiedDate);
		attributeGetterFunctions.put(
			"kaleoDefinitionId", KaleoTask::getKaleoDefinitionId);
		attributeSetterBiConsumers.put(
			"kaleoDefinitionId",
			(BiConsumer<KaleoTask, Long>)KaleoTask::setKaleoDefinitionId);
		attributeGetterFunctions.put(
			"kaleoDefinitionVersionId", KaleoTask::getKaleoDefinitionVersionId);
		attributeSetterBiConsumers.put(
			"kaleoDefinitionVersionId",
			(BiConsumer<KaleoTask, Long>)
				KaleoTask::setKaleoDefinitionVersionId);
		attributeGetterFunctions.put("kaleoNodeId", KaleoTask::getKaleoNodeId);
		attributeSetterBiConsumers.put(
			"kaleoNodeId",
			(BiConsumer<KaleoTask, Long>)KaleoTask::setKaleoNodeId);
		attributeGetterFunctions.put("name", KaleoTask::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<KaleoTask, String>)KaleoTask::setName);
		attributeGetterFunctions.put("description", KaleoTask::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<KaleoTask, String>)KaleoTask::setDescription);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getKaleoTaskId() {
		return _kaleoTaskId;
	}

	@Override
	public void setKaleoTaskId(long kaleoTaskId) {
		_columnBitmask = -1L;

		_kaleoTaskId = kaleoTaskId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinitionId = kaleoDefinitionId;
	}

	@Override
	public long getKaleoDefinitionVersionId() {
		return _kaleoDefinitionVersionId;
	}

	@Override
	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId) {
		_columnBitmask |= KALEODEFINITIONVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalKaleoDefinitionVersionId) {
			_setOriginalKaleoDefinitionVersionId = true;

			_originalKaleoDefinitionVersionId = _kaleoDefinitionVersionId;
		}

		_kaleoDefinitionVersionId = kaleoDefinitionVersionId;
	}

	public long getOriginalKaleoDefinitionVersionId() {
		return _originalKaleoDefinitionVersionId;
	}

	@Override
	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	@Override
	public void setKaleoNodeId(long kaleoNodeId) {
		_columnBitmask |= KALEONODEID_COLUMN_BITMASK;

		if (!_setOriginalKaleoNodeId) {
			_setOriginalKaleoNodeId = true;

			_originalKaleoNodeId = _kaleoNodeId;
		}

		_kaleoNodeId = kaleoNodeId;
	}

	public long getOriginalKaleoNodeId() {
		return _originalKaleoNodeId;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), KaleoTask.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoTask toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, KaleoTask>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		KaleoTaskImpl kaleoTaskImpl = new KaleoTaskImpl();

		kaleoTaskImpl.setMvccVersion(getMvccVersion());
		kaleoTaskImpl.setKaleoTaskId(getKaleoTaskId());
		kaleoTaskImpl.setGroupId(getGroupId());
		kaleoTaskImpl.setCompanyId(getCompanyId());
		kaleoTaskImpl.setUserId(getUserId());
		kaleoTaskImpl.setUserName(getUserName());
		kaleoTaskImpl.setCreateDate(getCreateDate());
		kaleoTaskImpl.setModifiedDate(getModifiedDate());
		kaleoTaskImpl.setKaleoDefinitionId(getKaleoDefinitionId());
		kaleoTaskImpl.setKaleoDefinitionVersionId(
			getKaleoDefinitionVersionId());
		kaleoTaskImpl.setKaleoNodeId(getKaleoNodeId());
		kaleoTaskImpl.setName(getName());
		kaleoTaskImpl.setDescription(getDescription());

		kaleoTaskImpl.resetOriginalValues();

		return kaleoTaskImpl;
	}

	@Override
	public int compareTo(KaleoTask kaleoTask) {
		int value = 0;

		if (getKaleoTaskId() < kaleoTask.getKaleoTaskId()) {
			value = -1;
		}
		else if (getKaleoTaskId() > kaleoTask.getKaleoTaskId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof KaleoTask)) {
			return false;
		}

		KaleoTask kaleoTask = (KaleoTask)object;

		long primaryKey = kaleoTask.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		KaleoTaskModelImpl kaleoTaskModelImpl = this;

		kaleoTaskModelImpl._originalCompanyId = kaleoTaskModelImpl._companyId;

		kaleoTaskModelImpl._setOriginalCompanyId = false;

		kaleoTaskModelImpl._setModifiedDate = false;

		kaleoTaskModelImpl._originalKaleoDefinitionVersionId =
			kaleoTaskModelImpl._kaleoDefinitionVersionId;

		kaleoTaskModelImpl._setOriginalKaleoDefinitionVersionId = false;

		kaleoTaskModelImpl._originalKaleoNodeId =
			kaleoTaskModelImpl._kaleoNodeId;

		kaleoTaskModelImpl._setOriginalKaleoNodeId = false;

		kaleoTaskModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<KaleoTask> toCacheModel() {
		KaleoTaskCacheModel kaleoTaskCacheModel = new KaleoTaskCacheModel();

		kaleoTaskCacheModel.mvccVersion = getMvccVersion();

		kaleoTaskCacheModel.kaleoTaskId = getKaleoTaskId();

		kaleoTaskCacheModel.groupId = getGroupId();

		kaleoTaskCacheModel.companyId = getCompanyId();

		kaleoTaskCacheModel.userId = getUserId();

		kaleoTaskCacheModel.userName = getUserName();

		String userName = kaleoTaskCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoTaskCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoTaskCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoTaskCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoTaskCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoTaskCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoTaskCacheModel.kaleoDefinitionId = getKaleoDefinitionId();

		kaleoTaskCacheModel.kaleoDefinitionVersionId =
			getKaleoDefinitionVersionId();

		kaleoTaskCacheModel.kaleoNodeId = getKaleoNodeId();

		kaleoTaskCacheModel.name = getName();

		String name = kaleoTaskCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			kaleoTaskCacheModel.name = null;
		}

		kaleoTaskCacheModel.description = getDescription();

		String description = kaleoTaskCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			kaleoTaskCacheModel.description = null;
		}

		return kaleoTaskCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<KaleoTask, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<KaleoTask, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoTask, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((KaleoTask)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<KaleoTask, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<KaleoTask, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoTask, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((KaleoTask)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, KaleoTask>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _kaleoTaskId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoDefinitionVersionId;
	private long _originalKaleoDefinitionVersionId;
	private boolean _setOriginalKaleoDefinitionVersionId;
	private long _kaleoNodeId;
	private long _originalKaleoNodeId;
	private boolean _setOriginalKaleoNodeId;
	private String _name;
	private String _description;
	private long _columnBitmask;
	private KaleoTask _escapedModel;

}
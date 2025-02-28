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

package com.liferay.batch.engine.model.impl;

import com.liferay.batch.engine.model.BatchEngineImportTask;
import com.liferay.batch.engine.model.BatchEngineImportTaskContentBlobModel;
import com.liferay.batch.engine.model.BatchEngineImportTaskModel;
import com.liferay.batch.engine.service.BatchEngineImportTaskLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the BatchEngineImportTask service. Represents a row in the &quot;BatchEngineImportTask&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>BatchEngineImportTaskModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BatchEngineImportTaskImpl}.
 * </p>
 *
 * @author Shuyang Zhou
 * @see BatchEngineImportTaskImpl
 * @generated
 */
public class BatchEngineImportTaskModelImpl
	extends BaseModelImpl<BatchEngineImportTask>
	implements BatchEngineImportTaskModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a batch engine import task model instance should use the <code>BatchEngineImportTask</code> interface instead.
	 */
	public static final String TABLE_NAME = "BatchEngineImportTask";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"batchEngineImportTaskId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"batchSize", Types.BIGINT},
		{"callbackURL", Types.VARCHAR}, {"className", Types.VARCHAR},
		{"content", Types.BLOB}, {"contentType", Types.VARCHAR},
		{"endTime", Types.TIMESTAMP}, {"errorMessage", Types.VARCHAR},
		{"executeStatus", Types.VARCHAR}, {"fieldNameMapping", Types.CLOB},
		{"operation", Types.VARCHAR}, {"parameters", Types.CLOB},
		{"startTime", Types.TIMESTAMP}, {"taskItemDelegateName", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("batchEngineImportTaskId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("batchSize", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("callbackURL", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("className", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("content", Types.BLOB);
		TABLE_COLUMNS_MAP.put("contentType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("endTime", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("errorMessage", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("executeStatus", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fieldNameMapping", Types.CLOB);
		TABLE_COLUMNS_MAP.put("operation", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("parameters", Types.CLOB);
		TABLE_COLUMNS_MAP.put("startTime", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("taskItemDelegateName", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table BatchEngineImportTask (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,batchEngineImportTaskId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,batchSize LONG,callbackURL VARCHAR(75) null,className VARCHAR(255) null,content BLOB,contentType VARCHAR(75) null,endTime DATE null,errorMessage VARCHAR(1000) null,executeStatus VARCHAR(75) null,fieldNameMapping TEXT null,operation VARCHAR(75) null,parameters TEXT null,startTime DATE null,taskItemDelegateName VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table BatchEngineImportTask";

	public static final String ORDER_BY_JPQL =
		" ORDER BY batchEngineImportTask.batchEngineImportTaskId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY BatchEngineImportTask.batchEngineImportTaskId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long EXECUTESTATUS_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long BATCHENGINEIMPORTTASKID_COLUMN_BITMASK = 8L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public BatchEngineImportTaskModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _batchEngineImportTaskId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBatchEngineImportTaskId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _batchEngineImportTaskId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return BatchEngineImportTask.class;
	}

	@Override
	public String getModelClassName() {
		return BatchEngineImportTask.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<BatchEngineImportTask, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<BatchEngineImportTask, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BatchEngineImportTask, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((BatchEngineImportTask)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<BatchEngineImportTask, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<BatchEngineImportTask, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(BatchEngineImportTask)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<BatchEngineImportTask, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<BatchEngineImportTask, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, BatchEngineImportTask>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			BatchEngineImportTask.class.getClassLoader(),
			BatchEngineImportTask.class, ModelWrapper.class);

		try {
			Constructor<BatchEngineImportTask> constructor =
				(Constructor<BatchEngineImportTask>)proxyClass.getConstructor(
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

	private static final Map<String, Function<BatchEngineImportTask, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<BatchEngineImportTask, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<BatchEngineImportTask, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<BatchEngineImportTask, Object>>();
		Map<String, BiConsumer<BatchEngineImportTask, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<BatchEngineImportTask, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", BatchEngineImportTask::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<BatchEngineImportTask, Long>)
				BatchEngineImportTask::setMvccVersion);
		attributeGetterFunctions.put("uuid", BatchEngineImportTask::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<BatchEngineImportTask, String>)
				BatchEngineImportTask::setUuid);
		attributeGetterFunctions.put(
			"batchEngineImportTaskId",
			BatchEngineImportTask::getBatchEngineImportTaskId);
		attributeSetterBiConsumers.put(
			"batchEngineImportTaskId",
			(BiConsumer<BatchEngineImportTask, Long>)
				BatchEngineImportTask::setBatchEngineImportTaskId);
		attributeGetterFunctions.put(
			"companyId", BatchEngineImportTask::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<BatchEngineImportTask, Long>)
				BatchEngineImportTask::setCompanyId);
		attributeGetterFunctions.put(
			"userId", BatchEngineImportTask::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<BatchEngineImportTask, Long>)
				BatchEngineImportTask::setUserId);
		attributeGetterFunctions.put(
			"createDate", BatchEngineImportTask::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<BatchEngineImportTask, Date>)
				BatchEngineImportTask::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", BatchEngineImportTask::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<BatchEngineImportTask, Date>)
				BatchEngineImportTask::setModifiedDate);
		attributeGetterFunctions.put(
			"batchSize", BatchEngineImportTask::getBatchSize);
		attributeSetterBiConsumers.put(
			"batchSize",
			(BiConsumer<BatchEngineImportTask, Long>)
				BatchEngineImportTask::setBatchSize);
		attributeGetterFunctions.put(
			"callbackURL", BatchEngineImportTask::getCallbackURL);
		attributeSetterBiConsumers.put(
			"callbackURL",
			(BiConsumer<BatchEngineImportTask, String>)
				BatchEngineImportTask::setCallbackURL);
		attributeGetterFunctions.put(
			"className", BatchEngineImportTask::getClassName);
		attributeSetterBiConsumers.put(
			"className",
			(BiConsumer<BatchEngineImportTask, String>)
				BatchEngineImportTask::setClassName);
		attributeGetterFunctions.put(
			"content", BatchEngineImportTask::getContent);
		attributeSetterBiConsumers.put(
			"content",
			(BiConsumer<BatchEngineImportTask, Blob>)
				BatchEngineImportTask::setContent);
		attributeGetterFunctions.put(
			"contentType", BatchEngineImportTask::getContentType);
		attributeSetterBiConsumers.put(
			"contentType",
			(BiConsumer<BatchEngineImportTask, String>)
				BatchEngineImportTask::setContentType);
		attributeGetterFunctions.put(
			"endTime", BatchEngineImportTask::getEndTime);
		attributeSetterBiConsumers.put(
			"endTime",
			(BiConsumer<BatchEngineImportTask, Date>)
				BatchEngineImportTask::setEndTime);
		attributeGetterFunctions.put(
			"errorMessage", BatchEngineImportTask::getErrorMessage);
		attributeSetterBiConsumers.put(
			"errorMessage",
			(BiConsumer<BatchEngineImportTask, String>)
				BatchEngineImportTask::setErrorMessage);
		attributeGetterFunctions.put(
			"executeStatus", BatchEngineImportTask::getExecuteStatus);
		attributeSetterBiConsumers.put(
			"executeStatus",
			(BiConsumer<BatchEngineImportTask, String>)
				BatchEngineImportTask::setExecuteStatus);
		attributeGetterFunctions.put(
			"fieldNameMapping", BatchEngineImportTask::getFieldNameMapping);
		attributeSetterBiConsumers.put(
			"fieldNameMapping",
			(BiConsumer<BatchEngineImportTask, Map<String, Serializable>>)
				BatchEngineImportTask::setFieldNameMapping);
		attributeGetterFunctions.put(
			"operation", BatchEngineImportTask::getOperation);
		attributeSetterBiConsumers.put(
			"operation",
			(BiConsumer<BatchEngineImportTask, String>)
				BatchEngineImportTask::setOperation);
		attributeGetterFunctions.put(
			"parameters", BatchEngineImportTask::getParameters);
		attributeSetterBiConsumers.put(
			"parameters",
			(BiConsumer<BatchEngineImportTask, Map<String, Serializable>>)
				BatchEngineImportTask::setParameters);
		attributeGetterFunctions.put(
			"startTime", BatchEngineImportTask::getStartTime);
		attributeSetterBiConsumers.put(
			"startTime",
			(BiConsumer<BatchEngineImportTask, Date>)
				BatchEngineImportTask::setStartTime);
		attributeGetterFunctions.put(
			"taskItemDelegateName",
			BatchEngineImportTask::getTaskItemDelegateName);
		attributeSetterBiConsumers.put(
			"taskItemDelegateName",
			(BiConsumer<BatchEngineImportTask, String>)
				BatchEngineImportTask::setTaskItemDelegateName);

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
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getBatchEngineImportTaskId() {
		return _batchEngineImportTaskId;
	}

	@Override
	public void setBatchEngineImportTaskId(long batchEngineImportTaskId) {
		_batchEngineImportTaskId = batchEngineImportTaskId;
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
	public long getBatchSize() {
		return _batchSize;
	}

	@Override
	public void setBatchSize(long batchSize) {
		_batchSize = batchSize;
	}

	@Override
	public String getCallbackURL() {
		if (_callbackURL == null) {
			return "";
		}
		else {
			return _callbackURL;
		}
	}

	@Override
	public void setCallbackURL(String callbackURL) {
		_callbackURL = callbackURL;
	}

	@Override
	public String getClassName() {
		if (_className == null) {
			return "";
		}
		else {
			return _className;
		}
	}

	@Override
	public void setClassName(String className) {
		_className = className;
	}

	@Override
	public Blob getContent() {
		if (_contentBlobModel == null) {
			try {
				_contentBlobModel =
					BatchEngineImportTaskLocalServiceUtil.getContentBlobModel(
						getPrimaryKey());
			}
			catch (Exception exception) {
			}
		}

		Blob blob = null;

		if (_contentBlobModel != null) {
			blob = _contentBlobModel.getContentBlob();
		}

		return blob;
	}

	@Override
	public void setContent(Blob content) {
		if (_contentBlobModel == null) {
			_contentBlobModel = new BatchEngineImportTaskContentBlobModel(
				getPrimaryKey(), content);
		}
		else {
			_contentBlobModel.setContentBlob(content);
		}
	}

	@Override
	public String getContentType() {
		if (_contentType == null) {
			return "";
		}
		else {
			return _contentType;
		}
	}

	@Override
	public void setContentType(String contentType) {
		_contentType = contentType;
	}

	@Override
	public Date getEndTime() {
		return _endTime;
	}

	@Override
	public void setEndTime(Date endTime) {
		_endTime = endTime;
	}

	@Override
	public String getErrorMessage() {
		if (_errorMessage == null) {
			return "";
		}
		else {
			return _errorMessage;
		}
	}

	@Override
	public void setErrorMessage(String errorMessage) {
		_errorMessage = errorMessage;
	}

	@Override
	public String getExecuteStatus() {
		if (_executeStatus == null) {
			return "";
		}
		else {
			return _executeStatus;
		}
	}

	@Override
	public void setExecuteStatus(String executeStatus) {
		_columnBitmask |= EXECUTESTATUS_COLUMN_BITMASK;

		if (_originalExecuteStatus == null) {
			_originalExecuteStatus = _executeStatus;
		}

		_executeStatus = executeStatus;
	}

	public String getOriginalExecuteStatus() {
		return GetterUtil.getString(_originalExecuteStatus);
	}

	@Override
	public Map<String, Serializable> getFieldNameMapping() {
		return _fieldNameMapping;
	}

	@Override
	public void setFieldNameMapping(
		Map<String, Serializable> fieldNameMapping) {

		_fieldNameMapping = fieldNameMapping;
	}

	@Override
	public String getOperation() {
		if (_operation == null) {
			return "";
		}
		else {
			return _operation;
		}
	}

	@Override
	public void setOperation(String operation) {
		_operation = operation;
	}

	@Override
	public Map<String, Serializable> getParameters() {
		return _parameters;
	}

	@Override
	public void setParameters(Map<String, Serializable> parameters) {
		_parameters = parameters;
	}

	@Override
	public Date getStartTime() {
		return _startTime;
	}

	@Override
	public void setStartTime(Date startTime) {
		_startTime = startTime;
	}

	@Override
	public String getTaskItemDelegateName() {
		if (_taskItemDelegateName == null) {
			return "";
		}
		else {
			return _taskItemDelegateName;
		}
	}

	@Override
	public void setTaskItemDelegateName(String taskItemDelegateName) {
		_taskItemDelegateName = taskItemDelegateName;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(BatchEngineImportTask.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), BatchEngineImportTask.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public BatchEngineImportTask toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, BatchEngineImportTask>
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
		BatchEngineImportTaskImpl batchEngineImportTaskImpl =
			new BatchEngineImportTaskImpl();

		batchEngineImportTaskImpl.setMvccVersion(getMvccVersion());
		batchEngineImportTaskImpl.setUuid(getUuid());
		batchEngineImportTaskImpl.setBatchEngineImportTaskId(
			getBatchEngineImportTaskId());
		batchEngineImportTaskImpl.setCompanyId(getCompanyId());
		batchEngineImportTaskImpl.setUserId(getUserId());
		batchEngineImportTaskImpl.setCreateDate(getCreateDate());
		batchEngineImportTaskImpl.setModifiedDate(getModifiedDate());
		batchEngineImportTaskImpl.setBatchSize(getBatchSize());
		batchEngineImportTaskImpl.setCallbackURL(getCallbackURL());
		batchEngineImportTaskImpl.setClassName(getClassName());
		batchEngineImportTaskImpl.setContentType(getContentType());
		batchEngineImportTaskImpl.setEndTime(getEndTime());
		batchEngineImportTaskImpl.setErrorMessage(getErrorMessage());
		batchEngineImportTaskImpl.setExecuteStatus(getExecuteStatus());
		batchEngineImportTaskImpl.setFieldNameMapping(getFieldNameMapping());
		batchEngineImportTaskImpl.setOperation(getOperation());
		batchEngineImportTaskImpl.setParameters(getParameters());
		batchEngineImportTaskImpl.setStartTime(getStartTime());
		batchEngineImportTaskImpl.setTaskItemDelegateName(
			getTaskItemDelegateName());

		batchEngineImportTaskImpl.resetOriginalValues();

		return batchEngineImportTaskImpl;
	}

	@Override
	public int compareTo(BatchEngineImportTask batchEngineImportTask) {
		long primaryKey = batchEngineImportTask.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof BatchEngineImportTask)) {
			return false;
		}

		BatchEngineImportTask batchEngineImportTask =
			(BatchEngineImportTask)object;

		long primaryKey = batchEngineImportTask.getPrimaryKey();

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
		BatchEngineImportTaskModelImpl batchEngineImportTaskModelImpl = this;

		batchEngineImportTaskModelImpl._originalUuid =
			batchEngineImportTaskModelImpl._uuid;

		batchEngineImportTaskModelImpl._originalCompanyId =
			batchEngineImportTaskModelImpl._companyId;

		batchEngineImportTaskModelImpl._setOriginalCompanyId = false;

		batchEngineImportTaskModelImpl._setModifiedDate = false;

		batchEngineImportTaskModelImpl._contentBlobModel = null;

		batchEngineImportTaskModelImpl._originalExecuteStatus =
			batchEngineImportTaskModelImpl._executeStatus;

		batchEngineImportTaskModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<BatchEngineImportTask> toCacheModel() {
		BatchEngineImportTaskCacheModel batchEngineImportTaskCacheModel =
			new BatchEngineImportTaskCacheModel();

		batchEngineImportTaskCacheModel.mvccVersion = getMvccVersion();

		batchEngineImportTaskCacheModel.uuid = getUuid();

		String uuid = batchEngineImportTaskCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			batchEngineImportTaskCacheModel.uuid = null;
		}

		batchEngineImportTaskCacheModel.batchEngineImportTaskId =
			getBatchEngineImportTaskId();

		batchEngineImportTaskCacheModel.companyId = getCompanyId();

		batchEngineImportTaskCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			batchEngineImportTaskCacheModel.createDate = createDate.getTime();
		}
		else {
			batchEngineImportTaskCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			batchEngineImportTaskCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			batchEngineImportTaskCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		batchEngineImportTaskCacheModel.batchSize = getBatchSize();

		batchEngineImportTaskCacheModel.callbackURL = getCallbackURL();

		String callbackURL = batchEngineImportTaskCacheModel.callbackURL;

		if ((callbackURL != null) && (callbackURL.length() == 0)) {
			batchEngineImportTaskCacheModel.callbackURL = null;
		}

		batchEngineImportTaskCacheModel.className = getClassName();

		String className = batchEngineImportTaskCacheModel.className;

		if ((className != null) && (className.length() == 0)) {
			batchEngineImportTaskCacheModel.className = null;
		}

		batchEngineImportTaskCacheModel.contentType = getContentType();

		String contentType = batchEngineImportTaskCacheModel.contentType;

		if ((contentType != null) && (contentType.length() == 0)) {
			batchEngineImportTaskCacheModel.contentType = null;
		}

		Date endTime = getEndTime();

		if (endTime != null) {
			batchEngineImportTaskCacheModel.endTime = endTime.getTime();
		}
		else {
			batchEngineImportTaskCacheModel.endTime = Long.MIN_VALUE;
		}

		batchEngineImportTaskCacheModel.errorMessage = getErrorMessage();

		String errorMessage = batchEngineImportTaskCacheModel.errorMessage;

		if ((errorMessage != null) && (errorMessage.length() == 0)) {
			batchEngineImportTaskCacheModel.errorMessage = null;
		}

		batchEngineImportTaskCacheModel.executeStatus = getExecuteStatus();

		String executeStatus = batchEngineImportTaskCacheModel.executeStatus;

		if ((executeStatus != null) && (executeStatus.length() == 0)) {
			batchEngineImportTaskCacheModel.executeStatus = null;
		}

		batchEngineImportTaskCacheModel.fieldNameMapping =
			getFieldNameMapping();

		batchEngineImportTaskCacheModel.operation = getOperation();

		String operation = batchEngineImportTaskCacheModel.operation;

		if ((operation != null) && (operation.length() == 0)) {
			batchEngineImportTaskCacheModel.operation = null;
		}

		batchEngineImportTaskCacheModel.parameters = getParameters();

		Date startTime = getStartTime();

		if (startTime != null) {
			batchEngineImportTaskCacheModel.startTime = startTime.getTime();
		}
		else {
			batchEngineImportTaskCacheModel.startTime = Long.MIN_VALUE;
		}

		batchEngineImportTaskCacheModel.taskItemDelegateName =
			getTaskItemDelegateName();

		String taskItemDelegateName =
			batchEngineImportTaskCacheModel.taskItemDelegateName;

		if ((taskItemDelegateName != null) &&
			(taskItemDelegateName.length() == 0)) {

			batchEngineImportTaskCacheModel.taskItemDelegateName = null;
		}

		return batchEngineImportTaskCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{mvccVersion=");
		sb.append(getMvccVersion());
		sb.append(", uuid=");
		sb.append(getUuid());
		sb.append(", batchEngineImportTaskId=");
		sb.append(getBatchEngineImportTaskId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", batchSize=");
		sb.append(getBatchSize());
		sb.append(", callbackURL=");
		sb.append(getCallbackURL());
		sb.append(", className=");
		sb.append(getClassName());
		sb.append(", contentType=");
		sb.append(getContentType());
		sb.append(", endTime=");
		sb.append(getEndTime());
		sb.append(", errorMessage=");
		sb.append(getErrorMessage());
		sb.append(", executeStatus=");
		sb.append(getExecuteStatus());
		sb.append(", fieldNameMapping=");
		sb.append(getFieldNameMapping());
		sb.append(", operation=");
		sb.append(getOperation());
		sb.append(", parameters=");
		sb.append(getParameters());
		sb.append(", startTime=");
		sb.append(getStartTime());
		sb.append(", taskItemDelegateName=");
		sb.append(getTaskItemDelegateName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(64);

		sb.append("<model><model-name>");
		sb.append("com.liferay.batch.engine.model.BatchEngineImportTask");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>mvccVersion</column-name><column-value><![CDATA[");
		sb.append(getMvccVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>batchEngineImportTaskId</column-name><column-value><![CDATA[");
		sb.append(getBatchEngineImportTaskId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>batchSize</column-name><column-value><![CDATA[");
		sb.append(getBatchSize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>callbackURL</column-name><column-value><![CDATA[");
		sb.append(getCallbackURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>className</column-name><column-value><![CDATA[");
		sb.append(getClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contentType</column-name><column-value><![CDATA[");
		sb.append(getContentType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endTime</column-name><column-value><![CDATA[");
		sb.append(getEndTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>errorMessage</column-name><column-value><![CDATA[");
		sb.append(getErrorMessage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>executeStatus</column-name><column-value><![CDATA[");
		sb.append(getExecuteStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fieldNameMapping</column-name><column-value><![CDATA[");
		sb.append(getFieldNameMapping());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>operation</column-name><column-value><![CDATA[");
		sb.append(getOperation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parameters</column-name><column-value><![CDATA[");
		sb.append(getParameters());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startTime</column-name><column-value><![CDATA[");
		sb.append(getStartTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>taskItemDelegateName</column-name><column-value><![CDATA[");
		sb.append(getTaskItemDelegateName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, BatchEngineImportTask>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _batchEngineImportTaskId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _batchSize;
	private String _callbackURL;
	private String _className;
	private BatchEngineImportTaskContentBlobModel _contentBlobModel;
	private String _contentType;
	private Date _endTime;
	private String _errorMessage;
	private String _executeStatus;
	private String _originalExecuteStatus;
	private Map<String, Serializable> _fieldNameMapping;
	private String _operation;
	private Map<String, Serializable> _parameters;
	private Date _startTime;
	private String _taskItemDelegateName;
	private long _columnBitmask;
	private BatchEngineImportTask _escapedModel;

}
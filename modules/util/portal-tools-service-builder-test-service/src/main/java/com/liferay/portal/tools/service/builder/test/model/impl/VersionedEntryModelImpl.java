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

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.tools.service.builder.test.model.VersionedEntry;
import com.liferay.portal.tools.service.builder.test.model.VersionedEntryModel;
import com.liferay.portal.tools.service.builder.test.model.VersionedEntryVersion;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the VersionedEntry service. Represents a row in the &quot;VersionedEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>VersionedEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link VersionedEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VersionedEntryImpl
 * @generated
 */
public class VersionedEntryModelImpl
	extends BaseModelImpl<VersionedEntry> implements VersionedEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a versioned entry model instance should use the <code>VersionedEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "VersionedEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"headId", Types.BIGINT},
		{"head", Types.BOOLEAN}, {"versionedEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("headId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("head", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("versionedEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table VersionedEntry (mvccVersion LONG default 0 not null,headId LONG,head BOOLEAN,versionedEntryId LONG not null primary key,groupId LONG)";

	public static final String TABLE_SQL_DROP = "drop table VersionedEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY versionedEntry.versionedEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY VersionedEntry.versionedEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"value.object.entity.cache.enabled.com.liferay.portal.tools.service.builder.test.model.VersionedEntry"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"value.object.finder.cache.enabled.com.liferay.portal.tools.service.builder.test.model.VersionedEntry"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"value.object.column.bitmask.enabled.com.liferay.portal.tools.service.builder.test.model.VersionedEntry"),
		true);

	public static final long GROUPID_COLUMN_BITMASK = 1L;

	public static final long HEAD_COLUMN_BITMASK = 2L;

	public static final long HEADID_COLUMN_BITMASK = 4L;

	public static final long VERSIONEDENTRYID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.VersionedEntry"));

	public VersionedEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _versionedEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setVersionedEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _versionedEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return VersionedEntry.class;
	}

	@Override
	public String getModelClassName() {
		return VersionedEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<VersionedEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<VersionedEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<VersionedEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((VersionedEntry)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<VersionedEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<VersionedEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(VersionedEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<VersionedEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<VersionedEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, VersionedEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			VersionedEntry.class.getClassLoader(), VersionedEntry.class,
			ModelWrapper.class);

		try {
			Constructor<VersionedEntry> constructor =
				(Constructor<VersionedEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<VersionedEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<VersionedEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<VersionedEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<VersionedEntry, Object>>();
		Map<String, BiConsumer<VersionedEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<VersionedEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", VersionedEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<VersionedEntry, Long>)VersionedEntry::setMvccVersion);
		attributeGetterFunctions.put("headId", VersionedEntry::getHeadId);
		attributeSetterBiConsumers.put(
			"headId",
			(BiConsumer<VersionedEntry, Long>)VersionedEntry::setHeadId);
		attributeGetterFunctions.put(
			"versionedEntryId", VersionedEntry::getVersionedEntryId);
		attributeSetterBiConsumers.put(
			"versionedEntryId",
			(BiConsumer<VersionedEntry, Long>)
				VersionedEntry::setVersionedEntryId);
		attributeGetterFunctions.put("groupId", VersionedEntry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<VersionedEntry, Long>)VersionedEntry::setGroupId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	public boolean getHead() {
		return _head;
	}

	@Override
	public boolean isHead() {
		return _head;
	}

	public boolean getOriginalHead() {
		return _originalHead;
	}

	public void setHead(boolean head) {
		_columnBitmask |= HEAD_COLUMN_BITMASK;

		if (!_setOriginalHead) {
			_setOriginalHead = true;

			_originalHead = _head;
		}

		_head = head;
	}

	@Override
	public void populateVersionModel(
		VersionedEntryVersion versionedEntryVersion) {

		versionedEntryVersion.setGroupId(getGroupId());
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
	public long getHeadId() {
		return _headId;
	}

	@Override
	public void setHeadId(long headId) {
		_columnBitmask |= HEADID_COLUMN_BITMASK;

		if (!_setOriginalHeadId) {
			_setOriginalHeadId = true;

			_originalHeadId = _headId;
		}

		if (headId >= 0) {
			setHead(false);
		}
		else {
			setHead(true);
		}

		_headId = headId;
	}

	public long getOriginalHeadId() {
		return _originalHeadId;
	}

	@Override
	public long getVersionedEntryId() {
		return _versionedEntryId;
	}

	@Override
	public void setVersionedEntryId(long versionedEntryId) {
		_versionedEntryId = versionedEntryId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, VersionedEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public VersionedEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, VersionedEntry>
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
		VersionedEntryImpl versionedEntryImpl = new VersionedEntryImpl();

		versionedEntryImpl.setMvccVersion(getMvccVersion());
		versionedEntryImpl.setHeadId(getHeadId());
		versionedEntryImpl.setVersionedEntryId(getVersionedEntryId());
		versionedEntryImpl.setGroupId(getGroupId());

		versionedEntryImpl.resetOriginalValues();

		return versionedEntryImpl;
	}

	@Override
	public int compareTo(VersionedEntry versionedEntry) {
		long primaryKey = versionedEntry.getPrimaryKey();

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

		if (!(object instanceof VersionedEntry)) {
			return false;
		}

		VersionedEntry versionedEntry = (VersionedEntry)object;

		long primaryKey = versionedEntry.getPrimaryKey();

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
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		VersionedEntryModelImpl versionedEntryModelImpl = this;

		versionedEntryModelImpl._originalHeadId =
			versionedEntryModelImpl._headId;

		versionedEntryModelImpl._setOriginalHeadId = false;

		versionedEntryModelImpl._originalHead = versionedEntryModelImpl._head;

		versionedEntryModelImpl._setOriginalHead = false;

		versionedEntryModelImpl._originalGroupId =
			versionedEntryModelImpl._groupId;

		versionedEntryModelImpl._setOriginalGroupId = false;

		versionedEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<VersionedEntry> toCacheModel() {
		VersionedEntryCacheModel versionedEntryCacheModel =
			new VersionedEntryCacheModel();

		versionedEntryCacheModel.mvccVersion = getMvccVersion();

		versionedEntryCacheModel.headId = getHeadId();

		versionedEntryCacheModel.head = isHead();

		versionedEntryCacheModel.versionedEntryId = getVersionedEntryId();

		versionedEntryCacheModel.groupId = getGroupId();

		return versionedEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<VersionedEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<VersionedEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<VersionedEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((VersionedEntry)this));
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
		Map<String, Function<VersionedEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<VersionedEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<VersionedEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((VersionedEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, VersionedEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _headId;
	private long _originalHeadId;
	private boolean _setOriginalHeadId;
	private boolean _head;
	private boolean _originalHead;
	private boolean _setOriginalHead;
	private long _versionedEntryId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _columnBitmask;
	private VersionedEntry _escapedModel;

}
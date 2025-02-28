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

package com.liferay.dynamic.data.mapping.internal.change.tracking.reference;

import com.liferay.change.tracking.reference.TableReferenceDefinition;
import com.liferay.change.tracking.reference.builder.TableReferenceInfoBuilder;
import com.liferay.dynamic.data.mapping.model.DDMStorageLinkTable;
import com.liferay.dynamic.data.mapping.model.DDMStructureTable;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersionTable;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStorageLinkPersistence;
import com.liferay.portal.kernel.model.CompanyTable;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(service = TableReferenceDefinition.class)
public class DDMStorageLinkTableReferenceDefinition
	implements TableReferenceDefinition<DDMStorageLinkTable> {

	@Override
	public void defineTableReferences(
		TableReferenceInfoBuilder<DDMStorageLinkTable>
			tableReferenceInfoBuilder) {

		tableReferenceInfoBuilder.nonreferenceColumn(
			DDMStorageLinkTable.INSTANCE.uuid
		).singleColumnReference(
			DDMStorageLinkTable.INSTANCE.companyId,
			CompanyTable.INSTANCE.companyId
		).nonreferenceColumns(
			DDMStorageLinkTable.INSTANCE.classNameId,
			DDMStorageLinkTable.INSTANCE.classPK
		).singleColumnReference(
			DDMStorageLinkTable.INSTANCE.structureId,
			DDMStructureTable.INSTANCE.structureId
		).singleColumnReference(
			DDMStorageLinkTable.INSTANCE.structureVersionId,
			DDMStructureVersionTable.INSTANCE.structureVersionId
		);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _ddmStorageLinkPersistence;
	}

	@Override
	public DDMStorageLinkTable getTable() {
		return DDMStorageLinkTable.INSTANCE;
	}

	@Reference
	private DDMStorageLinkPersistence _ddmStorageLinkPersistence;

}
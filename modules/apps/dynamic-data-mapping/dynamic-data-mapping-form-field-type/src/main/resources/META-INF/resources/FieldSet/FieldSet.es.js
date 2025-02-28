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

import './FieldSet.scss';

import {Layout, getRepeatedIndex} from 'dynamic-data-mapping-form-renderer';
import React, {useMemo} from 'react';

import {FieldBase} from '../FieldBase/ReactFieldBase.es';
import Panel from './Panel.es';

const getRowsArray = (rows) => {
	if (typeof rows === 'string') {
		try {
			return JSON.parse(rows);
		}
		catch (e) {
			return [];
		}
	}

	return rows;
};

const getRows = (rows, nestedFields) => {
	const normalizedRows = getRowsArray(rows);

	return normalizedRows.map((row) => ({
		...row,
		columns: row.columns.map((column) => ({
			...column,
			fields: column.fields.map((fieldName) => {
				return nestedFields.find(
					(nestedField) => nestedField.fieldName === fieldName
				);
			}),
		})),
	}));
};

const FieldSet = ({
	collapsible,
	label,
	name,
	nestedFields = [],
	readOnly,
	repeatable,
	rows,
	showLabel,
	...otherProps
}) => {
	const repeatedIndex = useMemo(() => getRepeatedIndex(name), [name]);

	return (
		<FieldBase
			{...otherProps}
			name={name}
			readOnly={readOnly}
			repeatable={collapsible ? false : repeatable}
			showLabel={false}
		>
			<div className="ddm-field-types-fieldset__nested">
				{showLabel && !collapsible && (
					<>
						<label className="text-uppercase">{label}</label>
						<div className="ddm-field-types-fieldset__nested-separator">
							<div className="mt-1 separator" />
						</div>
					</>
				)}

				{collapsible ? (
					<Panel
						readOnly={readOnly}
						repeatable={repeatable}
						showLabel={showLabel}
						showRepeatableRemoveButton={
							repeatable && repeatedIndex > 0
						}
						title={label}
					>
						<Layout rows={getRows(rows, nestedFields)} />
					</Panel>
				) : (
					<Layout rows={getRows(rows, nestedFields)} />
				)}
			</div>
		</FieldBase>
	);
};

export default FieldSet;

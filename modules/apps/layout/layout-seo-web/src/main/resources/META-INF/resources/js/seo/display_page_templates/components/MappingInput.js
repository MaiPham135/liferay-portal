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

import ClayForm, {ClayInput} from '@clayui/form';
import {PropTypes} from 'prop-types';
import React, {useState} from 'react';

import MappingPanel from './MappingPanel';

const UNMAPPED_OPTION = {
	key: 'unmapped',
	label: `-- ${Liferay.Language.get('unmapped')} --`,
};

function MappingInput({
	fieldType,
	helpMessage,
	initialFields,
	label,
	name,
	selectedFieldKey,
	selectedSource,
}) {
	const fields = [
		UNMAPPED_OPTION,
		...initialFields.filter(({type}) => type === fieldType),
	];
	const [source, setSource] = useState(selectedSource);
	const [field, setField] = useState(
		fields.find(({key}) => key === selectedFieldKey) || UNMAPPED_OPTION
	);

	const isActive = !!field && field.key !== UNMAPPED_OPTION.key;

	const inititalSourceLabel = selectedSource
		? selectedSource.classTypeLabel || selectedSource.classNameLabel
		: '';

	const handleOnchange = ({field, source}) => {
		setSource(source);
		setField(field);
	};

	return (
		<ClayForm.Group>
			<label className="control-label">{label}</label>
			<ClayInput.Group>
				<ClayInput.GroupItem>
					<ClayInput
						className="dpt-mapping-input"
						readOnly
						type="text"
						value={`${
							(isActive &&
								inititalSourceLabel &&
								`${inititalSourceLabel}: `) ||
							''
						}${field.label}`}
					/>
					<ClayInput name={name} type="hidden" value={field.key} />
				</ClayInput.GroupItem>
				<ClayInput.GroupItem shrink>
					<MappingPanel
						field={field}
						fields={fields}
						isActive={isActive}
						name={name}
						onChange={handleOnchange}
						source={{
							...source,
							initialValue: inititalSourceLabel,
						}}
					/>
				</ClayInput.GroupItem>
			</ClayInput.Group>
			{helpMessage && <ClayForm.Text>{helpMessage}</ClayForm.Text>}
		</ClayForm.Group>
	);
}

MappingInput.propTypes = {
	helpMessage: PropTypes.string,
	name: PropTypes.string.isRequired,
	selectedFieldKey: PropTypes.string,
	selectedSource: PropTypes.shape({
		classNameLabel: PropTypes.string,
		classTypeLabel: PropTypes.string,
	}).isRequired,
};

export default MappingInput;

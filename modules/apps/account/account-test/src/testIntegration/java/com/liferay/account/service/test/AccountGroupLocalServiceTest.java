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

package com.liferay.account.service.test;

import com.liferay.account.model.AccountGroup;
import com.liferay.account.service.AccountGroupLocalService;
import com.liferay.account.service.test.util.AccountGroupTestUtil;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Albert Lee
 */
@DataGuard(scope = DataGuard.Scope.METHOD)
@RunWith(Arquillian.class)
public class AccountGroupLocalServiceTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAddAccountGroup() throws Exception {
		AccountGroup accountGroup = _addAccountGroup();

		Assert.assertNotNull(
			_accountGroupLocalService.fetchAccountGroup(
				accountGroup.getAccountGroupId()));
	}

	@Test
	public void testDeleteAccountGroup() throws Exception {
		_testDeleteAccountGroup(
			_addAccountGroup(),
			accountGroup -> _accountGroupLocalService.deleteAccountGroup(
				accountGroup));
		_testDeleteAccountGroup(
			_addAccountGroup(),
			accountGroup -> _accountGroupLocalService.deleteAccountGroup(
				accountGroup.getAccountGroupId()));
	}

	@Test
	public void testSearchAccountGroups() throws Exception {
		long[] accountGroupIds = new long[5];

		for (int i = 0; i < accountGroupIds.length; i++) {
			AccountGroup accountGroup = _addAccountGroup();

			accountGroupIds[i] = accountGroup.getAccountGroupId();
		}

		BaseModelSearchResult<AccountGroup> baseModelSearchResult =
			_accountGroupLocalService.searchAccountGroups(
				TestPropsValues.getCompanyId(), null, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Assert.assertEquals(5, baseModelSearchResult.getLength());

		String keywords = RandomTestUtil.randomString();

		List<AccountGroup> expectedAccountGroups = Arrays.asList(
			_addAccountGroup(keywords, RandomTestUtil.randomString()),
			_addAccountGroup(RandomTestUtil.randomString(), keywords));

		baseModelSearchResult = _accountGroupLocalService.searchAccountGroups(
			TestPropsValues.getCompanyId(), keywords, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(
			expectedAccountGroups.size(), baseModelSearchResult.getLength());

		expectedAccountGroups = _accountGroupLocalService.getAccountGroups(
			TestPropsValues.getCompanyId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		baseModelSearchResult = _accountGroupLocalService.searchAccountGroups(
			TestPropsValues.getCompanyId(), null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(
			expectedAccountGroups.size(), baseModelSearchResult.getLength());

		expectedAccountGroups = Arrays.asList(
			_addAccountGroup(RandomTestUtil.randomString(), keywords),
			_addAccountGroup(RandomTestUtil.randomString(), keywords),
			_addAccountGroup(RandomTestUtil.randomString(), keywords),
			_addAccountGroup(RandomTestUtil.randomString(), keywords),
			_addAccountGroup(RandomTestUtil.randomString(), keywords));

		Comparator<AccountGroup> comparator =
			(accountGroup1, accountGroup2) -> {
				String name1 = accountGroup1.getName();
				String name2 = accountGroup2.getName();

				return name1.compareToIgnoreCase(name2);
			};

		_testSearchAccountGroups(
			comparator, expectedAccountGroups, keywords, false);
		_testSearchAccountGroups(
			comparator, expectedAccountGroups, keywords, true);
	}

	private AccountGroup _addAccountGroup() throws Exception {
		return AccountGroupTestUtil.addAccountGroup(
			_accountGroupLocalService, RandomTestUtil.randomString(),
			RandomTestUtil.randomString());
	}

	private AccountGroup _addAccountGroup(String name, String description)
		throws Exception {

		return AccountGroupTestUtil.addAccountGroup(
			_accountGroupLocalService, name, description);
	}

	private void _testDeleteAccountGroup(
			AccountGroup accountGroup,
			UnsafeConsumer<AccountGroup, Exception> unsafeConsumer)
		throws Exception {

		unsafeConsumer.accept(accountGroup);

		Assert.assertNull(
			_accountGroupLocalService.fetchAccountGroup(
				accountGroup.getAccountGroupId()));
	}

	private void _testSearchAccountGroups(
			Comparator<AccountGroup> comparator,
			List<AccountGroup> expectedAccountGroups, String keywords,
			boolean reversed)
		throws Exception {

		int delta = 3;
		int start = 1;

		BaseModelSearchResult<AccountGroup> baseModelSearchResult =
			_accountGroupLocalService.searchAccountGroups(
				TestPropsValues.getCompanyId(), keywords, start, start + delta,
				OrderByComparatorFactoryUtil.create(
					"AccountGroup", "name", !reversed));

		List<AccountGroup> actualAccountGroups =
			baseModelSearchResult.getBaseModels();

		Assert.assertEquals(
			actualAccountGroups.toString(), delta, actualAccountGroups.size());

		if (reversed) {
			expectedAccountGroups.sort(comparator.reversed());
		}
		else {
			expectedAccountGroups.sort(comparator);
		}

		for (int i = 0; i < delta; i++) {
			Assert.assertEquals(
				expectedAccountGroups.get(start + i),
				actualAccountGroups.get(i));
		}
	}

	@Inject
	private AccountGroupLocalService _accountGroupLocalService;

}
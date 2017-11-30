package com.showshine.skynet.service;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.showshine.skynet.context.Contants;
import com.showshine.skynet.context.SystemConfigKey;
import com.showshine.skynet.dao.cache.RedisBaseDao;
import com.showshine.skynet.strategy.handle.BlockStrategyService;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年11月30日
 */
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SystemConfigServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemConfigServiceTest.class);

	@Autowired
	private SystemConfigService systemConfigService;
	@Autowired
	private RedisBaseDao redisBaseDao;
	
	@Test
	public void testQueryStrategyList1() throws InterruptedException {
		systemConfigService.updateByKey(Contants.UREALSOON_FIRST_LOAN, "permissionService,highRiskAreasService,insideBlackListService");
		systemConfigService.updateByKey(Contants.UREALSOON_RELOAN, "highRiskAreasService,insideBlackListService,fraudListService");
		
		List<BlockStrategyService> list1 = systemConfigService.queryStrategyList(Contants.UREALSOON_FIRST_LOAN);
		List<BlockStrategyService> list2 = systemConfigService.queryStrategyList(Contants.UREALSOON_RELOAN);
		assertTrue(list1.size() == 3);
		assertTrue(list2.size() == 3);
		
		int n1 = systemConfigService.updateByKey(Contants.UREALSOON_FIRST_LOAN, "permissionService,highRiskAreasService");
		int n2 = systemConfigService.updateByKey(Contants.UREALSOON_RELOAN, "highRiskAreasService,fraudListService");
		
		int n3 = systemConfigService.updateByKey(SystemConfigKey.KEY_SWITCH_STRATEGY_RELOAD, "ON");
		assertTrue(n1 * n2 * n3 > 0);
		
		Thread.sleep(TimeUnit.MINUTES.toMillis(1));
		
		list1 = systemConfigService.queryStrategyList(Contants.UREALSOON_FIRST_LOAN);
		assertTrue(list1.size() == 2);
		list2 = systemConfigService.queryStrategyList(Contants.UREALSOON_RELOAN);
		assertTrue(list2.size() == 2);
	}
	
	@Test
	public void testQueryStrategyList2() throws InterruptedException {
		int n0 = systemConfigService.updateByKey(Contants.UREALSOON_FIRST_LOAN, "permissionService,highRiskAreasService,insideBlackListService");
		System.out.println("n0 :" + n0);
		final ExecutorService exec = Executors.newFixedThreadPool(20, new CustomizableThreadFactory("exec1-strategyList"));
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					exec.submit(new Runnable() {
						
						@Override
						public void run() {
							List<BlockStrategyService> list = systemConfigService.queryStrategyList(Contants.UREALSOON_FIRST_LOAN);
							LOGGER.info("{} strategyList size {}.", Contants.UREALSOON_FIRST_LOAN, list.size());
							assertTrue(list.size() <= 3);
						}
					});
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
//						e.printStackTrace();
					}
				}
			}
		}).start();
		
		Thread.sleep(30 * 1000);
		
		LOGGER.info("open switch {}", SystemConfigKey.KEY_SWITCH_STRATEGY_RELOAD);
		int n1 = systemConfigService.updateByKey(Contants.UREALSOON_FIRST_LOAN, "permissionService,highRiskAreasService");
		int n2 = systemConfigService.updateByKey(SystemConfigKey.KEY_SWITCH_STRATEGY_RELOAD, "ON");
		redisBaseDao.deleteKey(SystemConfigKey.KEY_SWITCH_STRATEGY_RELOAD);
		
		assertTrue(n1 * n2 > 0);
		ExecutorService exec2 = Executors.newFixedThreadPool(20, new CustomizableThreadFactory("exec2-strategyList"));
		long start = System.currentTimeMillis();
		while(true) {
			exec2.submit(new Runnable() {
				
				@Override
				public void run() {
					List<BlockStrategyService> list = systemConfigService.queryStrategyList(Contants.UREALSOON_FIRST_LOAN);
					LOGGER.info("{} strategyList size {}.", Contants.UREALSOON_FIRST_LOAN, list.size());
					assertTrue(list.size() == 2);
				}
			});
			Thread.sleep(10);
			if((System.currentTimeMillis() - start) / 1000 / 60 >= 2) {
				break;
			}
		}
		
	}

}

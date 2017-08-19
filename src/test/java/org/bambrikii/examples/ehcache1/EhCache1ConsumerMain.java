package org.bambrikii.examples.ehcache1;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Alexander Arakelyan on 19/08/17 15:34.
 */
public class EhCache1ConsumerMain {
	private static Logger LOGGER = LoggerFactory.getLogger(EhCache1ConsumerMain.class);

	public static void main(String[] args) throws InterruptedException {
		//1. Create a cache manager
		CacheManager cm = CacheManager.create(EhCache1ConsumerMain.class.getResourceAsStream("/ehcache-consumer.xml"));

		//3. Get a cache called "cache1"
		Cache cache = cm.getCache("cache1");

		long total = 0;

		//5. Get element from cache
		for (int i = 1; i <= 10000; i++) {
			String key = Integer.toString(i);
			long start = System.currentTimeMillis();
			Element ele = cache.get(key);
			long stop = System.currentTimeMillis();
			total += stop - start;
			//6. Print out the element
			String output = (ele == null ? null : ele.getObjectValue().toString());
			LOGGER.debug("{} - {}", key, output);
			if (i % 10 == 0) {
				LOGGER.debug("Total of {} = {}, average = {}", i, total, total / i);
			}
			Thread.sleep(10);
		}

		LOGGER.debug("sleep");
		Thread.sleep(100000);

		//8. shut down the cache manager
		cm.shutdown();
	}
}

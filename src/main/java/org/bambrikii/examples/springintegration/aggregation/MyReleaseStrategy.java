package org.bambrikii.examples.springintegration.aggregation;

import org.springframework.integration.aggregator.ReleaseStrategy;
import org.springframework.integration.store.MessageGroup;

/**
 * Created by Alexander Arakelyan on 05/06/18 22:01.
 */
class MyReleaseStrategy implements ReleaseStrategy {
    @Override
    public boolean canRelease(MessageGroup group) {
        return group.getMessages().size() > 2;
    }
}

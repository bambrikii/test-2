package org.bambrikii.examples.springintegration.customoutboundadapter;

import org.springframework.integration.dsl.core.MessageHandlerSpec;

/**
 * Created by Alexander Arakelyan on 06/06/18 21:45.
 */
public class MyCustomOutboundAdapterSpec
        extends MessageHandlerSpec<MyCustomOutboundAdapterSpec,
        MyCustomOutboundHandler>
    /*
        implements ComponentsRegistration
        */ {
    //    @Override
//    public Collection<Object> getComponentsToRegister() {
//        return null;
//    }
    MyCustomOutboundAdapterSpec(String host) {
        this.target = new MyCustomOutboundHandler(host);
    }
}

package com.example.demo.cxf;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;

/**
 * Created by lijiyang on 2017/11/30.
 */
public class CSFclient {
    public static void main(String[] args) throws Exception {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("wdsl的url");
        QName qName = new QName("命名空间","方法名");
        String param1 = "参数1";
        String param2 = "参数2";
        Object[] object = client.invoke(qName, param1,param2);
    }

}

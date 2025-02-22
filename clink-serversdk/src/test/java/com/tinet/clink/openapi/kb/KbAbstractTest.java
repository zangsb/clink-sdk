package com.tinet.clink.openapi.kb;


import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import org.junit.Before;

/**
 * @author feizq
 * @date 2021/06/26
 **/
public class KbAbstractTest {

    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        configuration = new ClientConfiguration("659f737b1134aaac65c9d893248a3de9", "3aKa9i82qRl322s1540t");
        configuration.setScheme("http");
        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");

        client = new Client(configuration);
    }
}

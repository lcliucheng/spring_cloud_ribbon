package org.springframework.cloud.openfeign;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liu cheng
 * @since 2019-09-09 11:24
 */

public class TestApplication {

    public static void main(String[] args) {
        String a="http://zzjr/dddd";
        int zzjr = a.indexOf("zzjr");
        int length = "zzjr".length();
        String substring = a.substring(0, zzjr + length);
        String substring1 = a.substring(zzjr + length, a.length());


        System.out.println(substring);
        System.out.println(substring1);

    }
}

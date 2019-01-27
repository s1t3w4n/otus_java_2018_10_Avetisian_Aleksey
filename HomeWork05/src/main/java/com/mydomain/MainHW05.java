package com.mydomain;

import javax.management.*;
import java.lang.management.ManagementFactory;
/*TODO
Сделать загрузку настроек VM из файла(папка properties)
 */
public class MainHW05 {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        try {
            start();
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }
    }
    private static void start() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        long beginTime = System.currentTimeMillis();

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.anylink:type=com.mydomain.Benchmark");

        Benchmark mbean = new Benchmark(10_000);
        mbs.registerMBean(mbean, name);
        //mbean.run(100_000);
        mbean.run(10_000_000);//out of memory

        System.out.println("time: " + (System.currentTimeMillis() - beginTime)/1000);
    }
  }

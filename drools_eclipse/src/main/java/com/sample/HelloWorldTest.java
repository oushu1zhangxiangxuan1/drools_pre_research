package com.sample;

import java.lang.reflect.Method;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class HelloWorldTest{
    public static final void main(String[] args){
        try{
            HelloWorldTest hello = new HelloWorldTest();
            // hello.Test1();
            // hello.Test2();
            // hello.Test3();
            // hello.Test4();
            // hello.Test5();
            Method m = hello.getClass().getMethod("Test"+args[0]);
            m.invoke(hello);
        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    public void Test1(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer KieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = KieContainer.newKieSession("kession-test2");

        Message message = new Message();
        message.setId("123");
        message.setName("haha");
        kieSession.insert(message);

        int i = kieSession.fireAllRules();
        System.out.println("=========="+i);
        kieSession.dispose();
    }

    public void Test2(){
        try{
            //load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession  = kContainer.newKieSession("all-rules");

            // go !
            // Message message = new Message();
            kSession.fireAllRules();
            kSession.dispose();
        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    public void Test3(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer KieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = KieContainer.newKieSession("kession-test3");

        Message message = new Message();
        message.setId("123");
        message.setName("haha");
        kieSession.insert(message);

        int i = kieSession.fireAllRules();
        System.out.println("=========="+i);
        kieSession.dispose();
    }
    public void Test4(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer KieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = KieContainer.newKieSession("kession-test4");

        Message message = new Message();
        message.setId("123");
        message.setName("haha");
        kieSession.insert(message);

        int i = kieSession.fireAllRules();
        System.out.println("=========="+i);
        kieSession.dispose();
    }
    public void Test5(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession-test5");

        Message1 msg = new Message1();
        msg.setMessage("Hello world!");
        msg.setStatus(Message1.HELLO);
        kieSession.insert(msg);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    public void Test6(){
        //Wrong data insert into session
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession-test2");

        Message1 msg = new Message1();
        msg.setMessage("Hello world!");
        msg.setStatus(Message1.HELLO);
        kieSession.insert(msg);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
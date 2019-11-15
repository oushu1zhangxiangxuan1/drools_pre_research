package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class HelloWorldTest{
    public static final void main(String[] args){
        HelloWorldTest hello = new HelloWorldTest();
        // hello.Test1();
        // hello.Test2();
        hello.Test3();
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
}
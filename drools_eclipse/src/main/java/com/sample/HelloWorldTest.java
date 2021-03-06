package com.sample;

import java.lang.reflect.Method;

import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.api.io.ResourceType;
import org.drools.core.impl.InternalKnowledgeBase;
// import org.drools.KnowledgeBaseFactory;
// import org.drools.KnowledgeBase;
// import org.kie.internal.KnowledgeBaseFactory;
// import org.kie.internal.KnowledgeBase;
import org.drools.definition.type.FactType;
// import org.kie.api.definition.type.FactType;
// import org.kie.api.definition.KiePackage;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import org.kie.api.builder.KieRepository;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieBuilder;

import org.kie.api.builder.Message.Level;

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
        // multi rule package config
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

    public void Test7(){
        // merchandise valuation
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession-test7");

        Product fan= new Product("Fan", 280);
        Product washer = new Product("Washer", 2200);
        Product phone = new Product("Phone", 998);

        kieSession.insert(fan);
        kieSession.insert(washer);
        kieSession.insert(phone);
        
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    // public void Test8(){
    //     // read drl manually from class path
    //     KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    //     kbuilder.add(ResourceFactory.newClassPathResource("com/rules1/r1.drl"), ResourceType.DRL);

    //     KnowledgeBuilderErrors errors = kbuilder.getErrors();
    //     if(errors.size()>0){
    //         for(KnowledgeBuilderError error:errors){
    //             System.err.println(error);
    //         }
    //         return;
    //     }

    //     //drools 7.X

    //     InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    //     Collection<KiePackage> pkgs = kbuilder.getKnowledgePackages();
    //     kbase.addPackages(pkgs);
    //     KieSession kieSession = kbase.newKieSession();
    //     Product fan = new Product("fan", 1);
    //     kieSession.insert(fan);
    //     kieSession.fireAllRules();
    //     kieSession.dispose();
    // }

    public void Test9(){
        // read drl manually from class path trial 2
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();

        String drlStr = "rule hello4 when then System.out.println(\"=============hello4================\"); end";
    

        kfs.write("src/main/resources/com/sample/rules9/r2.drl", drlStr);
        KieBuilder kb = ks.newKieBuilder(kfs);

        kb.buildAll();
        if(kb.getResults().hasMessages(Level.ERROR)){
            System.out.println("Failed to build rules: "+kb.getResults().toString());
            return;
        }

        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());

        KieSession kieSession = kContainer.newKieSession();
        Product fan = new Product("fan", 1);
        kieSession.insert(fan);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    // public void Test10(){
    //     //type declaration in drl file
    //     try{

    //         KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    //         FactType personType = kbase.getFactType("com.sample.rules10", "Person");
            
    //         Object bob = personType.newInstance();
    //         personType.set(bob, "name", "Bob");
    //         personType.set(bob, "age", 43);


    //         KieServices kieServices = KieServices.Factory.get();
    //         KieContainer kieContainer = kieServices.getKieClasspathContainer();
    //         KieSession kieSession = kieContainer.newKieSession("kession-test10");

    //         kieSession.insert(bob);
    //         kieSession.fireAllRules();
    //         String name = personType.get(bob,"name").toString();
    //         int age=Integer.parseInt(personType.get(bob, "age").toString());
    //         System.out.println("name: "+name);
    //         System.out.println("age: "+age);
    //         kieSession.dispose();
    //     }catch(Throwable t){
    //         t.printStackTrace();
    //     }
    // }

    public void Test11(){
        //type declaration in drl file
        try{
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession("kession-test11");

            FactType personType = (FactType)kieSession.getKieBase().getFactType("com.sample.rules11", "Person");

            Object bob = personType.newInstance();
            personType.set(bob, "name", "Bob");
            personType.set(bob, "age", 43);


            kieSession.insert(bob);
            kieSession.fireAllRules();
            String name = personType.get(bob,"name").toString();
            int age=Integer.parseInt(personType.get(bob, "age").toString());
            System.out.println("name: "+name);
            System.out.println("age: "+age);
            kieSession.dispose();
        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    public void Test12(){
        // test insert json and map
        try{
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession("kession-test12");





            Person p1 = new Person(1, "p1");

            Person p2 = new Person(2, "p2");


            Map bob = new HashMap<>();

            bob.put("name", "Bob");
            bob.put("age",12);

            kieSession.insert(bob);



            JSONObject obj = new JSONObject();
            obj.put("name", "John");
            obj.put("age",22);
            kieSession.insert(obj);


            kieSession.insert(p1);
            kieSession.insert(p2);
            Map bob1 = new HashMap<>();

            bob1.put("name", "Bob1");
            bob1.put("age",12);

            kieSession.insert(bob1);

            kieSession.fireAllRules();
            System.out.println(1);
            kieSession.fireAllRules();
            System.out.println(2);

            JSONObject obj1 = new JSONObject();
            obj1.put("name", "Saxon");
            obj1.put("age",22);

            Person p3 = new Person(3, "p3");
            kieSession.insert(obj1);
            kieSession.insert(p3);
            kieSession.fireAllRules();
            System.out.println(3);

//            String name = personType.get(bob,"name").toString();
//            int age=Integer.parseInt(personType.get(bob, "age").toString());
//            System.out.println("name: "+name);
//            System.out.println("age: "+age);
            kieSession.dispose();
        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    public void Test13(){
        // fireUntilHalt

        //but Entry only match once !? and the first insert p1 not triggered.
        //        Entry!
        //        Hello p2
        //        Grow p2 to 2
        //        Hello p1
        //        Grow p1 to 1
        //        Hello p2
        //        Grow p2 to 2
        //        Hello p1
        //        Grow p1 to 1
        //        Hello p2
        //        Grow p2 to 2
        //        Hello p1
        //        Grow p1 to 1
        //        Hello p2
        //        Grow p2 to 2
        //        Hello p1
        //        Grow p1 to 1

        try{
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession("kession-test13");


            Person p1 = new Person(1, "p1");

            Person p2 = new Person(2, "p2");

            new Thread(new Runnable() {
//                @Override
                public void run() {
                    kieSession.fireUntilHalt();
                }
            }).start();

            FactHandle serverHandle = kieSession.insert(p1);

            for (int i=8;i<=15;i++){
                if (i%2 == 0){
                    kieSession.update(serverHandle, p2);
                }else{
                    kieSession.update(serverHandle, p1);
                }
                Thread.sleep(1000);
            }

            Thread.sleep(1000);
            kieSession.halt();

        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    public void Test14(){
        // fireUntilHalt insert

        try{
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession("kession-test13");


            new Thread(new Runnable() {
                // @Override
                public void run() {
                    kieSession.fireUntilHalt();
                }
            }).start();

            for (Integer i=0;i<5;i++){
                kieSession.insert(new Person(i, "p"+i.toString()));
                Thread.sleep(1000);
            }

            Thread.sleep(1000);
            kieSession.halt();

        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    public void Test15(){
        // fireUntilHalt and get result from global

        try{
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession("kession-test15");


            new Thread(new Runnable() {
                // @Override
                public void run() {
                    kieSession.fireUntilHalt();
                }
            }).start();

            for (Integer i=0;i<5;i++){
                kieSession.insert(new Person(i, "p"+i.toString()));
                Thread.sleep(1000);
            }

            Thread.sleep(1000);
            kieSession.halt();

        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    public void Test16(){
        // fireUntilHalt and get result from global

        try{
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession("kession-test16");


            ResultEvent event = new ResultEvent();
            kieSession.setGlobal("event", event);


            new Thread(new Runnable() {
                // @Override
                public void run() {
                    kieSession.fireUntilHalt();
                }
            }).start();

            for (Integer i=0;i<5;i++){
                kieSession.insert(new Person(i, "p"+i.toString()));
                System.out.println(event.events);
                Thread.sleep(1000);
            }

            Thread.sleep(1000);
            kieSession.halt();

        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}
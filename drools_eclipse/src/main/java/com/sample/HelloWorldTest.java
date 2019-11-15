package com.sample;

import java.lang.reflect.Method;

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
// import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.definition.KiePackage;
import java.util.Collection;

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
}
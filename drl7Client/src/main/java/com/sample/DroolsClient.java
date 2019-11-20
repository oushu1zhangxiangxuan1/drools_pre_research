package com.sample;

import java.lang.reflect.Method;
import java.security.Provider.Service;
import java.sql.BatchUpdateException;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.RuleServicesClient;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.server.api.model.ServiceResponse;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;


import org.drools.compiler.kie.builder.impl.KieServicesImpl;

public class DroolsClient{

    public static final String SERVER_URL = "http://localhost:8080/kie-server/services/rest/server";
    public static final String PASSWORD = "kieserver1!";
    public static final String USERNAME = "kieserver";
    public static final String KIE_CONTAINER_ID = "HelloWorld_1.0.0-SNAPSHOT";

    public static final void main(String[] args){
        // try{
            DroolsClient hello = new DroolsClient();
            // Method m = hello.getClass().getMethod("Test"+args[0]);
            // m.invoke(hello);
            hello.Test1();
            // hello.Test2();
        // }catch(Throwable t){
        //     t.printStackTrace();
        // }
    }

    public void Test1(){

        // create config
        KieServicesConfiguration kieServicesConfiguration = KieServicesFactory.newRestConfiguration(SERVER_URL, USERNAME, PASSWORD, 10000L);

        kieServicesConfiguration.setMarshallingFormat(MarshallingFormat.JSON);

        // create rule client
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(kieServicesConfiguration);
        System.out.println("\n\n=========================================================\n\n");
        RuleServicesClient ruleServicesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);

        // craete facts 
        Message message = new Message();
        message.setId("123");
        message.setName("haha");


        // define command and insert facts
        KieServices ks = KieServices.Factory.get();
        // KieServices ks = KieServicesImpl.Factory.get();
        KieCommands kieCommands = ks.getCommands();
        List<Command<?>> commands = new LinkedList<>();
        commands.add(kieCommands.newInsert(message, "message"));
        commands.add(kieCommands.newFireAllRules());
        ServiceResponse<ExecutionResults> results = ruleServicesClient.executeCommandsWithResults(KIE_CONTAINER_ID, kieCommands.newBatchExecution(commands, "kssessionId"));


        //read response
        Message value = (Message)results.getResult().getValue("message");
        System.out.println(value);
    }

    public void Test2(){

        // example from redhat   
        // https://access.redhat.com/documentation/en-us/red_hat_jboss_brms/6.2/html/getting_started_guide/firing_rules_using_maven

        // create config
        KieServicesConfiguration kieServicesConfiguration = KieServicesFactory.newRestConfiguration(SERVER_URL, USERNAME, PASSWORD, 10000L);

        kieServicesConfiguration.setMarshallingFormat(MarshallingFormat.JSON);

        // create rule client
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(kieServicesConfiguration);
        System.out.println("\n\n=========================================================\n\n");
        RuleServicesClient ruleServicesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);

        // craete facts 
        Message message = new Message();
        message.setId("123");
        message.setName("haha");


        List<Command<?>> commands = new ArrayList<Command<?>>();
        commands.add((Command<?>)KieServices.Factory.get().getCommands().newInsert(message, "message"));
        commands.add((Command<?>)KieServices.Factory.get().getCommands().newFireAllRules());
        BatchExecutionCommand batchCommand = KieServices.Factory.get().getCommands().newBatchExecution(commands, "kssessionId");
        ServiceResponse<String> response = ruleServicesClient.executeCommands(KIE_CONTAINER_ID, batchCommand);
        System.out.println(response.getResult());
    }
}
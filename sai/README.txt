demo port 8180
demo-support 8181
shared h2 db 8182

Task 1 :Call activity with external task
Start process Weather-Check in WeatherCheck.bpmn through cokpit
child process 	view-forecast in printForecast.bpmn

Task 2 : Message throw catch
Start process 	printorder start in printorder_start.bpmn through postman

POST http://localhost:8180/CamundaWorkflow/rest/engine/default/message
{
  "messageName" : "OrderPacked",
  "businessKey" : "2",
  "processVariables" : {
    "orderid" : {"value" : "123", "type": "Long" }
  }
}

child process print order in printorder_Messagedelegate.bpmn

Task 3:  Spin out new process from external task
Start process ExternalChildTrigger in  externalChildTrigger.bpmn(parent) 
child process childProcess in childProcess.bpmn(child)


Phase Two
Task 1: Exclusive & inclusive trigger DMN
1a)exclusive trigger
Start process exclusive trigger in ExclusiveBPMNTrigger.bpmn
1b)inclusive trigger
Start process inclusive_trigger in  InclusiveBPMNTrigger.bpmns

Task 2: Three tier tasks interaction
Start process grandfather_demo in grandfather_demo.bpmn 
parent: parent_1.bpmn
child: grandchild_1.bpmn

Task 3: Message throw event without  java delegate
Start process trigger_throw_exp in trigger_throw.bpmn
child: printorder_Messagedelegate.bpmn

Phase 3
Task  1: BPMN_DMN demo
Start process dmn_Integration_Demo in BPMN_DMN_Demo.bpmn

Task 2: Camunda Logging 
Uncomment properties in related to logging application.properties 
Can  change log level DEBUG, TRACE

Task 3 
Set  email id & password in Keywords.java
Set email.demo=true in application.properties
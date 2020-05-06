microservice-parent port 8081
microservice-child port 8082

username :say
password: say

shared h2 db 8083

username :sa
password: password


Task 1 :Call activity with external task
Start process "Account-parent" from cockpit
Enter any of the pair of values
		Account Number		password
		12345				asdfgh
		23456				qwerty
		345678				zxcvbn

	if you make delay more than 20 seconds to provide the value into it It will be cancelled.
	Make sure the account number and its respective passwod is provided, otherwise it will be rejected.
After providing the right value it will take you to the Account-child automatically.

Task-2 Message throw catch
Start process  parentmessage from cockpit providing a buisness key like 1,2 etc. and a variable "question" taking String value as anything "What is your name?"
In the user-task if you know the question select "yes" it will answerable from that process
otherwise it will automatically call "Ask-Google" where Google will answerable.

Task-3 

Call two topics with two subscription in a same application( Spin out new process from external task also included)

Select add-numbers
provide any 2 numbers
in the first external-task it will add the both
and in the second external-task it will multiply with 5 

Phase-2

Task 2 : Three tier tasks interaction
->There will be two grand parent tasks; one which sets variables  and other which prints variables
->The first grandparent will have a parent task and a child task.
->Each tier will  set a variable which will  be printed by  grandparent 2.


Start Dash-Board from tasklist.
It will automatically take you to the Login and Register Page where u can select any one of this.

Task-3
Signal Throw catch
 
Start Parent-signal from tasklist and enter a value
It will redirect to the child process receive-signal 


************Presentation**************

-> Go to Microservice-parent and run the application at localhost:8081/CamundaWorkflow
-> start the process "enters into field"
->Choose the format
-> in Odi 
	1. choose runs >200 and wickets>3 and balls<200
	
	2.  choose runs <200 and wickets<=2 and balls[150..240]

1. Start the Process 'Parent-Process1' with a busisness key say 12
2. Start the Process 'Parent-Process2' with a busisness key starting with XYZ_"Parent-Process1 busisness key" like XYZ_12
3.	Parent-Process1 will first find any child process is already deployed or not 
	if not it will create a task
	if a task is already created the task can be edited
	
	if a user wants to loop the the process or edit he process he can do it by checking the loop-back task

4. In child process one has to perform the task	
5. Parent-Process2 will wait until the task is not performed in the child task
6. At last it will print all task completed in console
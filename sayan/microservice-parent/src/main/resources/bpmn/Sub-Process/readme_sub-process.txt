1. start the 'sub-process'
2. Enter Your Details( 3<Name<25(mandatory) , email(optional),mobile(mandatory),date of birth(optional) )
3. Review Data
4. if Sufficient it will go to next step, otherwise return back customer Page
5. Confirm payment
				a) if yes it will reach call-activity 'received-payment'
				b) if no itv  will discard and make a conditional start to retrn back customer page
				c) if no response found it will be automatically terminate the process after 10 seconds

6)in 'received-payment' if payment received by the site is 
				a) yes, it will send a signal to print an invoice and simultaneously send a notification that the order has been shipped
				b) no, it will send a user notification 'payment -failed'

7)if the invoice is not printed any-how it will send an escalasion event to trace the error
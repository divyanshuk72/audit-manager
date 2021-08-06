Audit Manager made using Spring Boot Microservices

A leading Supply chain Management Organization wants to automate the Audit processing, to make the management scalable and ensure clarity and ease of tracking.

Audit checklist module :

Audit checklist Module is a Middleware Microservice that performs following operations: 

· Provides a list of YES/NO type of questions for the audit based on the audit type 
· This will be consumed by the User interface the display the questions on the portal

Audit benchmark module :

Audit benchmark Module is a Middleware Microservice that performs the following operations: 

· Provides the acceptable number of answers with NO as the answer for various audit types

Audit severity module :

Audit severity Module is a Middleware Microservice that performs the following operations: 
· Gets the audit response and analyzes the project execution status 
. Gets the Audit benchmark detail from Microservice, compares the current project data. Determines the project execution status and the duration in which remedial action should be taken.

Authorization service :

This microservice is used with anonymous access to Generate JWT.

Audit management portal :

A Web Portal that allows a member to Login and allows to do following operations: 
· Login 
· Choose audit type and view audit questions 
· Provide response and view the project execution status 
· Store the Audit date, Audit type, project execution status and remediation duration in database

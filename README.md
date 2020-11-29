# CovidTestPlatform

Problem Statement:

This project deals with the confusion around the process to figure out how a person can reach to the nearest COVID-19 test center with availability.

Solution:

As a solution we are proposing to build an application where COVID-19 clinics can be registered as test center and the users(patients) can avail the service of the test center. Users will have to register, create their profile and will have the capability to search for the nearest COVID-19 test centers based on their zip code. They will be shown with the available time and date for the appointment. Also, after the test, results will be uploaded by the physician to a document store to which only patient will be having access to. Test result accessibility will be role based i.e. only physicians and the corresponding patient will be able to see his/her results. The patients will also be able to view their past test reports. 

There will be three actors involved:
Sysadmins – Who will be responsible for adding COVID-19 test centers into the platform.
Physicians – Who will be responsible for uploading the test results.
End Users – Who will have to create a profile and schedule an appointment with the nearest test center shown in the result based on their zip code.

Following are the capabilities that we are planning to have in our platform:

•	SSO based authentication for users
•	Active Directory Authentication for enterprise users (Admins and Physicians) with role-based permissions
•	Efficient data storage of medical documents in AWS S3 bucket
•	Notification integration (SMS/Email) using third party service 
•	Analytics around the test result data
•	Decouple notification as an async process using message queue (Kafka)
•	Distributed scheduling algorithm for scheduling appointments



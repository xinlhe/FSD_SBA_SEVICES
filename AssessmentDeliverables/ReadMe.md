Assessment Deliverables of FSD-SBA

topic : Stock Market Charting(FSD)v3.0

1. FrontEnd Source code

	git url : https://github.com/xinlhe/FSD_SBA_UI

2. Mid TierSource code of all Microservices
	
	git url : https://github.com/xinlhe/FSD_SBA_SERVICES
	
	2.1 name : FSD-FinalAssignment-MicroService-EurekaServer 
		port : 8761
	
	2.2 name : SBA_MicroService_UserClient
		port : 8081
	
	2.3 name : SBA_MicroService_CompanyClient
	    port : 8082
	
	2.4 name : SBA_MicroService_ExchangeClient
	    port : 8083
		
	2.5 name : SBA_MicroService_ZuulService
	    port : 8090
		
	2.6 name : SBA_MicroService_Ribbon
		port : 8084
	
	2.7 name : SBA_MicroService_UploadClient
		port : 8085
	
	2.8 name : SBA_MicroService_Tester
	
	2.9 name : SBA_MicroService_Model
	
3. Screen shots of Usage of Post Man tool to test each End Point of all Microservices
	
	git url : https://github.com/xinlhe/FSD_SBA_SERVICES/tree/master/AssessmentDeliverables/DeleveryScreenShots/PostMan

4. Few Steps on howto run the solution.
	
	mvn clean package

	mvn dockerfile:build

	docker run -d --name=FSD-FinalAssignment-MicroService-EurekaServer -p 8761:8761 FSD-FinalAssignment-MicroService-EurekaServer:latest

	docker run -d --name=SBA_MicroService_UserClient --link registry -p 8001:8001 SBA_MicroService_UserClient:latest

	docker run -d --name=SBA_MicroService_CompanyClient --link registry -p 8002:8002 SBA_MicroService_CompanyClient:latest
	
	docker run -d --name=SBA_MicroService_ExchangeClient --link registry -p 8003:8003 SBA_MicroService_ExchangeClient:latest
	
	docker run -d --name=SBA_MicroService_ZuulService --link registry -p 8090:8090 SBA_MicroService_ZuulService:latest
	
	docker run -d --name=SBA_MicroService_Ribbon --link registry -p 8084:8084 SBA_MicroService_Ribbon:latest
	
	docker run -d --name=SBA_MicroService_UploadClient --link registry -p 8085:8085 SBA_MicroService_UploadClient:latest

5. Test code of Angular and Mid Tier need to be included
	
	Run ng test to execute the frontend test

	Run mvn clean test to execute the backend test

6. Jmeter’s JMX file to test atleast one REST End point, and Screenshot of report
	
	JMeter's JMX file url : 
	
	JMeter's Screenshots url : 

<?xml version="1.0" encoding="UTF-8"?>
<#escape x as x?xml>
<#-- Used to generate a ShowTrainingCourse message as the result of a corresponding
	 GetTrainingCourse message. --> 
<ShowTrainingCourse releaseID="TG.9.5.1" xmlns="http://www.ventyx.com/IP/1" xmlns:oa="http://www.openapplications.org/oagis/9" 
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.ventyx.com/IP/1 ${IPPath}/1_0_0/BODs/ShowTrainingCourse.xsd">
	<oa:ApplicationArea>
      	  <oa:Sender>
      	  	<#-- Logical ID of the sending system -->
            <oa:LogicalID>${SystemID!}</oa:LogicalID>
            <#-- Task ID relating to what is being done -->
            <oa:TaskID>ShowTrainingCourse</oa:TaskID>
            <#-- Optional Reference ID for this request -->
            <oa:ReferenceID></oa:ReferenceID>
            <#-- Optional authorization ID -->
            <oa:AuthorizationID></oa:AuthorizationID>
          </oa:Sender>
          <oa:Receiver>
          	 <#-- Logical ID of the receiving system -->
             <oa:LogicalID><#if MessageHeader??>${MessageHeader.ServiceContext['FromSystemID']}</#if></oa:LogicalID>
          </oa:Receiver>
          <#-- Creation date and time of the message -->
          <oa:CreationDateTime>${PTIME.nowDate?string("yyyy-MM-dd'T'HH:mm:ss")}</oa:CreationDateTime>
          <#-- Globally Unique Identifier (GUID) that will make each Business Object Document instance uniquely identifiable -->
          <oa:BODID>${MessageUUID!}</oa:BODID>      
		  <oa:UserArea>
            <ServiceContext>
                <#--Name of the service -->
                <ServiceName>ShowTrainingCourse</ServiceName>
                <#-- Service version -->
                <ServiceVersion>1.0</ServiceVersion>
                <#-- Message Type or OAGIS Noun-->
                <MessageType>TrainingCourse</MessageType>
            </ServiceContext>
            <AuthorizationContext>
            </AuthorizationContext>
        </oa:UserArea>
	</oa:ApplicationArea>
	<DataArea>
		<oa:Show/>
		<#if trainingCourseResults??>
		<TrainingCourse>
			<TrainingCourseID>${trainingCourseResults.id!}</TrainingCourseID>
			<Name>${trainingCourseResults.name!}</Name>
			<Description>${trainingCourseResults.desc!}</Description>
			<Status>${trainingCourseResults.status!}</Status>
			<TrainingCourseType>${trainingCourseResults.type!}</TrainingCourseType>
			<TrainingCourseTypeDescription>${trainingCourseResults.typeDesc!}</TrainingCourseTypeDescription>
			<DeliveryMethod>${trainingCourseResults.deliveryMethod!}</DeliveryMethod>
			<DeliveryMethodDescription>${trainingCourseResults.deliveryMethodDesc!}</DeliveryMethodDescription>			
			<#if trainingCourseResults.requalInd?has_content>
			<RequalificationIndicator>${trainingCourseResults.requalInd!}</RequalificationIndicator>			
			<RequalificationPeriod>${trainingCourseResults.requalMonths!}</RequalificationPeriod>
			</#if>										
		</TrainingCourse>	
		</#if>
	</DataArea>
</ShowTrainingCourse>
</#escape>

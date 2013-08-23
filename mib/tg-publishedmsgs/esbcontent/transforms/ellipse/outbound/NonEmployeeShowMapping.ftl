<?xml version="1.0" encoding="UTF-8"?>
<#import "/transforms/common/CommonMarkupMacros.ftl" as utils>
<#escape x as x?xml>
<#-- This mapping is used when generating ShowPersonnel messages as a result of a GetPersonnel message for NonEmployee.  
     The contents of the message is populated based on the mappings below. -->
<ShowPersonnel xmlns="http://www.openapplications.org/oagis/9" xmlns:oa="http://www.openapplications.org/oagis/9" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" releaseID="TG.9.5.1">
	<oa:ApplicationArea>
      	  <oa:Sender>
      	  	<#-- Logical ID of the sending system -->
            <oa:LogicalID>${SystemID!}</oa:LogicalID>
            <#-- Task ID relating to what is being done -->
            <oa:TaskID>ShowPersonnel</oa:TaskID>
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
		  <oa:UserArea xmlns="http://www.ventyx.com/IP/1"
            xsi:schemaLocation="http://www.ventyx.com/IP/1 ${IPPath}/1_0_0/ApplicationAreaExtensions.xsd">
            <ServiceContext>
                <#--Name of the service -->
                <ServiceName>ShowPersonnel</ServiceName>
                <#-- Service version -->
                <ServiceVersion>1.0</ServiceVersion>
                <#-- Message Type or OAGIS Noun-->
                <MessageType>Personnel</MessageType>
            </ServiceContext>
            <AuthorizationContext>
            </AuthorizationContext>
        </oa:UserArea>
	</oa:ApplicationArea>
	<oa:DataArea>
		<oa:Show/>
		<#if nonEmployeeResults??>
		<Personnel>
			<ID>${nonEmployeeResults.id!}</ID>
			<GivenName>${nonEmployeeResults.firstName!}</GivenName>	
			<MiddleName>${nonEmployeeResults.secondName!}</MiddleName>
			<FamilyName>${nonEmployeeResults.lastName!}</FamilyName>
			<#-- Work phone number -->
			<Communication>
				<UseCode>Work</UseCode>
				<DialNumber>${nonEmployeeResults.workPhone!}</DialNumber>
			</Communication> 
			<Status>
				<Description>${nonEmployeeResults.status!}</Description>
			</Status>
			<#-- Hired on date-->
			<EffectiveTimePeriod>
				<StartDateTime>${utils.strToDate(nonEmployeeResults.startDate!)}</StartDateTime>
				<EndDateTime>${utils.strToDate(nonEmployeeResults.endDate!)}</EndDateTime>
			</EffectiveTimePeriod>
			<oa:UserArea xmlns="http://www.ventyx.com/IP/Personnel/1" 
					  xsi:schemaLocation="http://www.ventyx.com/IP/Personnel/1 ${IPPath}/1_0_0/Nouns/PersonnelUserArea.xsd">
				<Email>${nonEmployeeResults.emailAddr!}</Email>
				<EmployeeType>N</EmployeeType>
				<SupplierCode>${nonEmployeeResults.supplierCode!}</SupplierCode>
				<SupplierName>${nonEmployeeResults.supplierName!}</SupplierName>
			</oa:UserArea>
		</Personnel>
		</#if>
	</oa:DataArea>
</ShowPersonnel>
</#escape>

<?xml version="1.0" encoding="UTF-8"?>
<#import "/transforms/common/CommonMarkupMacros.ftl" as utils>
<#escape x as x?xml>
<#-- This mapping is used when generating ShowPersonnel messages as a result of a GetPersonnel message for Employee.  
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
		<#if employeeResults??>
		<Personnel>
			<ID>${employeeResults.id!}</ID>
			<GivenName>${employeeResults.firstName!}</GivenName>	
			<FamilyName>${employeeResults.lastName!}</FamilyName>	
			<#-- Work phone number -->
			<Communication>
				<UseCode>Work</UseCode>
				<DialNumber>${employeeResults.workPhone!}</DialNumber>
			</Communication> 
			<Status>
				<Code>${employeeResults.statusCode!}</Code>
				<Description>${employeeResults.statusDesc!}</Description>
			</Status>	 	
			<#if employeeResults.primRepCode?has_content>
			<HROrganizationIDs>
      			<ID sequence="1" sequenceName='${employeeResults.orgDesc!}'>${employeeResults.primRepCode?substring(0,4)}</ID>
      			<ID sequence="2" sequenceName='${employeeResults.businessUnitDesc!}'>${employeeResults.primRepCode?substring(4,8)}</ID>
      			<ID sequence="3" sequenceName='${employeeResults.groupDesc!}'>${employeeResults.primRepCode?substring(8,12)}</ID>
      			<ID sequence="4" sequenceName='${employeeResults.branchDesc!}'>${employeeResults.primRepCode?substring(12,16)}</ID>
   			</HROrganizationIDs>
   			</#if>			
			<Facility>
				<Name>${employeeResults.locationId!}</Name>
				<Description>${employeeResults.locationDesc!}</Description>
			</Facility>
			<oa:UserArea xmlns="http://www.ventyx.com/IP/Personnel/1" 
					  xsi:schemaLocation="http://www.ventyx.com/IP/Personnel/1 ${IPPath}/1_0_0/Nouns/PersonnelUserArea.xsd">
				<#if employeeResults.startDate?has_content>
				<JobTitleEffectiveDate>${utils.strToDate(employeeResults.startDate!)}</JobTitleEffectiveDate>
				</#if>
				<Email>${employeeResults.emailAddr!}</Email>
				<EmployeeType>E</EmployeeType>	
				<PayrollEmployeeIndicator>${employeeResults.isPayrollInd()?string}</PayrollEmployeeIndicator>
				<StaffCategoryCode>${employeeResults.staffCategoryCode!}</StaffCategoryCode>		
				<StaffCategoryDescription>${employeeResults.staffCategoryDesc!}</StaffCategoryDescription>
				<#-- Employee Supervisor details -->
				<SuperiorPositionID>${employeeResults.superPositionId!}</SuperiorPositionID>
				<SuperiorTitle>${employeeResults.superPositionTitle!}</SuperiorTitle>				
				<#if employeeResults.endDate?has_content>
				<JobTitleEndDate>${utils.strToDate(employeeResults.endDate!)}</JobTitleEndDate>	
				</#if>						
				<Photo>${employeeResults.photoPath!}</Photo>
				<JobTitleDescription>${employeeResults.positionTitle!}</JobTitleDescription>
				<JobTitle>${employeeResults.positionId!}</JobTitle>				
			</oa:UserArea>
		</Personnel>
		</#if>
	</oa:DataArea>
</ShowPersonnel>
</#escape>



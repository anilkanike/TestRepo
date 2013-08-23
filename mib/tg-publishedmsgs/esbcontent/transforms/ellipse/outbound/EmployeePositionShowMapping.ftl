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
		<#if employeePositionResults??>
		<#list employeePositionResults as employee>
		<Personnel>
			<ID>${employee.id!}</ID>
			<GivenName>${employee.firstName!}</GivenName>	
			<FamilyName>${employee.lastName!}</FamilyName>	
			<#-- Work phone number -->
			<Communication>
				<UseCode>Work</UseCode>
				<DialNumber>${employee.workPhone!}</DialNumber>
			</Communication>  
			<Status>
				<Code>${employee.statusCode!}</Code>
				<Description>${employee.statusDesc!}</Description>
			</Status>		
			<#if employee.primRepCode?has_content>
			<HROrganizationIDs>
      			<ID sequence="1" sequenceName='${employee.orgDesc!}'>${employee.primRepCode?substring(0,4)}</ID>
      			<ID sequence="2" sequenceName='${employee.businessUnitDesc!}'>${employee.primRepCode?substring(4,8)}</ID>
      			<ID sequence="3" sequenceName='${employee.groupDesc!}'>${employee.primRepCode?substring(8,12)}</ID>
      			<ID sequence="4" sequenceName='${employee.branchDesc!}'>${employee.primRepCode?substring(12,16)}</ID>
   			</HROrganizationIDs>
   			</#if>				
			<Facility>
				<Name>${employee.locationId!}</Name>
				<Description>${employee.locationDesc!}</Description>
			</Facility>
			<oa:UserArea xmlns="http://www.ventyx.com/IP/Personnel/1" 
					  xsi:schemaLocation="http://www.ventyx.com/IP/Personnel/1 ${IPPath}/1_0_0/Nouns/PersonnelUserArea.xsd">
				<#if employee.startDate?has_content>
				<JobTitleEffectiveDate>${utils.strToDate(employee.startDate!)}</JobTitleEffectiveDate>
				</#if>
				<Email>${employee.emailAddr!}</Email>
				<EmployeeType>E</EmployeeType>	
				<PayrollEmployeeIndicator>${employee.isPayrollInd()?string}</PayrollEmployeeIndicator>
				<StaffCategoryCode>${employee.staffCategoryCode!}</StaffCategoryCode>		
				<StaffCategoryDescription>${employee.staffCategoryDesc!}</StaffCategoryDescription>
				<#-- Employee Supervisor details -->
				<SuperiorPositionID>${employee.superPositionId!}</SuperiorPositionID>
				<SuperiorTitle>${employee.superPositionTitle!}</SuperiorTitle>				
				<#if employee.endDate?has_content>
				<JobTitleEndDate>${utils.strToDate(employee.endDate!)}</JobTitleEndDate>	
				</#if>						
				<Photo>${employee.photoPath!}</Photo>
				<JobTitleDescription>${employee.positionTitle!}</JobTitleDescription>
				<JobTitle>${employee.positionId!}</JobTitle>				
			</oa:UserArea>
		</Personnel>
		</#list>
		</#if>
	</oa:DataArea>
</ShowPersonnel>
</#escape>


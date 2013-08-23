<?xml version="1.0" encoding="UTF-8"?>
<#import "/transforms/common/CommonMarkupMacros.ftl" as utils>
<#escape x as x?xml>
<GetPersonnelLeave xmlns:oa="http://www.openapplications.org/oagis/9" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<oa:ApplicationArea>				
		<oa:Sender>
			<oa:LogicalID>GetPersonnelLeave</oa:LogicalID>
			<oa:TaskID>ShowPersonnelLeave</oa:TaskID>
			<oa:ReferenceID>PublishPersonnelLeave</oa:ReferenceID>
		</oa:Sender>
		<oa:CreationDateTime>${PTIME.nowDate?string("yyyy-MM-dd'T'HH:mm:ss")}</oa:CreationDateTime>
		<oa:BODID>${MessageUUID!}</oa:BODID>
		<oa:UserArea xmlns="http://www.ventyx.com/IP/1">
            <ServiceContext>
                <#--Name of the service -->
                <ServiceName>GetPersonnelLeave</ServiceName>
                <#-- Service version -->
                <ServiceVersion>1.0</ServiceVersion>
                <#-- Message Type or OAGIS Noun-->
                <MessageType>PersonnelLeave</MessageType>
            </ServiceContext>
            <AuthorizationContext>
            	<OrgID>${DefaultDistrict}</OrgID>
            	<Proxy>
            		<UserID>${UserID}</UserID>
            		<Role>${PositionID}</Role>		            		
            	</Proxy>		            	
            </AuthorizationContext>
        </oa:UserArea>	
	</oa:ApplicationArea>
	<oa:DataArea>
		<oa:Get/>
		<PersonnelLeave>
			<EmployeeID>${.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].primaryKey.employeeId}</EmployeeID>
			<LeaveType>${.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].primaryKey.leaveType}</LeaveType>
			<LeaveReason>${.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].primaryKey.lveReason}</LeaveReason>
			<LeaveApprovalStatus>${.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].primaryKey.lveApprStatus}</LeaveApprovalStatus>
			<LeaveStartDate>${utils.strToDate(.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].primaryKey.lveStDate!)}</LeaveStartDate>
			<LeaveStartTime>${.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].lveStartTime}</LeaveStartTime>
			<LeaveEndDate>${utils.strToDate(.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].lveEndDate)}</LeaveEndDate>
			<LeaveEndTime>${.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].lveStopTime}</LeaveEndTime>
			<RequestCreatedDate>${utils.strToDate(.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].createdDate)}</RequestCreatedDate>
			<RequestCreatedTime>${.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].createdTime__9}</RequestCreatedTime>
			<RequestCreatedBy>${.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].createdEmpId?trim}</RequestCreatedBy>
			<RequestApprovedDate>${utils.strToDate(.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].approvedDate)}</RequestApprovedDate>
			<RequestApprovedTime>${.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].approvedTime__9}</RequestApprovedTime>
			<RequestApprovedBy>${.vars["com.mincom.ellipse.edoi.ejb.msf888.MSF888Rec"].approvedEmpId?trim}</RequestApprovedBy>
		</PersonnelLeave>				
	</oa:DataArea>
</GetPersonnelLeave>	
</#escape>	

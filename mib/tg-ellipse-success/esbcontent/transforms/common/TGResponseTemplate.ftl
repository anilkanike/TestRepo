<#if OAGISVerb?? && OAGISVerb?length &gt; 0><?xml version="1.0" encoding="UTF-8"?>
<#-- Generates the default response based on the OAGIS verb-->
<#if OAGISVerb == "Get">
<#-- Generates the Show response for a Get -->
<Show${OAGISNoun} releaseID="9.5.1"
	xmlns="http://www.openapplications.org/oagis/9"
	xmlns:oa="http://www.openapplications.org/oagis/9"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <ApplicationArea>      
      <Sender>
        <LogicalID>${SystemID!}</LogicalID>
        <TaskID>Show${OAGISNoun}</TaskID>
        <ReferenceID></ReferenceID>
        <AuthorizationID></AuthorizationID>
      </Sender>
      <CreationDateTime>${PTIME.nowDate?string("yyyy-MM-dd'T'HH:mm:ss")}</CreationDateTime>
      <BODID>${MessageUUID!}</BODID>
      <@genUserArea/>            
   </ApplicationArea>
   <DataArea>
      <Show>
      </Show>
   </DataArea>       
</Show${OAGISNoun}>
<#elseif OAGISVerb == "Process">
<#-- Generates the Acknowledge response for a Process -->
<Acknowledge${OAGISNoun} releaseID="TG.1.0.0"
    xmlns="http://www.ventyx.com/IP/1" 
	xmlns:oa="http://www.openapplications.org/oagis/9" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://www.ventyx.com/IP/1 ../IP/1_0_0/BODs/Acknowledge${OAGISNoun}.xsd">
   <oa:ApplicationArea>
      <oa:Sender>
        <oa:LogicalID>${SystemID!}</oa:LogicalID>
        <oa:TaskID>Acknowledge${OAGISNoun}</oa:TaskID>
        <oa:ReferenceID></oa:ReferenceID>
        <oa:AuthorizationID></oa:AuthorizationID>
      </oa:Sender>
      <oa:CreationDateTime>${PTIME.nowDate?string("yyyy-MM-dd'T'HH:mm:ss")}</oa:CreationDateTime>
      <oa:BODID>${MessageUUID!}</oa:BODID>
      <@genUserArea/>                     
   </oa:ApplicationArea>
   <DataArea>
      <oa:Acknowledge>
      </oa:Acknowledge>
	  <${OAGISNoun}/>
   </DataArea>       
</Acknowledge${OAGISNoun}>
<#elseif OAGISVerb == "Post">
<#-- Generates the Acknowledge response for a Post -->
<Acknowledge${OAGISNoun} releaseID="9.5.1"
    xmlns="http://www.openapplications.org/oagis/9"
    xmlns:oa="http://www.openapplications.org/oagis/9"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <ApplicationArea>
      <Sender>
        <LogicalID>${SystemID!}</LogicalID>
        <TaskID>Acknowledge${OAGISNoun}</TaskID>
        <ReferenceID></ReferenceID>
        <AuthorizationID></AuthorizationID>
      </Sender>
      <CreationDateTime>${PTIME.nowDate?string("yyyy-MM-dd'T'HH:mm:ss")}</CreationDateTime>
      <BODID>${MessageUUID!}</BODID>
      <@genUserArea/>                     
   </ApplicationArea>
   <DataArea>
      <Acknowledge>
      </Acknowledge>
   </DataArea>       
</Acknowledge${OAGISNoun}>      
<#elseif OAGISVerb == "Update">
<#-- Generates the Respond response for an Update -->
<Respond${OAGISNoun} releaseID="9.5.1"
    xmlns="http://www.openapplications.org/oagis/9"
    xmlns:oa="http://www.openapplications.org/oagis/9"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <ApplicationArea>
      <Sender>
        <LogicalID>${SystemID!}</LogicalID>
        <TaskID>Respond${OAGISNoun}</TaskID>
        <ReferenceID></ReferenceID>
        <AuthorizationID></AuthorizationID>
      </Sender>
      <CreationDateTime>${PTIME.nowDate?string("yyyy-MM-dd'T'HH:mm:ss")}</CreationDateTime>
      <BODID>${MessageUUID!}</BODID>
      <@genUserArea/>            
   </ApplicationArea>
   <DataArea>
      <Respond>
      </Respond>
   </DataArea>       
</Respond${OAGISNoun}>
<#elseif OAGISVerb == "Sync">
<#-- Generates the Confirm response for a Sync -->
<ConfirmBOD releaseID="9.5.1"
    xmlns="http://www.openapplications.org/oagis/9"
    xmlns:oa="http://www.openapplications.org/oagis/9"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <ApplicationArea>
      <Sender>
        <LogicalID>${SystemID!}</LogicalID>
        <TaskID>ConfirmBOD</TaskID>
        <ReferenceID></ReferenceID>
        <AuthorizationID></AuthorizationID>
      </Sender>
      <CreationDateTime>${PTIME.nowDate?string("yyyy-MM-dd'T'HH:mm:ss")}</CreationDateTime>
      <BODID>${MessageUUID!}</BODID>
      <@genUserArea/>                  
   </ApplicationArea>
   <DataArea>
      <Confirm>
      </Confirm>
      <BOD/>
   </DataArea>
</ConfirmBOD>
<#else>
<#-- Generates the Confirm as a default -->
<ConfirmBOD releaseID="9.5.1"
    xmlns="http://www.openapplications.org/oagis/9"
    xmlns:oa="http://www.openapplications.org/oagis/9"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <ApplicationArea>
      <Sender>
        <LogicalID>${SystemID!}</LogicalID>
        <TaskID>ConfirmBOD</TaskID>
        <ReferenceID></ReferenceID>
        <AuthorizationID></AuthorizationID>
      </Sender>
      <CreationDateTime>${PTIME.nowDate?string("yyyy-MM-dd'T'HH:mm:ss")}</CreationDateTime>
      <BODID>${MessageUUID!}</BODID>
      <@genUserArea/>                     
   </ApplicationArea>
   <DataArea>
      <!-- Unknown verb ${OAGISVerb} -->
      <Confirm>
      </Confirm>
      <BOD/>
   </DataArea>       
</ConfirmBOD>
</#if>
</#if>
<#macro genUserArea>
<#if ServiceContext??>
   <oa:UserArea xmlns:ip="http://www.ventyx.com/IP/1">
      <ip:ServiceContext>
         <ip:ServiceName>${ServiceContext.ServiceName!}</ip:ServiceName>
         <ip:ServiceVersion>${ServiceContext.ServiceVersion!}</ip:ServiceVersion>
         <ip:MessageType>${ServiceContext.MessageType!}</ip:MessageType>
      </ip:ServiceContext>
      <ip:AuthorizationContext>
      </ip:AuthorizationContext>
   </oa:UserArea>
</#if>
</#macro>
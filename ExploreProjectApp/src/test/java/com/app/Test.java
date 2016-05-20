package com.app;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.*;

public class Test {
  static String temp1 = "{'status':100,'recoverableViolation':false,'modelJson':{'initiativeId':500401,'violationsPresent':false,'processTypeCode':7429,'processId':536090663,'step':1,'serviceRequestLogId':536090661,'processedByTypeRef':'T_HEALTH_CARE_PROVIDER','processedByRefId':536090662,'keepProcessOpen':false,'channelCode':380,'moreInfoPresent':false,'dataInputMode':380,'triggerCorrespondence':true,'typeRef':'T_HEALTH_CARE_PROVIDER','refId':536090662,'enrollmentId':536090696,'enrollmentIdentifier':'PAT5360906695','enrollmentStatusCode':5019,'enrolled':true,'formattedStatusDate':'05/09/2016','signaturePresent':false,'kaPresent':false,'kaCompleted':false,'kaSuspended':false,'patient':{'initiativeId':500401,'violationsPresent':false,'typeCode':5800,'createUser':'eizvvm0','id':536090662,'firstName':'testformyself','lastName':'v','genderCode':22,'birthDate':'05/09/2016','raceCode':65,'passedDuplicateCheck':false,'residentialAddresses':[{'initiativeId':500401,'violationsPresent':false,'typeCode':5103,'createUser':'eizvvm0','id':536090689,'typeRef':'T_PATIENT','refId':536090662,'zipCode':'41365','countryCode':18,'statusCode':11,'messagesPresent':false}],'messagesPresent':false},'hcpId':513358907,'stakeholderTypeRef':'T_HEALTH_CARE_PROVIDER','treatmentStatus':{'violationsPresent':false,'type':'','typeCode':7468,'typeRef':'T_PATIENT','serviceParentRefId':513358907,'serviceParentTypeRef':'T_HEALTH_CARE_PROVIDER','eventStatusHistories':[{'violationsPresent':false,'statusCode':7433,'status':'','messagesPresent':false}],'messagesPresent':false},'monitoringFrequency':{'violationsPresent':false,'type':'','typeCode':7529,'typeRef':'T_PATIENT','initiatorTypeRef':'T_HEALTH_CARE_PROVIDER','initiatorRefId':513358907,'frequencyCode':6496,'frequency':'','messagesPresent':false},'registrationAttestation':{'violationsPresent':false,'messagesPresent':false},'hcpInfo':{'initiativeId':500401,'violationsPresent':false,'type':'Health Care Participant','typeCode':7528,'createUser':'webuser','lastUpdateUser':'e578k1p','id':513358907,'firstName':'Bonnie','lastName':'Chase TX','professionalCredentialCode':3609,'professionalCredential':'M.D.','preferredContactMethodCode':57,'preferredContactMethod':'Email','passedDuplicateCheck':false,'certificationIncompleteViolation':false,'navigateToTranstionPrescriberProcess':false,'userRoleName':'Prescriber','webUsername':'BCHASE','userGroupCode':7602,'emails':[{'initiativeId':500401,'violationsPresent':false,'createUser':'e578k1p','lastUpdateUser':'webuser','id':535344662,'typeRef':'T_HEALTH_CARE_PROVIDER','refId':513358907,'address':'bonnie.chase@yahoo.com','messagesPresent':false}],'businessPhones':[{'initiativeId':500401,'violationsPresent':false,'type':'Business','typeCode':53,'createUser':'e578k1p','id':535839952,'typeRef':'T_HEALTH_CARE_PROVIDER','refId':513358907,'countryCode':18,'country':'USA','number':'3450556666','ext':'456444','primary':true,'messagesPresent':false}],'faxes':[{'initiativeId':500401,'violationsPresent':false,'type':'Fax','typeCode':56,'createUser':'webuser','id':513358946,'typeRef':'T_HEALTH_CARE_PROVIDER','refId':513358907,'countryCode':18,'country':'USA','number':'7883787834','primary':true,'messagesPresent':false}],'deas':[{'initiativeId':500401,'violationsPresent':false,'type':'DEA Number','typeCode':5190,'createUser':'webuser','lastUpdateUser':'e578k1p','id':513358951,'typeRef':'T_HEALTH_CARE_PROVIDER','refId':513358907,'value':'SC4545656','primary':true,'valid':true,'validateOverrideDate':'11/13/2015','markedForRemoval':false,'messagesPresent':false}],'npis':[{'initiativeId':500401,'violationsPresent':false,'type':'NPI - National Provider Identifier','typeCode':5010,'createUser':'webuser','lastUpdateUser':'e578k1p','id':513358952,'typeRef':'T_HEALTH_CARE_PROVIDER','refId':513358907,'value':'9753256787','primary':true,'valid':true,'validateOverrideDate':'11/13/2015','markedForRemoval':false,'messagesPresent':false}],'messagesPresent':false},'hcpName':'Bonnie Chase TX','terminallyIllCheckBox':false,'benAttestationCheckBox':false,'designeeTreatmentRationaleNotProvided':false,'designeeHospiceCheckBox':false,'monthlyMonitoringFrequency':false,'missingAttestationSignature':false,'missingTreatmentRationale':false,'monitoringFrequencyNotSuggestedOne':false,'sendMissingBenAttestationCorrespondence':false,'hideLabIntakeStep':false,'messagesPresent':false},'serviceRequestLogId':536090661,'errorsPresent':false,'responseSuccessful':true}";
  public static final String BRACKET = "] - ";
  public static void main(String[] args) {
    System.out.println(BRACKET.length());
    for (int cp = 32; cp < 48; ++cp) {
      //System.out.printf("%c : %s%n", cp, Character.getName(cp));
    }
    
    String test = "abc from def";
    
    for (String string : test.split("from")) {
      //System.out.println(string);
    }

    for (String string2 : StringUtils.splitByWholeSeparator(test, "from")) {
      //System.out.println(string2);
    }
    //System.out.println(toPrettyFormat(temp));
  }
  
  public static String toPrettyFormat(String jsonString) 
  {
    
      JsonParser parser = new JsonParser();
      JsonObject json = parser.parse(jsonString).getAsJsonObject();

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String prettyJson = gson.toJson(json);

      return prettyJson;
  }
}

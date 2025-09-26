import groovy.xml.* 
import groovy.json.JsonSlurper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import groovy.util.Node
//import groovy.transform.Field 

//@Field 
//String nodeValue = "" 

def xmlFile = new File('payload3.xml')
def inputXml = xmlFile.getText('UTF-8')
def ServiceResponse = new XmlSlurper().parseText(inputXml)

def writer = new StringWriter()
def builder = new MarkupBuilder(writer)
def nodeValue = ""

def get_currentOrderItem_function = ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem -> }
    

	def prescrit_function = { ->
        println("__1__-------- prescrit_function debuger START --------__1__")
		def kip_bendex_did = ServiceResponse.Orders.Order.Did
		def kip_bendex_customer_number = ServiceResponse.Orders.Order.BACompany3
		def kip_bendex_business_unit = ServiceResponse.Orders.Order.BACompany2
		def kip_bendex_description = ServiceResponse.Orders.Order.Description.text()
		
		kip_bendex_description = kip_bendex_description.replace('/', '')
		kip_bendex_description = kip_bendex_description.replace('\\', '')
		kip_bendex_description = kip_bendex_description.replace('~', '')
		kip_bendex_description = kip_bendex_description.replace('%', '')
		kip_bendex_description = kip_bendex_description.replace('*', '')
		kip_bendex_description = kip_bendex_description.replace(':', '')
		kip_bendex_description = kip_bendex_description.replace('\r\n', '')
		kip_bendex_description = kip_bendex_description.replace('\r', '')
		kip_bendex_description = kip_bendex_description.replace('\n', '')
		
		def kip_bendex_baCompany = ServiceResponse.Orders.Order.BACompany
		def kip_bendex_createdBy = ServiceResponse.Orders.Order.CreatedBy


		def incoming_date = ServiceResponse.Orders.Order.CreatedOn.text()

		def cleaned = incoming_date.replace("T", " ").replace("Z", "")
		

		def inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		def outputFormat = new SimpleDateFormat("yyyy-MM-dd")
		
		def parsedDate = inputFormat.parse(cleaned)
		def kip_bendex_creation_date = outputFormat.format(parsedDate)
		




		def kip_bendex_response_result = ServiceResponse.Result
		def kip_bendex_response_details = ServiceResponse.Details
		def kip_bendex_version = ServiceResponse.Version
		def kip_jb_positionStringFlag = 'THEREISNOPOS888'
		def kip_bendex_fast_track = 'N'
        //println "Working dir: " + new File(".").absolutePath
        println("prescrit_function execution is finished")
        println("RETURN:")
        println("kip_bendex_did --- " + kip_bendex_did)
        println(" kip_bendex_customer_number--- " + kip_bendex_customer_number)
        println(" kip_bendex_business_unit--- " + kip_bendex_business_unit)
        println(" kip_bendex_description--- " + kip_bendex_description)
        println(" kip_bendex_baCompany--- " + kip_bendex_baCompany)
        println(" kip_bendex_createdBy--- " + kip_bendex_createdBy)



        println("\r\n!__1__!-------- prescrit_function debuger END --------!__1__!")
		return[
			kip_bendex_did: kip_bendex_did,
			kip_bendex_customer_number: kip_bendex_customer_number,
			kip_bendex_business_unit: kip_bendex_business_unit,
			kip_bendex_description: kip_bendex_description,
			kip_bendex_baCompany: kip_bendex_baCompany,
			kip_bendex_createdBy: kip_bendex_createdBy,
			kip_bendex_creation_date: kip_bendex_creation_date,
			kip_bendex_response_result: kip_bendex_response_result,
			kip_bendex_response_details: kip_bendex_response_details,
			kip_bendex_version: kip_bendex_version,
			kip_jb_positionStringFlag: kip_jb_positionStringFlag,
			kip_bendex_fast_track: kip_bendex_fast_track
		]

	}

	def prescript = prescrit_function()




    def dicts_maker_function = { ->
        println("\r\n\r\n\r\n__2__-------- dicts_maker_function debuger START --------__2__")

        def prj_SapCodes_array_JSON_File = new File('prj_SapCodes_array.json').text
        def prj_SapCodes_array = new JsonSlurper().parseText(prj_SapCodes_array_JSON_File)

        def prj_SapCodesArray_FL_array = new File('prj_SapCodes_FL_array.json').text
        def prj_SapCodes_FL_array = new JsonSlurper().parseText(prj_SapCodesArray_FL_array)

        def prj_coil_metal_ext_array_JSON_File = new File('prj_coil_metal_ext_array.json').text
        def prj_coil_metal_ext_array = new JsonSlurper().parseText(prj_coil_metal_ext_array_JSON_File)

        def prj_coil_finish_ext_array_JSON_File = new File('prj_coil_finish_ext_array.json').text
        def prj_coil_finish_ext_array = new JsonSlurper().parseText(prj_coil_finish_ext_array_JSON_File)

        def prj_coil_guige_ext_array_JSON_File = new File('prj_coil_guige_ext_array.json').text
        def prj_coil_guige_ext_array = new JsonSlurper().parseText(prj_coil_guige_ext_array_JSON_File)
        
        def prj_coil_colour_ext_array_JSON_File = new File('prj_coil_colour_ext_array.json').text
        def prj_coil_colour_ext_array = new JsonSlurper().parseText(prj_coil_colour_ext_array_JSON_File)

        def coverWidth2Dict_JSON_File = new File('coverWidth2Dict.json').text
        def coverWidth2Dict = new JsonSlurper().parseText(coverWidth2Dict_JSON_File)

        def front_colour_colour_JSON_File = new File('front_colour.colour.json').text
        def front_colour_colour = new JsonSlurper().parseText(front_colour_colour_JSON_File)

        def front_colour_type_JSON_File = new File('front_colour.type.json').text
        def front_colour_type = new JsonSlurper().parseText(front_colour_type_JSON_File)

        def front_colour_colour_IE_array_JSON_File = new File('front_colour.colour_IE.json').text
        def front_colour_colour_IE_array = new JsonSlurper().parseText(front_colour_colour_IE_array_JSON_File)

        def front_colour_type_IE_array_JSON_File = new File('front_colour.type_IE.json').text
        def front_colour_type_IE = new JsonSlurper().parseText(front_colour_type_IE_array_JSON_File)

        def kipBendexPrjArrayDET = new File('kipBendexPrjArrayDET.txt').text
        def kipBendexArrayDET = kipBendexPrjArrayDET.split(',').collect { it.trim() }

        def kipBendexPrjArrayCNF = new File('kipBendexPrjArrayCNF.txt').text
        def kipBendexArrayCNF = kipBendexPrjArrayCNF.split(',').collect { it.trim() }

        //def prj_SapCodesArray_FL_array = new File('kipBendexPrjArrayCNF.txt').text
        //def prj_SapCodesArray_FL_array = new File('prj_SapCodes_FL_array.json').text
        //def prj_SapCodes_FL_array = prj_SapCodesArray_FL_array.split(',').collect { it.trim() }
        //println(prj_SapCodes_FL_array)
        println("__2__-------- dicts_maker_function debuger END --------__2__")
 
        return [
        prj_SapCodes_array:prj_SapCodes_array, 
        prj_coil_metal_ext_array:prj_coil_metal_ext_array,
        prj_coil_finish_ext_array:prj_coil_finish_ext_array,
        prj_coil_guige_ext_array:prj_coil_guige_ext_array,
        prj_coil_colour_ext_array:prj_coil_colour_ext_array,
        coverWidth2Dict:coverWidth2Dict,
        front_colour_colour:front_colour_colour,
        front_colour_type:front_colour_type,
        front_colour_colour_IE_array:front_colour_colour_IE_array,
        front_colour_type_IE:front_colour_type_IE,
        kipBendexArrayDET:kipBendexArrayDET,
        kipBendexArrayCNF:kipBendexArrayCNF,
		prj_SapCodes_FL_array: prj_SapCodes_FL_array
        ]
    }

	def dicts_list = dicts_maker_function()




 
def del_date_function = { currentOrder->
    println("\r\n\r\n\r\n__3__-------- del_date_function debuger START --------__3__")
    def incomingDate = currentOrder.DeliveryDate.text()
	def date = LocalDateTime.parse(incomingDate)
	def formatedDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"))	
    println("formatedDate --- " + formatedDate)
    println("__3__-------- del_date_function debuger END --------__3__")
    return formatedDate
}




def order_type_function = { currentOrderItem ->
    println("\r\n\r\n\r\n__4__-------- order_type_function debuger START --------__4__")
    def orderType = ""

    // ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
        def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

        def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
        def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
        def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }
                        
        def i = 0
                        
        def isOrderTypePresented = false
        def orderType_value = ""
                        
        if (isCustomPropertiesPresented != 0){
            while(i < countItems_CustomProperties){
                if(items_CustomProperties[i]?.@name?.text() == "ORDER_TYPE"){
                    isOrderTypePresented = true
                    orderType_value = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'ORDER_TYPE' }?.text()
                }
                i++
            } 

            if(isOrderTypePresented){
                orderType_value = orderType_value
            } else {
                orderType_value = "ZKCO"
            }


        } else {
            if(isTemplateParametersPresented != 0){

                while(i < countItems_TemplateParameters){
                    if(items_TemplateParameters[i]?.@name?.text() == "ORDER_TYPE"){
                        isOrderTypePresented = true
                        orderType_value = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'ORDER_TYPE' }?.text()						
                    }
                    i++
                }



                if(isOrderTypePresented){
                    orderType_value = orderType_value
                } else {
                    orderType_value = "ZKCO"
                }

            } else {
                orderType_value = "ZKCO"

            }
            
        }

        orderType = orderType_value
        println("__4__-------- order_type_function debuger END --------__4__")
    // }

    return orderType
}




def sales_org_function = { currentOrderItem ->
    println("\r\n\r\n\r\n__5__-------- sales_org_function debuger START --------__5__")
    def salesOrg = ""
    // ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
        def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

        def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
        def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
        def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }

        def isSalesOrgPresented = false					
        def sales_org_value = ""
        def i = 0


        if(isCustomPropertiesPresented != 0){
            if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                sales_org_value = "IE10" 
            } else {
                while(i < countItems_CustomProperties){
                    if(items_CustomProperties[i]?.@name?.text() == "SALES_ORG"){
                        isSalesOrgPresented = true
                    }
                    i++	
                }


                if(isSalesOrgPresented){
                    sales_org_value = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'SALES_ORG' }?.text()
                } else {
                    sales_org_value = "GB40"
                }
            }
        } else {
            if(isTemplateParametersPresented != 0){ 
                if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                    sales_org_value = "IE10"
                } else {
                    while(i < countItems_TemplateParameters){
                        if(items_TemplateParameters[i]?.@name?.text() == "SALES_ORG"){
                            isSalesOrgPresented = true
                        }
                        i++
                    } 

                    if(isSalesOrgPresented){
                        sales_org_value = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'SALES_ORG' }?.text()
                    } else {
                        sales_org_value = "GB40"
                    }
                }
            } else {
                if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                    sales_org_value = "IE10"
                } else {
                    sales_org_value = "GB40"
                }
            }
        }
        salesOrg = sales_org_value
        println("__5__-------- sales_org_function debuger END --------__5__")
    // }

    return salesOrg
}




def distribution_channel_function = { currentOrderItem ->
    println("\r\n\r\n\r\n__6__-------- distribution_channel_function debuger START --------__6__")
    def distribution_channel_value = ""


    // ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
		def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
		def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
		def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

		def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
		def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
		def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }

		def isDistributionChannelPresented = false
		def distribution_channel = ""
		def i = 0

		if(isCustomPropertiesPresented != 0){
			
			if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
				
				distribution_channel = "I1" 
			} else {
				while(i < countItems_CustomProperties){
					if(items_CustomProperties[i]?.@name?.text() == "DISTRIBUTION_CHANNEL"){
						isDistributionChannelPresented = true
					}
					i++
				}

				if(isDistributionChannelPresented){
					distribution_channel = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'DISTRIBUTION_CHANNEL' }?.text()
				} else {
					distribution_channel = "G4"
				}
			}
		} else{
			if(isTemplateParametersPresented != 0){
				
				if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
					distribution_channel = "I1"
					
				} else {	
					while(i < countItems_TemplateParameters){
						if(items_TemplateParameters[i]?.@name?.text() == "DISTRIBUTION_CHANNEL"){
							isDistributionChannelPresented = true
						}
						i++

						if(isDistributionChannelPresented){
							distribution_channel = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'DISTRIBUTION_CHANNEL' }?.text()
						} else {
							distribution_channel = "G4"
						}
					}
				}
			} else {
				
				if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
					distribution_channel = "I1"
				} else {
					distribution_channel = "G4"
				}
			}
		}
		distribution_channel_value = distribution_channel
        println("__6__-------- distribution_channel_function debuger END --------__6__")
		
	// }
    return distribution_channel_value
}




def division_function = { currentOrderItem ->
    println("\r\n\r\n\r\n__7__-------- division_function debuger START --------__7__")
    def division_value = ""

    // ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
        def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

        def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
        def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
        def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }

        def isDivisionPresented = false
        def division = ""
        def i = 0

        if(isCustomPropertiesPresented != 0){
            if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                division = "K1"
            } else {
                while(i < countItems_CustomProperties){
                    if(items_CustomProperties[i]?.@name?.text() == "DIVISION"){
                        isDivisionPresented = true
                    }
                    i++
                }

                if(isDivisionPresented){
                    division = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'DIVISION' }?.text()
                } else {
                    division = "15"
                }
            }
        } else {
            if(isTemplateParametersPresented != 0){
                if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                    division = "K1"
                } else {
                    while(i < countItems_TemplateParameters){
                        if(items_TemplateParameters[i]?.@name?.text() == "DIVISION"){
                            isDivisionPresented = true
                        }
                        i++
                    }
                    
                    if(isDivisionPresented){
                        division = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'DIVISION' }?.text()
                    } else {
                        division = "15"
                    }
                }


            } else {
                if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                    division = "K1"
                } else {
                    division = "15"
                }
            }
        }
        division_value = division
        println("__7__-------- division_function debuger END --------__7__")
    // }

    return division_value
}



// due to here we need to pass the node value and create the node it self insted original node dummy_ship
// the xml builder it self should be passed into the function as dummy_ship_function_builder paameter
def dummy_ship_function = { dummy_ship_function_builder, currentOrderItem -> 
    println("\r\n\r\n\r\n__8__-------- dummy_ship_function debuger START --------__8__")
    def dummy_ship_value = ""
    def dummy_shiping = ""

        if(currentOrderItem.count { it.ShoppingItemType.text() ==~ /.*Shipping.*/ } > 0){
            dummy_shiping = currentOrderItem.ShoppingItemType.text()
        } else {
            dummy_shiping = ""
        }
        dummy_ship_value = dummy_shiping
    println("__8__-------- dummy_ship_function debuger END --------__8__")
    if(dummy_ship_value.length() != 0){
        dummy_ship_function_builder."ShoppingItemType"(dummy_ship_value)
    }
    
}




def material_code__function = { kipBendexArrayDET_parameter, kipBendexArrayCNF_parameter, prj_SapCodes_array_parameter, prj_SapCodes_FL_array_parameter, currentOrderItem ->
    println("\r\n\r\n\r\n__9__-------- material_code__function debuger START --------__9__")
    def dummy_ship_value = ""
    def dummy_shiping = ""

    def kipBendexArrayDET = kipBendexArrayDET_parameter
	def kipBendexArrayCNF = kipBendexArrayCNF_parameter
	def prj_SapCodes_array = prj_SapCodes_array_parameter
    def prj_SapCodes_FL_array = prj_SapCodes_FL_array_parameter
    
    ////!def nodeValue = null

    //ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
		def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
		def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
		def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

		def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
		def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
		def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }

        println("countItems_CustomProperties ---- " + countItems_CustomProperties)
        println("countItems_TemplateParameters ---- " + countItems_TemplateParameters)
		
		def kipBendexDistributionListValue = ""
		def sapcode = ""
		def kipJbBSChecker = null
		def codes_checker = 0
		def bends = ""

			

		if(countItems_TemplateParameters > 1 | countItems_CustomProperties > 1){

			kipBendexDistributionListValue = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'DISTRIBUTION LIST' }?.text()
            println("1 kipBendexDistributionListValue --- " + kipBendexDistributionListValue)


			if(kipBendexDistributionListValue == null){
                
				kipBendexDistributionListValue = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'DISTRIBUTION LIST' }?.text()
			}

            println("2 kipBendexDistributionListValue --- " + kipBendexDistributionListValue)
			if(kipBendexDistributionListValue == "Facades"){
                
				nodeValue = 'FacadesTrue'
			}


			if(kipBendexDistributionListValue != "Facades"){
                
				sapcode   = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()
				nodeValue = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()
                
				if(nodeValue == null){
					sapcode   = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()
					nodeValue = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()
				} else {
                    if(nodeValue.length() == 0){
                        sapcode   = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()
					    nodeValue = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()
                    }
                }
			}

		} else {
            
			def kipBendexDecisionTppvExist = false;
			def kipBendexIfItemIsShipping = currentOrderItem.ShoppingItemType


			if(kipBendexIfItemIsShipping == "Shipping"){
				nodeValue = 'FREIGHT'

			} else {
				def kipBendexName = currentOrderItem.Name

				if(currentOrderItem.Profile.@kind == "profile"){
                    

					if(currentOrderItem.Profile.@length == 150){
						kipJbBSChecker = 1
						def profileFromPayloadValue = currentOrderItem.Profile.@flangeSum

                        //if (profileFromPayloadValue == null | profileFromPayloadValue.size() == 0){
                        //    profileFromPayloadValue = 0
                        //}
                        if(profileFromPayloadValue == null){
                            profileFromPayloadValue = 0
                        }else{
                            def profileFromPayloadValue_type = profileFromPayloadValue.getClass().name
                            if(profileFromPayloadValue_type == "java.util.ArrayList"){
                                if(profileFromPayloadValue.size()){
                                    profileFromPayloadValue = 0
                                }
                            }
                        }	




						def profileFromPayloadValue_to_number = profileFromPayloadValue.toDouble()
						def width = Math.round(profileFromPayloadValue_to_number)



						if(width > 1500){
							nodeValue = 'BS1500'
						} else {
							nodeValue = prj_SapCodes_array[width.toString()] ?: "NO_VALUE"
						}
						codes_checker = 1

					}

				}
				

                // def bendingCount = currentOrderItem.Profile.@bendingCount.text()
                // if(bendingCount.length() == 0 | bendingCount == null){
                //     bendingCount = 0
                // } else {
                //     bendingCount = Integer.parseInt(currentOrderItem.Profile.@bendingCount)
                // }
				def bendingCount = Integer.parseInt(currentOrderItem.Profile.@bendingCount.text())
				def hemRiCount = currentOrderItem.Profile.Flanges.Flange?.count { it.@hemRi?.text()?.trim() }
                // def hemRiCount = 0
                
                // def is_profile_presented = currentOrderItem.Profile.Flanges.Flange.@hemRi.text()
                // if(is_profile_presented.length() == 0){
                //     hemRiCount = currentOrderItem.Profile.Flanges.Flange?.count { it.@hemRi?.text()?.trim() }
                // }


				bends = bendingCount - hemRiCount
				
                println("BENDS --- " + bends)

				if(currentOrderItem.Profile.@kind == "profile" && codes_checker == 0 && bends < 10){
                    println("TRUE 1 ----")
					if(currentOrderItem.Profile.@length != 150){
                        println("TRUE 2 ----")
						def profileFromPayloadValue = currentOrderItem.Profile.@flangeSum.text()
                        println("profileFromPayloadValue flangeSum ---" + profileFromPayloadValue)

                        //if(profileFromPayloadValue == null | profileFromPayloadValue.length() == 0){
                        //    profileFromPayloadValue = 0
                        //}

                        if(profileFromPayloadValue == null){
                            profileFromPayloadValue = 0
                        }else{
                            def profileFromPayloadValue_type = profileFromPayloadValue.getClass().name
                            if(profileFromPayloadValue_type == "java.lang.String"){
                                if(profileFromPayloadValue.length() == 0){
                                    profileFromPayloadValue = 0
                                }
                            }
                        }

						def profileFromPayloadValue_to_number = profileFromPayloadValue.toDouble()
						def widthFL = Math.round(profileFromPayloadValue_to_number)
                        println("widthFL ---- " + widthFL)

						//def check_SapCodes_array = prj_SapCodes_FL_array.get(widthFL.toString()) ?: "NO_VALUE"
                        def check_SapCodes_array = prj_SapCodes_FL_array[widthFL.toString()] ?: "NO_VALUE"
						if(check_SapCodes_array != "NO_VALUE"){
							nodeValue = check_SapCodes_array
						} else {
							nodeValue = "FL1500"
						}
						codes_checker = 1
						

					}
				}

                println("currentOrderItem.Profile.@kind --- " + currentOrderItem.Profile.@kind)
                println("codes_checker --- " + codes_checker)
				if(currentOrderItem.Profile.@kind == "box" && codes_checker == 0){
                    println("TRUE Profile.@kind == box && codes_checker == 0")
					def profileFromPayloadValue = currentOrderItem.Profile.@width.text()
                    if(profileFromPayloadValue == null){
                        profileFromPayloadValue = 0
                    } else {
                        profileFromPayloadValue_type = profileFromPayloadValue.getClass().name
                        println("profileFromPayloadValue_type -----* " + profileFromPayloadValue_type)
                        if(profileFromPayloadValue_type == "java.lang.String"){
                            if(profileFromPayloadValue.length() == 0){
                                profileFromPayloadValue = 0
                            }
                        }
                    }
                    println("profileFromPayloadValue after check is --- " + profileFromPayloadValue)
					def profileFromPayloadValue_to_number = profileFromPayloadValue.toDouble()
					def widthFL = Math.round(profileFromPayloadValue_to_number)

					def check_SapCodes_array = prj_SapCodes_FL_array[widthFL.toString()] ?: "NO_VALUE"

					if(check_SapCodes_array != "NO_VALUE"){
						nodeValue = check_SapCodes_array
					} else {
						nodeValue = "FL1500"
					}
					codes_checker = 1
                    println("? nodeValue --- " + nodeValue)
				}


				if(currentOrderItem.Profile.@kind == "profile" && codes_checker == 0 && bends >= 10){
					nodeValue = "FLPROF"
					codes_checker = 1
				}
			}
		}

		if(nodeValue == null){
			nodeValue = ""
		}
        println("Node value is y---y " + nodeValue )
        
        println("__9__-------- material_code__function debuger END --------__9__")
        
        
	//}
    return nodeValue
    
}


println("\r\n\r\n\r\n__xx__-------- nodeValue debuger START --------__xx__")
////!def nodeValue = material_code__function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, dicts_list.prj_SapCodes_array, dicts_list.prj_SapCodes_FL_array, get_currentOrderItem_function)
//nodeValue = material_code__function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, dicts_list.prj_SapCodes_array, dicts_list.prj_SapCodes_FL_array, get_currentOrderItem_function)

println("nodeValue x---x " + nodeValue)
println("\r\n\r\n\r\n__xx__-------- nodeValue debuger END --------__xx__")




def order_qty_function = { kipBendexArrayDET_parameter, kipBendexArrayCNF_parameter, nodeValue_parameter, currentOrderItem ->
println("\r\n\r\n\r\n__10__-------- order_qty_function debuger START --------__10__")
    def finalvar_value = ""
    def kipBendexArrayDET = kipBendexArrayDET_parameter
    def kipBendexArrayCNF = kipBendexArrayCNF_parameter

        def finalvar = ""
        def kip_jb_string_of_nodes_checker = ""
        def kip_jb_dictionary_trigger = 0
        def i = 0
        def order_qty_vale = ""
        def ORDER_QTY = ""
        def nodevalue = nodeValue_parameter
        

        //ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
        
            //def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.'@xml:space'.text().length()
            def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
            def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
            def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

            //def isTemplateParametersPresented = currentOrderItem.TemplateParameters.'@xml:space'.text().length()
            def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
            def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
            def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }
            println("isCustomPropertiesPresented is ---- " + isCustomPropertiesPresented)
            println("isTemplateParametersPresented is ---- " + isTemplateParametersPresented)
          	

            if(isCustomPropertiesPresented != 0){
                if(countItems_CustomProperties == 1 && items_CustomProperties[0]?.@name?.text() == "mark_ID"){
                    println("ONE ITEM IN CUSTOM")
                } else {
                    while(kip_jb_dictionary_trigger != 1 && i < countItems_CustomProperties){
                        if(items_CustomProperties[i]?.text().length() > 0 && items_CustomProperties[i] != null){
                            
                            if(items_CustomProperties[i]?.@name?.text().take(3) != "BX_"){
                                
                                if(items_CustomProperties[i]?.@name?.text() in kipBendexArrayDET ){
                                    println("kipBendexArrayDET check" + i + "TRUE")
                                    kip_jb_string_of_nodes_checker += "true,"
                                } else {
                                    kip_jb_string_of_nodes_checker += "1"
                                }

                                if(items_CustomProperties[i]?.@name?.text() in kipBendexArrayCNF ){
                                    kip_jb_string_of_nodes_checker += "true,"
                                } else {
                                    kip_jb_string_of_nodes_checker += "1"
                                }
                            }
                        }
                        i++
                    }
                }

                order_qty_vale = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0]
                println("1 order_qty_vale ---- " + order_qty_vale)
                println("kip_jb_string_of_nodes_checker --- " + kip_jb_string_of_nodes_checker)
                println("kip_jb_string_of_nodes_checker length --- " + kip_jb_string_of_nodes_checker.length())

                //if(kip_jb_string_of_nodes_checker.length() == 0){
                if(!kip_jb_string_of_nodes_checker.contains("true,")){
                    if(currentOrderItem.ShoppingItemType.text().contains("Shipping")){
                        ORDER_QTY = currentOrderItem.Quantity.text()
                    } else {
                        if(order_qty_vale != null){
                            ORDER_QTY = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0].text()
                        }
                    }
                } else {
                    if(order_qty_vale != null){
                        ORDER_QTY = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0].text()
                    }
                }
                    

            } else {
                
                if(countItems_TemplateParameters != 0){
                    while(kip_jb_dictionary_trigger != 1 && i < countItems_TemplateParameters){

                        if(items_TemplateParameters[i]?.text().length() > 0 && items_TemplateParameters[i] != null){

                            if(items_TemplateParameters[i]?.@name?.text().take(3) != "BX_"){
                                if(items_TemplateParameters[i]?.@name?.text() in kipBendexArrayDET ){
                                    kip_jb_string_of_nodes_checker += "true,"
                                } else {
                                    kip_jb_string_of_nodes_checker += "1"
                                }

                                if(items_TemplateParameters[i]?.@name?.text() in kipBendexArrayCNF ){
                                    kip_jb_string_of_nodes_checker += "true,"
                                } else {
                                    kip_jb_string_of_nodes_checker += "1"
                                }
                            }
                        }
                        i++
                    }

                    order_qty_vale = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0]
                    println("kip_jb_string_of_nodes_checker is ----" + kip_jb_string_of_nodes_checker)

                    //if(kip_jb_string_of_nodes_checker.length() == 0){
                    if(!kip_jb_string_of_nodes_checker.contains("true,")){
                        if(currentOrderItem.ShoppingItemType.text().contains("Shipping")){
                            println("Shipping TRUE ")
                            ORDER_QTY = currentOrderItem.Quantity.text()
                        } else {
                            if(order_qty_vale != null){
                                ORDER_QTY = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0].text()
                            }
                        }    
                    } else {
                        if(order_qty_vale != null){
                            ORDER_QTY = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0].text()
                        }
                    }
                }
                
                  
            }
            
            println("1 ORDER_QTY --- " + ORDER_QTY)
            println("order_qty_vale --- " + order_qty_vale)




            if( isCustomPropertiesPresented == 0 | (countItems_CustomProperties == 1 && items_CustomProperties[0]?.@name?.text() == "mark_ID") && isTemplateParametersPresented == 0 && nodevalue != "Shipping"){
                ORDER_QTY = currentOrderItem.Quantity.text()
            }
            println("2 ORDER_QTY --- " + ORDER_QTY)
            println("ORDER_QTY.length() --- " + ORDER_QTY.length())

            if(ORDER_QTY.length() == 0){
                ORDER_QTY = 0
            }

            if(ORDER_QTY != 0){
                finalvar = new BigDecimal(ORDER_QTY).setScale(3, BigDecimal.ROUND_HALF_UP)
            } else {
                finalvar = 0
            }
            
            println("1 finalvar --- " + finalvar)
            println("ORDER_QTY --- " + ORDER_QTY)

            if( isCustomPropertiesPresented == 0 | (countItems_CustomProperties == 1 && items_CustomProperties[0]?.@name?.text() == "mark_ID") && isTemplateParametersPresented == 0){
                
                def checker = 0
                if(currentOrderItem.Profile.@kind == "profile" && currentOrderItem.Profile.@length == 150 && checker == 0){
                    println(" TRUE ")
                    finalvar = currentOrderItem.Quantity.text()
                    checker = 1
                } else {
                    println(" FALSE ")
                }
                
                def length = 0
                length = currentOrderItem.Profile.@length
                println("894 length --- is " + length)
                

                length_null_check = length.text()
                println("894 length_null_check --- is " + length_null_check.getClass().name)
                 //if(length == null | length.size() == 0){
                 //   length = 0
                 ///}


                 if(length_null_check == null){
                    length = 0
                 }else{
                    def length_class = length_null_check.getClass().name
                    if(length_class == "java.lang.String"){
                        if(length_null_check.length() == 0){
                            length = 0
                        }
                    }
                 }


                length = length.toDouble()
                
                println("1 length ---- " + length)

                if(currentOrderItem.Profile.@kind == "profile" && currentOrderItem.Profile.@length != 150 && checker == 0){
                    finalvar = (length/1000).toDouble()
                    finalvar = new BigDecimal(finalvar).setScale(3, BigDecimal.ROUND_HALF_UP)
                    finalvar = finalvar * new BigDecimal(currentOrderItem.Quantity.text())

                    checker = 1
                }
                
                if(currentOrderItem.Profile.@kind == "box" && checker == 0){
                    
                    finalvar = (length/1000).toDouble()
                    finalvar = new BigDecimal(finalvar).setScale(3, BigDecimal.ROUND_HALF_UP)
                    finalvar = finalvar * new BigDecimal(currentOrderItem.Quantity.text())
                    
                    checker = 1
                }
                
                if(currentOrderItem.ShoppingItemType.text().contains("Shipping") && checker == 0){
                    
                    finalvar = currentOrderItem.Quantity.text()
                    checker = 1
                }
                
            }
        println("1 finalvar --- " + finalvar)
        //finalvar = finalvar.toBigDecimal().toInteger()
        //finalvar = finalvar.stripTrailingZeros().toPlainString()
        finalvar = finalvar.toBigDecimal().stripTrailingZeros().toPlainString()
        println("1 finalvar_value --- " + finalvar)
        finalvar_value = finalvar

        
    //} 
    
    return finalvar_value
    println("_10__-------- order_qty_function debuger END --------__10__")
}




def plant_function = { currentOrderItem -> 
    println("\r\n\r\n\r\n__11__-------- plant_function debuger START --------__11__")
    def finish = ""
    def i = 0
    def isPlantPresented = false

    // ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
        def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

        def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
        def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
        def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }




        if(isCustomPropertiesPresented != 0){
            if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                finish = "IE10"
            } else {
                while(i < countItems_CustomProperties){
                    if(items_CustomProperties[i]?.@name?.text() == "PLANT"){
                        isPlantPresented = true
                    }
                    i++
                }

                if(isPlantPresented){
                    finish = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'PLANT' }?.text()
                } else {
                    finish = "GB20"
                }
            }
        } else {
            if(isTemplateParametersPresented != 0){
                if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                    finish = "IE10"
                } else {
                    while(i < countItems_TemplateParameters){
                        if(items_TemplateParameters[i]?.@name?.text() == "PLANT"){
                            isPlantPresented = true
                        }
                        i++
                    }

                    if(isPlantPresented){
                        finish = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'PLANT' }?.text()
                    } else {
                        finish = "GB20"
                    }
                }
            } else {
                if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                    finish = "IE10"
                } else {
                    finish = "GB20"
                }
            }
        }
    println("__11__-------- plant_function debuger END --------__11__")
    // }
    return finish

}






def dummy_matterial_item_function = { kipBendexArrayCNF_parameter, coverWidth2Dict_parameter, prj_coil_metal_ext_array_parameter, prj_coil_finish_ext_array_parameter, prj_coil_guige_ext_array_parameter, prj_coil_colour_ext_array_parameter, front_colour_colour_parameter, front_colour_type_parameter, front_colour_colour_IE_array_parameter, front_colour_type_IE_parameter, nodeValue_parameter,  currentOrderItem, plant_value->
            println("\r\n\r\n\r\n__12__-------- dummy_matterial_item_function debuger START --------__12__")
            
            def currentCreateNodeName_array = []
            def currentCreateNodeValue_array = []
            def all_nodes_name_array = []
            def all_nodes_value_array = []

            def currentCreateNodeName = ""
            def currentCreateNodeValue = ""
            def cuurrent_plant_value = plant_value
            println("plant_value ---- is " + plant_value)
            
            nodeValue = nodeValue_parameter

            def kipBendexArrayCNF = kipBendexArrayCNF_parameter
            def prj_coil_metal_ext_array = prj_coil_metal_ext_array_parameter
            def prj_coil_finish_ext_array = prj_coil_finish_ext_array_parameter
            def prj_coil_guige_ext_array = prj_coil_guige_ext_array_parameter
            def prj_coil_colour_ext_array = prj_coil_colour_ext_array_parameter
            def front_colour_colour = front_colour_colour_parameter
            def front_colour_type = front_colour_type_parameter
            def front_colour_colour_IE_array = front_colour_colour_IE_array_parameter
            def front_colour_type_IE = front_colour_type_IE_parameter
            def coverWidth2Dict = coverWidth2Dict_parameter


            def kip_jb_positionStringFlag = "THEREISNOPOS888"




        //ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
        //currentOrderItem.each { currentOrderItem ->
            def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
            def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
            def itemPos1_CustomProperties = items_CustomProperties[0]?.@name?.text()
            def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()

            def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
            def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }
            def itemPos1_TemplateParameters = items_TemplateParameters[0]?.@name?.text()
            def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()

            println("isCustomPropertiesPresented --- " + isCustomPropertiesPresented)
            println("isTemplateParametersPresented --- " + isTemplateParametersPresented)

            def z_pc_checker = 0
            def IE_checker = 0



            def currency = ""

            def mockTester = 1


            
            println("CUR --- " + ServiceResponse.Orders.Order.Currency?.@oid?.text())
            if(ServiceResponse.Orders.Order.Currency?.@oid?.text() == "45d3d4ec-3f9e-4a12-abfc-ddc4e2b11f9a"){
                currency = "GBP"
            }

            if(ServiceResponse.Orders.Order.Currency?.@oid?.text() == "f6e2a38e-610a-4027-9493-70cf8b74ddc4"){
                currency = "EUR"
            }

            println(" real CUR --- " + currency)
            if(!currency){
                currency = "THEREISNOCURRENCY!!@"
            }
                
            if(currency != "THEREISNOCURRENCY!!@"){
                //"CURRENCY"(currency)
                currentCreateNodeName_array << "CURRENCY"
                currentCreateNodeValue_array << currency

            }	


            println("currentCreateNodeName_array 44---44 " + currentCreateNodeName_array)

            if (isCustomPropertiesPresented != 0) {
                    if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                        IE_checker = 1
                    }
            } else {

                if(isTemplateParametersPresented != 0){
                    if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                        IE_checker = 1
                    }
                }else {
                    if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
                        IE_checker = 1
                    }
                }
            }


            

                
                
                def checker = 0
                
                i = 0
                def fast_track_value = ""
                


                if(isCustomPropertiesPresented != 0 | isTemplateParametersPresented != 0 | nodeValue == 'FREIGHT'){
                    println("isCustomPropertiesPresented != 0 | isTemplateParametersPresented != 0 | nodeValue == 'FREIGHT' ---- TRUE")
                    if(isCustomPropertiesPresented != 0){
                        def items = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
                        def itemPos1 = items[0]?.@name?.text()
                        def countItems = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
                        def kip_bendex_fast_track = ""


                        if(countItems == 1 && itemPos1 == "mark_ID"){
                            println("ONE ITEM IN CUSTOM __ 12")
                        } else {
                            while (i < countItems){
                                if(items[i]?.text().length() > 0 && items[i] != null){
                                    if(items[i]?.@name?.text().take(3) != "BX_" | items[i]?.@name?.text() == "BX_NO_OF_ITEMS"){																				
                                        if(items[i]?.@name?.text() in kipBendexArrayCNF){				
                                            if(items[i]?.@name?.text() != "CONDITION"){
                                                currentCreateNodeName = items[i]?.@name?.text()
                                                currentCreateNodeValue = items[i]?.text()
                                                


                                                if(items[i]?.@name?.text() == "COLOUR"){
                                                    currentCreateNodeName = items[i]?.@name?.text()
                                                    currentCreateNodeValue = items[i]?.text()

                                                    if(currentCreateNodeValue.length() > 20){
                                                        currentCreateNodeValue = currentCreateNodeValue.take(20)
                                                                                                                    
                                                    }
                                                }


                                                if(items[i]?.@name?.text() == "PRODTEXT1"){
                                                    currentCreateNodeName = items[i]?.@name?.text()
                                                    currentCreateNodeValue = items[i]?.text()
                                                    if(currentCreateNodeValue.length() > 28){
                                                        currentCreateNodeValue = currentCreateNodeValue.take(28)
                                                    }
                                                }


                                                if(items[i]?.@name?.text() == "Z_PC_TYPE" | items[i]?.@name?.text() == "Z_PC_COLOUR"){
                                                    z_pc_checker = 1;
                                                }


                                                if(items[i]?.@name?.text() == "FAST_TRACK"){
                                                    fast_track_value = items[i]?.text()
                                                    if(fast_track_value == "Y"){
                                                        //SET IT AS OUTSIDE DPP
                                                        kip_bendex_fast_track = "Y"
                                                    }

                                                    if(fast_track_value == "N"){
                                                        //SET IT AS OUTSIDE DPP
                                                        kip_bendex_fast_track = "N"
                                                    }
                                                }
                                            }

                                            //"${currentCreateNodeName}"("${currentCreateNodeValue}")
                                            currentCreateNodeName_array << currentCreateNodeName
                                            currentCreateNodeValue_array << currentCreateNodeValue
                                            

                                        }
                                    }

                                    if(items[i]?.@name?.text() == "PACKAGING_SURCHARGE"){
                                        currentCreateNodeName = items[i]?.@name?.text()
                                        currentCreateNodeValue = items[i]?.text()
                                        //"${currentCreateNodeName}"("${currentCreateNodeValue}")
                                            currentCreateNodeName_array << currentCreateNodeName
                                            currentCreateNodeValue_array << currentCreateNodeValue
                                            
                                    }
                                }
                                i++
                            }






                        }
                        
                    } else {
                        def items = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }
                        def itemPos1 = items[0]?.@name?.text()
                        def countItems = currentOrderItem.TemplateParameters.Item.size()
                        println("\r\n TemplateParameters presented \r\n --- " + countItems)

                    

                        while (i < countItems){
                            if(isTemplateParametersPresented != 0){
                                if(items[i]?.@name?.text().take(3) != "BX_" | items[i]?.@name?.text() == "BX_NO_OF_ITEMS"){
                                    if(items[i]?.@name?.text() in kipBendexArrayCNF){
                                        if(items[i]?.@name?.text() != "CONDITION"){
                                            currentCreateNodeName = items[i]?.@name?.text()
                                            currentCreateNodeValue = items[i]?.text()

                                            
                                            if(items[i]?.@name?.text() == "COLOUR"){
                                                currentCreateNodeName = items[i]?.@name?.text()
                                                currentCreateNodeValue = items[i]?.text()

                                                if(currentCreateNodeValue.length() > 20){
                                                    currentCreateNodeValue = currentCreateNodeValue.take(20)																
                                                }
                                            }


                                            if(items[i]?.@name?.text() == "PRODTEXT1"){
                                                currentCreateNodeName = items[i]?.@name?.text()
                                                currentCreateNodeValue = items[i]?.text()
                                                if(currentCreateNodeValue.length() > 28){
                                                    currentCreateNodeValue = currentCreateNodeValue.take(28)
                                                }
                                            }


                                            if(items[i]?.@name?.text() == "Z_PC_TYPE" | items[i]?.@name?.text() == "Z_PC_COLOUR"){
                                                z_pc_checker = 1;
                                            }


                                            if(items[i]?.@name?.text() == "FAST_TRACK"){
                                                fast_track_value = items[i]?.text()
                                                if(fast_track_value == "Y"){
                                                    //SET IT AS OUTSIDE DPP
                                                    kip_bendex_fast_track = "Y"
                                                }

                                                if(fast_track_value == "N"){
                                                    //SET IT AS OUTSIDE DPP
                                                    kip_bendex_fast_track = "N"
                                                }
                                            }	
                                            //"${currentCreateNodeName}"("${currentCreateNodeValue}")
                                            currentCreateNodeName_array << currentCreateNodeName
                                            currentCreateNodeValue_array << currentCreateNodeValue
                                            //println("! ---------- TESTER ---------- !")
                                            //println("currentCreateNodeName_array -- " + currentCreateNodeName_array)											
                                        }
                                    }
                                }

                                println("xxxxxxxxxxxxxxxxx")
                                println("currentCreateNodeName_array --- " + currentCreateNodeName_array)
                                println("currentCreateNodeValue_array --- " + currentCreateNodeValue_array)
                                println("xxxxxxxxxxxxxxxxx")
                                if(items[i]?.@name?.text() == "PACKAGING_SURCHARGE"){
                                    currentCreateNodeName = items[i]?.@name?.text()
                                    currentCreateNodeValue = items[i]?.text()
                                    //"${currentCreateNodeName}"("${currentCreateNodeValue}")
                                    currentCreateNodeName_array << currentCreateNodeName
                                    currentCreateNodeValue_array << currentCreateNodeValue
                                }
                            }
                            i++
                        }
                    }
                } 


                //"BENDEX_POS"(currentOrderItem.PositionString.text())
                currentCreateNodeName_array << "BENDEX_POS"
                currentCreateNodeValue_array << currentOrderItem.PositionString.text()



            //1 part -----------------------------------------------------------------------------



            if((isCustomPropertiesPresented == 0 | (countItems_CustomProperties == 1 && itemPos1_CustomProperties == "mark_ID")) && isTemplateParametersPresented == 0 && nodeValue != "FREIGHT"){
            println("isCustomPropertiesPresented == 0 | (countItems_CustomProperties == 1 && itemPos1_CustomProperties == mark_ID && isTemplateParametersPresented == 0 && nodeValue != FREIGHT *** TURE *** ")
                def length_flag = ""
                def lengthFromPayloadValue = currentOrderItem.Profile.@length

                def lengthFromPayloadValue_null_check = lengthFromPayloadValue.text()

                //if(lengthFromPayloadValue == null | lengthFromPayloadValue.size() == 0){
                //    lengthFromPayloadValue = 0
                //}


                if(lengthFromPayloadValue_null_check == null){
                    lengthFromPayloadValue = 0
                }else{
                    def lengthFromPayloadValue_type = lengthFromPayloadValue_null_check.getClass().name
                    if(lengthFromPayloadValue_type == "java.lang.String"){
                        if(lengthFromPayloadValue_null_check.length() == 0){
                            lengthFromPayloadValue
                        }
                    }
                }
                
                def lengthFromPayloadValue_to_number = lengthFromPayloadValue.toDouble()

                
                if(lengthFromPayloadValue_to_number > 3000){
                    length_flag = "3+"
                } else {
                    length_flag = "3-"
                }
                

                def countProfileFlangesAngle = currentOrderItem.Profile.Flanges.Flange?.count { it.@angle?.text()?.trim() }
                def countProfileFlangeLength = currentOrderItem.Profile.Flanges.Flange?.count { it.@length?.text()?.trim() }
                def countProfileHemRiCount = currentOrderItem.Profile.Flanges.Flange?.count { it.@hemRi?.text()?.trim() }
                
                def bendingCount = currentOrderItem.Profile.@bendingCount.toString() as Integer
                println("bendingCount is --- " + bendingCount)
                println("bendingCount CLASS is --- ${bendingCount.getClass().name}")
                println("countProfileHemRiCount is --- " + countProfileHemRiCount)
                println("countProfileHemRiCount CLASS is --- ${countProfileHemRiCount.getClass().name}")
                

                def angels = ""
                def lengthes = ""
                def bends = ""

                if(countProfileFlangesAngle > 0 | countProfileFlangeLength > 0){
                    angels = countProfileFlangesAngle
                    lengthes = countProfileFlangeLength
                    bends = bendingCount - countProfileHemRiCount

                    println("bends is --- " + bends)
                    
                }

                def material_oid_length = currentOrderItem.Material?.@oid?.text().length()

                if(material_oid_length == 0){
                    material_oid = ""
                } else {
                    material_oid = currentOrderItem.Material?.@oid?.text()
                }
                
                def safe_edge_nodevalue = ""

                if(currentOrderItem.Profile.@kind == "profile"){
                    safe_edge_nodevalue = countProfileHemRiCount
                }

                if(currentOrderItem.Profile.@kind == "profile" && lengthFromPayloadValue == 150){
                    safe_edge_nodevalue = null
                }
                println("material_oid --- " + material_oid)
                
                def coil_metal_ext_value = prj_coil_metal_ext_array[material_oid.toString()]
                def coil_finish_ext_value = prj_coil_finish_ext_array[material_oid.toString()]
                def coil_gauge_ext_value = prj_coil_guige_ext_array[material_oid.toString()]
                def coil_colour_ext_value = prj_coil_colour_ext_array[material_oid.toString()]
                
                println("coil_metal_ext_value ----- " + coil_metal_ext_value)
                println("coil_finish_ext_value ----- " + coil_finish_ext_value)
                println("coil_gauge_ext_value ----- " + coil_gauge_ext_value)
                println("coil_colour_ext_value ----- " + coil_colour_ext_value)
                
                if(coil_metal_ext_value != null){
                    currentCreateNodeName_array << "COIL_METAL_EXT"
                    currentCreateNodeValue_array << coil_metal_ext_value
                } 
               
                if(coil_finish_ext_value != null){
                    currentCreateNodeName_array << "COIL_FINISH_EXT"
                    currentCreateNodeValue_array << coil_finish_ext_value
                }

                if(coil_gauge_ext_value != null){
                    currentCreateNodeName_array << "COIL_GAUGE_EXT"
                    currentCreateNodeValue_array << coil_gauge_ext_value
                }

                if(coil_colour_ext_value != null){
                    currentCreateNodeName_array << "COIL_COLOUR_EXT"
                    currentCreateNodeValue_array << coil_colour_ext_value
                }

                if(length_flag != null){
                    currentCreateNodeName_array << "LENGTH_FLAG"
                    currentCreateNodeValue_array << length_flag
                }
        
                println("currentCreateNodeName_array --- " + currentCreateNodeName_array)
                println("currentCreateNodeValue_array --- " + currentCreateNodeValue_array)

                //"COIL_METAL_EXT"(prj_coil_metal_ext_array[material_oid.toString()] ?: "NO_VALUE")
                //"COIL_FINISH_EXT"(prj_coil_finish_ext_array[material_oid.toString()] ?: "NO_VALUE")
                //"COIL_GAUGE_EXT"(prj_coil_guige_ext_array[material_oid.toString()] ?: "NO_VALUE")
                //"COIL_COLOUR_EXT"(prj_coil_colour_ext_array[material_oid.toString()] ?: "NO_VALUE")
                //"LENGTH_FLAG"(length_flag)

                 //!!!!!!!!!!!!! ISSUEE
                println("coverWidth2Dict ---- " + coverWidth2Dict)
                def coverWidth2Dict_value = ""
                def coverWidth2Dict_checker = coverWidth2Dict[material_oid.toString()]
                if(coverWidth2Dict_checker != null){
                    coverWidth2Dict_value = coverWidth2Dict_checker
                }


                println("coverWidth2Dict_value --- " + coverWidth2Dict_value)
                if(coverWidth2Dict_value.length() == 0){
                    //"COVER_WIDTH2"(1208)
                    currentCreateNodeName_array << "COVER_WIDTH2"
                    currentCreateNodeValue_array << 1208
                } else {
                    //"COVER_WIDTH2"(coverWidth2Dict_value)
                    currentCreateNodeName_array << "COVER_WIDTH2"
                    currentCreateNodeValue_array << coverWidth2Dict_value
                }

                def check = 0

                if(nodeValue == "FLPROF"){
                    //"BENDS"(30)
                    currentCreateNodeName_array << "BENDS"
                    currentCreateNodeValue_array << 30
                    check = 1
                }

                if(currentOrderItem.Profile.@kind == "profile" && check == 0){
                    //"BENDS"(bends)
                    currentCreateNodeName_array << "BENDS"
                    currentCreateNodeValue_array << bends
                }

                if(currentOrderItem.Profile.@kind == "profile"){
                    //"SAFE_EDGE"(safe_edge_nodevalue)
                    currentCreateNodeName_array << "SAFE_EDGE"
                    currentCreateNodeValue_array << safe_edge_nodevalue
                }

                if(currentOrderItem.Profile.@kind == "box"){
                    //"BENDS"(currentOrderItem.Profile.@bendingCount)
                    currentCreateNodeName_array << "BENDS"
                    currentCreateNodeValue_array << currentOrderItem.Profile.@bendingCount
                }
            }

            //2 part ---------------------------------------------------------



            def extra_operations = null

            def perforation_absX = currentOrderItem.Profile.Perforations.Perforation?.count { it.@absX?.text()?.trim() }
            def perforation_absY = currentOrderItem.Profile.Perforations.Perforation?.count { it.@absY?.text()?.trim() }
            def perforation_flangeIndex = currentOrderItem.Profile.Perforations.Perforation?.count { it.@flangeIndex?.text()?.trim() }
            def perforation_kind = currentOrderItem.Profile.Perforations.Perforation?.count { it.@kind?.text()?.trim() }
            def perforation_width = currentOrderItem.Profile.Perforations.Perforation?.count { it.@width?.text()?.trim() }
            def perforation_x = currentOrderItem.Profile.Perforations.Perforation?.count { it.@x?.text()?.trim() }
            def perforation_xAnchor = currentOrderItem.Profile.Perforations.Perforation?.count { it.@xAnchor?.text()?.trim() }
            def perforation_y = currentOrderItem.Profile.Perforations.Perforation?.count { it.@y?.text()?.trim() }
            def perforation_yAnchor = currentOrderItem.Profile.Perforations.Perforation?.count { it.@yAnchor?.text()?.trim() }

            def perforation_sum = perforation_absX + perforation_absY + perforation_flangeIndex + perforation_kind + perforation_width + perforation_x + perforation_xAnchor + perforation_y + perforation_yAnchor

            def notch_corner = currentOrderItem.Profile.Notches.Notch?.count { it.@corner?.text()?.trim() }
            def notch_height = currentOrderItem.Profile.Notches.Notch?.count { it.@height?.text()?.trim() }
            def notch_kind = currentOrderItem.Profile.Notches.Notch?.count { it.@kind?.text()?.trim() }
            def notch_width = currentOrderItem.Profile.Notches.Notch?.count { it.@width?.text()?.trim() }

            def notch_sum = notch_corner + notch_height + notch_kind + notch_width

            def cuts_angle = currentOrderItem.Profile.Cuts.Cut?.count { it.@angle?.text()?.trim() }
            def cuts_flangeIndex = currentOrderItem.Profile.Cuts.Cut?.count { it.@flangeIndex?.text()?.trim() }
            def cuts_kind = currentOrderItem.Profile.Cuts.Cut?.count { it.@kind?.text()?.trim() }

            def cuts_sum = cuts_angle + cuts_flangeIndex + cuts_kind

            items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
            countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
            itemPos1_CustomProperties = items_CustomProperties[0]?.@name?.text()


            if((isCustomPropertiesPresented == 0 | (countItems_CustomProperties == 1 && itemPos1_CustomProperties == "mark_ID")) && isTemplateParametersPresented == 0){
                if(perforation_sum > 0 | notch_sum > 0 | cuts_sum > 0){
                    extra_operations = "P"
                }

                if(currentOrderItem.Profile.@kind == "box"){
                    extra_operations = "P"
                }
            }

            def tapered_check = 0

            if(currentOrderItem.Profile.@conical == true){
                tapered_check = 1
            }

            if(tapered_check == 1){
                //"TAPERED"("Y")
                currentCreateNodeName_array << "TAPERED"
                currentCreateNodeValue_array << "Y"
            }

            
            currentCreateNodeName_array << "EXTRA_OPERATIONS"
            currentCreateNodeValue_array << extra_operations

            def hemRi_counter = 0

                                    
            def is_MasterItem_presented = currentOrderItem.MasterItem.text()

            // if(is_MasterItem_presented.length() != 0){
            //     if(currentOrderItem.Profile.@kind.text() == "composition" ){
            //         currentCreateNodeName_array << "SUB_COMPONENT"
            //         currentCreateNodeValue_array << is_MasterItem_presented
            //         //"SUB_COMPONENT"(is_MasterItem_presented)    
            //     }
            // }


            if(currentOrderItem.Profile.@kind == "composition"){
                kip_jb_positionStringFlag = currentOrderItem.PositionString.text()
            }
                




            def masterItem = currentOrderItem.MasterItem.text()
            if(!masterItem){
                masterItem = "NOHEREISEMPTY123"
            }


            // if(currentOrderItem.PositionString.text().toString().count('.') > 0){
            //     if(currentOrderItem.PositionString.text().toString().take(kip_jb_positionStringFlag.toString().length()) == kip_jb_positionStringFlag){
            //         if(masterItem != "NOHEREISEMPTY123"){
            //             if(currentOrderItem.Profile.@kind.text() == "composition" ){
            //                 if(!currentCreateNodeName_array.contains("SUB_COMPONENT")){
            //                     //"SUB_COMPONENT"(masterItem)
            //                     currentCreateNodeName_array << "SUB_COMPONENT"
            //                     currentCreateNodeValue_array << masterItem
            //                 }
            //             }
            //         }
            //     }
            // }

            def frontColor_oid = currentOrderItem.FrontColor.@oid

            if(!frontColor_oid){
                frontColor_oid = "NOHEREISEMPTY123"
            }

            println("frontColor_oid is ---***--- is " + frontColor_oid)
            println("IE_checker is ----***----- is " + IE_checker)

            def z_pc_colour_node_value = front_colour_colour[frontColor_oid.toString()]
            def z_pc_type_node_value = front_colour_type[frontColor_oid.toString()]
            def colour_node_value = front_colour_colour_IE_array[frontColor_oid.toString()]
            def painted_node_value = front_colour_type_IE[frontColor_oid.toString()]
            
            
            if(frontColor_oid != "NOHEREISEMPTY123" && z_pc_checker == 0){
                if(IE_checker == 0){


                    // def z_pc_colour_node_value = front_colour_colour[frontColor_oid.toString()]
                    // def z_pc_type_node_value = front_colour_type[frontColor_oid.toString()]
                    // def colour_node_value = front_colour_colour_IE_array[frontColor_oid.toString()]
                    // def painted_node_value = front_colour_type_IE[frontColor_oid.toString()]

                    println("xxxxxxxxxxxxxx Current TEST  START xxxxxxxxxxxxxxxxxxxxxxxxxxxx")
                    println("z_pc_colour_node_value ********+ " + z_pc_colour_node_value)
                    println("z_pc_type_node_value ********+ " + z_pc_type_node_value)
                    println("colour_node_value ********+ " + colour_node_value)
                    println("painted_node_value ********+ " + painted_node_value)
                    println("xxxxxxxxxxxxxx Current TEST  END xxxxxxxxxxxxxxxxxxxxxxxxxxxx")

                    if(plant_value == "GB20"){
                        if(z_pc_colour_node_value != null){
                            currentCreateNodeName_array << "Z_PC_COLOUR"
                            currentCreateNodeValue_array << z_pc_colour_node_value
                        }

                        if(z_pc_type_node_value != null){
                            currentCreateNodeName_array << "Z_PC_TYPE"
                            currentCreateNodeValue_array << z_pc_type_node_value
                        }
                    }


                    if(plant_value == "IE10"){
                        if(colour_node_value != null){
                            currentCreateNodeName_array << "COLOUR"
                            currentCreateNodeValue_array << colour_node_value
                        }

                        if(painted_node_value != null){
                            currentCreateNodeName_array << "PAINTED"
                            currentCreateNodeValue_array << painted_node_value
                        }
                    }


                }
            }

            def curent_BALand = ServiceResponse.Orders.Order.BALand
            println("curent_BALand ---- " + curent_BALand)
            println("currentCreateNodeName_array ---" + currentCreateNodeName_array)

            
            if(curent_BALand == "UK"){
                if(!currentCreateNodeName_array.contains("Z_PC_COLOUR") && !currentCreateNodeName_array.contains("Z_PC_COLOUR")){
                    if(z_pc_colour_node_value != null){
                        currentCreateNodeName_array << "Z_PC_COLOUR"
                        currentCreateNodeValue_array << z_pc_colour_node_value
                    }

                    if(z_pc_type_node_value != null){
                        currentCreateNodeName_array << "Z_PC_TYPE"
                        currentCreateNodeValue_array << z_pc_type_node_value
                    }
                }
            }

            if(curent_BALand == "IE" | curent_BALand == "XI"){
                if(!currentCreateNodeName_array.contains("COLOUR") && !currentCreateNodeName_array.contains("PAINTED")){
                    if(colour_node_value != null){
                            currentCreateNodeName_array << "COLOUR"
                            currentCreateNodeValue_array << colour_node_value
                    }

                        if(painted_node_value != null){
                            currentCreateNodeName_array << "PAINTED"
                            currentCreateNodeValue_array << painted_node_value
                        }
                }
            }

            // if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
            //            IE_checker = 1



            if((isCustomPropertiesPresented == 0 | countItems_CustomProperties == 1) && isTemplateParametersPresented == 0 && nodeValue != "FREIGHT"){
                if(nodeValue != "FLPROF"){
                    //"NO_ITEMS"(currentOrderItem.Quantity)
                    currentCreateNodeName_array << "NO_ITEMS"
                    currentCreateNodeValue_array << currentOrderItem.Quantity.toBigDecimal().intValue()
                }
            }

            println("NodeValueX ---- is " + nodeValue)
            println("FINALL currentCreateNodeName_array ----" + currentCreateNodeName_array)
            println("FINALL currentCreateNodeValue_array ----" + currentCreateNodeValue_array)

            if(nodeValue == "FLPROF"){
                def index_of_bends = currentCreateNodeName_array.findIndexOf { it == "SAFE_EDGE" }
                println("index_of_bends ---- is " + index_of_bends)
                if(index_of_bends >= 0){
                    currentCreateNodeName_array.remove(index_of_bends)
                    currentCreateNodeValue_array.remove(index_of_bends)
                }
            }

            all_nodes_name_array << currentCreateNodeName_array.clone()
            all_nodes_value_array << currentCreateNodeValue_array.clone()
            
            currentCreateNodeName_array.clear()
            currentCreateNodeValue_array.clear()
            println("__12__-------- dummy_matterial_item_function debuger END --------__12__")
        //}
            
            return[all_nodes_name_array: all_nodes_name_array, all_nodes_value_array: all_nodes_value_array]
            
            
}


//!!//def dummy_matterial_item_function_result = dummy_matterial_item_function(dicts_list.kipBendexArrayCNF, dicts_list.coverWidth2Dict, dicts_list.prj_coil_metal_ext_array, dicts_list.prj_coil_finish_ext_array, dicts_list.prj_coil_guige_ext_array, dicts_list.prj_coil_colour_ext_array, dicts_list.front_colour_colour, dicts_list.front_colour_type, dicts_list.front_colour_colour_IE_array, dicts_list.front_colour_type_IE, nodeValue)
//println("!! dummy_matterial_item_function_result !! ---" + dummy_matterial_item_function_result)


def p = 0

def dummy_matterial_item_NODE_MAKER_function = { dummy_matterial_item_NODE_MAKER_function_builder, currentCreateNodeName_array_parameter, currentCreateNodeValue_array_parameter ->
    def matterial_itemCreateNodeName_global_array = currentCreateNodeName_array_parameter
    def matterial_itemCreateNodeValue_global_array = currentCreateNodeValue_array_parameter

    def  current_node_names_array = matterial_itemCreateNodeName_global_array[0]
    def  current_node_values_array = matterial_itemCreateNodeValue_global_array[0]
    p = p + 1
    
    i = 0
    if(current_node_names_array != null){
        while( i < current_node_names_array.size()){
            if(current_node_values_array[i] != null && current_node_values_array[i] != "" && current_node_values_array[i] != "null" ){
                dummy_matterial_item_NODE_MAKER_function_builder."${current_node_names_array[i]}"(current_node_values_array[i])
            }
            i++
        }
    }    
}





def dummy_detailing_function = { kipBendexArrayDET_parameter, kipBendexArrayCNF_parameter, nodeValue_parameter, currentOrderItem ->
    println("\r\n\r\n\r\n__13__-------- dummy_detailing_function debuger START --------__13__")
    println (">>> dummy_detailing_function: GLOBAL nodeValue = ${nodeValue}")
    def kipJbBSChecker = 1
    nodeValue = nodeValue_parameter
    println("nodeValue at the start ***///" + nodeValue_parameter)
    kipBendexArrayDET = kipBendexArrayDET_parameter
    kipBendexArrayCNF = kipBendexArrayCNF_parameter
    //currentOrderItem = currentOrderItem_parameter



    def currentCreateNodeName_array = []
    def currentCreateNodeValue_array = []
    println("******************* --- ")
        println("currentCreateNodeName_array --- " + currentCreateNodeName_array)
        println("currentCreateNodeValue_array --- " + currentCreateNodeValue_array)
    currentCreateNodeName_array.clear()
    currentCreateNodeValue_array.clear()
    def all_nodes_name_array = []
    def all_nodes_value_array = []

    //ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->

        def kip_jb_string_of_nodes_detail = ""
        def currentCreateNodeName = ""
        def currentCreateNodeValue = 0
        def girth_checker = 0
        def valueGirth = 0
        def i = 0

        def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
        def itemPos1_CustomProperties = items_CustomProperties[0]?.@name?.text()

        def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
        def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
        def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' } 

        println("isCustomPropertiesPresented --- " + isCustomPropertiesPresented)

        if(currentOrderItem.Profile.@kind == "box" | currentOrderItem.Profile.@kind == "composition" | kipJbBSChecker == 1){
            girth_checker = 1
        }


        if(nodeValue == "FREIGHT"){
            println("nodeValue == FREIGHT. SKIP the logic on ELSE")
        } else {
            if(isCustomPropertiesPresented != 0){
                def items = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
                def itemPos1 = items[0]?.@name?.text()
                def countItems = currentOrderItem.CustomProperties.PropertiesValues.Item.size()

                if(countItems == 1 && itemPos1 == "mark_ID"){
                    println("ONE ITEM IN CUSTOM")
                } else {
                    while (i < countItems){
                        if(items[i]?.text().length() > 0 && items[i] != null){
                            if(items[i]?.@name?.text().take(3) != "BX_"){
                                if(items[i]?.@name?.text() in kipBendexArrayDET){
                                    if(items[i]?.@name?.text() != "BENDEX_POSITION"){

                                        currentCreateNodeName = items[i]?.@name?.text()
                                        currentCreateNodeValue = items[i]?.text()

                                        if(items[i]?.@name?.text() == "GIRTH"){

                                            def getProfileWidth = currentOrderItem.Profile.@width

                                            println("getProfileWidth --- " + getProfileWidth)
                                            if(getProfileWidth.size() == 0){
                                                getProfileWidth = "empty"
                                            }


                                        
                                            if(getProfileWidth != "empty"){
                                                
                                                getProfileWidth = currentOrderItem.Profile.@width.text()
                                                
                                                // if(getProfileWidth == null | getProfileWidth.length() == 0){
                                                //     getProfileWidth = "0"
                                                // }

                                                if(getProfileWidth == null){
                                                    getProfileWidth = "0"
                                                }else{
                                                    if(getProfileWidth.length() == 0){
                                                        getProfileWidth = "0"
                                                    }
                                                }
                                                
                                                def decimal = Double.parseDouble(getProfileWidth)
                                                decimal = decimal * 0.001
                                                decimal = BigDecimal.valueOf(decimal).setScale(4, java.math.RoundingMode.HALF_UP)
                                                decimal = decimal.doubleValue()
                                                def strDecimal = decimal.toString()
                                                def length = strDecimal.length()
                                                def index = strDecimal.indexOf('.') + 1

                                                def decimalLastChar = strDecimal.toString()[-1] as int


                                                if((length - index) == 4){
                                                    if(decimalLastChar >= 5){
                                                        def truncate_number = strDecimal.length() - 1
                                                        decimal = strDecimal.substring(0, truncate_number)
                                                        decimal = Double.parseDouble(decimal)
                                                        decimal = decimal + 0.001
                                                    } else {
                                                        def truncate_number = strDecimal.length() - 1
                                                        decimal = strDecimal.substring(0, truncate_number)
                                                        decimal = Double.parseDouble(decimal)
                                                    }

                                                } else {
                                                    decimal = BigDecimal.valueOf(decimal).setScale(3, java.math.RoundingMode.HALF_UP)
                                                }

                                                    currentCreateNodeValue = decimal
                                            }									
                                        }
                                        currentCreateNodeName_array << currentCreateNodeName
                                        currentCreateNodeValue_array << currentCreateNodeValue
                                        //"${currentCreateNodeName}"("${currentCreateNodeValue}")

                                    }						
                                }
                            }
                        }
                        i++
                    }
                }
            } else {
                while(i < countItems_TemplateParameters){
                    if(items_TemplateParameters[i]?.text().length() > 0 && items_TemplateParameters[i] != null){
                        if(items_TemplateParameters[i]?.@name?.text().take(3) != "BX_"){
                            
                            if(items_TemplateParameters[i]?.@name?.text() in kipBendexArrayDET){
                                
                                if(items_TemplateParameters[i]?.@name?.text() != "BENDEX_POSITION"){
                                    
                                    currentCreateNodeName = items_TemplateParameters[i]?.@name?.text()
                                    currentCreateNodeValue = items_TemplateParameters[i]?.text()

                                    if(items_TemplateParameters[i]?.@name?.text() == "GIRTH"){
                                        def getProfileWidth = currentOrderItem.Profile.@width
                              
                                        if(getProfileWidth.text().length() == 0){
                                            getProfileWidth = "empty"
                                        }

                                        if(getProfileWidth != "empty"){
                                            getProfileWidth = currentOrderItem.Profile.@width.text()
                                            // if(getProfileWidth == null | getProfileWidth.length() == 0){
                                            //     getProfileWidth = "0"
                                            // }

                                            if(getProfileWidth == null){
                                                getProfileWidth = "0"
                                            }else{
                                                if(getProfileWidth.length() == 0){
                                                    getProfileWidth = "0"
                                                }
                                            }

                                            def decimal = Double.parseDouble(getProfileWidth)
                                            decimal = decimal * 0.001
                                            decimal = BigDecimal.valueOf(decimal).setScale(4, java.math.RoundingMode.HALF_UP)
                                            decimal = decimal.doubleValue()
                                            def strDecimal = decimal.toString()
                                            def length = strDecimal.length()
                                            def index = strDecimal.indexOf('.') + 1
                                            def decimalLastChar = strDecimal.toString()[-1] as int

                                        

                                            if((length - index) == 4){
                                                if(decimalLastChar >= 5){
                                                def truncate_number = strDecimal.length() - 1
                                                decimal = strDecimal.substring(0, truncate_number)
                                                decimal = Double.parseDouble(decimal)
                                                decimal = decimal + 0.001
                                                } else {
                                                    def truncate_number = strDecimal.length() - 1
                                                    decimal = strDecimal.substring(0, truncate_number)
                                                    decimal = Double.parseDouble(decimal)
                                                }

                                            } else {
                                                    decimal = BigDecimal.valueOf(decimal).setScale(3, java.math.RoundingMode.HALF_UP)
                                            }
                                            currentCreateNodeValue = decimal
                                        }
                                    }


                                    currentCreateNodeName_array << currentCreateNodeName
                                    currentCreateNodeValue_array << currentCreateNodeValue
                                    println("!!!!!!!! ---- " + currentCreateNodeName_array)

                                }
                            } 
                        }
                    }
                    i++
                }
            }
        }

        println("-------------------- --- ")
        println("currentCreateNodeName_array --- " + currentCreateNodeName_array)
        println("currentCreateNodeValue_array --- " + currentCreateNodeValue_array)
        println("isCustomPropertiesPresented --- " + isCustomPropertiesPresented)
        println("countItems_CustomProperties --- " + countItems_CustomProperties)
        println("itemPos1_CustomProperties --- " + itemPos1_CustomProperties)
        println("isTemplateParametersPresented --- " + isTemplateParametersPresented)
        println("nodeValue b--b " + nodeValue)

        if((isCustomPropertiesPresented == 0 | (countItems_CustomProperties == 1 && itemPos1_CustomProperties == "mark_ID")) && isTemplateParametersPresented == 0 && nodeValue != "FREIGHT"){
            println ("TRUEERR")
        } else {
            println ("FALSERR")
        }
        

        if((isCustomPropertiesPresented == 0 | (countItems_CustomProperties == 1 && itemPos1_CustomProperties == "mark_ID")) && isTemplateParametersPresented == 0 && nodeValue != "FREIGHT"){
            println("TUrE")
            currentCreateNodeName_array.clear()
            currentCreateNodeValue_array.clear()


            def no_of_items_value = currentOrderItem.Quantity
            def length_value = currentOrderItem.Length.text().toBigDecimal()  / 1000
            length_value = length_value.stripTrailingZeros().toPlainString()
            def id_mark = currentOrderItem.Name.text()
            def bundle_no = 1
 
            currentCreateNodeName_array << "NO_OF_ITEMS"
            currentCreateNodeValue_array << no_of_items_value.toBigDecimal().toInteger()

            currentCreateNodeName_array << "LENGTH"
            //currentCreateNodeName_array << "DEBUG"
            currentCreateNodeValue_array << length_value

            currentCreateNodeName_array << "ID_MARK"
            currentCreateNodeValue_array << id_mark

            currentCreateNodeName_array << "BUNDLE_NO"
            currentCreateNodeValue_array << bundle_no

            println("no_of_items_value --- " + no_of_items_value)
            println("length_value --- " + length_value)
            println("id_mark --- " + id_mark)
            println("currentCreateNodeName_array --- " + currentCreateNodeName_array)
            println("currentCreateNodeValue_array --- " + currentCreateNodeValue_array)


            def thickness_value = currentOrderItem.Profile.@thickness.text()
            // if(thickness_value == null | thickness_value.length() == 0){
            //     thickness_value = 0
            // }

            if(thickness_value == null){
                thickness_value = 0
            }else{
                if(thickness_value.length() == 0){
                    thickness_value = 0
                }
            }

            thickness_value = thickness_value.toDouble()
            println("thickness_value === is " + thickness_value)



            if(currentOrderItem.Profile.@kind.text() == "profile" && thickness_value <= 0.9){
                valueGirth = currentOrderItem.Profile.@flangeSum.text()
                // if(valueGirth == null | valueGirth.length() == 0){
                //     valueGirth = 0
                // }

                if(valueGirth == null){
                    valueGirth = 0
                }else{
                    if(valueGirth.length() == 0){
                        valueGirth = 0
                    }
                }

                valueGirth = valueGirth.toDouble()
                
                if(currentOrderItem.Profile.@length.text().toInteger() == 150){
                    def hemRi_counter = currentOrderItem.Profile.Flanges.Flange?.count { it.@hemRi?.text()?.trim() }
                    
                    if(hemRi_counter == 0){
                        valueGirth = valueGirth - 10
                    }

                    if(hemRi_counter == 1){
                        valueGirth = valueGirth - 30
                    }

                    if(hemRi_counter == 2){
                        valueGirth = valueGirth - 60
                    }
                }


                def decimal = valueGirth
                decimal = decimal * 0.001
                decimal = BigDecimal.valueOf(decimal).setScale(4, java.math.RoundingMode.HALF_UP)
                decimal = decimal.doubleValue()
                def strDecimal = decimal.toString()
                def length = strDecimal.length()
                def index = strDecimal.indexOf('.') + 1

                def decimalLastChar = strDecimal.toString()[-1] as int


                if((length - index) == 4){
                    if(decimalLastChar >= 5){
                        def truncate_number = strDecimal.length() - 1
                        decimal = strDecimal.substring(0, truncate_number)
                        decimal = Double.parseDouble(decimal)
                        decimal = decimal + 0.001
                    } else {
                        def truncate_number = strDecimal.length() - 1
                        decimal = strDecimal.substring(0, truncate_number)
                        decimal = Double.parseDouble(decimal)
                    }

                } else {
                    decimal = BigDecimal.valueOf(decimal).setScale(3, java.math.RoundingMode.HALF_UP)
                }
                valueGirth = decimal

            }

            if(currentOrderItem.Profile.@kind.text() == "profile" && thickness_value > 0.9){
                println("---- thickness_value > 0.9 ----")
                valueGirth = currentOrderItem.Profile.@width.text()
                // if(valueGirth == null | valueGirth.length() == 0){
                //     valueGirth = 0
                // }

                if(valueGirth == null){
                    valueGirth = 0
                }else{
                    if(valueGirth.length() == 0){
                        valueGirth = 0
                    }
                }

                valueGirth = valueGirth.toDouble()


                if(currentOrderItem.Profile.@length.text().toInteger() == 150){
                    def hemRi_counter = currentOrderItem.Profile.Flanges.Flange?.count { it.@hemRi?.text()?.trim() }
                    
                    if(hemRi_counter == 0){
                        valueGirth = valueGirth - 10
                    }

                    if(hemRi_counter == 1){
                        valueGirth = valueGirth - 30
                    }

                    if(hemRi_counter == 2){
                        valueGirth = valueGirth - 60
                    }
                }

                def decimal = valueGirth
                println("decimal 1 ---- is " + decimal)
                decimal = decimal * 0.001
                println("decimal 2 ---- is " + decimal)
                decimal = BigDecimal.valueOf(decimal).setScale(4, java.math.RoundingMode.HALF_UP)
                println("decimal 3 ---- is " + decimal)
                decimal = decimal.doubleValue()
                println("decimal 3 ---- is " + decimal)
                def strDecimal = decimal.toString()
                def length = strDecimal.length()
                def index = strDecimal.indexOf('.') + 1

                def decimalLastChar = strDecimal.toString()[-1] as int
                println("index ---- is " + index)
                println("decimalLastChar ---- is " + decimalLastChar)


                if((length - index) == 4){
                    if(decimalLastChar >= 5){
                        def truncate_number = strDecimal.length() - 1
                        decimal = strDecimal.substring(0, truncate_number)
                        decimal = Double.parseDouble(decimal)
                        decimal = decimal + 0.001
                    } else {
                        def truncate_number = strDecimal.length() - 1
                        decimal = strDecimal.substring(0, truncate_number)
                        decimal = Double.parseDouble(decimal)
                    }

                } else {
                    decimal = BigDecimal.valueOf(decimal).setScale(3, java.math.RoundingMode.HALF_UP)
                }
                valueGirth = decimal
            }

            def is_material_membrane_armourplan_in_description = currentOrderItem.Description.text().contains("Membrane Armourplan")
            def is_material_membrane_armourplan_in_material = currentOrderItem.Material.text().contains("Membrane Armourplan")

            if(is_material_membrane_armourplan_in_description | is_material_membrane_armourplan_in_material){
                valueGirth = currentOrderItem.Profile.@flangeSum.text().toInteger() / 1000
            }

            currentCreateNodeName_array << "GIRTH"
            currentCreateNodeValue_array << valueGirth

        }   


        
        println("currentCreateNodeName_array 1****1 " + currentCreateNodeName_array)
        println("currentCreateNodeValue_array **** " + currentCreateNodeValue_array)
        all_nodes_name_array << currentCreateNodeName_array.clone()
        all_nodes_value_array << currentCreateNodeValue_array.clone()
        currentCreateNodeName_array.clear()
        currentCreateNodeValue_array.clear()

        println("currentCreateNodeName_array 2****2 " + currentCreateNodeName_array)
        println("currentCreateNodeValue_array **** " + currentCreateNodeValue_array)
        println("__13__-------- dummy_detailing_function debuger END --------__13__")
        

    //}
        
        return[all_nodes_name_array: all_nodes_name_array, all_nodes_value_array: all_nodes_value_array]
        
}


println("************* HELPER *************")
println("nodeValue 77777 " + nodeValue)
//!//def dummy_detailing_function_result = dummy_detailing_function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, nodeValue)
println("nodeValue 77777 " + nodeValue)
//println("dummy_detailing_function_result ---- all_nodes_name_array --- " + dummy_detailing_function_result.all_nodes_name_array)
//println("dummy_detailing_function_result ---- all_nodes_value_array --- " + dummy_detailing_function_result.all_nodes_value_array)
println("************* HELPER *************")


def z = 0
def dummy_detailing_NODE_MAKER_function = { dummy_detailing_NODE_MAKER_function_builder, node_name_parameter, node_value_parameter ->
    println("node_name_parameter ---" + node_name_parameter)
    println("node_value_parameter ---" + node_value_parameter)
    

    
    def matterial_itemCreateNodeName_global_array = node_name_parameter
    def matterial_itemCreateNodeValue_global_array = node_value_parameter
    

    def  current_node_names_array = matterial_itemCreateNodeName_global_array[0]
    def  current_node_values_array = matterial_itemCreateNodeValue_global_array[0]
    //println("node_name_parameter 123 --- " + node_name_parameter)
    //println("node_value_parameter 123 --- " + node_value_parameter)


    //def  current_node_names_array = node_name_parameter[1]
    //def  current_node_values_array = node_value_parameter[1]


    
    println("current_node_names_array --- " + current_node_names_array)
    println("current_node_values_array --- " + current_node_values_array)
    z = z +1

    i = 0
    
    if(current_node_names_array != null){
        println "current_node_names_array TYPE=${current_node_names_array.getClass()} VALUE=${current_node_names_array}"
        println "current_node_values_array TYPE=${current_node_values_array.getClass()} VALUE=${current_node_values_array}"
        println("current_node_names_array.size() --- " + current_node_names_array.size())
            while(i < current_node_names_array.size()){
                if(current_node_values_array[i] != null && current_node_values_array[i] != "" && current_node_values_array[i] != "null" ){
                    dummy_detailing_NODE_MAKER_function_builder."${current_node_names_array[i]}"(current_node_values_array[i])
                }
            i++
            }


    }
    //println("z --- " + z)
    //z = 0
    //current_node_names_array.clear()
    //current_node_values_array.clear()
}




def dummy_position_function = { kipBendexArrayDET_parameter, kipBendexArrayCNF_parameter  ->
    println("\r\n\r\n\r\n__14__-------- dummy_position_function debuger START --------__14__")

    kipBendexArrayDET = kipBendexArrayDET_parameter
    kipBendexArrayCNF = kipBendexArrayCNF_parameter
    
    



    def currentCreateNodeName_array = []
    def currentCreateNodeValue_array = []
    def all_nodes_name_array = []
    def all_nodes_value_array = []
    def q = []
    def w = []
    def final_node_name = ""
    def final_node_value = ""




    def kip_jb_dictionary_trigger = 0
    def i = 0


     ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
        def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
        def itemPos1_CustomProperties = items_CustomProperties[0]?.@name?.text()

        def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
        def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
        def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' } 



        println("isCustomPropertiesPresented --- " + isCustomPropertiesPresented)
        println("countItems_CustomProperties --- " + countItems_CustomProperties)
        println("isTemplateParametersPresented --- " + isTemplateParametersPresented)
        println("countItems_TemplateParameters --- " + countItems_TemplateParameters)

        if(isCustomPropertiesPresented != 0){
            println("TRUEe 1 ")
            if(countItems_CustomProperties == 1 && itemPos1_CustomProperties == "mark_ID"){
                println("ONE ITEM IN CUSTOM")
            } else {
                
                while(kip_jb_dictionary_trigger != 1 && i < countItems_CustomProperties){
                    if(items_CustomProperties[i]?.text().length() > 0 && items_CustomProperties[i] != null){
                        if(items_CustomProperties[i]?.@name?.text().take(3) != "BX_"){
                            if(items_CustomProperties[i]?.@name?.text() in kipBendexArrayDET){
                                //currentCreateNodeName_array << items_CustomProperties[i]?.@name?.text()
                                //currentCreateNodeValue_array << items_CustomProperties[i]?.text()
                                kip_jb_dictionary_trigger = 1
                            }

                            if(items_CustomProperties[i]?.@name?.text() in kipBendexArrayCNF){
                                //currentCreateNodeName_array << items_CustomProperties[i]?.@name?.text()
                                //currentCreateNodeValue_array << items_CustomProperties[i]?.text()
                                kip_jb_dictionary_trigger = 1
                            }
                        }
                    }

                    i++
                }
            }
            println("kip_jb_dictionary_trigger 1 ---- " + kip_jb_dictionary_trigger)

            i = 0
            //if(currentCreateNodeName_array.size() > 0){
            if(kip_jb_dictionary_trigger > 0){
                while(i < countItems_CustomProperties){
                    
                    if(items_CustomProperties[i]?.@name?.text() == "BENDEX_POSITION"){
                        //currentCreateNodeName_array << "BENDEX_POSITION"
                        //currentCreateNodeValue_array << currentOrderItem.PositionString.text()
                        final_node_name = "BENDEX_POSITION"
                        final_node_value = currentOrderItem.PositionString.text()
                    } 
                    i++
                }
            } 

        } else {
            while(kip_jb_dictionary_trigger != 1 && i < countItems_TemplateParameters){
                println("LOOPER kip_jb_dictionary_trigger ---- " + kip_jb_dictionary_trigger)
                println("LOOPER --- " + i)
                if(items_TemplateParameters[i]?.text().length() > 0 && items_TemplateParameters[i] != null){
                    if(items_TemplateParameters[i]?.@name?.text().take(3) != "BX_"){
                        if(items_TemplateParameters[i]?.@name?.text() in kipBendexArrayDET){
                            //currentCreateNodeName_array << items_TemplateParameters[i]?.@name?.text()
                            //currentCreateNodeValue_array << items_TemplateParameters[i]?.text()
                            kip_jb_dictionary_trigger = 1
                        }

                        if(items_TemplateParameters[i]?.@name?.text() in kipBendexArrayCNF){

                            //currentCreateNodeName_array << items_TemplateParameters[i]?.@name?.text()
                            //currentCreateNodeValue_array << items_TemplateParameters[i]?.text()
                            kip_jb_dictionary_trigger = 1
                        }
                    }
                }
                i++
            }
            //println("currentCreateNodeName_array --- " + currentCreateNodeName_array)
            println("AFTER kip_jb_dictionary_trigger is --- " + kip_jb_dictionary_trigger)
            //if(currentCreateNodeName_array.size() > 0){
            i = 0
            if(kip_jb_dictionary_trigger > 0){
                println("TRUE")
                while(i < countItems_TemplateParameters){
                    if(items_TemplateParameters[i]?.@name?.text() == "BENDEX_POSITION"){
                        println("BENDEX_POSITION has been found")
                        //currentCreateNodeName_array << "BENDEX_POSITION"
                        //currentCreateNodeValue_array << currentOrderItem.PositionString.text()
                        final_node_name = "BENDEX_POSITION"
                        final_node_value = currentOrderItem.PositionString.text()
                    } 
                    i++
                }
            } 
        }

        println("AFTER LOGIC final_node_name --- " + final_node_name)
        println("AFTER LOGIC final_node_value --- " + final_node_value)

        println("kip_jb_dictionary_trigger ---" + kip_jb_dictionary_trigger)
        def checkerShip = 0

        if(final_node_name.length() == 0){
            //if(kip_jb_dictionary_trigger == 0){
                if(currentOrderItem.ShoppingItemType == "Shipping"){
                    //currentCreateNodeName_array << ""
                    //currentCreateNodeValue_array << ""
                    checkerShip = 1
                }

                if(currentOrderItem.Profile.@kind.text() == "profile" | currentOrderItem.Profile.@kind.text() == "box" | currentOrderItem.Name.text() == "Adapter Profile"){
                    if(checkerShip == 0){
                        //currentCreateNodeName_array << "BENDEX_POSITION"
                        //currentCreateNodeValue_array << currentOrderItem.PositionString.text()
                        final_node_name = "BENDEX_POSITION"
                        final_node_value = currentOrderItem.PositionString.text()
                    }
                }
            //}
        }
        
    


        println("final_node_name --- " + final_node_name)
        println("final_node_value --- " + final_node_value)
        
    println("__14__-------- dummy_position_function debuger END --------__14__")
        currentCreateNodeName_array << final_node_name
        currentCreateNodeValue_array << final_node_value
        final_node_name = ""
        final_node_value = ""
        //all_nodes_name_array << currentCreateNodeName_array.clone()
        //all_nodes_value_array << currentCreateNodeValue_array.clone()
        //currentCreateNodeName_array.clear()
        //currentCreateNodeValue_array.clear()


    }
    
    i = 0

    return [
        currentCreateNodeName_array: currentCreateNodeName_array,
        currentCreateNodeValue_array: currentCreateNodeValue_array
    ]

    //return[all_nodes_name_array: all_nodes_name_array, all_nodes_value_array: all_nodes_value_array]
}



def dummy_position_function_result = dummy_position_function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF )
println("xxxxxxxxxxxxxxx HELPER xxxxxxxxxxxxxxx")
println("dummy_position_function_result ---- currentCreateNodeName_array --- " + dummy_position_function_result.currentCreateNodeName_array)
println("dummy_position_function_result ---- currentCreateNodeValue_array --- " + dummy_position_function_result.currentCreateNodeValue_array)
println("xxxxxxxxxxxxxxx HELPER xxxxxxxxxxxxxxx")


def y = 0

def dummy_position_NODE_MAKER_function = { dummy_position_NODE_MAKER_function_builder, node_name_parameter, node_value_parameter ->
    println("\r\n\r\n\r\n__15__-------- dummy_position_NODE_MAKER_function debuger START --------__15__")
    println("node_name_parameter --- " + node_name_parameter)
    println("node_value_parameter --- " + node_value_parameter)
    def node_maker_names = node_name_parameter
    def node_maker_values = node_value_parameter


    def current_node_maker_name = node_maker_names[y]
    def current_node_maker_value = node_maker_values[y]


    y = y + 1


        if(current_node_maker_name != null & current_node_maker_name != "null" & current_node_maker_value != null & current_node_maker_value != "null"){
            if(current_node_maker_name.size() != 0 & current_node_maker_value.size() != 0){
                dummy_position_NODE_MAKER_function_builder."${current_node_maker_name}"(current_node_maker_value)
            }
   
    }



    println("__15__-------- dummy_position_NODE_MAKER_function debuger END --------__15__")
}


	 builder.ORDER_CREATE {
        println("__16__-------- BUILDER debuger START --------__16__")
		"Oid"(ServiceResponse.Orders.Order.Oid)
        
        HEADER{
			CUSTOMER{
				"PO_NUM"(ServiceResponse.Orders.Order.Description)//tested
				"SOLD_TO"(ServiceResponse.Orders.Order.BACompany3)//tested
				"CONTACT"()
			}
			DELIVERY_DETAILS{
				ADDRESS{
					"SHIP_TO"(ServiceResponse.Orders.Order.SACompany3)//tested
					"NAME1"(ServiceResponse.Orders.Order.SACompany)//tested
					"STREET1"(ServiceResponse.Orders.Order.SAStreetAddress)//tested
					"STREET4"()//tested
					"STREET5"()//tested
					"POSTL_COD1"(ServiceResponse.Orders.Order.SAZIP)//tested
					"CITY1"(ServiceResponse.Orders.Order.SACity)//tested
					"REGION"(ServiceResponse.Orders.Order.SAState)//tested
					"COUNTRY"(ServiceResponse.Orders.Order.SALand)//tested
					"TRANSP_ZONE"()//tested
				}
                ServiceResponse.Orders.Order.eachWithIndex { currentOrder, idx  ->
				    "DEL_DATE"(del_date_function(currentOrder))
                }
				"DELIVERY_HEADER_TEXT"(ServiceResponse.Orders.Order.DeliveryComments)//tested
			}
			GENERAL_DATA{
				"DID"(ServiceResponse.Orders.Order.Did)//tested
				"CREATED_BY"(ServiceResponse.Orders.Order.CreatedBy)//tested
				"ORDER_TYPE"(order_type_function(get_currentOrderItem_function))
				"SALES_ORG"(sales_org_function(get_currentOrderItem_function))
				"DISTRIBUTION_CHANNEL"(distribution_channel_function(get_currentOrderItem_function))
				"DIVISION"(division_function(get_currentOrderItem_function))
				"BUILDING_SECTOR"()//tested
				"KS_HEADER_TEXT_1"(ServiceResponse.Orders.Order.Comments)//tested
                "BUSINESS_UNIT"(ServiceResponse.Orders.Order.BACompany2)
			}
         
		}
        ServiceResponse.Orders.Order.OrderItems.OrderItem.eachWithIndex { currentOrderItem, idx  ->
            
          
                MATERIAL_ITEM{
                    nodeValue = material_code__function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, dicts_list.prj_SapCodes_array, dicts_list.prj_SapCodes_FL_array, currentOrderItem)
                    println(">>> [${idx}] GLOBAL nodeValue = ${nodeValue}")
                    println("nodeValue 888888 " + nodeValue)
                    def nodeValue_test_arr_a = []
                    nodeValue_test_arr_a << nodeValue
                    println("def nodeValue_test_arr_a >>>>> " + nodeValue_test_arr_a)

                    ///// "DUMMY_SHIP"("NODE VALUE")
                    dummy_ship_function(delegate, currentOrderItem)
                    //def material_code__function_result = material_code__function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, dicts_list.prj_SapCodes_array, dicts_list.prj_SapCodes_FL_array, currentOrderItem)
                    if(nodeValue && nodeValue.length() != 0){
                        ////!"MATERIAL_CODE"(material_code__function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, dicts_list.prj_SapCodes_array, dicts_list.prj_SapCodes_FL_array, currentOrderItem))
                        "MATERIAL_CODE"(nodeValue)
                    }

                    //"MATERIAL_CODE"(material_code__function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, dicts_list.prj_SapCodes_array, currentOrderItem))
                    def order_qty_function_result = order_qty_function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, nodeValue, currentOrderItem)
                    if(order_qty_function_result == 0){
                        println("order_qty_function_result is 0. ORDER_QTY should be excluded for final XML structure")
                    } else {
                        "ORDER_QTY"(order_qty_function_result)
                    }
                    //"ORDER_QTY"(order_qty_function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, nodeValue, currentOrderItem))
                    "PLANT"(plant_function(currentOrderItem))
                    def plant_value = plant_function(currentOrderItem)
                    //"CONDITION"()
                    "PRICE"(currentOrderItem.Price)
                    "DISCOUNT"(currentOrderItem.Discount)
                    ////"DUMMY_MATERIAL_ITEM"(NODE VALUE)
                    def dummy_matterial_item_function_result = dummy_matterial_item_function(dicts_list.kipBendexArrayCNF, dicts_list.coverWidth2Dict, dicts_list.prj_coil_metal_ext_array, dicts_list.prj_coil_finish_ext_array, dicts_list.prj_coil_guige_ext_array, dicts_list.prj_coil_colour_ext_array, dicts_list.front_colour_colour, dicts_list.front_colour_type, dicts_list.front_colour_colour_IE_array, dicts_list.front_colour_type_IE, nodeValue, currentOrderItem, plant_value)
                    println("dummy_matterial_item_function_result ---- all_nodes_name_array --- " + dummy_matterial_item_function_result.all_nodes_name_array)
                    println("dummy_matterial_item_function_result ---- all_nodes_value_array --- " + dummy_matterial_item_function_result.all_nodes_value_array)
                    dummy_matterial_item_NODE_MAKER_function(delegate, dummy_matterial_item_function_result.all_nodes_name_array, dummy_matterial_item_function_result.all_nodes_value_array)
                    DETAILING{
                        // //"DUMMY_DETAILING"("NODE VALUE")
                        //println (">>> !dummy_detailing_function!: GLOBAL nodeValue = ${nodeValue}")
                        println("BEFORE dummy_detailing_function_result nodeValue ----" + nodeValue)
                        def dummy_detailing_function_result = dummy_detailing_function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, nodeValue, currentOrderItem)
                        println("dummy_detailing_function_result ---- all_nodes_name_array --- " + dummy_detailing_function_result.all_nodes_name_array)
                        println("dummy_detailing_function_result ---- all_nodes_value_array --- " + dummy_detailing_function_result.all_nodes_value_array)
                        dummy_detailing_NODE_MAKER_function(delegate, dummy_detailing_function_result.all_nodes_name_array, dummy_detailing_function_result.all_nodes_value_array)
                        ////"DUMMY_POSITION"("NODE VALUE")
                        dummy_position_NODE_MAKER_function(delegate, dummy_position_function_result.currentCreateNodeName_array, dummy_position_function_result.currentCreateNodeValue_array)

                    }
                }
            
        }
        println("__16__-------- BUILDER debuger END --------__16__")
	}
	   
    
    
	
	 

	def resultXml = writer.toString()

 

    //println(resultXml)
    //println(writer)


	def orderCustomerOid = ServiceResponse.Orders.Order.Customer.@oid.text()
    //println("orderCustomerOid is ---" + orderCustomerOid)
    def orderCustomerOid_flag = 0
    if(orderCustomerOid == "922e2c80-33aa-4c1d-b87a-9bc74649ee55" | orderCustomerOid == "2f5158e6-d64a-4de5-aeca-e4df45a7c50c"){
        //orderCustomerOid_flag true
        orderCustomerOid_flag = 1
    } else {
        //orderCustomerOid_flag false
        orderCustomerOid_flag = 0
    }

    
    //println("orderCustomerOid_flag is ---" + orderCustomerOid_flag)

    

    // def subComponentOperation = resultXml.getText('UTF-8')
    // def ServiceResponse_02 = new XmlSlurper().parseText(subComponentOperation)



println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY")
    
def writerX = new StringWriter()   // объявляем снаружи
def currentArray = []

ServiceResponse.Orders.Order.OrderItems.OrderItem.eachWithIndex { currentOrderItem, idx  -> 
    if(currentOrderItem.Profile.@kind == "composition"){
        currentArray << currentOrderItem.PositionString
     }
}



def root = new XmlParser().parseText(writer.toString())

if(currentArray.size() != 0){
    ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
        if(currentOrderItem.MasterItem && currentOrderItem.MasterItem.text()){
            def masterItem = currentOrderItem.MasterItem.text().trim()
            def positionString = currentOrderItem.PositionString.text().trim()

            currentArray.each { currentArrayValue ->
                if(currentArrayValue == positionString.split("\\.")[0]){
                    root.MATERIAL_ITEM.each { item ->
                        def bendexPos = item.BENDEX_POS.text().trim()

                        if(bendexPos == positionString){
                            def bendexPosNode = item.children().find { it.name() == 'BENDEX_POS' }
                            println("TRUE****")
                            println("bendexPosNode ----" + bendexPosNode)
                         
                                def bendexPosNodeIndex = item.children().indexOf(bendexPosNode) // вставка перед BENDEX_POS
                                def insertIndex = bendexPosNodeIndex + 1 
                                item.children().add(insertIndex, new Node(item, 'SUB_COMPONENT', masterItem))

                                // def existingSub = item.children().find { it.name() == 'SUB_COMPONENT' && it.text() == masterItem }
                                // if(!existingSub){
                                //     item.children().add(insertIndex, new Node(item, 'SUB_COMPONENT', masterItem))
                                // }
                            

                            
                            // def subComponentsAfter = item.children().findAll { it.name() == 'SUB_COMPONENT' }

                            
                            // if(subComponentsAfter.size() > 1){
                            //     subComponentsAfter[1..-1].each { redundant ->
                            //         item.remove(redundant)
                            //     }
                            // }

                            // Проверка
                            //println("Оставшиеся SUB_COMPONENT после удаления лишних:")
                            item.children().findAll { it.name() == 'SUB_COMPONENT' }.each { println(it) }
                            println(item)
                            //println("✅ SUB_COMPONENT вставлен один раз перед BENDEX_POS для позиции ${bendexPos}")
                        } else {
                            println("FALSE *****")
                        }
                    }



                }
            }
        }
    }


    // Сохраняем финальный XML в writerX
    def printer = new XmlNodePrinter(new PrintWriter(writerX))
    printer.preserveWhitespace = true
    printer.print(root)
}

// Выводим финальный XML
println(writerX.toString())

root = new XmlParser().parseText(writerX.toString())
println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm")
//println(root)


// root.MATERIAL_ITEM.each { item -> 
//     def subComponents = item.'SUB_COMPONENT'
//     println("subComponents ----- " + subComponents)
//      if (subComponents.size() > 1) {
//         subComponents[1..-1]*.replaceNode{} // Удаляем все начиная со второго
//     }

//     println(item)
//     // def subComponentsAfter = item.children().findAll { it.name() == 'SUB_COMPONENT' }
//     // println(subComponentsAfter)
//     // if(subComponentsAfter.size() != 0){
//     //     subComponentsAfter[1..-1]*.replaceNode{}
//     // }
//     // println("FATER " + subComponentsAfter)
// }


def keepFirstSubComponent(node) {
    if (!node.children()) return

    def subComponentSeen = false
    def toRemove = []

    node.children().each { child ->
        if (child instanceof Node && child.name() == 'SUB_COMPONENT') {
            if (!subComponentSeen) {
                subComponentSeen = true // оставляем первый
            } else {
                toRemove << child      // все остальные удаляем
            }
        }
    }

    toRemove.each { node.remove(it) }

    // Рекурсивно обрабатываем вложенные узлы
    node.children().each { child ->
        if (child instanceof Node) keepFirstSubComponent(child)
    }
}

// Применяем к каждому MATERIAL_ITEM
root.'MATERIAL_ITEM'.each { item ->
    keepFirstSubComponent(item)
}

// Красивый вывод с отступами
def writerZ = new StringWriter()
def printer = new XmlNodePrinter(new PrintWriter(writerZ), "    ")
printer.setPreserveWhitespace(true)
printer.print(root)

println writerZ.toString()
resultXml = writerZ.toString()


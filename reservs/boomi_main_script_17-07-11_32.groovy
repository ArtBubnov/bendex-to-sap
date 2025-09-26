import com.boomi.execution.ExecutionUtil
import java.util.Properties
import groovy.xml.*
import groovy.json.JsonSlurper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat

for (int x = 0; x < dataContext.getDataCount(); x++) {
    InputStream is = dataContext.getStream(x)
	Properties props = dataContext.getProperties(x)
	
	
	def xmlFile = dataContext.getStream(x)
	def inputXml = xmlFile.getText("UTF-8")
	def ServiceResponse = new XmlSlurper().parseText(inputXml)
	
	def writer = new StringWriter()
	def builder = new MarkupBuilder(writer)

    def get_currentOrderItem_function = ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem -> }


	def prescrit_function = { ->
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


        def prj_SapCodes_array_JSON_File = ExecutionUtil.getDynamicProcessProperty("dpp_prj_SapCodes_array")
        def prj_SapCodes_array = new JsonSlurper().parseText(prj_SapCodes_array_JSON_File)

		def prj_SapCodes_FL_array_JSON_File = ExecutionUtil.getDynamicProcessProperty("dpp_prj_SapCodes_FL_array")
        def prj_SapCodes_FL_array = new JsonSlurper().parseText(prj_SapCodes_FL_array_JSON_File)

        def prj_coil_metal_ext_array_JSON_File = ExecutionUtil.getDynamicProcessProperty("dpp_prj_coil_metal_ext_array")
        def prj_coil_metal_ext_array = new JsonSlurper().parseText(prj_coil_metal_ext_array_JSON_File)

        def prj_coil_finish_ext_array_JSON_File = ExecutionUtil.getDynamicProcessProperty("dpp_prj_coil_finish_ext_array")
        def prj_coil_finish_ext_array = new JsonSlurper().parseText(prj_coil_finish_ext_array_JSON_File)

        def prj_coil_guige_ext_array_JSON_File = ExecutionUtil.getDynamicProcessProperty("dpp_prj_coil_guige_ext_array")
        def prj_coil_guige_ext_array = new JsonSlurper().parseText(prj_coil_guige_ext_array_JSON_File)
        
        def prj_coil_colour_ext_array_JSON_File = ExecutionUtil.getDynamicProcessProperty("dpp_prj_coil_colour_ext_array")
        def prj_coil_colour_ext_array = new JsonSlurper().parseText(prj_coil_colour_ext_array_JSON_File)

        def coverWidth2Dict_JSON_File = ExecutionUtil.getDynamicProcessProperty("dpp_coverWidth2Dict")
        def coverWidth2Dict = new JsonSlurper().parseText(coverWidth2Dict_JSON_File)

        def front_colour_colour_JSON_File = ExecutionUtil.getDynamicProcessProperty("dpp_front_colour_colour_array")
        def front_colour_colour = new JsonSlurper().parseText(front_colour_colour_JSON_File)

        def front_colour_type_JSON_File = ExecutionUtil.getDynamicProcessProperty("dpp_front_colour_type")
        def front_colour_type = new JsonSlurper().parseText(front_colour_type_JSON_File)

        def front_colour_colour_IE_array_JSON_File = ExecutionUtil.getDynamicProcessProperty("dpp_front_colour_colour_IE")
        def front_colour_colour_IE_array = new JsonSlurper().parseText(front_colour_colour_IE_array_JSON_File)

        def front_colour_type_IE_array_JSON_File = ExecutionUtil.getDynamicProcessProperty("dpp_front_colour_type_IE")
        def front_colour_type_IE = new JsonSlurper().parseText(front_colour_type_IE_array_JSON_File)

        def kipBendexPrjArrayDET = ExecutionUtil.getDynamicProcessProperty("dpp_kipBendexPrjArrayDET")
        def kipBendexArrayDET = kipBendexPrjArrayDET.split(',').collect { it.trim() }

        def kipBendexPrjArrayCNF = ExecutionUtil.getDynamicProcessProperty("dpp_kipBendexPrjArrayCNF")
        def kipBendexArrayCNF = kipBendexPrjArrayCNF.split(',').collect { it.trim() }



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





def del_date_function = { ->
    def incomingDate = ServiceResponse.Orders.Order.DeliveryDate.text()
	def date = LocalDateTime.parse(incomingDate)
	def formatedDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"))	

    return formatedDate
}




def order_type_function = { currentOrderItem ->
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
    // }

    return orderType
}




def sales_org_function = { currentOrderItem ->
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

    // }

    return salesOrg
}




def distribution_channel_function = { currentOrderItem ->
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

		
	// }
    return distribution_channel_value
}




def division_function = { currentOrderItem -> 
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
    // }

    return division_value
}



// due to here we need to pass the node value and create the node it self insted original node dummy_ship
// the xml builder it self should be passed into the function as dummy_ship_function_builder paameter
def dummy_ship_function = { dummy_ship_function_builder, currentOrderItem -> 
    def dummy_ship_value = ""
    def dummy_shiping = ""

        if(currentOrderItem.count { it.ShoppingItemType.text() ==~ /.*Shipping.*/ } > 0){
            dummy_shiping = currentOrderItem.ShoppingItemType.text()
        } else {
            dummy_shiping = ""
        }
        dummy_ship_value = dummy_shiping

    dummy_ship_function_builder."ShoppingItemType"(dummy_ship_value)
}




def material_code__function = { kipBendexArrayDET_parameter, kipBendexArrayCNF_parameter, prj_SapCodes_array_parameter, currentOrderItem ->
    def dummy_ship_value = ""
    def dummy_shiping = ""

    def kipBendexArrayDET = kipBendexArrayDET_parameter
	def kipBendexArrayCNF = kipBendexArrayCNF_parameter
	def prj_SapCodes_array = prj_SapCodes_array_parameter
    def nodeValue = null

    //ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
		def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
		def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
		def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

		def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
		def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
		def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }

		
		def kipBendexDistributionListValue = ""
		def sapcode = ""
		def kipJbBSChecker = null
		def codes_checker = 0
		def bends = ""

			

		if(countItems_TemplateParameters > 1 | countItems_CustomProperties > 1){
            

			kipBendexDistributionListValue = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'DISTRIBUTION LIST' }?.text()



			if(kipBendexDistributionListValue == null){
                
				kipBendexDistributionListValue = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'DISTRIBUTION LIST' }?.text()
			}


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
				
				def bendingCount = Integer.parseInt(currentOrderItem.Profile.@bendingCount.text())
				def hemRiCount = currentOrderItem.Profile.Flanges.Flange?.count { it.@hemRi?.text()?.trim() }
				bends = bendingCount - hemRiCount
				

				if(currentOrderItem.Profile.@kind == "profile" && codes_checker == 0 && bends < 10){
                    
					if(currentOrderItem.Profile.@length != 150){
						
						def profileFromPayloadValue = currentOrderItem.Profile.@flangeSum.text()
                        

                        if(profileFromPayloadValue == null | profileFromPayloadValue.length() == 0){
                            profileFromPayloadValue = 0
                        }	

						def profileFromPayloadValue_to_number = profileFromPayloadValue.toDouble()
						def widthFL = Math.round(profileFromPayloadValue_to_number)

						def check_SapCodes_array = prj_SapCodes_array[widthFL.toString()] ?: "NO_VALUE"

						if(check_SapCodes_array != "NO_VALUE"){
							nodeValue = check_SapCodes_array
						} else {
							nodeValue = "FL1500"
						}
						codes_checker = 1
						

					}
				}


				if(currentOrderItem.Profile.@kind == "box" && codes_checker == 0){
					def profileFromPayloadValue = currentOrderItem.Profile.@width		
					def profileFromPayloadValue_to_number = profileFromPayloadValue.toDouble()
					def widthFL = Math.round(profileFromPayloadValue_to_number)

					def check_SapCodes_array = prj_SapCodes_array[widthFL.toString()] ?: "NO_VALUE"

					if(check_SapCodes_array != "NO_VALUE"){
						nodeValue = check_SapCodes_array
					} else {
						nodeValue = "FL1500"
					}
					codes_checker = 1


					
				}
				if(currentOrderItem.Profile.@kind == "profile" && codes_checker == 0 && bends < 10){
					nodeValue = "FLPROF"
					codes_checker = 1
				}
			}
		}

		if(nodeValue == null){
			nodeValue = ""
		}
        
        
	//}
    return nodeValue
}

def nodeValue = material_code__function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, dicts_list.prj_SapCodes_array, get_currentOrderItem_function)
println("Node value is --- " + nodeValue )




def order_qty_function = { kipBendexArrayDET_parameter, kipBendexArrayCNF_parameter, nodeValue_parameter, currentOrderItem ->
    def finalvar_value = ""
    def kipBendexArrayDET = kipBendexArrayDET_parameter
    def kipBendexArrayCNF = kipBendexArrayCNF_parameter

        def finalvar = ""
        def kip_jb_string_of_nodes_checker = ""
        def kip_jb_dictionary_trigger = 0
        def i = 0
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

          	

            if(isCustomPropertiesPresented != 0){
                if(countItems_CustomProperties == 1 && items_CustomProperties[0]?.@name?.text() == "mark_ID"){
                    println("ONE ITEM IN CUSTOM")
                } else {
                    while(kip_jb_dictionary_trigger != 1 && i < countItems_CustomProperties){
                        if(items_CustomProperties[i]?.text().length() > 0 && items_CustomProperties[i] != null){
                            
                            if(items_CustomProperties[i]?.@name?.text().take(3) != "BX_"){
                                
                                if(items_CustomProperties[i]?.@name?.text() in kipBendexArrayDET ){
                                    kip_jb_string_of_nodes_checker += "true,"
                                } else {
                                    kip_jb_string_of_nodes_checker = "1"
                                }

                                if(items_CustomProperties[i]?.@name?.text() in kipBendexArrayCNF ){
                                    kip_jb_string_of_nodes_checker += "true,"
                                } else {
                                    kip_jb_string_of_nodes_checker = "1"
                                }
                            }
                        }
                        i++
                    }
                }

                if(kip_jb_string_of_nodes_checker.length() == 0){
                    if(currentOrderItem.ShoppingItemType.text().contains("Shipping")){
                        ORDER_QTY = currentOrderItem.Quantity.text()
                    } else {
                        ORDER_QTY = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0].text()
                    }
                } else {
                    ORDER_QTY = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0].text()
                }
                    

            } else {
                
                if(countItems_TemplateParameters != 0){
                    while(kip_jb_dictionary_trigger != 1 && i < countItems_TemplateParameters){

                        if(items_TemplateParameters[i]?.text().length() > 0 && items_TemplateParameters[i] != null){

                            if(items_TemplateParameters[i]?.@name?.text().take(3) != "BX_"){
                                if(items_TemplateParameters[i]?.@name?.text() in kipBendexArrayDET ){
                                    kip_jb_string_of_nodes_checker += "true,"
                                } else {
                                    kip_jb_string_of_nodes_checker = "1"
                                }

                                if(items_TemplateParameters[i]?.@name?.text() in kipBendexArrayCNF ){
                                    kip_jb_string_of_nodes_checker += "true,"
                                } else {
                                    kip_jb_string_of_nodes_checker = "1"
                                }
                            }
                        }
                        i++
                    } 


                    if(kip_jb_string_of_nodes_checker.length() == 0){
                        if(currentOrderItem.ShoppingItemType.text().contains("Shipping")){
                            println("Shipping TRUE ")
                            ORDER_QTY = currentOrderItem.Quantity.text()
                        } else {
                            ORDER_QTY = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0].text()
                        }    
                    } else {
                        ORDER_QTY = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0].text()
                    }
                }
                
                
            }
            

    




            if( isCustomPropertiesPresented == 0 | (countItems_CustomProperties == 1 && items_CustomProperties[0]?.@name?.text() == "mark_ID") && isTemplateParametersPresented == 0 && nodevalue != "Shipping"){
                ORDER_QTY = currentOrderItem.Quantity.text()
            }

            
            finalvar = new BigDecimal(ORDER_QTY).setScale(3, BigDecimal.ROUND_HALF_UP)
            
        
            if( isCustomPropertiesPresented == 0 
                |
                (countItems_CustomProperties == 1 && items_CustomProperties[0]?.@name?.text() == "mark_ID")
                && 
                isTemplateParametersPresented == 0 
            ){
                
                def checker = 0
                if(currentOrderItem.Profile.@kind == "profile" && currentOrderItem.Profile.@length == 150 && checker == 0){
                    finalvar = currentOrderItem.Quantity.text()
                    checker = 1
                }
                
                def length = 0
                
                
                def current_orderItem_profile = currentOrderItem.Profile.text()
                if(current_orderItem_profile.length() != 0){
                    length = currentOrderItem.Profile.@length.toDouble()
                }
                

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

        finalvar_value = finalvar
        
    //}
    
    return finalvar_value
}




def plant_function = { currentOrderItem -> 
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

    // }
    return finish

}






def dummy_matterial_item_function = { kipBendexArrayCNF_parameter, prj_coil_metal_ext_array_parameter, prj_coil_finish_ext_array_parameter, prj_coil_guige_ext_array_parameter, prj_coil_colour_ext_array_parameter, front_colour_colour_parameter, front_colour_type_parameter, front_colour_colour_IE_array_parameter, front_colour_type_IE_parameter, nodeValue_parameter ->
            def currentCreateNodeName_array = []
            def currentCreateNodeValue_array = []
            def all_nodes_name_array = []
            def all_nodes_value_array = []

            def currentCreateNodeName = ""
            def currentCreateNodeValue = ""
            
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


            def kip_jb_positionStringFlag = "THEREISNOPOS888"




        ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
        //currentOrderItem.each { currentOrderItem ->
            def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
            def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
            def itemPos1_CustomProperties = items_CustomProperties[0]?.@name?.text()
            def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()

            def isTemplateParametersPresented = currentOrderItem.TemplateParameters.Item.size()
            def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }
            def itemPos1_TemplateParameters = items_TemplateParameters[0]?.@name?.text()
            def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()

            def z_pc_checker = 0
            def IE_checker = 0



            def currency = ""

            def mockTester = 1


            

            if(ServiceResponse.Orders.Order.Oid.text() == "45d3d4ec-3f9e-4a12-abfc-ddc4e2b11f9a"){
                currency = "GBP"
            }

            if(ServiceResponse.Orders.Order.Oid.text() == "f6e2a38e-610a-4027-9493-70cf8b74ddc4"){
                currency = "EUR"
            }

            if(!currency){
                currency = "THEREISNOCURRENCY!!@"
            }
                
            if(currency != "THEREISNOCURRENCY!!@"){
                //"CURRENCY"(currency)
                currentCreateNodeName_array << "CURRENCY"
                currentCreateNodeValue_array << currency

            }	


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
                    if(isCustomPropertiesPresented != 0){
                        def items = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
                        def itemPos1 = items[0]?.@name?.text()
                        def countItems = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
                        def kip_bendex_fast_track = ""


                        if(countItems == 1 && itemPos1 == "mark_ID"){
                            println("ONE ITEM IN CUSTOM")
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



            if((isCustomPropertiesPresented == 0 | countItems_CustomProperties == 1 && itemPos1_CustomProperties == "mark_ID") && isTemplateParametersPresented == 0 && nodeValue == "FREIGHT"){
            //if(mockTester == 1){	
                def length_flag = ""

                if(ServiceResponse.Orders.Order.Oid.text() == "45d3d4ec-3f9e-4a12-abfc-ddc4e2b11f9a"){
                    currency = "GBP"
                }

                if(ServiceResponse.Orders.Order.Oid.text() == "f6e2a38e-610a-4027-9493-70cf8b74ddc4"){
                    currency = "EUR"
                }

                if(!currency){
                    currency = "THEREISNOCURRENCY!!@"
                }

                if(currency != "THEREISNOCURRENCY!!@"){
                    //"CURRENCY"(currency)
                    currentCreateNodeName_array << "CURRENCY"
                    currentCreateNodeValue_array << currency
                }

                "BENDEX_POS"(currentOrderItem.PositionString.text())

                def lengthFromPayloadValue = currentOrderItem.Profile.@length
                def lengthFromPayloadValue_to_number = lengthFromPayloadValue.toDouble()

                
                if(lengthFromPayloadValue_to_number > 3000){
                    length_flag = "3+"
                } else {
                    length_flag = "3-"
                }
                

                def countProfileFlangesAngle = currentOrderItem.Profile.Flanges.Flange?.count { it.@angle?.text()?.trim() }
                def countProfileFlangeLength = currentOrderItem.Profile.Flanges.Flange?.count { it.@length?.text()?.trim() }
                def countProfileHemRiCount = currentOrderItem.Profile.Flanges.Flange?.count { it.@hemRi?.text()?.trim() }

                def angels = ""
                def lengthes = ""
                def bends = ""

                if(countProfileFlangesAngle > 0 | countProfileFlangeLength > 0){
                    angels = countProfileFlangesAngle
                    lengthes = countProfileFlangeLength
                    bends = countProfileHemRiCount
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

                
                def coil_metal_ext_value = prj_coil_metal_ext_array[material_oid.toString()]
                def coil_finish_ext_value = prj_coil_finish_ext_array[material_oid.toString()]
                def coil_gauge_ext_value = prj_coil_guige_ext_array[material_oid.toString()]
                def coil_colour_ext_value = prj_coil_colour_ext_array[material_oid.toString()]

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
        

                //"COIL_METAL_EXT"(prj_coil_metal_ext_array[material_oid.toString()] ?: "NO_VALUE")
                //"COIL_FINISH_EXT"(prj_coil_finish_ext_array[material_oid.toString()] ?: "NO_VALUE")
                //"COIL_GAUGE_EXT"(prj_coil_guige_ext_array[material_oid.toString()] ?: "NO_VALUE")
                //"COIL_COLOUR_EXT"(prj_coil_colour_ext_array[material_oid.toString()] ?: "NO_VALUE")
                //"LENGTH_FLAG"(length_flag)


                def coverWidth2Dict_value = ""
                def coverWidth2Dict_checker = coverWidth2Dict[material_oid.toString()]
                if(coverWidth2Dict_checker != null){
                    coverWidth2Dict_value = coverWidth2Dict_checker
                }



                if(coverWidth2Dict_value == "NO_VALUE"){
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

            if((isCustomPropertiesPresented == 0 | countItems_CustomProperties == 1 && itemPos1_CustomProperties == "mark_ID") && isTemplateParametersPresented == 0){
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

            //"EXTRA_OPERATIONS"(extra_operations)
            currentCreateNodeName_array << "EXTRA_OPERATIONS"
            currentCreateNodeValue_array << extra_operations

            def hemRi_counter = 0


            if(currentOrderItem.Profile.@kind == "composition"){
                kip_jb_positionStringFlag = currentOrderItem.PositionString.text()
            }
                




            def masterItem = currentOrderItem.MasterItem.text()
            if(!masterItem){
                masterItem = "NOHEREISEMPTY123"
            }


            if(currentOrderItem.PositionString.text().toString().count('.') > 0){
                if(currentOrderItem.PositionString.text().toString().take(kip_jb_positionStringFlag.toString().length()) == kip_jb_positionStringFlag){
                    if(masterItem != "NOHEREISEMPTY123"){
                        //"SUB_COMPONENT"(masterItem)
                        currentCreateNodeName_array << "SUB_COMPONENT"
                        currentCreateNodeValue_array << masterItem
                    }
                }
            }

            def frontColor_oid = currentOrderItem.FrontColor.@oid

            if(!frontColor_oid){
                frontColor_oid = "NOHEREISEMPTY123"
            }


            if(frontColor_oid != "NOHEREISEMPTY123" && z_pc_checker == 0){
                if(IE_checker == 0){


                    def z_pc_colour_node_value = front_colour_colour[frontColor_oid.toString()]
                    def z_pc_type_node_value = front_colour_type[frontColor_oid.toString()]
                    def colour_node_value = front_colour_colour_IE_array[frontColor_oid.toString()]
                    def painted_node_value = front_colour_type_IE[frontColor_oid.toString()]

                    if(z_pc_colour_node_value != null){
                        currentCreateNodeName_array << "Z_PC_COLOUR"
                        currentCreateNodeValue_array << z_pc_colour_node_value
                    }

                    if(z_pc_type_node_value != null){
                        currentCreateNodeName_array << "Z_PC_TYPE"
                        currentCreateNodeValue_array << z_pc_type_node_value
                    }

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

            if((isCustomPropertiesPresented == 0 | countItems_CustomProperties == 1) && isTemplateParametersPresented == 0 && nodeValue != "FREIGHT"){
                if(nodeValue != "FLPROF"){
                    //"NO_ITEMS"(currentOrderItem.Quantity)
                    currentCreateNodeName_array << "NO_ITEMS"
                    currentCreateNodeValue_array << currentOrderItem.Quantity
                }
            }


            
            all_nodes_name_array << currentCreateNodeName_array.clone()
            all_nodes_value_array << currentCreateNodeValue_array.clone()
            
            currentCreateNodeName_array.clear()
            currentCreateNodeValue_array.clear()
        }
            
            return[all_nodes_name_array: all_nodes_name_array, all_nodes_value_array: all_nodes_value_array]
            
}


def dummy_matterial_item_function_result = dummy_matterial_item_function(dicts_list.kipBendexArrayCNF, dicts_list.prj_coil_metal_ext_array, dicts_list.prj_coil_finish_ext_array, dicts_list.prj_coil_guige_ext_array, dicts_list.prj_coil_colour_ext_array, dicts_list.front_colour_colour, dicts_list.front_colour_type, dicts_list.front_colour_colour_IE_array, dicts_list.front_colour_type_IE, nodeValue)


def p = 0

def dummy_matterial_item_NODE_MAKER_function = { dummy_matterial_item_NODE_MAKER_function_builder, currentCreateNodeName_array_parameter, currentCreateNodeValue_array_parameter ->
    def matterial_itemCreateNodeName_global_array = currentCreateNodeName_array_parameter
    def matterial_itemCreateNodeValue_global_array = currentCreateNodeValue_array_parameter

    def  current_node_names_array = matterial_itemCreateNodeName_global_array[p]
    def  current_node_values_array = matterial_itemCreateNodeValue_global_array[p]
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





def dummy_detailing_function = { kipBendexArrayDET_parameter, kipBendexArrayCNF_parameter, nodeValue_parameter ->


    def kipJbBSChecker = 1
    nodeValue = nodeValue_parameter
    kipBendexArrayDET = kipBendexArrayDET_parameter
    kipBendexArrayCNF = kipBendexArrayCNF_parameter



    def currentCreateNodeName_array = []
    def currentCreateNodeValue_array = []
    def all_nodes_name_array = []
    def all_nodes_value_array = []

    ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->

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
                                            if(getProfileWidth.text().length() == 0){
                                                getProfileWidth = "empty"
                                            }


                                        
                                            if(getProfileWidth != "empty"){
                                                
                                                getProfileWidth = currentOrderItem.Profile.@width.text()
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


        if((isCustomPropertiesPresented == 0 | countItems_CustomProperties == 1 && itemPos1_CustomProperties == "mark_ID") && isTemplateParametersPresented == 0){
            currentCreateNodeName_array.clear()
            currentCreateNodeValue_array.clear()


            def no_of_items_value = currentOrderItem.Quantity
            def length_value = currentOrderItem.Length.text().toBigDecimal()  / 1000
            def id_mark = currentOrderItem.Length.Name


            def thickness_value = currentOrderItem.Profile.@thickness.text().toDouble()

            if(currentOrderItem.Profile.@kind.text() == "profile" && thickness_value <= 0.9){
                valueGirth = currentOrderItem.Profile.@flangeSum.text().toDouble()
                
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
                valueGirth = currentOrderItem.Profile.@width.text().toDouble()
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
            
        }   

        

        all_nodes_name_array << currentCreateNodeName_array.clone()
        all_nodes_value_array << currentCreateNodeValue_array.clone()
    }
        
        return[all_nodes_name_array: all_nodes_name_array, all_nodes_value_array: all_nodes_value_array]
        
}



def dummy_detailing_function_result = dummy_detailing_function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, nodeValue)
//println("************* HELPER *************")
//println("dummy_detailing_function_result ---- all_nodes_name_array --- " + dummy_detailing_function_result.all_nodes_name_array)
//println("dummy_detailing_function_result ---- all_nodes_value_array --- " + dummy_detailing_function_result.all_nodes_value_array)
//println("************* HELPER *************")


def z = 0
def dummy_detailing_NODE_MAKER_function = { dummy_detailing_NODE_MAKER_function_builder, node_name_parameter, node_value_parameter ->

    
    def matterial_itemCreateNodeName_global_array = node_name_parameter
    def matterial_itemCreateNodeValue_global_array = node_value_parameter
    
    def  current_node_names_array = matterial_itemCreateNodeName_global_array[z]
    def  current_node_values_array = matterial_itemCreateNodeValue_global_array[z]
    
    z = z +1

    i = 0
    
    if(current_node_names_array != null){
        while(i < current_node_names_array.size()){
            if(current_node_values_array[i] != null && current_node_values_array[i] != "" && current_node_values_array[i] != "null" ){
                dummy_detailing_NODE_MAKER_function_builder."${current_node_names_array[i]}"(current_node_values_array[i])
            }
        i++
        }
    }

}




def dummy_position_function = { kipBendexArrayDET_parameter, kipBendexArrayCNF_parameter  ->


    kipBendexArrayDET = kipBendexArrayDET_parameter
    kipBendexArrayCNF = kipBendexArrayCNF_parameter
    



    def currentCreateNodeName_array = []
    def currentCreateNodeValue_array = []
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


        if(isCustomPropertiesPresented != 0){
            if(countItems_CustomProperties == 1 && itemPos1_CustomProperties == "mark_ID"){
                println("ONE ITEM IN CUSTOM")
            } else {
                while(kip_jb_dictionary_trigger != 1 && i < countItems_CustomProperties){
                    if(items_CustomProperties[i]?.text().length() > 0 && items_CustomProperties[i] != null){
                        if(items_CustomProperties[i]?.@name?.text().take(3) != "BX_"){
                            if(items_CustomProperties[i]?.@name?.text() in kipBendexArrayDET){
                                currentCreateNodeName_array << items_CustomProperties[i]?.@name?.text()
                                currentCreateNodeValue_array << items_CustomProperties[i]?.text()
                                kip_jb_dictionary_trigger = 1
                            }

                            if(items_CustomProperties[i]?.@name?.text() in kipBendexArrayCNF){
                                currentCreateNodeName_array << items_CustomProperties[i]?.@name?.text()
                                currentCreateNodeValue_array << items_CustomProperties[i]?.text()
                                kip_jb_dictionary_trigger = 1
                            }
                        }
                    }

                    i++
                }
            }


    
            if(currentCreateNodeName_array.size() > 0){
                while(i < countItems_CustomProperties){
                    if(items_CustomProperties[i]?.@name?.text() == "BENDEX_POSITION"){
                        currentCreateNodeName_array << "BENDEX_POSITION"
                        currentCreateNodeValue_array << currentOrderItem.PositionString.text()
                        final_node_name = "BENDEX_POSITION"
                        final_node_value = currentOrderItem.PositionString.text()
                    } 
                    i++
                }
            } 

        } else {
            while(kip_jb_dictionary_trigger != 1 && i < countItems_TemplateParameters){
                if(items_TemplateParameters[i]?.text().length() > 0 && items_TemplateParameters[i] != null){
                    if(items_TemplateParameters[i]?.@name?.text().take(3) != "BX_"){
                        if(items_TemplateParameters[i]?.@name?.text() in kipBendexArrayDET){
                            currentCreateNodeName_array << items_TemplateParameters[i]?.@name?.text()
                            currentCreateNodeValue_array << items_TemplateParameters[i]?.text()
                            kip_jb_dictionary_trigger = 1
                        }

                        if(items_TemplateParameters[i]?.@name?.text() in kipBendexArrayCNF){

                            currentCreateNodeName_array << items_TemplateParameters[i]?.@name?.text()
                            currentCreateNodeValue_array << items_TemplateParameters[i]?.text()
                            kip_jb_dictionary_trigger = 1
                        }
                    }
                }
                i++
            }

            if(currentCreateNodeName_array.size() > 0){
                while(i < countItems_TemplateParameters){
                    if(items_TemplateParameters[i]?.@name?.text() == "BENDEX_POSITION"){
                        currentCreateNodeName_array << "BENDEX_POSITION"
                        currentCreateNodeValue_array << currentOrderItem.PositionString.text()
                        final_node_name = "BENDEX_POSITION"
                        final_node_value = currentOrderItem.PositionString.text()
                    } 
                    i++
                }
            } 
        }


        def checkerShip = 0
        if(currentCreateNodeName_array.size() > 0){
            if(currentOrderItem.ShoppingItemType == "Shipping"){
                currentCreateNodeName_array << ""
                currentCreateNodeValue_array << ""
                checkerShip = 1
            }

            if(currentOrderItem.Profile.@kind.text() == "profile" | currentOrderItem.Profile.@kind.text() == "box" | currentOrderItem.Name.text() == "Adapter Profile"){
                if(checkerShip == 0){
                    currentCreateNodeName_array << "BENDEX_POSITION"
                    currentCreateNodeValue_array << currentOrderItem.PositionString.text()
                    final_node_name = "BENDEX_POSITION"
                    final_node_value = currentOrderItem.PositionString.text()
                }
            }
        }


    }
    

    return [
        currentCreateNodeName_array: currentCreateNodeName_array,
        currentCreateNodeValue_array: currentCreateNodeValue_array
    ]
}



def dummy_position_function_result = dummy_position_function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF )



def y = 0

def dummy_position_NODE_MAKER_function = { dummy_position_NODE_MAKER_function_builder, node_name_parameter, node_value_parameter ->
    def node_maker_names = node_name_parameter
    def node_maker_values = node_value_parameter

    def current_node_maker_name = node_maker_names[y]
    def current_node_maker_value = node_maker_values[y]

    y = y + 1


        if(current_node_maker_name != null & current_node_maker_name != "null" & current_node_maker_name.size() != 0 & current_node_maker_value != null & current_node_maker_value != "null" & current_node_maker_value.size() != 0){
            dummy_position_NODE_MAKER_function_builder."${current_node_maker_name}"(current_node_maker_value)
 
   
    }



    
}


   
	 builder.ORDER_CREATE {
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
				"DEL_DATE"(del_date_function())
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
        ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
            MATERIAL_ITEM{
                ///// "DUMMY_SHIP"("NODE VALUE")
                dummy_ship_function(delegate, currentOrderItem)
                
                "MATERIAL_CODE"(material_code__function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, dicts_list.prj_SapCodes_array, currentOrderItem))
                
                "ORDER_QTY"(order_qty_function(dicts_list.kipBendexArrayDET, dicts_list.kipBendexArrayCNF, nodeValue, currentOrderItem))
                "PLANT"(plant_function(currentOrderItem))
                "CONDITION"()
                "PRICE"(currentOrderItem.Price)
                "DISCOUNT"(currentOrderItem.Discount)
                ////"DUMMY_MATERIAL_ITEM"(NODE VALUE)
                dummy_matterial_item_NODE_MAKER_function(delegate, dummy_matterial_item_function_result.all_nodes_name_array, dummy_matterial_item_function_result.all_nodes_value_array)
                DETAILING{
                    // //"DUMMY_DETAILING"("NODE VALUE")
                    dummy_detailing_NODE_MAKER_function(delegate, dummy_detailing_function_result.all_nodes_name_array, dummy_detailing_function_result.all_nodes_value_array)
                    ////"DUMMY_POSITION"("NODE VALUE")
                    dummy_position_NODE_MAKER_function(delegate, dummy_position_function_result.currentCreateNodeName_array, dummy_position_function_result.currentCreateNodeValue_array)
                    
                }
            }
        }
	}
	
	
	
	def resultXml = writer.toString()
	
	def orderCustomerOid = ServiceResponse.Orders.Order.Customer.@oid.text()
    def orderCustomerOid_flag = 0
    if(orderCustomerOid == "922e2c80-33aa-4c1d-b87a-9bc74649ee55" | orderCustomerOid == "2f5158e6-d64a-4de5-aeca-e4df45a7c50c"){
        //orderCustomerOid_flag true
        orderCustomerOid_flag = 1
    } else {
        //orderCustomerOid_flag false
        orderCustomerOid_flag = 0
    }
	props.setProperty("document.dynamic.userdefined.orderCustomerOid_flag", orderCustomerOid_flag?.toString())
	
	dataContext.storeStream(new ByteArrayInputStream(resultXml.getBytes("UTF-8")), props)
	
}
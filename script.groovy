import groovy.xml.*
import groovy.json.JsonSlurper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


	//def xmlFile = new File('payload.xml')
	def xmlFile = new File('payload2.xml')


	def prj_SapCodes_array_JSON_File = new File('prj_SapCodes_array.json').text
	def prj_SapCodes_array = new JsonSlurper().parseText(prj_SapCodes_array_JSON_File)

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
///--------------------------------------------------------

	def front_colour_colour_JSON_File = new File('front_colour.colour.json').text
	def front_colour_colour = new JsonSlurper().parseText(front_colour_colour_JSON_File)

	def front_colour_type_JSON_File = new File('front_colour.type.json').text
	def front_colour_type = new JsonSlurper().parseText(front_colour_type_JSON_File)

	def front_colour_colour_IE_array_JSON_File = new File('front_colour.colour_IE.json').text
	def front_colour_colour_IE_array = new JsonSlurper().parseText(front_colour_colour_IE_array_JSON_File)

	def front_colour_type_IE_array_JSON_File = new File('front_colour.type_IE.json').text
	def front_colour_type_IE = new JsonSlurper().parseText(front_colour_type_IE_array_JSON_File)










//coverWidth2Dict


	def inputXml = xmlFile.getText('UTF-8')
	def ServiceResponse = new XmlSlurper().parseText(inputXml)

	def writer = new StringWriter()
	def builder = new MarkupBuilder(writer)

	//def kipBendexPrjArrayDET = "ANGLE, ANGLE1_2, ANGLE_1, ANGLE_2, BENDEX_POSITION, BENDS1, BRACKET_TYPE, BULK_ORDER_REF, BUNDLE_MARK, BUNDLE_NO, COVER_WIDTH, CUT_BACK, DIMENSION_A, DIMENSION_B, DIMENSION_C, DIMENSION_CA, DIMENSION_D, DIMENSION_E, DIMENSION_F, DIMENSION_L, DIMENSION_X, DIMENSION_Y, END_TYPE, END_TYPE2, FACE_SIZE, FHEIGHT, DET_FINISH, GALV_LENGTH, GIRTH, GROOVE_A, GROOVE_B, GROOVE_C, GUTTER_GTH, HANDING, ID_MARK, IS_SPECIALS_1, IS_SPECIALS_2, JI_PRODUCTION_NO, JI_PURCHASE_LINE_NO, JI_PURCHASE_NO, KSP_MULTIBEAM_STUBB, LENGTH, LINER_GRTH, LINER_LGTH, LOAD_NO, NO_OF_ITEMS, OA_LENGTH, ORIENTATION, POSITION, PVC_LENGTH, RADIUS, REBATE_B, REBATE_C, RETURN_END, RUN_NO, SLDCS_CUT, SLDCS_CUT_BACK, SLDCS_CUT_LEN, SLDCS_CUT_WID, SLDCS_PANQTY, THERMAL_BREAK, TILELENGTH, TYPE, TYPE_OF_CLEAT, WL_TYPE, Z_FP_STACKING"
	def kipBendexPrjArrayDET = new File('kipBendexPrjArrayDET.txt').text
	def kipBendexArrayDET = kipBendexPrjArrayDET.split(',').collect { it.trim() }

	def kipBendexPrjArrayCNF = new File('kipBendexPrjArrayCNF.txt').text
	def kipBendexArrayCNF = kipBendexPrjArrayCNF.split(',').collect { it.trim() }




	
	
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
				//"DEL_DATE"{
					def incomingDate = ServiceResponse.Orders.Order.DeliveryDate.text()
					def date = LocalDateTime.parse(incomingDate)
					def formatedDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"))					
					println("formatedDate")
					println(formatedDate)
				//}
				"DEL_DATE"(formatedDate)				
				"DELIVERY_HEADER_TEXT"(ServiceResponse.Orders.Order.DeliveryComments)//tested
			}
			GENERAL_DATA{
				"DID"(ServiceResponse.Orders.Order.Did)//tested
				"CREATED_BY"(ServiceResponse.Orders.Order.CreatedBy)//tested



//--------------"ORDER_TYPE"{ START------------------------------------------------------------------------------------------------
//VS code tested
				ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem_forOrderType ->
					def isCustomPropertiesPresented_forOrderType = currentOrderItem_forOrderType.CustomProperties.PropertiesValues.'@xml:space'.text().length()
					def countItems_CustomProperties_forOrderType = currentOrderItem_forOrderType.CustomProperties.PropertiesValues.Item.size()
					def items_CustomProperties_forOrderType = currentOrderItem_forOrderType.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

					def isTemplateParametersPresented_forOrderType = currentOrderItem_forOrderType.TemplateParameters.'@xml:space'.text().length()
					def countItems_TemplateParameters_forOrderType = currentOrderItem_forOrderType.TemplateParameters.Item.size()
					def items_TemplateParameters_forOrderType = currentOrderItem_forOrderType.TemplateParameters.'**'.findAll { it.name() == 'Item' }
					
					def i = 0
					
					def isOrderTypePresented_forOrderType = false
					def orderType_value = ""
					
					if (isCustomPropertiesPresented_forOrderType != 0){
						while(i < countItems_CustomProperties_forOrderType){
							if(items_CustomProperties_forOrderType[i]?.@name?.text() == "ORDER_TYPE"){
								isOrderTypePresented_forOrderType = true
								orderType_value = currentOrderItem_forOrderType.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'ORDER_TYPE' }?.text()
							}
							i++
						} 


						if(isOrderTypePresented_forOrderType){
							orderType_value = orderType_value
						} else {
							orderType_value = "ZKCO"
						}
					} else {
						if(isTemplateParametersPresented_forOrderType != 0){
							while(i < countItems_TemplateParameters_forOrderType){
								if(items_TemplateParameters_forOrderType[i]?.@name?.text() == "ORDER_TYPE"){
									isOrderTypePresented_forOrderType = true
									orderType_value = currentOrderItem_forOrderType.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'ORDER_TYPE' }?.text()						
								}
								i++
							}

							if(isOrderTypePresented_forOrderType){
								orderType_value = orderType_value
							} else {
								orderType_value = "ZKCO"
							}

						} else {
							orderType_value = "ZKCO"

						}
					}
					"ORDER_TYPE"(orderType_value)
				}
 
 //VS code tested
//**************}"ORDER_TYPE"{ END*******************************************************



//--------------"SALES_ORG"{ START---------------------------------------------------------------------
//VS code tested
println("\r\n" + "\r\n" + "\r\n" + "------- !!!SALES_ORG LOG START!!! --------")
				ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem_SALES_ORG ->
					def isCustomPropertiesPresented_forSalesOrg = currentOrderItem_SALES_ORG.CustomProperties.PropertiesValues.'@xml:space'.text().length()
					def countItems_CustomProperties_forSalesOrg = currentOrderItem_SALES_ORG.CustomProperties.PropertiesValues.Item.size()
					def items_CustomProperties_forSalesOrg = currentOrderItem_SALES_ORG.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

					def isTemplateParametersPresented_forSalesOrg = currentOrderItem_SALES_ORG.TemplateParameters.'@xml:space'.text().length()
					def countItems_TemplateParameters_forSalesOrg = currentOrderItem_SALES_ORG.TemplateParameters.Item.size()
					def items_TemplateParameters_forSalesOrg = currentOrderItem_SALES_ORG.TemplateParameters.'**'.findAll { it.name() == 'Item' }

					def isSalesOrgPresented = false					
					def sales_org_value = ""
					def i = 0



					if(isCustomPropertiesPresented_forSalesOrg != 0){
						if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
							sales_org_value = "IE10"
						} else {
							while(i < countItems_CustomProperties_forSalesOrg){
								if(items_CustomProperties_forSalesOrg[i]?.@name?.text() == "SALES_ORG"){
									isSalesOrgPresented = true
								}
								i++	
							}

							if(isSalesOrgPresented){
								sales_org_value = currentOrderItem_SALES_ORG.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'SALES_ORG' }?.text()
							} else {
								sales_org_value = "GB40"
							}
						}
					} else {
						if(isTemplateParametersPresented_forSalesOrg != 0){ 
							if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
								sales_org_value = "IE10"
							} else {
								while(i < countItems_TemplateParameters_forSalesOrg){
									if(items_TemplateParameters_forSalesOrg[i]?.@name?.text() == "SALES_ORG"){
										isSalesOrgPresented = true
									}
									i++
								} 

								if(isSalesOrgPresented){
									sales_org_value = currentOrderItem_SALES_ORG.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'SALES_ORG' }?.text()
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
					"SALES_ORG"(sales_org_value)
				}
println("\r\n" + "\r\n" + "\r\n" + "------- !!!SALES_ORG LOG END!!! --------")
//VS code tested
//**************"SALES_ORG"{ END**********************************************************************
				


//--------------"DISTRIBUTION_CHANNEL"{ START---------------------------------------------------------------------
//	VS code tested		
				//"DISTRIBUTION_CHANNEL"{
println("\r\n" + "\r\n" + "\r\n" + "------- !!!DISTRIBUTION_CHANNEL LOG START!!! --------")
				ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem_DISTRIBUTION_CHANNEL ->
					def isCustomPropertiesPresented_forDC = currentOrderItem_DISTRIBUTION_CHANNEL.CustomProperties.PropertiesValues.'@xml:space'.text().length()
					def countItems_CustomProperties_forDC = currentOrderItem_DISTRIBUTION_CHANNEL.CustomProperties.PropertiesValues.Item.size()
					def items_CustomProperties_forDC = currentOrderItem_DISTRIBUTION_CHANNEL.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

					def isTemplateParametersPresented_forDC = currentOrderItem_DISTRIBUTION_CHANNEL.TemplateParameters.'@xml:space'.text().length()
					def countItems_TemplateParameters_forDC = currentOrderItem_DISTRIBUTION_CHANNEL.TemplateParameters.Item.size()
					def items_TemplateParameters_forDC = currentOrderItem_DISTRIBUTION_CHANNEL.TemplateParameters.'**'.findAll { it.name() == 'Item' }

					def isDistributionChannelPresented = false
					def distribution_channel = ""
					def i = 0

					if(isCustomPropertiesPresented_forDC != 0){
						if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
							distribution_channel = "I1" 
						} else {
							while(i < countItems_CustomProperties_forDC){
								if(items_CustomProperties_forDC[i]?.@name?.text() == "DISTRIBUTION_CHANNEL"){
									isDistributionChannelPresented = true
								}
								i++
							}

							if(isDistributionChannelPresented){
								distribution_channel = currentOrderItem_DISTRIBUTION_CHANNEL.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'DISTRIBUTION_CHANNEL' }?.text()
							} else {
								distribution_channel = "G4"
							}
						}
					} else{
						if(isTemplateParametersPresented_forDC != 0){
							if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
								while(i < countItems_TemplateParameters_forDC){
									if(items_TemplateParameters_forDC[i]?.@name?.text() == "DISTRIBUTION_CHANNEL"){
										isDistributionChannelPresented = true
									}
									i++

									if(isDistributionChannelPresented){
										distribution_channel = currentOrderItem_DISTRIBUTION_CHANNEL.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'DISTRIBUTION_CHANNEL' }?.text()
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
					"DISTRIBUTION_CHANNEL"(distribution_channel)
				}
println("\r\n" + "\r\n" + "\r\n" + "------- !!!DISTRIBUTION_CHANNEL  LOG END!!! --------")  
				//}
//	VS code tested				
//**************"DISTRIBUTION_CHANNEL"{ END**********************************************************************


//--------------"DIVISION"{ START---------------------------------------------------------------------
				//"DIVISION"{
println("\r\n" + "\r\n" + "\r\n" + "------- !!!DIVISION LOG START!!! --------")
				ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem_DIVISION ->
					def isCustomPropertiesPresented_forDIVISION = currentOrderItem_DIVISION.CustomProperties.PropertiesValues.'@xml:space'.text().length()
					def countItems_CustomProperties_forDIVISION = currentOrderItem_DIVISION.CustomProperties.PropertiesValues.Item.size()
					def items_CustomProperties_forDIVISION = currentOrderItem_DIVISION.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

					def isTemplateParametersPresented_forDIVISION = currentOrderItem_DIVISION.TemplateParameters.'@xml:space'.text().length()
					def countItems_TemplateParameters_forDIVISION = currentOrderItem_DIVISION.TemplateParameters.Item.size()
					def items_TemplateParameters_forDIVISION = currentOrderItem_DIVISION.TemplateParameters.'**'.findAll { it.name() == 'Item' }

					def isDivisionPresented = false
					def division = ""
					def i = 0

					

					if(isCustomPropertiesPresented_forDIVISION != 0){
						println("HERE")
						if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
							division = "K1"
						} else {
							while(i < countItems_CustomProperties_forDIVISION){
								if(countItems_CustomProperties_forDIVISION[i]?.@name?.text() == "DIVISION"){
									isDistributionChannelPresented = true
								}
								i++
							}
							if(isDistributionChannelPresented){
								division = currentOrderItem_DIVISION.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'DIVISION' }?.text()
							} else {
								division = "15"
							}
						}
					} else {
						if(isTemplateParametersPresented_forDIVISION != 0){
							if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
								division = "K1"
							} else {
								while(i < countItems_TemplateParameters_forDIVISION){
									if(countItems_TemplateParameters_forDIVISION[i]?.@name?.text() == "DIVISION"){
										isDistributionChannelPresented = true
									}
									i++

									if(isDistributionChannelPresented){
										division = currentOrderItem_DIVISION.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'DIVISION' }?.text()
									} else {
										division = "15"
									}
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
					"DIVISION"(division)
				
				}
println("\r\n" + "\r\n" + "\r\n" + "------- !!!DIVISION LOG END!!! --------")
				//}
//**************"DIVISION"{ END**********************************************************************				
				"BUILDING_SECTOR"()//tested
				"KS_HEADER_TEXT_1"(ServiceResponse.Orders.Order.Comments)//tested
			}
		}
		MATERIAL_ITEM{
			ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
				def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.'@xml:space'.text().length()
				def isTemplateParametersPresented = currentOrderItem.TemplateParameters.'@xml:space'.text().length()
				
				// !!! should be changed to read BOOMI DPP kip.bendex.prj_array_DET
				

//--------------"DUMMY_SHIP"{ START---------------------------------------------------------------------
				
				

//**************"DUMMY_SHIP"{ END**********************************************************************




//--------------MATERIAL_CODE logic  block------------------------------------------
				// logic start
				
				def materialCodeNodeValue  = ""

				if(isCustomPropertiesPresented != 0){
					def items = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
					def itemPos1 = items[0]?.@name?.text()
					def countItems = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
					//def loopStoper = kipDictionaryTrigger
					def loopStoper = 0
					def i = 0
					def materialCodeNodeValueFlag = 0
					
					
					if(countItems == 1 && itemPos1 == "mark_ID"){
						println("ONE ITEM IN CUSTOM")
					} else {
						while (loopStoper !=1 && i < countItems){
							if(items[i]?.text().length() > 0 && items[i] != null){
								if(items[i]?.@name?.text().take(3) != "BX_"){
									if(items[i]?.@name?.text() in kipBendexArrayDET ){

										materialCodeNodeValueFlag = 1

									}

									if(items[i]?.@name?.text() in kipBendexArrayCNF ){
																	
										materialCodeNodeValueFlag = 1

									}
								}
							}
							i++
						}
					}

					
				
					if(materialCodeNodeValueFlag == 0){
						if(currentOrderItem.ShoppingItemType.text().contains("Shipping")){
							materialCodeNodeValue = currentOrderItem.Description.text().replace('&#xD', '')

							materialCodeNodeValue = materialCodeNodeValue.replace('/', '')
							materialCodeNodeValue = materialCodeNodeValue.replace('\\', '')
							materialCodeNodeValue = materialCodeNodeValue.replace('~', '')
							materialCodeNodeValue = materialCodeNodeValue.replace('%', '')
							materialCodeNodeValue = materialCodeNodeValue.replace('*', '')
							materialCodeNodeValue = materialCodeNodeValue.replace(':', '')
							materialCodeNodeValue = materialCodeNodeValue.replace('\r\n', '')
							materialCodeNodeValue = materialCodeNodeValue.replace('\r', '')
							materialCodeNodeValue = materialCodeNodeValue.replace('\n', '')

							"MATERIAL_CODE"(materialCodeNodeValue)
						}else{
							materialCodeNodeValue = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()
						}
					}else{
						materialCodeNodeValue = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()
					}
					

				}




			
					def TemplateParametersItems = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }
					def countTemplateParametersItems = currentOrderItem.TemplateParameters.Item.size()

					def CustomPropertiesItems = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
					def countCustomPropertiesItems = currentOrderItem.CustomProperties.PropertiesValues.Item.size()

					def nodeValue = null
					def kipBendexDistributionListValue = ""
					def sapcode = ""
					def kipJbBSChecker = null
					def codes_checker = 0



					if(countTemplateParametersItems > 1 | countCustomPropertiesItems > 1){
						
						kipBendexDistributionListValue = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'DISTRIBUTION LIST' }?.text()
						
						
						if(kipBendexDistributionListValue == null){
							kipBendexDistributionListValue = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'DISTRIBUTION LIST' }?.text()
						}

						if(kipBendexDistributionListValue == "Facades"){
							nodeValue = 'FacadesTrue'
						}


						if(kipBendexDistributionListValue != "Facades"){
							sapcode = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()
							nodeValue = currentOrderItem.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()

							if(nodeValue == null){
								sapcode = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()
								nodeValue = currentOrderItem.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'SAPCODE' }?.text()
							}
						}
						println("nodeValue")
						println(nodeValue)

						//$kip.bendex.if_item_is_shipping = ServiceResponse$Order$OrderItems$OrderItem.ShoppingItemType$;

					} else {
						def kipBendexDecisionTppvExist = false;
						def kipBendexIfItemIsShipping = currentOrderItem.ShoppingItemType

						println(kipBendexIfItemIsShipping)

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
							def bends = bendingCount - hemRiCount
							

							if(currentOrderItem.Profile.@kind == "profile" && codes_checker == 0 && bends < 10){
								if(currentOrderItem.Profile.@length != 150){
									
									def profileFromPayloadValue = currentOrderItem.Profile.@flangeSum						
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


							println("---- TEST ----")
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

								
								println("---- TEST ----")
							}

							if(currentOrderItem.Profile.@kind == "profile" && codes_checker == 0 && bends < 10){
								nodeValue = "FLPROF"
								codes_checker = 1
							}
						}
					}



				"MATERIAL_CODE"(nodeValue)






				//not needed ? } 


			





































				
				// logic END
//--------------MATERIAL_CODE logic  block------------------------------------------




				
				"ORDER_QTY"("!!!__TBD_SCRIPT__!!!")
				"PLANT"("!!!__TBD_SCRIPT__!!!")
				"CONDITION"("SHOULD BE EMPTY")
				"PRICE"(currentOrderItem.Price)
				"DISCOUNT"(currentOrderItem.Discount)
//--------------"DUMMY_MATERIAL_ITEM"{----------------------------------------------
		
				//"DUMMY_MATERIAL_ITEM"{
					
					def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
					def itemPos1_CustomProperties = items_CustomProperties[0]?.@name?.text()
					def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()

					def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }
					def itemPos1_TemplateParameters = items_TemplateParameters[0]?.@name?.text()
					def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()

					def z_pc_checker = 0
					def IE_checker = 0

					

					def currency = ""

					def mockTester = 1

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
							"CURRENCY"(currency)
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

						println("safe_edge_nodevalue")
						println(safe_edge_nodevalue)

						//
						//def tester = ""
						println("material_oid")
						println(material_oid)
						println("length_flag")
						println(length_flag)

						"COIL_METAL_EXT"(prj_coil_metal_ext_array[material_oid.toString()] ?: "NO_VALUE")
						"COIL_FINISH_EXT"(prj_coil_finish_ext_array[material_oid.toString()] ?: "NO_VALUE")
						"COIL_GAUGE_EXT"(prj_coil_guige_ext_array[material_oid.toString()] ?: "NO_VALUE")
						"COIL_COLOUR_EXT"(prj_coil_colour_ext_array[material_oid.toString()] ?: "NO_VALUE")
						"LENGTH_FLAG"(length_flag)


						def coverWidth2Dict_value = coverWidth2Dict[material_oid.toString()] ?: "NO_VALUE"
						println("coverWidth2Dict_value")
						println(coverWidth2Dict_value)

						if(coverWidth2Dict_value == "NO_VALUE"){
							"COVER_WIDTH2"(1208)
						} else {
							"COVER_WIDTH2"(coverWidth2Dict_value)
						}

						def check = 0
					
						if(nodeValue == "FLPROF"){
							"BENDS"(30)
							check = 1
						}

						if(currentOrderItem.Profile.@kind == "profile" && check == 0){
							"BENDS"(bends)
						}

						if(currentOrderItem.Profile.@kind == "profile"){
							"SAFE_EDGE"(safe_edge_nodevalue)
						}

						if(currentOrderItem.Profile.@kind == "box"){
							"BENDS"(currentOrderItem.Profile.@bendingCount)
						}




					} else { //--------------- big else start
						println("BIG ELSE")

					

						// ************ from HERE **************************************************************************
						//tested


						//--------------"CURRENCY node creation logic  block
						// logic start
						

						сurrency = ""

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
							"CURRENCY"(currency)
						}	
						// node creation "node name"(node value)
						


						//tested
						// logic end
						//--------------"CURRENCY node creation logic  block



						//VS CODE tested
						//--------------IE_checker define logic  block
						// logic start
						

						if (isCustomPropertiesPresented != 0) {
							println("isCustomPropertiesPresented true")
							if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
								IE_checker = 1
							}
						} else {
							println("isCustomPropertiesPresented false")
							if(isTemplateParametersPresented != 0){
								println("isTemplateParametersPresented true")
								if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
									IE_checker = 1
								}
							}else {
								println("isTemplateParametersPresented false")
								if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
									IE_checker = 1
								}
							}
						}
						
						def checker = 0
						
						i = 0
						def fast_track_value = ""
						println(isCustomPropertiesPresented)
						println(isTemplateParametersPresented)
						println(nodeValue)
						def currentCreateNodeName = ""
						def currentCreateNodeValue = ""


						if(isCustomPropertiesPresented != 0 | isTemplateParametersPresented != 0 | nodeValue == 'FREIGHT'){
							if(isCustomPropertiesPresented != 0){
								def items = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
								def itemPos1 = items[0]?.@name?.text()
								def countItems = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
								def kip_bendex_fast_track = ""

								println(items)
								println(itemPos1)
								println(countItems)
								if(countItems == 1 && itemPos1 == "mark_ID"){
									println("ONE ITEM IN CUSTOM")
								} else {
									println("FALSE")


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

													"${currentCreateNodeName}"("${currentCreateNodeValue}")

												}
											}

											if(items[i]?.@name?.text() == "PACKAGING_SURCHARGE"){
												currentCreateNodeName = items[i]?.@name?.text()
												currentCreateNodeValue = items[i]?.text()
												"${currentCreateNodeName}"("${currentCreateNodeValue}")
											}
										}
										i++
									}






								}
								
							} else {
								def items = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }
								def itemPos1 = items[0]?.@name?.text()
								def countItems = currentOrderItem.TemplateParameters.Item.size()

							
								println(items)
								println(itemPos1)
								println(countItems)

								while (i < countItems){
									if(isTemplateParametersPresented != 0){
										println("TRUE 1")
										if(items[i]?.@name?.text().take(3) != "BX_" | items[i]?.@name?.text() == "BX_NO_OF_ITEMS"){
											if(items[i]?.@name?.text() in kipBendexArrayCNF){
												if(items[i]?.@name?.text() != "CONDITION"){
													currentCreateNodeName = items[i]?.@name?.text()
													currentCreateNodeValue = items[i]?.text()

													
													if(items[i]?.@name?.text() == "COLOUR"){
														println("TRUE5")
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
													"${currentCreateNodeName}"("${currentCreateNodeValue}")											
												}
											}
										}


										if(items[i]?.@name?.text() == "PACKAGING_SURCHARGE"){
											currentCreateNodeName = items[i]?.@name?.text()
											currentCreateNodeValue = items[i]?.text()
											"${currentCreateNodeName}"("${currentCreateNodeValue}")
										}
									}
									i++
								}
							}
						} 

						"BENDEX_POS"(currentOrderItem.PositionString.text())

						// ************ till HERE **************************************************************************
					} // //--------------- big else end

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
						"TAPERED"("Y")
					}
				
					"EXTRA_OPERATIONS"(extra_operations)

					def hemRi_counter = 0

					def kip_jb_positionStringFlag = 0
					if(currentOrderItem.Profile.@kind == "composition"){
						kip_jb_positionStringFlag = currentOrderItem.PositionString.text()
					}
						

					println("HERE")


					def masterItem = currentOrderItem.MasterItem.text()
					if(!masterItem){
						masterItem = "NOHEREISEMPTY123"
					}


					if(currentOrderItem.PositionString.text().toString().count('.') > 0){
						if(currentOrderItem.PositionString.text().toString().take(kip_jb_positionStringFlag.length()) == kip_jb_positionStringFlag){
							if(masterItem != "NOHEREISEMPTY123"){
								"SUB_COMPONENT"(masterItem)
							}
						}
					}

					def frontColor_oid = currentOrderItem.FrontColor.@oid

					if(!frontColor_oid){
						frontColor_oid = "NOHEREISEMPTY123"
					}

					if(frontColor_oid != "NOHEREISEMPTY123" && z_pc_checker == 0){
						if(IE_checker == 0){
							"Z_PC_COLOUR"(front_colour_colour[frontColor_oid.toString()] ?: "NO_VALUE")
							"Z_PC_TYPE"(front_colour_type[frontColor_oid.toString()] ?: "NO_VALUE")
							"COLOUR"(front_colour_colour_IE_array[frontColor_oid.toString()] ?: "NO_VALUE")
							"PAINTED"(front_colour_type_IE[frontColor_oid.toString()] ?: "NO_VALUE")
						}
					}

					if((isCustomPropertiesPresented == 0 | countItems_CustomProperties == 1) && isTemplateParametersPresented == 0 && nodeValue != "FREIGHT"){
						if(nodeValue != "FLPROF"){
							"NO_ITEMS"(currentOrderItem.Quantity)
						}
					}

				//}//DUMMY_MATERIAL_ITEM tail


//front_colour_colour
//front_colour_type
//front_colour_colour_IE_array
//front_colour_type_IE
				




//--------------MATERIAL_CODE logic  block------------------------------------------
				//}
				DETAILING{
					//"DUMMY_DETAILING"{
					println("\r\n" + "\r\n" + "\r\n" + "------- LOG START --------")
					def kip_jb_string_of_nodes_detail = ""
					def currentCreateNodeValue = 0
					def girth_checker = 0
					def valueGirth = ""
					

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
														if(!getProfileWidth){
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

															def decimalLastChar = strDecimal[-1] as int


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

													"${currentCreateNodeName}"("${currentCreateNodeValue}")

												}						
											}
										}
									}
									i++
								}
							}
						}
					}



						println("\r\n" + "\r\n" + "\r\n" + "------- LOG STOP --------")
					//}//DUMMY_DETAILING tail 




					"DUMMY_POSITION"("!!!__TBD_SCRIPT__!!!")
				}
			}
		}
	}


def resultXml = writer.toString()

//println("\r\n" + "\r\n" + "\r\n" + "------- LOG START --------")
//println(resultXml)
				//println("\r\n" + "\r\n" + "\r\n" + "------- LOG START --------")
				//println("isCustomPropertiesPresented before")
				//println(isCustomPropertiesPresented)
				//println("\r\n" + "\r\n" + "\r\n" + "------- LOG END --------")


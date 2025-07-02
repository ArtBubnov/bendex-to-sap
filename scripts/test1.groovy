import groovy.xml.*

    def xmlFile = new File('payload.xml')
	def inputXml = xmlFile.getText('UTF-8')
	def ServiceResponse = new XmlSlurper().parseText(inputXml)

	def writer = new StringWriter()
	def builder = new MarkupBuilder(writer)

	def currentCreateNodeName_array = []
    def currentCreateNodeValue_array = []
	//temp
	def nodeValue =""
    def dicst_script_return = evaluate(new File("scripts/dicts.groovy"))
	//temp
    def kipBendexArrayDET = dicst_script_return.kipBendexArrayDET
    def kipBendexArrayCNF = dicst_script_return.kipBendexArrayCNF
	def front_colour_colour = dicst_script_return.front_colour_colour
	def front_colour_colour_IE_array = dicst_script_return.front_colour_colour
	kip_jb_positionStringFlag = "THEREISNOPOS888"




ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
	def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.'@xml:space'.text().length()
	def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
	def itemPos1_CustomProperties = items_CustomProperties[0]?.@name?.text()
	def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()

	def isTemplateParametersPresented = currentOrderItem.TemplateParameters.'@xml:space'.text().length()
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




//***************+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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

		
		//
		//def tester = ""
		
		"COIL_METAL_EXT"(prj_coil_metal_ext_array[material_oid.toString()] ?: "NO_VALUE")
		"COIL_FINISH_EXT"(prj_coil_finish_ext_array[material_oid.toString()] ?: "NO_VALUE")
		"COIL_GAUGE_EXT"(prj_coil_guige_ext_array[material_oid.toString()] ?: "NO_VALUE")
		"COIL_COLOUR_EXT"(prj_coil_colour_ext_array[material_oid.toString()] ?: "NO_VALUE")
		"LENGTH_FLAG"(length_flag)


		def coverWidth2Dict_value = coverWidth2Dict[material_oid.toString()] ?: "NO_VALUE"
		
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
		def currentCreateNodeName = ""
		def currentCreateNodeValue = ""


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

	//"EXTRA_OPERATIONS"(extra_operations)
	currentCreateNodeName_array << "EXTRA_OPERATIONS"
	currentCreateNodeValue_array << extra_operations

	def hemRi_counter = 0


	if(currentOrderItem.Profile.@kind == "composition"){
		kip_jb_positionStringFlag = currentOrderItem.PositionString.text()
	}
		

	println("HERE")


	def masterItem = currentOrderItem.MasterItem.text()
	if(!masterItem){
		masterItem = "NOHEREISEMPTY123"
	}


	if(currentOrderItem.PositionString.text().toString().count('.') > 0){
	println("HERE")
	println("kip_jb_positionStringFlag is " + kip_jb_positionStringFlag)
	//println("kip_jb_positionStringFlag len is " + kip_jb_positionStringFlag.toString().length())
		if(currentOrderItem.PositionString.text().toString().take(kip_jb_positionStringFlag.toString().length()) == kip_jb_positionStringFlag){
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
			println("front_colour_colour[frontColor_oid.toString()] is " + front_colour_colour[frontColor_oid.toString()])
			def test = front_colour_colour[frontColor_oid.toString()?: "NO_VALUE"]
			test = front_colour_colour["fox"?: "NO_VALUE"]
			println("test is " + test)

			def z_pc_colour_node_value = front_colour_colour[frontColor_oid.toString()]
			def z_pc_type_node_value = front_colour_colour[frontColor_oid.toString()]
			def colour_node_value = front_colour_colour[frontColor_oid.toString()]
			def painted_node_value = front_colour_colour[frontColor_oid.toString()]

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

			//"Z_PC_COLOUR"(test ?: "NO_VALUE")
			//"Z_PC_TYPE"(front_colour_type[frontColor_oid.toString()] ?: "NO_VALUE")
			//"COLOUR"(front_colour_colour_IE_array[frontColor_oid.toString()] ?: "NO_VALUE")
			//"PAINTED"(front_colour_type_IE[frontColor_oid.toString()] ?: "NO_VALUE")
		}
	}

	if((isCustomPropertiesPresented == 0 | countItems_CustomProperties == 1) && isTemplateParametersPresented == 0 && nodeValue != "FREIGHT"){
		if(nodeValue != "FLPROF"){
			//"NO_ITEMS"(currentOrderItem.Quantity)
			currentCreateNodeName_array << "NO_ITEMS"
			currentCreateNodeValue_array << currentOrderItem.Quantity
		}
	}


	//def currentCreateNodeName_array = []
    //def currentCreateNodeValue_array = []
	("currentCreateNodeName_array is :" + currentCreateNodeName_array)
	println("currentCreateNodeValue_array is :" + currentCreateNodeValue_array)
	//}//DUMMY_MATERIAL_ITEM tail
}
import groovy.xml.*

    def xmlFile = new File('payload2.xml')
	def inputXml = xmlFile.getText('UTF-8')
	def ServiceResponse = new XmlSlurper().parseText(inputXml)

	def writer = new StringWriter()
	def builder = new MarkupBuilder(writer)
	def dummy_ship_value = ""
    def dummy_shiping = ""


	def dicst_script_return = evaluate(new File("scripts/dicts.groovy"))
	def kipBendexArrayDET = dicst_script_return.kipBendexArrayDET
	def kipBendexArrayCNF = dicst_script_return.kipBendexArrayCNF
	def prj_SapCodes_array = dicst_script_return.prj_SapCodes_array





    ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
		def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.'@xml:space'.text().length()
		def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
		def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

		def isTemplateParametersPresented = currentOrderItem.TemplateParameters.'@xml:space'.text().length()
		def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
		def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }


		def nodeValue = null
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

		

	}

	println("nodeValue is :" + nodeValue)
import groovy.xml.*

    def xmlFile = new File('payload.xml')
	def inputXml = xmlFile.getText('UTF-8')
	def ServiceResponse = new XmlSlurper().parseText(inputXml)

	def writer = new StringWriter()
	def builder = new MarkupBuilder(writer)
	def distribution_channel_value = ""


    ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
		def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.'@xml:space'.text().length()
		def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
		def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

		def isTemplateParametersPresented = currentOrderItem.TemplateParameters.'@xml:space'.text().length()
		def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
		def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }

		def isDistributionChannelPresented = false
		def distribution_channel = ""
		def i = 0

		if(isCustomPropertiesPresented != 0){
			println("isCustomPropertiesPresented is: " + isCustomPropertiesPresented) 
			if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
				println("ServiceResponse.Orders.Order.BALand is: " + ServiceResponse.Orders.Order.BALand)
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
				println("isTemplateParametersPresented is: " + isTemplateParametersPresented)
				if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
					distribution_channel = "I1"
					println("ServiceResponse.Orders.Order.BALand is: " + ServiceResponse.Orders.Order.BALand) 
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
				println("")
				if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
					distribution_channel = "I1"
				} else {
					distribution_channel = "G4"
				}
			}
		}
		distribution_channel_value += distribution_channel

		println("FINAL distribution_channel_value is: " + distribution_channel_value)
	}



return [distribution_channel_value: distribution_channel_value]
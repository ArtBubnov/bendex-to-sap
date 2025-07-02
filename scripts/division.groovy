import groovy.xml.*

    def xmlFile = new File('payload.xml')
	def inputXml = xmlFile.getText('UTF-8')
	def ServiceResponse = new XmlSlurper().parseText(inputXml)

	def writer = new StringWriter()
	def builder = new MarkupBuilder(writer)
	def division_value = ""




ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
	def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.'@xml:space'.text().length()
	def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
	def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

	def isTemplateParametersPresented = currentOrderItem.TemplateParameters.'@xml:space'.text().length()
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
	division_value += division
}

return [division_value: division_value]

import groovy.xml.*

    def xmlFile = new File('payload.xml')
	def inputXml = xmlFile.getText('UTF-8')
	def ServiceResponse = new XmlSlurper().parseText(inputXml)

	def writer = new StringWriter()
	def builder = new MarkupBuilder(writer)
	def orderType = ""


ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
	def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.'@xml:space'.text().length()
	def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
	def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

	def isTemplateParametersPresented = currentOrderItem.TemplateParameters.'@xml:space'.text().length()
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

	orderType += orderType_value
}

return [orderType: orderType]
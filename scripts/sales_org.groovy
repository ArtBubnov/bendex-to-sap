import groovy.xml.*

    def xmlFile = new File('payload.xml')
	def inputXml = xmlFile.getText('UTF-8')
	def ServiceResponse = new XmlSlurper().parseText(inputXml)

	def writer = new StringWriter()
	def builder = new MarkupBuilder(writer)
	def salesOrg = ""



ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
	def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.'@xml:space'.text().length()
	def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
	def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

	def isTemplateParametersPresented = currentOrderItem.TemplateParameters.'@xml:space'.text().length()
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
	salesOrg += sales_org_value
    
}



return [salesOrg: salesOrg]
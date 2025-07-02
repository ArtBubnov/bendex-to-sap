import groovy.xml.*

    def xmlFile = new File('payload2.xml')
	def inputXml = xmlFile.getText('UTF-8')
	def ServiceResponse = new XmlSlurper().parseText(inputXml)

	def writer = new StringWriter()
	def builder = new MarkupBuilder(writer)
	def dummy_ship_value = ""
    def dummy_shiping = ""



    ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
        if(currentOrderItem.count { it.ShoppingItemType.text() ==~ /.*Shipping.*/ } > 0){
            dummy_shiping = currentOrderItem.ShoppingItemType.text()
        } else {
            dummy_shiping = ""
        }
        dummy_ship_value += dummy_shiping
        println("dummy_ship_value is: " + dummy_ship_value)
    }





println(dummy_ship_value)
return [dummy_ship_value: dummy_ship_value]
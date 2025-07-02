import groovy.xml.*

def xmlFile = new File('payload2.xml')
def inputXml = xmlFile.getText('UTF-8')
def ServiceResponse = new XmlSlurper().parseText(inputXml)

def writer = new StringWriter()
def builder = new MarkupBuilder(writer)

def dicst_script_return = evaluate(new File("scripts/dicts.groovy"))
def kipBendexArrayDET = dicst_script_return.kipBendexArrayDET
def kipBendexArrayCNF = dicst_script_return.kipBendexArrayCNF





    def finalvar = ""
    def kip_jb_string_of_nodes_checker = ""
    def kip_jb_dictionary_trigger = 0
    def i = 0
    def ORDER_QTY = ""
    def nodevalue = ""
    

    ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
    
        def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.'@xml:space'.text().length()
        def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
        def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

        def isTemplateParametersPresented = currentOrderItem.TemplateParameters.'@xml:space'.text().length()
        def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
        def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' }

        
    

        if(isCustomPropertiesPresented != 0){
            if(countItems_CustomProperties == 1 && items_CustomProperties[0]?.@name?.text() == "mark_ID"){
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
            while(kip_jb_dictionary_trigger != 1 && i < countItems_TemplateParameters){
                if(items_TemplateParameters[i]?.text().length() > 0 && items_TemplateParameters[i] != null){

                    if(items_TemplateParameters[i]?.@name?.text().take(3) != "BX_"){
                        if(items_TemplateParameters[i]?.@name?.text() in dicst_script_return.kipBendexArrayDET ){
                            kip_jb_string_of_nodes_checker += "true,"
                        } else {
                            kip_jb_string_of_nodes_checker = "1"
                        }

                        if(items_TemplateParameters[i]?.@name?.text() in dicst_script_return.kipBendexArrayCNF ){
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
                    ORDER_QTY = currentOrderItem.Quantity.text()
                } else {
                    ORDER_QTY = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0].text()
                }    
            } else {
                ORDER_QTY = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' && it.@name == 'ORDER_QTY' }[0].text()
            }
        
        }
    

    
    if( isCustomPropertiesPresented == 0 
        | 
        (
            countItems_CustomProperties == 1 
            && 
            items_CustomProperties[0]?.@name?.text() == "mark_ID"
        ) 
        && 
        isTemplateParametersPresented == 0 
        && 
        nodevalue != "Shipping"
    ){
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

        def length = currentOrderItem.Profile.@length.toDouble()
        if(currentOrderItem.Profile.@kind == "profile" && currentOrderItem.Profile.@length != 150 && checker == 0){
            finalvar = (length/1000).toDouble()
            finalvar = new BigDecimal(finalvar).setScale(3, BigDecimal.ROUND_HALF_UP)
            finalvar = finalvar * currentOrderItem.Quantity.text()

            checker = 1
        }

        if(currentOrderItem.Profile.@kind == "box" && checker == 0){
            finalvar = (length/1000).toDouble()
            finalvar = new BigDecimal(finalvar).setScale(3, BigDecimal.ROUND_HALF_UP)
            finalvar = finalvar * currentOrderItem.Quantity.text()

            checker = 1
        }

        if(currentOrderItem.ShoppingItemType.text().contains("Shipping") && checker == 0){
            finalvar = currentOrderItem.Quantity.text()
            checker = 1
        }
    }
}


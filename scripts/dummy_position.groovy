import groovy.xml.*

   def xmlFile = new File('payload.xml')
def inputXml = xmlFile.getText('UTF-8')
def ServiceResponse = new XmlSlurper().parseText(inputXml)

def writer = new StringWriter()
def builder = new MarkupBuilder(writer)



//to be deleted
    def dicst_script_return = evaluate(new File("scripts/dicts.groovy"))
    def kipBendexArrayDET = dicst_script_return.kipBendexArrayDET
    def kipBendexArrayCNF = dicst_script_return.kipBendexArrayCNF


//to be deleted

    def currentCreateNodeName_array = []
    def currentCreateNodeValue_array = []





def kip_jb_dictionary_trigger = 0
def i = 0


ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->
    def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.'@xml:space'.text().length()
    def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
    def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
    def itemPos1_CustomProperties = items_CustomProperties[0]?.@name?.text()

    def isTemplateParametersPresented = currentOrderItem.TemplateParameters.'@xml:space'.text().length()
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
                } 
                i++
            }
        } 
    }


    def checkerShip = 0
    if(currentCreateNodeName_array.size() > 0){
        if(currentOrderItem.ShoppingItemType == "Shipping"){
            currentCreateNodeName_array.clear()
            currentCreateNodeValue_array.clear()
            checkerShip = 1
        }

        if(currentOrderItem.Profile.@kind.text() == "profile" | currentOrderItem.Profile.@kind.text() == "box" | currentOrderItem.Name.text() == "Adapter Profile"){
            if(checkerShip == 0){
                currentCreateNodeName_array << "BENDEX_POSITION"
                currentCreateNodeValue_array << currentOrderItem.PositionString.text()
            }
        }
    }
    
}


//println("items_CustomProperties " + i + " is: TRUE")
//println("items_CustomProperties " + i + " is: FALSe")
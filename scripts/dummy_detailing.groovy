import groovy.xml.*

   def xmlFile = new File('payload.xml')
def inputXml = xmlFile.getText('UTF-8')
def ServiceResponse = new XmlSlurper().parseText(inputXml)

def writer = new StringWriter()
def builder = new MarkupBuilder(writer)

//to be deleted
    def kipJbBSChecker = 1
    def nodeValue = ""
    def dicst_script_return = evaluate(new File("scripts/dicts.groovy"))
    def kipBendexArrayDET = dicst_script_return.kipBendexArrayDET
    def kipBendexArrayCNF = dicst_script_return.kipBendexArrayCNF
//to be deleted 

    def currentCreateNodeName_array = []
    def currentCreateNodeValue_array = []

ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem ->

    def kip_jb_string_of_nodes_detail = ""
    def currentCreateNodeName = ""
    def currentCreateNodeValue = 0
    def girth_checker = 0
    def valueGirth = 0
    def i = 0

    def isCustomPropertiesPresented = currentOrderItem.CustomProperties.PropertiesValues.'@xml:space'.text().length()
    def countItems_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.Item.size()
    def items_CustomProperties = currentOrderItem.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }
    def itemPos1_CustomProperties = items_CustomProperties[0]?.@name?.text()

    def isTemplateParametersPresented = currentOrderItem.TemplateParameters.'@xml:space'.text().length()
    def countItems_TemplateParameters = currentOrderItem.TemplateParameters.Item.size()
    def items_TemplateParameters = currentOrderItem.TemplateParameters.'**'.findAll { it.name() == 'Item' } 


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
                                        if(getProfileWidth.text().length() == 0){
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

                                            def decimalLastChar = strDecimal.toString()[-1] as int


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
                                    currentCreateNodeName_array << currentCreateNodeName
                                    currentCreateNodeValue_array << currentCreateNodeValue
                                    //"${currentCreateNodeName}"("${currentCreateNodeValue}")

                                }						
                            }
                        }
                    }
                    i++
                }
            }
        } else {
            while(i < countItems_TemplateParameters){
                if(items_TemplateParameters[i]?.text().length() > 0 && items_TemplateParameters[i] != null){
                    if(items_TemplateParameters[i]?.@name?.text().take(3) != "BX_"){
                        if(items_TemplateParameters[i]?.@name?.text() in kipBendexArrayDET){
                            if(items_TemplateParameters[i]?.@name?.text() != "BENDEX_POSITION"){
                                currentCreateNodeName = items_TemplateParameters[i]?.@name?.text()
                                currentCreateNodeValue = items_TemplateParameters[i]?.text()

                                if(items_TemplateParameters[i]?.@name?.text() == "GIRTH"){
                                    def getProfileWidth = currentOrderItem.Profile.@width
                                    if(getProfileWidth.text().length() == 0){
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
                                        def decimalLastChar = strDecimal.toString()[-1] as int

                                      

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


                                currentCreateNodeName_array << currentCreateNodeName
                                currentCreateNodeValue_array << currentCreateNodeValue
                                

                            }
                        } 
                    }
                }
                i++
            }
        }
    }


    if((isCustomPropertiesPresented == 0 | countItems_CustomProperties == 1 && itemPos1_CustomProperties == "mark_ID") && isTemplateParametersPresented == 0){
        currentCreateNodeName_array.clear()
        currentCreateNodeValue_array.clear()


        def no_of_items_value = currentOrderItem.Quantity
        def length_value = currentOrderItem.Length.text().toBigDecimal()  / 1000
        def id_mark = currentOrderItem.Length.Name


        def thickness_value = currentOrderItem.Profile.@thickness.text().toDouble()

        if(currentOrderItem.Profile.@kind.text() == "profile" && thickness_value <= 0.9){
            valueGirth = currentOrderItem.Profile.@flangeSum.text().toDouble()
            
            if(currentOrderItem.Profile.@length.text().toInteger() == 150){
                def hemRi_counter = currentOrderItem.Profile.Flanges.Flange?.count { it.@hemRi?.text()?.trim() }
                
                if(hemRi_counter == 0){
                    valueGirth = valueGirth - 10
                }

                if(hemRi_counter == 1){
                    valueGirth = valueGirth - 30
                }

                if(hemRi_counter == 2){
                    valueGirth = valueGirth - 60
                }
            }


            def decimal = valueGirth
            decimal = decimal * 0.001
            decimal = BigDecimal.valueOf(decimal).setScale(4, java.math.RoundingMode.HALF_UP)
            decimal = decimal.doubleValue()
            def strDecimal = decimal.toString()
            def length = strDecimal.length()
            def index = strDecimal.indexOf('.') + 1

            def decimalLastChar = strDecimal.toString()[-1] as int


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
            valueGirth = decimal

        }

        if(currentOrderItem.Profile.@kind.text() == "profile" && thickness_value > 0.9){
            valueGirth = currentOrderItem.Profile.@width.text().toDouble()
            if(currentOrderItem.Profile.@length.text().toInteger() == 150){
                def hemRi_counter = currentOrderItem.Profile.Flanges.Flange?.count { it.@hemRi?.text()?.trim() }
                
                if(hemRi_counter == 0){
                    valueGirth = valueGirth - 10
                }

                if(hemRi_counter == 1){
                    valueGirth = valueGirth - 30
                }

                if(hemRi_counter == 2){
                    valueGirth = valueGirth - 60
                }
            }

            def decimal = valueGirth
            decimal = decimal * 0.001
            decimal = BigDecimal.valueOf(decimal).setScale(4, java.math.RoundingMode.HALF_UP)
            decimal = decimal.doubleValue()
            def strDecimal = decimal.toString()
            def length = strDecimal.length()
            def index = strDecimal.indexOf('.') + 1

            def decimalLastChar = strDecimal.toString()[-1] as int


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
            valueGirth = decimal
        }
    }


    //currentCreateNodeName_array << currentCreateNodeName
    //currentCreateNodeValue_array << currentCreateNodeValue

    println("currentCreateNodeName_array --- " + currentCreateNodeName_array)
    println("currentCreateNodeValue_array --- " + currentCreateNodeValue_array)
}
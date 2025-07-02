                ServiceResponse.Orders.Order.OrderItems.OrderItem.each { currentOrderItem_DIVISION ->
					def isCustomPropertiesPresented_forDIVISION = currentOrderItem_DIVISION.CustomProperties.PropertiesValues.'@xml:space'.text().length()
					def countItems_CustomProperties_forDIVISION = currentOrderItem_DIVISION.CustomProperties.PropertiesValues.Item.size()
					def items_CustomProperties_forDIVISION = currentOrderItem_DIVISION.CustomProperties.PropertiesValues.'**'.findAll { it.name() == 'Item' }

					def isTemplateParametersPresented_forDIVISION = currentOrderItem_DIVISION.TemplateParameters.'@xml:space'.text().length()
					def countItems_TemplateParameters_forDIVISION = currentOrderItem_DIVISION.TemplateParameters.Item.size()
					def items_TemplateParameters_forDIVISION = currentOrderItem_DIVISION.TemplateParameters.'**'.findAll { it.name() == 'Item' }

					def isDivisionPresented = false
					def division = ""
					def i = 0

					

					if(isCustomPropertiesPresented_forDIVISION != 0){
						println("HERE")
						if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
							division = "K1"
						} else {
							while(i < countItems_CustomProperties_forDIVISION){
								if(countItems_CustomProperties_forDIVISION[i]?.@name?.text() == "DIVISION"){
									isDistributionChannelPresented = true
								}
								i++
							}
							if(isDistributionChannelPresented){
								division = currentOrderItem_DIVISION.CustomProperties.PropertiesValues.'**'.find { it.name() == 'Item' && it.@name == 'DIVISION' }?.text()
							} else {
								division = "15"
							}
						}
					} else {
						if(isTemplateParametersPresented_forDIVISION != 0){
							if(ServiceResponse.Orders.Order.BALand == "IE" | ServiceResponse.Orders.Order.BALand == "XI"){
								division = "K1"
							} else {
								while(i < countItems_TemplateParameters_forDIVISION){
									if(countItems_TemplateParameters_forDIVISION[i]?.@name?.text() == "DIVISION"){
										isDistributionChannelPresented = true
									}
									i++

									if(isDistributionChannelPresented){
										division = currentOrderItem_DIVISION.TemplateParameters.'**'.find { it.name() == 'Item' && it.@name == 'DIVISION' }?.text()
									} else {
										division = "15"
									}
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
					"DIVISION"(division)
				
				}
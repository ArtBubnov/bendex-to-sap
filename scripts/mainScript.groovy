import groovy.xml.*
import groovy.json.JsonSlurper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


    def dicst_script_return = evaluate(new File("scripts/dicts.groovy"))



    def xmlFile = new File('payload2.xml')
	def inputXml = xmlFile.getText('UTF-8')
	def ServiceResponse = new XmlSlurper().parseText(inputXml)

	def writer = new StringWriter()
	def builder = new MarkupBuilder(writer)




    def order_type_script_return = evaluate(new File("scripts/order_type.groovy"))
    
    println("order_type_return")
    println(order_type_script_return.orderType)
    println("order_type_return")
    println("\r\n" + "\r\n" + "\r\n")


    def sales_org_script_return = evaluate(new File("scripts/sales_org.groovy"))
    println("sales_org_script_return")
    println(sales_org_script_return.salesOrg)
    println("sales_org_script_return")
    println("\r\n" + "\r\n" + "\r\n")
    



    def distribution_channel_script_return = evaluate(new File("scripts/distribution_channel.groovy"))
    println("distribution_channel_script_return")
    println(distribution_channel_script_return.distribution_channel_value)
    println("distribution_channel_script_return")
    println("\r\n" + "\r\n" + "\r\n")
    //distribution_channel_value




    def division_script_return = evaluate(new File("scripts/division.groovy"))
    println("division_script_return")
    println(division_script_return.division_value)
    println("division_script_return")
    println("\r\n" + "\r\n" + "\r\n")




    def dummy_shiping_script_return = evaluate(new File("scripts/dummy_ship.groovy"))
    println("dummy_shiping_script_return")
    println(dummy_shiping_script_return.dummy_ship_value)
    println("dummy_shiping_script_return")
    println("\r\n" + "\r\n" + "\r\n")





//if(currentOrderItem.ShoppingItemType.text().contains("Shipping")){
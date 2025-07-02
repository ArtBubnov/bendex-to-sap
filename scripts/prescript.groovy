import groovy.xml.*
import java.text.SimpleDateFormat

    def xmlFile = new File('payload.xml')
	def inputXml = xmlFile.getText('UTF-8')
	def ServiceResponse = new XmlSlurper().parseText(inputXml)

	def writer = new StringWriter()
	def builder = new MarkupBuilder(writer)

    def kip_bendex_did = ServiceResponse.Orders.Order.Did
    def kip_bendex_customer_number = ServiceResponse.Orders.Order.BACompany3
    def kip_bendex_business_unit = ServiceResponse.Orders.Order.BACompany2
    def kip_bendex_description = ServiceResponse.Orders.Order.Description.text()
    
	kip_bendex_description = kip_bendex_description.replace('/', '')
	kip_bendex_description = kip_bendex_description.replace('\\', '')
	kip_bendex_description = kip_bendex_description.replace('~', '')
	kip_bendex_description = kip_bendex_description.replace('%', '')
	kip_bendex_description = kip_bendex_description.replace('*', '')
	kip_bendex_description = kip_bendex_description.replace(':', '')
	kip_bendex_description = kip_bendex_description.replace('\r\n', '')
    kip_bendex_description = kip_bendex_description.replace('\r', '')
    kip_bendex_description = kip_bendex_description.replace('\n', '')
    
    def kip_bendex_baCompany = ServiceResponse.Orders.Order.BACompany
    def kip_bendex_createdBy = ServiceResponse.Orders.Order.CreatedBy


    def incoming_date = ServiceResponse.Orders.Order.CreatedOn.text()
	

	def cleaned = incoming_date.replace("T", " ").replace("Z", "")
	

	def inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
	def outputFormat = new SimpleDateFormat("yyyy-MM-dd")
	
	def parsedDate = inputFormat.parse(cleaned)
	def kip_bendex_creation_date = outputFormat.format(parsedDate)
    




    def kip_bendex_response_result = ServiceResponse.Result
    def kip_bendex_response_details = ServiceResponse.Details
    def kip_bendex_version = ServiceResponse.Version
    def kip_jb_positionStringFlag = 'THEREISNOPOS888'
    def kip_bendex_fast_track = 'N'


return[
    kip_bendex_did: kip_bendex_did
    kip_bendex_customer_number: kip_bendex_customer_number
    kip_bendex_business_unit: kip_bendex_business_unit
    kip_bendex_description: kip_bendex_description
    kip_bendex_baCompany: kip_bendex_baCompany
    kip_bendex_createdBy: kip_bendex_createdBy
    kip_bendex_creation_date: kip_bendex_creation_date
    kip_bendex_response_result: kip_bendex_response_result
    kip_bendex_response_details: kip_bendex_response_details
    kip_bendex_version: kip_bendex_version
    kip_jb_positionStringFlag: kip_jb_positionStringFlag
    kip_bendex_fast_track: kip_bendex_fast_track
]
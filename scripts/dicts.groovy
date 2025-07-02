import groovy.json.JsonSlurper

    def prj_SapCodes_array_JSON_File = new File('prj_SapCodes_array.json').text
	def prj_SapCodes_array = new JsonSlurper().parseText(prj_SapCodes_array_JSON_File)

	def prj_coil_metal_ext_array_JSON_File = new File('prj_coil_metal_ext_array.json').text
	def prj_coil_metal_ext_array = new JsonSlurper().parseText(prj_coil_metal_ext_array_JSON_File)

	def prj_coil_finish_ext_array_JSON_File = new File('prj_coil_finish_ext_array.json').text
	def prj_coil_finish_ext_array = new JsonSlurper().parseText(prj_coil_finish_ext_array_JSON_File)

	def prj_coil_guige_ext_array_JSON_File = new File('prj_coil_guige_ext_array.json').text
	def prj_coil_guige_ext_array = new JsonSlurper().parseText(prj_coil_guige_ext_array_JSON_File)
	
	def prj_coil_colour_ext_array_JSON_File = new File('prj_coil_colour_ext_array.json').text
	def prj_coil_colour_ext_array = new JsonSlurper().parseText(prj_coil_colour_ext_array_JSON_File)

	def coverWidth2Dict_JSON_File = new File('coverWidth2Dict.json').text
	def coverWidth2Dict = new JsonSlurper().parseText(coverWidth2Dict_JSON_File)

	def front_colour_colour_JSON_File = new File('front_colour.colour.json').text
	def front_colour_colour = new JsonSlurper().parseText(front_colour_colour_JSON_File)

	def front_colour_type_JSON_File = new File('front_colour.type.json').text
	def front_colour_type = new JsonSlurper().parseText(front_colour_type_JSON_File)

	def front_colour_colour_IE_array_JSON_File = new File('front_colour.colour_IE.json').text
	def front_colour_colour_IE_array = new JsonSlurper().parseText(front_colour_colour_IE_array_JSON_File)

	def front_colour_type_IE_array_JSON_File = new File('front_colour.type_IE.json').text
	def front_colour_type_IE = new JsonSlurper().parseText(front_colour_type_IE_array_JSON_File)

	def kipBendexPrjArrayDET = new File('kipBendexPrjArrayDET.txt').text
	def kipBendexArrayDET = kipBendexPrjArrayDET.split(',').collect { it.trim() }

	def kipBendexPrjArrayCNF = new File('kipBendexPrjArrayCNF.txt').text
	def kipBendexArrayCNF = kipBendexPrjArrayCNF.split(',').collect { it.trim() }



return [
prj_SapCodes_array:prj_SapCodes_array, 
prj_coil_metal_ext_array:prj_coil_metal_ext_array,
prj_coil_finish_ext_array:prj_coil_finish_ext_array,
prj_coil_guige_ext_array:prj_coil_guige_ext_array,
prj_coil_colour_ext_array:prj_coil_colour_ext_array,
coverWidth2Dict:coverWidth2Dict,
front_colour_colour:front_colour_colour,
front_colour_type:front_colour_type,
front_colour_colour_IE_array:front_colour_colour_IE_array,
front_colour_type_IE:front_colour_type_IE,
kipBendexArrayDET:kipBendexArrayDET,
kipBendexArrayCNF:kipBendexArrayCNF
]

 
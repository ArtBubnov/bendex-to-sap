import groovy.xml.*

// Исходный XML
def xmlText = '''
<ORDER_CREATE>
<Oid>50dd3ee5-b356-45de-8743-0f9757e28ef3</Oid>
	<HEADER>
		<CUSTOMER>
			<PO_NUM>TN24092025- RE-TEST (2)</PO_NUM>
			<SOLD_TO>104151</SOLD_TO>
			<CONTACT/>
		</CUSTOMER>
		<DELIVERY_DETAILS>
			<ADDRESS>
				<SHIP_TO>907225</SHIP_TO>
				<NAME1>Kingspan Ltd.</NAME1>
				<STREET1>Unit 2 Greenfield Business Park</STREET1>
				<STREET4/>
				<STREET5/>
				<POSTL_COD1>CH8 7GJ</POSTL_COD1>
				<CITY1>Holywell</CITY1>
				<REGION>FL - Flintshire</REGION>
				<COUNTRY>GB</COUNTRY>
				<TRANSP_ZONE/>
			</ADDRESS>
			<DEL_DATE>20251001</DEL_DATE>
			<DELIVERY_HEADER_TEXT/>
		</DELIVERY_DETAILS>
		<GENERAL_DATA>
			<DID>122530</DID>
			<CREATED_BY>tnixon</CREATED_BY>
			<ORDER_TYPE>ZKCO</ORDER_TYPE>
			<SALES_ORG>GB40</SALES_ORG>
			<DISTRIBUTION_CHANNEL>G4</DISTRIBUTION_CHANNEL>
			<DIVISION>15</DIVISION>
			<BUILDING_SECTOR/>
			<KS_HEADER_TEXT_1/>
			<BUSINESS_UNIT>Kingspan</BUSINESS_UNIT>
		</GENERAL_DATA>
	</HEADER>
	<MATERIAL_ITEM>
		<MATERIAL_CODE>FL425</MATERIAL_CODE>
		<ORDER_QTY>0.35</ORDER_QTY>
		<PLANT>GB20</PLANT>
		<CONDITION/>
		<PRICE>0</PRICE>
		<DISCOUNT>0</DISCOUNT>
		<CURRENCY>GBP</CURRENCY>
		<BENDEX_POS>1.1</BENDEX_POS>
		<SUB_COMPONENT>8ded268e-aac7-407a-b470-6c31a791fb2f</SUB_COMPONENT>
		<COIL_METAL_EXT>S</COIL_METAL_EXT>
		<COIL_FINISH_EXT>G</COIL_FINISH_EXT>
		<COIL_GAUGE_EXT>1.60</COIL_GAUGE_EXT>
		<COIL_COLOUR_EXT>GALV</COIL_COLOUR_EXT>
		<LENGTH_FLAG>3-</LENGTH_FLAG>
		<COVER_WIDTH2>1250Z28</COVER_WIDTH2>
		<BENDS>4</BENDS>
		<EXTRA_OPERATIONS>P</EXTRA_OPERATIONS>
		<NO_ITEMS>2</NO_ITEMS>
		<DETAILING>
		  <NO_OF_ITEMS>2</NO_OF_ITEMS>
		  <LENGTH>0.175</LENGTH>
		  <ID_MARK>Socket Stop End Profile</ID_MARK>
		  <BUNDLE_NO>1</BUNDLE_NO>
		  <GIRTH>0</GIRTH>
		  <BENDEX_POSITION>1.1</BENDEX_POSITION>
		  <SUB_COMPONENT>nested-1</SUB_COMPONENT>
		  <SUB_COMPONENT>nested-2</SUB_COMPONENT>
		</DETAILING>
		<SUB_COMPONENT>8ded268e-aac7-407a-b470-6c31a791fb2f</SUB_COMPONENT>
	</MATERIAL_ITEM>
	<MATERIAL_ITEM>
		<MATERIAL_CODE>FL425</MATERIAL_CODE>
		<ORDER_QTY>0.35</ORDER_QTY>
		<PLANT>GB20</PLANT>
		<CONDITION/>
		<PRICE>0</PRICE>
		<DISCOUNT>0</DISCOUNT>
		<CURRENCY>GBP</CURRENCY>
		<BENDEX_POS>1.1</BENDEX_POS>
		<COIL_METAL_EXT>S</COIL_METAL_EXT>
		<COIL_FINISH_EXT>G</COIL_FINISH_EXT>
		<COIL_GAUGE_EXT>1.60</COIL_GAUGE_EXT>
		<COIL_COLOUR_EXT>GALV</COIL_COLOUR_EXT>
		<LENGTH_FLAG>3-</LENGTH_FLAG>
		<COVER_WIDTH2>1250Z28</COVER_WIDTH2>
		<BENDS>4</BENDS>
		<EXTRA_OPERATIONS>P</EXTRA_OPERATIONS>
		<NO_ITEMS>2</NO_ITEMS>
		<DETAILING>
		  <NO_OF_ITEMS>2</NO_OF_ITEMS>
		  <LENGTH>0.175</LENGTH>
		  <ID_MARK>Socket Stop End Profile</ID_MARK>
		  <BUNDLE_NO>1</BUNDLE_NO>
		  <GIRTH>0</GIRTH>
		  <BENDEX_POSITION>1.1</BENDEX_POSITION>
		  <SUB_COMPONENT>nested-1</SUB_COMPONENT>
		  <SUB_COMPONENT>nested-2</SUB_COMPONENT>
		</DETAILING>

	</MATERIAL_ITEM>
</ORDER_CREATE>
'''

// Парсим XML
def parser = new XmlParser()


def root = parser.parseText(xmlText)
println("root ----- " + root + "---- root")
// Универсальная рекурсивная функция: оставляет только первый SUB_COMPONENT на любом уровне
def keepFirstSubComponent(node) {
    if (!node.children()) return

    def subComponentSeen = false
    def toRemove = []

    node.children().each { child ->
        if (child instanceof Node && child.name() == 'SUB_COMPONENT') {
            if (!subComponentSeen) {
                subComponentSeen = true // оставляем первый
            } else {
                toRemove << child      // все остальные удаляем
            }
        }
    }

    toRemove.each { node.remove(it) }

    // Рекурсивно обрабатываем вложенные узлы
    node.children().each { child ->
        if (child instanceof Node) keepFirstSubComponent(child)
    }
}

// Применяем к каждому MATERIAL_ITEM
root.'MATERIAL_ITEM'.each { item ->
    keepFirstSubComponent(item)
}

// Красивый вывод с отступами
def writerZ = new StringWriter()
def printer = new XmlNodePrinter(new PrintWriter(writerZ), "    ")
printer.setPreserveWhitespace(true)
printer.print(root)

println writerZ.toString()

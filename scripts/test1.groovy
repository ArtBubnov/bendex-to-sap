	def timeToRequest = -2880
	def now = new Date()
			def cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
			cal.setTime(now)
			cal.add(Calendar.MINUTE, timeToRequest)
			def fromFormatted = cal.getTime().format("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone("UTC"))
			result = "&from=" + fromFormatted
			println(result)
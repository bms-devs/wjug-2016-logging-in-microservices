import groovy.transform.Field

@Field def random = new Random()
@Field def directory = new File("logstash/logs")
@Field def useJson = (this.args[0] == 'json')

def randomBoolean() { return random.nextBoolean() }
def randomCorrelationId() { return UUID.randomUUID().toString() }
def randomMethod(app) { (random.nextInt((app == "audit") ? 3 : 7) == 0) ? "POST" : "GET" }
def randomStatus() { return (random.nextInt(8) == 0) ? 500 : 200 }
def randomTime() { return random.nextInt(2000) / 1000f }

def randomKrsNo() { return String.format("%010d", random.nextInt(1000000)) }
def randomNipNo() { return String.format("%03d-%02d-%02d-%03d", random.nextInt(1000), random.nextInt(100), random.nextInt(100), random.nextInt(1000)) }

def sleepShort() { Thread.sleep(100 + random.nextInt(500)) }
def sleepLong() { Thread.sleep(1000 + random.nextInt(500)) }

def logEntry(app, url, correlationId) {
	if (useJson) {
		return "{\"app\": \"${app}\", \"method\": \"${randomMethod(app)}\", \"url\": \"${url}\", \"status\": ${randomStatus()}, \"time\": " + String.format('%.3f', randomTime()) + ", \"correlationId\": \"${correlationId}\"}"
	}
	else {
		return "${randomMethod(app)} ${url} (status:${randomStatus()} time:" + String.format('%.3f', randomTime()) + "s correlationId:${correlationId})"
	}
}

def saveLogEntry(filename, logEntry) {
	sleepShort()
	new File(directory, filename + (useJson ? "-json.log" : "-plain.log")).append(logEntry + "\n")
}

Integer.MAX_VALUE.times {
	def correlationId = randomCorrelationId()

	if (randomBoolean()) {
		saveLogEntry("audit", logEntry("audit", "http://audit.service/trail", correlationId))
		saveLogEntry("krs", logEntry("krs", "http://krs.service/krs/${randomKrsNo()}/pdf", correlationId))
	}
	else {
		saveLogEntry("audit", logEntry("audit", "http://audit.service/trail", correlationId))
		saveLogEntry("audit", logEntry("audit", "http://audit.service/trail", correlationId))
		saveLogEntry("audit", logEntry("audit", "http://audit.service/trail", correlationId))
		saveLogEntry("ceidg", logEntry("ceidg", "http://ceidg.service/company/${randomNipNo()}", correlationId))
	}

	sleepLong()
}

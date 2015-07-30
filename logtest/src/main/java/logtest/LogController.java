package logtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/logger")
@RestController
public class LogController {

	private static final Logger LOG = LoggerFactory.getLogger(LogController.class);

	private static final String MSG = "Logback and ELK rocks :)";

	/**
	 * Perform simple GET against
	 * <i>http://{host}:{port}/logger/{debug|ìnfo|warn|error}</i> url to get a new log
	 * trace
	 * @param level The log level for the new log line
	 * @return a default message
	 */
	@RequestMapping(value = "/{level}", method = RequestMethod.GET)
	public String log(@PathVariable String level) {
		switch (level.toUpperCase()) {
		case "DEBUG":
			LOG.debug(MSG);
			break;
		case "INFO":
			LOG.info(MSG);
			break;
		case "WARN":
			LOG.warn(MSG);
			break;
		default:
			LOG.error(MSG);
			throw new RuntimeException("New crash occurs !");
		}
		return level + ":" + MSG;
	}
}

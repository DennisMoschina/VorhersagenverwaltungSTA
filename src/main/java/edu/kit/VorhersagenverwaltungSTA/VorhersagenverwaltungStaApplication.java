package edu.kit.VorhersagenverwaltungSTA;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Datastream;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.RequestManager;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.Source;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.SingleSelection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VorhersagenverwaltungStaApplication {

	private static final Logger log = LoggerFactory.getLogger(VorhersagenverwaltungStaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VorhersagenverwaltungStaApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Source source = new Source("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/");
			RequestManager reuqestMager = new RequestManager(source, restTemplate);

			SingleSelection selection = new SingleSelection(ObjectType.DATASTREAM, 1);
			reuqestMager.request(selection);
			Datastream datastream = (Datastream) reuqestMager.getResult();
			log.info(String.format("stream: %s", datastream.toString()));
		};
	}
}
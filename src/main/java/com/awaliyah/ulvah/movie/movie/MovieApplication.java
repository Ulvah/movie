package com.awaliyah.ulvah.movie.movie;

import com.awaliyah.ulvah.movie.movie.entity.Producer;
import com.awaliyah.ulvah.movie.movie.entity.Winner;
import com.awaliyah.ulvah.movie.movie.repository.WinnerRepository;
import com.awaliyah.ulvah.movie.movie.service.ProducerService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MovieApplication {

	@Autowired
	private ProducerService producerService;

	@Autowired
	private WinnerRepository winnerRepository;

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext appContext) {
		return args -> {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream in = cl.getResourceAsStream("movielist.csv");
			Reader reader = new InputStreamReader(in);
			Iterable<CSVRecord> records = CSVFormat.RFC4180
					.withDelimiter(';')
					.withHeader("year","title","studios","producers","winner")
					.parse(reader);


			Map<String, Winner> winnerMap = new HashMap<String, Winner>();
			Map<Integer, Winner> winnerMapSave = new HashMap<Integer, Winner>();
			List<Integer> intervals = new ArrayList<Integer>();
			for (CSVRecord record : records) {
				if (record.getRecordNumber() == 1) {
					continue;
				}

				String producers = record.get("producers");
				Integer ano = Integer.valueOf(record.get("year"));
				String title = record.get("title");
				String studios = record.get("studios");
				String winner = record.get("winner");
				Boolean isWinner = false;
				if (!winner.isEmpty()) {
					isWinner = true;
				}

				if (isWinner == true){
					for (String strProducer : producers.split(",|\\ and ")) {
						Winner newWinner = new Winner();
						if (winnerMap.containsKey(strProducer)) {
							newWinner = winnerMap.get(strProducer.trim());
							newWinner.setFollowingWin(ano);
							int inv = ano - newWinner.getPreviousWin();
							intervals.add(inv);
							winnerMapSave.put(inv, newWinner);
							// winnerRepository.save(newWinner);
						} else {
							newWinner.setProducer(strProducer);
							newWinner.setPreviousWin(ano);
							winnerMap.put(strProducer.trim(), newWinner);
						}

					}
				}

				Producer newProducer = new Producer(ano, title, studios, producers, isWinner);
				this.producerService.saveProducer(newProducer);
			}
			int min = Collections.min(intervals);
			int max = Collections.max(intervals);
			this.winnerRepository.save(winnerMapSave.get(min));
			this.winnerRepository.save(winnerMapSave.get(max));

		};
	}

}

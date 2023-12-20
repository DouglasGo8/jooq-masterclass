package com.packtpub.jooq.masterclass.customizations.safesql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WriteTypeSafeSQLApp implements CommandLineRunner {
  public static void main(String... args) {
    SpringApplication.run(WriteTypeSafeSQLApp.class, args);
  }

  @Override
  public void run(String... args) {
    log.info("WriteTypeSafeSQLApp...");

  }
}

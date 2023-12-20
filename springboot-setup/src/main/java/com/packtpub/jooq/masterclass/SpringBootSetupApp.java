package com.packtpub.jooq.masterclass;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;


@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class SpringBootSetupApp implements CommandLineRunner {
  //
  public static void main(String[] args) {
    //
    SpringApplication.run(SpringBootSetupApp.class, args);
  }

  final ClassicModelsRepo repo;

  record Customer(String id, String userName, String firstName, String lastName) {
    //
  }

  @Override
  public void run(String... args) {
    log.info("Starter App");
    this.repo.query();
  }


  @Repository
  @AllArgsConstructor
  static class ClassicModelsRepo {
    final DSLContext jooq;

    void query() {
      log.info("Start JOOQ Queries");
      // basic JOOQ Query
      jooq.selectFrom(table("tbl_customer"))
              .where(field("id").eq("af20558e-5e77-4a6e-bb2f-fef1f14c0ee9"))
              // to defer execution, must use fetch method later
              .fetchInto(Customer.class)
              .forEach(c -> log.info("{}-{}", c.id, c.firstName));
      // Or
      //var query = jooq.select().from(table("tblcards"))
      //        .where(field("cardid").eq(1));


    }

  }
}

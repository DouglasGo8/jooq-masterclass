package com.packtpub.jooq.masterclass;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Slf4j
@QuarkusMain
public class QuarkusSetupApp implements QuarkusApplication {
  public static void main(String... args) {
    Quarkus.run(QuarkusSetupApp.class, args);
  }

  @Inject
  ClassicModelRepo repo;

  @Override
  public int run(String... args) {
    this.repo.query();
    return 0;
  }

  record Customer(String id, String userName, String firstName, String lastName) {
    //
  }

  @ApplicationScoped
  @NoArgsConstructor
  static class ClassicModelRepo {
    @Inject
    DSLContext jooq;

    void query() {
      log.info("Start JOOQ Queries");
      //
      jooq.selectFrom(table("tbl_customer"))
              .where(field("id").eq("af20558e-5e77-4a6e-bb2f-fef1f14c0ee9"))
              // to defer execution, must use fetch method later
              .fetchInto(Customer.class)
              .forEach(c -> log.info("{}-{}", c.id, c.firstName));
      //
    }

  }
}

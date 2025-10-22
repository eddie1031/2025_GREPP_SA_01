package io.eddie.dddexp.pa;


import io.eddie.dddexp.pa.application.port.out.PaPostPersistencePort;
import io.eddie.dddexp.pa.application.service.PaBlogApplication;
import io.eddie.dddexp.pa.infrastructure.PaPostRepository;
import org.junit.jupiter.api.BeforeEach;

class PaBlogApplicationTests {

    PaBlogApplication application;

    @BeforeEach
    void init() {

        PaPostPersistencePort repository = new PaPostRepository();

//        application = new PaBlogApplication(repository);

    }



}
package com.img.arena.tennis.rest.api.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.img.arena.tennis.datastore.model.Match;
import com.img.arena.tennis.datastore.model.MatchId;
import com.img.arena.tennis.rest.api.endpoint.customer.v1.resource.AvBSummaryFormatter;
import com.img.arena.tennis.rest.api.endpoint.customer.v1.resource.AvBTimeSummaryFormatter;
import com.img.arena.tennis.rest.api.endpoint.customer.v1.resource.DateTimeProvider;
import com.img.arena.tennis.rest.api.endpoint.customer.v1.resource.NullSummaryFormatter;
import com.img.arena.tennis.rest.api.serializers.JsonSerializeToStringSerializer;
import com.img.arena.tennis.rest.api.serializers.ZonedDateTimeSerializer;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.Function;

import javax.annotation.PostConstruct;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.img.arena.tennis.datastore.repository"})
@EntityScan("com.img.arena.tennis.datastore.model")
@ComponentScan({"com.img.arena.tennis.core.impl",
                "com.img.arena.tennis.rest.api.endpoint",
                "com.img.arena.tennis.rest.api.handlers"})
public class ApplicationConfiguration {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/London")));
    }

    @Bean
    public Map<String, Function<Match, String>> summaryFormatters(DateTimeProvider dateTimeProvider) {
        Map<String, Function<Match, String>> summaryFormattersMap = new HashMap<>(Map.of("AvB", new AvBSummaryFormatter(),
                                                                                         "AvBTime", new AvBTimeSummaryFormatter(dateTimeProvider)));
        summaryFormattersMap.put(null, new NullSummaryFormatter());
        return summaryFormattersMap;
    }

    @Bean
    public JavaTimeModule javaTimeModule() {
        return new JavaTimeModule();
    }

    @Bean
    public Jdk8Module jdk8Module() {
        return new Jdk8Module();
    }

    @Bean
    public SimpleModule additionalSerializers() {
        return new SimpleModule().addSerializer(MatchId.class, new JsonSerializeToStringSerializer())
                                 .addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());

    }
}

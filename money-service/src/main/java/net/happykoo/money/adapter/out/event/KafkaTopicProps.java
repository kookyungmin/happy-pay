package net.happykoo.money.adapter.out.event;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "task")
public record KafkaTopicProps(
    String rechargeTopic,
    String rechargeResultTopic,
    String firmBankingTopic,
    String firmBankingResultTopic
) {

}

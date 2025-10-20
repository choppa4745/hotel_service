//package com.example.hotel.configuration.listener;
//
//
//import com.example.hotel.entity.Booking;
//import com.example.hotel.repository.BookingRepository;
//import io.swagger.v3.oas.annotations.headers.Header;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class KafkaMessageListener {
//
//    private final BookingRepository bookingEventRepository;
//    private final RegisterRepository registerEventsRepository;
//
//    @KafkaListener(
//            topics = "${app.kafka.kafkaMessageRegisterTopic}",
//            groupId = "${app.kafka.kafkaMessageGroupId}",
//            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory"
//    )
//    public void listenRegisterEvent(@Payload RegisterEvent registerEvent,
//                                    @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
//                                    @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
//                                    @Header(value = KafkaHeaders.RECEIVED_PARTITION) Integer partition,
//                                    @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
//        log.info("Received register event: {}", registerEvent);
//        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, partition, topic, timestamp);
//
//        registerEventsRepository.save(registerEvent);
//    }
//
//    @KafkaListener(
//            topics = "${app.kafka.kafkaMessageNewBookingTopic}",
//            groupId = "${app.kafka.kafkaMessageGroupId}",
//            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory"
//    )
//    public void listenNewBookingEvent(@Payload Booking bookingEvent,
//                                      @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
//                                      @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
//                                      @Header(value = KafkaHeaders.RECEIVED_PARTITION) Integer partition,
//                                      @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
//        log.info("Received new booking event: {}", bookingEvent);
//        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, partition, topic, timestamp);
//
//        bookingEventRepository.save(bookingEvent);
//    }
//}
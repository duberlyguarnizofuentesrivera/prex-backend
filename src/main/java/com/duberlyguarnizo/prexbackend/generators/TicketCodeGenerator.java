package com.duberlyguarnizo.prexbackend.generators;

import com.duberlyguarnizo.prexbackend.model.Ticket;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class TicketCodeGenerator implements ValueGenerator<String> {
    private static final Logger logger = LoggerFactory.getLogger(TicketCodeGenerator.class);

    @Override
    public String generateValue(Session session, Object o) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); //sufficient for 25 years
        Ticket ticket = (Ticket) o;
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(chars[now.getYear() - 2022]); //2022 is the first year of the system
        String month = String.valueOf(chars[now.getMonthValue() - 1]);
        // 5 digits... up to 9,999,999 tickets
        String ticketId = String.format("%5s", Long.toString(ticket.getTicketId(), 36).toUpperCase()).replace(" ", "0");
        logger.info("TicketCodeGenerator: ticketId: {}", ticketId);
        return year + month + ticketId;
    }
}
